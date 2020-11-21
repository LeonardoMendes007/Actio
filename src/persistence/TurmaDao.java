package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Turma;
import persistence.interfaces.IGenericDao;
import persistence.interfaces.ITurmaDao;

public class TurmaDao implements ITurmaDao{

	private Connection c;
	
	public TurmaDao() throws ClassNotFoundException, SQLException {

		IGenericDao gDao = new GenericDao(); 
	    c = gDao.getConnection();
	}
	
	@Override
	public Turma findTurma(Turma turma) throws SQLException {
		
		String sql = "select * from tbTurma where idTurma = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, turma.getId());
		
		
		ResultSet rs = ps.executeQuery();
		
		
		int cont = 0;
		
		if(rs.next()) {
			
		}
		return turma;
	}

	@Override
	public List<Turma> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
