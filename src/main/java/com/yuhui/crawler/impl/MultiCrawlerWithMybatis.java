package com.yuhui.crawler.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuhui.crawler.Crawler;
import com.yuhui.crawler.mapper.PlayListMapper;
import com.yuhui.crawler.mapper.SongMapper;
import com.yuhui.crawler.mapper.WebPageMapper;
import com.yuhui.crawler.model.PlayList;
import com.yuhui.crawler.model.Song;
import com.yuhui.crawler.model.WebPage;
import com.yuhui.crawler.model.WebPage.PageType;
import com.yuhui.crawler.model.WebPage.Status;

@Component
public class MultiCrawlerWithMybatis implements Crawler {
    
    public static final Integer MAX_THREADS = 100;
    
    @Autowired
    private WebPageMapper webPageMapper;
    
    @Autowired
    private SongMapper songMapper;
    
    @Autowired
    private PlayListMapper playListMapper;
    
    @Override
    public void initCrawlerList() {
        for(int i = 0; i < 43; i++) {
        		WebPage webPage = new WebPage("http://music.163.com/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset="  + (i * 35), PageType.playlists);
            webPage.setId("seed"+i);
//            System.out.println(webPage);
        		webPageMapper.save(webPage);
        }
    }

    @Override
    public synchronized WebPage getUnCrawlPage() {
        WebPage webPage = webPageMapper.findTopByStatus(Status.uncrawl);
        if(webPage == null) {
            return null;
        }
        webPageMapper.updateStatus(webPage.getId(), Status.crawled);
        return webPage;
    }
    
    @Override
    public synchronized List<WebPage> addToCrawlList(List<WebPage> webPages) {
        for(WebPage webPage:webPages) {
        		if(webPageMapper.findById(webPage.getId()) != null) {continue;}
        		if(webPage.getTitle().length()>21688) {
        			webPageMapper.updateStatus(webPage.getId(), Status.crawled);
        			continue;
        		}
        		webPageMapper.save(webPage);
        }
        return webPages;
    }
    
    @Override
    public synchronized Song saveSong(Song song) {
    		songMapper.save(song);
    		return song;
    }
    
    @Override
    public List<Song> getSongs() {
    		return songMapper.findAll();
    }

    @Override
    public void doRun(){
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        for(int i = 0; i < MAX_THREADS; i++) {
            executorService.execute(new MultiCrawlerThread(this));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	@Override
	public synchronized PlayList savePlayList(PlayList playList) {
		playListMapper.save(playList);
		return playList;
	}
    
}
