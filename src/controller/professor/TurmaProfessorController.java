package controller.professor;

import view.card.turma.CardTurma;
import view.professor.TurmaProfessor;

public class TurmaProfessorController {

	private TurmaProfessor disciplina;
	
	
	public TurmaProfessorController(TurmaProfessor disciplina) {
		this.disciplina = disciplina;
	}
	
	public void addCards() {
		
		 String[][] disciplinas = {
				{"Programação Orientada a Ódio",  "#FF9DBA"},
				{"Lógica de Programação","#9DC4FF"},
				{"Banco de Dados", "#9DFFA1"}, 
			};
			

		
		String[][] turmas = {
				{"4° ADS TARDE", disciplinas[0][0], disciplinas[0][1]}, 
				{"4° ADS NOITE", disciplinas[0][0], disciplinas[0][1]}, 
				{"4° ADS MANHÃ", disciplinas[0][0], disciplinas[0][1]}, 
				{"5° ADS MANHÃ", disciplinas[0][0], disciplinas[0][1]}, 
				{"1° ADS TARDE", disciplinas[1][0], disciplinas[1][1]}, 
				{"1° ADS TARDE", disciplinas[1][0], disciplinas[1][1]},
				{"2° ADS TARDE", disciplinas[2][0], disciplinas[2][1]},
				{"2° ADS NOITE", disciplinas[2][0], disciplinas[2][1]}
		};

		for (int i = 0; i < turmas.length; i++) {
			CardTurma card = new CardTurma(250.0, 150.0, turmas[i][1], turmas[i][0], turmas[i][2]);
			disciplina.addDisciplina(card);
		}
	}
	
	
	
}
