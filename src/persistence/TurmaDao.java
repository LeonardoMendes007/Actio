package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Disciplina;
import model.DisciplinaTurmaProfessor;
import model.Professor;
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
	
	public List<Turma> findTurmaDisciplina(Disciplina disc) throws SQLException{
		
		
		String sql = "select tbTurma.* from tbDisciplinaTurmaProfessor " + 
					"inner join tbTurma on tbDisciplinaTurmaProfessor.idTurma = tbTurma.idTurma " +
					"inner join tbDisciplina on tbDisciplinaTurmaProfessor.idDisciplina = tbDisciplina.idDisciplina " +
					"where tbDisciplina.nomeDisciplina like ?";
		

		PreparedStatement ps = c.prepareStatement(sql);
		
		ps.setString(1, disc.getNome());
		
		ResultSet rs = ps.executeQuery();
		
		List<Turma> turmas = new ArrayList<Turma>();
		
		
		while(rs.next()) {
	
			Turma t = new Turma();
			t.setId(rs.getInt("idTurma"));
			t.setCurso(rs.getString("cursoTurma"));
			t.setPeriodo(rs.getString("periodoTurma"));
			t.setSemestre(rs.getInt("semestreTurma"));
	
			
			turmas.add(t);
		}
		
		
		return turmas;
	}
	 

	public Turma findTurmaTexto(int semestre, String periodo, String curso) throws SQLException {
		String sql = "select * from tbTurma where semestreTurma = ? and periodoTurma like ? and cursoTurma like ?";
	

	PreparedStatement ps = c.prepareStatement(sql);
	
	ps.setInt(1, semestre);
	ps.setString(2, periodo);
	ps.setString(3, curso);
	
	ResultSet rs = ps.executeQuery();

	
	Turma t = null;
	while(rs.next()) {

		t = new Turma();
		t.setId(rs.getInt("idTurma"));
		t.setCurso(rs.getString("cursoTurma"));
		t.setPeriodo(rs.getString("periodoTurma"));
		t.setSemestre(rs.getInt("semestreTurma"));

		
	}
	
	
	return t;
	}
	@Override
	public List<Turma> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
