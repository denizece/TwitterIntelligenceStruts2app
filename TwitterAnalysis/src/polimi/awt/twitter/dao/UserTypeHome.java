// default package
// Generated May 31, 2013 2:46:41 PM by Hibernate Tools 3.4.0.CR1
package polimi.awt.twitter.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import polimi.awt.twitter.entity.UserType;

/**
 * Home object for domain model class UserType.
 * 
 * @see .UserType
 * @author Hibernate Tools
 */
@Stateless
public class UserTypeHome {

	private static final Log log = LogFactory.getLog(UserTypeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public List<UserType> listUserTypes() {
		System.out.println("in UserTypeHome ListUsertype method!!");
		List<UserType> utypes = null;// new ArrayList<UserType>();
		utypes = entityManager.createQuery("from UserType").getResultList();
		System.out.println(" ListUsertype method!! success no exception");

		return utypes;
	}

	public void persist(UserType transientInstance) {
		log.debug("persisting UserType instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(UserType persistentInstance) {
		log.debug("removing UserType instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public UserType merge(UserType detachedInstance) {
		log.debug("merging UserType instance");
		try {
			UserType result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserType findById(Integer id) {
		log.debug("getting UserType instance with id: " + id);
		try {
			UserType instance = entityManager.find(UserType.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
