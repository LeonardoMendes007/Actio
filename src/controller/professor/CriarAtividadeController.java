package controller.professor;

import java.time.ZoneId;
import java.util.Date;

import model.Atividade;
import model.DisciplinaTurmaProfessor;
import view.professor.CriarAtividade;

public class CriarAtividadeController {

	private CriarAtividade viewCriarAtividade;

	public CriarAtividadeController(CriarAtividade viewCriarAtividade) {
		this.viewCriarAtividade = viewCriarAtividade;
	}
	
	public String criarAtividade() {
		
		Atividade atividade = new Atividade();
		
		if(viewCriarAtividade.getTfAtividade().getText().isEmpty()) {
			return "Erro: é necessário um titulo para atividade";
		}
		
		atividade.setNome(viewCriarAtividade.getTfAtividade().getText());
		
		atividade.setDescricao(viewCriarAtividade.getTaDescricao().getText());
		
		atividade.setDtEntrega(Date.from(viewCriarAtividade.getDtEntrega().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
//		DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor(new , , viewCriarAtividade.getProfessor());
		
		System.out.println(atividade.toString());
		
		return null;
	}
	
	
}
