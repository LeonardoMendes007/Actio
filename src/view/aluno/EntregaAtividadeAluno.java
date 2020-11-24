package view.aluno;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import controller.aluno.ControllerEntregaAtividade;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Atividade;
import view.Util;

public class EntregaAtividadeAluno {

	private BorderPane borderPrincipal;

	private GridPane gridAtividade;

	private Label lblAtividade;

	private BorderPane borderInterno;

	private ImageView ivGroup;

	private Label lblDisciplina;

	private Label lblTitulo;

	private Label lblDataDeEntrega;

	private Label lblNumDeDias;

	private Label lblLegenda;

	private Label lblEntrega;
	
	private Label lblTarefa;

	private Label lblArquivos;

	private Button btRemover;

	private Button btAdicionar;

	private Button btEntregar;

	private Label lblNomeAtividade;

	private HBox hboxButtons;
	
	private FileChooser file = new FileChooser();

	private GridPane gridCentral;

	private GridPane gridData;

	private GridPane gridSuperior;

	private GridPane gridTituloLegenda;

	private Atividade atividade;
	
	private ControllerEntregaAtividade controller = new ControllerEntregaAtividade(this);

	public EntregaAtividadeAluno(Atividade atividade, BorderPane pane) {

		this.atividade = atividade;

		this.borderPrincipal = pane;

		clearBorderPrincipal();

		initAtividade();

	}

	private void clearBorderPrincipal() {

		borderPrincipal.getChildren().clear();

	}

	private void initAtividade() {

		initGridAtividade();
		initLabelAtividade();
		initBorderInterno();
		initGridSuperior();
		initCentroCard(atividade.getNome(), atividade.getDescricao());
		initPrazoDeEntrega(atividade.getDtEntrega());
		initDisciplina(atividade.getDiscTurmaProf().getDisciplina().getNome());
		verificarGroup(atividade.isGrupo());

		initGridCentral();

		initLabelArquivos();

		initLabelEntrega();

		initButtons();

		initButtonEntregar();

		initEventReponsivel();

	}

	private void initButtonEntregar() {

		btEntregar = new Button("Entregar");

		Util.setFontePadrao(new Button[] { btEntregar }, 25, FontWeight.BOLD);

		btEntregar.setTextFill(Color.WHITE);
		btEntregar.setStyle(btEntregar.getStyle() + "-fx-background-color: #1D5959;");
		
		btEntregar.setOnMouseClicked((x) -> {
			
		     if(!lblEntrega.getText().equals("Clique para adicionar Arquivo") && !lblAtividade.getText().isEmpty()) {
		    	 
		    	 controller.entregarTarefa();
		    	 
		     }			
		    	 
		});

		borderInterno.setRight(btEntregar);
		borderInterno.setAlignment(btEntregar, Pos.BOTTOM_CENTER);
		borderInterno.setMargin(btEntregar, new Insets(0, 0, 15, 0));

	}

	private void initButtons() {
		btAdicionar = new Button("Adicionar");
		btRemover = new Button("Remover");

		Util.setFontePadrao(new Button[] { btAdicionar, btRemover }, 15, FontWeight.BOLD);

		btAdicionar.setStyle(btAdicionar.getStyle() + "-fx-background-color: #91CF2D;");
		btRemover.setStyle(btRemover.getStyle() + "-fx-background-color: #F55B51;");

		btAdicionar.setOnMouseClicked((x) -> {
		
			lblEntrega.setText(file.showOpenDialog(new Stage()).getPath());
		});
		
		initHbox();

		hboxButtons.getChildren().addAll(btRemover, btAdicionar);
		hboxButtons.setMargin(btAdicionar, new Insets(0, 0, 0, 10));

	}

	private void initHbox() {

		hboxButtons = new HBox();

		hboxButtons.setPadding(new Insets(10, 10, 10, 0));

		gridCentral.add(hboxButtons, 0, 4);
	}

	private void initLabelEntrega() {


		lblEntrega = new Label("Clique para adicionar Arquivo");
		lblArquivos = new Label("Clique para baixar tarefa");
		
		Util.setFontePadrao(new Label[] { lblEntrega, lblArquivos }, 18, FontWeight.NORMAL);

		lblArquivos.setStyle(lblArquivos.getStyle() + "-fx-color: blue;");
		lblEntrega.setStyle(lblEntrega.getStyle() + "-fx-color: blue;");

		gridCentral.add(lblEntrega, 0, 3);
		gridCentral.add(lblArquivos, 0, 1);

	}

	private void initLabelArquivos() {

		lblArquivos = new Label("Arquivos");

		lblNomeAtividade = new Label("Atividade");

		Util.setFontePadrao(new Label[] { lblArquivos, lblNomeAtividade }, 23, FontWeight.BOLD);

		lblArquivos.setPadding(new Insets(10, 10, 10, 0));

		lblNomeAtividade.setPadding(new Insets(10, 10, 10, 0));

		gridCentral.add(lblNomeAtividade, 0, 0);
		gridCentral.add(lblArquivos, 0, 2);

	}

	private void initGridCentral() {
		gridCentral = new GridPane();

		gridCentral.setAlignment(Pos.CENTER_LEFT);
		gridCentral.setPadding(new Insets(20, 20, 20, 20));

		borderInterno.setCenter(gridCentral);

	}

