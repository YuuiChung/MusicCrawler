# 网易云音乐爬虫
http://www.wkis.top:8080/crawler/songs
## 效果
![](https://github.com/YuuiChung/MusicCrawler/blob/master/src/main/resources/static/webImage/1.png)
![](https://github.com/YuuiChung/MusicCrawler/blob/master/src/main/resources/static/webImage/2.png)
![](https://github.com/YuuiChung/MusicCrawler/blob/master/src/main/resources/static/webImage/3.png)
![](https://github.com/YuuiChung/MusicCrawler/blob/master/src/main/resources/static/webImage/4.png)

## 使用方法：
#### 建立3个表：
表web_page<br>
CREATE TABLE IF NOT EXISTS `web_page` (<br>
    `id` varchar(35) NOT NULL,<br>
    `url` varchar(100) NOT NULL,<br>
    `title` varchar(21688) NOT NULL,<br>
    `status` char(10) NOT NULL,<br>
    `type` char(10) NOT NULL,<br>
    PRIMARY KEY (`id`)<br>
) ENGINE=InnoDB DEFAULT CHARSET=utf8;<br>

表playlist
CREATE TABLE IF NOT EXISTS `playlist` (<br>
`id` varchar(15) DEFAULT NULL,<br>
  `name` varchar(255) DEFAULT NULL,<br>
  `url` varchar(255) DEFAULT NULL,<br>
  `author` varchar(255) DEFAULT NULL,<br>
  `image` varchar(255) DEFAULT NULL,<br>
  `playCount` int(11) DEFAULT NULL,<br>
  `collectCount` int(11) DEFAULT NULL,<br>
  `introduction` text,<br>
  `tag` varchar(255) DEFAULT NULL<br>
) ENGINE=InnoDB DEFAULT CHARSET=utf8;<br>

表song
CREATE TABLE IF NOT EXISTS `song` (<br>
  `id` varchar(35) DEFAULT NULL,<br>
  `url` varchar(255) DEFAULT NULL,<br>
  `title` varchar(255) DEFAULT NULL,<br>
  `author` varchar(255) DEFAULT NULL,<br>
  `image` varchar(255) DEFAULT NULL,<br>
  `album` varchar(255) DEFAULT NULL,<br>
  `commentCount` int(20) DEFAULT NULL<br>
) ENGINE=InnoDB DEFAULT CHARSET=utf8;<br>
#### 访问步骤：
1、访问localhost:8080/crawler/start 初始化爬虫资源<br>
2、访问localhost:8080/crawler/index.html 访问首页

**这三个表存放页面信息，分为存放：**

[歌单列表页面](http://music.163.com/#/discover/playlist)

[歌单页面](http://music.163.com/#/discover/playlist)

[歌曲页面](http://music.163.com/#/song?id=143238)


