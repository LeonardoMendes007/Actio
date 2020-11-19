package view.professor;



import java.util.ArrayList;

import controller.professor.TurmaController;
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
import view.card.turma.CardTurma;

public class Turma {

	private BorderPane borderPrincipal;
	
	private GridPane gridTelaTurma;
	
	private Label lblTurma;
	
	private TilePane tileDisciplina;
		
	private ScrollPane scrollDisciplina;
	
	private GridPane gridDisciplina;
	
	private ScrollPane scrollTurma;
	
	private Label lblNomeDisciplina;
	
	
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

		initTileDisciplina();
		
		initEventResponsivel();
		
		controller.addCards();
		
	}

	private void initEventResponsivel() {
		
		borderPrincipal.widthProperty().addListener((x) -> {
			scrollDisciplina.setPrefWidth(borderPrincipal.getWidth() * 0.9);
			tileDisciplina.setPrefWidth(borderPrincipal.getWidth() * 0.5);
			
		});

		
		//tileDisciplina.widthProperty().addListener((x) -> gridDisciplina.setPrefWidth(tileDisciplina.getWidth() * 0.5));
		
		
		//gridTelaDisciplina.heightProperty().addListener((x) -> gridDisciplina.setPrefHeight());
		//gridTelaTurma.widthProperty().addListener((x) -> gridDisciplina.setPrefWidth(gridTelaTurma.getWidth() * 0.9));
	
		//scroll pane 
		//gridTelaTurma.widthProperty().addListener((x) -> scrollTurma.setPrefWidth(gridDisciplina.getWidth() * 0.9));
		
	
	}

	private void initTileDisciplina() {
	    tileDisciplina = new TilePane(10, 10);
	    tileDisciplina.setOrientation(Orientation.VERTICAL);
	    tileDisciplina.setPrefColumns(1);
   
	   // tileDisciplina.setPadding(new Insets(50));
	   // tileDisciplina.setPrefWidth(scrollDisciplina.getWidth()* 0.5);
	    scrollDisciplina.setContent(tileDisciplina);
	}

	private void initScrollDisciplina() {
		
		scrollDisciplina = new ScrollPane();
		//scrollDisciplina.setPrefSize(2000, 2000);
		//scrollDisciplina.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollDisciplina.setPadding(new Insets(20, 0, 0, 0));
		
	//	scrollDisciplina.setStyle("-fx-background-color:transparent;");
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
		gridDisciplina.setPadding(new Insets(10, 20, 5, 20));
		//gridDisciplina.setPrefWidth(scrollTurma.getWidth() * 0.8);
	
		gridDisciplina.setPrefWidth(600);
		gridDisciplina.setGridLinesVisible(true);
		
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
		scrollTurma.setPrefSize(800, 200);
		scrollTurma.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollTurma.setPadding(new Insets(20, 0, 0, 0));

		gridDisciplina.add(scrollTurma, 0, 1);
	}
	

	
	private void initHBoxTurma() {

		hBoxTurma = new HBox(10);
		hBoxTurma.setStyle("-fx-background-color: #D7D7D7;");
		hBoxTurma.setAlignment(Pos.CENTER);
		hBoxTurma.setPadding(new Insets(0, 0, 20, 0));

		scrollTurma.setContent(hBoxTurma);

	}
	
	public void initNovaDisciplina(CardTurma card) {
		initGridNomeDisciplina();
		initLabelNomeDisciplina(card.getDisciplina());
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
