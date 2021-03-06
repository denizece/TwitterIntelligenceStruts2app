package polimi.awt.twitter.entity;

// default package
// Generated May 31, 2013 2:46:41 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Query generated by hbm2java
 */
@Entity
@Table(name = "Query", catalog = "twitterDB")
public class Query implements java.io.Serializable {

	private Integer idQuery;
	private Campaign campaign;
	private String queryText;
	private Set<Tweet> tweets = new HashSet<Tweet>(0);

	public Query() {
	}

	public Query(Campaign campaign) {
		this.campaign = campaign;
	}

	public Query(Campaign campaign, String queryText, Set<Tweet> tweets) {
		this.campaign = campaign;
		this.queryText = queryText;
		this.tweets = tweets;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idQuery", unique = true, nullable = false)
	public Integer getIdQuery() {
		return this.idQuery;
	}

	public void setIdQuery(Integer idQuery) {
		this.idQuery = idQuery;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCampaign", nullable = false)
	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	@Column(name = "QueryText", length = 45)
	public String getQueryText() {
		return this.queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "query")
	public Set<Tweet> getTweets() {
		return this.tweets;
	}

	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}

}
