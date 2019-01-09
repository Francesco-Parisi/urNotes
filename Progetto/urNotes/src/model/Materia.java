package model;

public class Materia {
	public Materia(String nome) {
		this.setNome(nome);
	}

	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return this.getClass().getName()+"["+this.getNome()+"]";
	}

	private String nome;
}
