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
import model.DisciplinaProfessor;
import model.Professor;
import model.Turma;
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
		ps.setInt(7, atividade.getTurma().getId());
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
				"tbDisciplina.nomeDisciplina, tbDisciplina.corDisciplina, " + 
				"tbTurma.cursoTurma, tbTurma.periodoTurma, tbTurma.semestreTurma " + 
				"from tbAtividade " + 
				"inner join tbDisciplina on tbDisciplina.idDisciplina = tbAtividade.idDisciplina " + 
				"inner join tbTurma on tbTurma.idTurma = tbAtividade.idTurma " + 
				"where tbAtividade.idTurma = ?";
	
		
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
			
	
			a.setTurma(t);
			a.setDisciplina(disc);
			
			
			atividades.add(a);
			System.out.println(a.getNome() + " " + a.getDescricao() + " " + a.getDisciplina());
			
		}
		
		ps.close();
		return atividades;
		
		
	}


	
	private boolean isGrupo(int tipo) {
		return tipo == 1;
	}


}
