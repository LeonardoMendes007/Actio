package controller.professor;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import model.Atividade;
import model.Disciplina;
import model.DisciplinaTurmaProfessor;
import model.Professor;
import model.Turma;
import persistence.DisciplinaDao;
import persistence.TurmaDao;
import model.DisciplinaTurmaProfessor;
import view.professor.CriarAtividade;

public class CriarAtividadeController{

	private CriarAtividade viewCriarAtividade;
	private List<Turma> turmas;
	List<Disciplina> disciplinas;
	private Atividade atividade;
	
	public CriarAtividadeController(CriarAtividade viewCriarAtividade) {
		this.viewCriarAtividade = viewCriarAtividade;
	}
	
	public String criarAtividade() {
		
		atividade = new Atividade();
		
		if(viewCriarAtividade.getTfAtividade().getText().isEmpty()) {
			return "Erro: é necessário um titulo para atividade";
		}
		
		atividade.setNome(viewCriarAtividade.getTfAtividade().getText());
		
		atividade.setDescricao(viewCriarAtividade.getTaDescricao().getText());
		
		atividade.setDtEntrega(Date.from(viewCriarAtividade.getDtEntrega().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		atividade.setDiscTurmaProf(criaDiscTurmaProfessor());
//		DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor(new , , viewCriarAtividade.getProfessor());
	
		atividade.setDtEmissao(new Date());
		
		
		LocalDate dataPublicacaoLocal = viewCriarAtividade.getDtDataPublicacao().getValue();
		
		//Date dataPublicacao = Date.
		
		
		
	
		return null;
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
	
	public DisciplinaTurmaProfessor criaDiscTurmaProfessor() {
		Turma t = turmas.get(viewCriarAtividade.getCbTurma().getSelectionModel().getSelectedIndex());
		Disciplina d = disciplinas.get(viewCriarAtividade.getCbDisciplina().getSelectionModel().getSelectedIndex());
		
		DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor(d, t, viewCriarAtividade.getProfessor());
		
		return dtp;
	}
	
	
	
	


	

}
