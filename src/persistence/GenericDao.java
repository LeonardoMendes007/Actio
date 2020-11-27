package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import persistence.interfaces.IGenericDao;

public class GenericDao implements IGenericDao {

	private Connection c;

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		String hostName = "192.168.99.101";
		String dbName = "bdActio";
		String user = "sa";
		String senha = "Alicia@1234";
	
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		c = DriverManager.getConnection(String.format(
				"jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;", hostName, dbName, user, senha));

		
		System.out.println("Aeeeeee felicidade do dia");

		
		return c;
	}

	public void closeConnection() throws SQLException {
		c.close();
	}

}
