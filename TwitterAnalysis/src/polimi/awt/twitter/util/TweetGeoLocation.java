package polimi.awt.twitter.util;

import twitter4j.GeoLocation;

public class TweetGeoLocation {

	private int id;
	private String tweetText;
	// private GeoLocation geoLocation;

	private double latitude;
	private double longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTweetText() {
		return tweetText;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = trimSurrogates(tweetText);
	}

	public String trimSurrogates(String input) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (!Character.isHighSurrogate(ch) && !Character.isLowSurrogate(ch)) {
				sb.append(ch);
			}
		}
		return sb.toString().replace("@", "AT_").replace("\"", "'").replace("//", "/").replace("\n", " ").replace("\r", " ");
	}

}
