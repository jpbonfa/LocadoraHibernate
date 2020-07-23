package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.dao.ContatoDAO;
import br.com.treinamento.locadora.model.Contato;

/**
 *
 * @author joaop
 *
 */
public class ContatoController {

    public Contato buscarPorCodigo(int codigo) {
        return new ContatoDAO().buscarPorCodigo(codigo);
    }

    public void excluir(Contato contato) {
        new ContatoDAO().excluir(contato);
    }

    public void salvar(Contato contato) {
        new ContatoDAO().salvar(contato);
    }
}
