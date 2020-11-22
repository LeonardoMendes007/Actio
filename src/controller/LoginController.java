package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Aluno;
import model.Professor;
import model.Usuario;
import persistence.ProfessorDao;
import persistence.UsuarioDao;
import view.Login;
import view.aluno.MenuAluno;
import view.professor.MenuProfessor;

public class LoginController {

	private Login login;
	private Usuario usuario;
	
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


	@SuppressWarnings("unused")
	private void verificarAutenticidade(String email, String senha) {
		usuario = new Usuario(email, senha);
		
		UsuarioController uController = new UsuarioController();
		
		try {
			uController.buscarUsuario(usuario);
			System.out.println(usuario.getNome());
			
		
			if(usuario.getNome() != null) {
				
				AlunoController aControlller = new AlunoController();
				System.out.println(usuario.getId());
				Aluno aluno = new Aluno();
				aluno.setId(usuario.getId());
				
				aControlller.buscarAluno(aluno);
					

				System.out.println(aluno.getNome());
				
				if(aluno.getNome() != null) {
					MenuAluno ma = new MenuAluno(login.getScene(), aluno);
					
					
					
				}else{
					System.out.println(usuario.getId());
					ProfessorController pController = new ProfessorController();
					
					Professor professor = new Professor();
					professor.setId(usuario.getId());
					
					pController.buscarProfessor(professor);
					MenuProfessor mp = new MenuProfessor(login.getScene(), professor);
				}
			}else {
				login.setErro("Email ou senha inválido");
			}
			
			//criarLog(email, senha);
			
		} catch (ClassNotFoundException | SQLException e) {
			login.setErro(e.getMessage());
		} 
		
	}

	private void criarLog(String email, String senha) {
	

		try (BufferedWriter wt = new BufferedWriter(new FileWriter(new File("log.txt")))){
			 wt.write(usuario.getId());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
