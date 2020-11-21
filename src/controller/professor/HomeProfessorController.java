package controller.professor;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import model.Atividade;
import persistence.AtividadeDao;
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
}

