package controller.aluno;

import model.Aluno;
import view.aluno.AtividadeAluno;
import view.aluno.DisciplinaAluno;
import view.aluno.HomeAluno;
import view.aluno.MenuAluno;

public class MenuAlunoController {

	private MenuAluno menu;

	public MenuAlunoController(MenuAluno menu) {
		this.menu = menu;
	}

	public void btHomeAction(Aluno aluno) {
		
		clearChildren();
		new HomeAluno(menu.getBorderConteudo(), aluno);

	}

	public void btAtividadeAction() {

		clearChildren();
		new AtividadeAluno(menu.getBorderConteudo(), menu.getAluno());
	}
	
	public void btDisciplinaAction() {

		clearChildren();
		new DisciplinaAluno(menu.getBorderConteudo());

	}

	private void clearChildren() {

		menu.getBorderConteudo().getChildren().clear();

	}

}
