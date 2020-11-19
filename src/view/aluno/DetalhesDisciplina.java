package view.aluno;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import view.Util;

public class DetalhesDisciplina {
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

	private TableView<String> table;

	private Label lblArquivos;

	private Button btRemover;

	private Button btAdicionar;

	private Button btEntregar;

	private HBox hboxButtons;

	private GridPane gridCentral;

	private GridPane gridData;

	private GridPane gridSuperior;

	private GridPane gridTituloLegenda;
	
	

	public DetalhesDisciplina(BorderPane pane) {

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
		initCentroCard("Trabalho Eng 3", "Querem nos matar");
		initPrazoDeEntrega(new Date());
		initDisciplina("Programação Orientada a Disgraça");
		verificarGroup(true);

		initGridCentral();

	}
	
	private void initGridCentral() {
		gridCentral = new GridPane();

		gridCentral.setAlignment(Pos.CENTER_LEFT);
		gridCentral.setPadding(new Insets(20, 20, 20, 20));

		borderInterno.setCenter(gridCentral);

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

		borderInterno.setStyle("-fx-background-color: #FF9DBA; -fx-background-radius: 20px; -fx-border-radius: 20px;");

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

		lblAtividade = new Label("Detalhes Disciplina");

		lblAtividade.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + 30 + " ;");

		gridAtividade.add(lblAtividade, 0, 0);

		gridAtividade.setMargin(lblAtividade, new Insets(0, 0, 20, 0));

	}

	private void initPrazoDeEntrega(Date prazo) {

		lblDataDeEntrega = new Label("Até " + prazo.getDay() + "/" + prazo.getMonth());
		lblDataDeEntrega.setTextFill(Color.web("#FFFFFF"));
		lblDataDeEntrega.setTextAlignment(TextAlignment.RIGHT);

		lblNumDeDias = new Label("Faltam " + 2 + " dias");
		lblNumDeDias.setTextFill(Color.web("#FFFFFF"));
		lblNumDeDias.setTextAlignment(TextAlignment.RIGHT);

//		lblDataDeEntrega.setAlignment(Pos.CENTER);
//		lblNumDeDias.setAlignment(Pos.CENTER);

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
}
