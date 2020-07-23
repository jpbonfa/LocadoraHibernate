package br.com.treinamento.locadora.controller;

import br.com.treinamento.locadora.dao.FilmeGeneroDAO;
import br.com.treinamento.locadora.model.FilmeGenero;
import java.util.ArrayList;

/**
 * Classe responsavel por encapsular os metodos de controle do genro do filme
 *
 * @author joaop
 * @since 09/07/2020
 */
public class FilmeGeneroControlller {

    public ArrayList<FilmeGenero> buscarFilme(int codigoFilme) {
        return new FilmeGeneroDAO().buscarFilme(codigoFilme);

    }

    public void excluir(int codigoFilme) {
        for (FilmeGenero obj : buscarFilme(codigoFilme)) {
            new FilmeGeneroDAO().excluir(obj);

        }
    } 
    public void salvar(FilmeGenero filmeGenero){
        new FilmeGeneroDAO().salvar(filmeGenero);
        
    }

}
