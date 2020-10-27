package it.corso.accenture.entities;

public class Contatto{
	private String nome;
	private String numero;
	
	public Contatto(String nome, String numero) {
		super();
		this.nome = nome;
		this.numero = numero;
	}
	public Contatto() {
		super();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public boolean isUguale(Contatto contatto) {
		if(this.nome == contatto.getNome() && this.numero == contatto.getNumero()) {
			return true;
		}
		return false;
	}
}
