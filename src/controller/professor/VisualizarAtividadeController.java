package controller.professor;

import java.sql.SQLException;
import java.util.List;

import model.Aluno;
import model.Atividade;
import model.Usuario;
import persistence.AtividadeDao;
import view.card.tarefa.professor.CardVizualizarAtividade;
import view.professor.HomeProfessor;
import view.professor.VisualizarAtividade;

public class VisualizarAtividadeController {
	
	private VisualizarAtividade viewVisualizar;
	
	public VisualizarAtividadeController(VisualizarAtividade viewVisualizar) {
		
		this.viewVisualizar = viewVisualizar;

	}
	
	public void addCards() {
		
		try {
			AtividadeDao dao = new AtividadeDao();
			
			List<Aluno> alunos = dao.findAlunos(viewVisualizar.getAtividade());
			
			for (Aluno aluno : alunos) {

				
				CardVizualizarAtividade card1 = new CardVizualizarAtividade(viewVisualizar.getBorderPrincipal(), aluno, viewVisualizar.getAtividade());
			
			    viewVisualizar.setCard(card1);

			    
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	public void deleteAtividade() {
		
		AtividadeDao dao;
		try {
			dao = new AtividadeDao();
			
			dao.delete(viewVisualizar.getAtividade());
			
		    new HomeProfessor(viewVisualizar.getBorderPrincipal(), viewVisualizar.getAtividade().getDiscTurmaProf().getProfessor());
			
		} catch (ClassNotFoundException | SQLException e) {
			
			System.err.println("Erro ao excluir atividade");
			e.printStackTrace();
			
		}
		
		
		
		
	}

}
