package controller.professor;

import java.sql.SQLException;
import java.util.List;

import model.Atividade;
import persistence.AtividadeDao;
import view.card.tarefa.professor.CardTarefaHorizontal;
import view.professor.AtividadeProfessor;

public class AtividadeProfessorController {

	private AtividadeProfessor viewAtividadeProfessor;

	private List<Atividade> atividades;

	public AtividadeProfessorController(AtividadeProfessor atividade) {
		this.viewAtividadeProfessor = atividade;
	}

	public void verificarCards() {

		try {
			AtividadeDao atividadeDao = new AtividadeDao();

			atividades = atividadeDao.findAtividadeTurmaProfessor(viewAtividadeProfessor.getProfessor());

			addCards(atividades);

			addFiltro();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void addCards(List<Atividade> atividades) {

		for (Atividade atividade : atividades) {

			System.out.println(atividade.getNome());
			CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeProfessor.getBorderPrincipal(),
					atividade);

			viewAtividadeProfessor.addCardAtividade(card);

		}
	}

	private void addFiltro() {

		for (Atividade atividade : atividades) {
			if (!viewAtividadeProfessor.getCbDisciplina().getItems()
					.contains(atividade.getDiscTurmaProf().getDisciplina().getNome())) {
				viewAtividadeProfessor.getCbDisciplina().getItems()
						.add(atividade.getDiscTurmaProf().getDisciplina().getNome());
			}

			if (!viewAtividadeProfessor.getCbTurma().getItems()
					.contains(atividade.getDiscTurmaProf().getTurma().getCurso() + " - "
							+ atividade.getDiscTurmaProf().getTurma().getPeriodo())) {

				viewAtividadeProfessor.getCbTurma().getItems().add(atividade.getDiscTurmaProf().getTurma().getPeriodo()+"° "+ atividade.getDiscTurmaProf().getTurma().getCurso()
						+ " - " + atividade.getDiscTurmaProf().getTurma().getPeriodo());

			}
		}

		viewAtividadeProfessor.getCbDisciplina().getItems().add("Todas");
		viewAtividadeProfessor.getCbTurma().getItems().add("Todas");
	}

	private void addCards() {

		for (Atividade atividade : atividades) {
			CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeProfessor.getBorderPrincipal(),
					atividade);

			viewAtividadeProfessor.addCardAtividade(card);
		}

	}

	public void filtrarDisciplina(String s) {

		viewAtividadeProfessor.clearCardAtvidade();

		for (int i = 0; i < atividades.size(); i++) {

			if (s.equals(atividades.get(i).getDiscTurmaProf().getDisciplina().getNome()) || s.equals("Todas")) {
				CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeProfessor.getBorderPrincipal(),
						atividades.get(i));

				viewAtividadeProfessor.addCardAtividade(card);
			}
		}

	}

	public void filtrarTurma(String s) {

		viewAtividadeProfessor.clearCardAtvidade();

		for (int i = 0; i < atividades.size(); i++) {

			if (s.equals(atividades.get(i).getDiscTurmaProf().getTurma().getCurso() + " - "
					+ atividades.get(i).getDiscTurmaProf().getTurma().getPeriodo())  || s.equals("Todas")) {

				CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeProfessor.getBorderPrincipal(),
						atividades.get(i));

				viewAtividadeProfessor.addCardAtividade(card);
			}
		}

	}

	public void filtrarStatus(String s) {

		viewAtividadeProfessor.clearCardAtvidade();

		for (Atividade atividade : atividades) {

			if (atividade.getDtEntrega().after(atividade.getDtEmissao())) {
				CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeProfessor.getBorderPrincipal(),
						atividade);

				viewAtividadeProfessor.addCardAtividade(card);
			}

		}
	}

}
