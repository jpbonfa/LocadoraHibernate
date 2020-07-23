package br.com.treinamento.locadora.dao;

import br.com.treinamento.locadora.model.Cliente;
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
public class ClienteDAO extends GenericDAO {

    public ArrayList<Cliente> buscarTodos() {

        ArrayList<Cliente> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Cliente.class);
        criteria.addOrder(Order.asc("idCliente"));
        listaRetorno = (ArrayList<Cliente>) criteria.list();
        sessao.close();
        return listaRetorno;

    }

    public Cliente buscarPorCodigo(int codigo) {

        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Cliente cliente = (Cliente) sessao.get(Cliente.class, codigo);
        sessao.close();
        return cliente;
    }

    public ArrayList<Cliente> buscarNome(String nome) {

        ArrayList<Cliente> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Cliente.class);
        criteria.add(Restrictions.ilike("nome", nome + "%"));
        criteria.addOrder(Order.asc("nome"));
        listaRetorno = (ArrayList<Cliente>) criteria.list();
        sessao.close();
        return listaRetorno;

    }
}
