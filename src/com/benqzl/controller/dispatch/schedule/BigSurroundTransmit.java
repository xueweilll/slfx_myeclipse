package com.benqzl.controller.dispatch.schedule;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.dispatch.Receipt;
import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;
import com.benqzl.pojo.util.ReceiptAndDispatch;
import com.benqzl.service.activiti.ActivitiUnitService;
import com.benqzl.service.dispatch.BigSurroundService;
import com.benqzl.service.dispatch.ReceiptDispatchService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/** 
* @ClassName: BigSurroundController 
* @Description: TODO(大包围调度操作) 
* @author pxj
* @date 2016年1月4日 下午3:30:51 
*  
*/
@Controller
@RequestMapping("dispatchTransmitList")
public class BigSurroundTransmit extends BasicController{
	@Autowired
	private BigSurroundService service;
	@Autowired
	private ReceiptDispatchService receiptDispatchService;
	@Autowired
	private ActivitiUnitService activitiUnitService;
	public BigSurroundTransmit() {
		super(BigSurroundTransmit.class);
	}
	
	/** 
	* @Title: index 
	* @Description: TODO(任务中心操作页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/dispatch/dispatchTransmitList");
		return mv;
	}
	/** 
	* @Title: dispatchTransmitList 
	* @Description: TODO(调度转发list) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/dispatchTransmitList", method = RequestMethod.GET)
	public ModelAndView dispatchTransmitList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/dispatchTransmitList");
		return mv;
	}
	/** 
	* @Title: dispatchTransmit 
	* @Description: TODO(调度转发) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/dispatchTransmit", method = RequestMethod.GET)
	public ModelAndView dispatchTransmit(String id,String receiptid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id",id);
	
		if(id.equals("0")){
			mv.addObject("dcode",UUID.randomUUID().toString());
		}else{
			try {
				ReceiptDispatch dispatch = service.findDispatchById(id);
				Receipt receipt=service.findByTransmitID(receiptid);
			    mv.addObject("receiptname", receipt.getEmployee().getName());
			    mv.addObject("way",receipt.getWay().equals(new Long("0"))?"电话":"传真");
			    mv.addObject("launcher", receipt.getLauncher());
			    mv.addObject("launchertime",datetimeFormat.format(receipt.getLaunchtime()));
			    mv.addObject("endtime",datetimeFormat.format(receipt.getEndtime()));
			    mv.addObject("receiptetime",datetimeFormat.format(receipt.getReceiptetime()));
			    mv.addObject("dispatch", dispatch);
				mv.addObject("dcode",dispatch.getCode());
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		
		mv.setViewName("/dispatch/dispatchTransmit");
		return mv;
	}
	@RequestMapping(value = "/transmit", method = RequestMethod.POST)
	@ResponseBody
	public String transmit(String id,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("transmiter", user.getUserid());
			ReceiptDispatch rd = receiptDispatchService.selectByPk(id);
			/*int state =3;
			if(rd.getState() == 2){
				state = 4;
			}*/
			map.put("trstate", 1);
			service.updateByPrimaryKeyTransmit(map);
			//activitiUnitService.messagePush("forward", user.getUserid(), rd.getReceiptid());
			//activitiUnitService.completeTask("forward", user.getUserid(), rd.getReceiptid());
			activitiUnitService.completeTask("forward", user.getUserid(), rd.getId(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'转发失敗！'}";
		}

