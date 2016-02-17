package br.com.ufg.si.nutrileigos.dao;

import br.com.ufg.si.nutrileigos.model.Post;
import br.com.ufg.si.nutrileigos.model.Usuario;

import java.sql.SQLException;
import java.util.List;

/**
 * Abstração de operações de DAO (Data Access Object) para Post.
 *
 * @author Ana Leticia
 */
public interface PostDao {

    /**
     *
     * @param post
     * @return
     * @throws SQLException
     */
    Post persist(Post post) throws SQLException;

    /**
     * Retorna um Post com base em um titulo.
     *
     * @param title o titulo do post
     * @return o Post ou null
     */
    Post findByTitle(String title) throws SQLException;

    /**
     * Retorna a lista de posts de um determinado autor
     *
     * @param author o autor dos posts.
     * @return a lista de posts de um determinado autor, ou uma lista vazia
     */
    List<Post> findByAuthor(Usuario author) throws SQLException;
}
