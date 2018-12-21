package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DriverManagerConnectionPool{
	private static DataSource ds;

	private static List<Connection> freeDbConnections;

	static {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("DB driver not found:"+ e.getMessage());
		} 
	}
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/appunti");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private static synchronized Connection createDBConnection() throws SQLException {
		try {
			Connection newConnection =ds.getConnection();
			newConnection.setAutoCommit(false);
			System.out.println("prima");
			return newConnection;
		} catch (NullPointerException e) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection newConnection = DriverManager.getConnection("jdbc:mysql://localhost/appunti?" + "user=root&password=root&useSSL=false");
				newConnection.setAutoCommit(false);
				System.out.println("seconda");
				return newConnection;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	
	}
	
	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();		
			System.out.println("connessione riuscita");
		}

		return connection;
	}

	public static synchronized void releaseConnection(Connection connection) throws SQLException {
		if(connection != null) freeDbConnections.add(connection);
	}
}