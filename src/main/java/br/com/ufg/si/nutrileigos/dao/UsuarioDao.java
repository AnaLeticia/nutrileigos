package br.com.ufg.si.nutrileigos.dao;

import br.com.ufg.si.nutrileigos.model.Usuario;

import java.sql.SQLException;
import java.util.List;

/**
 * Abstração de operações de DAO (Data Access Object) para Usuário.
 *
 * @author Ana Leticia
 */
public interface UsuarioDao {

    /**
     *
     * @param usuario
     * @return
     * @throws SQLException
     */
    Usuario persist(Usuario usuario) throws SQLException;

    /**
     *
     * @param name
     * @return
     * @throws SQLException
     */
    Usuario findByName(String name) throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    List<Usuario> findAll() throws SQLException;




}