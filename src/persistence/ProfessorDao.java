package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Professor;
import model.Usuario;
import persistence.interfaces.IGenericDao;
import persistence.interfaces.IProfessorDao;

public class ProfessorDao implements IProfessorDao {
	private Connection c;

	public ProfessorDao() throws ClassNotFoundException, SQLException {

		IGenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public Professor findProfessor(Professor professor) throws SQLException {
		String sql = "select * from tbUsuario inner join tbProfessor on tbProfessor.idProfessor = tbUsuario.idUsuario  where idProfessor = ?";

		PreparedStatement ps = c.prepareStatement(sql);

		ps.setInt(1, professor.getId());

		ResultSet rs = ps.executeQuery();

		int cont = 0;

		while (rs.next()) {

			professor.setNome(rs.getString("nomeUsuario"));
			professor.setSobrenome(rs.getString("sobrenomeUsuario"));
			professor.setEmail(rs.getString("emailUsuario"));
			professor.setSenha(rs.getString("senhaUsuario"));
			professor.setFotoPerfil(rs.getString("fotoPerfil"));

			cont++;
		}

		if (cont == 0) {
			professor = new Professor();
		}

		return professor;
	}

}
