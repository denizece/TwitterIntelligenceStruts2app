package polimi.awt.twitter.entity;

// default package
// Generated Jun 10, 2013 6:37:02 PM by Hibernate Tools 3.4.0.CR1

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
 * Que generated by hbm2java
 */
@Entity
@Table(name = "Que", catalog = "DBawtTwitter")
public class Que implements java.io.Serializable {

	private Integer idQue;
	private Campaign campaign;
	private String queText;
	private Set<Tweet> tweets = new HashSet<Tweet>(0);

	public Que() {
	}

	public Que(Campaign campaign) {
		this.campaign = campaign;
	}

	public Que(Campaign campaign, String queText, Set<Tweet> tweets) {
		this.campaign = campaign;
		this.queText = queText;
		this.tweets = tweets;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idQue", unique = true, nullable = false)
	public Integer getIdQue() {
		return this.idQue;
	}

	public void setIdQue(Integer idQue) {
		this.idQue = idQue;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCampaign", nullable = false)
	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	@Column(name = "QueText", length = 45)
	public String getQueText() {
		return this.queText;
	}

	public void setQueText(String queText) {
		this.queText = queText;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "que")
	public Set<Tweet> getTweets() {
		return this.tweets;
	}

	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}

}