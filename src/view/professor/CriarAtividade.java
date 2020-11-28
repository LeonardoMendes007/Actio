package view.professor;

import java.awt.event.ActionListener;
import java.io.File;

import controller.professor.CriarAtividadeController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Disciplina;
import model.Professor;
import model.Turma;
import view.Util;

public class CriarAtividade {

	private BorderPane borderPrincipal;

	private Label lblCriarAtividade;

	private GridPane gridPrincipal;

	private BorderPane borderInterno;

	private Label lblNome;

	private TextField tfAtividade;

	private Label lblDescricao;

	private TextArea taDescricao;

	private StackPane stackInterno;

	private Button btAgendar;

	private Button btPublicar;

	private GridPane gridInterno;

	private GridPane gridButtons;

	private GridPane gridAgendar;

	private Label lblAgendar;

	private BorderPane borderAgendar;

	private Label lblDataPublicacao;

	private DatePicker dtDataPublicacao;

	private Button btConcluir;

	private Label lblDataEntrega;

	private DatePicker dtEntrega;

	private GridPane gridData;

	private Label lblAtividadeEmGrupo;
	
	private Label lblDisciplina;
	
	private Label lblTurma;
	
	private Label lblEntrega;
	
	private Button btBaixarArquivos;
	
	private Button btAdicionar;
	
	private Button btRemover;
	
	private FileChooser chooser = new FileChooser();
	
	private File file;
	
	private HBox hboxButtons;

	private ComboBox<String> cbGrupo;
	
	private ComboBox<String> cbDisciplina;
	
	private ComboBox<String> cbTurma;

	private CriarAtividadeController controller = new CriarAtividadeController(this);

	private Professor professor;

	public CriarAtividade(BorderPane borderPrincipal, Professor professor) {
		this.borderPrincipal = borderPrincipal;
		this.professor = professor;

		initTela();
	}

	private void initTela() {

		borderPrincipal.getChildren().clear();

		initGrid();

		initLabelCriarAtividade();

		initBorderInterno();

		initStackPaneInterno();

		initGridInterno();

		initLabels();

		initTextField();

		initTextArea();

		initGridButtons();

		initButtonAgendar();

		initLabelDataEntrega();

		initTextFieldDataEntrega();

		initButtonPublicar();

		initGridAgendar();

		initLabelAgendar();

		initBorderAgendar();

		initAtividadeEmGrupo();
		
		initDisciplinaAtividade();
		
		initTurmaAtividade();

		initLabelEntrega();
		
		initAddArquivo();
		
		initGridData();

		initLabelDataPublicacao();

		initTextFieldDataPublicacao();

		initButtonConcluir();

	}

	
	private void initAddArquivo() {

		btAdicionar = new Button("Adicionar");
		btRemover = new Button("Remover");

		Util.setFontePadrao(new Button[] { btAdicionar, btRemover }, 15, FontWeight.BOLD);

		Util.hoverFade(btAdicionar);
		Util.hoverFade(btRemover);
		
		btAdicionar.setStyle(btAdicionar.getStyle() + "-fx-background-color: #91CF2D; -fx-pointer: hand;");
		btRemover.setStyle(btRemover.getStyle() + "-fx-background-color: #F55B51;");

		btAdicionar.setOnMouseClicked((x) -> {

			file = chooser.showOpenDialog(new Stage());

			lblEntrega.setText(file.getName());

		});

		btRemover.setOnMouseClicked((x) -> {

			controller.deletarTarefa();

		});

		initHbox();

		hboxButtons.getChildren().addAll(btRemover, btAdicionar);
		hboxButtons.setMargin(btAdicionar, new Insets(0, 0, 0, 10));

		
	}
	
	private void initHbox() {

		hboxButtons = new HBox();

		hboxButtons.setPadding(new Insets(10, 10, 10, 0));

		gridInterno.add(hboxButtons, 0, 5);
	}
	
