package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.AssetsMapper;
import com.benqzl.pojo.system.Assets;
@Service("assetsService")
public class AssetsServiceImpl implements AssetsService {
	 @Autowired
	private AssetsMapper assetsMapper;
	@Override
	public int pageCount(Map<String, Object> map) {
		return assetsMapper.pageCount(map);
	}

	@Override
	public List<Assets> findByPage(Map<String, Object> map) {
		return assetsMapper.findByPage(map);
	}

	@Override
	public Assets selectByPrimaryKey(String id) {
		return assetsMapper.selectByPrimaryKey(id);
	}

	@Override
	public void insert(Assets assets) {
		assetsMapper.insert(assets);
		
	}

	@Override
	public void updateByPrimaryKey(Assets assets) {
	    assetsMapper.updateByPrimaryKeySelective(assets);
		
	}

	@Override
	public void deleteByPrimaryKey(String id) {
		assetsMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public Assets selectDownByPrimaryKey(String id) {
		return assetsMapper.selectDownByPrimaryKey(id);
	}

	@Override
	public Assets selectQrByPrimaryKey(String id) {
		return assetsMapper.selectQrByPrimaryKey(id);
	}

}
