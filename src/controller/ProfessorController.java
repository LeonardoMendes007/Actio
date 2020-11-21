package controller;

import java.sql.SQLException;

import controller.interfaces.IProfessorController;
import model.Professor;
import model.Usuario;
import persistence.ProfessorDao;

public class ProfessorController implements IProfessorController{

	@Override
	public void buscarProfessor(Usuario p) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ProfessorDao pDao = new ProfessorDao();
		pDao.findProfessor(p);
	}

}
