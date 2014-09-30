package polimi.awt.twitter.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import polimi.awt.twitter.entity.Campaign;
import polimi.awt.twitter.entity.Que;
import polimi.awt.twitter.entity.Tweet;
import polimi.awt.twitter.entity.User;
import polimi.awt.twitter.util.HibernateUtil;

public class QueDAO {
	public Que add(Que query) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(query);
        session.getTransaction().commit();
        return query;
    }
	public Que findById(int id) {
		   
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	       session.beginTransaction();
	       Que selectedQuery = (Que) session.load(Que.class, id);
	       System.out.println("queryDAO findbyid--Query====>>"+selectedQuery.getIdQue() + " ----- " + selectedQuery.getQueText());
	       Hibernate.initialize(selectedQuery);
	       for(Tweet t:selectedQuery.getTweets()){
	    	   Hibernate.initialize(t);
	       }
	       session.getTransaction().commit();
	       return selectedQuery;
	   }
	
    public Que delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Que query = (Que) session.load(Que.class, id);
        if(null != query) {
            session.delete(query);
        }
        session.getTransaction().commit();
        return query;
    }
//    public List<Tweet> geoList(int queryId) {
//    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//    	session.beginTransaction();
//    	List<Tweet> tweets = null;
//    	try {
//    	Criteria c = session.createCriteria(Tweet.class)
//    	.createAlias("query", "q")
//    	.add(Restrictions.eq("q.id", queryId));
//    	tweets = c.list();
//    	} catch (HibernateException e) {
//    	e.printStackTrace();
//    	session.getTransaction().rollback();
//    	}
//    	session.getTransaction().commit();
//    	return tweets;
//    	}

 
    @SuppressWarnings("unchecked")
	public List<Que> list() {
         
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Que> querys = null;
        try {
             
            querys = (List<Que>)session.createQuery("from Query").list();
             
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        return querys;
    }
}
