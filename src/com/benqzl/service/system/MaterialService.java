package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Material;
import com.benqzl.pojo.system.Prickle;
import com.benqzl.pojo.system.Size;

public interface MaterialService {
	int pageCount(Map<String, Object> map);
	List<Material> findByPage(Map<String, Object> map);
	int insert(Material record);
	int updateByPrimaryKey(Material record);
	int deleteByPrimaryKey(String id);
	int pageSizeCount(Map<String, Object> map);
	List<Size> findSizeByPage(Map<String, Object> map);
	int insertSize(Size record);
	int updateSizeByPrimaryKey(Size record);
	int deleteSizeByPrimaryKey(String id);
	int pagePrickleCount(Map<String, Object> map);
	List<Prickle> findPrickleByPage(Map<String, Object> map);
	int insertPrickle(Prickle record);
	int updatePrickleByPrimaryKey(Prickle record);
	int deletePrickleByPrimaryKey(String id);
	Size selectSizeByPrimaryKey(String id);
	Prickle selectPrickleByPrimaryKey(String id);
	Material selectMaterialByPrimaryKey(String id);
	List<Prickle> findPrickleName();
	List<Size> findSizeName();
	int selectcount();
	int validateprickle(String prickleid);
	int validatesize(String sizeid);
}
