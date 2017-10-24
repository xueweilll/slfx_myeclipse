package com.benqzl.dao.material;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.material.MaterialManage;

public interface MaterialManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(List<MaterialManage> manages);

    int insertSelective(MaterialManage record);

    MaterialManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MaterialManage record);

    int updateByPrimaryKey(MaterialManage record);

	List<MaterialManage> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);
	
	List<MaterialManage> selectByTime(Map<String, Object> map);
}