package view.card.turma;

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

public class CardTurma implements ICard{
	
	private BorderPane borderPrincipal;

	private Label lblTurma;
	
	private String disciplina;
	
	private String turma;
	
	public CardTurma(Double width, Double height, String disciplina, String turma, String corHexa) {
		
		this.borderPrincipal = new BorderPane();
		this.borderPrincipal.setPrefSize(width, height);
		this.borderPrincipal.setStyle("-fx-background-color: "+ corHexa+"; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-cursor: hand;");
		
		initLabel(turma);
		
		initEvent();
		
		this.disciplina = disciplina;
		this.turma = turma;
	}
	
	private void initEvent() {
		borderPrincipal.setOnMouseClicked((x) -> System.out.println("Você clicou em " + lblTurma.getText()));
		
		Util.hoverFade(this.borderPrincipal);
	}

	private void initLabel(String turma) {

		lblTurma = new Label(turma);
		lblTurma.setTextAlignment(TextAlignment.LEFT);
		lblTurma.setTextFill(Color.web("#000000", 0.5));
		lblTurma.setPadding(new Insets(0, 0, 15, 15));
		
		Util.setFontePadrao(new Label[] {lblTurma}, 16, FontWeight.BOLD);
		

		borderPrincipal.setCenter(lblTurma);
		borderPrincipal.setAlignment(lblTurma, Pos.BOTTOM_LEFT);
	}

	@Override
	public Node getCard() {
		return borderPrincipal;
	}
	
	public String getDisciplina() {
		return disciplina;
	}

	

}
