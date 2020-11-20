package controller.professor;

import javafx.scene.layout.BorderPane;
import view.card.tarefa.professor.CardVizualizarAtividade;
import view.professor.VisualizarAtividade;

public class VisualizarAtividadeController {
	
	private VisualizarAtividade visualizar;
	
	public VisualizarAtividadeController(VisualizarAtividade visualizar) {
		
		this.visualizar = visualizar;
	}
	
	public void addCards() {
		
		CardVizualizarAtividade card1 = new CardVizualizarAtividade(visualizar.getBorderPrincipal(), "Alicia", true, "10");
		CardVizualizarAtividade card2 = new CardVizualizarAtividade(visualizar.getBorderPrincipal(), "Leonardo", true, "0");
		CardVizualizarAtividade card3 = new CardVizualizarAtividade(visualizar.getBorderPrincipal(), "Alicia", true, "-");
		CardVizualizarAtividade card4 = new CardVizualizarAtividade(visualizar.getBorderPrincipal(), "Alicia", true, "-");
		CardVizualizarAtividade card5 = new CardVizualizarAtividade(visualizar.getBorderPrincipal(), "Alicia", true, "-");
		CardVizualizarAtividade card6 = new CardVizualizarAtividade(visualizar.getBorderPrincipal(), "Alicia", true, "-");
		CardVizualizarAtividade card7 = new CardVizualizarAtividade(visualizar.getBorderPrincipal(), "Alicia", true, "-");
		CardVizualizarAtividade card8 = new CardVizualizarAtividade(visualizar.getBorderPrincipal(), "Alicia", true, "-");
		CardVizualizarAtividade card9 = new CardVizualizarAtividade(visualizar.getBorderPrincipal(), "Alicia", true, "-");
		CardVizualizarAtividade card10 = new CardVizualizarAtividade(visualizar.getBorderPrincipal(), "Alicia", true, "-");
	
	    visualizar.setCard(card1);
	    visualizar.setCard(card2);
	    visualizar.setCard(card3);
	    visualizar.setCard(card4);
	    visualizar.setCard(card5);
	    visualizar.setCard(card6);
	    visualizar.setCard(card7);
	    visualizar.setCard(card8);
	    visualizar.setCard(card9);
	    visualizar.setCard(card10);
	
	}

}
