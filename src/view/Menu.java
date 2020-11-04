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
	private Button btLupaMais;
	private Button btLupaMenos;
	private GridPane gridMaisMenos;
	private ImageView ivUser;
	private Label lblNome;
	
	private MenuController controller = new MenuController(this);
	
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
		
		controller.btHomeAction();
		
	}


	private void initConteudo() {
       
		borderConteudo = new BorderPane();
		
		borderCentral.setCenter(borderConteudo);
		
	}


	private void initNewScene() {
		borderPrincipal = new BorderPane();
		scn.setRoot(borderPrincipal);		
	}


	private void initBarraSuperior() throws FileNotFoundException {
	    
		borderCentral = new BorderPane();
        
		
		Image imagem = new Image(new FileInputStream("C:\\Users\\mende\\Downloads\\l.png"));
		
		ivUser = new ImageView(imagem);
		ivUser.setFitWidth(50.0);
		ivUser.setFitHeight(50.0);
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
		
		initGridMaisMenos();
		
		initGridLateralCentral();
	
		initBorderPaneLeft();
		
		initEvents();
		
		borderPrincipal.setLeft(borderLeft); 
		
	}


	private void initEvents() {

		lblActio.setOnMouseClicked(x -> {
			controller.btHomeAction();
			clearCurrentScreen();
		});
		
		btAtividade.setOnMouseClicked(x -> {
			controller.btAtividadeAction();
			currentScreen(btAtividade);
		});
		
		btDisciplina.setOnMouseClicked(x -> {
			controller.btDisciplinaAction(); 
			currentScreen(btDisciplina);
		});
//		btLupaMais.setOnMouseClicked(x -> controller.btAumentarFonte());
//		btLupaMenos.setOnMouseClicked(x -> controller.btDiminuirFonte());
	}


	private void initBorderPaneLeft() {
		borderLeft = new BorderPane();
		borderLeft.setStyle("-fx-background-color: gray;");
		
		
		initLabelActio();
		
		borderLeft.setCenter(grid);
		borderLeft.setBottom(gridMaisMenos);
		borderLeft.setTop(lblActio);
	}


	private void initLabelActio() {
		lblActio = new Label("Actio");
		lblActio.setAlignment(Pos.TOP_CENTER);
		lblActio.setStyle("-fx-cursor: hand; -fx-font-size: 2em ; -fx-font-weight: bolder;");
		lblActio.setPrefWidth(120);
		lblActio.setPrefHeight(40);
		lblActio.setPadding(new Insets(10, 0, 0, 30));
		
	}


	private void initGridLateralCentral() {
		grid = new GridPane();
		grid.setVgap(20);
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setStyle("-fx-background-color: gray;");
		
		
		grid.add(btAtividade, 0, 3);
		grid.add(btDisciplina, 0, 4);
		
		
	}


	private void initGridMaisMenos() {
		gridMaisMenos = new GridPane();
		gridMaisMenos.setHgap(15);
		
		gridMaisMenos.setAlignment(Pos.CENTER);
		gridMaisMenos.add(btLupaMais, 0, 0);
		gridMaisMenos.add(btLupaMenos, 1, 0);	
		
	}


	private void initMenuButtons() {
	    btAtividade = new Button("Atividade");
		btDisciplina = new Button("Disciplina");
		btLupaMais = new Button("+");
		btLupaMenos = new Button("-");
		
		
		initButtonMaisMenos(new Button[] {btLupaMais,btLupaMenos});
		Util.initButtons(new Button[] {btAtividade,btDisciplina});
		
		clearCurrentScreen();
		
	}


	private void initButtonMaisMenos(Button[] buttons) {
		
		
		for (Button button : buttons) {
			button.setPrefWidth(30);
			button.setPrefHeight(30);
			gridMaisMenos.setMargin(button, new Insets(0, 0, 15, 0));
		}
	}


	public BorderPane getBorderConteudo() {
		return borderConteudo;
	}


	public void setBorderConteudo(BorderPane borderConteudo) {
		this.borderConteudo = borderConteudo;
	}
	
	public void currentScreen(Button button) {
		
		clearCurrentScreen();
		
		button.setStyle("-fx-cursor: hand; -fx-font-size: 1.8em ; -fx-background-color: white; -fx-font-weight: bolder;");
		
	}


	private void clearCurrentScreen() {
		
		Button[] buttons = {btAtividade, btDisciplina};
		
		for (Button button : buttons) {
			button.setStyle("-fx-cursor: hand; -fx-font-size: 1.6em; -fx-font-weight: bolder; -fx-background-color: gray;");
		}
	}
	
	

}
