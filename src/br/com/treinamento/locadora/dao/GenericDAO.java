package br.com.treinamento.locadora.dao;

import br.com.treinamento.locadora.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Classe responsável por armazenar os metodos genericos de salvar, atualizar e
 * excluir
 *
 * @author joaop
 * @since 06/07/2020
 *
 */
public abstract class GenericDAO {

    @SuppressWarnings("finally")
    public void salvar(Object object) {
        Session sessao = null;
        Transaction transacao = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            sessao.saveOrUpdate(object);
            transacao.commit();
        } catch (Exception e) {
            transacao.rollback();
            sessao.close();
        } finally {
            sessao.close();
        }
    }

    public void excluir(Object object) {
        Session sessao = null;
        Transaction transacao = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            sessao.delete(object);
            transacao.commit();
        } catch (Exception e) {
            transacao.rollback();
            sessao.close();
        } finally {
            sessao.close();
        }
    }
}
