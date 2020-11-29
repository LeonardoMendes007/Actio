package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	public List<AlunoEntregaTotalizado> findAllAlunoEntrega(Turma turma, Disciplina disciplina) throws SQLException {
		String sql = "select tbUsuario.nomeUsuario + ' ' + tbUsuario.sobrenomeUsuario as nomeAluno, tbUsuario.emailUsuario, "
				+ "	avg (case when (tbEntrega.nota is null) "
				+ "	then "
				+ "		0 "
				+ "	else "
				+ "		tbEntrega.nota "
				+ "	end) as nota "
				+ "	 from tbUsuario "
				+ "inner join tbAluno on tbUsuario.idUsuario = tbAluno.idAluno "
				+ "inner join tbTurma on tbTurma.idTurma = tbAluno.idTurma "
				+ "inner join tbDisciplinaTurmaProfessor on tbDisciplinaTurmaProfessor.idTurma = tbTurma.idTurma "
				+ "inner join tbAtividade on tbAtividade.idDisciplinaTurmaProfessor = tbDisciplinaTurmaProfessor.idDisciplinaTurmaProfessor "
				+ "left outer join tbEntrega on tbAtividade.idAtividade = tbEntrega.idAtividade "
				+ "where tbDisciplinaTurmaProfessor.idTurma = ? and tbDisciplinaTurmaProfessor.idDisciplina = ? "
				+ "group by tbUsuario.nomeUsuario, tbUsuario.sobrenomeUsuario, tbUsuario.emailUsuario";

		PreparedStatement ps = c.prepareStatement(sql);

		ps.setInt(1, turma.getId());
		ps.setInt(2, disciplina.getId());

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

	public String selectAVG(Turma turma, Disciplina disciplina) throws SQLException {

		String sql = "SELECT AVG(nota) FROM tbEntrega e, tbAluno a, tbDisciplina d, tbDisciplinaTurmaProfessor dtp, tbAtividade ati "
				+ "WHERE e.idAluno = a.idAluno " + "AND d.idDisciplina = dtp.idDisciplina "
				+ "AND dtp.idDisciplinaTurmaProfessor = ati.idDisciplinaTurmaProfessor "
				+ "AND e.idAtividade = ati.idAtividade " + "AND dtp.idTurma = ? " + "AND dtp.idDisciplina = ? ";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, turma.getId());
		ps.setInt(2, disciplina.getId());

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getDouble(1) + "";
		}

		return "0";
	}

	public String selectCount(Turma turma, Disciplina disciplina) throws SQLException {
		String sql = "SELECT COUNT(a.idAluno) FROM tbEntrega e, tbAluno a, tbDisciplina d, tbDisciplinaTurmaProfessor dtp, tbAtividade ati "
				+ "WHERE e.idAluno = a.idAluno " + "AND d.idDisciplina = dtp.idDisciplina "
				+ "AND dtp.idDisciplinaTurmaProfessor = ati.idDisciplinaTurmaProfessor "
				+ "AND e.idAtividade = ati.idAtividade " + "AND dtp.idTurma = ? " + "AND dtp.idDisciplina = ? "
						+ "GROUP BY e.idAluno";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, turma.getId());
		ps.setInt(2, disciplina.getId());

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt(1) + "";
		}

		return "0";
	}

	public String selectMax(Turma turma, Disciplina disciplina) throws SQLException {
		String sql = "SELECT MAX(e.nota) FROM tbEntrega e, tbAluno a, tbDisciplina d, tbDisciplinaTurmaProfessor dtp, tbAtividade ati "
				+ "WHERE e.idAluno = a.idAluno " + "AND d.idDisciplina = dtp.idDisciplina "
				+ "AND dtp.idDisciplinaTurmaProfessor = ati.idDisciplinaTurmaProfessor "
				+ "AND e.idAtividade = ati.idAtividade " + "AND dtp.idTurma = ? " + "AND dtp.idDisciplina = ? ";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, turma.getId());
		ps.setInt(2, disciplina.getId());
		
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getDouble(1) + "";
		}

		return "0";
	}

	public String selectMin(Turma turma, Disciplina disciplina) throws SQLException {
		String sql = "SELECT MIN(e.nota) FROM tbEntrega e, tbAluno a, tbDisciplina d, tbDisciplinaTurmaProfessor dtp, tbAtividade ati "
				+ "WHERE e.idAluno = a.idAluno " + "AND d.idDisciplina = dtp.idDisciplina "
				+ "AND dtp.idDisciplinaTurmaProfessor = ati.idDisciplinaTurmaProfessor "
				+ "AND e.idAtividade = ati.idAtividade " + "AND dtp.idTurma = ? "
		+ "AND dtp.idDisciplina = ? ";


		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, turma.getId());
		ps.setInt(2, disciplina.getId());

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getDouble(1) + "";
		}

		return "0";
	}

}
