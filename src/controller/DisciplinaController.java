package controller;

import view.Disciplina;
import view.cardDisciplina.CardDisciplina;

public class DisciplinaController {

	private Disciplina disciplina;
	
	public DisciplinaController(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public void addCards() {
		
		String[][] professores = {{"Antonio Carvalho", "Programa��o\nOrientada a �dio"}, {"Ricardo Satohi", "L�gica\nde Programa��o"}, {"Leandro Colevati", "Banco\nde Dados"}, {"Cristina", "Engenharia\nde Software II"}, {"Paulinho", "Sistemas\nda Informa��o"}};

		for (int i = 0; i < professores.length; i++) {
			CardDisciplina card = new CardDisciplina(290.0, 175.0, professores[i][1], professores[i][0]);
			disciplina.addCard(card);
		}
	}
	
	
	
}
