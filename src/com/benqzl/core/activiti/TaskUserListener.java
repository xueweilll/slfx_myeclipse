package com.benqzl.core.activiti;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.benqzl.pojo.system.ActivitiTask;
import com.benqzl.pojo.system.Menu;
import com.benqzl.pojo.system.MessageCenter;
import com.benqzl.pojo.system.User;
import com.benqzl.service.dispatch.BigSurroundService;
import com.benqzl.service.patrol.PatrolPlanService;
import com.benqzl.service.system.MessageCenterService;
import com.benqzl.service.system.WorkFlowService;
import com.benqzl.unit.SendMessageAutoUtil;


/** 
* @ClassName: TaskUserListener 
* @Description: TODO(监听任务节点，动态添加任务办理人——需数据库存在任务办理人信息) 
* @author 冯庆国
* @date 2015年12月29日 下午1:17:01 
*  
*/
public class TaskUserListener implements TaskListener{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -6893485249968260856L;
	@Override
	public void notify(DelegateTask arg0) {
		// TODO Auto-generated method stub
		String processDefinitionId=arg0.getProcessDefinitionId();//流程定义ID
		String taskDefinitionKey=arg0.getTaskDefinitionKey();//任务key
		if(taskDefinitionKey.equals("confirm")){
			taskDefinitionKey="execute";
		}
		Subject subject = SecurityUtils.getSubject();
		User user= (User) subject.getSession().getAttribute("loginUser");
		/*
		 * 通过流程定义ID,任务key查询数据库，得到任务办理人信息，设置组任务办理人
		 */
		WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		List<String> strings=null;
		List<String> patrolplan = new ArrayList<>();
		patrolplan.add("execute");
		patrolplan.add("confirm");
		patrolplan.add("aduit");
		String stationid=null;
		if(taskDefinitionKey.equals("station")){
			BigSurroundService bigSurroundService =  (BigSurroundService) ac.getBean("bigSurroundService");
			try {
				strings = bigSurroundService.findStationsIds(arg0.getVariable("receiptId").toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(patrolplan.contains(taskDefinitionKey)){
			//传递主表ID,获取枢纽ID集合
			PatrolPlanService patrolPlanService = (PatrolPlanService) ac.getBean("patrolPlanService");
			stationid = patrolPlanService.selectSidByPPID(arg0.getVariable("patrolplanId").toString());
		}
		List<ActivitiTask> activitiTasks = new ArrayList<ActivitiTask>();
		WorkFlowService service=(WorkFlowService) ac.getBean("workFlowService");
		try {
			activitiTasks=service.findActivitiTasks(processDefinitionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<String> userids = new ArrayList<>();
		if(taskDefinitionKey.equals("station")){
			if(strings.size()!=0&&strings!=null){
				for (String string : strings) {
					Map<String, Object> map = new HashMap<>();
					map.put("statiodId", string);
					List<String> strings2 = new ArrayList<>();
					for (ActivitiTask activitiTask: activitiTasks) {
						if(activitiTask.getTaskid().equals(taskDefinitionKey+string)){
							strings2.add(activitiTask.getTaskuserid());
							userids.add(activitiTask.getTaskuserid());
						}
					}
					map.put("userids", strings2);
					list.add(map);
				}
				arg0.setVariable("stationsInfo", list);
			}
			
		}else if(patrolplan.contains(taskDefinitionKey)){
			List<String> strings2 = new ArrayList<>();
			for (ActivitiTask activitiTask: activitiTasks) {
				if(activitiTask.getTaskid().equals(taskDefinitionKey+stationid)){
					strings2.add(activitiTask.getTaskuserid());
					userids.add(activitiTask.getTaskuserid());
				}
			}
			arg0.setVariable(taskDefinitionKey, strings2);
		}else{
			for (ActivitiTask activitiTask: activitiTasks) {
				if(activitiTask.getTaskid().equals(taskDefinitionKey)){
					userids.add(activitiTask.getTaskuserid());
				}
			}
		}
		String tid=null;
		if(arg0.getVariable("sdId")!=null){
			tid=arg0.getVariable("sdId").toString();
		}else if(patrolplan.contains(taskDefinitionKey)){
			tid=arg0.getVariable("patrolplanId").toString();
		}else if(arg0.getVariable("maintainId")!=null){
			tid=arg0.getVariable("maintainId").toString();
		}else{
			tid=arg0.getVariable("receiptId").toString();
		}
		MessageCenterService centerService = (MessageCenterService) ac.getBean("messageCenterService");
		List<MessageCenter> centers = new ArrayList<MessageCenter>();
		for (String string : userids) {
			MessageCenter center = new MessageCenter();
			center.setId(UUID.randomUUID().toString());
			User suser = new User();
			suser.setUserid(user.getUserid());
			center.setSendUser(suser);
			User ruser = new User();
			ruser.setUserid(string);
			center.setReceiverUser(ruser);
			center.setSendtime(new Date());
			center.setTid(tid);
			Menu menu = new Menu();
			menu.setMenuid(arg0.getFormKey());
			center.setMenu(menu);
			centers.add(center);
			arg0.addCandidateUser(string);
		}
		try {
			if(centers.size()!=0&&centers!=null){
				centerService.insert(centers);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SendMessageAutoUtil.sendMessageListAuto(userids);
	}

}
