package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Turma;

public interface ITurmaDao {
	

	public Turma findTurma(Turma turma) throws SQLException;
	public List<Turma> findAll() throws SQLException;

}
