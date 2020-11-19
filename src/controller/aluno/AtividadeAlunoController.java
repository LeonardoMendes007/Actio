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

	public AtividadeAlunoController(AtividadeAluno atividade) {
		this.atividadeAluno = atividade;
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
			CardTarefaHorizontal card = new CardTarefaHorizontal(atividadeAluno.getBorderPrincipal(),atividade);

			atividadeAluno.addCardAtvidade(card);
		}

	}

}
