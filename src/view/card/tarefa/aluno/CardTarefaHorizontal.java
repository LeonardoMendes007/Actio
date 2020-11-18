package view.card.tarefa.aluno;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import org.graalvm.compiler.java.GraphBuilderPhase.Instance;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import view.Util;
import view.aluno.EntregaAtividadeAluno;
import view.card.ICard;

public class CardTarefaHorizontal implements ICard {

	private BorderPane borderPrincipal;
	
	private HBox hboxPrincipal;

	private Label lblDisciplina;

	private Label lblTitulo;

	private Label lblLegenda;

	private Label lblDataDeEntrega;

	private Label lblNumDeDias;

	private ImageView ivGroup;

	private GridPane gridData;

	private Color corDisciplina;

	public CardTarefaHorizontal(BorderPane pane, String titulo, String legenda, String disciplina, String corHexa, Date prazo,
			boolean group) {

		this.borderPrincipal = pane; 
		
		corDisciplina = Color.web(corHexa);

		this.hboxPrincipal = new HBox(15);
		this.hboxPrincipal.setPrefSize(500, 50);
		this.hboxPrincipal.setStyle("-fx-background-color: " + corHexa
				+ "; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-cursor: hand;");
		this.hboxPrincipal.setPadding(new Insets(5));

		verificarGroup(group);

		initCentroCard(titulo, legenda);

		initDisciplina(disciplina);

		initPrazoDeEntrega(prazo);

		initEvent();

		initReponsivelCar();
	}

	private void initReponsivelCar() {

		hboxPrincipal.widthProperty().addListener((x) -> {

			double largura = hboxPrincipal.getWidth();

			lblTitulo.setMinWidth(largura * 0.22);
			lblLegenda.setMinWidth(largura * 0.24);
			lblDisciplina.setMinWidth(largura * 0.26);
			lblDataDeEntrega.setMinWidth((largura - 70) * 0.15);
			lblNumDeDias.setMinWidth((largura - 70) * 0.15);

		});

	}

	private void initPrazoDeEntrega(Date prazo) {

		lblDataDeEntrega = new Label("Até " + prazo.getDay() + "/" + prazo.getMonth());
		lblDataDeEntrega.setTextFill(Color.web("#FFFFFF"));

		lblNumDeDias = new Label("Faltam " + 2 + " dias");
		lblNumDeDias.setTextFill(Color.web("#FFFFFF"));

		lblDataDeEntrega.setAlignment(Pos.CENTER);
		lblNumDeDias.setAlignment(Pos.CENTER);

		Util.setFontePadrao(new Label[] { lblDataDeEntrega }, 12, FontWeight.BOLD);
		Util.setFontePadrao(new Label[] { lblNumDeDias }, 12, FontWeight.NORMAL);

		gridData = new GridPane();

		gridData.add(lblDataDeEntrega, 0, 0);
		gridData.add(lblNumDeDias, 0, 1);
		gridData.setValignment(lblDataDeEntrega, VPos.CENTER);
		gridData.setHalignment(lblDataDeEntrega, HPos.RIGHT);
		gridData.setValignment(lblNumDeDias, VPos.CENTER);
		gridData.setHalignment(lblNumDeDias, HPos.RIGHT);
		gridData.setPadding(new Insets(0, 0, 0, 10));
		hboxPrincipal.getChildren().add(gridData);
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
			ivGroup.setFitWidth(35.0);
			ivGroup.setFitHeight(35.0);
			hboxPrincipal.getChildren().add(ivGroup);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void initDisciplina(String disciplina) {

		lblDisciplina = new Label(disciplina);
		lblDisciplina.setTextFill(Color.web("#000000", 0.5));

		Util.setFontePadrao(new Label[] { lblDisciplina }, 11, FontWeight.NORMAL);

		lblDisciplina.setPadding(new Insets(5));
		lblDisciplina.setAlignment(Pos.CENTER);
		lblDisciplina.setStyle(lblDisciplina.getStyle()
				+ "-fx-background-color: rgba(0, 0, 0, 0.3); -fx-background-radius: 10px; -fx-border-radius: 10px;");

		lblDisciplina.setTextFill(corDisciplina);
		hboxPrincipal.getChildren().add(lblDisciplina);

	}

	private void initCentroCard(String titulo, String legenda) {

		lblTitulo = new Label(titulo);
		lblTitulo.setPadding(new Insets(0, 0, 0, 10));
		lblTitulo.setTextFill(Color.web("#000000", 0.5));

		lblLegenda = new Label(legenda);
		lblLegenda.setTextFill(Color.web("#000000", 0.5));

		Util.setFontePadrao(new Label[] { lblTitulo }, 16, FontWeight.BOLD);
		Util.setFontePadrao(new Label[] { lblLegenda }, 10, FontWeight.LIGHT);

		hboxPrincipal.getChildren().addAll(lblTitulo, lblLegenda);
	}

	private void initEvent() {
		hboxPrincipal.setOnMouseClicked((x) -> {

	        new EntregaAtividadeAluno(borderPrincipal);
			
		});

		Util.hoverFade(hboxPrincipal);
	}

	public HBox getHboxPrincipal() {
		return hboxPrincipal;
	}

	public void setHboxPrincipal(HBox hboxPrincipal) {
		this.hboxPrincipal = hboxPrincipal;
	}

	@Override
	public HBox getCard() {
		return hboxPrincipal;
	}
}
