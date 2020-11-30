package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Professor;
import model.Usuario;

public interface IProfessorDao {
	
	public Professor findProfessor(Professor professor) throws SQLException;

}
