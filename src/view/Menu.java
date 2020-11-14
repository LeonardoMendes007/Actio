package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.MenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class Menu {

	private Scene scn;
	private BorderPane borderPrincipal;
	private BorderPane borderCentral;
	private BorderPane borderLeft;
	private BorderPane borderConteudo;
	private GridPane grid;
	private GridPane gridUsuario;
	private GridPane gridAtrrbUsuario;
	private Label lblActio;
	private Button btAtividade;
	private Button btDisciplina;
	private Button btTurma;
	private ImageView ivUser;
	private Label lblNome;
	private Label lblTipoUsuario;
	private Background azulEscuro;
	private Background azulClaro;
	
	private MenuController menuController = new MenuController(this);

	public Menu(Scene scn) {
		this.scn = scn;
		initNewScene();
		initMenu();

		menuController.btHomeAction();

	}

	private void initMenu() {

		initMenuButtons();

		initGridLateralCentral();

		initBorderPaneLeft();

		initEvents();
		
		initPerfil();
		
		initBarraSuperior();
		
		initConteudo();
		

		borderPrincipal.setLeft(borderLeft);

	}
	
	private void initPerfil() {
		
		try {
			Image imagem = new Image(new FileInputStream("src\\view\\img\\icon2.png"));
		

			ivUser = new ImageView(imagem);
			ivUser.setFitWidth(50.0);
			ivUser.setFitHeight(50.0);
			ivUser.setStyle("-fx-background-radius: 10px; -fx-border-radius: 10px;");
			
			lblNome = new Label("Fulano");
			lblNome.setStyle("-fx-cursor: hand;");
			Util.setFontePadrao(new Label [] { lblNome }, 15, FontWeight.NORMAL);

			lblTipoUsuario = new Label("Aluno");
			lblTipoUsuario.setStyle("-fx-cursor: hand;");
			lblTipoUsuario.setTextFill(Color.web("#1D5959"));
			Util.setFontePadrao(new Label [] { lblTipoUsuario }, 15, FontWeight.BOLD);

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initConteudo() {

		borderConteudo = new BorderPane();
		borderConteudo.setStyle("-fx-background: #EAEAEA;");

		borderCentral.setCenter(borderConteudo);

	}

	private void initNewScene() {
		borderPrincipal = new BorderPane();
		borderPrincipal.setStyle("-fx-background-color: #1D5959;");
		scn.setRoot(borderPrincipal);
	}

	private void initBarraSuperior(){

		borderCentral = new BorderPane();
		borderCentral.setStyle(
				"-fx-background-color: #EAEAEA; -fx-background-radius: 35px 0px 0px 35px; -fx-border-radius: 15px;");

		initGridUsuario();

		borderPrincipal.setCenter(borderCentral);

	}

	private void initGridUsuario() {
		gridUsuario = new GridPane();
		gridUsuario.setAlignment(Pos.CENTER_RIGHT);
		gridUsuario.setPadding(new Insets(5, 15, 10, 10));
		gridUsuario.setHgap(0);

		//Grid com o nome e o tipo do usuário
		gridAtrrbUsuario = new GridPane();
		gridAtrrbUsuario.setAlignment(Pos.CENTER_RIGHT);
		gridAtrrbUsuario.setPadding(new Insets(0, 10, 0, 0));
		gridAtrrbUsuario.setVgap(0);

		gridAtrrbUsuario.add(lblNome, 0, 0);
		gridAtrrbUsuario.add(lblTipoUsuario, 0, 1);
		
		
		gridUsuario.add(gridAtrrbUsuario, 0, 0);
		gridUsuario.add(ivUser, 1, 0);
		
		borderCentral.setTop(gridUsuario);

	}

	
	private void initEvents() {

		lblActio.setOnMouseClicked(x -> {
			menuController.btHomeAction();
			clearCurrentScreen();
		});

		btAtividade.setOnMouseClicked(x -> {
			menuController.btAtividadeAction();
			currentScreen(btAtividade);
			
		});

		btAtividade.setOnMouseEntered(x -> {
			btAtividade.setBackground(azulClaro);
			
		});
		
		btAtividade.setOnMouseExited(x -> {
			btAtividade.setBackground(azulEscuro);
		});

		btDisciplina.setOnMouseClicked(x -> {
			menuController.btDisciplinaAction();
			currentScreen(btDisciplina);
		});
		
		btDisciplina.setOnMouseEntered(x -> {
			btDisciplina.setBackground(azulClaro);
			
		});
		
		btDisciplina.setOnMouseExited(x -> {
			btDisciplina.setBackground(azulEscuro);
		});
		
	}

	private void initBorderPaneLeft() {
		borderLeft = new BorderPane();
		borderLeft.setStyle("-fx-background-color: #1D5959;");

		initLabelActio();

		borderLeft.setCenter(grid);
		borderLeft.setTop(lblActio);
		borderLeft.setMargin(lblActio, new Insets(40, 0, 0, 0));
	}

	private void initLabelActio() {
		lblActio = new Label();
		lblActio.setAlignment(Pos.TOP_CENTER);
		FileInputStream input;
		try {
			input = new FileInputStream("src\\view\\img\\icon.png");
			lblActio.setBackground(new Background(
					new BackgroundImage(new Image(input), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
							BackgroundPosition.CENTER, new BackgroundSize(35, 35, true, true, true, false))));
		} catch (FileNotFoundException e) {
			lblActio.setText("Actio");
		}

		lblActio.setStyle("-fx-cursor: hand;");
		lblActio.setPrefWidth(200);
		lblActio.setPrefHeight(80);
		lblActio.setAlignment(Pos.CENTER);
	}

	private void initGridLateralCentral() {
		grid = new GridPane();
		grid.setVgap(20);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setStyle("-fx-background-color: #1D5959;");

		grid.add(btAtividade, 0, 3);
		grid.add(btDisciplina, 0, 4);

	}

	private void initMenuButtons() {
		
		//Seta as cores do fundo
		
		btAtividade = new Button("Atividade");
		btDisciplina = new Button("Disciplina");

		azulEscuro = new Background(new BackgroundFill(Color.web("#1D5959"), CornerRadii.EMPTY, Insets.EMPTY));
		azulClaro  = new Background(new BackgroundFill(Color.web("#53BDBE"), CornerRadii.EMPTY, Insets.EMPTY));

		setConfigButtons(new Button[] { btAtividade, btDisciplina });

		Util.initButtons(new Button[] { btAtividade, btDisciplina });

		clearCurrentScreen();

	}

	private void setConfigButtons(Button[] buttons) {

		for (Button button : buttons) {
		
			button.setBackground(azulEscuro);
			
			changeButtonIcon(button, false);
		}

	}

	public BorderPane getBorderConteudo() {
		return borderConteudo;
	}

	public void setBorderConteudo(BorderPane borderConteudo) {
		this.borderConteudo = borderConteudo;
	}

	public String getLblNomeUsuario() {
		return lblNome.getText();
	}

	public void setLblNomeUsuario(String nome) {
		this.lblNome.setText(nome);
	}
	

	public void changeButtonIcon(Button b, boolean selected) {
		FileInputStream iconeBotao;
		
		///Se o botão foi selecionado muda os ícones para a versão escura
		if(selected) {
			try {	
				if((b.getText().equals("Atividade"))){
					 iconeBotao = new FileInputStream("src\\view\\img\\pencil - azul.png");
					 b.setGraphic(new ImageView(new Image(iconeBotao, 25, 25, true, false)));
					 
				}else {
					 iconeBotao = new FileInputStream("src\\view\\img\\book - azul.png");
					 b.setGraphic(new ImageView(new Image(iconeBotao, 25, 25, true, false)));
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}		
		}else {
			try {	
				if((b.getText().equals("Atividade"))){
					 iconeBotao = new FileInputStream("src\\view\\img\\pencil - branco.png");
					 b.setGraphic(new ImageView(new Image(iconeBotao, 25, 25, true, false)));
					 
				}else {
					 iconeBotao = new FileInputStream("src\\view\\img\\book - branco.png");
					 b.setGraphic(new ImageView(new Image(iconeBotao, 25, 25, true, false)));
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}




	public void currentScreen(Button button) {
		clearCurrentScreen();


		button.setStyle("-fx-cursor: hand; -fx-background-color: #53BDBE; -fx-font-size: 1.8em ; -fx-font-weight: bolder;");
		button.setTextFill(Color.web("#1D5959"));
		
		changeButtonIcon(button, true);


		button.setPrefWidth(200);
	}

	private void clearCurrentScreen() {

		
		Button[] buttons = {btAtividade, btDisciplina};

		for (Button button : buttons) {

			button.setBackground(azulEscuro);

			button.setStyle("-fx-cursor: hand; -fx-font-size: 1.6em; -fx-font-weight: bolder;");
			button.setTextFill(Color.web("#F4F4F4"));
			changeButtonIcon(button, false);

			
		}
	}

}
