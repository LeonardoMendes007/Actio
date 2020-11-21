package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Aluno;
import model.Disciplina;
import model.Professor;
import model.Turma;
import model.Usuario;
import persistence.interfaces.IAlunoDao;
import persistence.interfaces.IGenericDao;

public class AlunoDao implements IAlunoDao{

	private Connection c;
	
	public AlunoDao() throws ClassNotFoundException, SQLException {

		IGenericDao gDao = new GenericDao(); 
	    c = gDao.getConnection();
	}
	
	@Override
	public Aluno findAluno(Aluno a) throws SQLException {

		String sql = "select * from tbUsuario inner join tbAluno on tbUsuario.idUsuario = tbAluno.idAluno where idAluno = ?";

		PreparedStatement ps = c.prepareStatement(sql);
		
		ps.setInt(1, a.getId());
		
		ResultSet rs = ps.executeQuery();
		

		
		int cont = 0;
		
		while(rs.next()) {
			
			
			a.setNome(rs.getString("nomeUsuario"));
			a.setSobrenome(rs.getString("sobrenomeUsuario"));
			a.setEmail(rs.getString("emailUsuario"));
			a.setSenha(rs.getString("senhaUsuario"));
			a.setFotoPerfil(rs.getString("fotoPerfil"));
			
			Turma t = new Turma();
			t.setId(Integer.parseInt(rs.getString("idTurma")));
			
			a.setTurma(t);
			cont++;
		}
		
		if(cont == 0) {
			a = new Aluno();
		}
		
		return a;
	}

	@Override
	public List<Aluno> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
