package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Assets;

public interface AssetsService {
	int pageCount(Map<String, Object> map);
	List<Assets> findByPage(Map<String, Object> map);
	Assets selectByPrimaryKey(String id);
	void insert(Assets assets);
	void updateByPrimaryKey(Assets assets);
	void deleteByPrimaryKey(String id);
	Assets selectDownByPrimaryKey(String id);
	Assets selectQrByPrimaryKey(java.lang.String id);
}
