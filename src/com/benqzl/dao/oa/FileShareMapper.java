package com.benqzl.dao.oa;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.oa.FileShare;

public interface FileShareMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileShare record);

    int insertSelective(FileShare record);

    FileShare selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileShare record);

    int updateByPrimaryKey(FileShare record);

//	List<FileShare> findByPage(Map<String, Object> map);
	
	List<FileShare> findMyShare(Map<String, Object> map);
	
	
	List<FileShare> findShareToMe(Map<String, Object> map);

	int pageCount(Map<String, Object> map);

	int deleteFilesShareByFilesId(String id);
}