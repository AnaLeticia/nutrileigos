package br.com.ufg.si.nutrileigos.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ufg.si.nutrileigos.model.Usuario;
import br.com.ufg.si.nutrileigos.service.UsuarioService;

@WebServlet(urlPatterns = "/login.html")
public class LoginServlet extends HttpServlet {
	
	private UsuarioService usuarioService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		
		String login = req.getParameter("username");
		String password = req.getParameter("password");
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(password);
		

		usuarioService.persistir(usuario);
		
		// redireciona para pagina de erro ou para pagina register.html

	}
	

}
