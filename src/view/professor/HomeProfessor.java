package view.professor;

import controller.professor.HomeProfessorController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Professor;
import view.card.ICard;
import view.card.turma.CardTurma;

public class HomeProfessor {

	private BorderPane borderPrincipal;

	private BorderPane borderInterno;

	private GridPane gridAtividades;

	private HBox hboxAtividades;

	private ScrollPane scrollAtividades;

	private HBox hboxInternoAtividades;

	private GridPane gridNotificacoes;

	private VBox vboxNotificacoes;

	private ScrollPane scrollNotificacoes;

	private VBox vboxInternoNotificacoes;

	private GridPane gridCalendario;

	private Label lblAtividade;

	private Label lblNotificacoes;

	private Label lblCalendario;

	private Professor professor;
	
	private HomeProfessorController controller = new HomeProfessorController(this);

	public HomeProfessor(BorderPane border, Professor p) {
		this.borderPrincipal = border;
		this.setProfessor(p);
		initTela();
	}

	public void initTela() {

		initBorderInterno();

		initGridNotificacoes();

		initGridAtividades();

		initGridCalendario();

		initHBoxAtividades();

		initVBoxNotificacoes();

		initScrollAtividades();

		initScrollNotificacoes();

		initHBoxInternoAtividades();

		initVBoxInternoNotificacoes();

		initLabels();

		initEventsResponsivel();

		controller.verificarCards();

	}

	private void initEventsResponsivel() {

		borderPrincipal.widthProperty()
				.addListener((x) -> vboxNotificacoes.setMinWidth(0.30 * borderPrincipal.getWidth()));
		scrollNotificacoes.widthProperty()
				.addListener((x) -> vboxInternoNotificacoes.setMinWidth(0.97 * scrollNotificacoes.getWidth()));

	}

	private void initVBoxInternoNotificacoes() {
		vboxInternoNotificacoes = new VBox(7);
		vboxInternoNotificacoes.setStyle("-fx-background-color: #D7D7D7;");
		vboxInternoNotificacoes.setAlignment(Pos.CENTER);
		vboxInternoNotificacoes.setPadding(new Insets(3));

		scrollNotificacoes.setContent(vboxInternoNotificacoes);

	}

	private void initHBoxInternoAtividades() {

		hboxInternoAtividades = new HBox(10);
		hboxInternoAtividades.setStyle("-fx-background-color: #D7D7D7;");
		hboxInternoAtividades.setAlignment(Pos.CENTER);
		hboxInternoAtividades.setPadding(new Insets(0, 0, 20, 0));

		scrollAtividades.setContent(hboxInternoAtividades);

	}

	private void initScrollNotificacoes() {

		scrollNotificacoes = new ScrollPane();

		scrollNotificacoes.setStyle("-fx-background-color: #D7D7D7; -fx-hbar-policy : never;");

		vboxNotificacoes.getChildren().add(scrollNotificacoes);
		vboxNotificacoes.setPadding(new Insets(10, 10, 10, 10));
		vboxNotificacoes.setVgrow(scrollNotificacoes, Priority.ALWAYS);

	}

	private void initScrollAtividades() {

		scrollAtividades = new ScrollPane();
		scrollAtividades.setStyle("-fx-background-color: #D7D7D7; -fx-vbar-policy : never;");

		hboxAtividades.getChildren().add(scrollAtividades);
		hboxAtividades.setPadding(new Insets(10, 10, 10, 10));
		hboxAtividades.setHgrow(scrollAtividades, Priority.ALWAYS);

	}

	private void initVBoxNotificacoes() {
		vboxNotificacoes = new VBox();

		vboxNotificacoes
				.setStyle("-fx-background-color: #D7D7D7; -fx-background-radius: 20px; -fx-border-radius: 20px;");

		gridNotificacoes.add(vboxNotificacoes, 0, 1);

	}

	private void initHBoxAtividades() {

		hboxAtividades = new HBox();

		hboxAtividades.setStyle("-fx-background-color: #D7D7D7; -fx-background-radius: 20px; -fx-border-radius: 20px;");

		gridAtividades.add(hboxAtividades, 0, 1);
	}

	private void initGridCalendario() {

		gridCalendario = new GridPane();
		gridCalendario.setHgap(10);
		gridCalendario.setVgap(10);
		gridCalendario.setStyle(" -fx-background-radius: 20px; -fx-border-radius: 20px;");
		gridCalendario.setPadding(new Insets(20, 0, 5, 20));

		borderInterno.setCenter(gridCalendario);
		borderInterno.setMargin(gridCalendario, new Insets(0, 15, 15, 15));

	}

	private void initGridAtividades() {

		gridAtividades = new GridPane();
		gridAtividades.setHgap(10);
		gridAtividades.setVgap(10);
		gridAtividades.setStyle("-fx-background-radius: 20px; -fx-border-radius: 20px;");
		gridAtividades.setPadding(new Insets(20, 0, 5, 20));

		borderInterno.setTop(gridAtividades);
		borderInterno.setMargin(gridAtividades, new Insets(-70, 15, 0, 20));

	}

	private void initGridNotificacoes() {
		gridNotificacoes = new GridPane();
		gridNotificacoes.setHgap(10);
		gridNotificacoes.setVgap(10);
		gridNotificacoes.setStyle(" -fx-background-radius: 20px; -fx-border-radius: 20px;");
		gridNotificacoes.setPadding(new Insets(10, 10, 5, 10));

		borderPrincipal.setRight(gridNotificacoes);
		borderPrincipal.setMargin(gridNotificacoes, new Insets(0, 10, 10, 10));

	}

	private void initLabels() {

		lblAtividade = new Label("Atividades para Corrigir");
		lblNotificacoes = new Label("Notificações");
		lblCalendario = new Label("Calendario");

		lblAtividade.setPadding(new Insets(0, 0, 0, 10));
		lblNotificacoes.setPadding(new Insets(0, 0, 0, 10));
		lblCalendario.setPadding(new Insets(0, 0, 0, 10));

		setFontePadrao(new Label[] { lblAtividade, lblNotificacoes, lblCalendario }, 25);

		gridAtividades.add(lblAtividade, 0, 0);
		gridNotificacoes.add(lblNotificacoes, 0, 0);
		gridCalendario.add(lblCalendario, 0, 0);

	}

	private void setFontePadrao(Node[] texts, int tamanho) {

		for (Node text : texts) {
			text.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + tamanho + " ;");
		}
	}

	private void initBorderInterno() {

		borderInterno = new BorderPane();

		borderPrincipal.setCenter(borderInterno);

	}

	public BorderPane getBorderPrincipal() {
		return borderPrincipal;
	}

	public void addCardAtividade(ICard card) {

		hboxInternoAtividades.getChildren().add(card.getCard());
	}

	public void addCardNotificacao(ICard card) {

		vboxInternoNotificacoes.getChildren().add(card.getCard());
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
