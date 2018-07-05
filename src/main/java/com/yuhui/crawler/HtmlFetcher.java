package com.yuhui.crawler;


import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class HtmlFetcher {

    public String fetch(String url) {
        try {
            Connection.Response response = Jsoup.connect(url)
            		.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
            		.timeout(3000).execute();
            return response.statusCode() / 100 == 2 ? response.body() : null;
        } catch (Exception e) {
            return null;
        }
    }
    
}
