
package model;

public class AlunoEntregaTotalizado {

	private String aluno;
	private String email;
	private Double nota;

	public AlunoEntregaTotalizado(String aluno, String email, Double nota) {
		super();
		this.aluno = aluno;
		this.email = email;
		this.nota = nota;
	}

	public AlunoEntregaTotalizado() {
		super();
	}

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

}
