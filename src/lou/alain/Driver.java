package lou.alain;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Driver {
	
	static String [] keys = //get your own
			
	public static void main(String[] args) throws TwitterException, FileNotFoundException, UnsupportedEncodingException {
		PrintWriter pw = new PrintWriter("data.txt", "UTF-8");
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(keys[0]).setOAuthConsumerSecret(keys[1]).setOAuthAccessToken(keys[2]).setOAuthAccessTokenSecret(keys[3]);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter t = tf.getInstance();
		
		Query q =  new Query("#ClimateChange");
		//q.setGeoCode(new GeoLocation(43.653908, -79.384293), 1000000, Query.KILOMETERS);
		
		q.setCount(100);
		QueryResult r = t.search(q);
		for(Status s: r.getTweets()){
			System.out.println(s.getText());
			//if(s.getGeoLocation() != null){
				pw.println(s.getGeoLocation() + "\t" + s.getText());
			//}
		}
		
		pw.close();

	}

}
