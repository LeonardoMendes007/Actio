package controller;


import javafx.event.ActionEvent;
import view.Login;
import view.Menu;

public class LoginController {
	
	private Login login;
	
	public LoginController(Login login) {
		this.login = login;
	}
	
	public void logar(ActionEvent e) {
		
		if(!login.getEmail().isBlank()) {
			if(!login.getSenha().isBlank()) {
				
				verificarAutenticidade(login.getEmail(),login.getSenha());
				
			}else {
				login.senhaIsBlank();
			}
			
		}else {
			login.emailIsBlank();
			if(login.getSenha().isBlank()) {
				login.senhaIsBlank();
			}
		}
		
	}

	private void verificarAutenticidade(String email, String senha) {
		
		if(email.equals("E-MAIL") && senha.equals("Senha")) {
			Menu menu = new Menu(login.getScene());
		}else {
			login.setErro("Email ou Senha incorretos, Por favor tente novamente.");
		}
		
	}
 

}
