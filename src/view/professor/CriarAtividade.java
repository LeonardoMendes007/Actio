package view.professor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import view.Util;

public class CriarAtividade {

	private BorderPane borderPrincipal;

	private Label lblCriarAtividade;

	private GridPane gridPrincipal;

	private BorderPane borderInterno;

	private Label lblNome;

	private TextField tfAtividade;

	private Label lblDescricao;

	private TextArea taDescricao;

	private StackPane stackInterno;

	private Button btAgendar;

	private Button btPublicar;

	private GridPane gridInterno;

	private GridPane gridButtons;

	private GridPane gridAgendar;

	private Label lblAgendar;

	private BorderPane borderAgendar;

	private Label lblDataPublicacao;

	private TextField tfDataPublicacao;

	private Button btConcluir;

	private GridPane gridData;

	public CriarAtividade(BorderPane borderPrincipal) {
		this.borderPrincipal = borderPrincipal;

		initTela();
	}

	private void initTela() {

		borderPrincipal.getChildren().clear();

		initGrid();

		initLabelCriarAtividade();

		initBorderInterno();

		initStackPaneInterno();

		initGridInterno();

		initLabels();

		initTextField();

		initTextArea();

		initGridButtons();

		initButtonAgendar();

		initButtonPublicar();

		initGridAgendar();

		initLabelAgendar();

		initBorderAgendar();

		initGridData();

		initLabelDataPublicacao();

		initTextFieldDataPublicacao();

		initButtonConcluir();

	}

	private void initButtonConcluir() {
		
		btConcluir = new Button("Concluir");
		
		btConcluir.setTextFill(Color.WHITE);
		
		Util.setFontePadrao(new Button[] {btConcluir}, 20, FontWeight.BOLD);
		
		Util.hoverFade(btConcluir);
		
		btConcluir.setStyle(btConcluir.getStyle() + "-fx-background-color: #57BEBE; -fx-background-radius: 10px; -fx-border-radius: 10px;");
		
		borderAgendar.setBottom(btConcluir);
		borderAgendar.setAlignment(btConcluir, Pos.CENTER);
		borderAgendar.setMargin(btConcluir, new Insets(10));
		
		btConcluir.setOnMouseClicked((x) -> mostrarCriarAtividades());
		
	}

	private void mostrarCriarAtividades() {
		
		borderInterno.setCenter(stackInterno);
		borderInterno.setRight(gridButtons);
		
	}

	private void mostrarAgendar() {

		borderInterno.getChildren().clear();

		borderInterno.setCenter(gridAgendar);

	}

	private void initGridData() {

		gridData = new GridPane();

		borderAgendar.setCenter(gridData);
		borderAgendar.setMargin(gridData, new Insets(25, 20, 20, 26));

		borderAgendar.setAlignment(gridData, Pos.CENTER);
	}

	private void initTextFieldDataPublicacao() {

		tfDataPublicacao = new TextField();
		tfDataPublicacao.setPrefHeight(40);
		tfDataPublicacao.setPrefWidth(150);

		gridData.add(tfDataPublicacao, 0, 1);
	}

	private void initLabelDataPublicacao() {

		lblDataPublicacao = new Label("Data de Publicacao");
		lblDataPublicacao.setTextFill(Color.GRAY);

		Util.setFontePadrao(new Label[] { lblDataPublicacao }, 22, FontWeight.BOLD);

		gridData.add(lblDataPublicacao, 0, 0);

	}

	private void initBorderAgendar() {

		borderAgendar = new BorderPane();
		borderAgendar.setPrefSize(300, 200);

		borderAgendar.setStyle("-fx-background-color: #D7D7D7; -fx-background-radius: 20px; -fx-border-radius: 20px;");

		gridAgendar.add(borderAgendar, 0, 1);
		gridAgendar.setPadding(new Insets(15));
	}

	private void initLabelAgendar() {

		lblAgendar = new Label("Agendar");

		lblAgendar.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + 25 + " ;");

		gridAgendar.add(lblAgendar, 0, 0);

