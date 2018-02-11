package twarkov;

import java.util.*;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterAPI {

	protected String myUsername;
	protected String myTimeline;
	
	public TwitterAPI(String username) throws TwitterException {
		myUsername = username;
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey("GqF5MVj3CvGPY3JeGHtRAOyK2")
			.setOAuthConsumerSecret("B1KVzXvLr6gszQAzrwPrh4gPGZBuSpN48zuSV5657OjVilqSyO")
			.setOAuthAccessToken("962453336585842688-QjRP1VqCMf02Ipe9bOvvBcwofMqfAsw")
			.setOAuthAccessTokenSecret("gWlbeswOJDrjBG9o3bySclcvY3dhKbtT9d44YONQzsVrV")
			.setTweetModeExtended(true);
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		List<Status> stats = twitter.getUserTimeline(username, new Paging(1,200));
		StringBuilder tweets = new StringBuilder();
		for (Status st:stats) {
			if (st.getText().length()>4 && st.getText().substring(0, 4).equals("RT @")) continue; // get rid of retweets
			tweets.append(st.getText().replaceAll("https://t.co/\\w*", " ")).append(" "); // get rid of tweet link
		}
		
		myTimeline = tweets.toString();
	}
	
	public String getTimeline() {
		return myTimeline;
	}
	
	public String getUser() {
		return myUsername;
	}

	
	/*
	public static void main(String[] args) throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey("GqF5MVj3CvGPY3JeGHtRAOyK2")
			.setOAuthConsumerSecret("B1KVzXvLr6gszQAzrwPrh4gPGZBuSpN48zuSV5657OjVilqSyO")
			.setOAuthAccessToken("962453336585842688-QjRP1VqCMf02Ipe9bOvvBcwofMqfAsw")
			.setOAuthAccessTokenSecret("gWlbeswOJDrjBG9o3bySclcvY3dhKbtT9d44YONQzsVrV")
			.setTweetModeExtended(true);
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		List<Status> stats = twitter.getUserTimeline("Coder_Coda", new Paging(1,30));
		//StringBuilder tweets = new StringBuilder();
		for (Status st:stats) {
			if (st.getText().substring(0, 4).equals("RT @")) continue; // get rid of retweets
			//tweets.append(st.getText().replaceAll("https://t.co/\\w*", " ")); // get rid of tweet link
			System.out.println(st.getText());
		}
	}
	*/
}
