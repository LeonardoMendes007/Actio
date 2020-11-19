package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Atividade;
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
		
		String sql = "INSERT INTO atividade (nome,descricao,dtEmissao,dtPublicacao,dtEntrega,arquivos, turmaidTurma) VALUES (?,?,?,?,?,?,?)";
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Atividade atividade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Atividade findAtividade(Atividade atividade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Atividade> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}