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

		CardTarefaHorizontal cardTarefaHorizontal1 = new CardTarefaHorizontal("Exerc�cio  P1", "4� ADS - TARDE", "Programa��o Orientada a �dio", "#FF9DBA", new Date(), false);
		CardTarefaHorizontal cardTarefaHorizontal2 = new CardTarefaHorizontal("Exerc�cio  P2", "4� ADS - TARDE", "Programa��o Orientada a �dio", "#9DC4FF",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal3 = new CardTarefaHorizontal("Exerc�cio  P2", "4� ADS - TARDE", "Programa��o Orientada a �dio","#FF9DBA", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal4 = new CardTarefaHorizontal("Exerc�cio  P2", "4� ADS - TARDE", "Programa��o Orientada a �dio","#9DC4FF", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal5 = new CardTarefaHorizontal("Exerc�cio  P2", "4� ADS - TARDE", "Programa��o Orientada a �dio","#FF9DBA", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal6 = new CardTarefaHorizontal("Exerc�cio  P2", "4� ADS - TARDE", "Programa��o Orientada a �dio", "#9DC4FF",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal7 = new CardTarefaHorizontal("Exerc�cio  P2", "4� ADS - TARDE", "Programa��o Orientada a �dio", "#FF9DBA",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal8 = new CardTarefaHorizontal("Exerc�cio  P2", "4� ADS - TARDE", "Programa��o Orientada a �dio", "#9DC4FF",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal9 = new CardTarefaHorizontal("Exerc�cio  P2", "4� ADS - TARDE", "Programa��o Orientada a �dio", "#FF9DBA", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal10 = new CardTarefaHorizontal("Exerc�cio P2","4� ADS - TARDE", "Programa��o Orientada a �dio", "#9DC4FF",new Date(), true);		
	    
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
