package view.professor;



import java.util.ArrayList;

import controller.professor.TurmaProfessorController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.text.FontWeight;
import model.Disciplina;
import model.Professor;
import view.Util;
import view.card.turma.CardTurma;

public class ViewTurmaProfessor {

	private BorderPane borderPrincipal;
	
	private GridPane gridTelaTurma;
	
	private Label lblTurma;
	
	private TilePane tileDisciplina;
		
	private ScrollPane scrollDisciplina;
	
	private GridPane gridDisciplina;
	
	private ScrollPane scrollTurma;
	
	private Label lblNomeDisciplina;
	
	private HBox hBoxScroll;
	
	private HBox hBoxTurma;
	
	private Professor professor;

 	
	private TurmaProfessorController controller = new TurmaProfessorController(this);

	public ViewTurmaProfessor(BorderPane border, Professor p) {
		setProfessor(p);
		this.borderPrincipal = border;
		
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
		
		//gridTelaDisciplina.heightProperty().addListener((x) -> gridDisciplina.setPrefHeight());
		//gridTelaTurma.widthProperty().addListener((x) -> gridDisciplina.setPrefWidth(gridTelaTurma.getWidth() * 0.9));
	
	
	}

	private void initTileDisciplina() {
	    tileDisciplina = new TilePane(10, 10);
	    tileDisciplina.setOrientation(Orientation.VERTICAL);
	    tileDisciplina.setPrefColumns(1);
	    
	    
	    scrollDisciplina.setContent(tileDisciplina);
		
	}

	private void initScrollDisciplina() {
		
		scrollDisciplina = new ScrollPane();
		scrollDisciplina.setPrefSize(2000, 2000);
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
		gridDisciplina.setPrefSize(500, 300);
		
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
		scrollTurma.setPrefSize(2000, 300);
		
		hBoxScroll.getChildren().add(scrollTurma);
		hBoxScroll.setPadding(new Insets(10, 10, 10, 10));
		hBoxScroll.setHgrow(scrollTurma, Priority.ALWAYS);	
	}
	
	private void initHboxScroll() {

		hBoxScroll = new HBox(10);
		hBoxScroll.setStyle("-fx-background-color: #D7D7D7;");
		hBoxScroll.setAlignment(Pos.CENTER);
		hBoxScroll.setPadding(new Insets(0, 0, 20, 0));
		hBoxScroll.setPrefSize(500, 300);
		gridDisciplina.add(hBoxScroll, 0, 1);
		
	}
	
	private void initHBoxTurma() {

		hBoxTurma = new HBox(10);
		hBoxTurma.setStyle("-fx-background-color: #D7D7D7;");
		hBoxTurma.setAlignment(Pos.CENTER);
		hBoxTurma.setPadding(new Insets(0, 0, 20, 0));
		hBoxTurma.setPrefSize(500, 200);
		scrollTurma.setContent(hBoxTurma);

	}
	
	public HBox initNovaDisciplina(CardTurma card) {
		initGridNomeDisciplina();
		initLabelNomeDisciplina(card.getDisciplina().getNome());
		initHboxScroll();
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
