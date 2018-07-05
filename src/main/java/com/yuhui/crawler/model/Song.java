package com.yuhui.crawler.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

public class Song implements Serializable{
    
	private Long id;
    private String url;
    private String title;
    private String author;
    private String image;
    private String album;
    private Long commentCount;
    
    public Song() {
    		super();
    }
    
    public Song(String url, String title) {
        this();
        this.id = Long.parseLong(url.substring(30));
        this.url = url;
        this.title = title;
    }
    
    public Song(String url, String title, Long commentCount) {
    		this(url, title);
        this.commentCount = commentCount;
    }
    
	public Song(Long id, String url, String title, String author, String image, String album, Long commentCount) {
		super();
		this.id = id;
		this.url = url;
		this.title = title;
		this.author = author;
		this.image = image;
		this.album = album;
		this.commentCount = commentCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", url=" + url + ", title=" + title + ", author=" + author + ", image=" + image
				+ ", album=" + album + ", commentCount=" + commentCount + "]";
	}

    

}
