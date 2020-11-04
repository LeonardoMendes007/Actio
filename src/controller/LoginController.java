package controller;

import java.awt.KeyEventDispatcher;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.Login;
import view.Menu;

public class LoginController {

	private Login login;

	public LoginController(Login login) {
		this.login = login;
	}

	public void logar() {

		if (!login.getEmail().isBlank()) {
			if (!login.getSenha().isBlank()) {

				verificarAutenticidade(login.getEmail(), login.getSenha());

			} else {
				login.senhaIsBlank();
			}

		} else {
			login.emailIsBlank();
			if (login.getSenha().isBlank()) {
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
			Menu menu = new Menu(login.getScene());
		} else {
			login.setErro("Email ou Senha incorretos.");
		}

	}

}
