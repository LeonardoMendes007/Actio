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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Menu {
	
	private Scene scn;
	private BorderPane borderPrincipal;
	private BorderPane borderCentral;
	private BorderPane borderLeft;
	private BorderPane borderConteudo;
	private GridPane grid;
	private GridPane gridUsuario;
	private Label lblActio;
	private Button btAtividade;
	private Button btDisciplina;
	private Button btTurma;
	private ImageView ivUser;
	private Label lblNome;
	
	private MenuController menuController = new MenuController(this);
	
	public Menu(Scene scn) {
		this.scn = scn;
		initNewScene();
		initMenu();
		
		try {
			initBarraSuperior();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initConteudo();
		
		menuController.btHomeAction();
		
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


	private void initBarraSuperior() throws FileNotFoundException {
	    
		borderCentral = new BorderPane();
		borderCentral.setStyle("-fx-background-color: #EAEAEA; -fx-background-radius: 35px 0px 0px 35px; -fx-border-radius: 15px;");
        
		
		Image imagem = new Image(new FileInputStream("C:\\Users\\mende\\Downloads\\l.png"));
		
		ivUser = new ImageView(imagem);
		ivUser.setFitWidth(50.0);
		ivUser.setFitHeight(50.0);
		ivUser.setStyle("-fx-background-radius: 20px; -fx-border-radius: 20px;");
		lblNome = new Label("Fulano");
		lblNome.setStyle("-fx-cursor: hand; -fx-font-size: 1.4em ;");
		
		initGridUsuario();
		
		borderPrincipal.setCenter(borderCentral);
		
	}


	private void initGridUsuario() {
		gridUsuario = new GridPane();
		gridUsuario.setAlignment(Pos.CENTER_RIGHT);
		gridUsuario.setPadding(new Insets(5,15,10,10));
		gridUsuario.setHgap(15);
		
		gridUsuario.add(lblNome, 0, 0);
		gridUsuario.add(ivUser, 1, 0);
		
		borderCentral.setTop(gridUsuario);
		
	}


	private void initMenu() {
		
		
		initMenuButtons();
		
		initGridLateralCentral();
	
		initBorderPaneLeft();
		
		initEvents();
		
		borderPrincipal.setLeft(borderLeft); 
		
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
		
		btDisciplina.setOnMouseClicked(x -> {
			menuController.btDisciplinaAction(); 
			currentScreen(btDisciplina);
		});
//		btLupaMais.setOnMouseClicked(x -> menuController.btAumentarFonte());
//		btLupaMenos.setOnMouseClicked(x -> menuController.btDiminuirFonte());
	}


	private void initBorderPaneLeft() {
		borderLeft = new BorderPane();
		borderLeft.setStyle("-fx-background-color: #1D5959;");
		
		
		initLabelActio();
		
		borderLeft.setCenter(grid);
		borderLeft.setTop(lblActio);
	}


	private void initLabelActio() {
		lblActio = new Label("Actio");
		lblActio.setAlignment(Pos.TOP_CENTER);
		lblActio.setStyle("-fx-cursor: hand; -fx-font-size: 2em ; -fx-font-weight: bolder;");
		lblActio.setPrefWidth(200);
		lblActio.setPrefHeight(50);
		lblActio.setAlignment(Pos.BOTTOM_CENTER);
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
	    btAtividade = new Button("Atividade");
		btDisciplina = new Button("Disciplina");
		
		setConfigButtons(new Button[] {btAtividade,btDisciplina});
		
		
		Util.initButtons(new Button[] {btAtividade,btDisciplina});
		
		clearCurrentScreen();
		
	}

	private void setConfigButtons(Button[] buttons) {
		
		for (Button button : buttons) {
			button.setStyle("-fx-background-color: #1D5959;");
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


	public void currentScreen(Button button) {
		
		clearCurrentScreen();
		
		button.setStyle("-fx-cursor: hand; -fx-font-size: 1.8em ; -fx-background-color: #53BDBE; -fx-font-weight: bolder;");
		button.setPrefWidth(200);
	}


	private void clearCurrentScreen() {
		
		Button[] buttons = {btAtividade, btDisciplina};
		
		for (Button button : buttons) {
			button.setStyle("-fx-cursor: hand; -fx-font-size: 1.6em; -fx-font-weight: bolder; -fx-background-color: #1D5959;");
		}
	}
	
	

}
