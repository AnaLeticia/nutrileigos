package br.com.ufg.si.nutrileigos.model.factory;

import br.com.ufg.si.nutrileigos.model.Usuario;

/**
 * Fábrica de instâncias de Usuario.
 *
 * @author Ana Leticia
 */
public final class UsuarioFactory {

    /**
     * Clona um usuario.
     *
     * @param usuarioOriginal o usuario original que sera clonado
     * @return o usuario clone.
     */
    public static Usuario clone(Usuario usuarioOriginal) {
        if (usuarioOriginal != null) {
            Usuario clone = new Usuario();

            clone.setNome(usuarioOriginal.getNome());
            clone.setLogin(usuarioOriginal.getLogin());
            clone.setSenha(usuarioOriginal.getSenha());
            clone.setEmail(usuarioOriginal.getEmail());

            return clone;
        } else {
            return usuarioOriginal;
        }
    }

}
