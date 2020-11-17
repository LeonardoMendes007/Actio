package controller.professor;

import java.util.Date;

import view.card.tarefa.professor.CardTarefaHorizontal;
import view.professor.AtividadeProfessor;

public class AtividadeProfessorController {

	private AtividadeProfessor atividade;
	
	public AtividadeProfessorController(AtividadeProfessor atividade) {
		this.atividade = atividade;
	}
	
	public void addCards() {

		CardTarefaHorizontal cardTarefaHorizontal1 = new CardTarefaHorizontal("Exercício  P1", "4° ADS - TARDE", "Programação Orientada a Ódio", "#FF9DBA", new Date(), false);
		CardTarefaHorizontal cardTarefaHorizontal2 = new CardTarefaHorizontal("Exercício  P2", "4° ADS - TARDE", "Programação Orientada a Ódio", "#9DC4FF",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal3 = new CardTarefaHorizontal("Exercício  P2", "4° ADS - TARDE", "Programação Orientada a Ódio","#FF9DBA", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal4 = new CardTarefaHorizontal("Exercício  P2", "4° ADS - TARDE", "Programação Orientada a Ódio","#9DC4FF", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal5 = new CardTarefaHorizontal("Exercício  P2", "4° ADS - TARDE", "Programação Orientada a Ódio","#FF9DBA", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal6 = new CardTarefaHorizontal("Exercício  P2", "4° ADS - TARDE", "Programação Orientada a Ódio", "#9DC4FF",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal7 = new CardTarefaHorizontal("Exercício  P2", "4° ADS - TARDE", "Programação Orientada a Ódio", "#FF9DBA",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal8 = new CardTarefaHorizontal("Exercício  P2", "4° ADS - TARDE", "Programação Orientada a Ódio", "#9DC4FF",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal9 = new CardTarefaHorizontal("Exercício  P2", "4° ADS - TARDE", "Programação Orientada a Ódio", "#FF9DBA", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal10 = new CardTarefaHorizontal("Exercício P2","4° ADS - TARDE", "Programação Orientada a Ódio", "#9DC4FF",new Date(), true);		
	    
		atividade.addCardAtvidade(cardTarefaHorizontal1);
	    atividade.addCardAtvidade(cardTarefaHorizontal2);
	    atividade.addCardAtvidade(cardTarefaHorizontal3);
	    atividade.addCardAtvidade(cardTarefaHorizontal4);
	    atividade.addCardAtvidade(cardTarefaHorizontal5);
	    atividade.addCardAtvidade(cardTarefaHorizontal6);
	    atividade.addCardAtvidade(cardTarefaHorizontal7);
	    atividade.addCardAtvidade(cardTarefaHorizontal8);
	    atividade.addCardAtvidade(cardTarefaHorizontal9);
	    atividade.addCardAtvidade(cardTarefaHorizontal10);
	    
	    
		
	}
	
}
