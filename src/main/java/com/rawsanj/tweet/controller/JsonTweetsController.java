package com.rawsanj.tweet.controller;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/jsontweets")
public class JsonTweetsController {
	    
    private ConnectionRepository connectionRepository;
    
    private TwitterTemplate twitterTemplate;

    @Inject
    public JsonTweetsController(Twitter twitter, ConnectionRepository connectionRepository, TwitterTemplate twitterTemplate) {
        this.connectionRepository = connectionRepository;
        this.twitterTemplate = twitterTemplate;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String helloTwitter(@RequestParam String search, Model model) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }
        
        Connection<Twitter> con = connectionRepository.findPrimaryConnection(Twitter.class);
    	UserProfile userProfile = con.fetchUserProfile();
        String username =  userProfile.getFirstName() + " " + userProfile.getLastName(); 
    	        
        RestTemplate restTemplate = twitterTemplate.getRestTemplate();
               
        String response = restTemplate.getForObject("https://api.twitter.com/1.1/search/tweets.json?q="+search, String.class);
        System.out.println("JSON Response From Twitter: "+response);
        
        model.addAttribute("jsonstring", response);
        model.addAttribute("username", username);
        
        return "json";
    }
    
}