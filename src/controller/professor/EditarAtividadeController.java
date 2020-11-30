package controller.professor;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import persistence.TurmaDao;
import view.Util;
import view.professor.CriarAtividade;
import view.professor.EditarAtividade;

public class EditarAtividadeController {

	private EditarAtividade viewEditarAtividade;
	private List<Turma> turmas;
	List<Disciplina> disciplinas;
	private ObservableList<String> listaDiscCb;
	private ObservableList<String> listaTurmaCb;
	private ObservableList<String> listaGrupoCb;
	private Atividade atividade;

	private Date dtEntrega;
	private Date dtPublicacao;

	public EditarAtividadeController(EditarAtividade viewEditarAtividade) {
		this.viewEditarAtividade = viewEditarAtividade;

		atividade = viewEditarAtividade.getAtividade();
		listaGrupoCb = FXCollections.observableArrayList();
	}

	public void preencherCbDisciplina() {
		Disciplina d = new Disciplina();
		for (int i = 0; i < disciplinas.size(); i++) {
			if(disciplinas.get(i).getNome().equals(atividade.getDiscTurmaProf().getDisciplina().getNome())) {
			
				viewEditarAtividade.getCbDisciplina().setValue(listaDiscCb.get(i));
				d = disciplinas.get(i);
				//addTurmasComboBox(disciplinas.get(i));
			}
		}

	}
	
	
	public void preencherCbGrupo() {
		listaGrupoCb.addAll("Individual", " Grupo "); 
		viewEditarAtividade.getCbGrupo().setItems(listaGrupoCb);
		
		
		if(atividade.isGrupo() == true) {
			viewEditarAtividade.getCbGrupo().setValue(listaGrupoCb.get(1));
		}else {
			viewEditarAtividade.getCbGrupo().setValue(listaGrupoCb.get(0));

		}
	}
	
