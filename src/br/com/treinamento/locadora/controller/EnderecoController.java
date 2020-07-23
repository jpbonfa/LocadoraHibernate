package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.dao.EnderecoDAO;
import br.com.treinamento.locadora.model.Endereco;

/**
 *
 * @author joaop
 *
 */
public class EnderecoController {

    public Endereco buscarPorCodigo(int codigo) {
        return new EnderecoDAO().buscarPorCodigo(codigo);
    }

    public void excluir(Endereco endereco) {
        new EnderecoDAO().excluir(endereco);
    }

    public void salvar(Endereco endereco) {
        new EnderecoDAO().salvar(endereco);
    }
}
