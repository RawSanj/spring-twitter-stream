package com.rawsanj.tweet.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.social.twitter.api.FilterStreamParameters;
import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.UserStreamParameters;
import org.springframework.social.twitter.api.UserStreamParameters.WithOptions;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class StreamService {

	private final Logger log = LoggerFactory.getLogger(StreamService.class);

	@Autowired
	private Twitter twitter;

	@Async
	public Model streamApi(Model model, int time) throws InterruptedException{

		List<Tweet> tweets = new ArrayList<>();

    	List<StreamListener> listeners = new ArrayList<StreamListener>();
    	StreamListener streamListener = new StreamListener() {

			@Override
			public void onWarning(StreamWarningEvent warningEvent) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTweet(Tweet tweet) {
				//System.out.println(tweet.getUser().getName() + " : " + tweet.getText());
				log.info("User '{}', Tweeted : {}, from ; {}", tweet.getUser().getName() , tweet.getText(), tweet.getUser().getLocation());
				tweets.add(tweet);
				model.addAttribute("tweets", tweets);
			}

			@Override
			public void onLimit(int numberOfLimitedTweets) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDelete(StreamDeleteEvent deleteEvent) {
				// TODO Auto-generated method stub

			}
		};

		listeners.add(streamListener);
		//UserStreamParameters params = new UserStreamParameters().with(WithOptions.FOLLOWINGS).includeReplies(true);
//		Stream userStream = twitter.streamingOperations().sample(listeners);
		Float west=-122.75f;
		Float south=36.8f;
		Float east=-121.75f;
		Float north = 37.8f;

		FilterStreamParameters filterStreamParameters = new FilterStreamParameters();
		filterStreamParameters.addLocation(west, south, east, north);

		Stream userStream = twitter.streamingOperations().filter(filterStreamParameters, listeners);
//		twitter.streamingOperations().sample(listeners);

		Thread.sleep(time);
		userStream.close();
		return model;

	//	log.warn("Could not save Items for '{}', exception is: {}", blog.getName(), e.getMessage());
	}

//	public void streamFireApi(int time,  TwitterTemplate twitterTemplate) throws InterruptedException{
//
//		List<Tweet> tweets = new ArrayList<>();
//
//		List<StreamListener> listeners = new ArrayList<StreamListener>();
//		StreamListener streamListener = new StreamListener() {
//
//			@Override
//			public void onWarning(StreamWarningEvent warningEvent) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onTweet(Tweet tweet) {
//				//System.out.println(tweet.getUser().getName() + " : " + tweet.getText());
//				log.info("User '{}', Tweeted : {}, from ; {}", tweet.getUser().getName() , tweet.getText(), tweet.getUser().getLocation());
//
//			}
//
//			@Override
//			public void onLimit(int numberOfLimitedTweets) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onDelete(StreamDeleteEvent deleteEvent) {
//				// TODO Auto-generated method stub
//
//			}
//		};
//
//		listeners.add(streamListener);
//		//UserStreamParameters params = new UserStreamParameters().with(WithOptions.FOLLOWINGS).includeReplies(true);
////		Stream userStream = twitter.streamingOperations().sample(listeners);
//		Float west=-122.75f;
//		Float south=36.8f;
//		Float east=-121.75f;
//		Float north = 37.8f;
//
//		FilterStreamParameters filterStreamParameters = new FilterStreamParameters();
//		filterStreamParameters.addLocation(west, south, east, north);
//
//		Stream userStream = twitterTemplate.streamingOperations().filter(filterStreamParameters, listeners);
////		twitter.streamingOperations().sample(listeners);
//
//		Thread.sleep(time);
//		userStream.close();
//		//	log.warn("Could not save Items for '{}', exception is: {}", blog.getName(), e.getMessage());
//	}

}
