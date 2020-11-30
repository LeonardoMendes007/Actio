package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Aluno;
import model.AlunoEntregaTotalizado;
import model.Disciplina;
import model.Turma;

public interface IAlunoDao {
	
	public Aluno findAluno(Aluno a) throws SQLException;

	public List<AlunoEntregaTotalizado> findAlunoEntrega(Turma turma, Disciplina disciplina) throws SQLException;

	Double selectSumAluno(int idAluno) throws SQLException;

	Integer selectCountAluno(int idDisciplina, int idTurma) throws SQLException ;


	public List<AlunoEntregaTotalizado> findAlunoSemEntrega(Turma turma, Disciplina disciplina) throws SQLException;

}
