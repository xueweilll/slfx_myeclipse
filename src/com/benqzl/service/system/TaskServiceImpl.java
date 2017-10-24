package com.benqzl.service.system;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private org.activiti.engine.TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private HistoryService historyService;
	@Override
	public List<Task> findByPageTask(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		List<Task> list = taskService.createTaskQuery().taskCandidateUser(map.get("userid").toString())
				.taskCreatedAfter((Date)map.get("starttime")).taskCreatedBefore((Date)map.get("endtime"))
				.listPage((int)map.get("p1"),(int)map.get("p2"));
		List<Task> list2 = taskService.createTaskQuery().taskAssignee(map.get("userid").toString())
				.taskCreatedAfter((Date)map.get("starttime")).taskCreatedBefore((Date)map.get("endtime"))
				.listPage((int)map.get("p1"),(int)map.get("p2"));
		list.addAll(list2);
		return list;
	}

	@Override
	public int pageCount(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		long count=taskService.createTaskQuery().taskCandidateUser(map.get("userid").toString())
				.taskCreatedAfter((Date)map.get("starttime")).taskCreatedBefore((Date)map.get("endtime"))
				.count();
		long count1=taskService.createTaskQuery().taskAssignee(map.get("userid").toString())
				.taskCreatedAfter((Date)map.get("starttime")).taskCreatedBefore((Date)map.get("endtime"))
				.count();
		
		return (int) count+(int)count1;
	}

	@Override
	public void startP(String key) {
		// TODO Auto-generated method stub
		runtimeService.startProcessInstanceByKey(key);
	}

	@Override
	public List<HistoricTaskInstance> findByPageTaskHI(Map<String, Object> map)
			throws Exception {
		List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().taskAssignee(map.get("userid").toString())
				.taskCreatedAfter((Date)map.get("starttime")).taskCreatedBefore((Date)map.get("endtime"))
				.listPage((int)map.get("p1"),(int)map.get("p2"));
		return list;
	}

	@Override
	public int pageCountHI(Map<String, Object> map) throws Exception {
		long count=historyService.createHistoricTaskInstanceQuery().taskAssignee(map.get("userid").toString())
				.taskCreatedAfter((Date)map.get("starttime")).taskCreatedBefore((Date)map.get("endtime"))
				.count();
		return (int)count;
	}

	@Override
	public void claim(String id, String userid) throws Exception {
		taskService.claim(id, userid);
	}

	@Override
	public InputStream findViewById(String id) {
		Task  task = taskService.createTaskQuery().taskId(id).singleResult();
		ProcessDefinition definition=repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
		return repositoryService.getResourceAsStream(definition.getDeploymentId(),definition.getDiagramResourceName());
	}
	
	@Override
	public Map<String, Object> findCoordingByTask(String taskId) {
		//存放坐标
		Map<String, Object> map = new HashMap<String,Object>();
		//使用任务ID，查询任务对象
		Task task = taskService.createTaskQuery()//
					.taskId(taskId)//使用任务ID查询
					.singleResult();
		//获取流程定义的ID
		String processDefinitionId = task.getProcessDefinitionId();
		//获取流程定义的实体对象（对应.bpmn文件中的数据）
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinitionId);
		//流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		//使用流程实例ID，查询正在执行的执行对象表，获取当前活动对应的流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//创建流程实例查询
					.processInstanceId(processInstanceId)//使用流程实例ID查询
					.singleResult();
		//获取当前活动的ID
		String activityId = pi.getActivityId();
		//获取当前活动对象
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);//活动ID
		//获取坐标
		map.put("x", activityImpl.getX());
		map.put("y", activityImpl.getY());
		map.put("width", activityImpl.getWidth());
		map.put("height", activityImpl.getHeight());
		return map;
	}

}
