package controller.professor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import model.Aluno;
import model.Atividade;
import model.Entrega;
import persistence.EntregaDao;
import view.Util;
import view.professor.AvaliarAtividade;

public class AvaliarAtividadeController {

	private AvaliarAtividade viewAvaliarAtividade;

	private Aluno aluno;

	private Atividade atividade;

	private Entrega entrega;

	public AvaliarAtividadeController(AvaliarAtividade viewAvaliarAtividade, Aluno aluno, Atividade atividade) {
		this.viewAvaliarAtividade = viewAvaliarAtividade;
		this.aluno = aluno;
		this.atividade = atividade;

		try {
			EntregaDao dao = new EntregaDao();

			entrega = dao.findEntregaAtividadeAluno(atividade, aluno);

			initCampos();
		} catch (ClassNotFoundException | SQLException e) {

			System.err.println("Erro ao carregar aluno");

		}

	}

	private void initCampos() {

		if (entrega.getNota() == null) {

			viewAvaliarAtividade.getTfNota().setText(" - ");

		} else {

			viewAvaliarAtividade.getTfNota().setText("" + entrega.getNota());

		}

		System.out.println(entrega.getComentario());

		if (entrega.getComentario() != null) {
			viewAvaliarAtividade.getTaComentarios().setText(entrega.getComentario());
		}

	}

	public void baixarArquivoEntrega() {

		try {
			if (entrega != null) {

				System.out.println(entrega.getPathArquivos());

				Desktop.getDesktop().open(new File(entrega.getPathArquivos()));

			} else {

				System.err.println("Entrega não existe");

			}

		} catch (IOException e) {
			System.err.println("Entrega não existe");
		}

	}

	public void avaliarAtividade() {

		if (!viewAvaliarAtividade.getTfNota().getText().isEmpty()
				|| Double.parseDouble(viewAvaliarAtividade.getTfNota().getText()) > 10) {

			entrega.setNota(Double.parseDouble(viewAvaliarAtividade.getTfNota().getText()));

			entrega.setComentario(viewAvaliarAtividade.getTaComentarios().getText());

			try {
				EntregaDao dao = new EntregaDao();

				dao.updateProfessor(entrega);

				Util.confirmationDialog("Sucesso ", "A tarefa foi corrigida com sucesso",
						"Você poderá ver as atividades corrigidas e reavaliar na aba atividades");

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		} else {
			if(Double.parseDouble(viewAvaliarAtividade.getTfNota().getText()) > 10) {
				Util.warningDialog("Nota inválida", "A nota não pode ser maior do que 10", "A nota deve ser de 0 a 10");
			}else {
				Util.warningDialog("Erro", "A nota não pode ser vázia", "Preencha a nota de 0 a 10 no campo notas");
			}
		}

	}

}
