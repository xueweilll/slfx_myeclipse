package com.benqzl.controller.dispatch.water;

import java.lang.reflect.Type;
import java.text.ParseException;
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
import com.benqzl.pojo.water.TrGaterun;
import com.benqzl.pojo.water.TrPumprun;
import com.benqzl.service.water.HistoryWorkSituationService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Controller
@RequestMapping("historyWorkSituation")
public class HistoryWorkSituationController extends BasicController {

	@Autowired
	private HistoryWorkSituationService service;

	public HistoryWorkSituationController() {
		super(HistoryWorkSituationController.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Title: index
	 * @Description: TODO(物资调拨增删改查页面)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/water/historyWorkSituation");
		return mv;
	}

	@RequestMapping(value = "pumprunBind", method = RequestMethod.POST)
	@ResponseBody
	public String pumprunBind(int page, int rows, String starttime,
			String endtime, String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		if (code == null || code.equals("")) {
			map.put("code", null);
		} else {
			map.put("code", code);
		}
		if (starttime == null || starttime.trim().equals("")) {
			map.put("starttime", null);
		} else {
			try {
				map.put("starttime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (endtime == null || endtime.trim().equals("")) {
			map.put("endtime", null);
		} else {
			try {
				map.put("endtime", datetimeFormat.parse(endtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<TrPumprun> list = null;
		int pageCount = 0;
		try {
			list = service.findTrPumprunByPage(map);
			pageCount = service.pageCountTrPumprun(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", pageCount);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(TrPumprun.class,
				new JsonSerializer<TrPumprun>() {
					@Override
					public JsonElement serialize(TrPumprun arg0, Type arg1,
							JsonSerializationContext arg2) {
						// TODO Auto-generated method stub
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getfId());
						json.addProperty("sname", arg0.getfStationcode());
						json.addProperty("code", arg0.getfPumpcode());
						json.addProperty("time",
								datetimeFormat.format(arg0.getfTime()));
						String str1=null;
						if(arg0.getfRunstate()!=null){
						switch (arg0.getfRunstate()) {
						case "1":
							str1 = "启动";
							break;
						case "2":
							str1 = "停止";
							break;
						default:
							str1 = "报警";
							break;
						}}
						json.addProperty("runState", str1);
						String str=null;
						if(arg0.getfDir()!=null){
							switch (arg0.getfDir()) {
							case "1":
								str = "引水";
								break;
							default:
								str = "排水";
								break;
							}
						}
						json.addProperty("dir", str);
						json.addProperty("va", arg0.getfVa());
						json.addProperty("vb", arg0.getfVb());
						json.addProperty("vc", arg0.getfVc());
						json.addProperty("ia", arg0.getfIa());
						json.addProperty("ib", arg0.getfIb());
						json.addProperty("ic", arg0.getfIc());
						json.addProperty("power", arg0.getfPower());
						json.addProperty("flow", arg0.getfFlow());
						json.addProperty("temp", arg0.getfTemp());
						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}

	@RequestMapping(value = "gateBind", method = RequestMethod.POST)
	@ResponseBody
	public String gateBind(int page, int rows, String starttime,
			String endtime, String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		if (code == null || code.equals("")) {
			map.put("code", null);
		} else {
			map.put("code", code);
		}
		if (starttime == null || starttime.trim().equals("")) {
			map.put("starttime", null);
		} else {
			try {
				map.put("starttime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (endtime == null || endtime.trim().equals("")) {
			map.put("endtime", null);
		} else {
			try {
				map.put("endtime", datetimeFormat.parse(endtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<TrGaterun> list = null;
		int pageCount = 0;
		try {
			list = service.findTrGaterunByPage(map);
			pageCount = service.pageCountTrGaterun(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", pageCount);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(TrGaterun.class,
				new JsonSerializer<TrGaterun>() {
					@Override
					public JsonElement serialize(TrGaterun arg0, Type arg1,
							JsonSerializationContext arg2) {
						// TODO Auto-generated method stub
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getfId());
						json.addProperty("sname", arg0.getfStationcode());
						json.addProperty("code", arg0.getfGatecode());
						json.addProperty("time",
								datetimeFormat.format(arg0.getfTime()));
						String str1=null;
						if(arg0.getfRunstate()!=null){
						switch (arg0.getfRunstate()) {
						case "1":
							str1 = "上升";
							break;
						case "2":
							str1 = "停止";
							break;
						case "3":
							str1 = "下降";
							break;
						default:
							str1 = "报警";
							break;
						}}
						json.addProperty("runState", str1);
						json.addProperty("va", arg0.getfVa());
						json.addProperty("vb", arg0.getfVb());
						json.addProperty("vc", arg0.getfVc());
						json.addProperty("ia", arg0.getfIa());
						json.addProperty("ib", arg0.getfIb());
						json.addProperty("ic", arg0.getfIc());
						String str = null;
						if(arg0.getfPositionstate()!=null){
						switch (arg0.getfPositionstate()) {
						case "1":
							str = "全开";
							break;
						case "2":
							str = "中间";
							break;
						default:
							str = "全关";
							break;
						}}
						json.addProperty("state", str);
						json.addProperty("level", arg0.getfLevel());
						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}

}
