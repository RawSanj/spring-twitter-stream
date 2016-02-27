package com.rawsanj.tweet.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TweetZ {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    public TweetZ() {
		// TODO Auto-generated constructor stub
	}
    
    public TweetZ(String text, Date createdAt, String fromUser, String languageCode,String source) {
		this.text = text;
		this.createdAt = createdAt;
		this.fromUser =  fromUser;
		this.languageCode = languageCode;
		this.source = source;
	}
    
	private String text;
	
	private Date createdAt;
	
	private String fromUser;
	
	private String languageCode;
	
	private String source;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
   
}
