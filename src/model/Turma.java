package model;

public class Turma {

	private int id;
	private String curso;
	private int semestre;
	private String periodo;
	private Disciplina disciplina;
	private Professor professor;
	
	public Turma() {
		
	}
	
	public Turma(int id, String curso, int semestre, String periodo, Disciplina d, Professor p) {
		setId(id);
		setCurso(curso);
		setSemestre(semestre);
		setPeriodo(periodo);
		setDisciplina(d);
		setProfessor(p);
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
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	
}
