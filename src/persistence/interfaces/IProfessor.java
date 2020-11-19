package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Professor;

public interface IProfessor {
	
	public void insert(Professor professor) throws SQLException;
	public void update(Professor professor) throws SQLException;
	public void delete(Professor professor) throws SQLException;
	public Professor findAtividade(Professor professor) throws SQLException;
	public List<Professor> findAll() throws SQLException;

}
