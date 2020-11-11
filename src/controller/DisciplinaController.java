package controller;

import view.Disciplina;
import view.cardDisciplina.CardDisciplina;

public class DisciplinaController {

	private Disciplina disciplina;
	
	public DisciplinaController(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public void addCards() {
		
		String[][] professores = {{"Antonio Carvalho", "Programação\nOrientada a Ódio"}, {"Ricardo Satohi", "Lógica\nde Programação"}, {"Leandro Colevati", "Banco\nde Dados"}, {"Cristina", "Engenharia\nde Software II"}, {"Paulinho", "Sistemas\nda Informação"}};

		for (int i = 0; i < professores.length; i++) {
			CardDisciplina card = new CardDisciplina(290.0, 175.0, professores[i][1], professores[i][0]);
			disciplina.addCard(card);
		}
	}
	
	
	
}
