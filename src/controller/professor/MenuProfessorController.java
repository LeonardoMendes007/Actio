package controller.professor;

import view.professor.AtividadeProfessor;
import view.professor.HomeProfessor;
import view.professor.MenuProfessor;
import view.professor.TurmaProfessor;


public class MenuProfessorController {

	private MenuProfessor menu;

	public MenuProfessorController(MenuProfessor menu) {
		this.menu = menu;
	}

	public void btHomeAction() {
		
		clearChildren();
		new HomeProfessor(menu.getBorderConteudo(), menu.getProfessor());

	}

	public void btAtividadeAction() {

		clearChildren();
		new AtividadeProfessor(menu.getBorderConteudo());
	}
	
	public void btDisciplinaAction() {

		clearChildren();
		new TurmaProfessor(menu.getBorderConteudo(), menu.getProfessor());

	}

	private void clearChildren() {

		menu.getBorderConteudo().getChildren().clear();

	}

}
