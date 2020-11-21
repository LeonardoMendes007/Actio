package controller.interfaces;

import java.sql.SQLException;

import model.Aluno;
import model.Usuario;

public interface IAlunoController {

	public void buscarAluno(Aluno a) throws SQLException, ClassNotFoundException;
}
