package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Usuario;

public interface IUsuarioDao {
	
	public void insert(Usuario usuario) throws SQLException;
	public void update(Usuario usuario) throws SQLException;
	public void delete(Usuario usuario) throws SQLException;
	public Usuario findUsuario(Usuario usuario) throws SQLException;
	public List<Usuario> findAll() throws SQLException;


}
