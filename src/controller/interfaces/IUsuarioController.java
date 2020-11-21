package controller.interfaces;

import java.sql.SQLException;

import model.Usuario;

public interface IUsuarioController {


	public void buscarUsuario(Usuario u) throws SQLException, ClassNotFoundException;


}
