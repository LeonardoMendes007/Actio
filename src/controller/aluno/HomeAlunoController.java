package controller.aluno;

import java.util.Date;

import view.aluno.HomeAluno;
import view.card.notificacao.CardNotificacao;
import view.card.tarefa.professor.CardTarefa;

public class HomeAlunoController {

	private HomeAluno home;

	public HomeAlunoController(HomeAluno home2) {
		this.home = home2;
	}

	public void addCards() {

		CardTarefa cardTarefa1 = new CardTarefa("Exercício  P1", "Não sei até qual e vou mudar a data\n de entrega para semana que vem...", "Programação Orientada a Ódio", "#FF9DBA", new Date(), false);
		CardTarefa cardTarefa2 = new CardTarefa("Exercício  P1", "Não sei até qual e vou mudar a data\n de entrega para semana que vem...", "Programação Orientada a Ódio", "#9DC4FF", new Date(), true);
		
		CardNotificacao cardNotificacao1 = new CardNotificacao("A tarefa: Trabalho BD foi corrigida.", "Clique para ver sua nota", new Date());
		CardNotificacao cardNotificacao2 = new CardNotificacao("A tarefa: Trabalho SO || está atrasada 2 dias", "Clique para ver a atividade", new Date());
		
		home.addCardNotificacao(cardNotificacao1);
		home.addCardNotificacao(cardNotificacao2);
		
	    home.addCardAtividade(cardTarefa1);
	    home.addCardAtividade(cardTarefa2);
		
	}
}
