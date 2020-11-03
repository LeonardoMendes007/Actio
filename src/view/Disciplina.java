package view;



import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Disciplina {

	private BorderPane border;

	public Disciplina(BorderPane border) {
		this.border = border;
		
		initDisciplina();
	}

	public void initDisciplina() {

		border.setCenter(new Label("Aqui é a Disciplina"));
	}
}
