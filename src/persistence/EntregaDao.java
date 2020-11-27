package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Atividade;
import model.Disciplina;
import model.Entrega;
import persistence.interfaces.IEntrega;
import persistence.interfaces.IGenericDao;

public class EntregaDao implements IEntrega {

	private Connection c;

	public EntregaDao() throws ClassNotFoundException, SQLException {

		IGenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	@Override
	public void insert(Entrega entrega) throws SQLException {

		String sql = "insert into tbEntrega(arquivosEntrega, dtEntrega, nota, idAtividade, idAluno)"
				+ "	values (?, GETDATE(), ?, ?, ?)";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, entrega.getPathArquivos().replaceAll("//", "/"));
		ps.setFloat(2, 0);
		ps.setInt(3, entrega.getAtividade().getId());
		ps.setInt(4, entrega.getAluno().getId());

		ps.execute();
		ps.close();

	}

	@Override
	public void update(Entrega entrega) throws SQLException {
		String sql = "UPDATE tbEntrega " + "SET arquivosEntrega = ?, dtEntrega = GETDATE() " + "WHERE idEntrega = ? ";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, entrega.getPathArquivos().replaceAll("//", "/"));
		ps.setInt(2, entrega.getId());

		ps.execute();
		ps.close();
	}

	@Override
	public void delete(Entrega entrega) throws SQLException {

		String sql = "DELETE FROM tbEntrega WHERE idEntrega = ? ";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, entrega.getId());
		ps.execute();
		ps.close();

	}

	@Override
	public Entrega findEntrega(Entrega entrega) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entrega> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Entrega> findAllEntregaAluno(Aluno aluno) throws SQLException {

		String sql = "SELECT e.*, a.*  FROM tbEntrega e, tbAtividade a WHERE " + "e.idAtividade = a.idAtividade "
				+ "AND e.idAluno = ? " + "AND DATEDIFF(DAY, e.dtEntrega, GETDATE()) < 4 " + "AND e.nota <> 0 ";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, aluno.getId());

		ResultSet rs = ps.executeQuery();

		List<Entrega> entregas = new ArrayList<>();

		while (rs.next()) {

			Entrega entrega = new Entrega();
			entrega = new Entrega();
			entrega.setId(rs.getInt(1));
			entrega.setPathArquivos(rs.getString(2));
			entrega.setDtEntrega(rs.getDate(3));
			entrega.setNota(rs.getFloat(4));
			entrega.setAluno(aluno);

			Atividade a = new Atividade();
			a.setId(rs.getInt(7));
			a.setNome(rs.getString(8));
			a.setDescricao(rs.getString(9));
			a.setDtEmissao(rs.getDate(10));
			a.setDtPublicacao(rs.getDate(11));
			a.setDtEntrega(rs.getDate(12));
			a.setPathArquivo(rs.getString(13));
			a.setGrupo(isGrupo(rs.getInt(14)));

			entrega.setAtividade(a);

			entregas.add(entrega);
		}

		return entregas;

	}

	private boolean isGrupo(int tipo) {
		return tipo == 1;
	}

	public Entrega findEntregaAtividadeAluno(Atividade atividade, Aluno aluno) throws SQLException {

		String sql = "SELECT e.* " + "FROM tbEntrega e " + "WHERE e.idAluno = ? " + "AND e.idAtividade = ?";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, aluno.getId());
		ps.setInt(2, atividade.getId());

		ResultSet rs = ps.executeQuery();

		Entrega entrega = null;

		while (rs.next()) {

			entrega = new Entrega();
			entrega.setId(rs.getInt(1));
			entrega.setPathArquivos(rs.getString(2));
			entrega.setDtEntrega(rs.getDate(3));
			entrega.setNota(rs.getFloat(4));
			entrega.setAtividade(atividade);
			entrega.setAluno(aluno);
		}

		return entrega;
	}

	public List<Entrega> findEntregaAtividade(Disciplina disciplina, Aluno aluno) throws SQLException {

		String sql = "SELECT DISTINCT e.*, a.* "
				+ "FROM tbDisciplina d , tbDisciplinaTurmaProfessor dtp, tbAtividade a, tbAluno aluno, tbEntrega e "
				+ "WHERE d.idDisciplina = dtp.idDisciplina "
				+ "AND dtp.idDisciplinaTurmaProfessor = a.idDisciplinaTurmaProfessor "
				+ "AND a.idAtividade = e.idAtividade " + "AND d.idDisciplina = ? " + "AND e.idAluno = ?";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, disciplina.getId());
		ps.setInt(2, aluno.getId());

		ResultSet rs = ps.executeQuery();

		List<Entrega> entregas = new ArrayList<>();

		while (rs.next()) {

			Entrega entrega = new Entrega();
			entrega.setId(rs.getInt(1));
			entrega.setPathArquivos(rs.getString(2));
			entrega.setDtEntrega(rs.getDate(3));
			entrega.setNota(rs.getFloat(4));
			entrega.setAluno(aluno);

			Atividade atividade = new Atividade();
			atividade.setId(rs.getInt(7));
			atividade.setNome(rs.getString(8));
			atividade.setDescricao(rs.getString(9));
			atividade.setDtEmissao(rs.getDate(10));
			atividade.setDtPublicacao(rs.getDate(11));
			atividade.setDtEntrega(rs.getDate(12));
			atividade.setPathArquivo(rs.getString(13));
			atividade.setGrupo(isGrupo(rs.getInt(14)));

			entrega.setAtividade(atividade);

			entregas.add(entrega);

		}

		return entregas;
	}

	public List<Entrega> findAlunoAtividade(Atividade atividade) throws SQLException {

		String sql = "SELECT e.*, u.* "
				+ "FROM tbEntrega e, tbAtividade a, tbAluno al, tbUsuario u "
				+ "WHERE e.idAtividade = a.idAtividade "
				+ "AND e.idAluno = al.idAluno "
				+ "AND u.idUsuario = al.idAluno "
				+ "AND a.idAtividade = ? ";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, atividade.getId());
		
		ResultSet rs = ps.executeQuery();

		List<Entrega> entregas = new ArrayList<>();

		while (rs.next()) {

			Entrega entrega = new Entrega();
			entrega.setId(rs.getInt(1));
			entrega.setPathArquivos(rs.getString(2));
			entrega.setDtEntrega(rs.getDate(3));
			entrega.setNota(rs.getFloat(4));
			

			Aluno aluno = new Aluno();
			aluno.setId(rs.getInt("idUsuario"));
			aluno.setNome(rs.getString("nomeUsuario"));
			aluno.setSobrenome(rs.getString("sobrenomeUsuario"));
			aluno.setEmail(rs.getString("emailUsuario"));
			aluno.setSenha(rs.getString("senhaUsuario"));
			aluno.setFotoPerfil(rs.getString("fotoPerfil"));

			entrega.setAluno(aluno);
			entrega.setAtividade(atividade);

			entregas.add(entrega);

		}

		return entregas;
	}
}