	public void preencherCbTurma() {
		
		for (int i = 0; i < turmas.size(); i++) {
			if((turmas.get(i).getSemestre() + "° "+ turmas.get(i).getCurso() + " - " + turmas.get(i).getPeriodo()).equals(
					(atividade.getDiscTurmaProf().getTurma().getSemestre() + "° "+ atividade.getDiscTurmaProf().getTurma().getCurso() + " - " + atividade.getDiscTurmaProf().getTurma().getPeriodo()))) {
			
				viewEditarAtividade.getCbTurma().setValue(listaTurmaCb.get(i));
			}
		}
	}
	public void criarAtividade() {

		try {

			dtEntrega = Date.from(
					viewEditarAtividade.getDtEntrega().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

			if (viewEditarAtividade.getTfAtividade().getText().isEmpty()
					|| viewEditarAtividade.getTfAtividade().getText().contains("\\")) {

				if (viewEditarAtividade.getTfAtividade().getText().contains("\\")) {

					Util.errorDialog("Erro", "Nome de Atividade inválido", "Erro: O nome não pode conter \"\"\"");

				} else {

					Util.errorDialog("Erro", "Nome de Atividade inválido",
							"Erro: é necessário um titulo para atividade");

				}

			} else if (dtEntrega.compareTo(new Date()) < 0) {

				Util.errorDialog("Erro", "A data de entrega não pode ser menor que a data atual",
						"Erro: data de entrega menor que data atual");

			} else {
				atividade.setNome(viewEditarAtividade.getTfAtividade().getText());

				atividade.setDescricao(viewEditarAtividade.getTaDescricao().getText());

				atividade.setGrupo(isGrupo(viewEditarAtividade.getCbGrupo().getValue()));

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
					ativDao.update(atividade);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Util.confirmationDialog("Atividade Alterada", "Atividade alterada com sucesso",
						"Sua atividade foi alterada e está dispovivel na aba atividades");

			}
		}catch (Exception e) {
			
			if(viewEditarAtividade.getTfAtividade().getText().isEmpty()) {
				viewEditarAtividade.getTfAtividade().setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}else {
				viewEditarAtividade.getTfAtividade().setBorder(null);
			}
			
			if (viewEditarAtividade.getCbGrupo().getValue() == null) {
				viewEditarAtividade.getCbGrupo().setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}else {
				viewEditarAtividade.getTfAtividade().setBorder(null);
			}
			
			
			if(viewEditarAtividade.getCbTurma().getValue()== null) {
				viewEditarAtividade.getCbTurma().setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}else {
				viewEditarAtividade.getTfAtividade().setBorder(null);
			}
			
			
			if(viewEditarAtividade.getCbDisciplina().getValue()== null) {
				viewEditarAtividade.getCbDisciplina().setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}else {
				viewEditarAtividade.getTfAtividade().setBorder(null);
			}
			
			
			if(viewEditarAtividade.getDtEntrega().getValue()== null ) {
				viewEditarAtividade.getDtEntrega().setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}else {
				viewEditarAtividade.getTfAtividade().setBorder(null);
			}
			
			
			if(viewEditarAtividade.getLblEntrega().getText().equals("Clique para adicionar Arquivo")) {
				viewEditarAtividade.getLblEntrega().setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
			}else {
				viewEditarAtividade.getTfAtividade().setBorder(null);
			}
			
		}

	}

	public void addDataAtividade() {

		dtEntrega = Date
				.from(viewEditarAtividade.getDtEntrega().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		dtPublicacao = Date.from(
				viewEditarAtividade.getDtDataPublicacao().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

		if (dtPublicacao.compareTo(dtEntrega) >= 0) {

			Util.errorDialog("Erro", "A data de publicação é maior que a data de entrega",
					"Erro: a data de publicação não pode ser maior que a data de entrega");

		} else if (dtEntrega.compareTo(new Date()) < 0) {
			Util.errorDialog("Erro", "A data de entrega não pode ser menor que a data atual",
					"Erro: a data de publicação não pode ser maior que a data de entrega");

		} else {
			atividade.setDtPublicacao(Date.from(viewEditarAtividade.getDtDataPublicacao().getValue()
					.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
		
		System.out.println(atividade.getDtPublicacao());
		criarAtividade();

	}

	public boolean isGrupo(String texto) {
		if (texto.equals("Individual")) {
			return true;
		} else {
			return false;
		}
	}

	public void addDiscComboBox(Professor p) {

		listaDiscCb = FXCollections.observableArrayList();
		listaTurmaCb = FXCollections.observableArrayList();
		
		
		
		try {
			DisciplinaDao dDao = new DisciplinaDao();
			disciplinas = dDao.findDisciplinaProfessor(p);

			for (int i = 0; i < disciplinas.size(); i++) {
				listaDiscCb.add(i, disciplinas.get(i).getNome());
			}
			
			viewEditarAtividade.getCbDisciplina().setItems(listaDiscCb);
			
			//System.out.println(indexCb(listaDiscCb));
			preencherCbDisciplina();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addTurmasComboBox(Disciplina d) {
		
		

		try {
			TurmaDao turmaDao = new TurmaDao();
			turmas = turmaDao.findTurmaDisciplina(d);


			for (int i = 0; i < turmas.size(); i++) {
				String turmaString =  turmas.get(i).getSemestre() + "° "
						+ turmas.get(i).getCurso() + " - " + turmas.get(i).getPeriodo();
				
				listaTurmaCb.add(i,turmaString);

			}

			viewEditarAtividade.getCbTurma().setItems(listaTurmaCb);
			//viewEditarAtividade.getCbTurma().setItems(listaTurmaCb);

			preencherCbTurma();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int indexCb(ObservableList<String> lista) {
		for (int i = 0; i < disciplinas.size(); i++) {
			if(atividade.getDiscTurmaProf().getDisciplina().getNome().equals(lista.get(i))) {
				return i;
			}
		}	
		return -1;
	}
	
		
	

	public DisciplinaTurmaProfessor criaDiscTurmaProfessor() {

		Turma t = turmas.get(viewEditarAtividade.getCbTurma().getSelectionModel().getSelectedIndex());
		Disciplina d = disciplinas.get(viewEditarAtividade.getCbDisciplina().getSelectionModel().getSelectedIndex());

		DisciplinaTurmaProfessorDao dtpDao;
		DisciplinaTurmaProfessor dtp = null;
		DisciplinaTurmaProfessor idDiscTurmaProf = null;
		try {
			dtpDao = new DisciplinaTurmaProfessorDao();
			idDiscTurmaProf = dtpDao.buscaDisciplinaTurmaProfessor(d.getId(), t.getId(),
					viewEditarAtividade.getAtividade().getDiscTurmaProf().getProfessor().getId());
			dtp = new DisciplinaTurmaProfessor(idDiscTurmaProf.getId(), d, t, viewEditarAtividade.getAtividade().getDiscTurmaProf().getProfessor());

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dtp;
	}

	private void moverArquivo() {

		try {

			File source = new File(viewEditarAtividade.getFile().getAbsolutePath());

			File dest = new File(
					"tmp//" + atividade.getDiscTurmaProf().getId() + "//" + viewEditarAtividade.getDtEntrega().getValue()
							+ "//" + viewEditarAtividade.getTfAtividade().getText());

			if (dest.exists()) {
				FileUtils.forceDelete(dest);
			}

			dest = new File(
					"tmp//" + atividade.getDiscTurmaProf().getId() + "//" + viewEditarAtividade.getDtEntrega().getValue()
							+ "//" + viewEditarAtividade.getTfAtividade().getText());

			FileUtils.copyFileToDirectory(source, dest);

			// adrSystem.out.println(dest.getAbsolutePath());
			atividade.setPathArquivo(
					"tmp//" + atividade.getDiscTurmaProf().getId() + "//" + viewEditarAtividade.getDtEntrega().getValue()
							+ "//" + viewEditarAtividade.getTfAtividade().getText());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void deletarTarefa() {

		File file = viewEditarAtividade.getFile();

		if (file.exists()) {
			try {
				FileUtils.forceDelete(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			viewEditarAtividade.getLblEntrega().setText("Clique para adicionar um arquivo");
		}

	}
}
