package com.benqzl.unit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;*/
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.ActivitiTaskMapper;
import com.benqzl.dao.system.MessageCenterMapper;

/** 
* @ClassName: ActivitiUnitServiceImpl 
* @Description: TODO(流程操作) 
* @author 冯庆国
* @date 2016年1月7日 下午3:46:09 
*  
*/
@Service("activitiUnitService")
public class ActivitiUnitServiceImpl implements ActivitiUnitService{
/*	@Autowired
	private RepositoryService repositoryService;*/
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
/*	@Autowired
	private FormService formService;
	@Autowired
	private HistoryService historyService;*/
	@Autowired
	private MessageCenterMapper mapper;
	@Autowired 
	private ActivitiTaskMapper amapper;
	@Override
	public int statrtByKey(String key,String name,String id) {
		Map<String, Object> map= new HashMap<>();
		map.put(name, id);
		runtimeService.startProcessInstanceByKey(key, map);
		return 0;
	}
	
	@Override
	public int statrtByKey(String key, Map<String, Object> map)
			throws Exception {
		runtimeService.startProcessInstanceByKey(key, map);
		return 0;
	}
	/**
	 * 完成任务
	 * taskName:任务节点名称
	 * userid:用户Id
	 */
	@Override
	public int completeTask(String taskName, String userid,String id,String name,Object object) throws Exception {
		Task task = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).taskDefinitionKey(taskName).singleResult();
		Map<String, Object> map = new HashMap<>();
		map.put(name, object);
		taskService.claim(task.getId(), userid);
		taskService.complete(task.getId(),map);
		return 0;
	}
	@Override
	public int completeTaskByDorS(String taskName,String userid,String id,String dorsId,String name,Object object) throws Exception {
		Task task = null;
		if(taskName.equals("station")){
			List<Task> tasks =  taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).taskDefinitionKey(taskName).processDefinitionKey("districtDispath").list();
			for (Task task2 : tasks) {
				String sid=(String) taskService.getVariable(task2.getId(), "stationId");
				if(sid.equals(dorsId)){
					task=task2;
				}
			}
		}else{
			task = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).processVariableValueEquals(dorsId).taskDefinitionKey(taskName).processDefinitionKey("districtDispath").singleResult();
		}
		if(name!=null&&name!=""){
			Map<String, Object> map = new HashMap<>();
			map.put(name, object);
			taskService.claim(task.getId(), userid);
			taskService.complete(task.getId(),map);
		}else{
			taskService.claim(task.getId(), userid);
			taskService.complete(task.getId());
		}
		return 0;
	}
	@Override
	public int completeTask(String taskName, String userid,String id) throws Exception {
		Task task = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).taskDefinitionKey(taskName).singleResult();
		taskService.claim(task.getId(), userid);
		taskService.complete(task.getId());
		return 0;
	}
	@Override
	public List<String> findeReceiptIds(String userid,String type,String typeDate) throws Exception {
		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userid).list();
		List<String> list = new ArrayList<>();
		if(tasks.size()!=0&&tasks!=null){
			for (Task task : tasks) {
				if(!task.getProcessDefinitionId().contains("autonomous")){
					if(task.getTaskDefinitionKey().equals(typeDate)){
						Map<String, Object> varuables = taskService.getVariables(task.getId());
						if(type !=null){
							if(varuables.get("type").equals(type)){
								list.add(varuables.get("objId").toString());
							}
						}else{
							list.add(varuables.get("objId").toString());
						}
					}
				}
			}
		}
		return list;
	}
	@Override
	public List<String> findeSelfDispatchIds(String userid,String typeDate) throws Exception {
		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userid).list();
		List<String> list = new ArrayList<>();
		if(tasks.size()!=0&&tasks!=null){
			for (Task task : tasks) {
				if(task.getProcessDefinitionId().contains("autonomous")){
					if(task.getTaskDefinitionKey().equals(typeDate)){
						Map<String, Object> varuables = taskService.getVariables(task.getId());
						list.add(varuables.get("objId").toString());
					}
				}
			}
		}
		return list;
	}
	
	
	@Override
	public void messagePushByDorSid(String taskName,String userid,String id,String dorsId) throws Exception {
		Task task = null;
		if(taskName.equals("station")){
			List<Task> tasks =  taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).taskDefinitionKey(taskName).processDefinitionKey("districtDispath").list();
			for (Task task2 : tasks) {
				String sid=(String) taskService.getVariable(task2.getId(), "stationId");
				if(sid.equals(dorsId)){
					task=task2;
				}
			}
		}else{
			task = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).processVariableValueEquals(dorsId).taskDefinitionKey(taskName).processDefinitionKey("districtDispath").singleResult();
		}
		List<IdentityLink> strings=taskService.getIdentityLinksForTask(task.getId());
		String messageId=(String) taskService.getVariableLocal(task.getId(), "messageId");
		//String messageId=(String) task.getProcessVariables().get("messageId");
		for (IdentityLink identityLink : strings) {
			SendMessageAutoUtil.sendMessageAuto(identityLink.getUserId());
		}
		mapper.deleteByTip(messageId);
	}
	
	@Override
	public void messagePush(String taskName,String userid,String id) throws Exception {
		Task task = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).taskDefinitionKey(taskName).singleResult();
		List<IdentityLink> strings=taskService.getIdentityLinksForTask(task.getId());
		String messageId=(String) taskService.getVariableLocal(task.getId(), "messageId");
		//String messageId=(String) task.getProcessVariables().get("messageId");
		for (IdentityLink identityLink : strings) {
			SendMessageAutoUtil.sendMessageAuto(identityLink.getUserId());
		}
		mapper.deleteByTip(messageId);
	}
	@Override
	public List<String> findTaskStationsByPatrolplan(
			String userid, String typeDate) throws Exception {
		List<Task> list = taskService.createTaskQuery()
				.taskCandidateUser(userid).list();
		List<String> maps = new ArrayList<>();
		if (list.size() != 0 && list != null) {
			for (Task task : list) {
				if (task.getProcessDefinitionId().contains("patrolplan")) {
					if (task.getTaskDefinitionKey().equals(typeDate)) {
						Map<String, Object> varuables = taskService.getVariables(task.getId());
						String id=(String) varuables.get("patrolplanId");
						maps.add(id);
					}
				}
			}
		}
		return maps;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> userids(String taskName, String userid, String id) throws Exception {
		Task task = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).taskDefinitionKey(taskName).singleResult();
		return (List<String>) taskService.getVariables(task.getId()).get(taskName);
	}
	@Override
	public String selectMaxCode(String prefix) {
		String suffix= amapper.selectMaxCode();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd"); 
		String date = bartDateFormat.format(new Date());
		return prefix+"-"+date+"-"+suffix;
	}
	@Override
	public List<String> findMaintainId(String userid, String typeDate)
			throws Exception {
		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userid).processDefinitionKey("maintain").list();
		List<String> list = new ArrayList<>();
		if(tasks.size()!=0&&tasks!=null){
			for (Task task : tasks) {
				if(task.getTaskDefinitionKey().equals(typeDate)){
					Map<String, Object> varuables = taskService.getVariables(task.getId());
					list.add(varuables.get("maintainId").toString());
				}
			}
		}
		return list;
	}
	@Override
	public int completeTaskByPerson(String taskName, String userid, String id)
			throws Exception {
		Task task  = taskService.createTaskQuery().taskAssignee(userid).processVariableValueEquals(id).taskDefinitionKey(taskName).singleResult();
		taskService.complete(task.getId());
		return 0;
	}
	@Override
	public List<Map<String, Object>> findStations(String userid,String type)
			throws Exception {
		List<Task> tasks = new ArrayList<>();
		if(type.equals("A")){
			tasks = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(type).processDefinitionKey("districtDispath").taskDefinitionKey("station").list();
		}else{
			tasks = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueNotEquals("type", "A").processDefinitionKey("districtDispath").taskDefinitionKey("station").list();
		}
		//List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(type).processDefinitionKey("districtDispath").taskDefinitionKey("station").list();
		List<Map<String, Object>> maps = new ArrayList<>();
		if(tasks.size()!=0&&tasks!=null){
			for (Task task : tasks) {
				Map<String, Object> map = new HashMap<>();
				Map<String, Object> variables=taskService.getVariables(task.getId());
				map.put("objId", variables.get("objId"));
				map.put("stationId", variables.get("stationId"));
				maps.add(map);
			}
		}
		return maps;
	}
	@Override
	public List<Map<String, Object>> findDistricts(String userid,String type)
			throws Exception {
		List<Task> tasks = new ArrayList<>();
		if(type.equals("A")){
			tasks = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(type).processDefinitionKey("districtDispath").taskDefinitionKey("district").list();
		}else{
			tasks = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueNotEquals("type", "A").processDefinitionKey("districtDispath").taskDefinitionKey("district").list();
		}
		List<Map<String, Object>> maps = new ArrayList<>();
		if(tasks.size()!=0&&tasks!=null){
			for (Task task : tasks) {
				Map<String, Object> map = new HashMap<>();
				Map<String, Object> variables=taskService.getVariables(task.getId());
				map.put("objId", variables.get("objId"));
				map.put("departmentId", variables.get("departmentId"));
				maps.add(map);
			}
		}
		return maps;
	}

	@Override
	public List<String> findObjIds(String userid, String taskName, String type)
			throws Exception {
		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userid).taskDefinitionKey(taskName).processVariableValueEquals(type).list();
		List<String> list = new ArrayList<>();
		for (Task task : tasks) {
			String objId = (String) taskService.getVariable(task.getId(), "objId");
			list.add(objId);
		}
		return list;
	}

	@Override
	public int completeTasks(String taskName, String userid, String id,
			Map<String, Object> map) throws Exception {
		Task task = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).taskDefinitionKey(taskName).singleResult();
		taskService.claim(task.getId(), userid);
		taskService.complete(task.getId(),map);
		return 0;
	}

	@Override
	public List<Map<String, Object>> findDistrictsByPartrol(String userid,
			String taskName) throws Exception {
		List<Task> tasks = new ArrayList<>();
		tasks = taskService.createTaskQuery().taskCandidateUser(userid).processDefinitionKey("partolProcess").taskDefinitionKey(taskName).list();
		List<Map<String, Object>> maps = new ArrayList<>();
		if(tasks.size()!=0&&tasks!=null){
			for (Task task : tasks) {
				Map<String, Object> map = new HashMap<>();
				Map<String, Object> variables=taskService.getVariables(task.getId());
				map.put("objId", variables.get("objId"));
				map.put("departmentId", variables.get("departmentId"));
				maps.add(map);
			}
		}
		return maps;
	}

	@Override
	public List<Map<String, Object>> findStationsByPartrol(String userid)
			throws Exception {
		List<Task> tasks = new ArrayList<>();
		tasks = taskService.createTaskQuery().taskCandidateUser(userid).processDefinitionKey("partolProcess").taskDefinitionKey("station").list();
		List<Map<String, Object>> maps = new ArrayList<>();
		if(tasks.size()!=0&&tasks!=null){
			for (Task task : tasks) {
				Map<String, Object> map = new HashMap<>();
				Map<String, Object> variables=taskService.getVariables(task.getId());
				map.put("objId", variables.get("objId"));
				map.put("stationId", variables.get("stationId"));
				maps.add(map);
			}
		}
		return maps;
	}

	@Override
	public List<String> findObjIdsByPartrol(String userid, String taskName)
			throws Exception {
		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(userid).taskDefinitionKey(taskName).processDefinitionKey("partolProcess").list();
		List<String> list = new ArrayList<>();
		for (Task task : tasks) {
			String objId = (String) taskService.getVariable(task.getId(), "objId");
			list.add(objId);
		}
		return list;
	}

	@Override
	public int completeTaskByDorSByPartrol(String taskName, String userid,
			String id, String dorsId, String name, Object object)
			throws Exception {
		Task task = null;
		if(taskName.equals("station")){
			List<Task> tasks =  taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).taskDefinitionKey(taskName).processDefinitionKey("partolProcess").list();
			for (Task task2 : tasks) {
				String sid=(String) taskService.getVariable(task2.getId(), "stationId");
				if(sid.equals(dorsId)){
					task=task2;
				}
			}
		}else{
			task = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).processVariableValueEquals(dorsId).taskDefinitionKey(taskName).processDefinitionKey("partolProcess").singleResult();
		}
		if(name!=null&&name!=""){
			Map<String, Object> map = new HashMap<>();
			map.put(name, object);
			taskService.claim(task.getId(), userid);
			taskService.complete(task.getId(),map);
		}else{
			taskService.claim(task.getId(), userid);
			taskService.complete(task.getId());
		}
		return 0;
	}

	@Override
	public void messagePushByDorSidByPartrol(String taskName, String userid,
			String id, String dorsId) throws Exception {
		Task task = null;
		if(taskName.equals("station")){
			List<Task> tasks =  taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).taskDefinitionKey(taskName).processDefinitionKey("partolProcess").list();
			for (Task task2 : tasks) {
				String sid=(String) taskService.getVariable(task2.getId(), "stationId");
				if(sid.equals(dorsId)){
					task=task2;
				}
			}
		}else{
			task = taskService.createTaskQuery().taskCandidateUser(userid).processVariableValueEquals(id).processVariableValueEquals(dorsId).taskDefinitionKey(taskName).processDefinitionKey("partolProcess").singleResult();
		}
		List<IdentityLink> strings=taskService.getIdentityLinksForTask(task.getId());
		String messageId=(String) taskService.getVariableLocal(task.getId(), "messageId");
		for (IdentityLink identityLink : strings) {
			SendMessageAutoUtil.sendMessageAuto(identityLink.getUserId());
		}
		mapper.deleteByTip(messageId);
	}
}
