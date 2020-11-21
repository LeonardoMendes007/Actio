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

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	private void addCards() {

		for (Atividade atividade : atividades) {
			CardTarefaHorizontal card = new CardTarefaHorizontal(atividade);

			viewAtividadeAluno.addCardAtvidade(card);
		}

	}

	public void filtrarDisciplina(String s) {

		for (int i = 0; i <atividades.size(); i++) {

			if (s.equals(atividades.get(i).getDisciplina().getNome())) {
				CardTarefaHorizontal card = new CardTarefaHorizontal(atividades.get(i));

				viewAtividadeAluno.addCardAtvidade(card);
			}
		}

	}

	public void filtrarPessoas(String s) {
		for (Atividade atividade : atividades) {

			if (s.equals("Grupo") && atividade.isGrupo()) {
				CardTarefaHorizontal card = new CardTarefaHorizontal(atividade);

				viewAtividadeAluno.addCardAtvidade(card);
			}
			
			if (s.equals("Grupo") && atividade.isGrupo()) {
				CardTarefaHorizontal card = new CardTarefaHorizontal(atividade);

				viewAtividadeAluno.addCardAtvidade(card);
			}
		}
	}

	public void filtrarStatus(String s) {
		for (Atividade atividade : atividades) {

			/*if () {
				CardTarefaHorizontal card = new CardTarefaHorizontal(atividadeAluno.getBorderPrincipal(), atividade);

				atividadeAluno.addCardAtvidade(card);
			}*/
		}
	}

}
