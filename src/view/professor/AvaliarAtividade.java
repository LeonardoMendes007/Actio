package view.professor;

import controller.professor.AvaliarAtividadeController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import model.Aluno;
import model.Atividade;
import view.Util;

public class AvaliarAtividade {

	private BorderPane borderPrincipal;

	private GridPane gridPrincipal;

	private Label lblNomeAtividade;

	private Label lblNomeAluno;

	private BorderPane borderInterno;

	private GridPane gridInterno;

	private Label lblArquivosEnviados;

	private Label lblComentarios;

	private Label lblNota;

	private TextArea taComentarios;

	private TextField tfNota;

	private Button btEnviar;

	private GridPane gridNotas;

	private Button btBaixarArquivo;

	private Aluno aluno;

	private Atividade atividade;
	
	private AvaliarAtividadeController controller;

	public AvaliarAtividade(BorderPane borderPrincipal, Aluno aluno, Atividade atividade) {

		this.borderPrincipal = borderPrincipal;
		this.aluno = aluno;
		this.atividade = atividade;

		initTela();
	}

	private void initTela() {

		initPrepararTela();

		initLabelAtividade();

		initLabelNomeAluno();

		initBorderInterno();

		initGridInterno();

		initLabelArquivos();

		initLabelComentarios();

		initTextAreaComentarios();

		initTableArquivos();

		initGridNotas();

		initLabelNota();

		initTextFieldNota();

		initButtonEnviar();

		Util.setFontePadrao(new Label[] { lblArquivosEnviados, lblComentarios, lblNota }, 21, FontWeight.BOLD);
	
		controller = new AvaliarAtividadeController(this,aluno,atividade);
	}

	private void initTextFieldNota() {
		tfNota = new TextField();

		tfNota.setPrefWidth(borderInterno.getWidth() * 0.27);
		tfNota.setPrefHeight(borderInterno.getHeight() * 0.14);

		Util.setFontePadrao(new TextField[] { tfNota }, 21, FontWeight.BOLD);

		tfNota.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() > oldValue.intValue()) {
					char ch = tfNota.getText().charAt(oldValue.intValue());
					if (!(ch >= '0' && ch <= '9')) {
						tfNota.setText(tfNota.getText().substring(0, tfNota.getText().length() - 1));
					}
				}
			}
		});

		borderInterno.widthProperty().addListener((x) -> tfNota.setPrefWidth(borderInterno.getWidth() * 0.20));
		borderInterno.heightProperty().addListener((x) -> tfNota.setPrefHeight(borderInterno.getHeight() * 0.14));

		gridNotas.add(tfNota, 0, 1);

	}

	private void initLabelNota() {

		lblNota = new Label("Nota");

		gridNotas.add(lblNota, 0, 0);
		gridNotas.setMargin(lblNota, new Insets(15, 0, 5, 0));

	}

	private void initGridNotas() {

		gridNotas = new GridPane();

		borderInterno.setRight(gridNotas);

	}

	private void initButtonEnviar() {

		btEnviar = new Button("Enviar");

		btEnviar.setPrefWidth(180);

		btEnviar.setTextFill(Color.WHITE);

		Util.setFontePadrao(new Button[] { btEnviar }, 27, FontWeight.BOLD);

		btEnviar.setStyle(btEnviar.getStyle()
				+ "-fx-background-color: #1D5959; -fx-background-radius: 10px; -fx-border-radius: 10px;");

		borderInterno.setBottom(btEnviar);
		borderInterno.setAlignment(btEnviar, Pos.BOTTOM_RIGHT);
	}

	private void initTableArquivos() {

		btBaixarArquivo = new Button("Baixar Arquivo");

		Util.hoverFade(btBaixarArquivo);
		Util.hoverSize(btBaixarArquivo);
		Util.setFontePadrao(new Button[] { btBaixarArquivo }, 23, FontWeight.BOLD);

		btBaixarArquivo.setStyle(btBaixarArquivo.getStyle() + " -fx-background-color: #1D5959;");

		btBaixarArquivo.setPrefWidth(300);
		btBaixarArquivo.setPrefHeight(40);
		
		btBaixarArquivo.setOnMouseClicked((x) -> controller.baixarArquivoEntrega());

		gridInterno.add(btBaixarArquivo, 0, 1);
	}

	private void initTextAreaComentarios() {

		taComentarios = new TextArea();

		taComentarios.setPrefWidth(borderInterno.getWidth() * 0.65);
		taComentarios.setPrefHeight(borderInterno.getHeight() * 0.50);

		Util.setFontePadrao(new TextArea[] { taComentarios }, 17, FontWeight.NORMAL);

		borderInterno.widthProperty().addListener((x) -> taComentarios.setPrefWidth(borderInterno.getWidth() * 0.70));
		borderInterno.heightProperty()
				.addListener((x) -> taComentarios.setPrefHeight(borderInterno.getHeight() * 0.50));

		gridInterno.add(taComentarios, 0, 3);

	}

	private void initLabelComentarios() {

		lblComentarios = new Label("Comentários");

		gridInterno.add(lblComentarios, 0, 2);
		gridInterno.setMargin(lblComentarios, new Insets(15, 0, 5, 0));

	}

	private void initLabelArquivos() {

		lblArquivosEnviados = new Label("Arquivos Enviados");

		gridInterno.add(lblArquivosEnviados, 0, 0);
		gridInterno.setMargin(lblArquivosEnviados, new Insets(15, 0, 5, 0));
	}

	private void initGridInterno() {

		gridInterno = new GridPane();

		borderInterno.setCenter(gridInterno);

	}

	private void initBorderInterno() {

		borderInterno = new BorderPane();

		borderInterno.setStyle("-fx-background-color: #BEBDBD; -fx-background-radius: 10px; -fx-border-radius: 10px;");

		borderInterno.setPadding(new Insets(10));

		borderInterno.setPrefSize(2000, 2000);

		gridPrincipal.add(borderInterno, 0, 3);

	}

	private void initLabelNomeAluno() {

		lblNomeAluno = new Label(aluno.getNome());

		Util.setFontePadrao(new Label[] { lblNomeAluno }, 25, FontWeight.BOLD);

		gridPrincipal.add(lblNomeAluno, 0, 2);

	}

	private void initLabelAtividade() {

		lblNomeAtividade = new Label(atividade.getNome());

		Util.setFontePadrao(new Label[] { lblNomeAtividade }, 20, FontWeight.NORMAL);

		gridPrincipal.add(lblNomeAtividade, 0, 1);

	}

	private void initPrepararTela() {
		gridPrincipal = (GridPane) borderPrincipal.getChildren().get(0);

		gridPrincipal = (GridPane) gridPrincipal.getChildren().get(1);

		gridPrincipal.getChildren().clear();

		gridPrincipal.setStyle(gridPrincipal.getStyle());
	}
}
