package com.yrw.crawler.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

public class WebPage implements Serializable{
    
    public enum PageType {
        song, playlist, playlists;
    }
    
    public enum Status {
        crawled, uncrawl;
    }
    
    private String id;

    private String url;
    
    private String title;
    
    private PageType type;
    
    private Status status;
    
    public WebPage() {
    		super();
    }
    
    public WebPage(String url, PageType type) {
    		this();
        this.id = url.substring(22);
        this.url = url;
        this.title = " ";
        this.type = type;
        this.status = Status.uncrawl;
    }
    
    public WebPage(String url, PageType type, String title) {
    		this(url, type);
        this.title = title;
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PageType getType() {
        return type;
    }

    public void setType(PageType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "WebPage [url=" + url + ", title=" + title + ", status=" + status + "]";
    }

}
