package view;



import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Atividade  {

private BorderPane border;
	
	public Atividade(BorderPane border) {
		this.border = border;
		
		initAtividade();
	}

	public void initAtividade() {

		border.setCenter(new Label("Aqui é a Atividade"));
	}
}
