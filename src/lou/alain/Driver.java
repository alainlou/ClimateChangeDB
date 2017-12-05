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
	
	//get your own
	static String [] keys = {};
	
	public static void getTweets(String query, int n, String filename) throws FileNotFoundException, UnsupportedEncodingException, TwitterException, InterruptedException{
		PrintWriter pw = new PrintWriter(filename + ".txt", "UTF-8");
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(keys[0]).setOAuthConsumerSecret(keys[1]).setOAuthAccessToken(keys[2]).setOAuthAccessTokenSecret(keys[3]);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter t = tf.getInstance();
		
		//Query
		Query q =  new Query(query);
		q.setGeoCode(new GeoLocation(43.653908, -79.384293), 1000000, Query.KILOMETERS);
		
		int numTweets = n;
		int count = 0;
		boolean flag = true;
		
		while(flag){
			Thread.sleep(61000);
			q.setCount(100);
			QueryResult r = t.search(q);
			for(Status s: r.getTweets()){
				if(s.getGeoLocation() != null){
					System.out.println(s.getText());
					pw.println(s.getGeoLocation() + "\t" + s.getText());
					count++;
					if(count >= numTweets){
						flag = false;
						pw.close();
						break;
					}
				}
			}
			q.setMaxId(r.getTweets().get(r.getTweets().size()-1).getId());//gets the last ID of the last queried - only returns the next batch below this
		}	
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, TwitterException, InterruptedException{
		getTweets("#ClimateChange", 512, "temp3");
	}

}
