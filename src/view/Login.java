package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.LoginController;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

	private Scene scn;
	private BorderPane borderPrincipal;
	private StackPane stackPane;
	private GridPane gridForm;
	private TextField txtEmail;
	private PasswordField txtSenha;
	private Label lblErro;
	private Button btLogar;
	private Label lblLogin;
	private Label lblEmail;
	private Label lblSenha;
	private FadeTransition ft;
	private LoginController loginController = new LoginController(this);

	@Override
	public void start(Stage stage) throws Exception {
		borderPrincipal = new BorderPane();
		borderPrincipal.setId("borderPrincipal");

		scn = new Scene(borderPrincipal, 900, 600);

		borderPrincipal.getStylesheets().add(getClass().getResource("css//login.css").toExternalForm());

		setBackground();

		initLogin();

		stage.setScene(scn);
		stage.setTitle("actio");
		stage.show();

	}

	private void setBackground() throws FileNotFoundException {

		String img;
		
		switch ((int) (Math.random() * 3)) {
		case 1:

			img = "1.png";
			
			break;
		case 2:

			img = "2.png";
			break;
		case 3:

			img = "3.png";
			break;
		case 4:

			img = "deafult.png";
			break;
		default:

			img = "deafult.png";
			break;
		}
		FileInputStream input = new FileInputStream("src\\view\\img\\" + img);

		Image image = new Image(input);

		BackgroundImage backgroundimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(100, 100, true, true, true, true));

		Background background = new Background(backgroundimage);

		borderPrincipal.setBackground(background);

	}

	private void initLogin() {

		initGridForm();

		initBtLogar();

		initTextFields();

		initLblErro();

		initEvents();

		setNodesNoGridForm();

		borderPrincipal.setCenter(stackPane);

	}

	private void setNodesNoGridForm() {
		
		gridForm.add(lblLogin, 0, 0);
		gridForm.add(lblEmail, 0, 1);
		gridForm.add(txtEmail, 0, 2);
		gridForm.add(lblSenha, 0, 3);
		gridForm.add(txtSenha, 0, 4);
		gridForm.add(btLogar, 0, 6);
		gridForm.add(lblErro, 0, 5);

		gridForm.setHalignment(lblErro, HPos.CENTER);
		gridForm.setHalignment(btLogar, HPos.CENTER);
		gridForm.setHalignment(txtEmail, HPos.CENTER);
		gridForm.setHalignment(txtSenha, HPos.CENTER);

		BackgroundFill fill = new BackgroundFill(Color.web("#EAEAEA", 1), new CornerRadii(20), new Insets(0, 0, 0, 0));

		gridForm.setBackground(new Background(fill));

	}

	private void initLblErro() {
//		Font font = new Font(arg0, arg1)
		
		lblErro = new Label();
		lblLogin = new Label("Login");
		lblEmail = new Label("Email");
		lblSenha = new Label("Senha");
		
		lblLogin.getStyleClass().add("lbl-login");
		lblEmail.getStyleClass().add("lbl-email");
		lblSenha.getStyleClass().add("lbl-senha");
		
		
		

		setFontePadrao(new Label[] {lblEmail,lblSenha}, new Font("Poppins", 20));
		setFontePadrao(new Label[] {lblLogin}, new Font("Poppins", 40));
		setFontePadrao(new Label[] {lblErro}, new Font("Poppins", 15));
		
		initTextFields(new TextField[] { txtEmail, txtSenha });

	}

	private void setFontePadrao(Label[] texts, Font font) {
		
		for (Label text : texts) {
			text.setFont(font);
		}
	}

	private void initBtLogar() {
		btLogar = new Button("Entrar");
		btLogar.setAlignment(Pos.CENTER);

		Util.initButtons(new Button[] { btLogar });
		btLogar.getStyleClass().add("bt-logar");
		btLogar.setTextFill(Color.WHITE);
		btLogar.setStyle("-fx-font-size: 20px; -fx-font-family: Poppins;");

		btLogar.setPrefWidth(120);
		btLogar.setPrefHeight(45);

	}

	private void initGridForm() {
		stackPane = new StackPane();

		gridForm = new GridPane();
		gridForm.setHgap(20);
		gridForm.setVgap(20);
		gridForm.setId("grid-form");

		gridForm.setAlignment(Pos.CENTER);

		stackPane.getChildren().add(gridForm);
		stackPane.setMaxWidth(400);
		stackPane.setMaxHeight(450);
		stackPane.setAlignment(Pos.CENTER);
		gridForm.setStyle("-fx-padding: 10 20 10 20;");
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
			loginController.logar();
		});

		txtEmail.textProperty().addListener((x) -> isNotBlank(txtEmail));
		txtSenha.textProperty().addListener((x) -> isNotBlank(txtSenha));
		txtEmail.setOnKeyPressed((x) -> loginController.verifiqueKeyPressed(x));
		txtSenha.setOnKeyPressed((x) -> loginController.verifiqueKeyPressed(x));
	}

	private void initLabels(Label[] labels) {

		for (Label label : labels) {
			label.setStyle("-fx-font-size: 2em ;");
		}
	}

	private void initTextFields(TextField[] textFields) {

		for (TextField textField : textFields) {
			textField.setPrefWidth(450);
			textField.setPrefHeight(50);
			textField.getStyleClass().add("text-fields");
			textField.setStyle("-fx-border-color: #C2C2C2");
		}

	}

	public static void main(String[] args) {
		Application.launch(Login.class, args);
	}

	public void senhaIsBlank() {
		txtSenha.setStyle(txtSenha.getStyle().replaceAll("-fx-border-color: #C2C2C2", "-fx-border-color: red;"));

	}

	public void emailIsBlank() {
		txtEmail.setStyle(txtEmail.getStyle().replaceAll("-fx-border-color: #C2C2C2", "-fx-border-color: red;"));
	}

	private void isNotBlank(TextField txt) {

		txt.setStyle(txt.getStyle().replaceAll("-fx-border-color: red;", "-fx-border-color: #C2C2C2;"));

	}

	public String getEmail() {
		return txtEmail.getText();
	}

	public String getSenha() {
		return txtSenha.getText();
	}

	public void setErro(String erro) {
		lblErro.setText(erro);
		lblErro.setStyle("-fx-text-fill: #850B0B; -fx-font-size: 1.5em; -fx-font-weight: bolder; ");
	}

	public Scene getScene() {
		return scn;
	}

}
