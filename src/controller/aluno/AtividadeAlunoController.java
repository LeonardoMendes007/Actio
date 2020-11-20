package controller.aluno;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javafx.scene.layout.BorderPane;
import model.Atividade;
import persistence.AtividadeDao;
import view.aluno.AtividadeAluno;
import view.card.tarefa.aluno.CardTarefaHorizontal;

public class AtividadeAlunoController {

	private AtividadeAluno atividadeAluno;

	private List<Atividade> atividades;

	public AtividadeAlunoController(AtividadeAluno atividade) {
		this.atividadeAluno = atividade;
	}

	public void verificarCards() {

		try {
			AtividadeDao atividade = new AtividadeDao();

			atividades = atividades.findAll();

			addCards();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addCards() {

		for (Atividade atividade : atividades) {
			CardTarefaHorizontal card = new CardTarefaHorizontal(atividadeAluno.getBorderPrincipal(), atividade);

			atividadeAluno.addCardAtvidade(card);
		}

	}

	public void filtrarDisciplina(String s) {

		for (Atividade atividade : atividades) {

			if (s.equals(atividade.getTurma().getDisciplina().getNome())) {
				CardTarefaHorizontal card = new CardTarefaHorizontal(atividadeAluno.getBorderPrincipal(), atividade);

				atividadeAluno.addCardAtvidade(card);
			}
		}

	}

	public void filtrarPessoas(String s) {
		for (Atividade atividade : atividades) {

			if (s.equals("Grupo") && atividade.isGrupo()) {
				CardTarefaHorizontal card = new CardTarefaHorizontal(atividadeAluno.getBorderPrincipal(), atividade);

				atividadeAluno.addCardAtvidade(card);
			}
			
			if (s.equals("Grupo") && atividade.isGrupo()) {
				CardTarefaHorizontal card = new CardTarefaHorizontal(atividadeAluno.getBorderPrincipal(), atividade);

				atividadeAluno.addCardAtvidade(card);
			}
		}
	}

	public void filtrarStatus(String s) {
		for (Atividade atividade : atividades) {

			if () {
				CardTarefaHorizontal card = new CardTarefaHorizontal(atividadeAluno.getBorderPrincipal(), atividade);

				atividadeAluno.addCardAtvidade(card);
			}
		}
	}

}
