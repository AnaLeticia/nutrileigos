package br.com.ufg.si.nutrileigos.service;

import br.com.ufg.si.nutrileigos.dao.UsuarioDao;
import br.com.ufg.si.nutrileigos.dao.impl.UsuarioDaoJdbcImpl;
import br.com.ufg.si.nutrileigos.model.Usuario;

import java.sql.SQLException;
import java.util.List;

/**
 * Document-me!
 *
 * @author Ana Leticia
 */
public class UsuarioService {

    private UsuarioDao usuarioDao;

    public UsuarioService() {
        try {
            usuarioDao = new UsuarioDaoJdbcImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Usuario> listarTodos() {
        try {
            return usuarioDao.findAll();
        } catch (SQLException e) {
            return null;
        }

    }
}
