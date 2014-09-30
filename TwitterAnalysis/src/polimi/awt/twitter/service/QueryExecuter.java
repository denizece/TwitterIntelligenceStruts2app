package polimi.awt.twitter.service;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import polimi.awt.twitter.dao.QueDAO;
import polimi.awt.twitter.dao.TweetDAO;
import polimi.awt.twitter.entity.Tweet;
import polimi.awt.twitter.entity.Que;
import polimi.awt.twitter.util.HibernateUtil;
import polimi.awt.twitter.configuration.Configurator;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;
import org.apache.struts2.ServletActionContext;


public class QueryExecuter {	

	public void getQueriedTweets(int id){
		
		System.out.println("search in twitter started");
		
		Configurator c = new Configurator();
		Twitter tw = null;
		tw = c.configure();
		String username = "";		
		int resultcount = 0;
		GeoLocation geoLocation;
		String geoLoc = null;
		
		TweetDAO tDAO = new TweetDAO();
		QueDAO quDAO = new QueDAO();
		Que qu = quDAO.findById(id);
		String q_tweet = qu.getQueText();
		
		Query q = new Query(q_tweet);
		
		setSince_Yesterday(q);

		QueryResult result = null;
		System.out.println("search form");			
		
		List<Status> tweets = null;
		
		try {
			do {
				result = tw.search(q);
				tweets = result.getTweets();
				//List<Status> twHour = setSince_LastHour(tweets);
				//List<Status> twMin = setSince_LastMinute(tweets);
				if (tweets != null) 
				{
					for (Status tweet : tweets) 
					{
						if (tweet != null && resultcount < 100) 
						{
							User user = tweet.getUser();
							if (user != null )
							username = user.getScreenName();
							geoLocation = tweet.getGeoLocation();
							if (geoLocation != null)
								geoLoc = geoLocation.toString();
							
							String msg = tweet.getText();
							Date d = tweet.getCreatedAt();
							System.out.println("Tweet by " + username + " at " + d + ": " + msg);
							
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(d);
							int day = calendar.get(Calendar.DAY_OF_MONTH);
							int month = calendar.get(Calendar.MONTH);
							int year = calendar.get(Calendar.YEAR);
							int hour = calendar.get(Calendar.HOUR_OF_DAY);
							int minute = calendar.get(Calendar.MINUTE);
								
							Tweet t = new Tweet();
								
							t.setTweetText(msg);
							t.setQue(qu);
							t.setDate(d);
							t.setDay(day);
							t.setHour(hour);
							t.setMonth(month);
							t.setYear(year);
							t.setMinute(minute);
							t.setAuthor(username);
							if (geoLoc == null)
								t.setGeoLocation("not available");
							else
								t.setGeoLocation(geoLoc);
								
							tDAO.add(t);				
								
							resultcount++;
						}
					}
				}
			} 
			while ((q = result.nextQuery()) != null);

		} 		
		
		catch (TwitterException e) 
		{
			e.printStackTrace();
		}	
				
		System.out.println("COUNT  =  "+resultcount);
		resultcount = 0;
	}


	public void setSince_Yesterday(Query q) {
		q.setCount(100);

		long endtime = new Date().getTime();
		//long starttime = endtime-(1000*60);
		long starttime = endtime - (1000*3600*24);
		
		Date since = new Date(starttime);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sinceStr = df.format(since);
		q.setSince(sinceStr);
		
	}
	
	public List<Status> setSince_LastMinute(List<Status> tw)
	{
		ArrayList<Status> minlist = new ArrayList<Status>(tw);

		long endtime = new Date().getTime();
		long starttime = endtime-(1000*60);
		for (int i = 0; i<minlist.size(); i++)
		{
			if(minlist.get(i).getCreatedAt().before(new Date(starttime)))
				minlist.remove(i);
		}

		return minlist;
	}
	
	   public List<Status> setSince_LastHour(List<Status> tw)
		{
			List<Status> hourlist = new ArrayList<Status>(tw);

			long endtime = new Date().getTime();
			long starttime = endtime-(1000*3600);
			for (int i = 0; i < hourlist.size(); i++)
			{
				if(hourlist.get(i).getCreatedAt().before(new Date(starttime)))
					hourlist.remove(i);
			}

			return hourlist;
		}
}
