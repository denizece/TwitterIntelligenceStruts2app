package polimi.awt.twitter.dao;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.context.internal.ManagedSessionContext;

import polimi.awt.twitter.entity.Que;
import polimi.awt.twitter.entity.Tweet;
import polimi.awt.twitter.util.HibernateUtil;

public class TweetDAO {
	public Tweet add(Tweet tweet) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(tweet);
        session.getTransaction().commit();
        return tweet;
    }
    public Tweet delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Tweet tweet = (Tweet) session.load(Tweet.class, id);
        if(null != tweet) {
            session.delete(tweet);
        }
        session.getTransaction().commit();
        return tweet;
    }
 
    @SuppressWarnings("unchecked")
	public List<Tweet> list() {
         
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Tweet> Tweets = null;
        try {
             
            Tweets = (List<Tweet>)session.createQuery("from Tweet").list();
             
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        return Tweets;
    }
    
    public void deleteList(int id) {
         
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.setFlushMode(FlushMode.MANUAL);
        ManagedSessionContext.bind(session);
        session.beginTransaction();
        //List<Tweet> Tweets = null;
        //try {
            //Tweets = (List<Tweet>)session.createQuery("from Tweet").list();
            //session.delete(Tweets);
             
       //} catch (HibernateException e) {
            //e.printStackTrace();
            //session.getTransaction().rollback();
        //}
        
        //QueDAO qDAO = new QueDAO();
        //Que qu = qDAO.findById(id);
        
        //Query query = session.createSQLQuery("delete t from Tweet as t where t.que.idQue = '" +id+"'");
        //int result = query.executeUpdate();
        
        String hql = "delete from Tweet where que.idQue = :id";
        session.createQuery(hql).setInteger("id", id).executeUpdate();
        
        session.getTransaction().commit();
    }
    
}
