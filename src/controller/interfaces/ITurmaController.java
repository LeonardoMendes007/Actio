package controller.interfaces;

import java.sql.SQLException;

import model.Turma;

public interface ITurmaController {

	public void buscarTurma(Turma t) throws SQLException, ClassNotFoundException;
}
