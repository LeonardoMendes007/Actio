package professor.view;



import professor.controller.TurmaController;
import professor.view.card.turma.CardTurma;

import java.util.ArrayList;

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
import view.Util;

public class Turma {

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

	//Isso é temporário 
	ArrayList<String> disciplinasExistentes = new ArrayList<String>();
	
	private TurmaController controller = new TurmaController(this);

	public Turma(BorderPane border) {
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
		gridTelaTurma.widthProperty().addListener((x) -> gridDisciplina.setPrefWidth(gridTelaTurma.getWidth() * 0.9));
	
	
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
		
		
		tileDisciplina.getChildren().add(gridDisciplina);
		
	}
	
	
	private void initLabelNomeDisciplina(String disciplina) {
	
		lblNomeDisciplina = new Label(disciplina);
		Util.setFontePadrao(new Label[] { lblNomeDisciplina }, 20, FontWeight.BOLD);
		
		gridDisciplina.add(lblNomeDisciplina, 0, 0);
	}
	
	
	
	private void initScrollTurma() {
		scrollTurma = new ScrollPane();
		scrollTurma.setStyle("-fx-background-color: #D7D7D7; -fx-vbar-policy : never;");

		hBoxScroll.getChildren().add(scrollTurma);
		hBoxScroll.setPadding(new Insets(10, 10, 10, 10));
		hBoxScroll.setHgrow(scrollTurma, Priority.ALWAYS);	
	}
	
	private void initHboxScroll() {

		hBoxScroll = new HBox(10);
		hBoxScroll.setStyle("-fx-background-color: #D7D7D7;");
		hBoxScroll.setAlignment(Pos.CENTER);
		hBoxScroll.setPadding(new Insets(0, 0, 20, 0));

		gridDisciplina.add(hBoxScroll, 0, 1);
		
	}
	
	private void initHBoxTurma() {

		hBoxTurma = new HBox(10);
		hBoxTurma.setStyle("-fx-background-color: #D7D7D7;");
		hBoxTurma.setAlignment(Pos.CENTER);
		hBoxTurma.setPadding(new Insets(0, 0, 0, 0));

		scrollTurma.setContent(hBoxTurma);

	}
	
	public void initNovaDisciplina(CardTurma card) {
		initGridNomeDisciplina();
		initLabelNomeDisciplina(card.getDisciplina());
		initHboxScroll();
		initScrollTurma();
		initHBoxTurma();
		addCard(card);
		disciplinasExistentes.add(card.getDisciplina());
	}
	
	public void addCard(CardTurma card) {
		
		hBoxTurma.getChildren().add(card.getCard());	
	}
	
	public void addDisciplina(CardTurma card) {
		boolean existe = false;
		
		//Se o vetor está vazio ele já add
		if(disciplinasExistentes.isEmpty()) {
			initNovaDisciplina(card);
		
		}else{
			//Caso já exista uma disciplina no vetor ele vai verificar se
			//precisa criar uma nova, ou é só add mais um card de turma

			for(String disciplina : disciplinasExistentes) {
				if(card.getDisciplina().equals(disciplina)) {
					addCard(card);
					existe = true;
				}
			}
			
			if(existe == false) {
				initNovaDisciplina(card);	
			}
		}
	}

	public BorderPane getBorderPrincipal() {
		return borderPrincipal;
	}
	
}
