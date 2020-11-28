package controller.professor;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import model.Atividade;
import model.Disciplina;
import model.DisciplinaTurmaProfessor;
import model.Professor;
import model.Turma;
import persistence.AtividadeDao;
import persistence.DisciplinaDao;
import persistence.DisciplinaTurmaProfessorDao;
import persistence.TurmaDao;
import model.DisciplinaTurmaProfessor;
import view.professor.CriarAtividade;

public class CriarAtividadeController{

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
	
	public String criarAtividade() {
		
		dtEntrega = Date.from(viewCriarAtividade.getDtEntrega().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		if(viewCriarAtividade.getTfAtividade().getText().isEmpty()) {
			return "Erro: é necessário um titulo para atividade";
		
		}else if(dtEntrega.compareTo(new Date()) <  0) {
			return "Erro: a data de entrega não pode ser menor que a data atual";
		
		}else {
			atividade.setNome(viewCriarAtividade.getTfAtividade().getText());
			
			atividade.setDescricao(viewCriarAtividade.getTaDescricao().getText());
			
			atividade.setGrupo(isGrupo(viewCriarAtividade.getCbGrupo().getValue()));
			
			atividade.setDtEntrega(dtEntrega);

			atividade.setDiscTurmaProf(criaDiscTurmaProfessor());
		
			atividade.setDtEmissao(new Date());

			if(atividade.getDtPublicacao() == null) {
				atividade.setDtPublicacao(new Date());
			}
			
			try {
				AtividadeDao ativDao = new AtividadeDao();
				ativDao.insert(atividade);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "Atividade Cadastrada com Sucesso!";
			
			
			
		}
		
		
	}
	
	public String addDataAtividade()  {
	
		dtEntrega = Date.from(viewCriarAtividade.getDtEntrega().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		dtPublicacao = Date.from(viewCriarAtividade.getDtDataPublicacao().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		
		if(dtPublicacao.compareTo(dtEntrega) >= 0) {
			return "Erro: a data de publicação não pode ser maior que a data de entrega";

		}else if(dtEntrega.compareTo(new Date()) <  0) {
			return "Erro: a data de entrega não pode ser menor que a data atual";
		
		} else {
			atividade.setDtPublicacao(Date.from(viewCriarAtividade.getDtDataPublicacao().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			
			return criarAtividade();
		}
		
	}
	
	public boolean isGrupo(String texto) {
		if(texto.equals("Individual")) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public void addDiscComboBox(Professor p){

		try {
			DisciplinaDao dDao = new DisciplinaDao();
			disciplinas = dDao.findDisciplinaProfessor(p);
		
			for(int i = 0; i<disciplinas.size(); i++) {
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
		
			/*for(Turma t  : turmas) {
				viewCriarAtividade.getCbTurma().getItems().add(t.getSemestre() + "° " + t.getCurso() + " - " + t.getPeriodo());
			}*/
			
			for(int i = 0; i<turmas.size(); i++) {
				viewCriarAtividade.getCbTurma().getItems().add(i,turmas.get(i).getSemestre() + "° " + turmas.get(i).getCurso() + " - " + turmas.get(i).getPeriodo());

			}
		
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public DisciplinaTurmaProfessor criaDiscTurmaProfessor(){
		
		Turma t = turmas.get(viewCriarAtividade.getCbTurma().getSelectionModel().getSelectedIndex());
		Disciplina d = disciplinas.get(viewCriarAtividade.getCbDisciplina().getSelectionModel().getSelectedIndex());
		
		DisciplinaTurmaProfessorDao dtpDao;
		DisciplinaTurmaProfessor dtp = null;
		DisciplinaTurmaProfessor idDiscTurmaProf = null;
		try {
			dtpDao = new DisciplinaTurmaProfessorDao();
			idDiscTurmaProf = dtpDao.buscaDisciplinaTurmaProfessor(d.getId(), t.getId(), viewCriarAtividade.getProfessor().getId());
			dtp = new DisciplinaTurmaProfessor(idDiscTurmaProf.getId(), d, t, viewCriarAtividade.getProfessor());

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dtp;
	}
	
	
	
	


	

}
