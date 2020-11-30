package controller.professor;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.Atividade;
import model.Entrega;
import persistence.AtividadeDao;
import persistence.EntregaDao;
import view.card.notificacao.CardNotificacao;
import view.card.tarefa.professor.CardTarefaVertical;
import view.professor.HomeProfessor;

public class HomeProfessorController {

	private HomeProfessor viewHomeProfessor;

	public HomeProfessorController(HomeProfessor home) {
		this.viewHomeProfessor = home;
	}

	public void verificarCards() {
		
		try {
			AtividadeDao atividadeDao = new AtividadeDao();
			
			List<Atividade> atividades = atividadeDao.findAtividadeTurmaProfessor(viewHomeProfessor.getProfessor());

			
			addCards(atividades);
			
			addCorrigir();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private void addCards(List<Atividade> atividades) {
		
		for (Atividade atividade : atividades) {
			CardTarefaVertical card = new CardTarefaVertical(viewHomeProfessor.getBorderPrincipal(), atividade);
			
			viewHomeProfessor.addCardAtividade(card);
			
			
		}
		
	}

	private void addCorrigir() {

		try {
			EntregaDao dao = new EntregaDao();

			List<Entrega> entregas = dao.findAllParaCorrigir(viewHomeProfessor.getProfessor());
			
			for (Entrega entrega : entregas) {

				CardNotificacao not = new CardNotificacao(entrega.getAtividade().getNome(), "Tarefa Para Corrigir", entrega.getDtEntrega());

				viewHomeProfessor.addCardNotificacao(not);
					
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}

