package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.dao.UsuarioDAO;
import br.com.treinamento.locadora.model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author joaop
 */
public class UsuarioController {

    public ArrayList<Usuario> buscarTodos() {
        return new UsuarioDAO().buscarTodos();
    }
    
    public boolean verificaLogin(String login, String senha){
        for (Usuario user : new UsuarioDAO().buscarTodos()) {
            if (user.getLogin().equals(login) && user.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
}
