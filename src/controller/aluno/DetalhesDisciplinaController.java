package controller.aluno;

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
import model.AtividadeEntrega;
import model.Entrega;
import persistence.EntregaDao;
import view.Util;
import view.aluno.DetalhesDisciplina;

public class DetalhesDisciplinaController {

	private DetalhesDisciplina viewDetalhesDisciplina;

	private TableView<AtividadeEntrega> table;

	private TableColumn<AtividadeEntrega, String> colunaAtividade;

	private TableColumn<AtividadeEntrega, String> colunaDataEntrega;

	private TableColumn<AtividadeEntrega, String> colunaArquivo;

	private TableColumn<AtividadeEntrega, String> colunaDataTermino;

	private TableColumn<AtividadeEntrega, Double> colunaNota;

	private Label lblAVG;

	private TextField tfAVG;

	private Label lblSoma;

	private TextField tfSoma;

	private Label lblMax;

	private TextField tfMax;

	private Label lblMin;

	private TextField tfMin;
	
	private EntregaDao dao;

	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	private ObservableList<AtividadeEntrega> dados = FXCollections.observableArrayList();

	private List<Entrega> entregas;

	public DetalhesDisciplinaController(DetalhesDisciplina viewDetalhesDisciplina) {
		this.viewDetalhesDisciplina = viewDetalhesDisciplina;

		initTable();
	}

	private void initTable() {

		table = new TableView<>();

		Util.setFontePadrao(new TableView[] { table }, 15, FontWeight.NORMAL);

		colunaAtividade = new TableColumn<>("Atividade");
		colunaAtividade.setCellValueFactory(new PropertyValueFactory<>("atividade"));
		colunaDataEntrega = new TableColumn<>("Data Entrega");
		colunaDataEntrega.setCellValueFactory(new PropertyValueFactory<>("dtEntrega"));
		colunaArquivo = new TableColumn<>("Arquivo");
		colunaArquivo.setCellValueFactory(new PropertyValueFactory<>("Arquivo"));
		colunaDataTermino = new TableColumn<>("Data Termino");
		colunaDataTermino.setCellValueFactory(new PropertyValueFactory<>("dtTermino"));
		colunaNota = new TableColumn<>("Nota");
		colunaNota.setCellValueFactory(new PropertyValueFactory<>("nota"));

		table.getColumns().addAll(colunaAtividade, colunaDataEntrega, colunaArquivo, colunaDataTermino, colunaNota);

		colunaAtividade.setPrefWidth(table.getWidth() * 0.23);
		colunaDataEntrega.setPrefWidth(table.getWidth() * 0.23);
		colunaArquivo.setPrefWidth(table.getWidth() * 0.23);
		colunaDataTermino.setPrefWidth(table.getWidth() * 0.23);
		colunaNota.setPrefWidth(table.getWidth() * 0.08);

		table.widthProperty().addListener((x) -> {
			redimensionarColunas();
		});

		viewDetalhesDisciplina.setTable(table);

		viewDetalhesDisciplina.getBorderInterno().setMargin(table, new Insets(30, 70, 10, 70));

		initAVG();

		initCount();

		initMax();

		initMin();
		
		try {
			dao = new EntregaDao();
			
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

			tfMin.setText(dao.selectMin(viewDetalhesDisciplina.getAluno(), viewDetalhesDisciplina.getDisciplina()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void calcularMax() {
		try {

			tfMax.setText(dao.selectMax(viewDetalhesDisciplina.getAluno(), viewDetalhesDisciplina.getDisciplina()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initMin() {

		lblMin = new Label("Nota mínima: ");

		tfMin = new TextField();

		viewDetalhesDisciplina.initMin(lblMin, tfMin);

	}

	private void initMax() {
		lblMax = new Label("Nota máxima: ");

		tfMax = new TextField();

		viewDetalhesDisciplina.initMax(lblMax, tfMax);

	}

	private void calcularCount() {

		try {
			
			tfSoma.setText(dao.selectCount(viewDetalhesDisciplina.getAluno(), viewDetalhesDisciplina.getDisciplina()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void calcularAVG() {

		try {

			tfAVG.setText(dao.selectAVG(viewDetalhesDisciplina.getAluno(), viewDetalhesDisciplina.getDisciplina()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initAVG() {

		lblAVG = new Label("Média: ");

		tfAVG = new TextField();

		viewDetalhesDisciplina.initAVG(lblAVG, tfAVG);

	}

	private void initCount() {

		lblSoma = new Label("Total de Tarefas: ");

		tfSoma = new TextField();

		viewDetalhesDisciplina.initCount(lblSoma, tfSoma);

	}

	private void redimensionarColunas() {

		colunaAtividade.setPrefWidth(table.getWidth() * 0.23);
		colunaDataEntrega.setPrefWidth(table.getWidth() * 0.23);
		colunaArquivo.setPrefWidth(table.getWidth() * 0.23);
		colunaDataTermino.setPrefWidth(table.getWidth() * 0.23);
		colunaNota.setPrefWidth(table.getWidth() * 0.08);

	}

	public void buscarElementos() {

		try {

			entregas = dao.findEntregaAtividade(viewDetalhesDisciplina.getDisciplina(),
					viewDetalhesDisciplina.getAluno());

			if (entregas.size() > 0) {
				preencherTabela();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void preencherTabela() {

		for (Entrega entrega : entregas) {

			AtividadeEntrega ae = new AtividadeEntrega(entrega.getAtividade().getNome(),
					format.format(entrega.getAtividade().getDtEntrega()), new File(entrega.getPathArquivos()).getName(),
					format.format(entrega.getDtEntrega()), entrega.getNota());

			System.out.println(ae.toString());

			table.getItems().add(ae);

		}

	}

}
