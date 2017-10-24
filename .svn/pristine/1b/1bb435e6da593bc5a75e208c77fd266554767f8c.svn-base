package com.benqzl.controller.system.pivot;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
//import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Camera;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;
import com.benqzl.pojo.water.StPumpr;
import com.benqzl.service.system.StationService;
import com.benqzl.socket.MessageQueue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/** 
* @ClassName: PumpStationController 
* @Description: TODO(泵站维护) 
* @author lyf 
* @date 2015年11月23日 下午2:24:00 
*  
*/
@Controller
@RequestMapping(value="/station")
public class PumpStationController extends BasicController {

	public PumpStationController() {
		super(PumpStationController.class);
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
		mv.setViewName("system/station");
		return mv;		
	}
	
	@Autowired
	private StationService stationService;
	
	@RequestMapping(value="/stationList",method = RequestMethod.POST)
	@ResponseBody
	public String PageBind(int page, int rows,String conditions)
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
		if(conditions != null && conditions.length()>0){
			gson = new Gson();
			Station station = gson.fromJson(conditions, Station.class);
			if(station.getCode() != null && station.getCode().length()>0)
			{
				map.put("code", station.getCode());
			}
			if(station.getName() != null && station.getName().length()>0)
			{
				map.put("name", station.getName());
			}
		}
		List<Station> list = stationService.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = stationService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Station.class,
				new JsonSerializer<Station>() {

					@Override
					public JsonElement serialize(Station src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("Code", src.getCode());
						o.addProperty("Name", src.getName());
						o.addProperty("Lat", src.getLat().toString());
						o.addProperty("Lon", src.getLon().toString());
						o.addProperty("Levels", src.getLevels().toString());
						o.addProperty("Createtime",
								datetimeFormat.format(src.getCreatetime()));
						o.addProperty("Edittime",
								datetimeFormat.format(src.getEdittime()));
						o.addProperty("Remark", src.getRemark());
						o.addProperty("Dname", src.getDepartment().getName());
						o.addProperty("controlwaterlevel", src.getControlwaterlevel());
						o.addProperty("departmentid", src.getDepartmentid());
						o.addProperty("address", src.getAddress());
						return o;
					}
				}).create();
		json = gson.toJson(jsonMap);
		logger.info("this station list strJson is " + json);
		return json;
	}
	
	@RequestMapping(value="stationInfo",method = RequestMethod.GET)
	public ModelAndView stationInfo(String id)
	{
		ModelAndView mv = new ModelAndView();
		if(!id.equals("0")){
			Station station = stationService.selectByPrimaryKey(id);
			mv.addObject("station", station);
		}
		mv.setViewName("system/stationInfo");
		return mv;
	}
	
	@Resource
	MessageQueue messageQueue;
	
	@RequestMapping(value="save",method = RequestMethod.POST)
	@ResponseBody
