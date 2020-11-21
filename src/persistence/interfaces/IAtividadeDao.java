package persistence.interfaces;


import java.sql.SQLException;
import java.util.List;

import model.Atividade;
import model.Turma;

public interface IAtividadeDao {

	public void insert(Atividade atividade) throws SQLException;
	public void update(Atividade atividade) throws SQLException;
	public void delete(Atividade atividade) throws SQLException;
	public Atividade findAtividade(Atividade atividade) throws SQLException;
	public List<Atividade> findAtividadeTurma(Turma turma) throws SQLException;
	public List<Atividade> findAll() throws SQLException;
	
}
