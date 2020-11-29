package controller.aluno;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.Atividade;
import model.Entrega;
import persistence.AtividadeDao;
import persistence.EntregaDao;
import view.aluno.HomeAluno;
import view.card.notificacao.CardNotificacao;
import view.card.tarefa.aluno.CardTarefaVertical;

public class HomeAlunoController {

	private HomeAluno viewHomeAluno;

	private List<Atividade> atividades;

	public HomeAlunoController(HomeAluno home2) {
		this.viewHomeAluno = home2;
	}

	public void verificarCards() {

		try {
			AtividadeDao atividadeDao = new AtividadeDao();

			atividades = atividadeDao.findAtividadeTurma(viewHomeAluno.getAluno().getTurma());

			addCards();

			addNotificacoes();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addNotificacoes() {

		for (Atividade atividade : atividades) {
			if (atividade.getDtEntrega().after(new Date())) {
				CardNotificacao not = new CardNotificacao(atividade.getNome(),
						atividade.getDiscTurmaProf().getDisciplina().getNome(), atividade.getDtPublicacao());

				viewHomeAluno.addCardNotificacao(not);
			}

		}

		addNotEntrega();

	}

	private void addNotEntrega() {

		try {
			EntregaDao dao = new EntregaDao();

			List<Entrega> entregas = dao.findAllEntregaAluno(viewHomeAluno.getAluno());
			
			for (Entrega entrega : entregas) {

				CardNotificacao not = new CardNotificacao(entrega.getAtividade().getNome(), "Tarefa Corrigida", new Date());

				viewHomeAluno.addCardNotificacao(not);
					
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addCards() {

		for (Atividade atividade : atividades) {
			CardTarefaVertical card = new CardTarefaVertical(viewHomeAluno.getBorderPrincipal(), atividade,
					viewHomeAluno.getAluno());

			viewHomeAluno.addCardAtividade(card);

		}

	}

}
