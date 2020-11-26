package controller.aluno;

import java.sql.SQLException;
import java.util.List;
import java.util.Observable;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import model.Entrega;
import persistence.EntregaDao;
import view.aluno.DetalhesDisciplina;

public class DetalhesDisciplinaController {

	private DetalhesDisciplina viewDetalhesDisciplina;

	private TableView<String> table;

	private List<Entrega> entregas;
	
	public DetalhesDisciplinaController(DetalhesDisciplina viewDetalhesDisciplina) {
		this.viewDetalhesDisciplina = viewDetalhesDisciplina;

		initTable();
	}

	private void initTable() {

		table = new TableView<>();

		TableColumn<String, String> colunaAtividade = new TableColumn<>("Atividade");
		TableColumn<String, String> colunaDataEntrega = new TableColumn<>("Data Entrega");
		TableColumn<String, String> colunaArquivo = new TableColumn<>("Arquivo");
		TableColumn<String, String> colunaDataTermino = new TableColumn<>("Data Termino");
		TableColumn<String, String> colunaNota = new TableColumn<>("Nota");

		table.getColumns().addAll(colunaAtividade, colunaDataEntrega, colunaArquivo, colunaDataTermino, colunaNota);

		viewDetalhesDisciplina.setTable(table);

		viewDetalhesDisciplina.getBorderInterno().setMargin(table, new Insets(65, 80, 65, 80));

	}

	public void preencherTabela() {

		try {
			EntregaDao dao = new EntregaDao();

			entregas = dao.findEntregaAtividade(viewDetalhesDisciplina.getDisciplina(),
					viewDetalhesDisciplina.getAluno());
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
