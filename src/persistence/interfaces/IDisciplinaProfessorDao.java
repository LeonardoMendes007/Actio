package persistence.interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DisciplinaTurmaProfessor;

public interface IDisciplinaProfessorDao {

	public void inserir(int idDisc, int idTurma, int idProfessor) throws SQLException;

	public DisciplinaTurmaProfessor buscaDisciplinaTurmaProfessor(int idDisc, int idTurma, int idProfessor)
			throws SQLException;
}
