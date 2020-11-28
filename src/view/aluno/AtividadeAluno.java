package view.aluno;

import controller.aluno.AtividadeAlunoController;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import model.Aluno;
import view.Util;
import view.card.tarefa.aluno.CardTarefaHorizontal;

public class AtividadeAluno {

	private BorderPane borderPrincipal;

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

	private VBox vboxAtividade;

	private AtividadeAlunoController controller = new AtividadeAlunoController(this);

	private Aluno aluno;

	public AtividadeAluno(BorderPane border, Aluno a) {
		this.borderPrincipal = border;
		setAluno(a);

		initTela();
	}

	public void initTela() {

		initGridAtividade();

		initLabelAtividade();

		initGridFiltro();

		initLabelFiltro();

		initComboBoxFiltro();

		initScrollAtividade();

		initVboxAtividade();

		initEventResposivel();

		initEventCombox();

		controller.verificarCards();

	}

	private void initEventCombox() {

		cbDisciplina.getSelectionModel().selectedIndexProperty()
				.addListener((x) -> controller.filtroAtividades(cbStatus.getValue(), cbPessoas.getValue(), cbDisciplina.getValue()));
		cbPessoas.getSelectionModel().selectedIndexProperty()
				.addListener((x) -> controller.filtroAtividades(cbStatus.getValue(), cbPessoas.getValue(), cbDisciplina.getValue()));
		cbStatus.getSelectionModel().selectedIndexProperty()
				.addListener((x) -> controller.filtroAtividades(cbStatus.getValue(), cbPessoas.getValue(), cbDisciplina.getValue()));

	}

	private void initVboxAtividade() {

		vboxAtividade = new VBox(10);
		vboxAtividade.setPadding(new Insets(15));
		vboxAtividade.setStyle("-fx-background-color: #D9D6D6;");

		scrollAtividade.setContent(vboxAtividade);

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

		cbStatus.getItems().addAll("Aberto", "Fechado", "Todas");
		cbPessoas.getItems().addAll("Individual", "Grupo", "Todas");

		gridFiltro.add(cbStatus, 1, 1);
		gridFiltro.add(cbPessoas, 2, 1);
		gridFiltro.add(cbDisciplina, 3, 1);

	}

	private void initLabelFiltro() {
		lblFiltro = new Label("Filtrar por:");
		lblStatus = new Label("Status");
		lblDisciplina = new Label("Disciplina");
		lblPessoas = new Label("Pessoas");

		lblFiltro.setTextFill(Color.WHITE);
		lblStatus.setTextFill(Color.WHITE);
		lblDisciplina.setTextFill(Color.WHITE);
		lblPessoas.setTextFill(Color.WHITE);

		lblFiltro.setAlignment(Pos.CENTER_LEFT);

		lblFiltro.setMinWidth(140);

		lblFiltro.setPadding(new Insets(-25, 0, 0, 0));

		Util.setFontePadrao(new Label[] { lblFiltro }, 20, FontWeight.BOLD);
		Util.setFontePadrao(new Label[] { lblStatus, lblDisciplina, lblPessoas }, 15, FontWeight.NORMAL);

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
		gridFiltro.setPadding(new Insets(10, 20, 10, 20));

		gridFiltro.setStyle("-fx-background-color: #707070 ; -fx-background-radius: 20px; -fx-border-radius: 20px;");

		gridAtividade.add(gridFiltro, 0, 1);

	}

	private void initLabelAtividade() {

		lblAtividade = new Label("Atividades");

		lblAtividade.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + 30 + " ;");

		gridAtividade.add(lblAtividade, 0, 0);

		gridAtividade.setMargin(lblAtividade, new Insets(0, 0, 20, 0));
	}

	private void initGridAtividade() {

		gridAtividade = new GridPane();

		gridAtividade.setPrefSize(2000, 2000);

		gridAtividade.setPadding(new Insets(0, 0, 5, 20));

		borderPrincipal.setCenter(gridAtividade);
		borderPrincipal.setMargin(gridAtividade, new Insets(-30, 15, 0, 15));

	}

	public BorderPane getBorderPrincipal() {
		return borderPrincipal;
	}

	public void clearCardAtvidade() {

		vboxAtividade.getChildren().clear();
	}

	public void addCardAtvidade(CardTarefaHorizontal card) {

		initEventCard(card);

		vboxAtividade.getChildren().add(card.getCard());

	}

	private void initEventCard(CardTarefaHorizontal card) {

		gridFiltro.widthProperty().addListener((x) -> {

			if (borderPrincipal.getWidth() > 830) {
				card.getHboxPrincipal().setMinWidth(gridFiltro.getWidth() - 10);
			} else {
				card.getHboxPrincipal().setMinWidth(830);
			}

		});

		card.getHboxPrincipal().setMinWidth(gridFiltro.getWidth() - 10);

	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public ComboBox<String> getCbDisciplina() {
		return cbDisciplina;
	}

}
