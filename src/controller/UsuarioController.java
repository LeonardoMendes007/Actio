package controller;

import java.sql.SQLException;

import controller.interfaces.IUsuarioController;
import model.Usuario;
import persistence.UsuarioDao;

public class UsuarioController implements IUsuarioController{

	
	@Override
	public void buscarUsuario(Usuario u) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		UsuarioDao uDao = new UsuarioDao();
		
		uDao.findUsuario(u);
	}

}
