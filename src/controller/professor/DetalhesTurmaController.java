package controller.professor;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.FontWeight;
import model.AlunoEntregaTotalizado;
import model.AtividadeEntrega;
import model.Entrega;
import persistence.AlunoDao;
import persistence.EntregaDao;
import view.Util;
import view.professor.DetalhesTurmaProfessor;

public class DetalhesTurmaController {

	private DetalhesTurmaProfessor viewDetalhesTurmaProfessor;

	private TableView<AlunoEntregaTotalizado> table;

	private TableColumn<AlunoEntregaTotalizado, String> colunaAluno;

	private TableColumn<AlunoEntregaTotalizado, String> colunaEmail;

	private TableColumn<AlunoEntregaTotalizado, Double> colunaNota;

	private Label lblAVG;

	private TextField tfAVG;

	private Label lblSoma;

	private TextField tfSoma;

	private Label lblMax;

	private TextField tfMax;

	private Label lblMin;

	private TextField tfMin;
	
	private AlunoDao dao;

	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	private ObservableList<AlunoEntregaTotalizado> dados = FXCollections.observableArrayList();

	private List<AlunoEntregaTotalizado> alunoEntregas;

	public DetalhesTurmaController(DetalhesTurmaProfessor viewDetalhesTurmaProfessor) {
		this.viewDetalhesTurmaProfessor = viewDetalhesTurmaProfessor;

		initTable();
	}

	private void initTable() {

		table = new TableView<>();

		Util.setFontePadrao(new TableView[] { table }, 15, FontWeight.NORMAL);

		colunaAluno = new TableColumn<>("Aluno");
		colunaAluno.setCellValueFactory(new PropertyValueFactory<>("aluno"));
		colunaEmail = new TableColumn<>("Email");
		colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colunaNota = new TableColumn<>("Nota");
		colunaNota.setCellValueFactory(new PropertyValueFactory<>("Nota"));

		table.getColumns().addAll(colunaAluno, colunaEmail, colunaNota);

		colunaAluno.setPrefWidth(table.getWidth() * 0.40);
		colunaEmail.setPrefWidth(table.getWidth() * 0.50);
		colunaNota.setPrefWidth(table.getWidth() * 0.10);

		table.widthProperty().addListener((x) -> {
			redimensionarColunas();
		});

		viewDetalhesTurmaProfessor.setTable(table);

		viewDetalhesTurmaProfessor.getBorderInterno().setMargin(table, new Insets(30, 70, 10, 70));

		initAVG();

		initCount();

		initMax();

		initMin();
		
		try {
			dao = new AlunoDao();
			
			buscarElementos();

			calcularAVG();

			calcularCount();

			calcularMax();

			calcularMin();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erro ao tentar se conectar com o Banco");
		}

		

	}

	private void calcularMin() {
		try {

			tfMin.setText(dao.selectMin(viewDetalhesTurmaProfessor.getTurma(), viewDetalhesTurmaProfessor.getDisciplina()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void calcularMax() {
		try {

			tfMax.setText(dao.selectMax(viewDetalhesTurmaProfessor.getTurma(), viewDetalhesTurmaProfessor.getDisciplina()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initMin() {

		lblMin = new Label("Nota mínima: ");

		tfMin = new TextField();

		viewDetalhesTurmaProfessor.initMin(lblMin, tfMin);

	}

	private void initMax() {
		lblMax = new Label("Nota máxima: ");

		tfMax = new TextField();

		viewDetalhesTurmaProfessor.initMax(lblMax, tfMax);

	}

	private void calcularCount() {

		try {
			
			tfSoma.setText(dao.selectCount(viewDetalhesTurmaProfessor.getTurma(), viewDetalhesTurmaProfessor.getDisciplina()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void calcularAVG() {

		try {

			tfAVG.setText(dao.selectAVG(viewDetalhesTurmaProfessor.getTurma(), viewDetalhesTurmaProfessor.getDisciplina()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initAVG() {

		lblAVG = new Label("Média: ");

		tfAVG = new TextField();

		viewDetalhesTurmaProfessor.initAVG(lblAVG, tfAVG);

	}

	private void initCount() {

		lblSoma = new Label("Total de Alunos: ");

		tfSoma = new TextField();

		viewDetalhesTurmaProfessor.initCount(lblSoma, tfSoma);

	}

	private void redimensionarColunas() {

		colunaAluno.setPrefWidth(table.getWidth() * 0.40);
		colunaEmail.setPrefWidth(table.getWidth() * 0.50);
		colunaNota.setPrefWidth(table.getWidth() * 0.10);

	}

	public void buscarElementos() {

		try {

			alunoEntregas = dao.findAllAlunoEntrega(viewDetalhesTurmaProfessor.getTurma(), viewDetalhesTurmaProfessor.getDisciplina());

			if (alunoEntregas.size() > 0) {
				preencherTabela();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void preencherTabela() {

		for (AlunoEntregaTotalizado alunoEntrega : alunoEntregas) {

			AlunoEntregaTotalizado aet = new AlunoEntregaTotalizado(alunoEntrega.getAluno(), alunoEntrega.getEmail(), alunoEntrega.getNota());

			System.out.println(aet.toString());

			table.getItems().add(aet);

		}

	}
}
