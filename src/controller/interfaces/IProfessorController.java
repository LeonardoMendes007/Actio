package controller.interfaces;

import java.sql.SQLException;

import model.Professor;
import model.Usuario;

public interface IProfessorController {

	public void buscarProfessor(Professor p) throws SQLException, ClassNotFoundException;
}
