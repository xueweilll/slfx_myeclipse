package com.benqzl.service.activiti;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.dispatch.ReceiptDispatchMapper;
import com.benqzl.dao.dispatch.ReceiptMapper;
import com.benqzl.dao.dispatch.SelfDispatchMapper;
import com.benqzl.dao.system.ActivitiTaskMapper;
import com.benqzl.dao.system.MessageCenterMapper;
import com.benqzl.unit.SendMessageAutoUtil;

@Service()
public class ActivitiUnitServiceImpl implements ActivitiUnitService {
	/*
	 * @Autowired private RepositoryService repositoryService;
	 */
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	/*
	 * @Autowired private FormService formService;
	 */
	@Autowired
	private HistoryService historyService;
	@Autowired
	private MessageCenterMapper mapper;
	@Autowired
	private ActivitiTaskMapper amapper;
	@Autowired
	private ReceiptMapper rmapper;
	@Autowired
	private ReceiptDispatchMapper rdmapper;
	@Autowired
	private SelfDispatchMapper smapper;

	@Override
	public int statrtByKey(String key, Map<String, Object> map)
			throws Exception {
		if (map != null) {
			runtimeService.startProcessInstanceByKey(key, map);
		} else {
			runtimeService.startProcessInstanceByKey(key);
		}
		return 0;
	}

	@Override
	public int completeTask(String taskName, String userid, String id,
			Map<String, Object> map) throws Exception {
		Task task = taskService.createTaskQuery().taskCandidateUser(userid)
				.processVariableValueEquals(id).taskDefinitionKey(taskName)
				.singleResult();
		if (task != null) {
			List<IdentityLink> strings = taskService
					.getIdentityLinksForTask(task.getId());
			for (IdentityLink identityLink : strings) {
				SendMessageAutoUtil.sendMessageAuto(identityLink.getUserId());
			}
			String messageId = (String) taskService.getVariableLocal(
					task.getId(), "messageId");
			mapper.deleteByTip(messageId);
			if (map != null) {
				taskService.complete(task.getId(), map);
			} else {
				taskService.complete(task.getId());
			}
		}
		return 0;
	}

	@Override
	public int completeTaskByStation(String userid, String id, String sid,
			Map<String, Object> map) throws Exception {
		List<Task> tasks = taskService.createTaskQuery()
				.processDefinitionKey("districtDispath")
				.taskDefinitionKey("station").taskCandidateUser(userid)
				.processVariableValueEquals(id).list();
		Task task = null;
		for (Task task2 : tasks) {
			String stationid = (String) taskService.getVariable(task2.getId(),
					"stationId");
			if (sid.equals(stationid)) {
				task = task2;
			}
		}
		if (task != null) {
			List<IdentityLink> strings = taskService
					.getIdentityLinksForTask(task.getId());
			for (IdentityLink identityLink : strings) {
				SendMessageAutoUtil.sendMessageAuto(identityLink.getUserId());
			}
			String messageId = (String) taskService.getVariableLocal(
					task.getId(), "messageId");
			mapper.deleteByTip(messageId);
			if (map != null) {
				taskService.complete(task.getId(), map);
			} else {
				taskService.complete(task.getId());
			}
		}
		return 0;
	}

	@Override
	public int completeTaskByDepartment(String userid, String id, String did,
			Map<String, Object> map) throws Exception {
		Task task = taskService.createTaskQuery()
				.processDefinitionKey("districtDispath")
				.taskDefinitionKey("district").taskCandidateUser(userid)
				.processVariableValueEquals(id).processVariableValueEquals(did)
				.singleResult();
		if (task != null) {
			List<IdentityLink> strings = taskService
					.getIdentityLinksForTask(task.getId());
			for (IdentityLink identityLink : strings) {
				SendMessageAutoUtil.sendMessageAuto(identityLink.getUserId());
			}
			String messageId = (String) taskService.getVariableLocal(
					task.getId(), "messageId");
			mapper.deleteByTip(messageId);
			if (map != null) {
				taskService.complete(task.getId(), map);
			} else {
				taskService.complete(task.getId());
			}
		}
		return 0;
	}

