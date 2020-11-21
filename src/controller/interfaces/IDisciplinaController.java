package controller.interfaces;

import java.sql.SQLException;

import model.Disciplina;

public interface IDisciplinaController {

	public void buscarDisciplina(Disciplina d) throws SQLException, ClassNotFoundException;

}
