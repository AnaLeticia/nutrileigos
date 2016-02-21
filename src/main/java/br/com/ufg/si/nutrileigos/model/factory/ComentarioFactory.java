package br.com.ufg.si.nutrileigos.model.factory;

import br.com.ufg.si.nutrileigos.model.Comentario;

/**
 * Fabrica de Comentario.
 *
 * @author Ana Leticia
 */
public class ComentarioFactory {

    /**
     * Clona um Comentario.
     *
     * @param comentarioOriginal o comentario original que sera clonado.
     * @return o comentario clone.
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
