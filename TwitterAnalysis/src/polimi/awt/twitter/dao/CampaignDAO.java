package polimi.awt.twitter.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import polimi.awt.twitter.entity.Campaign;
import polimi.awt.twitter.entity.Que;
import polimi.awt.twitter.entity.User;
import polimi.awt.twitter.util.HibernateUtil;

public class CampaignDAO {
//	@SessionTarget
	Session session;
    
//	@TransactionTarget
	Transaction transaction;
	public Campaign add(Campaign campaign) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(campaign);
        session.getTransaction().commit();
        return campaign;
    }
	
	
	  public Campaign findById(int id) {
		   
		   Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	       session.beginTransaction();
	       Campaign camp = (Campaign) session.load(Campaign.class, id);
    	   System.out.println("campaignDAO findbyid--Campaign====>>"+camp.getIdCampaign() + " ----- " + camp.getName());
	       Hibernate.initialize(camp);
	       for(Que q:camp.getQues()){
	    	   Hibernate.initialize(q);
	       }
	       for(User q:camp.getUsers()){
	    	   Hibernate.initialize(q);
	       }
	       session.getTransaction().commit();
	       return camp;
	   }
	
	
    public Campaign delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Campaign campaign = (Campaign) session.load(Campaign.class, id);
        if(null != campaign) {
            session.delete(campaign);
        }
        session.getTransaction().commit();
        return campaign;
    }
	
	
	@SuppressWarnings("unchecked")
	public List<Campaign> listCampaign() {	
		List<Campaign> campaigns = null;
		/*try {
			
			types = session.createQuery("from Campaign").list();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return types;*/
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
             
            campaigns = (List<Campaign>)session.createQuery("from Campaign").list();
             
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        return campaigns;
		
	}

	public void saveCampaign(Campaign campaign) {
		try {
			session.save(campaign);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}

	public Campaign merge(Campaign detachedInstance) {
		System.out.println("merging Campaign instance");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
	    
	    
		try {
			Campaign result = (Campaign) session.merge(detachedInstance);
			session.saveOrUpdate(result);
			tx.commit();
			System.out.println("merge successful");
			return result;
		} catch (RuntimeException re) {
			System.out.println("merge failed "+ re);
			throw re;
		}
		
	}
	
}
