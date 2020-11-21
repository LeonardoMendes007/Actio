package controller.aluno;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Atividade;
import persistence.AtividadeDao;
import view.aluno.HomeAluno;
import view.card.tarefa.aluno.CardTarefaVertical;

public class HomeAlunoController {

	private HomeAluno viewHomeAluno;

	public HomeAlunoController(HomeAluno home2) {
		this.viewHomeAluno = home2;
	}

	public void verificarCards() {
	
		try {
			AtividadeDao atividadeDao = new AtividadeDao();
			
			List<Atividade> atividades = atividadeDao.findAtividadeTurma(viewHomeAluno.getAluno().getTurma());

			
			addCards(atividades);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private void addCards(List<Atividade> atividades) {
		
		for (Atividade atividade : atividades) {
			CardTarefaVertical card = new CardTarefaVertical(atividade);
			
			viewHomeAluno.addCardAtividade(card);
			
			
		}
		
	}
}
