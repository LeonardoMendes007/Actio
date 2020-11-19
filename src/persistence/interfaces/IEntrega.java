package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Entrega;

public interface IEntrega {

	public void insert(Entrega entrega) throws SQLException;
	public void update(Entrega entrega) throws SQLException;
	public void delete(Entrega entrega) throws SQLException;
	public Entrega findAtividade(Entrega entrega) throws SQLException;
	public List<Entrega> findAll() throws SQLException;
}
