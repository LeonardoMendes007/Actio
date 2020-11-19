package model;

public class Disciplina {

	private int id;
	private String nome;
	private String cor;
	
	public Disciplina() {
		
	}
	
	public Disciplina(int id, String nome, String cor) {
		setId(id);
		setNome(nome);
		setCor(cor);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	
}
