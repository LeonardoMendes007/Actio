package controller.professor;

import view.professor.Atividade;
import view.professor.Home;
import view.professor.Menu;
import view.professor.Turma;

public class MenuController {

	private Menu menu;

	public MenuController(Menu menu) {
		this.menu = menu;
	}

	public void btHomeAction() {
		
		clearChildren();
		new Home(menu.getBorderConteudo());

	}

	public void btAtividadeAction() {

		clearChildren();
		new Atividade(menu.getBorderConteudo());
	}
	
	public void btDisciplinaAction() {

		clearChildren();
		new Turma(menu.getBorderConteudo());

	}

	private void clearChildren() {

		menu.getBorderConteudo().getChildren().clear();

	}

}
