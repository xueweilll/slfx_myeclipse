package com.benqzl.service.dispatch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import java.util.UUID;

/*import org.activiti.engine.FormService;*/
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
/*import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;*/
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.dispatch.ReceiptDispatchMapper;
import com.benqzl.dao.dispatch.ReceiptMapper;
import com.benqzl.dao.dispatch.SelfDispatchMapper;
import com.benqzl.dao.system.DepartmentMapper;
import com.benqzl.dao.system.StationMapper;
import com.benqzl.pojo.dispatch.Receipt;
import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.pojo.system.Department;
import com.benqzl.pojo.system.Station;

@Service("dispatchExecuteService")
public class DispatchExecuteServiceImpl implements DispatchExecuteService {
	@Autowired
	private ReceiptMapper receiptMapper;
	
	@Autowired
	private SelfDispatchMapper dispatchMapper;
	@Autowired
	private RepositoryService repositoryService;
	/*@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private FormService formService;*/
	@Autowired
	private HistoryService historyService;
	@Override
	public List<Receipt> findByPageByReceipt(Map<String, Object> map)
			throws Exception {
		return receiptMapper.findByPage(map);
	}

	@Override
	public int pageCountByReceipt(Map<String, Object> map) throws Exception {
		return receiptMapper.pageCount(map);
	}

	@Override
	public List<SelfDispatch> findByPageBySelfReceipt(Map<String, Object> map)
			throws Exception {
		return dispatchMapper.findByPage(map);
	}

