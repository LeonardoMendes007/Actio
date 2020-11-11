package controller;

import view.Atividade;
import view.Disciplina;
import view.Home;
import view.Menu;

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
		new Disciplina(menu.getBorderConteudo());

	}

	private void clearChildren() {

		menu.getBorderConteudo().getChildren().clear();

	}

}
