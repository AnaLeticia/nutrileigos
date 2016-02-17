package br.com.ufg.si.nutrileigos.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utilitários de conexões SQL.
 *
 * @author Ana Leticia
 */
public final class ConnectionUtil {

    private static final Logger LOG = Logger.getLogger(ConnectionUtil.class);
    private static final String USER = "postgres";
    private static final String PASSWORD = "oobj.postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/nutrileigos";

    private static Connection conn;

    private ConnectionUtil() {
        // classe utilitaria.
    }

    static {
        initConnection();
    }

    private static void initConnection() {
        try {
            LOG.info("Carregando classe do driver do Postgres...");
            Class.forName("org.postgresql.Driver");
            LOG.info("Classe do driver do Postgres carregada com sucesso...");

            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e1) {
            LOG.error("Falha ao carregar a clase do driver do Postgres:", e1);

        } catch (SQLException e) {
            LOG.error("Falha ao obter conexao SQL: ", e);
        }
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
