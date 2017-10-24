package com.benqzl.controller.dispatch.manage;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.dispatch.Receipt;
import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.service.dispatch.DispatchExecuteService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/** 
* @ClassName: DispatchExecuteController 
* @Description: TODO(执行情况操作类) 
* @author 冯庆国
* @date 2016年2月19日 上午9:17:02 
*  
*/
@Controller
@RequestMapping("dispatchExecute")
public class DispatchExecuteController extends BasicController{
	@Autowired
	private DispatchExecuteService service;
	
	public DispatchExecuteController() {
		super(DispatchExecuteController.class);
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/dispatch/dispatchExecute");
		return mv;
	}
	@RequestMapping(value = "dispatchExecuteInfo", method = RequestMethod.GET)
	public ModelAndView dispatchExecuteInfo(String id,String type){
		List<String> pid=new ArrayList<>();
		try {
			pid = service.selectPDById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sid = "";
		for (String string : pid) {
			if(!string.contains("districtDispath")){
				sid=string;
			}
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("pid", sid);
		mv.addObject("id", id);
		mv.setViewName("/system/image");
		return mv;
	}
	
	@RequestMapping(value = "imageInfo", method = RequestMethod.GET)
	public ModelAndView imageInfo(String sid){
		ModelAndView mv = new ModelAndView();
		mv.addObject("sid", sid);
		mv.setViewName("/system/imageInfo");
		return mv;
	}
	@RequestMapping(value = "dispatchExecutesInfo", method = RequestMethod.GET)
	public ModelAndView dispatchExecutesInfo(String id,String type){
		String sid = "";
		try {
			sid = service.selectPDByIdByD(id,type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("pid", sid);
		mv.addObject("id", id);
		mv.setViewName("/system/image");
		return mv;
	}
	

	@RequestMapping(value = "taskBind", method = RequestMethod.POST)
	@ResponseBody
	public String taskBind(String id){
		List<Map<String, Object>> maps = new ArrayList<>();
		try {
			maps = service.findHisTask(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(maps);
	}
	@RequestMapping(value = "bind", method = RequestMethod.POST)
	@ResponseBody
	public String bind(int page,int rows,String code,String starttime,String endtime,String typeDate){
		Map<String, Object> map = new HashMap<String, Object>();
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		if(code==null||code.equals("")){
			map.put("code", null);
		}else{
			map.put("code", code);
		}
		if(starttime==null||starttime.trim().equals("")){
			map.put("begintime", null);
		}else{
			try {
				if(typeDate.equals("0")){
					map.put("begintime", starttime);
				}else{
					map.put("begintime", datetimeFormat.parse(starttime));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endtime==null||endtime.trim().equals("")){
			map.put("endtime", null);
		}else{
			try {
				if(typeDate.equals("0")){
					map.put("endtime", endtime);
				}else{
					map.put("endtime", datetimeFormat.parse(endtime));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int count=0;
		List<Receipt> list;
		List<SelfDispatch> list1;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			if(typeDate.equals("2")){//自主调度原先为0
				list1=service.findByPageBySelfReceipt(map);
				count=service.pageCountBySelfReceipt(map);
				jsonMap.put("total", count);
				jsonMap.put("rows", list1);
			}else{
				int a = (typeDate.equals("1") ? 0:1);
				map.put("type", a);
				list=service.findByPageByReceipt(map);
				count=service.pageCountByReceipt(map);
				jsonMap.put("total", count);
				jsonMap.put("rows", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(typeDate.equals("2")){//自主调度原先为0
			gson = new GsonBuilder().registerTypeAdapter(SelfDispatch.class,
					new JsonSerializer<SelfDispatch>() {
						@Override
						public JsonElement serialize(SelfDispatch dispatch, Type typeOfSrc,JsonSerializationContext context) {
							JsonObject o = new JsonObject();
							o.addProperty("id", dispatch.getId());
							o.addProperty("code", dispatch.getCode());
							o.addProperty("lname", dispatch.getUser().getUsername());
							o.addProperty("fqname", dispatch.getUser().getUsername());//汇报发起人
							o.addProperty("ltime", datetimeFormat.format(dispatch.getPromotetime()));
							o.addProperty("rcname", dispatch.getCreateUser().getUsername());
							o.addProperty("rctime", datetimeFormat.format(dispatch.getCreatetime()));
							return o;
						}
					}).create();
			strJson=gson.toJson(jsonMap);
		}else{
			gson = new GsonBuilder().registerTypeAdapter(Receipt.class,
					new JsonSerializer<Receipt>() {
						@Override
						public JsonElement serialize(Receipt src, Type typeOfSrc,
								JsonSerializationContext context) {
							JsonObject o = new JsonObject();
							o.addProperty("id", src.getId());
							o.addProperty("code1", src.getCode());
							o.addProperty("lname", src.getLauncher());
							o.addProperty("ltime",
									datetimeFormat.format(src.getLaunchtime()));
							o.addProperty("etime",
									datetimeFormat.format(src.getEndtime()));
							o.addProperty("rname", src.getReceipteUser()
									.getUsername());
							o.addProperty("rtime",
									datetimeFormat.format(src.getReceiptetime()));
							o.addProperty("rcname",src.getCreater());
							o.addProperty("rctime",
									datetimeFormat.format(src.getCreatetime()));
							return o;
						}
					}).create();
			strJson=gson.toJson(jsonMap);
		}
		return strJson;
	}

}
