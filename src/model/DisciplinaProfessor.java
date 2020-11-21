package model;

public class DisciplinaProfessor {

	private Disciplina disciplina;
	private Professor professor;
	
	public DisciplinaProfessor() {
		
	}
	
	public DisciplinaProfessor(Disciplina d, Professor p) {
		setDisciplina(d);
		setProfessor(p);
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
