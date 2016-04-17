package com.rawsanj.tweet.controller;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.rawsanj.tweet.util.TwitterProfileWithEmail;

@Controller
@RequestMapping("/userinfo")
public class UserInfoController {
	    
	private Twitter twitter;

    private ConnectionRepository connectionRepository;
    
    private TwitterTemplate twitterTemplate;

    @Inject
    public UserInfoController(Twitter twitter, ConnectionRepository connectionRepository, TwitterTemplate twitterTemplate) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
        this.twitterTemplate = twitterTemplate;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String helloTwitter(Model model) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }
        
        Connection<Twitter> con = connectionRepository.findPrimaryConnection(Twitter.class);
    	//ConnectionKey key = con.getKey();
    	UserProfile userProfile = con.fetchUserProfile();
    	System.out.println("Email address of Logged In User: "+ userProfile.getEmail());
    	System.out.println("Name of Logged In User: "+ userProfile.getFirstName() + " " + userProfile.getLastName()); 
                
        RestTemplate restTemplate = twitterTemplate.getRestTemplate();
               
        TwitterProfileWithEmail response = restTemplate.getForObject("https://api.twitter.com/1.1/account/verify_credentials.json?include_email=true", TwitterProfileWithEmail.class);
        System.out.println("Name: "+ response.getName()+ " Email: "+ response.getEmail());

        model.addAttribute("accountInfo", response);
        return "hello";
    }
    
}