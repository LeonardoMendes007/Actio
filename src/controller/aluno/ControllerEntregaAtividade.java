package controller.aluno;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;

import model.Aluno;
import model.Atividade;
import model.Entrega;
import persistence.EntregaDao;
import view.Util;
import view.aluno.EntregaAtividadeAluno;

public class ControllerEntregaAtividade {

	private EntregaAtividadeAluno viewEntregaAtividade;

	private Entrega entrega;

	private Aluno aluno;

	private Atividade atividade;

	public ControllerEntregaAtividade(EntregaAtividadeAluno viewEntregaAtividade, Atividade atividade, Aluno aluno) {

		this.viewEntregaAtividade = viewEntregaAtividade;

		this.aluno = aluno;

		this.atividade = atividade;

		verificarEnvio();

	}

	private void verificarEnvio() {

		try {
			EntregaDao dao = new EntregaDao();

			entrega = dao.findEntregaAtividadeAluno(atividade, aluno);

			if (entrega != null) {

				File file = new File(entrega.getPathArquivos());

				viewEntregaAtividade.getLblEntrega().setText(file.getName());

			}

			if (viewEntregaAtividade.getAtividade().getDtEntrega().before(new Date())) {
				viewEntregaAtividade.getBtAdicionar().setVisible(false);
				viewEntregaAtividade.getBtRemover().setVisible(false);
				viewEntregaAtividade.getBtEntregar().setVisible(false);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void entregarTarefa() {

		if (entrega == null) {
			inserirNoBanco();
		} else {
			updateNoBanco();
		}

	}

	private void updateNoBanco() {
		try {
			EntregaDao dao = new EntregaDao();

			entrega.setPathArquivos("tmp//"
					+ viewEntregaAtividade.getAtividade().getDiscTurmaProf().getDisciplina().getId() + "//"
					+ viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getId() + "//"
					+ viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getPeriodo() + "//"
					+ viewEntregaAtividade.getAtividade().getId() + "//" + viewEntregaAtividade.getAluno().getId()
					+ "//" + viewEntregaAtividade.getFile().getName());

			dao.updateAluno(entrega);

			moverArquivo();
			
			GregorianCalendar gc = new GregorianCalendar();
			
			gc.setTime(entrega.getDtEntrega());
			
			Util.confirmationDialog("Sucesso", "Atulização na entrega concluida", "A sua entrega foi modificada.  \n"
					+ "você poderá modifica-la até dia " + gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + (gc.get(GregorianCalendar.MONTH)+1) + " - " + gc.get(GregorianCalendar.HOUR_OF_DAY) + ":" + gc.get(GregorianCalendar.MINUTE));

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private void inserirNoBanco() {
		try {
			EntregaDao dao = new EntregaDao();

			entrega = new Entrega();

			entrega.setAtividade(viewEntregaAtividade.getAtividade());
			entrega.setAluno(viewEntregaAtividade.getAluno());
			entrega.setPathArquivos("tmp//"
					+ viewEntregaAtividade.getAtividade().getDiscTurmaProf().getDisciplina().getId() + "//"
					+ viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getId() + "//"
					+ viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getPeriodo() + "//"
					+ viewEntregaAtividade.getAtividade().getId() + "//" + viewEntregaAtividade.getAluno().getId()
					+ "//" + viewEntregaAtividade.getFile().getName());

			dao.insert(entrega);

			moverArquivo();
			
			Util.confirmationDialog("Sucesso", "Entrega efetuada com sucesso", "A sua entrega foi feita agora é a hora de rezar");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private void moverArquivo() {

		try {

			File source = new File(viewEntregaAtividade.getFile().getAbsolutePath());

			File dest = new File("tmp//"
					+ viewEntregaAtividade.getAtividade().getDiscTurmaProf().getDisciplina().getId() + "//"
					+ viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getId() + "//"
					+ viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getPeriodo() + "//"
					+ viewEntregaAtividade.getAtividade().getId() + "//" + viewEntregaAtividade.getAluno().getId());

			if (dest.exists()) {
				FileUtils.forceDelete(dest);
			}

			dest = new File("tmp//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getDisciplina().getId()
					+ "//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getId() + "//"
					+ viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getPeriodo() + "//"
					+ viewEntregaAtividade.getAtividade().getId() + "//" + viewEntregaAtividade.getAluno().getId());

			FileUtils.copyFileToDirectory(source, dest);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void baixarArquivoAtividade() {

		try {
			Desktop.getDesktop().open(new File(viewEntregaAtividade.getAtividade().getPathArquivo()));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void deletarTarefa() {

		if (entrega != null) {
			EntregaDao dao;
			try {
				dao = new EntregaDao();

				File file = new File(entrega.getPathArquivos());

				dao.delete(entrega);

				if (file.exists()) {
					FileUtils.forceDelete(file);

					viewEntregaAtividade.getLblEntrega().setText("Clique para adicionar um arquivo");
				}

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		viewEntregaAtividade.getLblEntrega().setText("Clique para adicionar um arquivo");

	}

}
