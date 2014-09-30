package polimi.awt.twitter.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import polimi.awt.twitter.entity.Query;
import polimi.awt.twitter.util.HibernateUtil;

public class QueryDAO {
	public Query add(Query query) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(query);
        session.getTransaction().commit();
        return query;
    }
    public Query delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = (Query) session.load(Query.class, id);
        if(null != query) {
            session.delete(query);
        }
        session.getTransaction().commit();
        return query;
    }
 
    @SuppressWarnings("unchecked")
	public List<Query> list() {
         
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Query> querys = null;
        try {
             
            querys = (List<Query>)session.createQuery("from Query").list();
             
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        return querys;
    }


}
