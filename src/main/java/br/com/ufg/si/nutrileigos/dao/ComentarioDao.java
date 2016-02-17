package br.com.ufg.si.nutrileigos.dao;

import br.com.ufg.si.nutrileigos.model.Comentario;

import java.sql.SQLException;
import java.util.List;

/**
 * Abstração de operações de DAO (Data Access Object) para Comentario.
 *
 * @author Ana Leticia
 */
public interface ComentarioDao {

    /**
     *
     * @param comentario
     * @return
     * @throws SQLException
     */
    Comentario persist(Comentario comentario) throws SQLException;

    /**
     *
     * @param postId
     * @return
     * @throws SQLException
     */
    List<Comentario> listAllFromPost(Long postId) throws SQLException;
}
