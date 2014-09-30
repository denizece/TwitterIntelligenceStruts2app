package polimi.awt.twitter.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import polimi.awt.twitter.entity.Campaign;
import polimi.awt.twitter.entity.User;
import polimi.awt.twitter.util.HibernateUtil;

public class UserDAO extends HibernateUtil{
	
	   public User add(User user) {
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        session.save(user);
	        session.getTransaction().commit();
	        return user;
	    }
	   public User merge(User detachedInstance) {
			System.out.println("merging User instance");
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
			try {
				User result = (User) session.merge(detachedInstance);
				System.out.println("merge successful");
				return result;
			} catch (RuntimeException re) {
				System.out.println("merge failed"+ re);
				throw re;
			}
		}
	   public User findById(int id) {
		   
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	       session.beginTransaction();
	       User user = (User) session.load(User.class, id);
	       System.out.println("userDAO findbyid--USER MANAGER====>>"+user.getIdUser() + " ----- " + user.getName());
	       Hibernate.initialize(user);
	       for(Campaign c:user.getCampaigns()){
	    	   Hibernate.initialize(c);
	       }
	       for(Campaign c:user.getCampaigns_1()){
	    	   Hibernate.initialize(c);
	       }
	       Hibernate.initialize(user.getUserType());
	       session.getTransaction().commit();
	       return user;
	   }
	   
	   public User getRegisteredUser(String name, String pass){
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
		   
	        User user = (User) session.createCriteria(User.class).add(Restrictions.and(Restrictions.eq("name",name),Restrictions.eq("password",pass))).uniqueResult();
	        return user;
	   }
	    public User delete(Long id) {
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        User user = (User) session.load(User.class, id);
	        if(null != user) {
	            session.delete(user);
	        }
	        session.getTransaction().commit();
	        return user;
	    }
	 
	    public List<User> list() {
	         
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        List<User> Users = null;
	        try {
	             
	            Users = (List<User>)session.createQuery("from User").list();
	             
	        } catch (HibernateException e) {
	            e.printStackTrace();
	            session.getTransaction().rollback();
	        }
	        session.getTransaction().commit();
	        return Users;
	    }

}
