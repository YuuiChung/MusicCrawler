package com.yrw.crawler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yrw.crawler.Crawler;
import com.yrw.crawler.HtmlParser;
import com.yrw.crawler.model.PlayList;
import com.yrw.crawler.model.Song;
import com.yrw.crawler.model.WebPage;
import com.yrw.crawler.model.WebPage.PageType;

public class BasicCrawler implements Crawler{
	
	private final HtmlParser htmlParser = new HtmlParser();
	//用List实现爬虫队列
    public List<WebPage> crawlerList;
    //已爬歌曲存放的地方
    public List<Song> songs = new ArrayList<>();
    //已爬歌单存放的地方
    public List<PlayList> playLists = new ArrayList<>();
    

	@Override
	public void initCrawlerList() {
		crawlerList = new ArrayList<WebPage>();
//      for(int i = 0; i < 43; i++) {
//          crawlerList.add(new WebPage("http://music.163.com/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset="  + (i * 35), PageType.playlists));
//      }
		crawlerList.add(new WebPage("http://music.163.com/playlist?id=997492845", PageType.playlist));
	}

	@Override
	public WebPage getUnCrawlPage() {
		if(crawlerList.isEmpty()) return null;
		//从队列中取出一个页面
		WebPage page = crawlerList.get(0);
		//标记为已爬（删除）
		crawlerList.remove(0);
		return page;
	}

	@Override
	public List<WebPage> addToCrawlList(List<WebPage> webPages) {
		crawlerList.addAll(webPages);
		return crawlerList;
	}

	@Override
	public Song saveSong(Song song) {
		songs.add(song);
		return song;
	}

	@Override
	public List<Song> getSongs() {
		return songs;
	}
	
	@Override
	public PlayList savePlayList(PlayList playList) {
		playLists.add(playList);
		return playList;
	}

	@Override
	public void doRun() {
		WebPage webPage;
        while ((webPage = getUnCrawlPage()) != null) {
        		if(webPage.getType() == PageType.playlists) {
        			//歌单列表页面
        			addToCrawlList(htmlParser.parsePlaylists(webPage.getUrl()));
        		}else if(webPage.getType() == PageType.playlist) {
        			//歌单页面
        			addToCrawlList(htmlParser.parsePlaylist(webPage.getUrl()));
        		}else {
        			//歌曲页面
        			Long comments = htmlParser.parseSong(webPage.getUrl());
        			saveSong(new Song(webPage.getUrl(), webPage.getTitle(), comments));
        		}
        }
		
	}

	public static <T> void main(String[] args) throws Exception {
        Date startTime = new Date();
        Crawler crawler = new BasicCrawler();
        crawler.initCrawlerList();
        crawler.run();
        for(Song song : crawler.getSongs()) {
            System.out.println(song);
        }
        System.out.println("花费时间：" + (new Date().getTime() - startTime.getTime()));
    }

	
}
