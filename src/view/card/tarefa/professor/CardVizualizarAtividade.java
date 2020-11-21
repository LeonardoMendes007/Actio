package view.card.tarefa.professor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import view.Util;

public class CardVizualizarAtividade {
	
	private GridPane gridCard;

	private Label lblEntregue;

	private Label lblAluno;
	
	private Button btCorrigir;
	
	private BorderPane borderPrincipal;
	
	public CardVizualizarAtividade(BorderPane borderPrincipal, String nome, boolean status, String nota) {

		this.borderPrincipal = borderPrincipal;

        initGridCard();
        
        initLabels(nome,status);
        
        initButton();
        
        initEvent();

	}

	private void initEvent() {
		
		gridCard.widthProperty().addListener((x) -> {
			
			lblAluno.setPrefWidth(gridCard.getWidth()*0.37);
			lblEntregue.setPrefWidth(gridCard.getWidth()*0.37);
			btCorrigir.setPrefWidth(gridCard.getWidth()*0.25);
			
		});
		
	}

	private void initButton() {
		
		btCorrigir = new Button("Corrigir");
		
		Util.setFontePadrao(new Button[] {btCorrigir}, 18, FontWeight.BOLD);
		
		btCorrigir.setStyle(btCorrigir.getStyle() + "-fx-background-color: #53BDBE;");
		
		btCorrigir.setAlignment(Pos.CENTER);
		
		btCorrigir.setTextFill(Color.WHITE);
		
		gridCard.add(btCorrigir, 2, 0);
	}

	private void initLabels(String nome, boolean status) {
		
		lblAluno = new Label(nome);
		
		if (status) {
			lblEntregue = new Label("SIM");
		}else {
			lblEntregue = new Label("N�O");
		}
		
		lblAluno.setAlignment(Pos.CENTER);
		lblEntregue.setAlignment(Pos.CENTER);
		
		Util.setFontePadrao(new Label[] {lblAluno, lblEntregue}, 20, FontWeight.BOLD);
		
		gridCard.add(lblAluno, 0, 0);
		gridCard.add(lblEntregue, 1, 0);
	}

	private void initGridCard() {
		
		gridCard = new GridPane();
		gridCard.setPrefWidth(borderPrincipal.getWidth());
		gridCard.setPrefHeight(35);
		gridCard.setAlignment(Pos.CENTER);
		gridCard.setPadding(new Insets(7));
		
		gridCard.setStyle("-fx-background-color: #D5D5D5; -fx-background-radius: 5px; -fx-border-radius: 5px;");
		
	    borderPrincipal.widthProperty().addListener((x) -> gridCard.setPrefWidth(borderPrincipal.getWidth()*0.90));
	}
	
	public GridPane getCard() {
		return gridCard;
	}

	
}