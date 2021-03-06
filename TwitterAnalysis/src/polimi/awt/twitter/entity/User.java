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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "User", catalog = "DBawtTwitter")
public class User implements java.io.Serializable {

	private Integer idUser;
	private UserType userType;
	private String name;
	private String password;
	private String email;
	private Set<Campaign> campaigns = new HashSet<Campaign>(0);
	private Set<Campaign> campaigns_1 = new HashSet<Campaign>(0);

	public User() {
	}

	public User(UserType userType) {
		this.userType = userType;
	}

	public User(UserType userType, String name, String password, String email,
			Set<Campaign> campaigns, Set<Campaign> campaigns_1) {
		this.userType = userType;
		this.name = name;
		this.password = password;
		this.email = email;
		this.campaigns = campaigns;
		this.campaigns_1 = campaigns_1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idUser", unique = true, nullable = false)
	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUserType", nullable = false)
	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Column(name = "Name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Password", length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Email", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Campaign> getCampaigns() {
		return this.campaigns;
	}

	public void setCampaigns(Set<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CampaignEmployee", catalog = "DBawtTwitter", uniqueConstraints = @UniqueConstraint(columnNames = "CampaignID"), joinColumns = { @JoinColumn(name = "EmployerID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "CampaignID", unique = true, nullable = false, updatable = false) })
	public Set<Campaign> getCampaigns_1() {
		return this.campaigns_1;
	}

	public void setCampaigns_1(Set<Campaign> campaigns_1) {
		this.campaigns_1 = campaigns_1;
	}

}
