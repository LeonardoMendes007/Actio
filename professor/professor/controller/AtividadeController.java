package professor.controller;

import java.util.Date;

import professor.view.card.tarefa.CardTarefaHorizontal;
import professor.view.Atividade;

public class AtividadeController {

	private Atividade atividade;
	
	public AtividadeController(Atividade atividade) {
		this.atividade = atividade;
	}
	
	public void addCards() {

		CardTarefaHorizontal cardTarefaHorizontal1 = new CardTarefaHorizontal("Exerc�cio  P1", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio","#FF9DBA", new Date(), false);
		CardTarefaHorizontal cardTarefaHorizontal2 = new CardTarefaHorizontal("Exerc�cio  P2", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio", "#9DC4FF",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal3 = new CardTarefaHorizontal("Exerc�cio  P2", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio","#FF9DBA", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal4 = new CardTarefaHorizontal("Exerc�cio  P2", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio","#9DC4FF", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal5 = new CardTarefaHorizontal("Exerc�cio  P2", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio","#FF9DBA", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal6 = new CardTarefaHorizontal("Exerc�cio  P2", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio", "#9DC4FF",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal7 = new CardTarefaHorizontal("Exerc�cio  P2", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio", "#FF9DBA",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal8 = new CardTarefaHorizontal("Exerc�cio  P2", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio", "#9DC4FF",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal9 = new CardTarefaHorizontal("Exerc�cio  P2", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio", "#FF9DBA", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal10 = new CardTarefaHorizontal("Exerc�cio  P2", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio", "#9DC4FF",new Date(), true);		
	    
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
