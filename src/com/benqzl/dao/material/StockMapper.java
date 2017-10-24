package com.benqzl.dao.material;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.material.Stock;
import com.benqzl.pojo.system.Material;

public interface StockMapper {
    int deleteByPrimaryKey(String id);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(String id);

	List<Material> findMaterials(Map<String, Object> map);

	List<Stock> findByPage(Map<String, Object> map);

	int pageCount(Map<String, Object> map);

	int updateState(Map<String, Object> map);

	List<Stock> findStockAll(Map<String, Object> map);

	Stock selectByIdAndType(Map<String, Object> map);
}