package com.yuhui.crawler.impl;


import com.yuhui.crawler.Crawler;
import com.yuhui.crawler.HtmlParser;
import com.yuhui.crawler.model.PlayList;
import com.yuhui.crawler.model.Song;
import com.yuhui.crawler.model.WebPage;
import com.yuhui.crawler.model.WebPage.PageType;

public class MultiCrawlerThread implements Runnable{
	
	private final Crawler multiCrawler;
    private final HtmlParser htmlParser = new HtmlParser();

    public MultiCrawlerThread(Crawler multiCrawler) {
        super();
        this.multiCrawler = multiCrawler;
    }
    
	@Override
	public void run() {
		WebPage webPage;
        int getUnCrawlPageTimes = 0;
        while (true) {
            webPage = multiCrawler.getUnCrawlPage();
            //防止开始时爬虫队列中未爬取页面过少，线程拿不到足够的未爬页面而过早退出
            if(webPage == null) {
                if(getUnCrawlPageTimes > 10) {
                    break;
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    getUnCrawlPageTimes++;
                    continue;
                }
            }
            
	        getUnCrawlPageTimes = 0;
			if(webPage.getType() == PageType.playlists) {
			    //歌单列表页面
			    multiCrawler.addToCrawlList(htmlParser.parsePlaylists(webPage.getUrl()));
			}else if(webPage.getType() == PageType.playlist) {
			    //歌单页面
			    multiCrawler.addToCrawlList(htmlParser.parsePlaylist(webPage.getUrl()));
			    PlayList playList =  htmlParser.parsePl(webPage.getUrl());
			    multiCrawler.savePlayList(playList);
			}else {
				//歌曲页面
			    Long comments = htmlParser.parseSong(webPage.getUrl());
			    Song song = htmlParser.parsePsong(webPage.getUrl());
			    song.setTitle(webPage.getTitle());
			   	song.setUrl(webPage.getUrl());
			   	song.setCommentCount(comments);
			    multiCrawler.saveSong(song);
			}
        }
	}
}
