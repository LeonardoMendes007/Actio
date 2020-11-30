package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Atividade;
import model.Disciplina;
import model.DisciplinaTurmaProfessor;
import model.Turma;
import persistence.interfaces.IDisciplinaProfessorDao;
import persistence.interfaces.IGenericDao;

public class DisciplinaTurmaProfessorDao implements IDisciplinaProfessorDao {

private Connection c;
	
	public DisciplinaTurmaProfessorDao() throws ClassNotFoundException, SQLException {

		IGenericDao gDao = new GenericDao(); 
	    c = gDao.getConnection();
	}
	
	@Override
	public void inserir(int idDisc, int idTurma, int idProfessor) throws SQLException {
		String sql = "insert into tbDisciplinaTurmaProfessor (idDisciplina, idTurma, idProfessor) "  
				+ " VALUES (?,?,?)";
	
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, idDisc);
		ps.setInt(2, idTurma);
		ps.setInt(3, idProfessor);

		ps.execute();
		ps.close();
	}
	
	@Override
	public DisciplinaTurmaProfessor buscaDisciplinaTurmaProfessor(int idDisc, int idTurma, int idProfessor) throws SQLException{
		
		String sql = "SELECT idDisciplinaTurmaProfessor from tbDisciplinaTurmaProfessor where idDisciplina = ? and idTurma = ? and idProfessor = ?";
	
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, idDisc);
		ps.setInt(2, idTurma);
		ps.setInt(3, idProfessor);
		
		ResultSet rs = ps.executeQuery();
		
		DisciplinaTurmaProfessor dtp = null;
		
		while(rs.next()) {
			dtp = new DisciplinaTurmaProfessor();
			dtp.setId(rs.getInt("idDisciplinaTurmaProfessor"));

		}
		
		ps.close();
		return dtp;
	}
	
}
