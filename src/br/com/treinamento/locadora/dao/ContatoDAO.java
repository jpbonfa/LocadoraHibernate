
package br.com.treinamento.locadora.dao;

import br.com.treinamento.locadora.model.Contato;
import br.com.treinamento.locadora.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author joaop
 * 
 */
public class ContatoDAO extends GenericDAO{
    
     public Contato buscarPorCodigo(int codigo) {

        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Contato contato = (Contato) sessao.get(Contato.class, codigo);
        sessao.close();
        return contato;
    }

}
