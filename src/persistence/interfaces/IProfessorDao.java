package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Professor;
import model.Usuario;

public interface IProfessorDao {
	
	public Professor findProfessor(Usuario professor) throws SQLException;
	public List<Professor> findAll() throws SQLException;

}
