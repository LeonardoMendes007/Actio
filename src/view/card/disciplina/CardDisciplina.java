package view.card.disciplina;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import view.Util;
import view.card.ICard;

public class CardDisciplina implements ICard{
	
	private BorderPane borderPrincipal;

	private Label lblDisciplina;
	
	private Label lblProfessor;
	
	public CardDisciplina(Double width, Double height, String disciplina, String professor) {
		
		this.borderPrincipal = new BorderPane();
		this.borderPrincipal.setPrefSize(width, height);
		this.borderPrincipal.setStyle("-fx-background-color: red; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-cursor: hand;");
		
		initLabel(disciplina,professor);
		
		initEvent();
	}
	
	private void initEvent() {
		borderPrincipal.setOnMouseClicked((x) -> System.out.println("Você clicou em " + lblDisciplina.getText()));
		
	}

	private void initLabel(String disciplina, String professor) {

		lblDisciplina = new Label(disciplina);
		lblProfessor = new Label(professor);
		lblProfessor.setPadding(new Insets(0, 0, 10, 15));
		
		Util.setFontePadrao(new Label[] {lblDisciplina}, 19);
		Util.setFontePadrao(new Label[] {lblProfessor}, 14);
		
		borderPrincipal.setCenter(lblDisciplina);
		borderPrincipal.setBottom(lblProfessor);
		borderPrincipal.setAlignment(lblDisciplina, Pos.BOTTOM_LEFT);
		borderPrincipal.setMargin(lblDisciplina, new Insets(0, 0, 20, 15));
	}

	@Override
	public Node getCard() {
		return borderPrincipal;
	}

	

}
