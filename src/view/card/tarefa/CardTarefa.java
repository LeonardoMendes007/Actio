package view.card.tarefa;

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
import view.Util;
import view.card.ICard;

public class CardTarefa implements ICard {

	private BorderPane borderPrincipal;

	private Label lblDisciplina;

	private Label lblTitulo;

	private Label lblLegenda;
	
	private Pane paneGroupEPrazo;

	private Label lblDataDeEntrega;

	private Label lblNumDeDias;

	public CardTarefa(String titulo, String legenda, String disciplina, Date prazo, boolean group) {

		this.borderPrincipal = new BorderPane();
		this.borderPrincipal.setPrefSize(280, 150);
		this.borderPrincipal.setStyle(
				"-fx-background-color: #9DC4FF; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-cursor: hand;");
        this.borderPrincipal.setPadding(new Insets(5));
		
		paneGroupEPrazo = new Pane();
		borderPrincipal.setTop(paneGroupEPrazo);
		
		initCentroCard(titulo, legenda);

		initDisciplina(disciplina);

		verificarGroup(group);
		
		initPrazoDeEntrega(prazo);
	}

	private void initPrazoDeEntrega(Date prazo) {
		
	    lblDataDeEntrega = new Label("Até " + prazo.getDay() + "/" + prazo.getMonth());
		lblNumDeDias = new Label("Faltam " + 2 + " dias");
		
		lblDataDeEntrega.setAlignment(Pos.CENTER_RIGHT);
		
		Util.setFontePadrao(new Label[] { lblDataDeEntrega }, 12);
		Util.setFontePadrao(new Label[] { lblNumDeDias }, 12);

		
		GridPane gridData = new GridPane();
		
		gridData.add(lblDataDeEntrega, 0, 0);
		gridData.add(lblNumDeDias, 0, 1);
		gridData.setLayoutX(190);
		paneGroupEPrazo.getChildren().add(gridData);
	}

	private void verificarGroup(boolean group) {

		if (group) {

			carregarImageGroup("src\\view\\img\\group 1.png");

		} else {

			carregarImageGroup("src\\view\\img\\user 1.png");

		}

	}

	private void carregarImageGroup(String path) {
		try {
			Image imagem = new Image(new FileInputStream(path));

			ImageView ivGroup = new ImageView(imagem);
			ivGroup.setFitWidth(40.0);
			ivGroup.setFitHeight(40.0);
			paneGroupEPrazo.getChildren().add(ivGroup);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		

	}

	private void initDisciplina(String disciplina) {

		lblDisciplina = new Label(disciplina);
		Util.setFontePadrao(new Label[] { lblDisciplina }, 12);

		lblDisciplina.setPadding(new Insets(5));
		lblDisciplina.setStyle(lblDisciplina.getStyle()
				+ "-fx-background-color: rgba(0, 0, 0, 0.3); -fx-background-radius: 10px; -fx-border-radius: 10px;");

		borderPrincipal.setBottom(lblDisciplina);
		borderPrincipal.setAlignment(lblDisciplina, Pos.CENTER);

	}

	private void initCentroCard(String titulo, String legenda) {

		lblTitulo = new Label(titulo);
		lblLegenda = new Label(legenda);

		Util.setFontePadrao(new Label[] { lblTitulo }, 16);
		Util.setFontePadrao(new Label[] { lblLegenda }, 12);

		GridPane gridInterno = new GridPane();
		gridInterno.add(lblTitulo, 0, 0);
		gridInterno.add(lblLegenda, 0, 1);
        gridInterno.setPadding(new Insets(0, 0, 0, 10));
		
		borderPrincipal.setCenter(gridInterno);
		borderPrincipal.setAlignment(gridInterno, Pos.BOTTOM_LEFT);
	}

	private void initEvent() {
		borderPrincipal.setOnMouseClicked((x) -> System.out.println("Você clicou em " + lblTitulo.getText()));

	}

	@Override
	public Node getCard() {
		return borderPrincipal;
	}
}
