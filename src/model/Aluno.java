package model;

public class Aluno extends Usuario{

	private Turma turma;
	
	public Aluno() {
		
	}
	
	public Aluno (Turma t) {
		setTurma(t);
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}
