# MusicCrawler
springboot+mybatis的网易云音乐爬虫
# 网易云音乐爬虫
http://www.wkis.top:8080/crawler/songs


## 目录

├── com.yrw.crawler
├──   Application.java			//启动Spring boot
├──   Crawler.java			// 爬虫的接口，定义了爬虫重要方法
├──   HtmlFetcher.java			//获取Html页面的方法
├──   HtmlParser.java			//解析Html页面的方法
├──   com.yrw.crawler.impl
├──     MultiCrawlerThread.java			//爬虫的流程
├── 	MultiCrawlerWithMybatis.java		//爬虫的实现类，用mybatis实现
├──   com.yrw.crawler.controller			//controller层，用于展示爬虫的结果
├──   com.yrw.crawler.mapper			// mapper层，用mybatis实现数据持久化
├──   com.yrw.model			// 数据模型
├──   com.yrw.task			//定时任务
```
使用方法：
#### 建立4个表：
```
create table song(
    id int(11) primary key, 
    url varchar(100) not null, 
    title varchar(21688) not null, 
    comment_count int(11) not null
);
```
**song表用来存放歌曲信息。**
```
create table playlists_page(
    id varchar(35) primary key, 
    url varchar(100) not null,  
    title varchar(21688) not null, 
    status char(10) not null, 
    type char(10) not null
);

create table playlist_page(
    ......
);

create table song_page(
    ......
);
```
**这三个表存放页面信息，分为存放：**

[歌单列表页面](http://music.163.com/#/discover/playlist)

[歌单页面](http://music.163.com/#/discover/playlist)

[歌曲页面](http://music.163.com/#/song?id=143238)
