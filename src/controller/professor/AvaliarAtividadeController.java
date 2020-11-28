package controller.professor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import model.Aluno;
import model.Atividade;
import model.Entrega;
import persistence.EntregaDao;
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
		} catch (ClassNotFoundException | SQLException e) {
			
			System.err.println("Erro ao carregar aluno");
			
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

		if (!viewAvaliarAtividade.getTfNota().getText().isEmpty()) {

			entrega.setNota(Double.parseDouble(viewAvaliarAtividade.getTfNota().getText()));

			try {
				EntregaDao dao = new EntregaDao();

				dao.updateProfessor(entrega);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
