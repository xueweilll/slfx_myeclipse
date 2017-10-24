package com.benqzl.service.system;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

public interface TaskService {

	List<Task> findByPageTask(Map<String, Object> map) throws Exception;

	int pageCount(Map<String, Object> map) throws Exception;

	void startP(String key) throws Exception;

	List<HistoricTaskInstance> findByPageTaskHI(Map<String, Object> map) throws Exception;

	int pageCountHI(Map<String, Object> map) throws Exception;

	void claim(String id, String userid) throws Exception;

	InputStream findViewById(String id)  throws Exception;
	
	Map<String, Object> findCoordingByTask(String taskId)  throws Exception;
	
}