//	public String stationSave (String jsonStr,HttpServletRequest request) throws IOException
//	{
		public String stationSave(@RequestParam("jsonStr") String jsonStr,
				@RequestParam(value="imgurl",required=false) MultipartFile file,HttpServletRequest request)
				throws IOException{
		String result="";
		gson = new Gson();
		System.out.println(jsonStr);
		Station station = gson.fromJson(jsonStr, Station.class);
		System.out.println(file.getOriginalFilename());
		/*String [] jstr=jsonStr.split("/");
		String id=jstr[0];
		String code=jstr[1];
		String name=jstr[2];
		String lat=jstr[3];
		String lon=jstr[4];
		String levels=jstr[5];
		//String remark=jstr[6];
		String nvrusername=jstr[7];
		String nvrpassword=jstr[8];
		String nvrurl=jstr[9];
		String nvrprot=jstr[10];
		String departmentid=jstr[11];
		String controlwaterlevel=jstr[12];
		
		String address = jstr[13].equals("0")?"":jstr[13];
		String serviceport=jstr[14];
		Station station=new Station();
		station.setId(id);
		station.setCode(code);
		station.setName(name);
		station.setLat(new BigDecimal(lat));
		station.setLon(new BigDecimal(lon));
		station.setLevels(new Long(levels));
		station.setRemark(remark);
		station.setNvrusername(nvrusername);
		station.setNvrpassword(nvrpassword);
		station.setNvrurl(nvrurl);
		station.setNvrprot(nvrprot);
		station.setDepartmentid(departmentid);
		station.setControlwaterlevel(new BigDecimal(controlwaterlevel));
		station.setAddress(address);
		station.setServiceport(serviceport);*/
		//上传文件
//		if(!station.getImgurl().equals("")){
//			String path = request.getSession().getServletContext().getRealPath("/upload")+"\\";
//			InputStream inputstream=new FileInputStream(station.getImgurl());
//			String filename=station.getImgurl().substring(station.getImgurl().lastIndexOf("\\")+1, station.getImgurl().length());
//			FileUtils.copyInputStreamToFile(inputstream, new File(path, filename));
//			station.setImgurl(path);
//		}
		if(!file.getOriginalFilename().equals("")){
			String path = request.getSession().getServletContext().getRealPath("/upload/stationimgs")+"\\";
			String str =station.getId()+".jpg";
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path,str));
			station.setImgurl(str);
		}
		if (station.getId().equals("0"))//判断id=0；
		{
			UUID uuid = UUID.randomUUID();
			station.setId(uuid.toString());
			station.setCreatetime(new Date());
			station.setEdittime(new Date());
			station.setIsdel(new Long(0));
			try {
				messageQueue.stationMap.put(station.getId(), station);
			 	stationService.insert(station);
				result= "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result= "{'result':false,'msg':'添加失敗！'}";
			}
		}
		else
		{
			station.setEdittime(new Date());
			try
			{
				messageQueue.stationMap.put(station.getId(), station);
				stationService.updateByPrimaryKey(station);
				result= "{'result':true}";
			}
			catch(Exception e)
			{
				e.printStackTrace();
				result= "{'result':false,'msg':'添加失敗！'}";
			}
		}
		return result;
	}
	
	@RequestMapping(value="delete",method = RequestMethod.POST)
	@ResponseBody
	public String stationDelete(String id)
	{
		String result="";
		if (id != null && id !="")
		{
			try
			{
				messageQueue.stationMap.remove(id);
				stationService.deleteIsdel(id);
				result= "{'result':true}";
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				result= "{'result':false,'msg':'删除失敗！'}";
			}
		}
		return result;
	}
	
	
	@RequestMapping(value="stationByFk",method=RequestMethod.POST)
	@ResponseBody
	public String stationByFk()
	{
		List<Station> list = stationService.stationByFk();
		gson = new GsonBuilder().registerTypeAdapter(Station.class, new JsonSerializer<Station>() {

			@Override
			public JsonElement serialize(Station arg0, Type arg1,
					JsonSerializationContext arg2) {
				// TODO Auto-generated method stub
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("name", arg0.getName());
				return json;
			}
		}).create();
		return gson.toJson(list);
	}
	/** 
	* @Title: patrolnormalstation 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="patrolnormalstation",method=RequestMethod.POST)
	@ResponseBody
	public String patrolnormalstation(HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("loginUser");
		List<Station> list = stationService.patrolnormalstation(user.getUserid());
		gson = new GsonBuilder().registerTypeAdapter(Station.class, new JsonSerializer<Station>() {

			@Override
			public JsonElement serialize(Station arg0, Type arg1,
					JsonSerializationContext arg2) {
				// TODO Auto-generated method stub
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("name", arg0.getName());
				return json;
			}
		}).create();
		return gson.toJson(list);
	}
	
	/** 
	* @Title: exsitCode 
	* @Description: code是否存在(这里用一句话描述这个方法的作用) 
	* @param @param code
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	@RequestMapping(value="exsitCode",method = RequestMethod.POST)
	@ResponseBody
	public String exsitCode(String code)
	{
		String result="";
		int count = stationService.exsitCode(code);
		if(count >0)
		{
			result= "{'result':false}";
			System.out.println(result);
			return result;
		}
		else
		{
			result= "{'result':true}";
			System.out.println(result);
			return result;
		}
	}
	
	@RequestMapping(value="exsitName",method = RequestMethod.POST)
	@ResponseBody
	public String exsitName(String code)
	{
		String result="";
		int count = stationService.exsitName(code);
		if(count >0)
		{
			result= "{'result':false}";
			System.out.println(result);
			return result;
		}
		else
		{
			result= "{'result':true}";
			System.out.println(result);
			return result;
		}
	}
	
	
	@RequestMapping(value="stationCombobox",method = RequestMethod.POST)
	@ResponseBody
	public String stationCombobox(String departmentid){
		HashMap<String, Object> map = new HashMap<>();
		if(departmentid != null && departmentid.length() > 0){
			map.put("departmentid", departmentid);
		}
		List<Station> list = stationService.selectBYCode(map);
		gson = new GsonBuilder().registerTypeAdapter(Station.class, new JsonSerializer<Station>() {

			@Override
			public JsonElement serialize(Station arg0, Type arg1,
					JsonSerializationContext arg2) {
				// TODO Auto-generated method stub
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());
				json.addProperty("name", arg0.getName());
				if(arg0.getControlwaterlevel() != null){
					json.addProperty("controlwaterlevel", arg0.getControlwaterlevel());
				}else {
					json.addProperty("controlwaterlevel", "0");
				}
				if(messageQueue.concurrentPumprs != null && messageQueue.concurrentPumprs.get(arg0.getCode()) != null){
					List<StPumpr> sts = (List<StPumpr>) messageQueue.concurrentPumprs.get(arg0.getCode());
					String truewaterlevel = "";
					for(StPumpr st : sts){
						truewaterlevel = st.getPpdwz().toString();
					}
					json.addProperty("truewaterlevel", truewaterlevel);
				}else {
					json.addProperty("truewaterlevel", "0");
				}
				return json;
			}
		}).create();
		return gson.toJson(list);
	}
	
	@RequestMapping(value="stationByAppCamera",method = RequestMethod.POST)
	@ResponseBody
	public String stationByAppCamera(){
		List<Station> list = stationService.selectByAppCamera();
		gson = new GsonBuilder().registerTypeAdapter(Station.class, new JsonSerializer<Station>() {

			@Override
			public JsonElement serialize(Station arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getId());//枢纽编号
				json.addProperty("name", arg0.getName());//枢纽名称
				json.addProperty("username", arg0.getNvrusername());
				json.addProperty("password", arg0.getNvrpassword());
				json.addProperty("url", arg0.getNvrurl());
				json.addProperty("port", arg0.getNvrprot());
				Gson g = new GsonBuilder().registerTypeAdapter(Camera.class, new JsonSerializer<Camera>() {
					@Override
					public JsonElement serialize(Camera src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());//摄像头id
						o.addProperty("name", src.getName());//摄像头名称
						o.addProperty("channel", src.getChannel());	//摄像头通道
						return o;
					}
				}).create();
				json.addProperty("cameras", g.toJson(arg0.getCameras()));			
				return json;
			}
		}).create();
		return gson.toJson(list);
	}
}
