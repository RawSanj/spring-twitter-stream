package com.rawsanj.tweet.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Place;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.GeoTemplate;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.rawsanj.tweet.entity.TweetZ;
import com.rawsanj.tweet.repository.TweetZRepository;
import com.rawsanj.tweet.service.StreamService;

@Controller
@Component
@RequestMapping("/")
public class HelloController {
	
	private TwitterTemplate twitterTemplate;
    
    private StreamService streamService;
    
    private TweetZRepository tweetZRepository;

    @Inject
    public HelloController(StreamService streamService, TwitterTemplate twitterTemplate, TweetZRepository tweetZRepository) {
        
        this.streamService = streamService;
        this.twitterTemplate=twitterTemplate;
        this.tweetZRepository=tweetZRepository;
    }

    @RequestMapping(value = "tweet/{search}/{count}",method=RequestMethod.GET)
    public String searchTwitter(Model model, @PathVariable String search, @PathVariable int count) {

        SearchResults results = twitterTemplate.searchOperations().search(
        	    new SearchParameters(search)
        	        .resultType(SearchParameters.ResultType.RECENT)
        	        .count(count));
        
        List<Tweet> tweets = results.getTweets();        
        model.addAttribute("tweets", tweets);
        
        
        System.out.println("+++++++++++++++++++DEBUGGING++++++++++++++++++");
        int i =0;
        
        for (Tweet tweet : tweets) {
        	i++;
			System.out.println(i + " - "+tweet.getUser().getName() + " Tweeted : "+tweet.getText() + " from " + tweet.getUser().getLocation() 
					+ " @ " + tweet.getCreatedAt() + tweet.getUser().getLocation()  );
			
			TweetZ tweetZ = new TweetZ(tweet.getText(), tweet.getCreatedAt(), tweet.getFromUser(), tweet.getLanguageCode(), tweet.getLanguageCode());
			
			tweetZRepository.save(tweetZ);
			
		}
        System.out.println("+++++++++++++++++++++++++++++++++GeoTemnplatePlz Work++++++++++++++++++++++++");
        RestTemplate restTemplate = twitterTemplate.getRestTemplate();
        GeoTemplate geoTemplate = new GeoTemplate(restTemplate, true, true);
        List<Place> place = geoTemplate.search(37.423021, -122.083739);
        for (Place p : place) {
			System.out.println(p.getName() + " " + p.getCountry() );
		} 
        System.out.println("+++++++++++++++++++++++++++++++++GeoTemnplatePlz Work++++++++++++++++++++++++");
        
        
        return "search";
    }
    
    @RequestMapping("/stream/{time}")
    public String streamTweet(@PathVariable int time, Model model) throws InterruptedException{
        
        Model returnedmodel = streamService.streamApi(model, time);
        model = returnedmodel;

        return "stream";
    }

//    @RequestMapping("/streamfire/{time}")
//    public String streamFireTweet(@PathVariable int time) throws InterruptedException{
//
//        streamService.streamFireApi(time, twitterTemplate);
//
//        return "stream";
//    }
       

}