	private void initLabelEntrega() {

		lblEntrega = new Label("Clique para adicionar Arquivo");

		Util.setFontePadrao(new Label[] { lblEntrega }, 23, FontWeight.SEMI_BOLD);

		
		gridInterno.add(lblEntrega, 0, 4);
		
		gridInterno.setMargin(lblEntrega, new Insets(15, 0, 15, 0));

	}
	
	private void initTurmaAtividade() {
		lblTurma = new Label("Turma");

		Util.setFontePadrao(new Label[] { lblTurma }, 15, FontWeight.BOLD);

		gridButtons.add(lblTurma, 0, 8);
		gridButtons.setMargin(lblTurma, new Insets(20, 0, 0, 0));
		
		cbTurma = new ComboBox<>();
		
		
				
		gridButtons.add(cbTurma, 0, 9);

	}
	
	private void initDisciplinaAtividade() {
		lblDisciplina = new Label("Disciplina");

		Util.setFontePadrao(new Label[] { lblDisciplina }, 15, FontWeight.BOLD);

		gridButtons.add(lblDisciplina, 0, 6);
		gridButtons.setMargin(lblDisciplina, new Insets(20, 0, 0, 0));
		
		cbDisciplina = new ComboBox<>();
		
		
		controller.addDiscComboBox(getProfessor());


		cbDisciplina.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println(cbDisciplina.getValue());
				Disciplina d = new Disciplina();
				d.setNome(cbDisciplina.getValue());
				controller.addTurmasComboBox(d);	
			}
		});
		
		gridButtons.add(cbDisciplina, 0, 7);

	}
	

	
	private void initAtividadeEmGrupo() {

		lblDataEntrega = new Label("Grupo");

		Util.setFontePadrao(new Label[] { lblDataEntrega }, 15, FontWeight.BOLD);

		gridButtons.add(lblDataEntrega, 0, 4);
		gridButtons.setMargin(lblDataEntrega, new Insets(20, 0, 0, 0));
		
		cbGrupo = new ComboBox<>();
		
		cbGrupo.getItems().addAll("Individual" , " Grupo ");
		
		gridButtons.add(cbGrupo, 0, 5);

	}

	private void initTextFieldDataEntrega() {

		dtEntrega = new DatePicker();
		dtEntrega.setPrefWidth(200);
		dtEntrega.setPrefHeight(30);

		System.out.println(dtEntrega.getValue());
		gridButtons.add(dtEntrega, 0, 3);
	}

	private void initLabelDataEntrega() {

		lblDataEntrega = new Label("Data de Entrega");

		Util.setFontePadrao(new Label[] { lblDataEntrega }, 15, FontWeight.BOLD);

		gridButtons.add(lblDataEntrega, 0, 2);
		gridButtons.setMargin(lblDataEntrega, new Insets(20, 0, 0, 0));

	}

	private void initButtonConcluir() {

		btConcluir = new Button("Concluir");

		btConcluir.setTextFill(Color.WHITE);

		Util.setFontePadrao(new Button[] { btConcluir }, 20, FontWeight.BOLD);

		Util.hoverFade(btConcluir);

		btConcluir.setStyle(btConcluir.getStyle()
				+ "-fx-background-color: #57BEBE; -fx-background-radius: 10px; -fx-border-radius: 10px;");

		borderAgendar.setBottom(btConcluir);
		borderAgendar.setAlignment(btConcluir, Pos.CENTER);
		borderAgendar.setMargin(btConcluir, new Insets(10));

		btConcluir.setOnMouseClicked((x) -> controller.addDataAtividade());

	}

	private void mostrarCriarAtividades() {

		borderInterno.setCenter(stackInterno);
		borderInterno.setRight(gridButtons);

	}

	private void mostrarAgendar() {

		borderInterno.getChildren().clear();

		borderInterno.setCenter(gridAgendar);

	}

	private void initGridData() {

		gridData = new GridPane();

		borderAgendar.setCenter(gridData);
		borderAgendar.setMargin(gridData, new Insets(25, 20, 20, 26));

		borderAgendar.setAlignment(gridData, Pos.CENTER);
	}

	private void initTextFieldDataPublicacao() {

		dtDataPublicacao = new DatePicker();
		dtDataPublicacao.setPrefHeight(40);
		dtDataPublicacao.setPrefWidth(220);

		gridData.add(dtDataPublicacao, 0, 1);
		gridData.setAlignment(Pos.CENTER);
	}

	private void initLabelDataPublicacao() {

		lblDataPublicacao = new Label("Data de Publicacao");
		lblDataPublicacao.setTextFill(Color.GRAY);

		Util.setFontePadrao(new Label[] { lblDataPublicacao }, 22, FontWeight.BOLD);

		gridData.add(lblDataPublicacao, 0, 0);

	}

	private void initBorderAgendar() {

		borderAgendar = new BorderPane();
		borderAgendar.setPrefSize(300, 200);

		borderAgendar.setStyle("-fx-background-color: #D7D7D7; -fx-background-radius: 20px; -fx-border-radius: 20px;");

		gridAgendar.add(borderAgendar, 0, 1);
		gridAgendar.setPadding(new Insets(15));
	}

	private void initLabelAgendar() {

		lblAgendar = new Label("Agendar");

		lblAgendar.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + 25 + " ;");

		gridAgendar.add(lblAgendar, 0, 0);

		gridAgendar.setMargin(lblAgendar, new Insets(0, 0, 10, 0));

	}

	private void initGridAgendar() {

		gridAgendar = new GridPane();
		gridAgendar.setMinSize(400, 400);

		gridAgendar.setAlignment(Pos.CENTER);

		gridAgendar.setStyle("-fx-background-color: #8E8D8D;-fx-background-radius: 20px; -fx-border-radius: 20px;");
	}

	private void initGridButtons() {

		gridButtons = new GridPane();

		gridButtons.setPadding(new Insets(10));

		borderInterno.setRight(gridButtons);
		borderInterno.setAlignment(gridButtons, Pos.BOTTOM_CENTER);

	}

	private void initButtonPublicar() {

		btPublicar = new Button("Publicar");

		btPublicar.setPrefWidth(borderInterno.getWidth() * 0.23);

		borderInterno.widthProperty().addListener((x) -> btPublicar.setPrefWidth(borderInterno.getWidth() * 0.23));

		btPublicar.setTextFill(Color.WHITE);

		Util.setFontePadrao(new Button[] { btPublicar }, 21, FontWeight.BOLD);
		btPublicar.setStyle(btPublicar.getStyle()
				+ "-fx-background-color: #1D5959; -fx-background-radius: 10px; -fx-border-radius: 10px;");

		Util.hoverFade(btPublicar);

		btPublicar.setOnMouseClicked((x) -> controller.criarAtividade());

		gridButtons.add(btPublicar, 0, 1);
		gridButtons.setMargin(btPublicar, new Insets(8, 0, 0, 0));
	}

	private void initButtonAgendar() {

		btAgendar = new Button("Agendar");

		btAgendar.setPrefWidth(borderInterno.getWidth() * 0.23);

		

		borderInterno.widthProperty().addListener((x) -> btAgendar.setPrefWidth(borderInterno.getWidth() * 0.23));

		btAgendar.setTextFill(Color.WHITE);

		Util.setFontePadrao(new Button[] { btAgendar }, 21, FontWeight.BOLD);
		btAgendar.setStyle(btAgendar.getStyle()
				+ "-fx-background-color: #57BEBE; -fx-background-radius: 10px; -fx-border-radius: 10px;");

		Util.hoverFade(btAgendar);

		gridButtons.add(btAgendar, 0, 0);
		
		btAgendar.setOnMouseClicked((x) -> mostrarAgendar());
	}

	private void initTextArea() {

		taDescricao = new TextArea();

		taDescricao.setPrefWidth(borderInterno.getWidth() * 0.70);
		taDescricao.setPrefHeight(borderInterno.getHeight() * 0.60);

		borderInterno.widthProperty().addListener((x) -> taDescricao.setPrefWidth(borderInterno.getWidth() * 0.70));
		borderInterno.heightProperty().addListener((x) -> taDescricao.setPrefHeight(borderInterno.getHeight() * 0.60));

		gridInterno.add(taDescricao, 0, 3);

	}

	private void initTextField() {

		tfAtividade = new TextField();

		tfAtividade.setPrefHeight(borderInterno.getHeight() * 0.10);
		tfAtividade.setPrefWidth(borderInterno.getWidth() * 0.70);

		borderInterno.widthProperty().addListener((x) -> tfAtividade.setPrefWidth(borderInterno.getWidth() * 0.70));
		borderInterno.heightProperty().addListener((x) -> tfAtividade.setPrefHeight(borderInterno.getHeight() * 0.10));

		gridInterno.add(tfAtividade, 0, 1);

	}

	private void initGridInterno() {

		gridInterno = new GridPane();

		gridInterno.setVgap(15);

		stackInterno.getChildren().add(gridInterno);

	}

	private void initLabels() {

		lblNome = new Label("Nome de Atividade");
		lblDescricao = new Label("Descrição da Atividade");

		lblNome.setTextFill(Color.GRAY);
		lblDescricao.setTextFill(Color.GRAY);

		Util.setFontePadrao(new Label[] { lblNome, lblDescricao }, 15, FontWeight.BOLD);

		gridInterno.add(lblNome, 0, 0);
		gridInterno.add(lblDescricao, 0, 2);

	}

	private void initStackPaneInterno() {

		stackInterno = new StackPane();
		stackInterno.setPadding(new Insets(40));

		borderInterno.setCenter(stackInterno);
	}

	private void initBorderInterno() {

		borderInterno = new BorderPane();

		borderInterno.setPrefSize(2000, 2000);

		borderInterno.setStyle("-fx-background-color: #D7D7D7; -fx-background-radius: 20px; -fx-border-radius: 20px;");

		gridPrincipal.add(borderInterno, 0, 1);
	}

	private void initLabelCriarAtividade() {

		lblCriarAtividade = new Label("Criar Atividade");

		lblCriarAtividade
				.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + 30 + " ;");

		gridPrincipal.add(lblCriarAtividade, 0, 0);

		gridPrincipal.setMargin(lblCriarAtividade, new Insets(0, 0, 10, 0));

	}

	private void initGrid() {

		gridPrincipal = new GridPane();

		gridPrincipal.setPrefSize(2000, 2000);

		gridPrincipal.setPadding(new Insets(0, 0, 5, 20));

		borderPrincipal.setCenter(gridPrincipal);
		borderPrincipal.setMargin(gridPrincipal, new Insets(-30, 15, 0, 15));
	}

	public Professor getProfessor() {
		return professor;
	}

	public TextField getTfAtividade() {
		return tfAtividade;
	}

	public void setTfAtividade(TextField tfAtividade) {
		this.tfAtividade = tfAtividade;
	}

	public TextArea getTaDescricao() {
		return taDescricao;
	}

	public void setTaDescricao(TextArea taDescricao) {
		this.taDescricao = taDescricao;
	}

	public DatePicker getDtDataPublicacao() {
		return dtDataPublicacao;
	}

	public void setDtDataPublicacao(DatePicker dtDataPublicacao) {
		this.dtDataPublicacao = dtDataPublicacao;
	}

	public DatePicker getDtEntrega() {
		return dtEntrega;
	}

	public void setDtEntrega(DatePicker dtEntrega) {
		this.dtEntrega = dtEntrega;
	}

	public ComboBox<String> getCbGrupo() {
		return cbGrupo;
	}

	public void setCbGrupo(ComboBox<String> cbGrupo) {
		this.cbGrupo = cbGrupo;
	}

	public ComboBox<String> getCbDisciplina() {
		return cbDisciplina;
	}

	public void setCbDisciplina(ComboBox<String> cbDisciplina) {
		this.cbDisciplina = cbDisciplina;
	}

	public ComboBox<String> getCbTurma() {
		return cbTurma;
	}

	public void setCbTurma(ComboBox<String> cbTurma) {
		this.cbTurma = cbTurma;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Label getLblEntrega() {
		return lblEntrega;
	}

	public void setLblEntrega(Label lblEntrega) {
		this.lblEntrega = lblEntrega;
	}
	
	

}
