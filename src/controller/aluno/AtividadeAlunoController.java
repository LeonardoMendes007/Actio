package controller.aluno;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import model.Atividade;
import persistence.AtividadeDao;
import view.aluno.AtividadeAluno;
import view.card.tarefa.aluno.CardTarefaHorizontal;

public class AtividadeAlunoController {

	private AtividadeAluno viewAtividadeAluno;

	private List<Atividade> atividades;

	public AtividadeAlunoController(AtividadeAluno atividade) {
		this.viewAtividadeAluno = atividade;
	}

	public void verificarCards() {

		try {
			AtividadeDao atividadeDao = new AtividadeDao();

			atividades = atividadeDao.findAtividadeTurma(viewAtividadeAluno.getAluno().getTurma());

			addCards();

			addFiltro();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addFiltro() {

		for (Atividade atividade : atividades) {
			if (!viewAtividadeAluno.getCbDisciplina().getItems()
					.contains(atividade.getDiscTurmaProf().getDisciplina().getNome())) {
				viewAtividadeAluno.getCbDisciplina().getItems()
						.add(atividade.getDiscTurmaProf().getDisciplina().getNome());
			}
		}

		viewAtividadeAluno.getCbDisciplina().getItems().add("Todas");
	}

	private void addCards() {

		for (Atividade atividade : atividades) {
			CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(), atividade, viewAtividadeAluno.getAluno());

			viewAtividadeAluno.addCardAtvidade(card);
		}

	}

	public void filtrarDisciplina(String s) {

		viewAtividadeAluno.clearCardAtvidade();

		for (int i = 0; i < atividades.size(); i++) {

			if (s.equals(atividades.get(i).getDiscTurmaProf().getDisciplina().getNome()) || s.equals("Todas")) {
				CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
						atividades.get(i), viewAtividadeAluno.getAluno());

				viewAtividadeAluno.addCardAtvidade(card);
			}
		}

	}

	public void filtrarPessoas(String s) {

		viewAtividadeAluno.clearCardAtvidade();

		for (Atividade atividade : atividades) {

			if (s.equals("Grupo") && atividade.isGrupo() || s.equals("Todas")) {
				CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
						atividade, viewAtividadeAluno.getAluno());

				viewAtividadeAluno.addCardAtvidade(card);
			}

			if (s.equals("Individual") && !atividade.isGrupo()) {
				CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
						atividade , viewAtividadeAluno.getAluno());

				viewAtividadeAluno.addCardAtvidade(card);
			}
		}

	}

	public void filtrarStatus(String s) {
		
		viewAtividadeAluno.clearCardAtvidade();
		
		for (Atividade atividade : atividades) {
			
			
			if (atividade.getDtEntrega().after(atividade.getDtEmissao())) {
				CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
						atividade , viewAtividadeAluno.getAluno());

				viewAtividadeAluno.addCardAtvidade(card);
			}
			
		}
	}

}
