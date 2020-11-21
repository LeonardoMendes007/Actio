package model;

import java.util.List;

public class Turma {

	private int id;
	private String curso;
	private int semestre;
	private String periodo;

	
	public Turma() {
		
	}
	
	public Turma(int id, String curso, int semestre, String periodo) {
		setId(id);
		setCurso(curso);
		setSemestre(semestre);
		setPeriodo(periodo);
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	
	
}
