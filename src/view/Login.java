package view;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.LoginController;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Login extends Application {

	private Scene scn;
	private BorderPane borderPrincipal;
	private GridPane gridForm;
	private TextField txtEmail;
	private PasswordField txtSenha;
	private Label lblErro;
	private Button btLogar;
	private FadeTransition ft;
	private LoginController loginController = new LoginController(this);

	@Override
	public void start(Stage stage) throws Exception {
		borderPrincipal = new BorderPane();
		borderPrincipal.setId("borderPrincipal");

		scn = new Scene(borderPrincipal, 900, 600);
		
		setBackground();

		initLogin();

		stage.setScene(scn);
		stage.setTitle("actio");
		stage.show();

	}

	private void setBackground() throws FileNotFoundException{
		
		FileInputStream input = new FileInputStream("src\\view\\img\\dragon.jpg");

		Image image = new Image(input);

		BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(100, 100, true, true, true, true));

		Background background = new Background(backgroundimage);

		borderPrincipal.setBackground(background);
		
	}

	private void initLogin() {
		
		initGridForm();
		
		initBtLogar();

		initTextFields();
		
		initLblErro();

		initEvents();
		
		setNodesInGridForm();

		borderPrincipal.setCenter(gridForm);

	}

	private void setNodesInGridForm() {
		gridForm.add(txtEmail, 0, 0);
		gridForm.add(txtSenha, 0, 1);
		gridForm.add(btLogar, 0, 2);
		gridForm.add(lblErro, 0, 3);

		gridForm.setHalignment(lblErro, HPos.CENTER);
		gridForm.setHalignment(btLogar, HPos.CENTER);
		gridForm.setHalignment(txtEmail, HPos.CENTER);
		gridForm.setHalignment(txtSenha, HPos.CENTER);
		
	}

	private void initLblErro() {
		lblErro = new Label();

		initTextFields(new TextField[] { txtEmail, txtSenha });
		
	}

	private void initBtLogar() {
		btLogar = new Button("Entrar");
		btLogar.setAlignment(Pos.CENTER);
		
		Util.initButtons(new Button[] { btLogar }); 
		
		btLogar.setPrefWidth(120);
		btLogar.setPrefHeight(45);
		btLogar.setStyle(btLogar.getStyle());
		
		ft = new FadeTransition(Duration.millis(3000), btLogar);
		ft.setFromValue(1.0);
		ft.setToValue(0.3);
		ft.setCycleCount(4);
		ft.setAutoReverse(true);
		
	}

	private void initGridForm() {
		gridForm = new GridPane();
		gridForm.setHgap(20);
		gridForm.setVgap(30);

		gridForm.setAlignment(Pos.CENTER);
		
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

		btLogar.addEventHandler(ActionEvent.ACTION, (x) -> {
			loginController.logar(x);
			ft.play();
		});
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
