package com.yuhui.crawler.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhui.crawler.model.WebPage;
import com.yuhui.crawler.model.WebPage.PageType;
import com.yuhui.crawler.model.WebPage.Status;

@Mapper
public interface WebPageMapper {
	
	public void save(WebPage webPage);
	
	//public void saveAll(List<WebPage> webPages);
	
	public void updateStatus(@Param("id") String id, @Param("status") Status status);
	
	public String findById(@Param("id") String id);
	
	public List<WebPage> findByType(@Param("type") PageType type);
	
	public WebPage findTopByStatus(@Param("status") Status status);
}
