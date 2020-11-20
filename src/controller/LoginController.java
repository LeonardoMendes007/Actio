package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Usuario;
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
			
//			criarLog(email,senha);			
			MenuAluno menu = new MenuAluno(login.getScene());
			
			
		}else if(email.equals("p") && senha.equals("p")){
			
//			criarLog(email,senha);	
			view.professor.MenuProfessor menuProfessor = new view.professor.MenuProfessor(login.getScene());
			
		}
		
		else {
			login.setErro("Email ou Senha incorretos.");
		}

	}

	private void criarLog(String email, String senha) {
		
		UsuarioDao userDao = new UsuarioDao();
		
		Usuario usuario = userDao.findUsuario(new Usuario(email, senha));
		
		try (BufferedWriter wt = new BufferedWriter(new FileWriter(new File("log.txt")))){
			 wt.write(usuario.getId());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
