package controller.professor;

import java.util.Date;

import view.card.notificacao.CardNotificacao;
import view.card.tarefa.professor.CardTarefaVertical;
import view.professor.HomeProfessor;

public class HomeProfessorController {

	private HomeProfessor home;

	public HomeProfessorController(HomeProfessor home) {
		this.home = home;
	}

	public void addCards() {

		CardTarefaVertical cardTarefa1 = new CardTarefaVertical("Exerc�cio  P1", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio", "#FF9DBA", new Date(), false);
		CardTarefaVertical cardTarefa2 = new CardTarefaVertical("Exerc�cio  P1", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio", "#9DC4FF", new Date(), true);
		
		CardNotificacao cardNotificacao1 = new CardNotificacao("A tarefa: Trabalho BD foi corrigida.", "Clique para ver sua nota", new Date());
		CardNotificacao cardNotificacao2 = new CardNotificacao("A tarefa: Trabalho SO || est� atrasada 2 dias", "Clique para ver a atividade", new Date());
		
		home.addCardNotificacao(cardNotificacao1);
		home.addCardNotificacao(cardNotificacao2);
		
	    home.addCardAtividade(cardTarefa1);
	    home.addCardAtividade(cardTarefa2);
		
	}
}
