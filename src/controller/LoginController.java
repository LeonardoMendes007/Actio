package controller;

import java.awt.KeyEventDispatcher;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.Login;
import view.aluno.MenuAluno;

public class LoginController {

	private Login login;

	public LoginController(Login login) {
		this.login = login;
	}

	public void logar() {

		if (!login.getEmail().isEmpty()) {//MUDEI de isBlank pra isEmpty pq isBlank n funcionou
			if (!login.getSenha().isEmpty()) {//MUDEI de isBlank pra isEmpty pq isBlank n funcionou

				verificarAutenticidade(login.getEmail(), login.getSenha());

			} else {
				login.senhaIsBlank();
			}

		} else {
			login.emailIsBlank();
			if (login.getSenha().isEmpty()) {	//MUDEI de isBlank pra isEmpty pq isBlank n funcionou
				login.senhaIsBlank();
			}
		}

	}

	public void verifiqueKeyPressed(KeyEvent key) {

		if (key.getCode() == KeyCode.ENTER) {
			
           logar();
			
		} 

	}

	private void verificarAutenticidade(String email, String senha) {

		if (email.equals("a") && senha.equals("b")) {
			MenuAluno menu = new MenuAluno(login.getScene());
		}else if(email.equals("p") && senha.equals("p")){
			view.professor.MenuProfessor menuProfessor = new view.professor.MenuProfessor(login.getScene());
		}
		
		else {
			login.setErro("Email ou Senha incorretos.");
		}

	}

}
