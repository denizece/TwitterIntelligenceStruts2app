package polimi.awt.twitter.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import polimi.awt.twitter.dao.CampaignDAO;
import polimi.awt.twitter.dao.QueDAO;
import polimi.awt.twitter.dao.TweetDAO;
import polimi.awt.twitter.dao.UserDAO;
import polimi.awt.twitter.dao.UserTypeDAO;
import polimi.awt.twitter.entity.Campaign;
import polimi.awt.twitter.entity.Que;
import polimi.awt.twitter.entity.Tweet;
import polimi.awt.twitter.entity.User;
import polimi.awt.twitter.entity.UserType;
import polimi.awt.twitter.service.LocationParser;
import polimi.awt.twitter.service.QueryExecuter;
import polimi.awt.twitter.util.TweetGeoLocation;
import twitter4j.GeoLocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class QueryAction extends ActionSupport implements ModelDriven<Que> {

	private String idCampaign;
	private Que newQuery;
	private Set<Tweet> tweets = new HashSet<Tweet>();
	private Campaign campaign;
	private String selectedQuery;
	private Set<Que> queries = new HashSet<Que>();
	private Set<GeoLocation> geolocs = new HashSet<GeoLocation>();
	private List<TweetGeoLocation> tweetGeoLocation;

	// private Map<String, GeoLocation> tweetGeoLocation;

	private int pointsOnMap;

	public Set<GeoLocation> getGeolocs() {
		return geolocs;
	}

	public void setGeolocs(Set<GeoLocation> geolocs) {
		this.geolocs = geolocs;
	}

	private int idUser;

	public String execute() {
		System.out.println("query action execute");
		QueDAO qDAO = new QueDAO();
		int id = Integer.parseInt(selectedQuery);
		Que q = qDAO.findById(id);
		tweets = q.getTweets();
		System.out.println("selected Query = " + q.getQueText());

		return SUCCESS;
	}

	public String selectQuery() {

		CampaignDAO cdao = new CampaignDAO();
		int id = Integer.parseInt(idCampaign);
		Campaign c = cdao.findById(id);
		System.out.println("campaignDAO findbyid--Campaign====>>"
				+ c.getIdCampaign() + " ----- " + c.getName());
		System.out.println("\n\nUSER ID USER IDDDDDD!!!!!!!!====>>" + idUser);

		setCampaign(c);
		System.out.println("campaignDAO findbyid--Campaign====>>"
				+ campaign.getIdCampaign() + " ----- " + campaign.getName());
		queries = c.getQues();
		return SUCCESS;
	}

	public String getqQueryGeoLocMap() {
		QueDAO qDAO = new QueDAO();
		LocationParser lp = new LocationParser();
		int id = Integer.parseInt(selectedQuery);
		Que q = qDAO.findById(id);
		tweets = q.getTweets();

		if (tweets.size() > 0) {
			tweetGeoLocation = new ArrayList<TweetGeoLocation>();

		}
		TweetGeoLocation currentTweet = null;
		System.out.println("Size of the Tweets:" + tweets.size());
		for (Tweet t : tweets) {
			GeoLocation gl = lp.getmyGeoLoc(t.getGeoLocation());
			if (gl != null) {
				// tweetGeoLocation.put(t.getTweetText(), gl);
				currentTweet = new TweetGeoLocation();
				currentTweet.setId(t.getIdTweet());
				currentTweet.setTweetText(t.getTweetText());
				currentTweet.setLatitude(gl.getLatitude());
				currentTweet.setLongitude(gl.getLongitude());
				
				tweetGeoLocation.add(currentTweet);
				// this.geolocs.add(gl);
			}
		}
		//setPointsOnMap(this.geolocs.size());
		setPointsOnMap(tweetGeoLocation.size());
		return SUCCESS;
	}

	public String deleteQuery() {

		System.out.println("delete query");
		System.out.println("Selected queryId: " + selectedQuery);
		int id = Integer.parseInt(selectedQuery);
		TweetDAO tDAO = new TweetDAO();
		tDAO.deleteList(id);

		QueDAO qDAO = new QueDAO();
		qDAO.delete(id);
		return SUCCESS;
	}

	public String loadCreate() {
		newQuery = new Que();
		System.out.println("loadCreate action");

		return SUCCESS;
	}

	public String loadQuery() {
		System.out.println("LOAD CREATE idCampaign=" + getIdCampaign());
		CampaignDAO cdao = new CampaignDAO();
		int id = Integer.parseInt(idCampaign);
		Campaign tcampaign = cdao.findById(id);
		setCampaign(tcampaign);
		return SUCCESS;
	}

	public String createNew() {
		System.out.println("createNew action");
		System.out.println("new Query =" + getNewQuery().getQueText());
		int id = Integer.parseInt(idCampaign);
		CampaignDAO campDAO = new CampaignDAO();
		Campaign camp = campDAO.findById(id);
		System.out.println("campaign is got which is: " + camp.getName());
		String newQuery_text = getNewQuery().getQueText();
		newQuery.setQueText(newQuery_text);
		newQuery.setCampaign(camp);
		QueDAO quDAO = new QueDAO();
		quDAO.add(newQuery);
		int z = newQuery.getIdQue();
		System.out.println("query is saved which is: " + newQuery.getQueText());

		QueryExecuter qe = new QueryExecuter();
		qe.getQueriedTweets(z);
		return SUCCESS;
	}

	@Override
	public Que getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Que getNewQuery() {
		return newQuery;
	}

	public void setNewQuery(Que newQuery) {
		this.newQuery = newQuery;
	}

	public Set<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}

	public String getSelectedQuery() {
		return selectedQuery;
	}

	public void setSelectedQuery(String selectedQuery) {
		this.selectedQuery = selectedQuery;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public Set<Que> getQueries() {
		return queries;
	}

	public void setQueries(Set<Que> queries) {
		this.queries = queries;
	}

	public String getIdCampaign() {
		return idCampaign;
	}

	public void setIdCampaign(String idCampaign) {
		this.idCampaign = idCampaign;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getPointsOnMap() {
		return pointsOnMap;
	}

	public void setPointsOnMap(int pointsOnMap) {
		this.pointsOnMap = pointsOnMap;
	}

	public List<TweetGeoLocation> getTweetGeoLocation() {
		return tweetGeoLocation;
	}

	public void setTweetGeoLocation(List<TweetGeoLocation> tweetGeoLocation) {
		this.tweetGeoLocation = tweetGeoLocation;
	}

}
