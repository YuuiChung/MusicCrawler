package com.yuhui.crawler.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhui.crawler.model.PlayList;

@Mapper
public interface PlayListMapper {
	
	public void save(PlayList playList);
	
	public String findById(@Param("id") Long id);
	
	public List<PlayList> findAll();
	
	public List<PlayList> findAllByPlayCount();
}
