package view.professor;

import controller.aluno.DetalhesDisciplinaController;
import controller.professor.DetalhesTurmaController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import model.Aluno;
import model.AlunoEntregaTotalizado;
import model.AtividadeEntrega;
import model.Disciplina;
import model.Turma;
import view.Util;

public class DetalhesTurmaProfessor {

	private BorderPane borderPrincipal;

	private GridPane gridAtividade;

	private GridPane gridTitulo;

	private Label lblAtividade;

	private BorderPane borderInterno;

	private Label lblDisciplina;

	private Label lblSemestreTurmaCurso;

	private GridPane gridCentral;

	private GridPane gridFuncaoDeAgregacao;

	private Turma turma;

	private Disciplina disciplina;

	private DetalhesTurmaController controller;

	public DetalhesTurmaProfessor(BorderPane pane, Disciplina disciplina, Turma turma) {

		this.borderPrincipal = pane;
		this.disciplina = disciplina;
		this.turma = turma;

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
		initGridTitulo();
		initCentroCard();
		initGridCentral();
		controller = new DetalhesTurmaController(this);

	}

	private void initGridTitulo() {

		gridTitulo = new GridPane();

		borderInterno.setTop(gridTitulo);
		borderInterno.setAlignment(gridTitulo, Pos.CENTER);
	}

	private void initGridCentral() {
		gridCentral = new GridPane();

		gridCentral.setAlignment(Pos.CENTER_LEFT);
		gridCentral.setPadding(new Insets(20, 20, 20, 20));

		borderInterno.setCenter(gridCentral);

	}

	private void initBorderInterno() {

		borderInterno = new BorderPane();
		borderInterno.setPrefSize(2000, 2000);

		borderInterno.setPadding(new Insets(15));

		borderInterno.setStyle("-fx-background-color:" + disciplina.getCor()
				+ "; -fx-background-radius: 20px; -fx-border-radius: 20px;");

		gridAtividade.add(borderInterno, 0, 1);
		gridAtividade.setAlignment(Pos.CENTER);
	}

	private void initGridAtividade() {

		gridAtividade = new GridPane();

		gridAtividade.setPrefSize(2000, 2000);

		gridAtividade.setPadding(new Insets(0, 0, 5, 20));

		borderPrincipal.setCenter(gridAtividade);
		borderPrincipal.setMargin(gridAtividade, new Insets(-30, 15, 0, 15));

	}

	private void initLabelAtividade() {

		lblAtividade = new Label("Detalhes Turma");

		lblAtividade.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + 30 + " ;");

		gridAtividade.add(lblAtividade, 0, 0);

		gridAtividade.setMargin(lblAtividade, new Insets(0, 0, 20, 0));

	}

	private void initCentroCard() {

		lblDisciplina = new Label(disciplina.getNome());
		lblDisciplina.setPadding(new Insets(0, 10, 0, 10));
		lblDisciplina.setTextFill(Color.web("#000000", 0.5));
		lblDisciplina.setAlignment(Pos.CENTER);

		Util.setFontePadrao(new Label[] { lblDisciplina }, 25, FontWeight.BOLD);

		lblDisciplina.setStyle(lblDisciplina.getStyle()
				+ "-fx-background-color: rgba(0, 0, 0, 0.2); -fx-background-radius: 10px; -fx-border-radius: 10px;");

		lblSemestreTurmaCurso = new Label(turma.getSemestre() + "° - " + turma.getCurso() + " " + turma.getPeriodo());
		lblSemestreTurmaCurso.setPadding(new Insets(0, 10, 0, 10));
		lblSemestreTurmaCurso.setTextFill(Color.web("#000000", 0.5));
		lblSemestreTurmaCurso.setAlignment(Pos.CENTER);

		Util.setFontePadrao(new Label[] { lblSemestreTurmaCurso }, 25, FontWeight.BOLD);

//		lblSemestreTurmaCurso.setStyle(lblSemestreTurmaCurso.getStyle()
//				+ "-fx-background-color: rgba(0, 0, 0, 0.2); -fx-background-radius: 10px; -fx-border-radius: 10px;");

		gridTitulo.add(lblDisciplina, 0, 0);
		gridTitulo.add(lblSemestreTurmaCurso, 0, 1);
		gridTitulo.setMargin(lblSemestreTurmaCurso, new Insets(10, 0, 0, 0));
		gridTitulo.setHalignment(lblAtividade, HPos.CENTER);
		gridTitulo.setHalignment(lblSemestreTurmaCurso, HPos.CENTER);
		gridTitulo.setAlignment(Pos.CENTER);

	}

	public void setTable(TableView<AlunoEntregaTotalizado> table) {

		borderInterno.setCenter(table);
	}

	public Turma getTurma() {
		return turma;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public BorderPane getBorderPrincipal() {
		return borderPrincipal;
	}

	public BorderPane getBorderInterno() {
		return borderInterno;
	}

	public void initAVG(Label lblAVG, TextField tfAVG) {
		gridFuncaoDeAgregacao = new GridPane();

		gridFuncaoDeAgregacao.add(lblAVG, 2, 0);
		gridFuncaoDeAgregacao.add(tfAVG, 3, 0);

		borderInterno.setBottom(gridFuncaoDeAgregacao);

		borderInterno.setMargin(gridFuncaoDeAgregacao, new Insets(15, 85, 15, 0));
		gridFuncaoDeAgregacao.setAlignment(Pos.CENTER_RIGHT);

		aplicarEstilos(tfAVG, lblAVG);
	}

	public void initCount(Label lblSoma, TextField tfSoma) {

		gridFuncaoDeAgregacao.add(lblSoma, 0, 0);
		gridFuncaoDeAgregacao.add(tfSoma, 1, 0);

		aplicarEstilos(tfSoma, lblSoma);

	}

	public void initMax(Label lblMax, TextField tfMax) {

		gridFuncaoDeAgregacao.add(lblMax, 2, 1);
		gridFuncaoDeAgregacao.add(tfMax, 3, 1);

		aplicarEstilos(tfMax, lblMax);

	}

	public void initMin(Label lblMin, TextField tfMin) {

		gridFuncaoDeAgregacao.add(lblMin, 0, 1);
		gridFuncaoDeAgregacao.add(tfMin, 1, 1);

		aplicarEstilos(tfMin, lblMin);

	}

	private void aplicarEstilos(TextField tf, Label lbl) {

		Util.setFontePadrao(new Label[] { lbl }, 20, FontWeight.BOLD);

		Util.setFontePadrao(new TextField[] { tf }, 20, FontWeight.SEMI_BOLD);

		tf.setPrefWidth(70);
		tf.setPrefHeight(30);

		tf.setAlignment(Pos.CENTER);
		tf.setEditable(false);

		gridFuncaoDeAgregacao.setMargin(lbl, new Insets(10, 0, 0, 10));
		gridFuncaoDeAgregacao.setMargin(tf, new Insets(10, 5, 0, 0));

	}
}
