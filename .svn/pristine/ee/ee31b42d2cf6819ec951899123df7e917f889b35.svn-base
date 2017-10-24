package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.CameraMapper;
import com.benqzl.pojo.system.Camera;
import com.benqzl.pojo.system.Station;

@Service("CameraService")
public class CameraServiceImpl implements CameraService {
	@Autowired
	private CameraMapper cameramapper;
	@Override
	public List<Camera> findByPage(Map<String, String> map){
		return cameramapper.findByPage(map);
	}
	@Override
	public List<Station> selectStation(){
		return cameramapper.selectStation();
	}
	@Override
	public int insert(Camera camera){
		return cameramapper.insert(camera);
	}
	@Override
	public int updateByPrimaryKeySelective(Camera camera){
		return cameramapper.updateByPrimaryKeySelective(camera);
	}
	@Override
	public Camera selectByPrimaryKey(String id){
		return cameramapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(String id){
		return cameramapper.deleteByPrimaryKey(id);
	}
	@Override
	public int pageCount(Map<String,String> map){
		return cameramapper.pageCount(map);
	}
	@Override
	public String selectCode(Map<String,String> map){
		return cameramapper.selectCode(map);
	}
	@Override
	public String cameraOnlyOne(Map<String, String> map) {
		return cameramapper.cameraOnlyOne(map);
	}
	@Override
	public List<Camera> selectByFk(String sid) {
		return cameramapper.selectByFk(sid);
	}
	@Override
	public List<Camera> selectByFkCode(String code) {
		return cameramapper.selectByFkCode(code);
	}
	@Override
	public int selectcount() {
		// TODO Auto-generated method stub
		return cameramapper.selectcount();
	}
	@Override
	public void insertSelective(Camera camera) {
		cameramapper.insertSelective(camera);
	}
}
