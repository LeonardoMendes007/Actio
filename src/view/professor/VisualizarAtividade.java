package view.professor;

import controller.professor.VisualizarAtividadeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import view.Util;
import view.card.tarefa.professor.CardVizualizarAtividade;

public class VisualizarAtividade {

	private BorderPane borderPrincipal;

	private Label lblCriarAtividade;

	private GridPane gridPrincipal;

	private GridPane gridInterno;

	private Label lblTitulo;

	private Label lblSubTitulo;

	private GridPane gridBarraSuperior;

	private Label lblAluno;

	private Label lblEntregue;

	private Label lblNota;
	
	private ScrollPane scrollLista;
	
	private VBox vboxLista;
	
	private VisualizarAtividadeController controller = new VisualizarAtividadeController(this);
	

	public VisualizarAtividade(BorderPane borderPrincipal) {
		this.borderPrincipal = borderPrincipal;

		initTela();
	}

	private void initTela() {

		borderPrincipal.getChildren().clear();

		initGrid();

		initLabelCriarAtividade();

		initGridInterno();

		initLabelTitulo();

		initLabelSubTitulo();

		initBarraSuperior();
		
		initLista();
		
		initTelaDetalhesAtividade();
		
		controller.addCards();

	}

	private void initTelaDetalhesAtividade() {
		
		
	}

	private void initLista() {
		
		initScroll();
		
		initVbox();
	}

	private void initVbox() {
		
		vboxLista = new VBox(5);
		
		vboxLista.setPadding(new Insets(10));
		
		scrollLista.setContent(vboxLista);
		
	}

	private void initScroll() {
		
		scrollLista = new ScrollPane();
		
		scrollLista.setStyle("-fx-background-color: #EAEAEA; -fx-background-radius: 5px; -fx-border-radius: 5px;");
		
		gridInterno.add(scrollLista, 0, 3);
	}

	private void initBarraSuperior() {

		initGridBarraSuperior();

		initLabelAluno();

		initLabelEntregue();

		initLabelNota();
	}

	private void initLabelNota() {

		lblAluno = new Label("Aluno");

		Util.setFontePadrao(new Label[] { lblAluno }, 20, FontWeight.BOLD);
		
		gridBarraSuperior.add(lblAluno, 0, 0);

		gridBarraSuperior.widthProperty().addListener((x)-> lblAluno.setPrefWidth(gridBarraSuperior.getWidth()*0.33));

		lblAluno.setAlignment(Pos.CENTER);
	}

	private void initLabelEntregue() {
		lblEntregue = new Label("Entregue");

		Util.setFontePadrao(new Label[] { lblEntregue }, 20, FontWeight.BOLD);

		gridBarraSuperior.add(lblEntregue, 1, 0);
		
		gridBarraSuperior.widthProperty().addListener((x)-> lblEntregue.setPrefWidth(gridBarraSuperior.getWidth()*0.33));

		lblEntregue.setAlignment(Pos.CENTER);

	}

	private void initLabelAluno() {
		lblNota = new Label("Nota");

		Util.setFontePadrao(new Label[] { lblNota }, 20, FontWeight.BOLD);

		gridBarraSuperior.add(lblNota, 2, 0);
		
		gridBarraSuperior.widthProperty().addListener((x)-> lblNota.setPrefWidth(gridBarraSuperior.getWidth()*0.33));

		
		lblNota.setAlignment(Pos.CENTER);
	}

	private void initGridBarraSuperior() {

		gridBarraSuperior = new GridPane();

		gridBarraSuperior.setStyle("-fx-background-color: #C4C4C4; -fx-background-radius: 5px; -fx-border-radius: 5px;");
		
		gridBarraSuperior.setPrefWidth(2000);
		gridBarraSuperior.setPrefHeight(60);
		
		gridBarraSuperior.setMargin(gridBarraSuperior, new Insets(15, 0, 0, 0));
		
		gridBarraSuperior.setAlignment(Pos.CENTER);
		gridInterno.add(gridBarraSuperior, 0, 2);

	}

	private void initLabelSubTitulo() {

		lblSubTitulo = new Label("4° ADS - TARDE");

		Util.setFontePadrao(new Label[] { lblSubTitulo }, 20, FontWeight.BOLD);

		gridInterno.add(lblSubTitulo, 0, 1);
	}

	private void initLabelTitulo() {

		lblTitulo = new Label("Atividade P1");

		Util.setFontePadrao(new Label[] { lblTitulo }, 27, FontWeight.BOLD);

		gridInterno.add(lblTitulo, 0, 0);
	}

	private void initGridInterno() {

		gridInterno = new GridPane();

		gridInterno.setPrefSize(2000, 2000);

		gridInterno.setPadding(new Insets(30));

		gridInterno.setStyle("-fx-background-color: #FF9DBA; -fx-background-radius: 20px; -fx-border-radius: 20px;");

		gridPrincipal.add(gridInterno, 0, 1);
	}

	private void initLabelCriarAtividade() {

		lblCriarAtividade = new Label("Visualizar Atividade");

		lblCriarAtividade
				.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + 30 + " ;");

		gridPrincipal.add(lblCriarAtividade, 0, 0);

		gridPrincipal.setMargin(lblCriarAtividade, new Insets(0, 0, 10, 0));

	}

	private void initGrid() {

		gridPrincipal = new GridPane();

		gridPrincipal.setPrefSize(2000, 2000);

		gridPrincipal.setPadding(new Insets(0, 0, 5, 20));

		borderPrincipal.setCenter(gridPrincipal);
		borderPrincipal.setMargin(gridPrincipal, new Insets(-30, 15, 0, 15));
	}
	
	public void setCard(CardVizualizarAtividade card) {
		
		vboxLista.getChildren().add(card.getCard());
		
	}

	private void trocarConteudo() {
		
		gridInterno.setVisible(false);
		
	}

	public BorderPane getBorderPrincipal() {
		return borderPrincipal;
	}

	public void setBorderPrincipal(BorderPane borderPrincipal) {
		this.borderPrincipal = borderPrincipal;
	}
	
	
	
	
}
