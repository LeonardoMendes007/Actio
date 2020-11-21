package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Usuario;

public interface IUsuarioDao {
	
	public Usuario findUsuario(Usuario usuario) throws SQLException;
	public List<Usuario> findAll() throws SQLException;


}
