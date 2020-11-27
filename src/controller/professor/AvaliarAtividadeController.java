package controller.professor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import model.Aluno;
import model.Atividade;
import model.Entrega;
import persistence.EntregaDao;
import view.professor.AvaliarAtividade;

public class AvaliarAtividadeController {
	
	private AvaliarAtividade viewAvaliarAtividade;
	
	private Aluno aluno;
	
	private Atividade atividade;

	public AvaliarAtividadeController(AvaliarAtividade viewAvaliarAtividade, Aluno aluno, Atividade atividade) {
		this.viewAvaliarAtividade = viewAvaliarAtividade;
		this.aluno = aluno;
		this.atividade = atividade;
	}

	public void baixarArquivoEntrega() {
		
		try {
			
			EntregaDao dao = new EntregaDao();

			Entrega entrega = dao.findEntregaAtividadeAluno(atividade, aluno);
			
			if (entrega != null) {

				System.out.println(entrega.getPathArquivos());
				
				Desktop.getDesktop().open(new File(entrega.getPathArquivos()));

			}else {
				
				System.err.println("Entrega não existe");
				
			}
			
			
			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
