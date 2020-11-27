package view.aluno;

import controller.aluno.DetalhesDisciplinaController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import model.Aluno;
import model.AtividadeEntrega;
import model.Disciplina;
import view.Util;

public class DetalhesDisciplina {
	
	private BorderPane borderPrincipal;

	private GridPane gridAtividade;

	private Label lblAtividade;

	private BorderPane borderInterno;
	
	private Label lblTitulo;

	private GridPane gridCentral;
	
	private Aluno aluno;
	
	private Disciplina disciplina;
	
    private DetalhesDisciplinaController controller;

	public DetalhesDisciplina(BorderPane pane, Disciplina disciplina, Aluno aluno) {

		this.borderPrincipal = pane;
		this.disciplina = disciplina;
		this.aluno = aluno;

		clearBorderPrincipal();

		initAtividade();

	}

	private void clearBorderPrincipal() {

		borderPrincipal.getChildren().clear();

	}

	private void initAtividade() {

		initGridAtividade();
		initLabelAtividade();
		initBorderInterno();
		initCentroCard(disciplina.getNome());
		initGridCentral();
		controller = new DetalhesDisciplinaController(this);

	}

	private void initGridCentral() {
		gridCentral = new GridPane();

		gridCentral.setAlignment(Pos.CENTER_LEFT);
		gridCentral.setPadding(new Insets(20, 20, 20, 20));

		borderInterno.setCenter(gridCentral);

	}


	private void initBorderInterno() {

		borderInterno = new BorderPane();
		borderInterno.setPrefSize(2000, 2000);

		borderInterno.setPadding(new Insets(15));

		borderInterno.setStyle("-fx-background-color:"+ disciplina.getCor() +"; -fx-background-radius: 20px; -fx-border-radius: 20px;");

		gridAtividade.add(borderInterno, 0, 1);
		gridAtividade.setAlignment(Pos.CENTER);
	}

	private void initGridAtividade() {

		gridAtividade = new GridPane();

		gridAtividade.setPrefSize(2000, 2000);

		gridAtividade.setPadding(new Insets(0, 0, 5, 20));

		borderPrincipal.setCenter(gridAtividade);
		borderPrincipal.setMargin(gridAtividade, new Insets(-30, 15, 0, 15));

	}

	private void initLabelAtividade() {

		lblAtividade = new Label("Detalhes Disciplina");

		lblAtividade.setStyle("-fx-font-weight: bolder; -fx-font-family: Poppins; " + "-fx-font-size: " + 30 + " ;");

		gridAtividade.add(lblAtividade, 0, 0);

		gridAtividade.setMargin(lblAtividade, new Insets(0, 0, 20, 0));

	}

	private void initCentroCard(String titulo) {

		lblTitulo = new Label(titulo);
		lblTitulo.setPadding(new Insets(0, 10, 0, 10));
		lblTitulo.setTextFill(Color.web("#000000", 0.5));
		lblTitulo.setAlignment(Pos.CENTER);


		Util.setFontePadrao(new Label[] { lblTitulo }, 25, FontWeight.BOLD);
		
		lblTitulo.setStyle(lblTitulo.getStyle() + "-fx-background-color: rgba(0, 0, 0, 0.2); -fx-background-radius: 10px; -fx-border-radius: 10px;");
		
		borderInterno.setTop(lblTitulo);
		borderInterno.setAlignment(lblTitulo, Pos.CENTER);
		

	}
	
	public void setTable(TableView<AtividadeEntrega> table) {
		
		borderInterno.setCenter(table);		
	}

	public Aluno getAluno() {
		return aluno;
	}


	public Disciplina getDisciplina() {
		return disciplina;
	}

	public BorderPane getBorderPrincipal() {
		return borderPrincipal;
	}
	
	public BorderPane getBorderInterno() {
		return borderInterno;
	}


	
	
	
}
