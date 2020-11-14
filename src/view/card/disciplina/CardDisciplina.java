package view.card.disciplina;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import view.Util;
import view.card.ICard;

public class CardDisciplina implements ICard{
	
	private BorderPane borderPrincipal;

	private Label lblDisciplina;
	
	private Label lblProfessor;
	
	public CardDisciplina(Double width, Double height, String disciplina, String professor, String corHexa) {
		
		this.borderPrincipal = new BorderPane();
		this.borderPrincipal.setPrefSize(width, height);
		this.borderPrincipal.setStyle("-fx-background-color: "+ corHexa+"; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-cursor: hand;");
		
		initLabel(disciplina,professor);
		
		initEvent();
	}
	
	private void initEvent() {
		borderPrincipal.setOnMouseClicked((x) -> System.out.println("Você clicou em " + lblDisciplina.getText()));
		
		Util.hoverFade(this.borderPrincipal);
	}

	private void initLabel(String disciplina, String professor) {

		lblDisciplina = new Label(disciplina);
		lblDisciplina.setWrapText(true);
		lblDisciplina.setTextAlignment(TextAlignment.LEFT);
		lblDisciplina.setMaxWidth(180);
		lblDisciplina.setTextFill(Color.web("#000000", 0.5));
		
		lblProfessor = new Label(professor);
		lblProfessor.setPadding(new Insets(0, 0, 15, 15));
		lblProfessor.setTextFill(Color.web("#000000", 0.5));
		
		Util.setFontePadrao(new Label[] {lblDisciplina}, 19, FontWeight.BOLD);
		Util.setFontePadrao(new Label[] {lblProfessor}, 14, FontWeight.NORMAL);
		
		borderPrincipal.setCenter(lblDisciplina);
		borderPrincipal.setBottom(lblProfessor);
		borderPrincipal.setAlignment(lblDisciplina, Pos.BOTTOM_LEFT);
		borderPrincipal.setMargin(lblDisciplina, new Insets(0, 0, 0, 15));
	}

	@Override
	public Node getCard() {
		return borderPrincipal;
	}

	

}
