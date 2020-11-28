package model;

public class DisciplinaTurmaProfessor {
	
	private int id;
	private Disciplina disciplina;
	private Turma turma;
	private Professor professor;
	
	
	
	public DisciplinaTurmaProfessor() {
		
	}
	
	public DisciplinaTurmaProfessor(int id, Disciplina d,Turma t, Professor p) {
		setId(id);
		setDisciplina(d);
		setTurma(t);
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

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
