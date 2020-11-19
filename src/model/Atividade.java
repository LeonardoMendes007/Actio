package model;

import java.util.Date;

public class Atividade {

	private int id;
	private String nome;
	private String descricao;
	private Date dtEmissao;
	private Date dtPublicacao;
	private Date dtEntrega;
	private String pathArquivo;
	private Turma turma;
	
	public Atividade() {
		
	}
	
	public Atividade(int id, String nome, String desc, Date emissao, Date publicacao, Date entrega, 
						String path, Turma t) {
		
		setId(id);
		setNome(nome);
		setDescricao(desc);
		setDtEmissao(emissao);
		setDtPublicacao(publicacao);
		setDtEntrega(entrega);
		setPathArquivo(path);
		setTurma(t);
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDtEmissao() {
		return dtEmissao;
	}
	public void setDtEmissao(Date dtEmissao) {
		this.dtEmissao = dtEmissao;
	}
	public Date getDtPublicacao() {
		return dtPublicacao;
	}
	public void setDtPublicacao(Date dtPublicacao) {
		this.dtPublicacao = dtPublicacao;
	}
	public Date getDtEntrega() {
		return dtEntrega;
	}
	public void setDtEntrega(Date dtEntrega) {
		this.dtEntrega = dtEntrega;
	}
	public String getPathArquivo() {
		return pathArquivo;
	}
	public void setPathArquivo(String pathArquivo) {
		this.pathArquivo = pathArquivo;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	
	
}
