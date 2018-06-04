package com.yrw.crawler.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yrw.crawler.impl.MultiCrawlerWithMybatis;
import com.yrw.crawler.mapper.WebPageMapper;
import com.yrw.crawler.model.WebPage;
import com.yrw.crawler.model.WebPage.PageType;
import com.yrw.crawler.model.WebPage.Status;

@Component
public class UpdateSchedule {

	@Autowired
    private MultiCrawlerWithMybatis multiCrawler;
    
    @Autowired
    private WebPageMapper webPageMapper;
	
    @Scheduled(cron = "0 0 1 * * *")
    public void update() {
    		//更新歌曲的评论数量
        List<WebPage> webPages = webPageMapper.findByType(PageType.song);
        for(WebPage page:webPages) {
        		webPageMapper.updateStatus(page.getId(), Status.uncrawl);
        }
        multiCrawler.doRun();
    }
}
