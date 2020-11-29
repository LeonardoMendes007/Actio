package controller.professor;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import model.Atividade;
import model.Disciplina;
import model.DisciplinaTurmaProfessor;
import model.Professor;
import model.Turma;
import persistence.AtividadeDao;
import persistence.DisciplinaDao;
import persistence.DisciplinaTurmaProfessorDao;
import persistence.EntregaDao;
import persistence.TurmaDao;
import model.DisciplinaTurmaProfessor;
import view.Util;
import view.professor.CriarAtividade;

public class CriarAtividadeController {

	private CriarAtividade viewCriarAtividade;
	private List<Turma> turmas;
	List<Disciplina> disciplinas;
	private Atividade atividade;

	private Date dtEntrega;
	private Date dtPublicacao;

	public CriarAtividadeController(CriarAtividade viewCriarAtividade) {
		this.viewCriarAtividade = viewCriarAtividade;

		atividade = new Atividade();
	}

	public void criarAtividade() {

		try {

			dtEntrega = Date.from(
					viewCriarAtividade.getDtEntrega().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

			if (viewCriarAtividade.getTfAtividade().getText().isEmpty()
					|| viewCriarAtividade.getTfAtividade().getText().contains("\\")) {

				if (viewCriarAtividade.getTfAtividade().getText().contains("\\")) {

					Util.errorDialog("Erro", "Nome de Atividade inválido", "Erro: O nome não pode conter \"\"\"");

				} else {

					Util.errorDialog("Erro", "Nome de Atividade inválido",
							"Erro: é necessário um titulo para atividade");

				}

			} else if (dtEntrega.compareTo(new Date()) < 0) {

				Util.errorDialog("Erro", "A data de entrega não pode ser menor que a data atual",
						"Erro: data de entrega menor que data atual");

			} else {
				atividade.setNome(viewCriarAtividade.getTfAtividade().getText());

				atividade.setDescricao(viewCriarAtividade.getTaDescricao().getText());

				atividade.setGrupo(isGrupo(viewCriarAtividade.getCbGrupo().getValue()));

				atividade.setDtEntrega(dtEntrega);

				atividade.setDiscTurmaProf(criaDiscTurmaProfessor());

				atividade.setDtEmissao(new Date());

				moverArquivo();
				System.out.println(atividade.getPathArquivo());

				if (atividade.getDtPublicacao() == null) {
					atividade.setDtPublicacao(new Date());
				}

				try {
					AtividadeDao ativDao = new AtividadeDao();
					ativDao.insert(atividade);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Util.confirmationDialog("Atividade Publicada", "Atividade publicada com sucesso",
						"Sua atividade foi cadastrada e está dispovivel agora na aba atividades");

			}
		}catch (Exception e) {
			
			if(viewCriarAtividade.getTfAtividade().getText().isEmpty()) {
				viewCriarAtividade.getTfAtividade().setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}else {
				viewCriarAtividade.getTfAtividade().setBorder(null);
			}
			
			if (viewCriarAtividade.getCbGrupo().getValue() == null) {
				viewCriarAtividade.getCbGrupo().setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}else {
				viewCriarAtividade.getTfAtividade().setBorder(null);
			}
			
			
			if(viewCriarAtividade.getCbTurma().getValue()== null) {
				viewCriarAtividade.getCbTurma().setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}else {
				viewCriarAtividade.getTfAtividade().setBorder(null);
			}
			
			
			if(viewCriarAtividade.getCbDisciplina().getValue()== null) {
				viewCriarAtividade.getCbDisciplina().setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}else {
				viewCriarAtividade.getTfAtividade().setBorder(null);
			}
			
			
			if(viewCriarAtividade.getDtEntrega().getValue()== null ) {
				viewCriarAtividade.getDtEntrega().setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}else {
				viewCriarAtividade.getTfAtividade().setBorder(null);
			}
			
			
			if(viewCriarAtividade.getLblEntrega().getText().equals("Clique para adicionar Arquivo")) {
				viewCriarAtividade.getLblEntrega().setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}else {
				viewCriarAtividade.getTfAtividade().setBorder(null);
			}
			
		}

	}

	public void addDataAtividade() {

		dtEntrega = Date
				.from(viewCriarAtividade.getDtEntrega().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		dtPublicacao = Date.from(
				viewCriarAtividade.getDtDataPublicacao().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

		if (dtPublicacao.compareTo(dtEntrega) >= 0) {

			Util.errorDialog("Erro", "A data de publicação é maior que a data de entrega",
					"Erro: a data de publicação não pode ser maior que a data de entrega");

		} else if (dtEntrega.compareTo(new Date()) < 0) {
			Util.errorDialog("Erro", "A data de entrega não pode ser menor que a data atual",
					"Erro: a data de publicação não pode ser maior que a data de entrega");

		} else {
			atividade.setDtPublicacao(Date.from(viewCriarAtividade.getDtDataPublicacao().getValue()
					.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}

	}

	public boolean isGrupo(String texto) {
		if (texto.equals("Individual")) {
			return true;
		} else {
			return false;
		}
	}

	public void addDiscComboBox(Professor p) {

		try {
			DisciplinaDao dDao = new DisciplinaDao();
			disciplinas = dDao.findDisciplinaProfessor(p);

			for (int i = 0; i < disciplinas.size(); i++) {
				viewCriarAtividade.getCbDisciplina().getItems().add(i, disciplinas.get(i).getNome());
			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addTurmasComboBox(Disciplina d) {
		try {
			TurmaDao turmaDao = new TurmaDao();
			turmas = turmaDao.findTurmaDisciplina(d);

			/*
			 * for(Turma t : turmas) {
			 * viewCriarAtividade.getCbTurma().getItems().add(t.getSemestre() + "° " +
			 * t.getCurso() + " - " + t.getPeriodo()); }
			 */

			for (int i = 0; i < turmas.size(); i++) {
				viewCriarAtividade.getCbTurma().getItems().add(i, turmas.get(i).getSemestre() + "° "
						+ turmas.get(i).getCurso() + " - " + turmas.get(i).getPeriodo());

			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DisciplinaTurmaProfessor criaDiscTurmaProfessor() {

		Turma t = turmas.get(viewCriarAtividade.getCbTurma().getSelectionModel().getSelectedIndex());
		Disciplina d = disciplinas.get(viewCriarAtividade.getCbDisciplina().getSelectionModel().getSelectedIndex());

		DisciplinaTurmaProfessorDao dtpDao;
		DisciplinaTurmaProfessor dtp = null;
		DisciplinaTurmaProfessor idDiscTurmaProf = null;
		try {
			dtpDao = new DisciplinaTurmaProfessorDao();
			idDiscTurmaProf = dtpDao.buscaDisciplinaTurmaProfessor(d.getId(), t.getId(),
					viewCriarAtividade.getProfessor().getId());
			dtp = new DisciplinaTurmaProfessor(idDiscTurmaProf.getId(), d, t, viewCriarAtividade.getProfessor());

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dtp;
	}

	private void moverArquivo() {

		try {

			File source = new File(viewCriarAtividade.getFile().getAbsolutePath());

			File dest = new File(
					"tmp//" + atividade.getDiscTurmaProf().getId() + "//" + viewCriarAtividade.getDtEntrega().getValue()
							+ "//" + viewCriarAtividade.getTfAtividade().getText());

			if (dest.exists()) {
				FileUtils.forceDelete(dest);
			}

			dest = new File(
					"tmp//" + atividade.getDiscTurmaProf().getId() + "//" + viewCriarAtividade.getDtEntrega().getValue()
							+ "//" + viewCriarAtividade.getTfAtividade().getText());

			FileUtils.copyFileToDirectory(source, dest);

			// adrSystem.out.println(dest.getAbsolutePath());
			atividade.setPathArquivo(
					"tmp//" + atividade.getDiscTurmaProf().getId() + "//" + viewCriarAtividade.getDtEntrega().getValue()
							+ "//" + viewCriarAtividade.getTfAtividade().getText());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void deletarTarefa() {

		File file = viewCriarAtividade.getFile();

		if (file.exists()) {
			try {
				FileUtils.forceDelete(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			viewCriarAtividade.getLblEntrega().setText("Clique para adicionar um arquivo");
		}

	}
}
