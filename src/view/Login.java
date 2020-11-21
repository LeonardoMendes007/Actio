
package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.LoginController;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	private Timeline timeline;
	
	@Override
	public void start(Stage stage) throws Exception {
		borderPrincipal = new BorderPane();
		borderPrincipal.setId("borderPrincipal");

		scn = new Scene(borderPrincipal, 1000, 600);

		borderPrincipal.getStylesheets().add(getClass().getResource("css//login.css").toExternalForm());

		setBackground();

		initLogin();

		stage = configStage(stage);
		stage.setScene(scn);
		
		stage.show();

	}

	private Stage configStage(Stage stage) {
		
		stage.setTitle("Actio | Seu sistema de atividades");
		
		stage.setMinWidth(800);
		stage.setMinHeight(600);
//		stage.initStyle(StageStyle.UNDECORATED);
		
		try {
			stage.getIcons().add(new Image(new FileInputStream(new File("src\\view\\img\\icon2.png"))));
		}catch (FileNotFoundException e) {
			new FileNotFoundException("Erro ao carregar o icone na barra superior");
		}
		
		
		return stage;
		
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

		initMouseEvents();
		
		setNodesNoGridForm();
		
		//setAnimation(stackPane);
		
		setLogoCantoEsqueda();
		
		borderPrincipal.setCenter(stackPane);
		

	}
	
	
	private void setAnimation(Node p) {
	
		FadeTransition ft = new FadeTransition(Duration.millis(2000), p);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.setDelay(Duration.millis(3500));
		ft.play();
	}

	private void setNodesNoGridForm() {
		
		//Definindo fonte padrão (do google fonts)
		gridForm.getStylesheets().add("https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap");

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

		
		lblErro = new Label();
		lblLogin = new Label("Login");
	
		lblEmail = new Label("Email");
		lblSenha = new Label("Senha");
		
		lblLogin.getStyleClass().add("lbl-login");
		lblEmail.getStyleClass().add("lbl-email");
		lblSenha.getStyleClass().add("lbl-senha");
		
		
	

		setFontePadrao(new Label[] {lblEmail,lblSenha}, 20, FontWeight.NORMAL);
		setFontePadrao(new Label[] {lblLogin}, 40, FontWeight.BOLD);
		setFontePadrao(new Label[] {lblErro}, 15, FontWeight.LIGHT);
		setFontePadrao(new Button[] {btLogar}, 25, FontWeight.BOLD);
	
		initTextFields(new TextField[] { txtEmail, txtSenha });

	}

	private void setFontePadrao(Node[] texts, int tamanho, FontWeight peso) {

		
		for (Node text : texts) {
			text.setStyle("-fx-font-family: Poppins; "
					+ "-fx-font-size: " + tamanho + " ;"
					+"-fx-font-weight: " +peso.getWeight() +";");
	
		}
		
	}

	private void setLogoCantoEsqueda() {


        try {
            Image imagem = new Image(new FileInputStream("src\\view\\img\\Logo_4.png"));

            ImageView ivLogo = new ImageView(imagem);
            ivLogo.setFitWidth(100.0);
            ivLogo.setFitHeight(35.0);

            borderPrincipal.setTop(ivLogo);
            borderPrincipal.setAlignment(ivLogo, Pos.TOP_LEFT);
            borderPrincipal.setMargin(ivLogo, new Insets(10, 0, 0, 10));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }

	private void initBtLogar() {
		btLogar = new Button("Entrar");
		btLogar.setAlignment(Pos.CENTER);

		Util.initButtons(new Button[] { btLogar });
		btLogar.getStyleClass().add("bt-logar");
		btLogar.setTextFill(Color.WHITE);
		btLogar.setPrefWidth(140);
		btLogar.setPrefHeight(45);

	}

	private void initGridForm() {
		stackPane = new StackPane();

		gridForm = new GridPane();
		gridForm.setHgap(20);
		gridForm.setVgap(18);
		gridForm.setId("grid-form");

		gridForm.setAlignment(Pos.CENTER);

		stackPane.getChildren().add(gridForm);
		stackPane.setOpacity(1);
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
	
	//Eventos do mouse (hover)
	private void initMouseEvents() {
		
		btLogar.setOnMousePressed((x) -> {
			btLogar.setScaleX(btLogar.getScaleX() + 0.05);
			btLogar.setScaleY(btLogar.getScaleY() + 0.05);

		});
		
		btLogar.setOnMouseReleased((x) -> {
			btLogar.setScaleX(btLogar.getScaleX() - 0.05);
			btLogar.setScaleY(btLogar.getScaleY() - 0.05);
		});
	}

	private void initLabels(Label[] labels) {

		for (Label label : labels) {
			label.setStyle("-fx-font-size: 2em ;");
		}
	}

	private void initTextFields(TextField[] textFields) {

		for (TextField textField : textFields) {
			textField.setPrefWidth(450);
			textField.setPrefHeight(40);
			
			textField.setBackground(new Background(new BackgroundFill(Color.web("#D7D7D7", 1), CornerRadii.EMPTY, Insets.EMPTY)));

		}

		setFontePadrao(textFields, 20, FontWeight.NORMAL);
	}

	public static void main(String[] args) {
		Application.launch(Login.class, args);
	}

	public void senhaIsBlank() {
		txtSenha.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));

	}

	public void emailIsBlank() {
		txtEmail.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));
		
	}

	private void isNotBlank(TextField txt) {

		txt.setBorder(null);

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
