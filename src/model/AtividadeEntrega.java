package model;

import java.util.Date;

public class AtividadeEntrega {
	
	private String atividade;
	private String dtEntrega;
	private String Arquivo;
	private String dtTermino;
	private Double nota;
	
	

	public AtividadeEntrega(String atividade, String dtEntrega, String arquivo, String dtTermino, Double nota) {
		super();
		this.atividade = atividade;
		this.dtEntrega = dtEntrega;
		Arquivo = arquivo;
		this.dtTermino = dtTermino;
		this.nota = nota;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public String getArquivo() {
		return Arquivo;
	}

	public void setArquivo(String arquivo) {
		Arquivo = arquivo;
	}

	

	public String getDtEntrega() {
		return dtEntrega;
	}

	public void setDtEntrega(String dtEntrega) {
		this.dtEntrega = dtEntrega;
	}

	public String getDtTermino() {
		return dtTermino;
	}

	public void setDtTermino(String dtTermino) {
		this.dtTermino = dtTermino;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "AtividadeEntrega [atividade=" + atividade + ", dtEntrega=" + dtEntrega + ", Arquivo=" + Arquivo
				+ ", dtTermino=" + dtTermino + ", nota=" + nota + "]";
	}
	
	


	
	
	

}
