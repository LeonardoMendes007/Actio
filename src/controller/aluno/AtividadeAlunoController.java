package controller.aluno;

import java.util.Date;

import view.aluno.AtividadeAluno;
import view.card.tarefa.aluno.CardTarefaHorizontal;

public class AtividadeAlunoController {

	private AtividadeAluno atividade;
	
	public AtividadeAlunoController(AtividadeAluno atividade) {
		this.atividade = atividade;
	}
	
	public void addCards() {

		CardTarefaHorizontal cardTarefaHorizontal1 = new CardTarefaHorizontal(atividade.getBorderPrincipal(),"Exercício  P1", "Não sei até qual e vou mudar a data\n de entrega para semana que vem...", "Programação Orientada a Ódio","#FF9DBA", new Date(), false);
		CardTarefaHorizontal cardTarefaHorizontal2 = new CardTarefaHorizontal(atividade.getBorderPrincipal(),"Exercício  P2", "Não sei até qual e vou mudar a data\n de entrega para semana que vem...", "Programação Orientada a Ódio", "#9DC4FF",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal3 = new CardTarefaHorizontal(atividade.getBorderPrincipal(),"Exercício  P2", "Não sei até qual e vou mudar a data\n de entrega para semana que vem...", "Programação Orientada a Ódio","#FF9DBA", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal4 = new CardTarefaHorizontal(atividade.getBorderPrincipal(),"Exercício  P2", "Não sei até qual e vou mudar a data\n de entrega para semana que vem...", "Programação Orientada a Ódio","#9DC4FF", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal5 = new CardTarefaHorizontal(atividade.getBorderPrincipal(),"Exercício  P2", "Não sei até qual e vou mudar a data\n de entrega para semana que vem...", "Programação Orientada a Ódio","#FF9DBA", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal6 = new CardTarefaHorizontal(atividade.getBorderPrincipal(),"Exercício  P2", "Não sei até qual e vou mudar a data\n de entrega para semana que vem...", "Programação Orientada a Ódio", "#9DC4FF",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal7 = new CardTarefaHorizontal(atividade.getBorderPrincipal(),"Exercício  P2", "Não sei até qual e vou mudar a data\n de entrega para semana que vem...", "Programação Orientada a Ódio", "#FF9DBA",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal8 = new CardTarefaHorizontal(atividade.getBorderPrincipal(),"Exercício  P2", "Não sei até qual e vou mudar a data\n de entrega para semana que vem...", "Programação Orientada a Ódio", "#9DC4FF",new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal9 = new CardTarefaHorizontal(atividade.getBorderPrincipal(),"Exercício  P2", "Não sei até qual e vou mudar a data\n de entrega para semana que vem...", "Programação Orientada a Ódio", "#FF9DBA", new Date(), true);
		CardTarefaHorizontal cardTarefaHorizontal10 = new CardTarefaHorizontal(atividade.getBorderPrincipal(),"Exercício  P2", "Não sei até qual e vou mudar a data\n de entrega para semana que vem...", "Programação Orientada a Ódio", "#9DC4FF",new Date(), true);		
	    
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
