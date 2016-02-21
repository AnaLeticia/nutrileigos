package br.com.ufg.si.nutrileigos.dao;

import br.com.ufg.si.nutrileigos.model.Usuario;

import java.sql.SQLException;
import java.util.List;

/**
 * Abstracao de operacoes de DAO (Data Access Object) para Usuario.
 * 
 *
 * @author Ana Leticia
 */
public interface UsuarioDao {

    /**
     * Insere um usuario no banco de dados.
     * @param usuario
     * @return
     * @throws SQLException
     */
    Usuario persist(Usuario usuario) throws SQLException;

    /**
     * Busca um usuario no banco de dados de acordo com o seu nome.
     * 
     * @param name
     * @return
     * @throws SQLException
     */
    Usuario findByName(String name) throws SQLException;

    /**
     * busca todos os usuarios do banco de dados.
     * 
     * @return Uma lista de usuários
     * @throws SQLException 
     */
    List<Usuario> findAll() throws SQLException;

}