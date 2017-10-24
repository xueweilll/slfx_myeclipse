package com.benqzl.controller.dispatch.schedule;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.water.TrPumprun;
import com.benqzl.service.system.StationService;
import com.benqzl.service.system.UnitService;
import com.benqzl.service.water.PumprunItemsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

@Controller
@RequestMapping("realTimeUnitReport")
public class RealTimeUnitReportController extends BasicController {
	public RealTimeUnitReportController() {
		super(RealTimeUnitReportController.class);
	}

	@Autowired
	private PumprunItemsService pumprunItemsService;

	/**
	 * @Title: index @Description: TODO(返回实时数据页面) @param @return 设定文件 @return
	 * ModelAndView 返回类型 @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dispatch/realTimeUnitReportList");
		return mv;
	}

	/**
	 * @Title: realTimeUnitReportList @Description:
	 * TODO(查询实时数据报表数据) @param @param page 第几页 @param @param rows
	 * 一页多少条 @param @param jsonStr 枢纽code/机组code @param @param begintimes
	 * 开始时间 @param @param endtimes 结束时间 @param @return 设定文件 @return String
	 * 返回类型 @throws
	 */
	@RequestMapping(value = "realTimeUnitReportList", method = RequestMethod.POST)
	@ResponseBody
	public String realTimeUnitReportList(String jsonStr, String begintimes, String endtimes) {
		Map<String, Object> map = new HashMap<String, Object>();
		String strJson = "";
		String starttime="";
		String endtime="";
		List<String> sid = new ArrayList<>();
		List<String> uid = new ArrayList<>();
		if (jsonStr == null || jsonStr == "") {
			sid = null;
			uid = null;
		} else {
			List<String> sids = new ArrayList<>();
			List<String> uids = new ArrayList<>();
			gson = new Gson();
			TrPumprun stationJson = gson.fromJson(jsonStr, TrPumprun.class);// 获取前台传递的数据参数，解析成java类
			if (stationJson.getScode().size() != 0) {
				for (String stid : stationJson.getScode()) {
					sids.add(stid);
				}
				if(stationJson.getUcode().size()!=0){
				for (String unid : stationJson.getUcode()) {
					uids.add(unid);
				}
				}else{
					uids = null;
				}
			} else {
				sids = null;
				uids = null;
			}
			sid = sids;
			uid = uids;
		}
		map.put("sids", sid);
		map.put("uids", uid);
		 
		List<Map<String, Object>>  list = new ArrayList<>();
		if (begintimes == null || begintimes.trim().equals("")) {
			map.put("starttime", null);
		} else {
			try {
				map.put("starttime", datetimeFormat.parse(begintimes));
				starttime=begintimes;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (endtimes == null || endtimes.trim().equals("")) {
			map.put("endtime", null);
		} else {
			try {
				map.put("endtime", datetimeFormat.parse(endtimes));
				endtime=endtimes;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(sid==null&&uid==null&&(begintimes==null||begintimes.equals(""))&&(endtimes==null||endtimes.equals(""))){
	    	  Date date=new Date();
	    	  SimpleDateFormat	datetimeFormat =new SimpleDateFormat("yyyy-MM");
	    	  map.put("month", datetimeFormat.format(date));
	      }else{
			map.put("month", null);
			}
		try {
			list = pumprunItemsService.findAllByTime(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new LinkedHashMap<String, Object>();
		List<Map<String, Object>> l=list(list);
		jsonMap.put("rows", list);
		jsonMap.put("footer", l);
		jsonMap.put("starttime", starttime);//前台往后台传的开始时间
		jsonMap.put("endtime", endtime);//前台往后台传的结束时间
		strJson = gson.toJson(jsonMap);
		return strJson;
	}

	@Autowired
	private StationService stationService;

	/**
	 * @Title: stations @Description: TODO(查询枢纽) @param @return 设定文件 @return
	 * String 返回类型 @throws
	 */
	@RequestMapping(value = "station", method = RequestMethod.POST)
	@ResponseBody
	public String stations() {
		String result = "";
		List<Station> stations = new ArrayList<>();
		try {
			stations = stationService.findStation();
			gson = new GsonBuilder().registerTypeAdapter(Station.class, new JsonSerializer<Station>() {
				@Override
				public JsonElement serialize(Station arg0, Type arg1, JsonSerializationContext arg2) {
					JsonObject json = new JsonObject();
					json.addProperty("id", arg0.getId()); // 枢纽id
					json.addProperty("name", arg0.getName());// 枢纽名称
					json.addProperty("code", arg0.getCode());// 枢纽code
					return json;
				}
			}).create();

		} catch (Exception e) {
			e.printStackTrace();
		}
		result = gson.toJson(stations);
		return result;
	}

	@Autowired
	private UnitService unitservice;

	/**
	 * @Title: unit @Description: TODO(返回机组code) @param @param sids
	 * //枢纽id @param @return 设定文件 @return String 返回类型 @throws
	 */
	@RequestMapping(value = "unit", method = RequestMethod.POST)
	@ResponseBody
	public String unit(String[] sids) {
		String result = "";
		List<Unit> unit = new ArrayList<>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<String> sid = new ArrayList<>();
			if (sids.length != 0) {
				for (int i = 0; i < sids.length; i++) {
					sid.add(sids[i]);
				}
				map.put("sid", sid);
				unit = unitservice.findUnit(map);
				gson = new GsonBuilder().registerTypeAdapter(Unit.class, new JsonSerializer<Unit>() {
					@Override
					public JsonElement serialize(Unit arg0, Type arg1, JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId()); // 机组id
						json.addProperty("name", arg0.getName()); // 机组名称
						json.addProperty("code", arg0.getCode()); // 机组code
						return json;
					}
				}).create();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result = gson.toJson(unit);
		return result;
	}

	/** 
	* @Title: findUnitDetialsInfo 
	* @Description: TODO(查看机组运行详情info) 
	* @param @param uid
	* @param @param starttime
	* @param @param endtime
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/findUnitDetialsInfo", method = RequestMethod.GET)
	public ModelAndView findUnitDetialsInfo(String uid, String starttime, String endtime) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("uid",uid);
		mv.addObject("starttime", starttime);
        mv.addObject("endtime",endtime);
		mv.setViewName("dispatch/realTimeUnitReportInfo");
		return mv;
	}
	
	/** 
	* @Title: findUnitDetials 
	* @Description: TODO(查看机组运行记录) 
	* @param @param uid  机组id
	* @param @param starttime 机组开始时间
	* @param @param endtime   机组结束时间
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="findUnitDetials",method=RequestMethod.POST)
	@ResponseBody
	public String findUnitDetials(String uid,String starttime,String endtime){
		HashMap<String, Object> map=new HashMap<>();
		String json="";
		if (starttime == null || starttime.trim().equals("")) {
			map.put("starttime", null);
		} else {
			try {
				map.put("starttime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
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
		map.put("uid", uid);
		
		List<Map<String, Object>> lists=pumprunItemsService.findByUnit(map);
		gson=new Gson();
		json=gson.toJson(lists);
		return json;
	}
	
	
	
	
	
	/** 
	* @Title: export 
	* @Description: TODO(实时统计导出execle) 
	* @param @param jsonStr    ucode=机组code/scode=枢纽code
	* @param @param begintimes 开始时间
	* @param @param endtimes   结束时间
	* @param @param response    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "export", method = RequestMethod.GET)
	@ResponseBody
	public void export(String jsonStr, String begintimes, String endtimes, HttpServletResponse response) {
		WritableWorkbook book = null;
		OutputStream so = null;
		String filename = "实时统计报表";
		try {
			so = response.getOutputStream();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> sid = new ArrayList<>();
		List<String> uid = new ArrayList<>();
		if (jsonStr == null || jsonStr == "") {
			sid = null;
			uid = null;
		} else {
			List<String> sids = new ArrayList<>();
			List<String> uids = new ArrayList<>();
			gson = new Gson();
			TrPumprun stationJson = gson.fromJson(jsonStr, TrPumprun.class);// 获取前台传递的数据参数，解析成java类
			if (stationJson.getScode().size() != 0) {
				for (String stid : stationJson.getScode()) {
					sids.add(stid);
				}
				if(stationJson.getUcode().size()!=0){
					for (String unid : stationJson.getUcode()) {
						uids.add(unid);
					}
					}else{
						uids = null;
					}
			} else {
				sids = null;
				uids = null;
			}
			sid = sids;
			uid = uids;
		}
		map.put("sids", sid);
		map.put("uids", uid);
		
		List<Map<String, Object>> list = new ArrayList<>();
		//List<RealTimeUnitReport> stationUnitList = new ArrayList<>();
		if (begintimes == null || begintimes.trim().equals("")) {
			map.put("starttime", null);
		} else {
			try {
				map.put("starttime", datetimeFormat.parse(begintimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (endtimes == null || endtimes.trim().equals("")) {
			map.put("endtime", null);
		} else {
			try {
				map.put("endtime", datetimeFormat.parse(endtimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(sid==null&&uid==null&&(begintimes==null||begintimes.equals(""))&&(endtimes==null||endtimes.equals(""))){
	    	  Date date=new Date();
	    	  SimpleDateFormat	datetimeFormat =new SimpleDateFormat("yyyy-MM");
	    	  map.put("month", datetimeFormat.format(date));
	      }else{
			map.put("month", null);
			}
		try {
			//list = receiptDispatchExecute.explortRealTimeUnitReport(map);
			list = pumprunItemsService.findAllByTime(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	/*	try {
			stationUnitList=unitService.findRealTimeReportUnitList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<stationUnitList.size();i++){
			for(int a=0;a<list.size();a++){
				if(stationUnitList.get(i).getScode().equals(list.get(a).getScode())&&
						stationUnitList.get(i).getUcode().equals(list.get(a).getUcode())){
					stationUnitList.get(i).setCountunit(list.get(a).getCountunit());
					break;
				}
			
			}
		}*/
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String fileName = df.format(new Date()) + filename;
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			try {
				response.setHeader("Content-Disposition",
						"attachment;fileName=" + new String(fileName.getBytes("gb2312"), "iso8859-1") + ".xls");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			book = Workbook.createWorkbook(so);
			WritableSheet sheet = book.createSheet(filename, 0);
			Label label1 = new Label(0, 0, "枢纽名称");
			Label label2 = new Label(1, 0, "机组名称");
			Label label3 = new Label(2, 0, "开机时长(时)");
			Label label4 = new Label(3, 0, "总流量(万立方米)");
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			for (int i = 1; i < list.size() + 1; i++) {
				Label number1 = new Label(0, i, list.get(i - 1).get("sname").toString());
				Label number2 = new Label(1, i, list.get(i - 1).get("uname").toString());
				Label number3 = new Label(2, i, list.get(i - 1).get("kjtime").toString());
				Label number4 = new Label(3, i, list.get(i - 1).get("totalflow").toString());//总量流量
				sheet.addCell(number1);
				sheet.addCell(number2);
				sheet.addCell(number3);
				sheet.addCell(number4);
			}
			List<Map<String,Object>> l=list(list);
			Label number1 = new Label(0, list.size()+1, "");
			Label number2 = new Label(1, list.size()+1, "总计");//
			Label number3 = new Label(2, list.size()+1, l.get(0).get("kjtime").toString());//总时长
			Label number4 = new Label(3, list.size()+1, l.get(0).get("totalflow").toString());//总流量
			sheet.addCell(number1);
			sheet.addCell(number2);
			sheet.addCell(number3);
			sheet.addCell(number4);
			book.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				book.close();
				so.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/** 
	* @Title: startTime 
	* @Description: TODO(返回开机时长) 
	* @param @param count 机组数量
	* @param @param scode 枢纽code
	* @param @return    设定文件 
	* @return long    返回开机时长
	* @throws 
	*/
	public long startTime(long count,String scode){
			Long kjtime = (long) 0;
			switch (scode) {
			case "tzh"://"童子河闸站"  
				kjtime = count * 120;
				break;
			case "hthn"://"横塘河南枢纽"
				kjtime = count * 120;
				break;
			case "hfg"://"横峰沟枢纽" 
				kjtime = count * 120;
				break;
			case "mjt"://"糜家塘枢纽"
				kjtime = count * 120;
				break;
			case "yhh"://"永汇河枢纽" 
				kjtime = count * 120;
				break;
			case "lzg"://"老澡港河枢纽"
				kjtime = count * 120;
				break;
			case "zg"://"澡港河南枢纽" 
				kjtime = count * 300;
				break;
			case "dhh"://"丁横河枢纽" 
				kjtime = count * 120;
				break;
			case "73200006"://"横塘河北枢纽"
				kjtime = count * 1800;
				break;
			case "dyhd"://"大运河东枢纽" 
				kjtime = count * 300;
				break;
			case "nyh"://"南运河枢纽"
				kjtime = count * 3600;
				break;
			case "cxh"://"串新河枢纽"
				kjtime = count * 3600;
				break;
			case "clg"://"采菱港枢纽" 
				kjtime = count * 300;
				break;
			case "xjh"://"西界河闸站"
				kjtime = count * 120;
				break;
			case "bth"://"北塘河枢纽"
				kjtime = count * 120;
				break;
			case "dyhx"://"大运河西枢纽"
				kjtime = count * 120;
				break;
			case "bthcz"://"北塘河船闸" 
				kjtime = count * 120;
				break;
			default:
				kjtime = (long) 0;
				break;
			}
			return kjtime;
	 }
    
	/** 
	* @Title: list 
	* @Description: TODO(返回list页面footer的数据) 
	* @param @param report RealTimeUnitReport类型的list
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	*/
	public List<Map<String,Object>> list(List<Map<String, Object>> list){
		List<Map<String,Object>> lists= new ArrayList<>();
        Map<String,Object> maps=new HashMap<>();
        double counttime=0.0;
        double countflow=0.0;
		for(int i=0;i<list.size();i++){
			countflow=countflow+(double)list.get(i).get("totalflow");
			counttime=counttime+(double)list.get(i).get("kjtime");
		}
		maps.put("uname", "总计:");
		maps.put("kjtime", Math.round(counttime*100)/100.0);
		maps.put("totalflow", Math.round(countflow*100)/100.0);
		lists.add(maps);
		return lists;
	}
}