	@Override
	public int pageCountBySelfReceipt(Map<String, Object> map) throws Exception {
		return dispatchMapper.pageCount(map);
	}
	
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private StationMapper stationMapper;
	@Autowired
	private ReceiptDispatchMapper dispatchMappers;
	@Override
	public List<Map<String, Object>> findHisTask(String id) {
		List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().variableValueEquals(id).orderByProcessInstanceStartTime().asc().list();
		List<String> strings = dispatchMappers.findReceiptIds(id);
		List<Department> departments = new ArrayList<>();
		List<Station> stations= new ArrayList<>();
		departments = departmentMapper.selectByArea();
		stations = stationMapper.stationByFk();
		List<Map<String, Object>> maps = new ArrayList<>();
		List<Map<String, Object>> maps1 = new ArrayList<>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(historicProcessInstances.size()>1){
			Integer count = 1;
			for (HistoricProcessInstance p : historicProcessInstances) {
				String processDefinitionId = p.getProcessDefinitionId();
				ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
				ProcessDefinitionEntity entity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinition.getId());
				List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery().processInstanceId(p.getId()).orderByTaskDueDate().desc().list();
				Integer count1=1;
				for (HistoricTaskInstance historicTaskInstance : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					if(!p.getProcessDefinitionId().contains("districtDispath")){
						map.put("name", historicTaskInstance.getName());
						if(historicTaskInstance.getEndTime()==null){
							map.put("state", 0);
						}else{
							map.put("state", 1);
							map.put("endtime", sf.format(historicTaskInstance.getEndTime()));
						}
						map.put("starttime", sf.format(historicTaskInstance.getStartTime()));
						ActivityImpl activityImpl = entity.findActivity(historicTaskInstance.getTaskDefinitionKey());
						map.put("hi", activityImpl.getHeight());
						map.put("wi", activityImpl.getWidth());
						map.put("x", activityImpl.getX());
						map.put("y", activityImpl.getY());
						maps.add(map);
					}else{
						if(historicTaskInstance.getEndTime()==null){
							map.put("states", "未完成");
						}else{
							map.put("states", "已完成");
							map.put("endtime", sf.format(historicTaskInstance.getEndTime()));
						}
						map.put("starttime", sf.format(historicTaskInstance.getStartTime()));
						if(historicTaskInstance.getTaskDefinitionKey().equals("station")){
							HistoricVariableInstance stationId=historyService.createHistoricVariableInstanceQuery().processInstanceId(p.getId()).taskId(historicTaskInstance.getId()).variableName("stationId").singleResult();
//							HistoricVariableInstance departmentId=historyService.createHistoricVariableInstanceQuery().processInstanceId(p.getId()).variableName("departmentId").singleResult();
							map.put("id", count*10+count1);
							count1++;
							map.put("_parentId", count);
							for (Station station : stations) {
								if(stationId.getValue().equals(station.getId())){
									map.put("name", station.getName()+"--实施");
								}
							}
						}else{
							HistoricVariableInstance departmentId=historyService.createHistoricVariableInstanceQuery().processInstanceId(p.getId()).variableName("departmentId").singleResult();
							map.put("id", count);
							for (Department department : departments) {
								if(department.getId().equals(departmentId.getValue())){
									map.put("name", department.getName()+"--执行");
								}
							}
						}
						maps1.add(map);
					}
				}
				if(historicProcessInstances.size()>1){
					if(!p.getProcessDefinitionId().contains("districtDispath")){
						ActivityImpl activityImpl1 = entity.findActivity("callactivity");
						Map<String, Object> call = new HashMap<String, Object>();
						call.put("hi", activityImpl1.getHeight());
						call.put("wi", activityImpl1.getWidth());
						call.put("x", activityImpl1.getX());
						call.put("y", activityImpl1.getY());
						call.put("name", "callactivity");
						maps.add(call);
					}
				}
				count++;
			}
		}else{
			HistoricProcessInstance ps=historicProcessInstances.get(0);
			if(ps!=null){
				String processDefinitionId = ps.getProcessDefinitionId();
				ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
				ProcessDefinitionEntity entity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinition.getId());
				List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery().processInstanceId(ps.getId()).orderByTaskDueDate().desc().list();
				for (HistoricTaskInstance historicTaskInstance : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", historicTaskInstance.getName());
					if(historicTaskInstance.getEndTime()==null){
						map.put("state", 0);
					}else{
						map.put("state", 1);
						map.put("endtime", sf.format(historicTaskInstance.getEndTime()));
					}
					map.put("starttime", sf.format(historicTaskInstance.getStartTime()));
					ActivityImpl activityImpl = entity.findActivity(historicTaskInstance.getTaskDefinitionKey());
					map.put("hi", activityImpl.getHeight());
					map.put("wi", activityImpl.getWidth());
					map.put("x", activityImpl.getX());
					map.put("y", activityImpl.getY());
					maps.add(map);
				}
			}
			Integer count = 1;
			if(strings.size()!=0){
				for (String string : strings) {
					Map<String, Object> countMap = new HashMap<>();
					String countId = UUID.randomUUID().toString();
					countMap.put("name", "第"+count+"次分解");
					countMap.put("id", countId);
					maps1.add(countMap);
					List<HistoricProcessInstance> eps=null;
					HistoricProcessInstance cps=null;
					if(ps.getProcessDefinitionId().contains("selfDispatch")){
						eps = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("districtDispath").variableValueEquals(id).list();
					}else{
						eps = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("districtDispath").variableValueEquals(string).list();
						cps = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("callBack").variableValueEquals(string).singleResult();
					}
					if(eps.size()!=0){
						for (HistoricProcessInstance historicProcessInstance : eps) {
							String countsId = UUID.randomUUID().toString();
							List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery().processInstanceId(historicProcessInstance.getId()).orderByTaskDueDate().desc().list();
							for (HistoricTaskInstance historicTaskInstance : list) {
								Integer count1=1;
								Map<String, Object> map = new HashMap<String, Object>();
								if(historicTaskInstance.getEndTime()==null){
									map.put("states", "未完成");
								}else{
									map.put("states", "已完成");
									map.put("endtime", sf.format(historicTaskInstance.getEndTime()));
								}
								map.put("starttime", sf.format(historicTaskInstance.getStartTime()));
								if(historicTaskInstance.getTaskDefinitionKey().equals("station")){
									HistoricVariableInstance stationId=historyService.createHistoricVariableInstanceQuery().processInstanceId(historicProcessInstance.getId()).taskId(historicTaskInstance.getId()).variableName("stationId").singleResult();
									map.put("id", countsId+count1);
									count1++;
									map.put("_parentId", countsId);
									for (Station station : stations) {
										if(stationId.getValue().equals(station.getId())){
											map.put("name", station.getName()+"--实施");
										}
									}
								}else{
									HistoricVariableInstance departmentId=historyService.createHistoricVariableInstanceQuery().processInstanceId(historicProcessInstance.getId()).variableName("departmentId").singleResult();
									map.put("id", countsId);
									map.put("_parentId", countId);
									for (Department department : departments) {
										if(department.getId().equals(departmentId.getValue())){
											map.put("name", department.getName()+"--执行");
										}
									}
								}
								maps1.add(map);
							}
						}
						
					}
					if(cps!=null){
						List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery().processInstanceId(cps.getId()).orderByTaskDueDate().desc().list();
						for (HistoricTaskInstance historicTaskInstance : list) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("name", historicTaskInstance.getName());
							map.put("id", UUID.randomUUID().toString());
							map.put("_parentId", countId);
							if(historicTaskInstance.getEndTime()==null){
								map.put("states", "未完成");
							}else{
								map.put("states", "已完成");
								map.put("endtime", sf.format(historicTaskInstance.getEndTime()));
							}
							map.put("starttime", sf.format(historicTaskInstance.getStartTime()));
							maps1.add(map);
						}
					}
					count++;
				}
			}
		}
		//HistoricProcessInstance ps = historyService.createHistoricProcessInstanceQuery().variableValueEquals(id).singleResult();
		
		
		if(maps1.size()>0){
			Map<String, Object> map = new HashMap<>();
			Map<String, Object> map1 = new HashMap<>();
			map1.put("total", maps1.size());
			map1.put("rows", maps1);
			map.put("name", "count");
			map.put("coutent", map1);
			maps.add(map);
		}
		return maps;
	}

	@Override
	public List<String> selectPDById(String id) throws Exception {
		List<HistoricProcessInstance> ps=historyService.createHistoricProcessInstanceQuery().variableValueEquals(id).list();
		
		List<String>  strings= new ArrayList<>();
		for (HistoricProcessInstance historicProcessInstance : ps) {
			strings.add(historicProcessInstance.getProcessDefinitionId());
		}
		return strings;
	}

	@Override
	public String selectPDByIdByD(String id, String type) throws Exception {
		List<HistoricProcessInstance> ps=historyService.createHistoricProcessInstanceQuery().variableValueEquals(id).list();
		HistoricProcessInstance p=null;
		for (HistoricProcessInstance historicProcessInstance : ps) {
			if(historicProcessInstance.getProcessDefinitionId().contains("districtDispath")){
				p=historicProcessInstance;
				List<HistoricVariableInstance> map=historyService.createHistoricVariableInstanceQuery().processInstanceId(historicProcessInstance.getId()).list();
				for (HistoricVariableInstance historicVariableInstance : map) {
					if(historicVariableInstance.getVariableName().equals("departmentId")){
						if(historicVariableInstance.getValue().equals(type)){
							p=historicProcessInstance;
						}
					}
				}
			}
		}
		return p.getProcessDefinitionId();
	}

}
