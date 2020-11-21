package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Disciplina;
import persistence.interfaces.IDisciplinaDao;
import persistence.interfaces.IGenericDao;

public class DisciplinaDao implements IDisciplinaDao{

	private Connection c;
	
	public DisciplinaDao() throws ClassNotFoundException, SQLException {

		IGenericDao gDao = new GenericDao(); 
	    c = gDao.getConnection();
	}
	
	@Override
	public Disciplina findDisciplina(Disciplina disciplina) throws SQLException {
	
		String sql = "select * from tbDisciplina where idDisciplina = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, disciplina.getId());
		
		
		ResultSet rs = ps.executeQuery();
		
		
		int cont = 0;
		
		if(rs.next()) {
			disciplina.setNome(rs.getString("nomeDisciplina"));
			disciplina.setCor(rs.getString("corDisciplina"));
		}
		
		if(cont == 0) {
			disciplina = new Disciplina();
		}
		return disciplina;
	}

	@Override
	public List<Disciplina> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
