package view;


import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class Util {
	
    public static void initButtons(Button[] buttons) {

		for (Button button : buttons) {
			button.setAlignment(Pos.CENTER);
			button.setPrefWidth(200);
			button.setPrefHeight(50);
		}

	}

	
}
