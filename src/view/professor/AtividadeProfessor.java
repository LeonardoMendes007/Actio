package view.professor;



import controller.professor.AtividadeProfessorController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import model.Professor;
import view.Util;
import view.card.tarefa.professor.CardTarefaHorizontal;

public class AtividadeProfessor {

	private BorderPane borderPrincipal;

	private GridPane gridAtividade;

	private Label lblAtividade;

	private GridPane gridFiltro;
	
	private Label lblFiltro;
	
	private Label lblStatus;
	
	private ComboBox<String> cbStatus;
	
	private Label lblTurma;
	
	private ComboBox<String> cbTurma;
	
	private Label lblDisciplina;
	
	private ComboBox<String> cbDisciplina;
	
	private ScrollPane scrollAtividade;
	
	private VBox vboxAtividade;
	
	private Button btCriarAtividade;
	
	private Professor professor;
	
	private AtividadeProfessorController controller = new AtividadeProfessorController(this);
	
	public AtividadeProfessor(BorderPane border, Professor professor) {
		this.borderPrincipal = border;
		this.professor = professor;

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
		
		initButtonCriarAtividade();
		
		initEventResposivel();
		
		initEventBtAtividade();
		
	    initEventCombox();
		
		controller.verificarCards();
		
		

	}
	
	private void initEventCombox() {

		cbDisciplina.getSelectionModel().selectedIndexProperty().addListener((x) -> controller.filtroAtividades(cbStatus.getValue(), cbDisciplina.getValue(),cbTurma.getValue()));
		cbTurma.getSelectionModel().selectedIndexProperty().addListener((x) -> controller.filtroAtividades(cbStatus.getValue(), cbDisciplina.getValue(),cbTurma.getValue()));
		cbStatus.getSelectionModel().selectedIndexProperty().addListener((x) -> controller.filtroAtividades(cbStatus.getValue(), cbDisciplina.getValue(),cbTurma.getValue()));

	}

	

	private void initEventBtAtividade() {
		
		btCriarAtividade.setOnMouseClicked((x) -> new CriarAtividade(borderPrincipal, professor));
		
	}

	private void initButtonCriarAtividade() {
		
		btCriarAtividade = new Button("Nova Atividade");
		
		Util.setFontePadrao(new Button[] {btCriarAtividade}, 15, FontWeight.BOLD);
		
		btCriarAtividade.setStyle(btCriarAtividade.getStyle() + "-fx-background-color: #57BEBE; -fx-background-radius: 20px; -fx-border-radius: 20px;");
		
		btCriarAtividade.setTextFill(Color.WHITE);
		btCriarAtividade.setPrefHeight(20);
		Util.initButtons(new Button[] {btCriarAtividade});
		
		Util.hoverFade(btCriarAtividade);
		
		gridAtividade.add(btCriarAtividade, 0, 1);
		gridAtividade.setMargin(btCriarAtividade, new Insets(-30, 0, 15, 0));
		gridAtividade.setHalignment(btCriarAtividade, HPos.RIGHT);
		gridAtividade.setValignment(btCriarAtividade, VPos.CENTER);
		
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
		
		gridAtividade.add(scrollAtividade, 0, 3);
		
	}

	private void initEventResposivel() {
		
		gridFiltro.widthProperty().addListener((x) -> {
			
			double total = gridAtividade.getWidth() - lblFiltro.getMinWidth();
			
			cbStatus.setPrefWidth(total * 0.33);
			cbTurma.setPrefWidth(total * 0.33);
			cbDisciplina.setPrefWidth(total * 0.33);
			
			
			
		});
		
		
		
	}

	private void initComboBoxFiltro() {
		cbStatus = new ComboBox<>();
		cbTurma = new ComboBox<>();
		cbDisciplina = new ComboBox<>();

		cbStatus.getItems().addAll("Aberto","Fechado");
		
		gridFiltro.add(cbStatus, 1, 1);
		gridFiltro.add(cbTurma, 2, 1);
		gridFiltro.add(cbDisciplina, 3, 1);
		
	}

	private void initLabelFiltro() {
		lblFiltro = new Label("Filtrar por:");
		lblStatus = new Label("Status");
		lblDisciplina = new Label("Disciplina");
		lblTurma = new Label("Turma");
		
		lblFiltro.setTextFill(Color.WHITE);
		lblStatus.setTextFill(Color.WHITE);
		lblDisciplina.setTextFill(Color.WHITE);
		lblTurma.setTextFill(Color.WHITE);
		
		lblFiltro.setAlignment(Pos.CENTER_LEFT);

		lblFiltro.setMinWidth(140);

		lblFiltro.setPadding(new Insets(-25, 0, 0, 0));
		
		Util.setFontePadrao(new Label[] {lblFiltro}, 20, FontWeight.BOLD);
		Util.setFontePadrao(new Label[] {lblStatus, lblDisciplina,lblTurma}, 15, FontWeight.NORMAL);
		
		gridFiltro.add(lblFiltro, 0, 1);
		gridFiltro.add(lblStatus, 1, 0);
		gridFiltro.add(lblTurma, 2, 0);
		gridFiltro.add(lblDisciplina, 3, 0);
		
		gridFiltro.setHalignment(lblDisciplina, HPos.CENTER);
		gridFiltro.setHalignment(lblStatus, HPos.CENTER);
		gridFiltro.setHalignment(lblTurma, HPos.CENTER);
		
	}

	private void initGridFiltro() {
		
		gridFiltro = new GridPane();
		gridFiltro.setPrefWidth(2000);
		gridFiltro.setHgap(20);
		gridFiltro.setVgap(5);
		gridFiltro.setPadding(new Insets(10,20,10,20));

		gridFiltro.setStyle("-fx-background-color: #707070 ; -fx-background-radius: 20px; -fx-border-radius: 20px;");
		
		gridAtividade.add(gridFiltro, 0, 2);
		
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

		borderPrincipal.setCenter(gridAtividade);
		borderPrincipal.setMargin(gridAtividade, new Insets(-30, 15, 0, 15));

	}

	public BorderPane getBorderPrincipal() {
		return borderPrincipal;
	}
	
	public void clearCardAtvidade() {

		vboxAtividade.getChildren().clear();
	}

	
	public void addCardAtividade(CardTarefaHorizontal card) {
		
		initEventCard(card);

		vboxAtividade.getChildren().add(card.getCard());
	}

	private void initEventCard(CardTarefaHorizontal card) {
		
		gridFiltro.widthProperty().addListener((x) -> {
			
			if(borderPrincipal.getWidth() > 830) {
				card.getHboxPrincipal().setMinWidth(gridFiltro.getWidth()-10);
			}else {
				card.getHboxPrincipal().setMinWidth(830);
			}
			
		});
		
		card.getHboxPrincipal().setMinWidth(gridFiltro.getWidth()-10);
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public ComboBox<String> getCbDisciplina() {
		return cbDisciplina;
	}

	public ComboBox<String> getCbTurma() {
		return cbTurma;
	}
	

	
	
	
}