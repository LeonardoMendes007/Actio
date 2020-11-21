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
	public Usuario findUsuario(Usuario usuario) throws SQLException {
		
		String sql = "select * from tbUsuario where emailUsuario = ? and senhaUsuario = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		
		ps.setString(1, usuario.getEmail());
		ps.setString(2, usuario.getSenha());
		
		
		ResultSet rs = ps.executeQuery();
		
		int cont = 0;
		while(rs.next()) {
			usuario.setId(Integer.parseInt(rs.getString("idUsuario")));

			cont++;
		}
		
		if(cont == 0) {
			usuario = new Usuario();
		}
		
		return usuario;
	}

	@Override
	public List<Usuario> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
