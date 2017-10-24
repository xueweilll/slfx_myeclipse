package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Assets;

public interface AssetsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Assets record);

    int insertSelective(Assets record);

    Assets selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Assets record);

    int updateByPrimaryKey(Assets record);

	int pageCount(Map<String, Object> map);

	List<Assets> findByPage(Map<String, Object> map);

	Assets selectDownByPrimaryKey(String id);

	Assets selectQrByPrimaryKey(String id);
}