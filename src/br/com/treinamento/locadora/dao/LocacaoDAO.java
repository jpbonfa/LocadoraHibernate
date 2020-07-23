package br.com.treinamento.locadora.dao;

import br.com.treinamento.locadora.model.Locacao;
import br.com.treinamento.locadora.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author joaop
 */
public class LocacaoDAO extends GenericDAO {

    public ArrayList<Locacao> buscarTodos() {

        ArrayList<Locacao> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Locacao.class);
        criteria.addOrder(Order.asc("idLocacao"));
        listaRetorno = (ArrayList<Locacao>) criteria.list();
        sessao.close();
        return listaRetorno;

    }

}
