package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Aluno;
import model.Atividade;
import model.Entrega;
import persistence.interfaces.IEntrega;
import persistence.interfaces.IGenericDao;

public class EntregaDao implements IEntrega{
	
    private Connection c;
	
	public EntregaDao() throws ClassNotFoundException, SQLException {

		IGenericDao gDao = new GenericDao(); 
	    c = gDao.getConnection();
	}

	@Override
	public void insert(Entrega entrega) throws SQLException {
		
		String sql = "insert into tbEntrega(arquivosEntrega, dtEntrega, idAtividade, idAluno)"
				+ "	values (?, GETDATE(), ?, ?)";
	
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, entrega.getPathArquivos());
		ps.setInt(4, entrega.getAtividade().getId());
		ps.setInt(5, entrega.getAluno().getId());
		
		ps.execute();
		ps.close();
		
	}

	@Override
	public void update(Entrega entrega) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Entrega entrega) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entrega findAtividade(Entrega entrega) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entrega> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
