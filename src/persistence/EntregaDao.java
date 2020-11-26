package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Aluno;
import model.Atividade;
import model.Entrega;
import persistence.interfaces.IEntrega;
import persistence.interfaces.IGenericDao;

public class EntregaDao implements IEntrega{
	
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
		String sql = "UPDATE tbEntrega "
				+ "SET arquivosEntrega = ?, dtEntrega = GETDATE() "
				+ "WHERE idEntrega = ? ";
	
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, entrega.getPathArquivos().replaceAll("//", "/"));
		ps.setInt(2, entrega.getId());
		
		
		ps.execute();
		ps.close();
	}

	@Override
	public void delete(Entrega entrega) throws SQLException {
		// TODO Auto-generated method stub
		
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

	public Entrega findEntregaAtividadeAluno(Atividade atividade, Aluno aluno) throws SQLException {
		

		String sql = "SELECT e.* "
				+ "FROM tbEntrega e "
				+ "WHERE e.idAluno = ? "
				+ "AND e.idAtividade = ?";
	
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, aluno.getId());
		ps.setInt(2, atividade.getId());
		
		ResultSet rs = ps.executeQuery();
		
		Entrega entrega = null;
		
		while(rs.next()){
			
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
}
