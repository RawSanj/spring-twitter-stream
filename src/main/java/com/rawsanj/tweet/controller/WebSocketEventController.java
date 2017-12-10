package com.rawsanj.tweet.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.rawsanj.tweet.service.StreamTweetEventService;

@Controller
@RequestMapping("/")
public class WebSocketEventController {
	    
    private StreamTweetEventService streamTweetEventService;
    
    private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public WebSocketEventController(StreamTweetEventService streamTweetEventService) {
        this.streamTweetEventService = streamTweetEventService;
    }
    
    @RequestMapping("/")
    public String streamTweetAsEvents(){
        return "events";
    }  
    
    @RequestMapping("/tweetLocation")
    public SseEmitter streamTweets() throws InterruptedException{
    	
    	SseEmitter sseEmitter = new SseEmitter();
    	emitters.add(sseEmitter);
    	sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
    	
    	streamTweetEventService.streamTweetEvent(emitters);
    	
    	return sseEmitter;
    }

}