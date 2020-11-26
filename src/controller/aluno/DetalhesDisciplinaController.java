package controller.aluno;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	private ObservableList<AtividadeEntrega> dados = FXCollections.observableArrayList();

	private List<Entrega> entregas;

	public DetalhesDisciplinaController(DetalhesDisciplina viewDetalhesDisciplina) {
		this.viewDetalhesDisciplina = viewDetalhesDisciplina;

		initTable();
	}

	private void initTable() {

		table = new TableView<>();
		
		Util.setFontePadrao(new TableView[] {table},  15, FontWeight.NORMAL);


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

		viewDetalhesDisciplina.getBorderInterno().setMargin(table, new Insets(65, 80, 65, 80));

		buscarElementos();

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
			EntregaDao dao = new EntregaDao();

			entregas = dao.findEntregaAtividade(viewDetalhesDisciplina.getDisciplina(),
					viewDetalhesDisciplina.getAluno());

			if (entregas.size() > 0) {
				preencherTabela();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
