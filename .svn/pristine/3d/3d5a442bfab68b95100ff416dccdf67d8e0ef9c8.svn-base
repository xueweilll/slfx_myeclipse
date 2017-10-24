package com.benqzl.service.material;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.material.Stock;
import com.benqzl.pojo.material.StockItems;
import com.benqzl.pojo.system.Material;
import com.benqzl.pojo.system.User;

public interface StockService {
	List<Material> findMaterials(Map<String, Object> map) throws Exception;

	void insert(Stock stock) throws Exception;

	List<Stock> findByPage(Map<String, Object> map) throws Exception;

	int pageCount(Map<String, Object> map)throws Exception;

	Stock selectById(Map<String, Object> map) throws Exception;

	List<StockItems> selectItemsByStockId(String id) throws Exception;

	void updateState(String id,Long state) throws Exception;

	List<Stock> findStockAll(String id) throws Exception;

	void insertStockItems(List<StockItems> strings1) throws Exception;

	void insertProfitAndLoss(String id) throws Exception;

	void delete(String id)throws Exception;
	
	void monthReceipts()throws Exception;

	Stock findStockById(String id)throws Exception;
	User selectUser(String id)throws Exception;
	
	Map<String, Object> findByYear()throws Exception;
	

}
