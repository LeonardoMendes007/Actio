package persistence.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Aluno;
import model.Atividade;
import model.Disciplina;
import model.Entrega;
import model.Professor;

public interface IEntregaDao {

	public void insert(Entrega entrega) throws SQLException;

	public void updateAluno(Entrega entrega) throws SQLException;

	public void updateProfessor(Entrega entrega) throws SQLException;

	public void delete(Entrega entrega) throws SQLException;

	public List<Entrega> findAllEntregaAluno(Aluno aluno) throws SQLException;

	public boolean isGrupo(int tipo);

	public Entrega findEntregaAtividadeAluno(Atividade atividade, Aluno aluno) throws SQLException;

	public List<Entrega> findEntregaAtividade(Disciplina disciplina, Aluno aluno) throws SQLException;

	public List<Entrega> findAlunoAtividade(Atividade atividade) throws SQLException;

	public String selectAVG(Aluno aluno, Disciplina disciplina) throws SQLException;

	public String selectCount(Aluno aluno, Disciplina disciplina) throws SQLException;

	public String selectMax(Aluno aluno, Disciplina disciplina) throws SQLException;

	public String selectMin(Aluno aluno, Disciplina disciplina) throws SQLException;

	public List<Entrega> findAllParaCorrigir(Professor professor) throws SQLException;
}
