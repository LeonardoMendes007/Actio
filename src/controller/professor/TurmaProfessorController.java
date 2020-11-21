package controller.professor;

import java.sql.SQLException;

import controller.interfaces.ITurmaController;
import model.Turma;
import persistence.TurmaDao;
import view.card.turma.CardTurma;
import view.professor.ViewTurmaProfessor;

public class TurmaProfessorController implements ITurmaController{

	private ViewTurmaProfessor viewTurmaProf;
	
	
	public TurmaProfessorController(ViewTurmaProfessor view) {
		this.viewTurmaProf = view;
	}
	
	public void addCards() {
		
		
	}

	@Override
	public void buscarTurma(Turma t) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		TurmaDao tDao = new TurmaDao();
		
		tDao.findTurma(t);
	}
	
	
	
	
}
