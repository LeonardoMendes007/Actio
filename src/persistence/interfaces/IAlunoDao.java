package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Aluno;
import model.Usuario;

public interface IAlunoDao {
	

	public Aluno findAluno(Aluno aluno) throws SQLException;
	public List<Aluno> findAll() throws SQLException;

}
