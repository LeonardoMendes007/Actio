package view.card.tarefa.professor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import model.Atividade;
import view.Util;
import view.card.ICard;
import view.professor.VisualizarAtividade;

public class CardTarefaVertical implements ICard {

	private BorderPane border;

	private Label lblDisciplina;

	private Label lblTitulo;

	private Label lblLegenda;

	private Pane paneGroupEPrazo;

	private Label lblDataDeEntrega;

	private Label lblNumDeDias;

	private Color corDisciplina;

	private BorderPane borderPrincipal;

	private Atividade atividade;

	public CardTarefaVertical(BorderPane borderPrincipal, Atividade atividade) {

		this.borderPrincipal = borderPrincipal;

		this.atividade = atividade;

		corDisciplina = Color.web(atividade.getDiscTurmaProf().getDisciplina().getCor());

		this.border = new BorderPane();
		this.border.setPrefSize(300, 150);
		this.border.setStyle("-fx-background-color: " + atividade.getDiscTurmaProf().getDisciplina().getCor()
				+ " ; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-cursor: hand;");
		this.border.setPadding(new Insets(5));

		paneGroupEPrazo = new Pane();
		border.setTop(paneGroupEPrazo);

		initCentroCard(atividade.getNome(), atividade.getDescricao());

		initDisciplina(atividade.getDiscTurmaProf().getDisciplina().getNome());

		verificarGroup(atividade.isGrupo());

		initPrazoDeEntrega(atividade.getDtEntrega());

		initEvent();
	}

	private void initPrazoDeEntrega(Date prazo) {

		GregorianCalendar gc = new GregorianCalendar();

		gc.setTime(prazo);
		
		gc.add(GregorianCalendar.DAY_OF_MONTH, -1);

		lblDataDeEntrega = new Label("At� " + gc.get(GregorianCalendar.DAY_OF_MONTH) + "/" + (gc.get(GregorianCalendar.MONTH) + 1));
		lblDataDeEntrega.setTextFill(Color.web("#FFFFFF"));

		lblNumDeDias = new Label("Faltam " + Util.diasRestarntes(prazo) + " dias");
		lblNumDeDias.setTextFill(Color.web("#FFFFFF"));

		lblDataDeEntrega.setAlignment(Pos.TOP_RIGHT);

		Util.setFontePadrao(new Label[] { lblDataDeEntrega }, 12, FontWeight.BOLD);
		Util.setFontePadrao(new Label[] { lblNumDeDias }, 12, FontWeight.NORMAL);

		GridPane gridData = new GridPane();

		gridData.add(lblDataDeEntrega, 0, 0);
		gridData.setHalignment(lblDataDeEntrega, HPos.RIGHT);
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

		border.setBottom(lblDisciplina);
		border.setAlignment(lblDisciplina, Pos.CENTER);

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

		border.setCenter(gridInterno);
		border.setAlignment(gridInterno, Pos.BOTTOM_LEFT);
	}

	private void initEvent() {
		border.setOnMouseClicked((x) -> new VisualizarAtividade(atividade, borderPrincipal));

		Util.hoverFade(this.border);
	}

	@Override
	public Node getCard() {
		return border;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
}
