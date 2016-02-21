package br.com.ufg.si.nutrileigos.model;

import java.util.Date;

/**
 * representa o post no forum
 *
 * @author Ana Leticia
 */
public class Post {

    private Long id;
    private String titulo;
    private String conteudo;
    private Usuario autor;
    private Date data;

    public Post() {
        super();
    }

    public Post(Long id) {
        setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
