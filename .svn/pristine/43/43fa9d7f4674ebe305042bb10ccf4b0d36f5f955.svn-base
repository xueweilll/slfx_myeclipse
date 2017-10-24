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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.ReceiptDispatchSupervise;
import com.benqzl.pojo.system.User;
import com.benqzl.service.activiti.ActivitiUnitService;
import com.benqzl.service.dispatch.ReceiptDispatchService;
//import com.benqzl.service.dispatch.ReceiptDispatchStationsService;
import com.benqzl.service.dispatch.ReceiptDispatchSuperviseServise;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


@Controller
@RequestMapping("supervise")
public class SuperviseController extends BasicController{
	public SuperviseController() {
		super(SuperviseController.class);
	}

	@Autowired
	private ReceiptDispatchService receiptDispatchService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView supervise() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dispatch/rdsupervise");
		return mv;
	}
	

	/**
	 * @Title: supervise
	 * @Description: 督察(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "superviseInfo", method = RequestMethod.GET)
	public ModelAndView superviseInfo(String rdid) {
		ModelAndView mv = new ModelAndView();
		ReceiptDispatch rd = receiptDispatchService.selectByPk(rdid);
		mv.addObject("rd", rd);
		mv.addObject("launchtime",
				datetimeFormat.format(rd.getReceipt().getLaunchtime()));
		mv.addObject("endtime",
				datetimeFormat.format(rd.getReceipt().getEndtime()));
		mv.addObject("receiptetime",
				datetimeFormat.format(rd.getReceipt().getReceiptetime()));
		mv.setViewName("dispatch/rdsuperviseInfo");
		return mv;
	}
	
	@RequestMapping(value = "superviseLookInfo", method = RequestMethod.GET)
	public ModelAndView superviseLookInfo(String rdid){
		ModelAndView mv = new ModelAndView();
		ReceiptDispatch rd = receiptDispatchService.selectByPk(rdid);
		mv.addObject("rd", rd);
		mv.addObject("launchtime",
				datetimeFormat.format(rd.getReceipt().getLaunchtime()));
		mv.addObject("endtime",
				datetimeFormat.format(rd.getReceipt().getEndtime()));
		mv.addObject("receiptetime",
				datetimeFormat.format(rd.getReceipt().getReceiptetime()));
		mv.setViewName("dispatch/rdsuperviselookInfo");
		return mv;
	}
	
	/**
	 * @Title: save
	 * @Description: 保存(这里用一句话描述这个方法的作用)
	 * @param @param jsonStr
	 * @param @param type 0：回访 1：督察
	 * @return void 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr, String type, HttpSession session) {
		String result = "";
		gson = new Gson();
		if ("1".equals(type)) {
			ReceiptDispatchSupervise supervise = gson.fromJson(jsonStr,
					ReceiptDispatchSupervise.class);
			User user = (User) session.getAttribute("loginUser");
			UUID uuid = UUID.randomUUID();
			supervise.setId(uuid.toString());
			if (user != null) {
				supervise.setCreater(user.getUserid());
			}
			supervise.setCreatetime(new Date());
			/*ReceiptDispatch rd = receiptDispatchService.selectByPk(supervise
					.getRdid());*/
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", supervise.getRdid());
			map.put("trstate", 3);
			try {
				receiptDispatchService.insertSupervise(supervise);
				//if (rd.getState() == 5) {
					receiptDispatchService.updateByPrimaryKeyState(map);
				//}
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'督察失敗！'}";
			}
		}
		return result;
	}

	@Autowired
	private ActivitiUnitService activitiUnitService;

	@RequestMapping(value = "pageBind", method = RequestMethod.POST)
	@ResponseBody
	public String pageBind(HttpServletRequest request, int page, int rows,
			String type, String typeDate,String code,String launcher,String begintime,String endtime) throws ParseException {
		String strJson = "";
		Map<String, Object> map = new HashMap<String, Object>();
		if(begintime !=null && !begintime.equals("")){
			map.put("begintime", datetimeFormat.parse(begintime));
		}
		if(endtime !=null && !endtime.equals("")){
			map.put("endtime", datetimeFormat.parse(endtime));
		}
		gson = new Gson();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		User user = (User) request.getSession().getAttribute("loginUser");
		map.put("p1", start);
		map.put("p2", rows);
		map.put("type", "1");
		map.put("state", 2);
		if(code != null){
			map.put("code",code);
		}
		if(launcher != null){
			map.put("launcher", launcher);
		}
		if (typeDate.equals("1")) {
			map.put("rpid", null);
		} else {
			try {
				if (activitiUnitService.findObjIds("returnVisit",user.getUserid(), "C").size() == 0) {
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					jsonMap.put("total", 0);
					List<String> l = new ArrayList<String>();
					jsonMap.put("rows", l);
					strJson = gson.toJson(jsonMap);
					return strJson;
				} else {
					map.put("rpid",activitiUnitService.findObjIds("returnVisit",user.getUserid(), "C"));
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		List<ReceiptDispatch> list = receiptDispatchService.findRdList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = receiptDispatchService.findRdCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(ReceiptDispatch.class,
				new JsonSerializer<ReceiptDispatch>() {
					@Override
					public JsonElement serialize(ReceiptDispatch src,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("code", src.getCode());
						o.addProperty("dispatchtype", "大包围调度");
						o.addProperty("launcher", src.getReceipt()
								.getLauncher());
						o.addProperty("launchTime", datetimeFormat.format(src
								.getReceipt().getLaunchtime()));
						o.addProperty("endTime", datetimeFormat.format(src
								.getReceipt().getEndtime()));
						o.addProperty("receipter", src.getNames()
								.getReceiptName());
						o.addProperty("receipteTime", datetimeFormat.format(src
								.getReceipt().getReceiptetime()));
						o.addProperty("memo", src.getMemo());
						o.addProperty("creater", src.getNames().getCreateName());
						o.addProperty("createtime",
								datetimeFormat.format(src.getCreatetime()));
						o.addProperty("transmit", src.getNames().getTransmitName());
						if (src.getTransmittime() != null) {
							o.addProperty("transmittime",datetimeFormat.format(src.getTransmittime()));
						}
						o.addProperty("state", src.getTrstate());
						if (src.getTrstate() <= 2) {
							o.addProperty("stateName", "未督察");
						}else if(src.getTrstate() > 3){
							o.addProperty("stateName", "转发完成");
						}else {
							o.addProperty("stateName", "督察中");
						}
						return o;
					}
				}).create();
		strJson = gson.toJson(jsonMap);

		logger.info("this document list strJson is " + strJson);

		return strJson;
	}
	
	@Autowired
	ReceiptDispatchSuperviseServise receiptDispatchSuperviseServise;
	
	@RequestMapping(value = "spListBind", method = RequestMethod.POST)
	@ResponseBody
	public String cbListBind(String rdid){
		List<ReceiptDispatchSupervise> list = receiptDispatchSuperviseServise.selectByRDID(rdid);
		gson = new GsonBuilder().registerTypeAdapter(ReceiptDispatchSupervise.class,
				new JsonSerializer<ReceiptDispatchSupervise>() {
					@Override
					public JsonElement serialize(ReceiptDispatchSupervise src,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("rdid", src.getRdid());
						o.addProperty("callbackpeople", src.getSupervisepeople());
						o.addProperty("callbackunit", src.getSuperviseunit());
						o.addProperty("callbacktime", datetimeFormat.format(src.getSupervisetime()));
						o.addProperty("operater", src.getOperateUser().getUsername());
						o.addProperty("callback", src.getSupervise());
						o.addProperty("creater", src.getCreater());
						o.addProperty("createtime",	datetimeFormat.format(src.getCreatetime()));
						return o;
					}
				}).create();
		//logger.info("this document list strJson is " + strJson);
		return gson.toJson(list);
	}
	

	/*@Autowired
	private ReceiptDispatchStationsService receiptDispatchStationsService;*/
	
	
	/** 
	* @Title: complete 
	* @Description: 完成(这里用一句话描述这个方法的作用) 
	* @param @param type
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "complete", method = RequestMethod.POST)
	@ResponseBody
	public String complete(String id,HttpSession session) {
		String result = "";
		//ReceiptDispatch rd = receiptDispatchService.selectByPk(id);
		User user = (User)session.getAttribute("loginUser");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);		
		map.put("trstate", 4);
		try {
			//activitiUnitService.messagePush("returnVisit", user.getUserid(), rd.getReceiptid());
			activitiUnitService.completeTask("returnVisit", user.getUserid(), id, null);			
			receiptDispatchService.updateByPrimaryKeyState(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
