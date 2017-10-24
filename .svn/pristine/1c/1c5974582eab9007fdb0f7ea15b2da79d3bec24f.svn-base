package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Material;

public interface MaterialMapper {
    int deleteByPrimaryKey(String id);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

	int pageCount(Map<String, Object> map);

	List<Material> findByPage(Map<String, Object> map);

	Material selectMaterialByPrimaryKey(String id);

	int selectcount();
}