package lou.alain;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Driver {
	
	static String [] keys = {};//get your own
			
	public static void main(String[] args) throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(keys[0]).setOAuthConsumerSecret(keys[1]).setOAuthAccessToken(keys[2]).setOAuthAccessTokenSecret(keys[3]);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter t = tf.getInstance();
		
		
		Query q =  new Query("#ClimateChange");
		q.setCount(15);
		QueryResult r = t.search(q);
		for(Status s: r.getTweets()){
			System.out.println(s);
		}

	}

}
