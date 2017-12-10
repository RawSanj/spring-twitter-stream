package com.rawsanj.tweet.controller;

import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/")
public class SearchController {
	
	private final TwitterTemplate twitterTemplate;
    
    public SearchController(TwitterTemplate twitterTemplate) {
        this.twitterTemplate=twitterTemplate;
    }

    @GetMapping(path = "tweet")
    public String searchTwitter(Model model, @RequestParam String search) {
    	
    	int count = 200;
    	
        SearchResults results = twitterTemplate.searchOperations().search(
        	    new SearchParameters(search)
        	        .resultType(SearchParameters.ResultType.RECENT)
        	        .count(count));
        
        List<Tweet> tweets = results.getTweets();        
        model.addAttribute("tweets", tweets);
        model.addAttribute("count", count);
        model.addAttribute("search", search);
        
        return "search";
    }

}