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
import com.benqzl.service.patrol.PatrolPlanService;
import com.benqzl.service.system.MessageCenterService;
import com.benqzl.service.system.WorkFlowService;
import com.benqzl.unit.SendMessageAutoUtil;

public class PatrolplanListener implements TaskListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -237065885825427387L;

	@Override
	public void notify(DelegateTask arg0) {
		// TODO Auto-generated method stub
		String processDefinitionId=arg0.getProcessDefinitionId();//流程定义ID
		String taskDefinitionKey=arg0.getTaskDefinitionKey();//任务key
		String stationid = null;
		Subject subject = SecurityUtils.getSubject();
		User user= (User) subject.getSession().getAttribute("loginUser");
		if(taskDefinitionKey.equals("confirm")){
			taskDefinitionKey="execute";
		}
		WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		PatrolPlanService patrolPlanService = (PatrolPlanService) ac.getBean("patrolPlanService");
		stationid = patrolPlanService.selectSidByPPID(arg0.getVariable("patrolplanId").toString());
		List<ActivitiTask> activitiTasks = new ArrayList<ActivitiTask>();
		WorkFlowService service=(WorkFlowService) ac.getBean("workFlowService");
		try {
			activitiTasks=service.findActivitiTasks(processDefinitionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> userids = new ArrayList<>();
		for (ActivitiTask activitiTask: activitiTasks) {
			if(activitiTask.getTaskid().equals(taskDefinitionKey+stationid)){
				userids.add(activitiTask.getTaskuserid());
			}
		}
		arg0.setVariable(arg0.getTaskDefinitionKey(), userids);
		MessageCenterService centerService = (MessageCenterService) ac
				.getBean("messageCenterService");
		String messageId = UUID.randomUUID().toString();
		arg0.setVariableLocal("messageId", messageId);
		User suser = new User();
		suser.setUserid(user.getUserid());
		List<MessageCenter> centers = new ArrayList<MessageCenter>();
		for (String string : userids) {
			MessageCenter center = new MessageCenter();
			center.setId(UUID.randomUUID().toString());
			center.setSendUser(suser);
			User ruser = new User();
			ruser.setUserid(string);
			center.setReceiverUser(ruser);
			center.setSendtime(new Date());
			center.setTid(messageId);
			Menu menu = new Menu();
			menu.setMenuid(arg0.getFormKey());
			center.setMenu(menu);
			centers.add(center);
			arg0.addCandidateUser(string);
		}
		try {
			if (centers.size() != 0 && centers != null) {
				centerService.insert(centers);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SendMessageAutoUtil.sendMessageListAuto(userids);
		
	}

}
