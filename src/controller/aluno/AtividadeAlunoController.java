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

	
	public void filtroAtividades(String status, String pessoas, String disciplina) {
		viewAtividadeAluno.clearCardAtvidade();

		for (int i = 0; i < atividades.size(); i++) {

			//Sem validação de status
			if(status == null) {
				
				if(pessoas == null) {
					
					if(disciplina == null) {

							CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
									atividades.get(i) , viewAtividadeAluno.getAluno());

							viewAtividadeAluno.addCardAtvidade(card);
						
					}else {
						//Somente disciplina
						if (disciplina.equals(atividades.get(i).getDiscTurmaProf().getDisciplina().getNome()) || disciplina.equals("Todas")) {
							CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
									atividades.get(i), viewAtividadeAluno.getAluno());

							viewAtividadeAluno.addCardAtvidade(card);
						}
					}

				
				}else {
				
					if (pessoas.equals("Grupo") && atividades.get(i).isGrupo() || pessoas.equals("Todas")) {
						if(disciplina == null) {
							CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
									atividades.get(i) , viewAtividadeAluno.getAluno());

							viewAtividadeAluno.addCardAtvidade(card);
						
						}else {
					
							if (disciplina.equals(atividades.get(i).getDiscTurmaProf().getDisciplina().getNome()) || disciplina.equals("Todas")) {
								CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
									atividades.get(i), viewAtividadeAluno.getAluno());

								viewAtividadeAluno.addCardAtvidade(card);
							}
						}
					}

					if (pessoas.equals("Individual") && !atividades.get(i).isGrupo()) {
						if(disciplina == null) {
							CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
									atividades.get(i) , viewAtividadeAluno.getAluno());

							viewAtividadeAluno.addCardAtvidade(card);
						
						}else {
					
							if (disciplina.equals(atividades.get(i).getDiscTurmaProf().getDisciplina().getNome()) || disciplina.equals("Todas")) {
								CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
									atividades.get(i), viewAtividadeAluno.getAluno());

								viewAtividadeAluno.addCardAtvidade(card);
							}
						}
					}
				}
			}else {
				//Com validacao de status
				if (atividades.get(i).getDtEntrega().after(atividades.get(i).getDtEmissao())) {
				
						if(pessoas == null) {
							
							if(disciplina == null) {

									CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
											atividades.get(i) , viewAtividadeAluno.getAluno());

									viewAtividadeAluno.addCardAtvidade(card);
								
							}else {
								//Somente disciplina
								if (disciplina.equals(atividades.get(i).getDiscTurmaProf().getDisciplina().getNome()) || disciplina.equals("Todas")) {
									CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
											atividades.get(i), viewAtividadeAluno.getAluno());

									viewAtividadeAluno.addCardAtvidade(card);
								}
							}

						
						}else {
						
							if (pessoas.equals("Grupo") && atividades.get(i).isGrupo() || pessoas.equals("Todas")) {
								if(disciplina == null) {
									CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
											atividades.get(i) , viewAtividadeAluno.getAluno());

									viewAtividadeAluno.addCardAtvidade(card);
								
								}else {
							
									if (disciplina.equals(atividades.get(i).getDiscTurmaProf().getDisciplina().getNome()) || disciplina.equals("Todas")) {
										CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
											atividades.get(i), viewAtividadeAluno.getAluno());

										viewAtividadeAluno.addCardAtvidade(card);
									}
								}
							}

							if (pessoas.equals("Individual") && !atividades.get(i).isGrupo()) {
								if(disciplina == null) {
									CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
											atividades.get(i) , viewAtividadeAluno.getAluno());

									viewAtividadeAluno.addCardAtvidade(card);
								
								}else {
							
									if (disciplina.equals(atividades.get(i).getDiscTurmaProf().getDisciplina().getNome()) || disciplina.equals("Todas")) {
										CardTarefaHorizontal card = new CardTarefaHorizontal(viewAtividadeAluno.getBorderPrincipal(),
											atividades.get(i), viewAtividadeAluno.getAluno());

										viewAtividadeAluno.addCardAtvidade(card);
									}
								}
							}
					 }

				}
			}
		}
	}

}
