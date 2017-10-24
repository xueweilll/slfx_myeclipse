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
import com.benqzl.pojo.water.StPptnR;
import com.benqzl.service.water.RainRegimeService;
import com.benqzl.unit.DecoderUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/** 
* @ClassName: HistoryRainRegimeController 
* @Description: 
* @author xw 
* @date 2016年1月31日 上午10:46:58 
*  
*/
@Controller
@RequestMapping(value="/historyRainRegime")
public class HistoryRainRegimeController extends BasicController{

	public HistoryRainRegimeController() {
		super(HistoryRainRegimeController.class);
	}

	/** 
	* @Title: index 
	* @Description: index界面(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value="",method = RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("water/historyRainRegime");
		return mv;		
	}
	
	@Autowired
	RainRegimeService rainRegimeService;
	
	@RequestMapping(value="/stationList",method = RequestMethod.POST)
	@ResponseBody
	public String PageBind(int page, int rows,String sname,String starttime,String endtime)
	{
		String json = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		if(sname !=null && sname.length() > 0){
			map.put("stcd", sname);
		}
		try {
			if(starttime != null && starttime.length() > 0){
				map.put("starttime", dateFormat.parse(starttime));
			}
			if(endtime != null && endtime.length() > 0){
				map.put("endtime", dateFormat.parse(endtime));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<StPptnR> list = rainRegimeService.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = rainRegimeService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(StPptnR.class,
				new JsonSerializer<StPptnR>() {

					@Override
					public JsonElement serialize(StPptnR src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("stcd", src.getStcd());
						if(src.getStation() != null){
						o.addProperty("stcdname", src.getStation().getName());
						}
						if(src.getTm() != null){
						o.addProperty("tm",
								datetimeFormat.format(src.getTm()));
						}
						if(src.getDpr() != null){
						o.addProperty("dpr", src.getDpr().toString());
						}
						if(src.getIntv() != null){
						o.addProperty("intv", src.getIntv().toString());
						}
						if(src.getPdr() != null){
						o.addProperty("pdr", src.getPdr().toString());
						}
						if(src.getDyp() != null){
						o.addProperty("dyp", src.getDyp().toString());
						}
						if(src.getHisrain() != null){
						o.addProperty("hisrain", src.getHisrain().toString());
						}
						if(src.getWth() =="5"){
							o.addProperty("wth", "雪");
						}else if(src.getWth() == "6"){
							o.addProperty("wth", "雨夹雪");
						}else if(src.getWth() == "7"){
							o.addProperty("wth", "雨");
						}else if(src.getWth() == "8"){
							o.addProperty("wth", "阴");
						}else if(src.getWth() == "9"){
							o.addProperty("wth", "晴");
						}
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		logger.info("this station list strJson is " + json);
		return json;
	}
	/**
	 * @Title: waterJSON
	 * @Description: TODO(图形数据)
	 * @param @param time
	 * @param @param year
	 * @param @param month
	 * @param @param day
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "rainJSON", method = RequestMethod.GET)
	@ResponseBody
	public String rainJSON(String time, String month,String station) throws ParseException {
		List<Map<String, Object>> map;
		Map<String, Object> map1 = new HashMap<String, Object>();
		String sname=DecoderUtil.decoder(station);
		//String years[]=dateFormat.format(new Date()).split("-");
		if ( month == null 
				||(month.equals(""))) {
			map1.put("month", null);
			//Station stations=stationservice.findStationName();
			map1.put("stcd", sname);
			//map1.put("month", years[0]+years[1]);
		}
		else {
			map1.put("month", month == null || month.equals("") ? null : month);
            map1.put("stcd", sname);
		}

		try {
			//查询图表
			map = rainRegimeService.findRainWater(map1);
			gson = new Gson();
			return gson.toJson(map);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'数据读取失败！'}";
		}
	}
	@RequestMapping(value="stationpicture",method = RequestMethod.POST)
	@ResponseBody
	public String stationpicture(){
		String json = "[{\"code\":\"lzg\",\"name\":\"老澡港河枢纽\"},"
				+ "{\"code\":\"hthn\",\"name\":\"横塘河南枢纽\"},{\"code\":\"tzh\",\"name\":\"童子河闸站\"},"
				+ "{\"code\":\"73200006\",\"name\":\"横塘河北枢纽\"}]";
		return json;
	}
	@RequestMapping(value="stationCombobox",method = RequestMethod.POST)
	@ResponseBody
	public String stationCombobox(){
		String json = "[{\"code\":\"\",\"name\":\"全部\"},{\"code\":\"lzg\",\"name\":\"老澡港河枢纽\"},"
				+ "{\"code\":\"hthn\",\"name\":\"横塘河南枢纽\"},{\"code\":\"tzh\",\"name\":\"童子河闸站\"},"
				+ "{\"code\":\"73200006\",\"name\":\"横塘河北枢纽\"}]";
		return json;
	}
}
