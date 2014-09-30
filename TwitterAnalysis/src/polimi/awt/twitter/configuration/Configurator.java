package polimi.awt.twitter.configuration;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Configurator 
{
	public Twitter configure()
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("xBV1RirUKlyNbDF2uXtEw")
		  .setOAuthConsumerSecret("zMRPthNnpWmcxIZfAUd2q9nDHztPyGORXY5mqYQCYMk")
		  .setOAuthAccessToken("362554654-sONDJMi1JMm0qBtGOxwE6OMurTpv9HdRibba0ufu")
		  .setOAuthAccessTokenSecret("wIFVTQdX6BrXg3uQrnWKwjmfkJ1NxBE3PiqG1961Dc");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter tw = tf.getInstance();
		return tw;
	}
}
