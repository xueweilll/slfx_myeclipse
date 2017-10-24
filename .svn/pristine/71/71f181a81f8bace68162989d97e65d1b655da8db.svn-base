package com.benqzl.service.system;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipInputStream;

/*import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;*/
import org.activiti.engine.RepositoryService;
/*import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;*/
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.benqzl.dao.oa.MessageMapper;
import com.benqzl.dao.system.ActivitiDeploymentMapper;
import com.benqzl.dao.system.ActivitiTaskMapper;
import com.benqzl.dao.system.DepartmentMapper;
import com.benqzl.dao.system.StationMapper;
import com.benqzl.pojo.system.ActivitiDeployment;
import com.benqzl.pojo.system.ActivitiTask;
import com.benqzl.pojo.system.Department;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;
/** 
* @ClassName: WorkFlowServiceImpl 
* @Description: TODO(流程配置相关操作) 
* @author 冯庆国
* @date 2015年12月30日 下午2:31:38 
*  
*/
@Service("workFlowService")
public class WorkFlowServiceImpl implements WorkFlowService {
	@Autowired
	private RepositoryService repositoryService;
/*	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private FormService formService;
	@Autowired
	private HistoryService historyService;*/
	@Autowired
	private ActivitiDeploymentMapper deploymentMapper;
	@Autowired
	private ActivitiTaskMapper taskMapper;
	@Autowired
	private StationMapper stationMapper;
	@Override
	public int insertDeployment(MultipartFile file, String name,String type,String userid) throws Exception {
		//FileInputStream fi = new FileInputStream(file);
		ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
		Deployment deployment=repositoryService//与流程定义和部署对象相关的Service
				.createDeployment()//创建一个部署对象
				.name(name)//添加部署的名称
				.addZipInputStream(zipInputStream)//指定zip格式的文件完成部署
				.deploy();//完成部署
		ProcessDefinition processDefinition =repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		deploymentMapper.deleteByname(processDefinition.getKey());
		ActivitiDeployment record = new ActivitiDeployment();
		record.setId(UUID.randomUUID().toString());
		record.setDeploymentname(name);
		record.setProcessdefinitionid(processDefinition.getId());
		record.setCreatetime(new Date());
		record.setEdittime(new Date());
		record.setEdituserid(userid);
		record.setType(new Long(type));
		record.setIsDel(new Long(0));
		record.setDeploymentId(deployment.getId());
		record.setProcessdefinitionKey(processDefinition.getKey());
		record.setVersion(processDefinition.getVersion()+"");
		deploymentMapper.insertSelective(record);
		return 0;
	}

