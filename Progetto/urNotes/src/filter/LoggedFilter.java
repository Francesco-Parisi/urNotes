package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/LoggedFilter")
public class LoggedFilter implements Filter {
	FilterConfig filterConfig;
   
    public LoggedFilter() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		Object log=((HttpServletRequest)request).getSession().getAttribute("logged");
		if(log==null) {
			
			RequestDispatcher dispatcher=filterConfig.getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}else {
			boolean logged= (boolean)log;
			//System.out.println("sono un filtro, logged: "+ logged);
			if(!logged) {
				//System.out.println("logged");
				RequestDispatcher dispatcher=filterConfig.getServletContext().getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
				
			}
		}
		
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		filterConfig=fConfig;
	}

}
