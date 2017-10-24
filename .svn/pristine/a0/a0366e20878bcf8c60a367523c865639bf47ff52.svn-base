package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Camera;
import com.benqzl.pojo.system.Station;

public interface CameraService {
	List<Camera> findByPage(Map<String, String> map);
	List<Station> selectStation();
	int insert(Camera camera);
	int updateByPrimaryKeySelective(Camera camera);
	Camera selectByPrimaryKey(String id);
	int deleteByPrimaryKey(String id);
	int pageCount(Map<String,String> map);
	String selectCode(Map<String,String> map);
	String cameraOnlyOne(Map<String, String> map);
	List<Camera> selectByFk(String sid);
	List<Camera> selectByFkCode(String code);
	int selectcount();
	void insertSelective(Camera camera);
}
