package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Disciplina;

public interface IDisciplina {
	
	public void insert(Disciplina disciplina) throws SQLException;
	public void update(Disciplina disciplina) throws SQLException;
	public void delete(Disciplina disciplina) throws SQLException;
	public Disciplina findDisciplina(Disciplina disciplina) throws SQLException;
	public List<Disciplina> findAll() throws SQLException;

}
