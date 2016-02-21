package br.com.ufg.si.nutrileigos.servlet;

import br.com.ufg.si.nutrileigos.dao.ComentarioDao;
import br.com.ufg.si.nutrileigos.dao.PostDao;
import br.com.ufg.si.nutrileigos.dao.UsuarioDao;
import br.com.ufg.si.nutrileigos.dao.impl.ComentarioDaoJdbcImpl;
import br.com.ufg.si.nutrileigos.dao.impl.PostDaoJdbcImpl;
import br.com.ufg.si.nutrileigos.dao.impl.UsuarioDaoJdbcImpl;
import br.com.ufg.si.nutrileigos.model.Comentario;
import br.com.ufg.si.nutrileigos.model.Post;
import br.com.ufg.si.nutrileigos.model.Usuario;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;

/**
 * {@link ServletContextListener} utilizado para iniciar a aplicacao.
 *  
 * @author Ana Leticia
 *
 */
@WebListener
public class StartupServletListener implements ServletContextListener {
	
	private static final Logger LOG = Logger.getLogger(StartupServletListener.class);
	
	private PostDao postDao;
	private UsuarioDao usuarioDao;
	private ComentarioDao comentarioDao;

	public void contextInitialized(ServletContextEvent arg0) {
//		try {
//			Usuario admin = new Usuario();
//			admin.setNome("Administrador");
//			admin.setLogin("admin");
//			admin.setSenha("admin");
//			admin.setEmail("analeh1@gmail.com");
//
//			usuarioDao = new UsuarioDaoJdbcImpl();
//			admin = usuarioDao.persist(admin);
//
//			Post post = new Post();
//			post.setTitulo("Meu post");
//			post.setAutor(admin);
//			post.setData(new Date());
//			post.setConteudo("O conteudo do meu post...");
//
//			postDao = new PostDaoJdbcImpl();
//			post = postDao.persist(post);
//
//			Comentario comentario = new Comentario();
//
//			comentario.setPost(post);
//			comentario.setConteudo("o conteudo do meu comentario");
//			comentario.setData(new Date());
//			comentario.setAutor(admin);
//
//			comentarioDao = new ComentarioDaoJdbcImpl();
//			comentario = comentarioDao.persist(comentario);
//		} catch (Exception e) {
//			LOG.error(e);
//			throw new RuntimeException(e);
//		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		desregistrarDriverJdgbc();
	}

	private void desregistrarDriverJdgbc() {
		// This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				LOG.info(String.format("deregistering jdbc driver: %s", driver));
			} catch (SQLException e) {
				LOG.error(String.format("Error deregistering driver %s", driver), e);
			}
		}
	}
}
