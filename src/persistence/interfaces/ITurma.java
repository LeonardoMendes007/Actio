package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Turma;

public interface ITurma {
	
	public void insert(Turma turma) throws SQLException;
	public void update(Turma turma) throws SQLException;
	public void delete(Turma turma) throws SQLException;
	public Turma findAtividade(Turma turma) throws SQLException;
	public List<Turma> findAll() throws SQLException;

}
