package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Usuario;
import persistence.interfaces.IGenericDao;
import persistence.interfaces.IUsuarioDao;

public class UsuarioDao implements IUsuarioDao{
	
	private Connection c;
	
	public UsuarioDao() throws ClassNotFoundException, SQLException {

		IGenericDao gDao = new GenericDao(); 
	    c = gDao.getConnection();
	}
	

	@Override
	public void insert(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Usuario usuario) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario findUsuario(Usuario usuario) throws SQLException {
		
		return null;
	}

	@Override
	public List<Usuario> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
