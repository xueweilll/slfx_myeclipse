package com.benqzl.service.system;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.benqzl.pojo.system.ActivitiDeployment;
import com.benqzl.pojo.system.ActivitiTask;

public interface WorkFlowService {
	int insertDeployment(MultipartFile zipfile, String name,String type,String userid) throws Exception;
	List<ActivitiDeployment>  findByPageDeployment(Map<String, Object> map)throws Exception;
	int pageCount(Map<String, Object> map) throws Exception;
	int delete(String id)throws Exception;
	List<Map<String, Object>> findTaskAll(String id) throws Exception;
	//Boolean saveStartProcess() throws Exception;
	int saveTaskUser(String jsonStr,String pid,String userid);
	InputStream findViewById(String id) throws Exception;
	List<String> findUserids(Map<String, Object> map) throws Exception;
	List<ActivitiTask> findActivitiTasks(String id) throws Exception;
	int deleteDeployment(String id) throws Exception; 
	
}
