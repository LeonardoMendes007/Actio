package controller.aluno;

import view.aluno.AtividadeAluno;
import view.aluno.DisciplinaAluno;
import view.aluno.HomeAluno;
import view.aluno.MenuAluno;

public class MenuAlunoController {

	private MenuAluno menu;

	public MenuAlunoController(MenuAluno menu) {
		this.menu = menu;
	}

	public void btHomeAction() {
		
		clearChildren();
		new HomeAluno(menu.getBorderConteudo());

	}

	public void btAtividadeAction() {

		clearChildren();
		new AtividadeAluno(menu.getBorderConteudo());
	}
	
	public void btDisciplinaAction() {

		clearChildren();
		new DisciplinaAluno(menu.getBorderConteudo());

	}

	private void clearChildren() {

		menu.getBorderConteudo().getChildren().clear();

	}

}
