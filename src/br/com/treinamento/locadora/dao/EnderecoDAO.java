
package br.com.treinamento.locadora.dao;

import br.com.treinamento.locadora.model.Endereco;
import br.com.treinamento.locadora.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author joaop
 */
public class EnderecoDAO extends GenericDAO{
    
     public Endereco buscarPorCodigo(int codigo) {

        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Endereco endereco = (Endereco) sessao.get(Endereco.class, codigo);
        sessao.close();
        return endereco;
    }

}
