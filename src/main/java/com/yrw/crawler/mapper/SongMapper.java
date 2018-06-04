package com.yrw.crawler.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yrw.crawler.model.Song;

@Mapper
public interface SongMapper {
	
	public void save(Song song);
	
	public String findById(@Param("id") Long id);
	
	public List<Song> findAll();
}
