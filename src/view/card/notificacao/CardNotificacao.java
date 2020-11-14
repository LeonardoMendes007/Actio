package view.card.notificacao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import view.Util;
import view.card.ICard;

public class CardNotificacao implements ICard{

	private BorderPane borderPrincipal;

	private Label lblTitulo;
	
	private Label lblClique;
	
	private Label lblData;

	public CardNotificacao(String titulo, String clique, Date data) {

		this.borderPrincipal = new BorderPane();
		this.borderPrincipal.setPrefSize(260, 60);
		this.borderPrincipal.setStyle(
				"-fx-background-color: #C2C2C2; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-cursor: hand;");
        this.borderPrincipal.setPadding(new Insets(5));
		
		initLabelTitulo(titulo);
		
		initLabelClique(clique);
		
		initLabelData(data);
		
		initEvents();
	}

	
	private void initLabelData(Date data) {
		
		lblData = new Label(data.getDay() + "/" + data.getMonth() + " - " + "12:50");
		
		Util.setFontePadrao(new Label[] { lblData }, 10, FontWeight.LIGHT);
		lblData.setTextFill(Color.web("#000000", 0.5));
		
		borderPrincipal.setTop(lblData);
		borderPrincipal.setAlignment(lblData, Pos.TOP_RIGHT);
		
	}


	private void initLabelClique(String clique) {
		
		lblClique = new Label(clique);
		
		Util.setFontePadrao(new Label[] { lblClique }, 11, FontWeight.BOLD);
		
		lblClique.setStyle(lblClique.getStyle()
				+ "-fx-background-color: rgba(0, 0, 0, 0.2); -fx-background-radius: 10px; -fx-border-radius: 10px;");
				
		lblClique.setPadding(new Insets(1, 7, 1, 7));
		lblClique.setTextFill(Color.web("#1D5959"));
		
		borderPrincipal.setBottom(lblClique);
		borderPrincipal.setAlignment(lblClique, Pos.CENTER);
	}


	private void initLabelTitulo(String titulo) {
		
		lblTitulo = new Label(titulo);
		lblTitulo.setPadding(new Insets(5, 10, 10, 10));

		//lblTitulo.setMaxWidth(220);
		
		Util.setFontePadrao(new Label[] { lblTitulo }, 12, FontWeight.NORMAL);
		
		borderPrincipal.setLeft(lblTitulo);
		
	}

	private void initEvents() {
		Util.hoverSize(borderPrincipal);
	}

	@Override
	public Node getCard() {
		return borderPrincipal;
	}

}
