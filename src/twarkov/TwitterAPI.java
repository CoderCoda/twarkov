package twarkov;

import java.util.*;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterAPI {
	
	protected String timeline;
	
	public TwitterAPI(String username) throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey("GqF5MVj3CvGPY3JeGHtRAOyK2")
			.setOAuthConsumerSecret("B1KVzXvLr6gszQAzrwPrh4gPGZBuSpN48zuSV5657OjVilqSyO")
			.setOAuthAccessToken("962453336585842688-QjRP1VqCMf02Ipe9bOvvBcwofMqfAsw")
			.setOAuthAccessTokenSecret("gWlbeswOJDrjBG9o3bySclcvY3dhKbtT9d44YONQzsVrV")
			.setTweetModeExtended(true);
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		List<Status> stats = twitter.getUserTimeline(username);
		StringBuilder tweets = new StringBuilder();
		for (Status st:stats) {
			if (st.getText().substring(0, 4).equals("RT @")) continue;
			tweets.append(st.getText().replaceAll("https://t.co/\\w*", " "));
		}
		
		timeline = tweets.toString();
	}
	
	public String getTimeline() {
		return timeline;
	}
}
