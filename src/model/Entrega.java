package model;

import java.util.Date;

public class Entrega {

	private int id;
	private String pathArquivos;
	private Date dtEntrega;
	private Double nota;
	private String comentario;
	private Atividade atividade;
	private Aluno aluno;

	public Entrega() {

	}

	public Entrega(int id, String pathArquivo, Date dtEntrega, double nota, Atividade ativ, Aluno aluno) {
		setId(id);
		setPathArquivos(pathArquivo);
		setDtEntrega(dtEntrega);
		setNota(nota);
		setAtividade(ativ);
		setAluno(aluno);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPathArquivos() {
		return pathArquivos;
	}

	public void setPathArquivos(String pathArquivos) {
		this.pathArquivos = pathArquivos;
	}

	public Date getDtEntrega() {
		return dtEntrega;
	}

	public void setDtEntrega(Date dtEntrega) {
		this.dtEntrega = dtEntrega;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
