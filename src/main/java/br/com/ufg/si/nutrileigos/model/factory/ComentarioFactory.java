package br.com.ufg.si.nutrileigos.model.factory;

import br.com.ufg.si.nutrileigos.model.Comentario;

/**
 * Fábrica de Comentario.
 *
 * @author Ana Leticia
 */
public class ComentarioFactory {

    /**
     * Clona um Comentário.
     *
     * @param comentarioOriginal o comentário original que será clonado.
     * @return o comentário clone.
     */
    public static Comentario clone(Comentario comentarioOriginal) {
        if (comentarioOriginal != null) {
            Comentario clone = new Comentario();

            clone.setConteudo(comentarioOriginal.getConteudo());
            clone.setAutor(comentarioOriginal.getAutor());
            clone.setData(comentarioOriginal.getData());

            return clone;
        } else {
            return comentarioOriginal;
        }
    }
}