	@Override
	public List<String> findObjIds(String taskName, String userid, String type)
			throws Exception {
		List<Task> tasks = taskService.createTaskQuery()
				.taskCandidateUser(userid).taskDefinitionKey(taskName)
				.processVariableValueEquals(type).list();
		List<String> list = new ArrayList<>();
		for (Task task : tasks) {
			String objId = (String) taskService.getVariable(task.getId(),
					"objId");
			list.add(objId);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findByStation(String userid, String type)
			throws Exception {
		List<Task> tasks = null;
		if (type.equals("A")) {
			tasks = taskService.createTaskQuery().taskCandidateUser(userid)
					.processVariableValueEquals(type)
					.processDefinitionKey("districtDispath")
					.taskDefinitionKey("station").list();
		} else {
			tasks = taskService.createTaskQuery().taskCandidateUser(userid)
					.processVariableValueNotEquals("type", "A")
					.processDefinitionKey("districtDispath")
					.taskDefinitionKey("station").list();
		}
		List<Map<String, Object>> maps = new ArrayList<>();
		if (tasks.size() != 0 && tasks != null) {
			for (Task task : tasks) {
				Map<String, Object> map = new HashMap<>();
				Map<String, Object> variables = taskService.getVariables(task
						.getId());
				map.put("objId", variables.get("objId"));
				map.put("stationId", variables.get("stationId"));
				maps.add(map);
			}
		}
		return maps;
	}

	@Override
	public List<Map<String, Object>> findByDepartment(String userid, String type)
			throws Exception {
		List<Task> tasks = new ArrayList<>();
		if (type.equals("A")) {
			tasks = taskService.createTaskQuery().taskCandidateUser(userid)
					.processVariableValueEquals(type)
					.processDefinitionKey("districtDispath")
					.taskDefinitionKey("district").list();
		} else {
			tasks = taskService.createTaskQuery().taskCandidateUser(userid)
					.processVariableValueNotEquals("type", "A")
					.processDefinitionKey("districtDispath")
					.taskDefinitionKey("district").list();
		}
		List<Map<String, Object>> maps = new ArrayList<>();
		if (tasks.size() != 0 && tasks != null) {
			for (Task task : tasks) {
				Map<String, Object> map = new HashMap<>();
				Map<String, Object> variables = taskService.getVariables(task
						.getId());
				map.put("objId", variables.get("objId"));
				map.put("departmentId", variables.get("departmentId"));
				maps.add(map);
			}
		}
		return maps;
	}

	@Override
	public String selectMaxCode(String prefix) {
		String suffix = amapper.selectMaxCode();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
		String date = bartDateFormat.format(new Date());
		return prefix + "-" + date + "-" + suffix;
	}

	@Override
	public String selectCode(String prefix) {
		String code = "";
		SimpleDateFormat yearDateFormat = new SimpleDateFormat("yyyy");
		String years = yearDateFormat.format(new Date());
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
		String date = bartDateFormat.format(new Date());
		HashMap<String, Object> map = new HashMap<>();
		map.put("code", prefix + "-" + years);
		if (prefix.equals("SD")) {
			code = smapper.selectCode(map);
		} else if(prefix.equals("CUFC-DO")){
			code = rmapper.selectCode(map);
		} else if(prefix.equals("CUFC-FJ")){
			code = rdmapper.selectCode(map);
		}
		String suffix = "";
		if (code == null) {
			suffix = String.format("%04d", 1);
		} else {
			// 反转截取字符
			// String reversecode = new
			// StringBuilder(code).reverse().toString().substring(9, 13);
			// String year = new
			// StringBuilder(reversecode).reverse().toString();// 反转字符串

			// if (year.equals(years)) {
			int a = Integer.parseInt(code.substring(code.length() - 4));
			suffix = String.format("%04d", a + 1);
			// } else {
			// suffix = String.format("%04d", 1);
			// }
		}

		return prefix + "-" + date + "-" + suffix;
	}

	@Autowired
	private ReceiptDispatchMapper dispatchMappers;

	@Override
	public boolean subflowIsOver(String id) throws Exception {
		List<String> strings = dispatchMappers.findReceiptIds(id);
		if (strings.size() != 0) {
			for (String string : strings) {
				List<HistoricProcessInstance> historicProcessInstances = historyService
						.createHistoricProcessInstanceQuery()
						.variableValueEquals(string)
						.orderByProcessInstanceStartTime().asc().list();
				if (historicProcessInstances.size() == 0) {
					return false;
				} else {
					for (HistoricProcessInstance historicProcessInstance : historicProcessInstances) {
						if (historicProcessInstance.getEndTime() == null) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean subflowIsFirst(String id) throws Exception {
		List<HistoricProcessInstance> instances = historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey("callBack").variableValueEquals(id)
				.list();
		if (instances.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
