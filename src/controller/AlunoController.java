package controller;

import java.sql.SQLException;

import controller.interfaces.IAlunoController;
import model.Aluno;
import model.Usuario;
import persistence.AlunoDao;

public class AlunoController implements IAlunoController{

	@Override
	public void buscarAluno(Aluno a) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		AlunoDao aDao = new AlunoDao();
		
		aDao.findAluno(a);
		
	}

	
	
}
