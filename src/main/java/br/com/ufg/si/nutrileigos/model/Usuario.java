package br.com.ufg.si.nutrileigos.model;

/**
 * Temos os contrutores e os getters e setters
 *
 * @author Ana Leticia
 */
public class Usuario {

    private Long id;
    private String nome;
    private String login;
    private String senha;
    private String email;

    public Usuario() {
        super();
    }

    public Usuario(Long id) {
        setId(id);
    }

    public Usuario(String nome) {
        super();
        setNome(nome);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
