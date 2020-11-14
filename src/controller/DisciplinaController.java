package controller;

import view.Disciplina;
import view.card.disciplina.CardDisciplina;

public class DisciplinaController {

	private Disciplina disciplina;
	
	public DisciplinaController(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public void addCards() {
		
		String[][] professores = {
				{"Antonio Carvalho", "Programa��o Orientada a �dio", "#FF9DBA"}, 
				{"Ricardo Satohi", "L�gica de Programa��o", "#9DC4FF"}, 
				{"Leandro Colevati", "Banco de Dados", "#9DFFA1"}, 
				{"Cristina", "Engenharia de Software II", "#FFDE9D"}, 
				{"Paulinho", "Sistemas da Informa��o", "#FFBA9D"}};

		for (int i = 0; i < professores.length; i++) {
			CardDisciplina card = new CardDisciplina(330.0, 175.0, professores[i][1], professores[i][0], professores[i][2]);
			disciplina.addCard(card);
		}
	}
	
	
	
}
