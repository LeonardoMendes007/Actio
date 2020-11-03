package view;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Home {
	
	private BorderPane border;
	
	public Home(BorderPane border) {
		this.border = border;
		
		initHome();
	}

	public void initHome() {

		border.setCenter(new Label("Aqui é a Home"));
	}

}
