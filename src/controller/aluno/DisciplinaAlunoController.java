package controller.aluno;

import java.sql.SQLException;
import java.util.List;

import model.Disciplina;
import model.DisciplinaTurmaProfessor;
import persistence.DisciplinaDao;
import view.aluno.DisciplinaAluno;
import view.card.disciplina.CardDisciplina;

public class DisciplinaAlunoController {

	private DisciplinaAluno viewDisciplina;
	
	private List<DisciplinaTurmaProfessor> disciplinas;
	
	public DisciplinaAlunoController(DisciplinaAluno disciplina) {
		this.viewDisciplina = disciplina;
	}
	
	public void addCards() {
		
		DisciplinaDao dDao;
		
		try {
			dDao = new DisciplinaDao();
			disciplinas = dDao.findDisciplinaAluno(viewDisciplina.getAluno());

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		for (DisciplinaTurmaProfessor disciplina : disciplinas) {
			CardDisciplina card = new CardDisciplina(330.0, 175.0, disciplina.getDisciplina(), disciplina.getProfessor());
			viewDisciplina.addCard(card);
		}
	}
	
	
	
}
