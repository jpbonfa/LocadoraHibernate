package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.dao.CidadeDAO;
import br.com.treinamento.locadora.model.Cidade;
import java.util.ArrayList;

/**
 * Classe responsavel por encapsular os metodos de controle da Cidade
 * @author joaop
 * @since 09/07/2020
 */
public class CidadeController {

    public ArrayList<Cidade> buscarTodos() {
        return new CidadeDAO().buscarTodos();

    }

}
