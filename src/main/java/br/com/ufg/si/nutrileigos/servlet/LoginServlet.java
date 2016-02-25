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

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	
	private UsuarioService usuarioService = new UsuarioService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String formType = req.getParameter("form-type");
		
		if (formType.equals("login-form")) {
			// faz login
			login(req, resp);
		} else if (formType.equals("register-form")) {
			// faz cadastro
			registrar(req, resp);
		}
	}
	
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");

		
		// consulte se existe usuario no banco cujo login é igual ao login da requisicao
		Usuario usuarioConsultadoBanco = usuarioService.consultarUsuarioPorLogin(login);
		
		// se existe usuario, conferir a senha do usuario
		if (usuarioConsultadoBanco != null) {
			if (usuarioConsultadoBanco.getSenha().equals(password)) {
				// redireciona para forum.html
				req.setAttribute("nomeUsuario", usuarioConsultadoBanco.getNome());
				resp.sendRedirect("/nutrileigos/forum.jsp");
			} else {
				// senha incorreta
				resp.getWriter().append("Senha incorreta");
			}
		} else {
			// se nao existe, devolve (responde) falando que nao exista.
			resp.getWriter().append("Usuario inexistente");
		}
		
		
	}

	private void registrar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String nome = req.getParameter("username");
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(password);
		usuario.setEmail(email);
		

		usuarioService.persistir(usuario);
		 resp.sendRedirect("/nutrileigos/register.html");
		
	}

	
	

}
