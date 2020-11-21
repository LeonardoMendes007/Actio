package controller;

import java.sql.SQLException;

import controller.interfaces.IDisciplinaController;
import model.Disciplina;
import persistence.DisciplinaDao;

public class DisciplinaController implements IDisciplinaController{

	@Override
	public void buscarDisciplina(Disciplina d) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		DisciplinaDao dDao = new DisciplinaDao();
		
		dDao.findDisciplina(d);
	}

}
