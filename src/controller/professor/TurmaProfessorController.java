package controller.professor;

import java.sql.SQLException;
import java.util.List;

import controller.interfaces.ITurmaController;
import model.Disciplina;
import model.DisciplinaTurmaProfessor;
import model.Turma;
import persistence.DisciplinaDao;
import persistence.TurmaDao;
import view.card.turma.CardTurma;
import view.professor.ViewTurmaProfessor;

public class TurmaProfessorController implements ITurmaController{

	private ViewTurmaProfessor viewTurmaProf;
	private List<DisciplinaTurmaProfessor> lista;
	
	public TurmaProfessorController(ViewTurmaProfessor view) {
		this.viewTurmaProf = view;
	}
	
	public void addCards() {
		
		try {
			DisciplinaDao dDao = new DisciplinaDao();
			lista = dDao.findTurmaDisciplinaProfessor(viewTurmaProf.getProfessor());
		
			
			for(DisciplinaTurmaProfessor dis : lista) {
				CardTurma card = new CardTurma(300.0, 100.0, dis.getDisciplina(), dis.getTurma());
				
				addDisciplina(card);
				//System.out.println(dis.getDisciplina().getNome() + " " + dis.getTurma().getCurso());
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addDisciplina(CardTurma card) {
	//f(buscaDisciplinaLista(card.getDisciplina()) == false) {
			viewTurmaProf.initNovaDisciplina(card);
	//	}else {
			viewTurmaProf.addCard(viewTurmaProf.initNovaDisciplina(card), card);
	//	}
	
	}

	@Override
	public void buscarTurma(Turma t) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		TurmaDao tDao = new TurmaDao();
		
		tDao.findTurma(t);
	}
	
	public boolean buscaDisciplinaLista(Disciplina d) {
		
		for(DisciplinaTurmaProfessor disciplina : lista) {
			if(disciplina.getDisciplina().getId() == d.getId()) {

				return true;
			}
		}
		
		return false;
	}
	
	
	
	
}
