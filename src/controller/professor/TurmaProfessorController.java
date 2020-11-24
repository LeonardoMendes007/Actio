package controller.professor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.interfaces.ITurmaController;
import javafx.scene.layout.HBox;
import model.Disciplina;
import model.DisciplinaTurmaProfessor;
import model.Turma;
import persistence.DisciplinaDao;
import persistence.TurmaDao;
import view.card.turma.CardTurma;
import view.professor.TurmaProfessor;

public class TurmaProfessorController implements ITurmaController{

	private TurmaProfessor viewTurmaProf;
	private List<DisciplinaTurmaProfessor> lista;
	private List<Disciplina> discJaAdicionadas = new ArrayList<Disciplina>();
	
	public TurmaProfessorController(TurmaProfessor view) {
		this.viewTurmaProf = view;
	}
	
	public void addCards() {
		
		try {
			DisciplinaDao dDao = new DisciplinaDao();
			lista = dDao.findTurmaDisciplinaProfessor(viewTurmaProf.getProfessor());
		
			
			for(DisciplinaTurmaProfessor dis : lista) {	
				addDisciplina(dis);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addDisciplina(DisciplinaTurmaProfessor disc) {
		CardTurma card = new CardTurma(300.0, 300.0, disc.getDisciplina(), disc.getTurma());


			
		if(!buscaDisciplinaLista(disc.getDisciplina())) {
			HBox teste = viewTurmaProf.initNovaDisciplina(card);
				
			addTurma(teste, disc.getDisciplina());
				
			discJaAdicionadas.add(disc.getDisciplina());
			
		}
		
		
		
		
	
	}

	@Override
	public void buscarTurma(Turma t) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		TurmaDao tDao = new TurmaDao();
		
		tDao.findTurma(t);
	}
	
	public boolean buscaDisciplinaLista(Disciplina d) {
		
		for(Disciplina disciplina : discJaAdicionadas) {
			if(disciplina.getId() == d.getId()) {
				System.out.println("existe");
				return true;
			}
		}
		
		System.out.println("n existe");
		
		return false;
	}
	
	
	public void addTurma(HBox teste, Disciplina d) {
		
		for(DisciplinaTurmaProfessor disc : lista) {
			
			if(disc.getDisciplina().getId() == d.getId()) {
				CardTurma card = new CardTurma(300.0, 100.0, disc.getDisciplina(), disc.getTurma());

				
				teste.getChildren().add(card.getCard());
			}
		}
	}
	
	
	
}
