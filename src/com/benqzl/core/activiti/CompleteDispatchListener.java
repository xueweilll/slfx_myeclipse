package com.benqzl.core.activiti;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.benqzl.service.dispatch.BigSurroundService;
import com.benqzl.service.dispatch.DispatchIssuedListService;

/** 
* @ClassName: CompleteDispatchListener 
* @Description: TODO(流程结束改写接收调度单状态监听事件) 
* @author 冯庆国
* @date 2016年1月12日 上午9:10:27 
*  
*/
public class CompleteDispatchListener implements ExecutionListener{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 4920824100694701004L;

	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		String id = arg0.getCurrentActivityId();
		WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		Map<String, Object> map=arg0.getVariables();
		if(id.equals("SDdistrictEnd")){
			//获取自主调度service
			DispatchIssuedListService dispatchIssuedListService=(DispatchIssuedListService) ac.getBean("DispatchIssuedListService");
			//获取主表Id
			String sdId = map.get("objId").toString();
			if((boolean) map.get("order")){
				dispatchIssuedListService.updateComplete(sdId);
			}
		}else if(id.equals("districtEnd")){
			BigSurroundService bigSurroundService = (BigSurroundService) ac.getBean("bigSurroundService");
			String receiptId = map.get("objId").toString();
			Map<String, Object> maps = new HashMap<>();
			maps.put("time", new Date());
			maps.put("id", receiptId);
			maps.put("state", 3);
			bigSurroundService.updateState(maps);
		}
		
	}

}
