// default package
// Generated May 31, 2013 2:46:41 PM by Hibernate Tools 3.4.0.CR1
package polimi.awt.twitter.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import polimi.awt.twitter.entity.Query;

/**
 * Home object for domain model class Query.
 * @see .Query
 * @author Hibernate Tools
 */
@Stateless
public class QueryHome {

	private static final Log log = LogFactory.getLog(QueryHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Query transientInstance) {
		log.debug("persisting Query instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Query persistentInstance) {
		log.debug("removing Query instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Query merge(Query detachedInstance) {
		log.debug("merging Query instance");
		try {
			Query result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Query findById(Integer id) {
		log.debug("getting Query instance with id: " + id);
		try {
			Query instance = entityManager.find(Query.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
