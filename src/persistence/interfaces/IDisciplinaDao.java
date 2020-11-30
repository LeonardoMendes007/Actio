package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Aluno;
import model.Disciplina;
import model.DisciplinaTurmaProfessor;
import model.Professor;

public interface IDisciplinaDao {
	

	public Disciplina findDisciplina(Disciplina disciplina) throws SQLException;
	
	public List<DisciplinaTurmaProfessor> findDisciplinaAluno(Aluno a) throws SQLException;
	
	public List<DisciplinaTurmaProfessor> findTurmaDisciplinaProfessor(Professor p) throws SQLException;
	
	public List<Disciplina> findDisciplinaProfessor(Professor p) throws SQLException;


}
