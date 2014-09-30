// default package
// Generated May 31, 2013 2:46:41 PM by Hibernate Tools 3.4.0.CR1
package polimi.awt.twitter.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import polimi.awt.twitter.entity.Tweet;

/**
 * Home object for domain model class Tweet.
 * @see .Tweet
 * @author Hibernate Tools
 */
@Stateless
public class TweetHome {

	private static final Log log = LogFactory.getLog(TweetHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tweet transientInstance) {
		log.debug("persisting Tweet instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tweet persistentInstance) {
		log.debug("removing Tweet instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tweet merge(Tweet detachedInstance) {
		log.debug("merging Tweet instance");
		try {
			Tweet result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tweet findById(Integer id) {
		log.debug("getting Tweet instance with id: " + id);
		try {
			Tweet instance = entityManager.find(Tweet.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
