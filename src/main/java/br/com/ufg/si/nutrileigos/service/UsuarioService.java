package br.com.ufg.si.nutrileigos.service;

import br.com.ufg.si.nutrileigos.dao.UsuarioDao;
import br.com.ufg.si.nutrileigos.dao.impl.UsuarioDaoJdbcImpl;
import br.com.ufg.si.nutrileigos.model.Usuario;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Classe de negócios (Business Object) que é responsável por aplicar as regras de negócio na entidade Usuario.
 *
 * @author Ana Leticia
 */
public class UsuarioService {
	
	private static final Logger LOG = Logger.getLogger(UsuarioService.class);

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
    
    public Usuario persistir(Usuario usuario) {
    	try {
			return usuarioDao.persist(usuario);
		} catch (SQLException e) {
			LOG.error("Falha ao persistir o usuario", e);
			return null;
		}
    }
    
}
