package br.com.treinamento.locadora.dao;

import br.com.treinamento.locadora.model.ItemLocacao;
import br.com.treinamento.locadora.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author joaop
 */
public class ItemLocacaoDAO extends GenericDAO {

    public ArrayList<ItemLocacao> buscarTodos() {

        ArrayList<ItemLocacao> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(ItemLocacao.class);
        criteria.addOrder(Order.asc("idItemLocacao"));
        listaRetorno = (ArrayList<ItemLocacao>) criteria.list();
        sessao.close();
        return listaRetorno;

    }

    public ArrayList<ItemLocacao> buscarLocacao(int codigo) {

        ArrayList<ItemLocacao> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(ItemLocacao.class);
        criteria.add(Restrictions.ilike("locacao_id_locacao", codigo));
        criteria.addOrder(Order.asc("id_item_locacao"));
        listaRetorno = (ArrayList<ItemLocacao>) criteria.list();
        sessao.close();
        return listaRetorno;

    }
}
