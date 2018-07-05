package com.yuhui.crawler.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuhui.crawler.HtmlParser;
import com.yuhui.crawler.impl.MultiCrawlerWithMybatis;
import com.yuhui.crawler.mapper.PlayListMapper;
import com.yuhui.crawler.mapper.SongMapper;
import com.yuhui.crawler.model.PlayList;

@Controller
@RequestMapping("/crawler")
public class CrawlerController {
    
    @Autowired
    private MultiCrawlerWithMybatis multiCrawler;
    
    private final HtmlParser htmlParser = new HtmlParser();
    
    @Autowired
    private SongMapper songMapper;
    
    @Autowired
    private PlayListMapper playListMapper;
    
    @ResponseBody
    @GetMapping("/start")
    public String start() throws InterruptedException {
        multiCrawler.run();
        return "爬取完毕";
    }
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    
    @GetMapping("/features")
    public String features(Model model, @RequestParam("id") String id) throws IOException {
    	PlayList playlist = htmlParser.parsePlaylistSong(id);
    	model.addAttribute("plmsg",playlist);
    	return "features";
    }
    
    
    @GetMapping("/comments")
    public String comments(Model model, @RequestParam("page") Optional<Integer> page) {
    	PageHelper.startPage(page.orElse(1), 20);
        model.addAttribute("songs", new PageInfo<>(songMapper.findAll()));
        return "comments";
    }
    
    @GetMapping("/playcount")
    public String playcount(Model model, @RequestParam("page") Optional<Integer> page) {
    	PageHelper.startPage(page.orElse(1), 20);
    	model.addAttribute("playlist", new PageInfo<>(playListMapper.findAllByPlayCount()));
        return "playcount";
    }
    
    @GetMapping("/songs")
    public String songs(Model model, @RequestParam("page") Optional<Integer> page) {
    		PageHelper.startPage(page.orElse(1), 10);
        model.addAttribute("songs", new PageInfo<>(songMapper.findAll()));
        return "songs";
    }
    
    @GetMapping("/playlist")
    public String playlist(Model model, @RequestParam("page") Optional<Integer> page) {
    		PageHelper.startPage(page.orElse(1), 9);
        model.addAttribute("playlist", new PageInfo<>(playListMapper.findAll()));
        return "playlist";
    }
}