	@Override
	public List<ActivitiDeployment>  findByPageDeployment(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		List<ActivitiDeployment> list  =deploymentMapper.findByPage(map);
		return list;
	}
	@Override
	public int pageCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (int) deploymentMapper.pageCount(map);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		deploymentMapper.deleteByPrimaryKey(id);
		return 0;
	}
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Override
	public List<Map<String, Object>> findTaskAll(String id) {
		List<User> users = messageMapper.selectUsers();
		List<String> ids = new ArrayList<>();
		for (User user : users) {
			ids.add(user.getUserid());
		}
		List<ActivitiTask> tasks = taskMapper.selectUserAll(id);
		List<Station> stations = stationMapper.stationByFk();
		List<Department> departments = new ArrayList<>();
		if(id.contains("districtDispath")||id.contains("selfDispatch")){
			departments = departmentMapper.selectByArea();
		}
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
		ProcessDefinitionEntity entity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinition.getId());
		List<Map<String, Object>> taskDefinitions = new ArrayList<Map<String, Object>>();
		ActivityImpl firsttask = entity.getInitial();
		boolean result=true;
		List<PvmTransition> as=firsttask.getOutgoingTransitions();
		Map<String,Integer> orderby = new HashMap<>();
		while (result) {
			List<PvmTransition> ab=new ArrayList<>();
			for (PvmTransition pvmTransition : as) {
				PvmActivity pvmActivity=pvmTransition.getDestination();
				if(as.size()>1){
					ab.addAll(pvmTransition.getDestination().getOutgoingTransitions());
				}else{
					ab=pvmTransition.getDestination().getOutgoingTransitions();
				}
				if(orderby.get(pvmTransition.getSource().getId())==null){
					orderby.put(pvmActivity.getId(),1);
				}else{
					if(orderby.get(pvmActivity.getId())==null){
						orderby.put(pvmActivity.getId(), orderby.get(pvmTransition.getSource().getId())+1);
					}
				}
				if(ab.size()==0){
					result=false;
				}
			}
			as=ab;
		}
		
		for (ActivityImpl activityImpl : entity.getActivities()) {
			TaskDefinition definition= (TaskDefinition) activityImpl.getProperty("taskDefinition");
			if(definition!=null){
				if(definition.getKey().equals("districtAuto")||definition.getKey().equals("district")){
					for (Department department : departments) {
						List<String> activitiTasks = new ArrayList<String>();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", definition.getKey()+department.getId());
						map.put("name", department.getName());
						map.put("orderby", orderby.get(definition.getKey()));
						for (ActivitiTask activitiTask : tasks) {
							if(activitiTask.getTaskid().equals(definition.getKey()+department.getId())){
								if(ids.contains(activitiTask.getTaskuserid())){
									activitiTasks.add(activitiTask.getTaskuserid());
								}
							}
						}
						map.put("users", activitiTasks);
						taskDefinitions.add(map);
					}
				}else if(definition.getKey().equals("station")){
					for (Station station : stations) {
						List<String> activitiTasks = new ArrayList<String>();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", definition.getKey()+station.getId());
						map.put("name", station.getName());
						map.put("orderby", orderby.get(definition.getKey()));
						for (ActivitiTask activitiTask : tasks) {
							if(activitiTask.getTaskid().equals(definition.getKey()+station.getId())){
								if(ids.contains(activitiTask.getTaskuserid())){
									activitiTasks.add(activitiTask.getTaskuserid());
								}
							}
						}
						map.put("users", activitiTasks);
						taskDefinitions.add(map);
					}
				}else{
					List<String> activitiTasks = new ArrayList<String>();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", definition.getKey());
					map.put("name", definition.getNameExpression().getExpressionText());
					map.put("orderby", orderby.get(definition.getKey()));
					for (ActivitiTask activitiTask : tasks) {
						if(activitiTask.getTaskid().equals(definition.getKey())){
							if(ids.contains(activitiTask.getTaskuserid())){
								activitiTasks.add(activitiTask.getTaskuserid());
							}
						}
					}
					map.put("users", activitiTasks);
					taskDefinitions.add(map);
				}
			}
		}
		return taskDefinitions;
	}

	@Override
	public int saveTaskUser(String jsonStr, String pid, String userid) {
		// TODO Auto-generated method stub
		String[] strs=jsonStr.split("/");
		List<ActivitiTask> tasks = new ArrayList<ActivitiTask>();
		for (String string : strs) {
			if(string.trim()!=""&&string!=null){
				String[] strs1=string.split(",");
				String taskid=strs1[0];
				for (int i=1;i<strs1.length;i++) {
					ActivitiTask task = new ActivitiTask();
					task.setCreatetime(new Date());
					task.setEdittime(new Date());
					task.setEdituserid(userid);
					task.setId(UUID.randomUUID().toString());
					task.setTaskid(taskid);
					task.setProcessdefinitionid(pid);
					task.setTaskuserid(strs1[i]);
					tasks.add(task);
				}
			}
		}
		taskMapper.deleteByPrimaryKey(pid);
		taskMapper.insert(tasks);
		return 0;
	}

	@Override
	public InputStream findViewById(String id) {
		// TODO Auto-generated method stub
		ProcessDefinition definition=repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
		return repositoryService.getResourceAsStream(definition.getDeploymentId(),definition.getDiagramResourceName());
	}

	@Override
	public List<String> findUserids(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return taskMapper.selectUser(map);
	}

	@Override
	public int deleteDeployment(String id) throws Exception {
		repositoryService.deleteDeployment(id, true);
		return 0;
	}

	@Override
	public List<ActivitiTask> findActivitiTasks(String id) throws Exception {
		// TODO Auto-generated method stub
		return taskMapper.selectUserAll(id);
	}
}
