package controller.aluno;

import java.sql.SQLException;

import model.Entrega;
import persistence.EntregaDao;
import view.aluno.EntregaAtividadeAluno;

public class ControllerEntregaAtividade {
	
	private EntregaAtividadeAluno viewEntregaAtividade;
	
	public ControllerEntregaAtividade(EntregaAtividadeAluno viewEntregaAtividade) {
		
		this.viewEntregaAtividade = viewEntregaAtividade;
	
	}

	public void entregarTarefa() {
		
		try {
			EntregaDao dao = new EntregaDao();
			
//			Entrega entrega = new Entrega(, dtEntrega, nota, ativ, aluno)
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
