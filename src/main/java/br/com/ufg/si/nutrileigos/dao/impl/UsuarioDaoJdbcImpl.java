package br.com.ufg.si.nutrileigos.dao.impl;

import br.com.ufg.si.nutrileigos.connection.ConnectionUtil;
import br.com.ufg.si.nutrileigos.dao.UsuarioDao;
import br.com.ufg.si.nutrileigos.model.Usuario;
import br.com.ufg.si.nutrileigos.model.factory.UsuarioFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacao de UsuarioDao usando JDBC.
 *
 * @author Ana Leticia
 */
public class UsuarioDaoJdbcImpl implements UsuarioDao {

    private static final Logger LOG = Logger.getLogger(UsuarioDaoJdbcImpl.class);

    private static final String SQL_INSERT = "INSERT INTO usuario (nome, login, senha, email) VALUES (?, ?, ?, ?)";
    private static final String SQL_FIND_ALL = "SELECT * FROM usuario ";
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM usuario WHERE nome = ? ";
    private static final String SQL_FIND_BY_LOGIN = "SELECT * FROM usuario WHERE login = ? ";

    private Connection conn;
    private PreparedStatement psInsert;
    private PreparedStatement psFindAll;
    private PreparedStatement psFindByName;
    private PreparedStatement psFindByLogin;

    public UsuarioDaoJdbcImpl() throws SQLException {
        conn = ConnectionUtil.getConnection();
        psInsert = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
        psFindAll = conn.prepareStatement(SQL_FIND_ALL);
        psFindByName = conn.prepareStatement(SQL_FIND_BY_NAME);
        psFindByLogin = conn.prepareStatement(SQL_FIND_BY_LOGIN);
    }

    @Override
    public Usuario persist(Usuario usuario) throws SQLException {
        if (usuario != null) {
            Usuario usuarioClone = UsuarioFactory.clone(usuario);
            try {
                psInsert.setString(1, usuario.getNome());
                psInsert.setString(2, usuario.getLogin());
                psInsert.setString(3, usuario.getSenha());
                psInsert.setString(4, usuario.getEmail());

                psInsert.executeUpdate();

                try (ResultSet generatedKeys = psInsert.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuarioClone.setId(generatedKeys.getLong(1));
                        return usuarioClone;
                    }
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                psInsert.clearParameters();
            }
        }
        return null;
    }

    @Override
    public Usuario findByName(String name) throws SQLException {
        Usuario user = null;
        try {
            psFindByName.setString(1, name);

            ResultSet rs = psFindByName.executeQuery();
            if (rs.next()) {
                user = usuarioFromResultSet(rs);
            }

            psFindByName.close();
            return user;
        } catch (SQLException e) {
            LOG.error("Falha ao listar o usuario de nome " + name, e);
            throw e;
        }
    }
    
    @Override
	public Usuario findByLogin(String login) throws SQLException {
    	Usuario usuario = null;
    	try {
    		psFindByLogin.setString(1, login);
    		
    		ResultSet rs = psFindByLogin.executeQuery();
    		if (rs.next()) {
    			usuario = usuarioFromResultSet(rs);
    		}
    	} catch (SQLException e) {
    		LOG.error("Falha ao listar o usuario de login " + login,  e);
    		throw e;
    	}

		return usuario;
	}

    @Override
    public List<Usuario> findAll() throws SQLException {
        List<Usuario> users = new ArrayList<>();

        try {
            ResultSet rs = psFindAll.executeQuery();
            while (rs.next()) {
                Usuario user = usuarioFromResultSet(rs);
                users.add(user);
            }

            psFindAll.close();

            return users;
        } catch (SQLException e) {
            LOG.error("Falha ao listar todos os usuarios.", e);
            throw e;
        }
    }

    /**
     * Constroi um Usuario com base no resultSet.
     *
     * @param rs o resultSet
     * @return uma instancia de Usuario
     * @throws SQLException
     */
    private Usuario usuarioFromResultSet(ResultSet rs) throws SQLException {
        String nomeBd = rs.getString("nome");
        String loginBd = rs.getString("login");
        String senhaBd = rs.getString("senha");
        String emailBd = rs.getString("email");

        Usuario user = new Usuario();
        user.setNome(nomeBd);
        user.setLogin(loginBd);
        user.setSenha(senhaBd);
        user.setEmail(emailBd);

        return user;
    }

	

}
