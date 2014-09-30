package polimi.awt.twitter.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import polimi.awt.twitter.entity.Campaign;
import polimi.awt.twitter.entity.User;
import polimi.awt.twitter.entity.UserType;
import polimi.awt.twitter.util.HibernateUtil;

public class UserTypeDAO extends HibernateUtil {
	
//	@SessionTarget
	Session session;
    
//	@TransactionTarget
	Transaction transaction;

	@SuppressWarnings("unchecked")
	
	
	  public UserType add(UserType UserType) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(UserType);
        session.getTransaction().commit();
        return UserType;
    }
    public UserType delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        UserType userType = (UserType) session.load(UserType.class, id);
        if(null != userType) {
            session.delete(userType);
        }
        session.getTransaction().commit();
        return userType;
    }
	
	
	public List<UserType> listUserType() {	
		List<UserType> types = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
             
            types = (List<UserType>)session.createQuery("from UserType").list();
             
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        return types;
		
	}
	   public UserType findById(int id) {
		   
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	       session.beginTransaction();
	       UserType usertype = (UserType) session.load(UserType.class, id);
	       System.out.println("userTypeDAO findbyid--USERType====>>"+usertype.getIdUserType() + " ----- " + usertype.getTypeName());
	       Hibernate.initialize(usertype);

	       session.getTransaction().commit();
	       return usertype;
	   }
	public void saveUser(UserType type) {
		try {
			session.save(type);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}


}
