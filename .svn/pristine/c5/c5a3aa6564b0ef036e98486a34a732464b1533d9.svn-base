package com.benqzl.dao.material;

import java.util.List;

import com.benqzl.pojo.material.StockItems;

public interface StockItemsMapper {
    int deleteByPrimaryKey(String id);
    
    int deleteByPrimaryById(List<String> list);

    int insert(List<StockItems> list);

    int insertSelective(StockItems record);

    StockItems selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StockItems record);

    int updateByPrimaryKey(StockItems record);
    
    List<StockItems> selectByStockId(String id);
}