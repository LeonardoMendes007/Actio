package view;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class Util {
	
    public static void initButtons(Button[] buttons) {

		for (Button button : buttons) {
			button.setAlignment(Pos.CENTER);
			button.setPrefWidth(200);
			button.setPrefHeight(50);
		}

	}
    
    public static void setFontePadrao(Node[] texts, int tamanho) {

		for (Node text : texts) {
			text.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + tamanho + " ;");
		}
	}


	
}
