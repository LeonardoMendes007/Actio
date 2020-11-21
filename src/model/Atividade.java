package model;

import java.sql.Date;

public class Atividade {

	private int id;
	private String nome;
	private String descricao;
	private Date dtEmissao;
	private Date dtPublicacao;
	private Date dtEntrega;
	private String pathArquivo;
	private boolean grupo;
	private DisciplinaTurmaProfessor discTurmaProf;
	
	public Atividade() {

	}

	public Atividade(int id, String nome, String descricao, Date dtEmissao, Date dtPublicacao, Date dtEntrega,
			String pathArquivo, boolean grupo, DisciplinaTurmaProfessor dtp) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dtEmissao = dtEmissao;
		this.dtPublicacao = dtPublicacao;
		this.dtEntrega = dtEntrega;
		this.pathArquivo = pathArquivo;
		this.grupo = grupo;
		this.discTurmaProf = dtp;
	}
	
	
	

	public Atividade(String nome, String descricao, Date dtEmissao, boolean grupo, Turma turma) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.dtEmissao = dtEmissao;
		this.grupo = grupo;
		
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

	

	public boolean isGrupo() {
		return grupo;
	}

	public void setGrupo(boolean grupo) {
		this.grupo = grupo;
	}

	

	public DisciplinaTurmaProfessor getDiscTurmaProf() {
		return discTurmaProf;
	}

	public void setDiscTurmaProf(DisciplinaTurmaProfessor discTurmaProf) {
		this.discTurmaProf = discTurmaProf;
	}
	
	

}
