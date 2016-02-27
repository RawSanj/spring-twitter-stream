package com.rawsanj.tweet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@SpringBootApplication
public class Application {
	
	@Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    TwitterTemplate getTwtTemplate(){
        return new TwitterTemplate(environment.getProperty("consumerKey"), environment.getProperty("consumerSecret"), environment.getProperty("accessToken"), environment.getProperty("accessTokenSecret"));
    }

} 