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

public class DistrictListener implements TaskListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6072352580195607124L;

	@Override
	public void notify(DelegateTask arg0) {
		String processDefinitionId = arg0.getProcessDefinitionId();
		String departmentId = (String) arg0.getVariables().get("departmentId");
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
			
			if(activitiTask.getTaskid().equals(taskDefinitionKey+departmentId)){
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
			if(taskDefinitionKey.equals("districtAuto")){
				menu.setMenuid("6f6edf5-dbe3-41fd-982f-232733848015");
				arg0.removeVariable("departmentId");
			}else if(arg0.getVariable("type").equals("A")){
				menu.setMenuid("8014c933-0be8-4d4c-92ef-ef29e2ed7e58");
			}else{
				menu.setMenuid("40d36a9c-6681-4152-a690-af01d34e38b3");
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
