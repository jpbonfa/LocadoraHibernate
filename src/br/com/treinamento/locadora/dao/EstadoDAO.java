/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treinamento.locadora.dao;

import br.com.treinamento.locadora.model.Estado;
import br.com.treinamento.locadora.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author joaop
 */
public class EstadoDAO {

    public ArrayList<Estado> buscarTodos() {

        ArrayList<Estado> listaRetorno = new ArrayList<>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Estado.class);
        criteria.addOrder(Order.asc("idEstado"));
        listaRetorno = (ArrayList<Estado>) criteria.list();
        sessao.close();
        return listaRetorno;

    }

    public Estado buscarPorCodigo(int codigo) {

        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Estado estado = (Estado) sessao.get(Estado.class, codigo);
        sessao.close();
        return estado;
    }
}