		gridAgendar.setMargin(lblAgendar, new Insets(0, 0, 10, 0));

	}

	private void initGridAgendar() {

		gridAgendar = new GridPane();
		gridAgendar.setMinSize(400, 400);

		gridAgendar.setAlignment(Pos.CENTER);

		gridAgendar.setStyle("-fx-background-color: #8E8D8D;-fx-background-radius: 20px; -fx-border-radius: 20px;");
	}

	private void initGridButtons() {

		gridButtons = new GridPane();

		gridButtons.setPadding(new Insets(10));

		borderInterno.setRight(gridButtons);
		borderInterno.setAlignment(gridButtons, Pos.BOTTOM_CENTER);

	}

	private void initButtonPublicar() {

		btPublicar = new Button("Publicar");

		btPublicar.setPrefWidth(borderInterno.getWidth() * 0.23);

		borderInterno.widthProperty().addListener((x) -> btPublicar.setPrefWidth(borderInterno.getWidth() * 0.23));

		btPublicar.setTextFill(Color.WHITE);

		Util.setFontePadrao(new Button[] { btPublicar }, 21, FontWeight.BOLD);
		btPublicar.setStyle(btPublicar.getStyle()
				+ "-fx-background-color: #1D5959; -fx-background-radius: 10px; -fx-border-radius: 10px;");

		Util.hoverFade(btPublicar);

		gridButtons.add(btPublicar, 0, 1);
		gridButtons.setMargin(btPublicar, new Insets(8, 0, 0, 0));
	}

	private void initButtonAgendar() {

		btAgendar = new Button("Agendar");

		btAgendar.setPrefWidth(borderInterno.getWidth() * 0.23);

		btAgendar.setOnMouseClicked((x) -> mostrarAgendar());

		borderInterno.widthProperty().addListener((x) -> btAgendar.setPrefWidth(borderInterno.getWidth() * 0.23));

		btAgendar.setTextFill(Color.WHITE);

		Util.setFontePadrao(new Button[] { btAgendar }, 21, FontWeight.BOLD);
		btAgendar.setStyle(btAgendar.getStyle()
				+ "-fx-background-color: #57BEBE; -fx-background-radius: 10px; -fx-border-radius: 10px;");

		Util.hoverFade(btAgendar);

		gridButtons.add(btAgendar, 0, 0);
	}

	private void initTextArea() {

		taDescricao = new TextArea();

		taDescricao.setPrefWidth(borderInterno.getWidth() * 0.70);
		taDescricao.setPrefHeight(borderInterno.getHeight() * 0.60);

		borderInterno.widthProperty().addListener((x) -> taDescricao.setPrefWidth(borderInterno.getWidth() * 0.70));
		borderInterno.heightProperty().addListener((x) -> taDescricao.setPrefHeight(borderInterno.getHeight() * 0.60));

		gridInterno.add(taDescricao, 0, 3);

	}

	private void initTextField() {

		tfAtividade = new TextField();

		tfAtividade.setPrefHeight(borderInterno.getHeight() * 0.10);
		tfAtividade.setPrefWidth(borderInterno.getWidth() * 0.70);

		borderInterno.widthProperty().addListener((x) -> tfAtividade.setPrefWidth(borderInterno.getWidth() * 0.70));
		borderInterno.heightProperty().addListener((x) -> tfAtividade.setPrefHeight(borderInterno.getHeight() * 0.10));

		gridInterno.add(tfAtividade, 0, 1);

	}

	private void initGridInterno() {

		gridInterno = new GridPane();

		gridInterno.setVgap(15);

		stackInterno.getChildren().add(gridInterno);

	}

	private void initLabels() {

		lblNome = new Label("Nome de Atividade");
		lblDescricao = new Label("Descri��o da Atividade");

		lblNome.setTextFill(Color.GRAY);
		lblDescricao.setTextFill(Color.GRAY);

		Util.setFontePadrao(new Label[] { lblNome, lblDescricao }, 15, FontWeight.BOLD);

		gridInterno.add(lblNome, 0, 0);
		gridInterno.add(lblDescricao, 0, 2);

	}

	private void initStackPaneInterno() {

		stackInterno = new StackPane();
		stackInterno.setPadding(new Insets(40));

		borderInterno.setCenter(stackInterno);
	}

	private void initBorderInterno() {

		borderInterno = new BorderPane();

		borderInterno.setPrefSize(2000, 2000);

		borderInterno.setStyle("-fx-background-color: #D7D7D7; -fx-background-radius: 20px; -fx-border-radius: 20px;");

		gridPrincipal.add(borderInterno, 0, 1);
	}

	private void initLabelCriarAtividade() {

		lblCriarAtividade = new Label("Criar Atividade");

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

}