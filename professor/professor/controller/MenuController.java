package professor.controller;

import professor.view.Atividade;
import professor.view.Turma;
import professor.view.Home;
import professor.view.Menu;

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
