package br.com.treinamento.locadora.dao;

import br.com.treinamento.locadora.model.Filme;
import br.com.treinamento.locadora.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Classe responsável por armazenar os metodos de pesquisa, os metodos para
 * salvar e excluir estao no GenericDAO
 *
 * @author joaop
 * @since 06/07/2020
 */
public class FilmeDAO extends GenericDAO {

    public ArrayList<Filme> buscarTodos() {

        ArrayList<Filme> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Filme.class);
        criteria.addOrder(Order.asc("idFilme"));
        listaRetorno = (ArrayList<Filme>) criteria.list();
        sessao.close();
        return listaRetorno;

    }

    public Filme buscarPorCodigo(int codigo) {

        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Filme filme = (Filme) sessao.get(Filme.class, codigo);
        sessao.close();
        return filme;
    }

    public ArrayList<Filme> buscarNome(String nome) {

        ArrayList<Filme> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Filme.class);
        criteria.add(Restrictions.ilike("nome", nome + "%"));
        criteria.addOrder(Order.asc("nome"));
        listaRetorno = (ArrayList<Filme>) criteria.list();
        sessao.close();
        return listaRetorno;

    }
}
