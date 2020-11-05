package view;


import com.sun.javafx.geom.Rectangle;

import controller.HomeController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Home {
	
	private BorderPane borderPrincipal;
	private Pane paneAtividades;
	private Pane paneAtividadesInterno;
	private HBox hboxAtividades;
	private GridPane gridAtividades;
	private Label lblAtividades;
	private HomeController controller = new HomeController(this);
	
	public Home(BorderPane border) {
		this.borderPrincipal = border;
		
//		initHome();
	}

	public void initHome() {
		
		initAtividadesSemana();

		
		
	}

	private void initAtividadesSemana() {
		
		initPaneAtividades();
		
		initGridAtividades();
	    
		borderPrincipal.setTop(paneAtividades);
		
	}

	private void initPaneAtividades() {
		
		paneAtividades = new Pane();
		
	}

	private void initGridAtividades() {
		
		gridAtividades = new GridPane();
		
		
		initLabelAtividades();
		
		initPaneAtividadesInterno();
		
		initHboxAtividades();
		
		paneAtividadesInterno.getChildren().add(hboxAtividades);
		
		gridAtividades.add(lblAtividades, 0, 0);
		gridAtividades.add(paneAtividadesInterno, 0, 1);
		gridAtividades.setAlignment(Pos.CENTER_LEFT);
		
		paneAtividades.getChildren().add(gridAtividades);
		
	}

	private void initPaneAtividadesInterno() {

		paneAtividadesInterno = new Pane();
		paneAtividades.setMaxHeight(180);
		paneAtividades.setMaxWidth(400);
		paneAtividades.setMinHeight(150);
		paneAtividades.setMinWidth(300);		
		paneAtividades.setStyle("-fx-background-color: gray;");
		
		
	}

	private void initHboxAtividades() {

		hboxAtividades = new HBox();
		hboxAtividades.setSpacing(10);
		
		for (int i = 0; i < 3; i++) {
			Pane pane = new Pane();
			pane.setStyle("-fx-background-color: black;");
			pane.setPrefHeight(50);
			pane.setPrefWidth(50);
			hboxAtividades.getChildren().add(pane);
		}
		
		
	}

	private void initLabelAtividades() {
		
		lblAtividades = new Label("Atividades da Semana");
		
	}

}
