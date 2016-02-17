package br.com.ufg.si.nutrileigos.dao.impl;

import br.com.ufg.si.nutrileigos.connection.ConnectionUtil;
import br.com.ufg.si.nutrileigos.dao.ComentarioDao;
import br.com.ufg.si.nutrileigos.model.Comentario;
import br.com.ufg.si.nutrileigos.model.Post;
import br.com.ufg.si.nutrileigos.model.Usuario;
import br.com.ufg.si.nutrileigos.model.factory.ComentarioFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de ComentarioDao usando JDBC.
 *
 * @author Ana Letícia
 */
public class ComentarioDaoJdbcImpl implements ComentarioDao {

    private static final String SQL_INSERT = "INSERT INTO comentario (post, autor, conteudo, data) VALUES (?, ?, ?, ?)";
    private static final String SQL_FIND_ALL = "SELECT * FROM comentario ";
    private static final String SQL_FIND_BY_AUTHOR = SQL_FIND_ALL + "WHERE autor = ? ";
    private static final String SQL_FIND_BY_POST = SQL_FIND_ALL + "WHERE post = ? ";

    private Connection conn;

    private PreparedStatement psInsert;
    private PreparedStatement psFindByTitle;
    private PreparedStatement psFindByAuthor;
    private PreparedStatement psFindByPost;

    public ComentarioDaoJdbcImpl() throws SQLException {
        conn = ConnectionUtil.getConnection();

        psInsert = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        psFindByPost = conn.prepareStatement(SQL_FIND_BY_POST);
    }

    @Override
    public Comentario persist(Comentario comentario) throws SQLException {
        if (comentario != null) {
            Comentario comentarioClone = ComentarioFactory.clone(comentario);

            try {
                psInsert.setLong(1, comentario.getPost().getId());
                psInsert.setLong(2, comentario.getAutor().getId());
                psInsert.setString(3, comentario.getConteudo());
                psInsert.setTimestamp(4, new Timestamp(comentario.getData().getTime()));

                psInsert.executeUpdate();

                try (ResultSet generatedKeys = psInsert.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        comentarioClone.setId(generatedKeys.getLong(1));
                        return comentarioClone;
                    }
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                psInsert.clearParameters();
            }
        }
        return comentario;
    }

    @Override
    public List<Comentario> listAllFromPost(Long postId) throws SQLException {
        if (postId != null) {
            List<Comentario> allCommentsFromPost = new ArrayList<>();

            psFindByPost.setLong(1, postId);

            ResultSet rs = psFindByPost.executeQuery();

            while (rs.next()) {
                Comentario post = comentarioFromResultSet(rs);
                allCommentsFromPost.add(post);
            }

            psFindByAuthor.close();

            return allCommentsFromPost;
        }
        return null;
    }

    /**
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Comentario comentarioFromResultSet(ResultSet rs) throws SQLException {
        Comentario comentario = new Comentario();

        Long post = rs.getLong("post");
        String conteudo = rs.getString("conteudo");
        java.util.Date data = rs.getTimestamp("data");
        Long idAutor = rs.getLong("autor");

        comentario.setPost(new Post(post));
        comentario.setConteudo(conteudo);
        comentario.setAutor(new Usuario(idAutor));
        comentario.setData(data);

        return comentario;
    }
}