	private void initEventReponsivel() {

		borderPrincipal.widthProperty()
				.addListener((x) -> borderInterno.setPrefWidth(borderPrincipal.getWidth() * 0.90));

		borderPrincipal.widthProperty().addListener((x) -> {

			double tamanho = borderPrincipal.getWidth() - (285 + 80);

			gridTituloLegenda.setPrefWidth(tamanho);
//			tableEntrega.setPrefWidth(borderPrincipal.getWidth() * 0.50);
		});

//		borderPrincipal.heightProperty().addListener((x) -> {
//
//			tableEntrega.setPrefHeight(borderInterno.getHeight() * 0.60);
//
//		});

	}

	private void initGridSuperior() {

		gridSuperior = new GridPane();

		gridSuperior.setPrefWidth(2000);

		borderInterno.setTop(gridSuperior);

	}

	private void initBorderInterno() {

		borderInterno = new BorderPane();
		borderInterno.setPrefSize(2000, 2000);

		borderInterno.setPadding(new Insets(15));

		borderInterno.setStyle("-fx-background-color:" + atividade.getDiscTurmaProf().getDisciplina().getCor()
				+ "; -fx-background-radius: 20px; -fx-border-radius: 20px;");

		gridAtividade.add(borderInterno, 0, 1);
		gridAtividade.setAlignment(Pos.CENTER);
//        gridAtividade.setGridLinesVisible(true);
	}

	private void initGridAtividade() {

		gridAtividade = new GridPane();

		gridAtividade.setPrefSize(2000, 2000);

		gridAtividade.setPadding(new Insets(0, 0, 5, 20));

		borderPrincipal.setCenter(gridAtividade);
		borderPrincipal.setMargin(gridAtividade, new Insets(-30, 15, 0, 15));

	}

	private void initLabelAtividade() {

		lblAtividade = new Label("Entrega Atividade");

		lblAtividade.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + 30 + " ;");

		gridAtividade.add(lblAtividade, 0, 0);

		gridAtividade.setMargin(lblAtividade, new Insets(0, 0, 20, 0));

	}

	private void initPrazoDeEntrega(Date prazo) {

		lblDataDeEntrega = new Label("At� " + prazo.getDay() + "/" + prazo.getMonth());
		lblDataDeEntrega.setTextFill(Color.web("#FFFFFF"));
		lblDataDeEntrega.setTextAlignment(TextAlignment.RIGHT);

		lblNumDeDias = new Label("Faltam " + 2 + " dias");
		lblNumDeDias.setTextFill(Color.web("#FFFFFF"));
		lblNumDeDias.setTextAlignment(TextAlignment.RIGHT);

		Util.setFontePadrao(new Label[] { lblDataDeEntrega }, 20, FontWeight.BOLD);
		Util.setFontePadrao(new Label[] { lblNumDeDias }, 20, FontWeight.NORMAL);

		gridData = new GridPane();

		gridData.add(lblDataDeEntrega, 0, 0);
		gridData.add(lblNumDeDias, 0, 1);

		gridData.setValignment(lblDataDeEntrega, VPos.CENTER);
		gridData.setHalignment(lblDataDeEntrega, HPos.RIGHT);
		gridData.setValignment(lblNumDeDias, VPos.CENTER);
		gridData.setHalignment(lblNumDeDias, HPos.RIGHT);

		gridSuperior.add(gridData, 2, 0);

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

			ivGroup = new ImageView(imagem);
			ivGroup.setFitWidth(80.0);
			ivGroup.setFitHeight(80.0);
			gridSuperior.add(ivGroup, 0, 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void initDisciplina(String disciplina) {

		lblDisciplina = new Label(disciplina);
		lblDisciplina.setTextFill(Color.web("#000000", 0.5));

		Util.setFontePadrao(new Label[] { lblDisciplina }, 20, FontWeight.NORMAL);

		lblDisciplina.setPadding(new Insets(2));
		lblDisciplina.setAlignment(Pos.CENTER);
		lblDisciplina.setStyle(lblDisciplina.getStyle()
				+ "-fx-background-color: rgba(0, 0, 0, 0.3); -fx-background-radius: 10px; -fx-border-radius: 10px;");

//		lblDisciplina.setTextFill(corDisciplina);
		borderInterno.setBottom(lblDisciplina);
		borderInterno.setAlignment(lblDisciplina, Pos.CENTER);
	}

	private void initCentroCard(String titulo, String legenda) {

		gridTituloLegenda = new GridPane();

		lblTitulo = new Label(titulo);
		lblTitulo.setPadding(new Insets(0, 0, 0, 10));
		lblTitulo.setTextFill(Color.web("#000000", 0.5));
		lblTitulo.setAlignment(Pos.CENTER);

		lblLegenda = new Label(legenda);
		lblLegenda.setPadding(new Insets(0, 0, 0, 10));
		lblLegenda.setTextFill(Color.web("#000000", 0.5));
		lblLegenda.setAlignment(Pos.CENTER);

		Util.setFontePadrao(new Label[] { lblTitulo }, 25, FontWeight.BOLD);
		Util.setFontePadrao(new Label[] { lblLegenda }, 20, FontWeight.LIGHT);

		gridTituloLegenda.add(lblTitulo, 0, 0);
		gridTituloLegenda.add(lblLegenda, 0, 1);
		gridTituloLegenda.setAlignment(Pos.CENTER);
		double tamanho = borderPrincipal.getWidth() - (260 + 80);

		gridTituloLegenda.setPrefWidth(tamanho);
		gridSuperior.add(gridTituloLegenda, 1, 0);

	}

	public Label getLblEntrega() {
		return lblEntrega;
	}

	public void setLblEntrega(Label lblEntrega) {
		this.lblEntrega = lblEntrega;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	
	
	

}
