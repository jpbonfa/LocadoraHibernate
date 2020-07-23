package br.com.treinamento.locadora.dao;

import br.com.treinamento.locadora.model.Vendedor;
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
public class VendedorDAO extends GenericDAO {

    public ArrayList<Vendedor> buscarTodos() {

        ArrayList<Vendedor> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Vendedor.class);
        criteria.addOrder(Order.asc("idVendedor"));
        listaRetorno = (ArrayList<Vendedor>) criteria.list();
        sessao.close();

        return listaRetorno;

    }

    public Vendedor buscarPorCodigo(int codigo) {

        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Vendedor vendedor = (Vendedor) sessao.get(Vendedor.class, codigo);
        sessao.close();
        return vendedor;
    }

    public ArrayList<Vendedor> buscarNome(String nome) {

        ArrayList<Vendedor> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Vendedor.class);
        criteria.add(Restrictions.ilike("nome", nome + "%"));
        criteria.addOrder(Order.asc("nome"));
        listaRetorno = (ArrayList<Vendedor>) criteria.list();
        sessao.close();
        return listaRetorno;

    }

}
