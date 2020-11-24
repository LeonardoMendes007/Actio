package controller.professor;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.Atividade;
import persistence.AtividadeDao;
import view.aluno.HomeAluno;
import view.card.tarefa.aluno.CardTarefaVertical;
import view.card.tarefa.professor.CardTarefaHorizontal;
import view.professor.AtividadeProfessor;

public class AtividadeProfessorController {

	private AtividadeProfessor viewAtividadeProfessor;
	
	public AtividadeProfessorController(AtividadeProfessor atividade) {
		this.viewAtividadeProfessor = atividade;
	}
	


	public void verificarCards() {
	
		try {
			AtividadeDao atividadeDao = new AtividadeDao();
			
			List<Atividade> atividades = atividadeDao.findAtividadeId(viewAtividadeProfessor.getProfessor().getId());
			
			addCards(atividades);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private void addCards(List<Atividade> atividades) {
		
		for (Atividade atividade : atividades) {
			CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeProfessor.getBorderPrincipal(), atividade);
			
			viewAtividadeProfessor.addCardAtividade(card);
			
			
		}
	}
	
}