		return "{'result':true,'msg':'转发成功！'}";
	}
	
	/** 
	* @Title: stationsBind 
	* @Description: TODO(站点信息绑定) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/stationsBind",method=RequestMethod.POST)
	@ResponseBody
	public String stationsBind(String id){
		List<Station> maps = new ArrayList<Station>();
		try {
			maps = service.findStations(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gson = new GsonBuilder().registerTypeAdapter(Station.class, new JsonSerializer<Station>() {
			@Override
			public JsonElement serialize(Station arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("name", arg0.getName());
				if(arg0.getCode()==null){
					json.addProperty("code", false);
				}else{
					json.addProperty("code", true);
				}
				return json;
			}
		}).create();
		return gson.toJson(maps);
	}
	/** 
	* @Title: instructionsBind 
	* @Description: TODO(指令信息绑定) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/instructionsBind",method=RequestMethod.POST)
	@ResponseBody
	public String instructionsBind(String id){
		List<Map<String, Object>> list = null;
		try {
			list = service.findInstructions(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.toJson(list);
	}
	
	
	public void insert(ReceiptDispatch receiptDispatch,String userid){
		try {
			boolean result =service.insert(receiptDispatch);
			if(result){
				//activitiUnitService.messagePush("bigOrder", userid, receiptDispatch.getReceiptid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	@ResponseBody
	public String commit(String jsonStr,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("loginUser");
		try {
			ReceiptDispatch receiptDispatch = gson.fromJson(jsonStr, ReceiptDispatch.class);
			receiptDispatch.setCreater(user.getUserid());
			receiptDispatch.setCreatetime(new Date());
			insert(receiptDispatch, user.getUserid());
			//activitiUnitService.completeTask("bigOrder", user.getUserid(), receiptDispatch.getReceiptid());
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'保存失败！'}";
		}
		return "{'result':true}";
	}
	
	/** 
	* @Title: disReceipt 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/disReceipt",method=RequestMethod.POST)
	@ResponseBody
	public String disReceipt(HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("loginUser");
		List<Receipt> list = null;
		try {
			List<String> strings = activitiUnitService.findObjIds("bigOrder",user.getUserid(), "C");
			list = service.findReceipts(strings);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String strJson="";
		gson = new GsonBuilder().registerTypeAdapter(Receipt.class, new JsonSerializer<Receipt>() {
			@Override
			public JsonElement serialize(Receipt arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("CODE", arg0.getCode());
				return json;
			}
		}).create();
		strJson = gson.toJson(list);
		return strJson;
	}
	
	@RequestMapping(value="/receiptBind",method=RequestMethod.POST)
	@ResponseBody
	public String receiptBind(String id){
		Receipt receipt = null;
		try {
			receipt = service.findReceiptById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String strJson="";
		gson = new GsonBuilder().registerTypeAdapter(Receipt.class, new JsonSerializer<Receipt>() {
			@Override
			public JsonElement serialize(Receipt arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("way", arg0.getWay().equals(new Long("0"))?"电话":"传真");
				json.addProperty("launchTime", datetimeFormat.format(arg0.getLaunchtime()));
				json.addProperty("launcher", arg0.getLauncher());
				json.addProperty("endTime",datetimeFormat.format(arg0.getEndtime()));
				json.addProperty("receipter", arg0.getReceipter());
				json.addProperty("receipteTime", datetimeFormat.format(arg0.getReceiptetime()));
				json.addProperty("memo", arg0.getMemo());
				json.addProperty("code", arg0.getCode());
				return json;
			}
		}).create();
		strJson = gson.toJson(receipt);
		return strJson;
	}	
	@RequestMapping(value="/dispatchTransmitList",method=RequestMethod.POST)
	@ResponseBody
	public String dispatchTransmitList(HttpServletRequest request,int page,int rows,String code,String bh,String type,String starttime,String endtime,String typeDate){
		User user=(User)request.getSession().getAttribute("loginUser");
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReceiptAndDispatch> list = new ArrayList<>();
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		map.put("type", "1");
		map.put("state", 0);
		if(bh==null||bh==""){
			map.put("bh", null);
		}else{
			map.put("bh", bh.replaceAll(" ", ""));
		}
		if(typeDate.equals("1")){
			map.put("rpid", null);
		}else{
			try {
				List<String> receipts = activitiUnitService.findObjIds("forward",user.getUserid(),  "C");
				//List<String> receipts = service.findReceiptIds(strings,"1");
				if(receipts.size()==0){
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					jsonMap.put("total", 0);
					jsonMap.put("rows", list);
					gson = new Gson();
					strJson=gson.toJson(jsonMap);
					return strJson;
				}else{
					map.put("rpid", receipts);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(code==null||code.equals("")){
			map.put("code", null);
		}else{
			map.put("code", code);
		}
		if(type==null||type.equals("")){
			map.put("way", null);
		}else{
			map.put("way", type);
		}
		if(starttime==null||starttime.trim().equals("")){
			map.put("stime", null);
		}else{
			try {
				map.put("stime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endtime==null||endtime.trim().equals("")){
			map.put("etime", null);
		}else{
			try {
				map.put("etime", datetimeFormat.parse(endtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int count=0;
		try {
			list=service.findByPage(map);
			count=service.pageCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", count);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(ReceiptAndDispatch.class, new JsonSerializer<ReceiptAndDispatch>() {
			@Override
			public JsonElement serialize(ReceiptAndDispatch arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("receiptid1", arg0.getReceipt().getId());
				json.addProperty("id", arg0.getDispatch().getId());
				json.addProperty("code", arg0.getDispatch().getCode());
				json.addProperty("receiptid", arg0.getReceipt().getCode());
				json.addProperty("username", arg0.getLname());
				json.addProperty("receipter", arg0.getRname());
				json.addProperty("creater", arg0.getDcname());
				json.addProperty("handler", arg0.getRcname());
				json.addProperty("edname", arg0.getEdname());
				json.addProperty("transmiter", arg0.getTransmiter());
				if(arg0.getDispatch().getTransmittime()==null){
					json.addProperty("transmittime","" );
				}else{
					json.addProperty("transmittime", datetimeFormat.format(arg0.getDispatch().getTransmittime()));
				}
				json.addProperty("launchtime", datetimeFormat.format(arg0.getReceipt().getLaunchtime()));
				json.addProperty("rtime", datetimeFormat.format(arg0.getReceipt().getReceiptetime()));
				json.addProperty("createtime", datetimeFormat.format(arg0.getDispatch().getCreatetime()));
				json.addProperty("handletime", datetimeFormat.format(arg0.getDispatch().getCreatetime()));
				json.addProperty("endtime", datetimeFormat.format(arg0.getReceipt().getEndtime()));
				
/*				switch (arg0.getDispatch().getState().toString()) {
						case "0":
							str="保存";
							break;
						case "1":
							str="已提交";
							break;
						case "2":
							str="已下发未转发";
							break;
						case "3":
							str="已转发未下发";
							break;
						case "4":
							str="下发并且已转发";
							break;
						case "5":
							str="片区调度完成";
							break;
						case "6":
							str="回访完成";
							break;
						case "7":
							str="完成";
							break;
				}*/
				
				json.addProperty("state", arg0.getTransmiter()==null? "未转发":"已转发");
				return json;
			}
		}).create();
		strJson=gson.toJson(jsonMap);
		return strJson;
	}
}

