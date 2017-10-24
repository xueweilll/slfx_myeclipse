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
import com.benqzl.service.dispatch.SelfDispatchStationsService;
import com.benqzl.service.system.MessageCenterService;
import com.benqzl.service.system.WorkFlowService;
import com.benqzl.unit.SendMessageAutoUtil;

/**
 * @ClassName: StationTaskUserListener
 * @Description: TODO(监听自主调度枢纽执行节点动态添加任务办理人)
 * @author 冯庆国
 * @date 2016年1月7日 下午12:44:43
 * 
 */
public class SefTaskUserListener implements TaskListener {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -1247553978378820465L;

	@Override
	public void notify(DelegateTask arg0) {
		// TODO Auto-generated method stub
		String processDefinitionId = arg0.getProcessDefinitionId();// 流程定义ID
		String taskDefinitionKey = arg0.getTaskDefinitionKey();// 任务key
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("loginUser");
		/*
		 * 通过流程定义ID,任务key查询数据库，得到任务办理人信息，设置组任务办理人
		 */
		WebApplicationContext ac = ContextLoader
				.getCurrentWebApplicationContext();
		List<String> strings = null;
		if (taskDefinitionKey.equals("station")) {
			SelfDispatchStationsService bigSurroundService = (SelfDispatchStationsService) ac
					.getBean("selfDispatchStationsService");
			try {
				strings = bigSurroundService.selectstations(arg0.getVariable(
						"sdId").toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<ActivitiTask> activitiTasks = new ArrayList<ActivitiTask>();
		WorkFlowService service = (WorkFlowService) ac
				.getBean("workFlowService");
		try {
			activitiTasks = service.findActivitiTasks(processDefinitionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<String> userids = new ArrayList<>();
		if (taskDefinitionKey.equals("station")) {
			if (strings.size() != 0 && strings != null) {
				for (String string : strings) {
					Map<String, Object> map = new HashMap<>();
					map.put("statiodId", string);
					List<String> strings2 = new ArrayList<>();
					for (ActivitiTask activitiTask : activitiTasks) {
						if (activitiTask.getTaskid().equals(
								taskDefinitionKey + string)) {
							strings2.add(activitiTask.getTaskuserid());
							userids.add(activitiTask.getTaskuserid());
						}
					}
					map.put("userids", strings2);
					list.add(map);
				}
				arg0.setVariable("stationsInfo", list);
			}

		} else {
			for (ActivitiTask activitiTask : activitiTasks) {
				if (activitiTask.getTaskid().equals(taskDefinitionKey)) {
					userids.add(activitiTask.getTaskuserid());
				}
			}
		}
		MessageCenterService centerService = (MessageCenterService) ac
				.getBean("messageCenterService");
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
			Menu menu = new Menu();
			center.setTid(arg0.getVariable("sdId").toString());
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
