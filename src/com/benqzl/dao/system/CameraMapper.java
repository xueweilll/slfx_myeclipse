package com.benqzl.dao.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.Camera;
import com.benqzl.pojo.system.Station;

public interface CameraMapper {
    int deleteByPrimaryKey(String id);

    int insert(Camera record);

    int insertSelective(Camera record);

    Camera selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Camera record);

    int updateByPrimaryKey(Camera record);
    
    List<Camera> findByPage(Map<String, String> map);
    
    List<Station> selectStation();
    int pageCount(Map<String,String> map);
    
    String selectCode(Map<String,String> map);

	String cameraOnlyOne(Map<String, String> map);
	
	List<Camera> selectByFk(String sid);
	
	List<Camera> selectByFkCode(String code);

	int selectcount();

	void deleteBySid(String id);
}