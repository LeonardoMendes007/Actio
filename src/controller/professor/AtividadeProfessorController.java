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
					.contains(atividade.getDiscTurmaProf().getTurma().getSemestre()
							+"° "+atividade.getDiscTurmaProf().getTurma().getCurso() + " - "
							+ atividade.getDiscTurmaProf().getTurma().getPeriodo())) {

				viewAtividadeProfessor.getCbTurma().getItems().add(atividade.getDiscTurmaProf().getTurma().getSemestre()
							+"° "+ atividade.getDiscTurmaProf().getTurma().getCurso()
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


	public void filtroAtividades(String status, String disciplina, String turma) {
		viewAtividadeProfessor.clearCardAtvidade();

		for (int i = 0; i < atividades.size(); i++) {

			//Sem validação de status
			if(status == null) {
				
				if(disciplina == null) {
					
					if(turma == null) {
						CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeProfessor.getBorderPrincipal(),
								atividades.get(i));

						viewAtividadeProfessor.addCardAtividade(card);
					}else {
						//Somente turma
						if (turma.equals(atividades.get(i).getDiscTurmaProf().getTurma().getSemestre()
								+"° "+atividades.get(i).getDiscTurmaProf().getTurma().getCurso() + " - "
								+ atividades.get(i).getDiscTurmaProf().getTurma().getPeriodo())  || turma.equals("Todas")) {

							CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeProfessor.getBorderPrincipal(),
									atividades.get(i));

							viewAtividadeProfessor.addCardAtividade(card);
						}
					}

				
				}else {
				
					if (disciplina.equals(atividades.get(i).getDiscTurmaProf().getDisciplina().getNome()) || disciplina.equals("Todas")) {
					
						if (turma.equals(atividades.get(i).getDiscTurmaProf().getTurma().getSemestre()
								+"° "+atividades.get(i).getDiscTurmaProf().getTurma().getCurso() + " - "
								+ atividades.get(i).getDiscTurmaProf().getTurma().getPeriodo())  || turma.equals("Todas")) {

							CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeProfessor.getBorderPrincipal(),
								atividades.get(i));

							viewAtividadeProfessor.addCardAtividade(card);
						}
					}
				}
			}else {
				//Com validacao de status
				if (atividades.get(i).getDtEntrega().after(atividades.get(i).getDtEmissao())) {
				
					if(disciplina == null) {
						
						if(turma == null) {
							CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeProfessor.getBorderPrincipal(),
									atividades.get(i));

							viewAtividadeProfessor.addCardAtividade(card);
						}else {
							//Somente turma
							if (turma.equals(atividades.get(i).getDiscTurmaProf().getTurma().getSemestre()
									+"° "+atividades.get(i).getDiscTurmaProf().getTurma().getCurso() + " - "
									+ atividades.get(i).getDiscTurmaProf().getTurma().getPeriodo())  || turma.equals("Todas")) {

								CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeProfessor.getBorderPrincipal(),
										atividades.get(i));

								viewAtividadeProfessor.addCardAtividade(card);
							}
						}

					
					}else {
					
						if (disciplina.equals(atividades.get(i).getDiscTurmaProf().getDisciplina().getNome()) || disciplina.equals("Todas")) {
						
							if (turma.equals(atividades.get(i).getDiscTurmaProf().getTurma().getSemestre()
									+"° "+atividades.get(i).getDiscTurmaProf().getTurma().getCurso() + " - "
									+ atividades.get(i).getDiscTurmaProf().getTurma().getPeriodo())  || turma.equals("Todas")) {

								CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeProfessor.getBorderPrincipal(),
									atividades.get(i));

								viewAtividadeProfessor.addCardAtividade(card);
							}
						}
					}
				}
			}
			
		}
		
	}

}
