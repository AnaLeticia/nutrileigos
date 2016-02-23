package br.com.ufg.si.nutrileigos.servlet;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import br.com.ufg.si.nutrileigos.connection.ConnectionUtil;
import br.com.ufg.si.nutrileigos.dao.ComentarioDao;
import br.com.ufg.si.nutrileigos.dao.PostDao;
import br.com.ufg.si.nutrileigos.dao.UsuarioDao;

/**
 * {@link ServletContextListener} utilizado para iniciar a aplicacao.
 *  
 * @author Ana Leticia
 *
 */
@WebListener
public class StartupServletListener implements ServletContextListener {
	
	private static Connection conn = null;
	
	private static final Logger LOG = Logger.getLogger(StartupServletListener.class);
	
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			createSequenceIdUsuario();
			
			createTableUsuario();
		} catch (Exception e) {
			LOG.error(e);
			throw new RuntimeException(e);
		}
	}
	
	private void createTableUsuario() throws SQLException {
		try {
			conn = ConnectionUtil.getConnection();
			
			String sql = "CREATE TABLE IF NOT EXISTS usuario "
//					+ "(id_usuario bigint default seq_id_usuario.nextval primary key, "
					+ "(id_usuario identity, "
					+ "nome varchar(255), "
					+ "login varchar(255), "
					+ "senha varchar(255), "
					+ "email varchar(255)"
					+ "); ";
			
			Statement ps = conn.createStatement();
			ps.execute(sql);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	private void createSequenceIdUsuario() throws SQLException {
		try {
			conn = ConnectionUtil.getConnection();
			
			String sql = "CREATE SEQUENCE IF NOT EXISTS sed_id_usuario START WITH 1 INCREMENT BY 1";
			
			Statement ps = conn.createStatement();
			ps.execute(sql);
		} catch (SQLException e) {
			throw e;
		}
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
