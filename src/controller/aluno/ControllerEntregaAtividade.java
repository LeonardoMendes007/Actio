package controller.aluno;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import model.Aluno;
import model.Atividade;
import model.Entrega;
import persistence.EntregaDao;
import view.aluno.EntregaAtividadeAluno;

public class ControllerEntregaAtividade {

	private EntregaAtividadeAluno viewEntregaAtividade;
	
	private Entrega entrega;
	
	private Aluno aluno;
	
	private Atividade atividade;

	public ControllerEntregaAtividade(EntregaAtividadeAluno viewEntregaAtividade,Atividade atividade, Aluno aluno) {

		this.viewEntregaAtividade = viewEntregaAtividade;
		
		this.aluno = aluno;
		
		this.atividade = atividade;
		
		verificarEnvio();

	}

	private void verificarEnvio() {
		
		
		try {
			EntregaDao dao = new EntregaDao();
			
			entrega = dao.findEntregaAtividadeAluno(atividade, aluno);
			
			if(entrega != null) {
				
				File file = new File(entrega.getPathArquivos());
				
				viewEntregaAtividade.getLblEntrega().setText(file.getName());
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	public void entregarTarefa() {
		
		if(entrega == null) {
			inserirNoBanco();
		}else {
			updateNoBanco();
		}

		

	}

	private void updateNoBanco() {
		try {
			EntregaDao dao = new EntregaDao();

			entrega = new Entrega();
			
			entrega.setPathArquivos("tmp//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getDisciplina().getId() + "//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getId() + "//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getPeriodo() + "//" + viewEntregaAtividade.getAluno().getId() + "//" + viewEntregaAtividade.getFile().getName());
			
			dao.update(entrega);
			
			moverArquivo();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void inserirNoBanco() {
		try {
			EntregaDao dao = new EntregaDao();

			entrega = new Entrega();
			
			entrega.setAtividade(viewEntregaAtividade.getAtividade());
			entrega.setAluno(viewEntregaAtividade.getAluno());
			entrega.setPathArquivos("tmp//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getDisciplina().getId() + "//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getId() + "//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getPeriodo() + "//" + viewEntregaAtividade.getAluno().getId() + "//" + viewEntregaAtividade.getFile().getName());
			
			dao.insert(entrega);
			
			moverArquivo();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void moverArquivo() {

		

		try {
			
			File source = new File(viewEntregaAtividade.getFile().getAbsolutePath());
			
			File dest = new File("tmp//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getDisciplina().getId() + "//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getId() + "//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getPeriodo() + "//" + viewEntregaAtividade.getAluno().getId());
			
			if(dest.exists()) {
				FileUtils.forceDelete(dest);
			}
			
			dest = new File("tmp//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getDisciplina().getId() + "//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getId() + "//" + viewEntregaAtividade.getAtividade().getDiscTurmaProf().getTurma().getPeriodo() + "//" + viewEntregaAtividade.getAluno().getId());
			
			
		    FileUtils.copyFileToDirectory(source, dest);
		    
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}

		
	}

	public void baixarArquivoAtividade() {

		try {
			Desktop.getDesktop().open(new File(viewEntregaAtividade.getAtividade().getPathArquivo()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
