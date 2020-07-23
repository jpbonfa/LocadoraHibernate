
package br.com.treinamento.locadora.dao;

import br.com.treinamento.locadora.model.Usuario;
import br.com.treinamento.locadora.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author joaop
 */
public class UsuarioDAO extends GenericDAO{
    
    public ArrayList<Usuario> buscarTodos() {

        ArrayList<Usuario> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Usuario.class);
        criteria.addOrder(Order.asc("idUsuario"));
        listaRetorno = (ArrayList<Usuario>) criteria.list();
        sessao.close();
        return listaRetorno;

    }
    
}
