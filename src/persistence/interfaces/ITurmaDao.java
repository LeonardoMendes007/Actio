package persistence.interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Disciplina;
import model.Turma;

public interface ITurmaDao {

	public Turma findTurma(Turma turma) throws SQLException;

	public List<Turma> findTurmaDisciplina(Disciplina disc) throws SQLException;

	public Turma findTurmaTexto(int semestre, String periodo, String curso) throws SQLException;

}
