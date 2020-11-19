package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Aluno;

public interface IAluno {
	
	public void insert(Aluno aluno) throws SQLException;
	public void update(Aluno aluno) throws SQLException;
	public void delete(Aluno aluno) throws SQLException;
	public Aluno findAtividade(Aluno aluno) throws SQLException;
	public List<Aluno> findAll() throws SQLException;

}
