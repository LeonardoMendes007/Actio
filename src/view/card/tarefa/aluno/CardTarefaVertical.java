package view.card.tarefa.aluno;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import model.Atividade;
import view.Util;
import view.aluno.EntregaAtividadeAluno;
import view.card.ICard;

public class CardTarefaVertical implements ICard {

	private BorderPane borderPrincipal;
	
	private BorderPane borderCard;

	private Label lblDisciplina;

	private Label lblTitulo;

	private Label lblLegenda;
	
	private Pane paneGroupEPrazo;

	private Label lblDataDeEntrega;

	private Label lblNumDeDias;

	private Color corDisciplina;
	
	public CardTarefaVertical(BorderPane pane, Atividade atividade) {
		
		this.borderPrincipal = pane; 

		corDisciplina = Color.web(atividade.getTurma().getDisciplina().getCor());
		
		this.borderCard = new BorderPane();
		this.borderCard.setPrefSize(280, 150);
		this.borderCard.setStyle("-fx-background-color: "+atividade.getTurma().getDisciplina().getCor()+" ; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-cursor: hand;");
		this.borderCard.setPadding(new Insets(5));

		paneGroupEPrazo = new Pane();
		borderPrincipal.setTop(paneGroupEPrazo);
		
		initCentroCard(atividade.getNome(), atividade.getDescricao());

		initDisciplina(atividade.getTurma().getDisciplina().getNome());

		verificarGroup(atividade.isGrupo());
		
		initPrazoDeEntrega(atividade.getDtEntrega());
		
		initEvent(atividade);
	}
	

	private void initPrazoDeEntrega(Date prazo) {
		
	    lblDataDeEntrega = new Label("Até " + prazo.getDay() + "/" + prazo.getMonth());
	    lblDataDeEntrega.setTextFill(Color.web("#FFFFFF"));
	    
	    lblNumDeDias = new Label("Faltam " + 2 + " dias");
		lblNumDeDias.setTextFill(Color.web("#FFFFFF"));
		
		
		lblDataDeEntrega.setAlignment(Pos.TOP_RIGHT);
		
		Util.setFontePadrao(new Label[] { lblDataDeEntrega }, 12, FontWeight.BOLD);
		Util.setFontePadrao(new Label[] { lblNumDeDias }, 12, FontWeight.NORMAL);

		GridPane gridData = new GridPane();
		
		gridData.add(lblDataDeEntrega, 0, 0);
		gridData.add(lblNumDeDias, 0, 1);
		gridData.setLayoutX(190);
		paneGroupEPrazo.getChildren().add(gridData);
	}

	private void verificarGroup(boolean group) {

		if (group) {

			carregarImageGroup("src\\view\\img\\group 1.png");

		} else {

			carregarImageGroup("src\\view\\img\\user 1.png");

		}

	}

	private void carregarImageGroup(String path) {
		try {
			Image imagem = new Image(new FileInputStream(path));

			ImageView ivGroup = new ImageView(imagem);
			ivGroup.setFitWidth(40.0);
			ivGroup.setFitHeight(40.0);
			paneGroupEPrazo.getChildren().add(ivGroup);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		

	}

	private void initDisciplina(String disciplina) {


		
		lblDisciplina = new Label(disciplina);
		Util.setFontePadrao(new Label[] { lblDisciplina }, 12, FontWeight.NORMAL);

		lblDisciplina.setPadding(new Insets(5));
		lblDisciplina.setStyle(lblDisciplina.getStyle()
				+ "-fx-background-color: rgba(0, 0, 0, 0.3); -fx-background-radius: 10px; -fx-border-radius: 10px; ");


		lblDisciplina.setTextFill(corDisciplina);

		borderCard.setBottom(lblDisciplina);
		borderCard.setAlignment(lblDisciplina, Pos.CENTER);

	}  

	private void initCentroCard(String titulo, String legenda) {

		lblTitulo = new Label(titulo);
		lblTitulo.setTextFill(Color.web("#000000", 0.5));
		
		lblLegenda = new Label(legenda);
		lblLegenda.setTextFill(Color.web("#000000", 0.5));
		
		Util.setFontePadrao(new Label[] { lblTitulo }, 15, FontWeight.BOLD);
		Util.setFontePadrao(new Label[] { lblLegenda }, 10, FontWeight.LIGHT);
		

		GridPane gridInterno = new GridPane();
		gridInterno.add(lblTitulo, 0, 0);
		gridInterno.add(lblLegenda, 0, 1);
        gridInterno.setPadding(new Insets(6, 0, 0, 10));
		
        borderCard.setCenter(gridInterno);
        borderCard.setAlignment(gridInterno, Pos.BOTTOM_LEFT);
	}

	private void initEvent(Atividade atividade) {
		borderCard.setOnMouseClicked((x) -> {

	        new EntregaAtividadeAluno(atividade, borderPrincipal);
			
		});

		Util.hoverFade(borderCard);
	}

	@Override
	public Node getCard() {
		return borderPrincipal;
	}
}
