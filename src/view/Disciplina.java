package view;



import controller.DisciplinaController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import view.card.disciplina.CardDisciplina;

public class Disciplina {

	private BorderPane borderPrincipal;
	
	private GridPane gridDisciplina;
	
	private Label lblDisciplina;
	
	private TilePane tileDisciplina;
	
	private ScrollPane scrollDisciplina;
	
	private DisciplinaController controller = new DisciplinaController(this);

	public Disciplina(BorderPane border) {
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
		
		gridDisciplina.heightProperty().addListener((x) -> tileDisciplina.setPrefHeight(gridDisciplina.getHeight()));
		gridDisciplina.widthProperty().addListener((x) -> tileDisciplina.setPrefWidth(gridDisciplina.getWidth()));
	}

	private void initTileDisciplina() {
	    tileDisciplina = new TilePane(30,30);
	    tileDisciplina.setPadding(new Insets(20));
	    tileDisciplina.setOrientation(Orientation.HORIZONTAL);
	    tileDisciplina.setPrefColumns(2);
	    
	    
	    scrollDisciplina.setContent(tileDisciplina);
		
	}

	private void initScrollDisciplina() {
		
		scrollDisciplina = new ScrollPane();
		scrollDisciplina.setPrefSize(2000, 2000);
		scrollDisciplina.setHbarPolicy(ScrollBarPolicy.NEVER);
	
		scrollDisciplina.setStyle("-fx-background-color:transparent;");
	    gridDisciplina.add(scrollDisciplina, 0, 1);
	}

	private void initLabelDisciplina() {
		
		lblDisciplina = new Label("Disciplina");
		
		lblDisciplina.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + 25 + " ;");
		
		gridDisciplina.add(lblDisciplina, 0, 0);
	}

	private void initGridDisciplina() {
		
		gridDisciplina = new GridPane();
		
		gridDisciplina.setPadding(new Insets(0, 0, 5, 20));
		
		borderPrincipal.setCenter(gridDisciplina);
		borderPrincipal.setMargin(gridDisciplina, new Insets(-30, 15, 0, 20));
		
		
		
	}
	
	public void addCard(CardDisciplina card) {
		
		tileDisciplina.getChildren().add(card.getCard());
	}

	public BorderPane getBorderPrincipal() {
		return borderPrincipal;
	}
	
}
