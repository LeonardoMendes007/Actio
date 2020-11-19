package controller.aluno;

import java.sql.SQLException;
import java.util.List;

import model.Atividade;
import persistence.AtividadeDao;
import view.aluno.HomeAluno;
import view.card.tarefa.aluno.CardTarefaVertical;

public class HomeAlunoController {

	private HomeAluno home;

	public HomeAlunoController(HomeAluno home2) {
		this.home = home2;
	}

	public void verificarCards() {
		
		try {
			AtividadeDao atividade = new AtividadeDao();
			
			List<Atividade> atividades = atividades.findAll();
			
			addCards(atividades);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addCards(List<Atividade> atividades) {
		
		for (Atividade atividade : atividades) {
			CardTarefaVertical card = new CardTarefaVertical(home.getBorderPrincipal(),atividade);
			
			home.addCardAtividade(card);
		}
		
	}
}
