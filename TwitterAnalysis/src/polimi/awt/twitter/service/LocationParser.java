package polimi.awt.twitter.service;

import twitter4j.GeoLocation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LocationParser {
	private GeoLocation geo;

		public GeoLocation getmyGeoLoc(String geoloc){
			if (geoloc==null)
				return null;
			if (geoloc.contentEquals("not available"))
					{this.geo=null;}
			
			else{
			Gson gson=new Gson();
			System.out.println(geoloc);
			int i=geoloc.indexOf("{");
			geoloc=geoloc.substring(i);
			this.geo = gson.fromJson(geoloc, GeoLocation.class);
			}
			return geo;
	}

}
