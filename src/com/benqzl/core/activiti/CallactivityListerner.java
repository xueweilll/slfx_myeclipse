package com.benqzl.core.activiti;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.benqzl.service.dispatch.BigSurroundService;

public class CallactivityListerner implements ExecutionListener{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 8177847311953702820L;

	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		Map<String, Object> map=arg0.getVariables();
		BigSurroundService bigSurroundService = (BigSurroundService) ac.getBean("bigSurroundService");
		String receiptId = map.get("objId").toString();
		Map<String, Object> maps = new HashMap<>();
		maps.put("time", new Date());
		maps.put("id", receiptId);
		maps.put("state", 3);
		bigSurroundService.updateState(maps);
	}

}
