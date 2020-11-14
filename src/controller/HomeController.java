package controller;

import java.util.Date;

import professor.view.card.tarefa.CardTarefa;
import view.Home;
import view.card.notificacao.CardNotificacao;

public class HomeController {

	private Home home;

	public HomeController(Home home2) {
		this.home = home2;
	}

	public void addCards() {

		CardTarefa cardTarefa1 = new CardTarefa("Exerc�cio  P1", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio", "#FF9DBA", new Date(), false);
		CardTarefa cardTarefa2 = new CardTarefa("Exerc�cio  P1", "N�o sei at� qual e vou mudar a data\n de entrega para semana que vem...", "Programa��o Orientada a �dio", "#9DC4FF", new Date(), true);
		
		CardNotificacao cardNotificacao1 = new CardNotificacao("A tarefa: Trabalho BD foi corrigida.", "Clique para ver sua nota", new Date());
		CardNotificacao cardNotificacao2 = new CardNotificacao("A tarefa: Trabalho SO || est� atrasada 2 dias", "Clique para ver a atividade", new Date());
		
		home.addCardNotificacao(cardNotificacao1);
		home.addCardNotificacao(cardNotificacao2);
		
	    home.addCardAtividade(cardTarefa1);
	    home.addCardAtividade(cardTarefa2);
		
	}
}
