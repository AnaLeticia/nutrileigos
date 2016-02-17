package br.com.ufg.si.nutrileigos.model;

import java.util.Date;

/**
 * Document-me!
 *
 * @author Ana Leticia
 */
public class Comentario {

    private Long id;
    private Post post;
    private String conteudo;
    private Date data;
    private Usuario autor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }


}
