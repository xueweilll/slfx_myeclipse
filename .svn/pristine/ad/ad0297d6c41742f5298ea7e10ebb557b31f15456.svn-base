package com.benqzl.dao.material;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.material.Storage;

public interface StorageMapper {
    int deleteByPrimaryKey(List<String> updateStorages);

    int insert(List<Storage> insertStorages);

    int insertSelective(Storage record);

    Storage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);

	List<Storage> selectStorageById(List<String> list);
	
	List<Storage> selectAll(Map<String, Object> map);

	List<Storage> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);

}