package com.benqzl.core.activiti;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.benqzl.service.system.MessageCenterService;
import com.benqzl.service.system.WorkFlowService;
import com.benqzl.unit.SendMessageAutoUtil;

public class StationListener implements TaskListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6072352580195607124L;

	@Override
	public void notify(DelegateTask arg0) {
		String processDefinitionId = arg0.getProcessDefinitionId();
		String stationId = (String) arg0.getVariables().get("stationId");
		arg0.setVariableLocal("stationId", stationId);
		String taskDefinitionKey = arg0.getTaskDefinitionKey();
		
		Subject subject = SecurityUtils.getSubject();
		User user= (User) subject.getSession().getAttribute("loginUser");
		
		WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		List<ActivitiTask> activitiTasks = new ArrayList<ActivitiTask>();
		WorkFlowService service=(WorkFlowService) ac.getBean("workFlowService");
		try {
			activitiTasks=service.findActivitiTasks(processDefinitionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> userIds = new ArrayList<>();
		for (ActivitiTask activitiTask : activitiTasks) {
			if(activitiTask.getTaskid().equals(taskDefinitionKey+stationId)){
				userIds.add(activitiTask.getTaskuserid());
			}
		}
		MessageCenterService centerService = (MessageCenterService) ac.getBean("messageCenterService");
		List<MessageCenter> centers = new ArrayList<MessageCenter>();
		String messageId = UUID.randomUUID().toString();
		arg0.setVariableLocal("messageId", messageId);
		User suser = new User();
		suser.setUserid(user.getUserid());
		for (String string : userIds) {
			MessageCenter center = new MessageCenter();
			center.setId(UUID.randomUUID().toString());
			center.setSendUser(suser);
			User ruser = new User();
			ruser.setUserid(string);
			center.setReceiverUser(ruser);
			center.setSendtime(new Date());
			center.setTid(messageId);
			Menu menu = new Menu();
			if(arg0.getVariable("type").equals("A")){
				menu.setMenuid("7af15cda-aa3a-485e-8969-fecafa2e7ce4");
			}else{
				menu.setMenuid("c09b8295-f5ec-42d6-8795-2151b893dff8");
			}
			center.setMenu(menu);
			centers.add(center);
			arg0.addCandidateUser(string);
		}
		try {
			if(centers.size()!=0&&centers!=null){
				centerService.insert(centers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		SendMessageAutoUtil.sendMessageListAuto(userIds);
	}

}
