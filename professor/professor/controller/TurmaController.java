package professor.controller;

import professor.view.Turma;
import professor.view.card.turma.CardTurma;

public class TurmaController {

	private Turma disciplina;
	
	
	public TurmaController(Turma disciplina) {
		this.disciplina = disciplina;
	}
	
	public void addCards() {
		
		 String[][] disciplinas = {
				{"Programa��o Orientada a �dio",  "#FF9DBA"},
				{"L�gica de Programa��o","#9DC4FF"},
				{"Banco de Dados", "#9DFFA1"}, 
			};
			

		
		String[][] turmas = {
				{"4� ADS TARDE", disciplinas[0][0], disciplinas[0][1]}, 
				{"4� ADS NOITE", disciplinas[0][0], disciplinas[0][1]}, 
				{"4� ADS MANH�", disciplinas[0][0], disciplinas[0][1]}, 
				{"5� ADS MANH�", disciplinas[0][0], disciplinas[0][1]}, 
				{"1� ADS TARDE", disciplinas[1][0], disciplinas[1][1]}, 
				{"1� ADS TARDE", disciplinas[1][0], disciplinas[1][1]},
				{"2� ADS TARDE", disciplinas[2][0], disciplinas[2][1]},
				{"2� ADS NOITE", disciplinas[2][0], disciplinas[2][1]}
		};

		for (int i = 0; i < turmas.length; i++) {
			CardTurma card = new CardTurma(250.0, 150.0, turmas[i][1], turmas[i][0], turmas[i][2]);
			disciplina.addDisciplina(card);
		}
	}
	
	
	
}
