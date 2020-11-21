package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Disciplina;
import model.DisciplinaTurmaProfessor;
import model.Professor;
import model.Turma;
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

	
	public List<DisciplinaTurmaProfessor> findDisciplinaAluno(Aluno a) throws SQLException{
		
		
		String sql = "select tbDisciplina.*, tbUsuario.idUsuario, tbUsuario.nomeUsuario, tbUsuario.sobrenomeUsuario from tbDisciplina " + 
				"inner join tbDisciplinaTurmaProfessor " + 
				"on tbDisciplina.idDisciplina = tbDisciplinaTurmaProfessor.idDisciplina " + 
				"inner join tbTurma " + 
				"on tbTurma.idTurma = tbDisciplinaTurmaProfessor.idTurma " + 
				"inner join tbAluno " + 
				"on tbTurma.idTurma = tbAluno.idTurma "
				+ "inner join tbUsuario "
				+ "on tbDisciplinaTurmaProfessor.idProfessor = tbUsuario.idUsuario " + 
				"where idAluno = ?";
		

		PreparedStatement ps = c.prepareStatement(sql);
		
		ps.setInt(1, a.getId());
		
		ResultSet rs = ps.executeQuery();
		
		List<DisciplinaTurmaProfessor> disciplinas = new ArrayList<DisciplinaTurmaProfessor>();
		
		
		while(rs.next()) {
			
			Disciplina d = new Disciplina();
			
			d.setId(rs.getInt("idDisciplina"));
			d.setNome(rs.getString("nomeDisciplina"));
			d.setCor(rs.getString("corDisciplina"));
			
			
			Turma t = new Turma();
			
			Professor p =  new Professor();
			p.setId(rs.getInt("idUsuario"));
			p.setNome(rs.getString("nomeUsuario"));
			p.setSobrenome(rs.getString("sobrenomeUsuario"));
			
			DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor(d, t, p);
			
			
			
			disciplinas.add(dtp);
		}
		
		
		return disciplinas;
	}
	@Override
	public List<Disciplina> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
