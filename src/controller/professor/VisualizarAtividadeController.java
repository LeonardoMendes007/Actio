package controller.professor;

import java.sql.SQLException;
import java.util.List;

import model.Aluno;
import model.Atividade;
import model.Usuario;
import persistence.AtividadeDao;
import view.card.tarefa.professor.CardVizualizarAtividade;
import view.professor.VisualizarAtividade;

public class VisualizarAtividadeController {
	
	private VisualizarAtividade visualizar;
	
	private Atividade atividade;
	
	public VisualizarAtividadeController(Atividade atividade, VisualizarAtividade visualizar) {
		
		this.atividade = atividade;
		
		this.visualizar = visualizar;
	}
	
	public void addCards() {
		
		try {
			AtividadeDao dao = new AtividadeDao();
			
			List<Aluno> alunos = dao.findAlunos(atividade);
			
			for (Aluno aluno : alunos) {

				
				CardVizualizarAtividade card1 = new CardVizualizarAtividade(visualizar.getBorderPrincipal(), aluno, atividade);
			
			    visualizar.setCard(card1);

			    
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

}
