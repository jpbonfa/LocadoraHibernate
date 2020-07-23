package br.com.treinamento.locadora.dao;

import br.com.treinamento.locadora.model.FilmeGenero;
import br.com.treinamento.locadora.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 * Classe responsável por armazenar os metodos de pesquisa, os metodos para
 * salvar e excluir estao no GenericDAO
 *
 * @author joaop
 * @since 06/07/2020
 */
public class FilmeGeneroDAO extends GenericDAO {

    public ArrayList<FilmeGenero> buscarFilme(int codigoFilme) {

        ArrayList<FilmeGenero> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(FilmeGenero.class);
        criteria.addOrder(Order.asc("idFilmeGenero"));

        for (FilmeGenero lista : (ArrayList<FilmeGenero>) criteria.list()) {
            if (lista.getFilmeIdFilme().getIdFilme() == codigoFilme) {
                listaRetorno.add(lista);
            }
        }
        sessao.close();
        return listaRetorno;

    }
    
    

}
