package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.dao.EstadoDAO;
import br.com.treinamento.locadora.model.Estado;
import java.util.ArrayList;

/**
 * Classe responsavel por encapsular os metodos de controle de estado
 * @author joaop
 * @since 09/07/2020
 */
public class EstadoController {

    public ArrayList<Estado> buscarTodos() {
        return new EstadoDAO().buscarTodos();
    }

}
