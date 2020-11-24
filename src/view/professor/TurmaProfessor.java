package view.professor;

import java.util.ArrayList;

import controller.professor.TurmaProfessorController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.FontWeight;
import model.Disciplina;
import model.Professor;
import view.Util;
import view.card.turma.CardTurma;

public class TurmaProfessor {

	private BorderPane borderPrincipal;

	private GridPane gridTelaTurma;

	private Label lblTurma;

	private TilePane tileDisciplina;

	private ScrollPane scrollDisciplina;

	private GridPane gridDisciplina;

	private ScrollPane scrollTurma;

	private Label lblNomeDisciplina;

	private HBox hBoxTurma;

	private Professor professor;

 	
	private TurmaProfessorController controller = new TurmaProfessorController(this);

	public TurmaProfessor(BorderPane border, Professor p) {
		this.borderPrincipal = border;

		setProfessor(p);
		initTela();
	}

	public void initTela() {

		initGridDisciplina();

		initLabelDisciplina();

		initScrollDisciplina();

		initEventResponsivel();

		initTileDisciplina();

		controller.addCards();

	}

	private void initEventResponsivel() {

		// gridTelaDisciplina.heightProperty().addListener((x) ->
		// gridDisciplina.setPrefHeight());
		gridTelaTurma.widthProperty().addListener((x) -> gridDisciplina.setPrefWidth(gridTelaTurma.getWidth() * 0.9));

	}

	private void initTileDisciplina() {
		tileDisciplina = new TilePane(10, 10);
		tileDisciplina.setOrientation(Orientation.VERTICAL);
		tileDisciplina.setPrefColumns(1);
		tileDisciplina.setPrefWidth(borderPrincipal.getWidth() * 0.95);

		borderPrincipal.widthProperty()
				.addListener((x) -> tileDisciplina.setPrefWidth(borderPrincipal.getWidth() * 0.95));

		scrollDisciplina.setContent(tileDisciplina);

	}

	private void initScrollDisciplina() {

		scrollDisciplina = new ScrollPane();
		scrollDisciplina.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollDisciplina.setPadding(new Insets(20, 0, 0, 0));

		scrollDisciplina.setStyle("-fx-background-color:transparent;");
		gridTelaTurma.add(scrollDisciplina, 0, 1);
	}

	private void initLabelDisciplina() {

		lblTurma = new Label("Turma");

		lblTurma.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + 25 + " ;");

		gridTelaTurma.add(lblTurma, 0, 0);
	}

	private void initGridDisciplina() {

		gridTelaTurma = new GridPane();

		gridTelaTurma.setPadding(new Insets(0, 0, 5, 20));

		borderPrincipal.setCenter(gridTelaTurma);
		borderPrincipal.setMargin(gridTelaTurma, new Insets(-30, 15, 0, 20));

	}

	private void initGridNomeDisciplina() {
		gridDisciplina = new GridPane();
		gridDisciplina.setVgap(5);
		gridDisciplina.setHgap(10);
		gridDisciplina.setStyle("-fx-background-radius: 20px; -fx-border-radius: 20px; -fx-background-color: #D7D7D7;");
		gridDisciplina.setPadding(new Insets(10, 0, 5, 20));

		gridDisciplina.setPrefWidth(borderPrincipal.getWidth()*0.95);
//		
		borderPrincipal.widthProperty().addListener((x) -> gridDisciplina.setPrefWidth(borderPrincipal.getWidth()*0.95));
//		

		tileDisciplina.getChildren().add(gridDisciplina);

	}

	private void initLabelNomeDisciplina(String disciplina) {

		lblNomeDisciplina = new Label(disciplina);
		Util.setFontePadrao(new Label[] { lblNomeDisciplina }, 20, FontWeight.BOLD);

		gridDisciplina.add(lblNomeDisciplina, 0, 0);
	}

	private void initScrollTurma() {
		scrollTurma = new ScrollPane();
		scrollTurma.setStyle("-fx-background-color: #D7D7D7;");
		scrollTurma.setPrefHeight(165);

		scrollTurma.setVbarPolicy(ScrollBarPolicy.NEVER);
//		gridDisciplina.setGridLinesVisible(true);

		
		gridDisciplina.add(scrollTurma, 0, 1);
	}

	private void initHBoxTurma() {

		hBoxTurma = new HBox(10);
		hBoxTurma.setStyle("-fx-background-color: #D7D7D7;");
		hBoxTurma.setAlignment(Pos.CENTER);
		hBoxTurma.setPadding(new Insets(5, 0, 15, 0));

		scrollTurma.setContent(hBoxTurma);

	}

	public HBox initNovaDisciplina(CardTurma card) {
		initGridNomeDisciplina();
		initLabelNomeDisciplina(card.getDisciplina().getNome());
		initScrollTurma();
		initHBoxTurma();
		
		return hBoxTurma;
	}

	public void addCard(HBox teste, CardTurma card) {
		
		teste.getChildren().add(card.getCard());	
	}

	public BorderPane getBorderPrincipal() {
		return borderPrincipal;
	}


	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
}
