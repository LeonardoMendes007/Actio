package view;


import java.io.FileInputStream;

import controller.LoginController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Login extends Application {

	private Scene scn;
	private BorderPane border;
	private GridPane grid;
	private TextField txtEmail;
	private PasswordField txtSenha;
	private Label lblErro;
	private Button btLogar;
	private LoginController loginController = new LoginController(this);

	@Override
	public void start(Stage stage) throws Exception {
		border = new BorderPane();
		border.setId("border");

		scn = new Scene(border, 900, 600);
		
		FileInputStream input = new FileInputStream("src\\view\\img\\fundo.png");

		// create a image
		Image image = new Image(input);

		// create a background image
		BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(100, 100, true, true, true, true));

		// create Background
		Background background = new Background(backgroundimage);

		// set background
		border.setBackground(background);

		initLogin();

		stage.setScene(scn);
		stage.setTitle("actio");
		stage.show();

	}

	private void initLogin() {

		grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(30);

		grid.setAlignment(Pos.CENTER);

		btLogar = new Button("Entrar");
		btLogar.setAlignment(Pos.CENTER);

		initTextFields();

		lblErro = new Label();

		initTextFields(new TextField[] { txtEmail, txtSenha });

		Util.initButtons(new Button[] { btLogar });

		btLogar.setPrefWidth(120);
		btLogar.setPrefHeight(45);
		btLogar.setStyle(btLogar.getStyle());

		initEvents();

		grid.add(txtEmail, 0, 0);
		grid.add(txtSenha, 0, 1);
		grid.add(btLogar, 0, 2);
		grid.add(lblErro, 0, 3);

		grid.setHalignment(lblErro, HPos.CENTER);
		grid.setHalignment(btLogar, HPos.CENTER);
		grid.setHalignment(txtEmail, HPos.CENTER);
		grid.setHalignment(txtSenha, HPos.CENTER);

		border.setCenter(grid);

	}

	private void initTextFields() {
		txtEmail = new TextField();
		txtSenha = new PasswordField();
		txtEmail.setAlignment(Pos.CENTER);
		txtEmail.setPromptText("E-MAIL");	
		txtSenha.setAlignment(Pos.CENTER);
		txtSenha.setPromptText("Senha");	

	}

	private void initEvents() {

		btLogar.addEventHandler(ActionEvent.ACTION, (x) -> loginController.logar(x));
		txtEmail.textProperty().addListener((x) -> isNotBlank(txtEmail));
		txtSenha.textProperty().addListener((x) -> isNotBlank(txtSenha));
	}

	private void initLabels(Label[] labels) {

		for (Label label : labels) {
			label.setStyle("-fx-font-size: 2em ;");
		}
	}

	private void initTextFields(TextField[] textFields) {

		for (TextField textField : textFields) {
			textField.setPrefWidth(450);
			textField.setPrefHeight(55);
			textField.setStyle("-fx-font-size: 1.4em ; -fx-border-color: black; -fx-border-style: solid; -fx-border-width: 2;");
            	
		}

	}

	public static void main(String[] args) {
		Application.launch(Login.class, args);
	}

	public void senhaIsBlank() {
		txtSenha.setStyle(txtSenha.getStyle().replaceAll("-fx-border-color: black;", "-fx-border-color: red;") );

	}

	public void emailIsBlank() {
		txtEmail.setStyle(txtEmail.getStyle().replaceAll("-fx-border-color: black;", "-fx-border-color: red;") );
	}

	private void isNotBlank(TextField txt) {

		txt.setStyle(txt.getStyle().replaceAll("-fx-border-color: red;", "-fx-border-color: black;") );

	}

	public String getEmail() {
		return txtEmail.getText();
	}

	public String getSenha() {
		return txtSenha.getText();
	}

	public void setErro(String erro) {
		lblErro.setText(erro);
		lblErro.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em; -fx-font-weight: bolder; ");
	}

	public Scene getScene() {
		return scn;
	}

}
