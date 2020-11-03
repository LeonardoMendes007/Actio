package view;


import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class Util {
	
    public static void initButtons(Button[] buttons) {

		for (Button button : buttons) {
			button.setAlignment(Pos.CENTER);
			button.setStyle("-fx-cursor: hand; -fx-font-size: 1.5em; -fx-font-weight: bolder;");
			button.setPrefWidth(150);
			button.setPrefHeight(50);
		}

	}

	
}
