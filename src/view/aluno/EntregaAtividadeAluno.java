package view.aluno;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
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

	private GridPane gridData;

	public EntregaAtividadeAluno(BorderPane pane) {

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

	}

	private void initBorderInterno() {

		borderInterno = new BorderPane();

		borderInterno.setStyle("-fx-background-color: #FF9DBA; -fx-background-radius: 20px; -fx-border-radius: 20px;");

		gridAtividade.add(borderInterno, 0, 1);

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
		lblTitulo.setPadding(new Insets(10, 0, 0, 10));
		lblTitulo.setTextFill(Color.web("#000000", 0.5));

		lblTurma = new Label(legenda);
		lblTurma.setPadding(new Insets(10, 0, 0, 0));
		lblTurma.setTextFill(Color.web("#000000", 0.5));

		Util.setFontePadrao(new Label[] { lblTitulo }, 16, FontWeight.BOLD);
		Util.setFontePadrao(new Label[] { lblTurma }, 16, FontWeight.BOLD);

		hboxPrincipal.getChildren().addAll(lblTitulo, lblTurma);
	}

}
