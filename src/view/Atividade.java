package view;

import java.util.Arrays;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class Atividade {

	private BorderPane border;

	private GridPane gridAtividade;

	private Label lblAtividade;

	private GridPane gridFiltro;
	
	private Label lblFiltro;
	
	private Label lblStatus;
	
	private ComboBox<String> cbStatus;
	
	private Label lblPessoas;
	
	private ComboBox<String> cbPessoas;
	
	private Label lblDisciplina;
	
	private ComboBox<String> cbDisciplina;
	
	private ScrollPane scrollAtividade;
	
	private TilePane tileAtividade;
	
	public Atividade(BorderPane border) {
		this.border = border;

		initAtividade();
	}

	public void initAtividade() {

		initGridAtividade();

		initLabelAtividade();
		
		initGridFiltro();
		
		initLabelFiltro();
		
		initComboBoxFiltro();
		
		initScrollAtividade();

		initTileAtividade();
		
		initEventResposivel();

	}

	private void initTileAtividade() {
	
		tileAtividade = new TilePane();
		tileAtividade.setPrefColumns(1);
		tileAtividade.setStyle("-fx-background-color: #D9D6D6;");

		scrollAtividade.setContent(tileAtividade);
		
	}

	private void initScrollAtividade() {
		
		scrollAtividade = new ScrollPane();
		
		
		scrollAtividade.setStyle("-fx-background-color: #D9D6D6;");
		
		gridAtividade.add(scrollAtividade, 0, 2);
		
	}

	private void initEventResposivel() {
		
		gridFiltro.widthProperty().addListener((x) -> {
			
			double total = gridAtividade.getWidth() - lblFiltro.getMinWidth();
			
			cbStatus.setPrefWidth(total * 0.33);
			cbPessoas.setPrefWidth(total * 0.33);
			cbDisciplina.setPrefWidth(total * 0.33);
			
			
			
		});
		
		
		
	}

	private void initComboBoxFiltro() {
		cbStatus = new ComboBox<>();
		cbPessoas = new ComboBox<>();
		cbDisciplina = new ComboBox<>();

		cbStatus.getItems().addAll("Aberto","Fechado");
		cbPessoas.getItems().addAll("Uma","Duas","Tr�s","Quatro","Cinco");
		cbDisciplina.getItems().addAll("Banco de Dados","Programa��o orientada a disgra�a");
		
		gridFiltro.add(cbStatus, 1, 1);
		gridFiltro.add(cbPessoas, 2, 1);
		gridFiltro.add(cbDisciplina, 3, 1);
		
	}

	private void initLabelFiltro() {
		lblFiltro = new Label("Filtrar por:");
		lblStatus = new Label("Status");
		lblDisciplina = new Label("Disciplina");
		lblPessoas = new Label("Pessoas");
		
		lblFiltro.setAlignment(Pos.CENTER_LEFT);

		lblFiltro.setMinWidth(140);

		lblFiltro.setPadding(new Insets(-40, 0, 0, 0));
		
		Util.setFontePadrao(new Label[] {lblFiltro}, 25);
		Util.setFontePadrao(new Label[] { lblStatus, lblDisciplina,lblPessoas}, 15);
		
		gridFiltro.add(lblFiltro, 0, 1);
		gridFiltro.add(lblStatus, 1, 0);
		gridFiltro.add(lblPessoas, 2, 0);
		gridFiltro.add(lblDisciplina, 3, 0);
		
		gridFiltro.setHalignment(lblDisciplina, HPos.CENTER);
		gridFiltro.setHalignment(lblStatus, HPos.CENTER);
		gridFiltro.setHalignment(lblPessoas, HPos.CENTER);
		
	}

	private void initGridFiltro() {
		
		gridFiltro = new GridPane();
		gridFiltro.setPrefWidth(2000);
		gridFiltro.setHgap(20);
		gridFiltro.setVgap(5);
		gridFiltro.setPadding(new Insets(10,20,10,20));

		gridFiltro.setStyle("-fx-background-color: #707070 ; -fx-background-radius: 20px; -fx-border-radius: 20px;");
		
		gridAtividade.add(gridFiltro, 0, 1);
		
	}

	private void initLabelAtividade() {

		lblAtividade = new Label("Atividades");

		lblAtividade.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + 30 + " ;");

		gridAtividade.add(lblAtividade, 0, 0);
		
		gridAtividade.setMargin(lblAtividade, new Insets(0,0,20,0));
	}

	private void initGridAtividade() {

		gridAtividade = new GridPane();
	
		gridAtividade.setPrefSize(2000, 2000);

		gridAtividade.setPadding(new Insets(0, 0, 5, 20));

		border.setCenter(gridAtividade);
		border.setMargin(gridAtividade, new Insets(-30, 15, 0, 15));

	}
}
