package br.com.ufg.si.nutrileigos.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utilitarios de conexoes SQL.
 *
 * @author Ana Leticia
 */
public final class ConnectionUtil {
	
	private static final Logger LOG = Logger.getLogger(ConnectionUtil.class);
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:h2:tcp://localhost/~/nutrileigos";

    private static Connection conn;

    private ConnectionUtil() {
        // classe utilitaria.
    }

    static {
        initConnection();
    }

    private static void initConnection() {
        try {
            carregarClasseDriverH2();

            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e1) {
            LOG.error("Falha ao carregar a clase do driver do Postgres:", e1);

        } catch (SQLException e) {
            LOG.error("Falha ao obter conexao SQL: ", e);
        }
    }

	private static void carregarClasseDriverPostgres() throws ClassNotFoundException {
		LOG.info("Carregando classe do driver do Postgres...");
		Class.forName("org.postgresql.Driver");
		LOG.info("Classe do driver do Postgres carregada com sucesso...");
	}
	
	private static void carregarClasseDriverH2() throws ClassNotFoundException {
		LOG.info("Carregando classe do driver do H2...");
		Class<?> forName = Class.forName("org.h2.Driver");
		
		LOG.info("Classe do driver do H2 carregada com sucesso...");
	}
	
    /**
     *
     * @return uma Connection pronta para uso.
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return conn;

    }
}
