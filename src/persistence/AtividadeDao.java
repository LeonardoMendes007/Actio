package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Atividade;
import model.Disciplina;
import model.DisciplinaTurmaProfessor;
import model.Professor;
import model.Turma;
import model.Usuario;
import persistence.interfaces.IAtividadeDao;
import persistence.interfaces.IGenericDao;

public class AtividadeDao implements IAtividadeDao{

	private Connection c;
	
	public AtividadeDao() throws ClassNotFoundException, SQLException {

		IGenericDao gDao = new GenericDao(); 
	    c = gDao.getConnection();
	}
	
	@Override
	public void insert(Atividade atividade) throws SQLException {
		
		String sql = "insert into tbAtividade (nomeAtividade, descAtividade, dtEmissaoAtividade, dtPublicacaoAtividade,\n" + 
				"					dtFechamentoAtividade, arquivosAtividade, idTipoAtividade, idTurma)	"
				+ " VALUES (?,?,?,?,?,?,?)";
	
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, atividade.getNome());
		ps.setString(2, atividade.getDescricao());
		ps.setDate(3, (Date) atividade.getDtEmissao());
		ps.setDate(4, (Date) atividade.getDtPublicacao());
		ps.setDate(5, (Date) atividade.getDtEntrega());
		ps.setString(6, atividade.getPathArquivo());
		//ps.setInt(7, atividade.getTurma().getId());
		ps.execute();
		ps.close();
	}

	@Override
	public void update(Atividade atividade) throws SQLException {
		
	}

	@Override
	public void delete(Atividade atividade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Atividade findAtividade(Atividade atividade) throws SQLException {
		
		return null;
	}

	@Override
	public List<Atividade> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Atividade> findAtividadeTurma(Turma turma) throws SQLException {
		
		System.out.println("Id da turma" + turma.getId());
		String sql = "select tbAtividade.*, " + 
				"tbDisciplina.idDisciplina, tbDisciplina.nomeDisciplina, tbDisciplina.corDisciplina, " + 
				"tbTurma.cursoTurma, tbTurma.periodoTurma, tbTurma.semestreTurma, " + 
				"tbUsuario.idUsuario, tbUsuario.nomeUsuario, tbUsuario.sobrenomeUsuario " + 
				"from tbAtividade\n" + 
				"inner join tbDisciplinaTurmaProfessor on tbAtividade.idDisciplinaTurmaProfessor = tbDisciplinaTurmaProfessor.idDisciplinaTurmaProfessor " + 
				"inner join tbDisciplina on tbDisciplinaTurmaProfessor.idDisciplina = tbDisciplina.idDisciplina " + 
				"inner join tbTurma on tbDisciplinaTurmaProfessor.idTurma = tbTurma.idTurma " + 
				"inner join tbUsuario on tbDisciplinaTurmaProfessor.idProfessor = tbUsuario.idUsuario " +
				"where tbDisciplinaTurmaProfessor.idTurma = ?";
	
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, turma.getId());
		
		
		ResultSet rs = ps.executeQuery();
		
		List<Atividade> atividades = new ArrayList<Atividade>();
		
		while(rs.next()) {
			
			System.out.println("uma atividade");
			Atividade a = new Atividade();
			a.setId(rs.getInt("idAtividade"));
			a.setNome(rs.getString("nomeAtividade"));
			a.setDescricao(rs.getString("descAtividade"));
			a.setDtEmissao(rs.getDate("dtEmissaoAtividade"));
			a.setDtPublicacao(rs.getDate("dtPublicacaoAtividade"));
			a.setDtEntrega(rs.getDate("dtFechamentoAtividade"));
			a.setPathArquivo(rs.getString("arquivosAtividade"));
			a.setGrupo(isGrupo(rs.getInt("idTipoAtividade")));
			
			
			Disciplina disc = new Disciplina();
			disc.setId(rs.getInt("idDisciplina"));
			disc.setNome(rs.getString("nomeDisciplina"));
			disc.setCor(rs.getString("corDisciplina"));	
			
			Turma t = new Turma();
			t.setId(turma.getId());
			t.setCurso(rs.getString("cursoTurma"));
			t.setSemestre(rs.getInt("semestreTurma"));
			t.setPeriodo(rs.getString("periodoTurma"));
			
	
			Professor p =  new Professor();
			p.setId(rs.getInt("idUsuario"));
			p.setNome(rs.getString("nomeUsuario"));
			p.setSobrenome(rs.getString("sobrenomeUsuario"));
			
			DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor(disc, t, p);
			
			a.setDiscTurmaProf(dtp);
			
			
			atividades.add(a);
			System.out.println(a.getNome() + " " + a.getDescricao() + " " + a.getDiscTurmaProf().getDisciplina().getNome());
			
		}
		
		ps.close();
		return atividades;
		
		
	}


	
	private boolean isGrupo(int tipo) {
		return tipo == 1;
	}
	
	public List<Aluno> findAlunos(Atividade atividade) throws SQLException {
		
		String sql = "SELECT u.* "
				+ "FROM tbUsuario u, tbAluno a, tbTurma t, tbDisciplinaTurmaProfessor d,tbAtividade ati "
				+ "WHERE u.idUsuario = a.idAluno "
				+ "AND a.idTurma = t.idTurma "
				+ "AND t.idTurma  = d.idTurma "
				+ "AND d.idDisciplinaTurmaProfessor = ati.idDisciplinaTurmaProfessor "
				+ "AND ati.nomeAtividade = ?";
	
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, atividade.getNome());
		
		ResultSet rs = ps.executeQuery();
		
		List<Aluno> usuarios = new ArrayList<Aluno>();
		
		while(rs.next()) {
			
			Aluno a = new Aluno();
			a.setId(rs.getInt("idUsuario"));
			a.setNome(rs.getString("nomeUsuario"));
			a.setSobrenome(rs.getString("sobrenomeUsuario"));
			a.setEmail(rs.getString("emailUsuario"));
			a.setSenha(rs.getString("senhaUsuario"));
			a.setFotoPerfil(rs.getString("fotoPerfil"));
			
			usuarios.add(a);
		}
		
		ps.close();
		return usuarios;
	}

	@Override
	public List<Atividade> findAtividadeTurmaProfessor(Professor professor) throws SQLException {

		
		String sql = "select tbAtividade.*, " + 
				"tbDisciplina.idDisciplina, tbDisciplina.nomeDisciplina, tbDisciplina.corDisciplina, " + 
				"tbTurma.idTurma, tbTurma.cursoTurma, tbTurma.periodoTurma, tbTurma.semestreTurma " + 
				"from tbAtividade " + 
				"inner join tbDisciplinaTurmaProfessor on tbAtividade.idDisciplinaTurmaProfessor = tbDisciplinaTurmaProfessor.idDisciplinaTurmaProfessor " + 
				"inner join tbDisciplina on tbDisciplinaTurmaProfessor.idDisciplina = tbDisciplina.idDisciplina " + 
				"inner join tbTurma on tbDisciplinaTurmaProfessor.idTurma = tbTurma.idTurma " + 
				"inner join tbUsuario on tbDisciplinaTurmaProfessor.idProfessor = tbUsuario.idUsuario " +
				"where tbDisciplinaTurmaProfessor.idProfessor = ?";
	
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, professor.getId());
		
		
		ResultSet rs = ps.executeQuery();
		
		List<Atividade> atividades = new ArrayList<Atividade>();
		
		while(rs.next()) {
			
		
			Atividade a = new Atividade();
			a.setId(rs.getInt("idAtividade"));
			a.setNome(rs.getString("nomeAtividade"));
			a.setDescricao(rs.getString("descAtividade"));
			a.setDtEmissao(rs.getDate("dtEmissaoAtividade"));
			a.setDtPublicacao(rs.getDate("dtPublicacaoAtividade"));
			a.setDtEntrega(rs.getDate("dtFechamentoAtividade"));
			a.setPathArquivo(rs.getString("arquivosAtividade"));
			a.setGrupo(isGrupo(rs.getInt("idTipoAtividade")));
			
			
			Disciplina disc = new Disciplina();
			disc.setId(rs.getInt("idDisciplina"));
			disc.setNome(rs.getString("nomeDisciplina"));
			disc.setCor(rs.getString("corDisciplina"));	
			
			Turma t = new Turma();
			t.setId(rs.getInt("idTurma"));
			t.setCurso(rs.getString("cursoTurma"));
			t.setSemestre(rs.getInt("semestreTurma"));
			t.setPeriodo(rs.getString("periodoTurma"));
			
	
			DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor(disc, t, professor);
			
			a.setDiscTurmaProf(dtp);
			
			
			atividades.add(a);
			System.out.println(a.getNome() + " " + a.getDescricao() + " " + a.getDiscTurmaProf().getDisciplina().getNome());
			
		}
		
		ps.close();
		return atividades;
		
	}

	public List<Atividade> findAtividadeId(int id) throws SQLException  {
		
		String sql = "SELECT u.* "
				+ "FROM tbUsuario u, tbAluno a, tbTurma t, tbDisciplinaTurmaProfessor d,tbAtividade ati "
				+ "WHERE u.idUsuario = a.idAluno "
				+ "AND a.idTurma = t.idTurma "
				+ "AND t.idTurma  = d.idTurma "
				+ "AND d.idDisciplinaTurmaProfessor = ati.idDisciplinaTurmaProfessor "
				+ "AND ati.nomeAtividade = ?";
	
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		List<Atividade> usuarios = new ArrayList<Atividade>();
		
		while(rs.next()) {
			
			Atividade a = new Atividade();
			a.setId(rs.getInt("idAtividade"));
			a.setNome(rs.getString("nomeAtividade"));

			
			usuarios.add(a);
		}
		
		ps.close();
		return usuarios;
	}


}
