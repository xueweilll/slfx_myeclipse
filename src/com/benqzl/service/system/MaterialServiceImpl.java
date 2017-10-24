package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.MaterialMapper;
import com.benqzl.dao.system.PrickleMapper;
import com.benqzl.dao.system.SizeMapper;
import com.benqzl.pojo.system.Material;
import com.benqzl.pojo.system.Prickle;
import com.benqzl.pojo.system.Size;
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialMapper materialService;
    @Autowired
    private SizeMapper sizemapper;
    @Autowired
    private PrickleMapper pricklemapper;
	@Override
	public int pageCount(Map<String, Object> map) {
		return materialService.pageCount(map);
	}

	@Override
	public List<Material> findByPage(Map<String, Object> map) {
		
		return materialService.findByPage(map);
	}

	@Override
	public int insert(Material record) {
	
		return materialService.insert(record);
	}

	@Override
	public int updateByPrimaryKey(Material record) {
		
		return materialService.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return materialService.deleteByPrimaryKey(id);
	}
	@Override
	public int insertSize(Size record) {
		return sizemapper.insertSize(record);
	}

	@Override
	public int updateSizeByPrimaryKey(Size record) {
		return sizemapper.updateSizeByPrimaryKey(record);
	}

	@Override
	public int deleteSizeByPrimaryKey(String id) {
		return sizemapper.deleteSizeByPrimaryKey(id);
	}

	@Override
	public int pagePrickleCount(Map<String, Object> map) {
		return pricklemapper.pagePrickleCount(map);
	}

	@Override
	public List<Prickle> findPrickleByPage(Map<String, Object> map) {
		return pricklemapper.findPrickleByPage(map);
	}

	@Override
	public int insertPrickle(Prickle record) {
		return pricklemapper.insertPrickle(record);
	}

	@Override
	public int updatePrickleByPrimaryKey(Prickle record) {
		return pricklemapper.updatePrickleByPrimaryKey(record);
	}

	@Override
	public int deletePrickleByPrimaryKey(String id) {
		return pricklemapper.deletePrickleByPrimaryKey(id);
	}

	@Override
	public int pageSizeCount(Map<String, Object> map) {
		return sizemapper.pageSizeCount(map);
	}

	@Override
	public List<Size> findSizeByPage(Map<String, Object> map) {
		return sizemapper.findSizeByPage(map);
	}

	@Override
	public Size selectSizeByPrimaryKey(String id) {
		return sizemapper.selectSizeByPrimaryKey(id);
	}

	@Override
	public Prickle selectPrickleByPrimaryKey(String id) {
		return pricklemapper.selectPrickleByPrimaryKey(id);
	}

	@Override
	public Material selectMaterialByPrimaryKey(String id) {
		return materialService.selectMaterialByPrimaryKey(id);
	}

	@Override
	public List<Prickle> findPrickleName() {
		return pricklemapper.findPrickleName();
	}

	@Override
	public List<Size> findSizeName() {	
		return sizemapper.findSizeName();
	}

	@Override
	public int selectcount() {
		// TODO Auto-generated method stub
		return materialService.selectcount();
	}

	@Override
	public int validateprickle(String prickleid) {
		return pricklemapper.validateprickle(prickleid);
	}

	@Override
	public int validatesize(String sizeid) {
		return sizemapper.validatesize(sizeid);
	}

}
