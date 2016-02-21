package br.com.ufg.si.nutrileigos.model.factory;

import br.com.ufg.si.nutrileigos.model.Post;

/**
 * Fabrica de instancias de post.
 *
 * @author Ana Leticia
 */
public class PostFactory {

    /**
     * Clona um post.
     *
     * @param postOriginal o post original que sera clonado
     * @return o post clone.
     */
    public static Post clone(Post postOriginal) {
        if (postOriginal != null) {
            Post clone = new Post();

            clone.setTitulo(postOriginal.getTitulo());
            clone.setConteudo(postOriginal.getConteudo());
            clone.setAutor(postOriginal.getAutor());
            clone.setData(postOriginal.getData());

            return clone;
        } else {
            return postOriginal;
        }
    }
}
