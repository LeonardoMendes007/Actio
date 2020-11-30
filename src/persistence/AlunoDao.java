package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Aluno;
import model.AlunoEntregaTotalizado;
import model.Disciplina;
import model.Turma;
import persistence.interfaces.IAlunoDao;
import persistence.interfaces.IGenericDao;

public class AlunoDao implements IAlunoDao {

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

		while (rs.next()) {

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

		if (cont == 0) {
			a = new Aluno();
		}

		return a;
	}

	@Override
	public List<Aluno> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AlunoEntregaTotalizado> findAlunoEntrega(Turma turma, Disciplina disciplina) throws SQLException {
		String sql = "SELECT u.idUsuario,  u.nomeUsuario + ' ' + u.sobrenomeUsuario, u.emailUsuario "
				+ "FROM tbEntrega e, tbAluno a, tbDisciplina d, tbDisciplinaTurmaProfessor dtp, tbAtividade ati, tbUsuario u "
				+ "WHERE  u.idUsuario = e.idAluno "
				+ "AND e.idAluno = a.idAluno "
				+ "AND d.idDisciplina = dtp.idDisciplina "
				+ "AND dtp.idDisciplinaTurmaProfessor = ati.idDisciplinaTurmaProfessor "
				+ "AND e.idAtividade = ati.idAtividade "
				+ "AND dtp.idTurma = ? "
				+ "AND dtp.idDisciplina = ? "
				+ "GROUP BY u.nomeUsuario, u.sobrenomeUsuario, u.emailUsuario, u.idUsuario";

		PreparedStatement ps = c.prepareStatement(sql);

		ps.setInt(1, turma.getId());
		ps.setInt(2, disciplina.getId());

		ResultSet rs = ps.executeQuery();

		List<AlunoEntregaTotalizado> alunoEntregas = new ArrayList<>();

		while (rs.next()) {

			AlunoEntregaTotalizado alunoEntrega = new AlunoEntregaTotalizado();
			alunoEntrega.setAluno(rs.getString(2));
			alunoEntrega.setEmail(rs.getString(3));
			alunoEntrega.setNota((selectSumAluno(rs.getInt(1)) / selectCountAluno(disciplina.getId(), turma.getId())));

			alunoEntregas.add(alunoEntrega);

		}

		return alunoEntregas;

	}

	private Double selectSumAluno(int idAluno) throws SQLException {

		String sql = "SELECT SUM(e.nota) "
				+ "FROM tbEntrega e, tbAluno a, tbDisciplina d, tbDisciplinaTurmaProfessor dtp, tbAtividade ati, tbUsuario u  "
				+ "WHERE  u.idUsuario = e.idAluno  " + "AND e.idAluno = a.idAluno  "
				+ "AND d.idDisciplina = dtp.idDisciplina  "
				+ "AND dtp.idDisciplinaTurmaProfessor = ati.idDisciplinaTurmaProfessor  "
				+ "AND e.idAtividade = ati.idAtividade " + "AND e.idAluno = ? ";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, idAluno);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getDouble(1);
		}

		return 0.0;
	}

	private Integer selectCountAluno(int idDisciplina, int idTurma) throws SQLException {

		String sql = "SELECT DISTINCT COUNT(ati.idAtividade) "
				+ "FROM tbAtividade ati, tbAluno a, tbDisciplinaTurmaProfessor dtp "
				+ "WHERE ati.idDisciplinaTurmaProfessor = dtp.idDisciplinaTurmaProfessor "
				+ "AND dtp.idDisciplina = ? "
				+ "AND dtp.idTurma = ? "
				+ "GROUP BY a.idAluno";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, idDisciplina);
		ps.setInt(2, idTurma);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt(1);
		}

		return 0;
	}


	public List<AlunoEntregaTotalizado> findAlunoSemEntrega(Turma turma, Disciplina disciplina) throws SQLException {
		
		String sql = "SELECT DISTINCT u.nomeUsuario + ' ' + u.sobrenomeUsuario, u.emailUsuario, 0.0 "
				+ "FROM  tbUsuario u inner join tbAluno a on u.idUsuario = a.idAluno "
				+ "inner join tbDisciplinaTurmaProfessor dtp on a.idTurma = dtp.idTurma "
				+ "inner join tbAtividade ati on dtp.idDisciplinaTurmaProfessor = ati.idDisciplinaTurmaProfessor "
				+ "left join tbEntrega on ati.idAtividade = tbEntrega.idAtividade "
				+ "where dtp.idDisciplina = ? AND dtp.idTurma = ? ";

		PreparedStatement ps = c.prepareStatement(sql);

		ps.setInt(2, turma.getId());
		ps.setInt(1, disciplina.getId());

		ResultSet rs = ps.executeQuery();

		List<AlunoEntregaTotalizado> alunoEntregas = new ArrayList<>();

		while (rs.next()) {

			AlunoEntregaTotalizado alunoEntrega = new AlunoEntregaTotalizado();
			alunoEntrega.setAluno(rs.getString(1));
			alunoEntrega.setEmail(rs.getString(2));
			alunoEntrega.setNota(rs.getDouble(3));

			alunoEntregas.add(alunoEntrega);

		}

		return alunoEntregas;
	}

}
