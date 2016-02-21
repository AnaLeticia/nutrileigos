package br.com.ufg.si.nutrileigos.dao.impl;

import br.com.ufg.si.nutrileigos.connection.ConnectionUtil;
import br.com.ufg.si.nutrileigos.dao.PostDao;
import br.com.ufg.si.nutrileigos.model.Post;
import br.com.ufg.si.nutrileigos.model.Usuario;
import br.com.ufg.si.nutrileigos.model.factory.PostFactory;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementação de PostDao usando JDBC.
 *
 * @author Ana Leticia
 */
public class PostDaoJdbcImpl implements PostDao {

    private static final Logger LOG = Logger.getLogger(PostDaoJdbcImpl.class);
    private static final String SQL_INSERT = "INSERT INTO post (titulo, conteudo, autor, data) VALUES (?, ?, ?, ?)";
    private static final String SQL_FIND_ALL = "SELECT * FROM post ";
    private static final String SQL_FIND_BY_TITLE = SQL_FIND_ALL + "WHERE titulo = ? ";
    private static final String SQL_FIND_BY_AUTHOR = SQL_FIND_ALL + "WHERE autor = ? ";

    private Connection conn;

    private PreparedStatement psInsert;
    private PreparedStatement psFindAll;
    private PreparedStatement psFindByTitle;
    private PreparedStatement psFindByAuthor;

    public PostDaoJdbcImpl() throws SQLException {
        conn = ConnectionUtil.getConnection();

        psInsert = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
        psFindAll = conn.prepareStatement(SQL_FIND_ALL);
        psFindByTitle = conn.prepareStatement(SQL_FIND_BY_TITLE);
        psFindByAuthor = conn.prepareStatement(SQL_FIND_BY_AUTHOR);
    }

    @Override
    public Post persist(Post post) throws SQLException {
        if (post != null) {
            Post postClone = PostFactory.clone(post);

            try {
                psInsert.setString(1, post.getTitulo());
                psInsert.setString(2, post.getConteudo());
                psInsert.setLong(3, post.getAutor().getId());
                psInsert.setTimestamp(4, new Timestamp(post.getData().getTime()));

                psInsert.executeUpdate();

                try (ResultSet generatedKeys = psInsert.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        postClone.setId(generatedKeys.getLong(1));
                        return postClone;
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
    public Post findByTitle(String title) throws SQLException {
        if (title != null) {
        	psFindByTitle.setString(1, title);

            ResultSet rs = psFindByTitle.executeQuery();

            if (rs.next()) {
                return postFromResultSet(rs);
            }

            psFindByAuthor.close();

        }
        return null;
    }

    @Override
    public List<Post> findByAuthor(Usuario author) throws SQLException {
        if (author != null) {
            List<Post> allPostsFromAuthor = new ArrayList<>();

            psFindByAuthor.setLong(1, author.getId());
            
            ResultSet rs = psFindByAuthor.executeQuery();
            
            while (rs.next()) {
                Post post = postFromResultSet(rs);
                allPostsFromAuthor.add(post);
            }

            psFindByAuthor.close();

            return allPostsFromAuthor;
        }
        return null;
    }

    /**
     * Constroi um Post com base no resultSet.
     *
     * @param rs o resultSet
     * @return uma instância de Post
     * @throws SQLException
     */
    private Post postFromResultSet(ResultSet rs) throws SQLException {
        Post post = new Post();

        String titulo = rs.getString("titulo");
        String conteudo = rs.getString("conteudo");
        Long idAutor = rs.getLong("autor");
        Date data = rs.getTimestamp("data");

        post.setTitulo(titulo);
        post.setConteudo(conteudo);
        post.setAutor(new Usuario(idAutor));
        post.setData(data);

        return post;
    }
}
