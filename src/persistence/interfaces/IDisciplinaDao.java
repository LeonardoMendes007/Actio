package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Disciplina;

public interface IDisciplinaDao {
	

	public Disciplina findDisciplina(Disciplina disciplina) throws SQLException;
	public List<Disciplina> findAll() throws SQLException;

}
