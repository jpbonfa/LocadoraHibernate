
package br.com.treinamento.locadora.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/** 
 * Classe responsável por armazenar os metodos de captura da sessao(getSession)
 * @author joaop
 */
public class HibernateUtil {
    
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory(){
        return new AnnotationConfiguration().configure().buildSessionFactory();
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
