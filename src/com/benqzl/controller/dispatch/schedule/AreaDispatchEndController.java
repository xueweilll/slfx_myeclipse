package com.benqzl.controller.dispatch.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.dispatch.Receipt;
import com.benqzl.pojo.system.User;
import com.benqzl.service.activiti.ActivitiUnitService;
import com.benqzl.service.dispatch.ReceiptService;
import com.benqzl.unit.DecoderUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/** 
* @ClassName: AreaDispatchEndController 
* @Description: TODO(工程科结束调度(片区调度)) 
* @author pxj 
* @date 2016年7月6日 上午10:13:56 
*  
*/
@Controller
@RequestMapping("dispatchAreaEDEnd")
public class AreaDispatchEndController extends BasicController {
	public AreaDispatchEndController() {
		super(AreaDispatchEndController.class);
	}
	@Autowired
	ActivitiUnitService activitiUnitService;
	@Autowired
	private ReceiptService receiptService;
	/** 
	* @Title: index 
	* @Description: TODO(跳转工程科结束调度(片区调度)页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dispatch/dispatchAreaEDEnd");
		return mv;
	}
/** 
* @Title: dispatchAreaEndList 
* @Description: TODO(工程科结束调度(片区调度)list数据) 
* @param @param request
* @param @param page
* @param @param rows 
* @param @param receipter调度接收人
* @param @param begintime开始时间
* @param @param endtime结束时间
* @param @param typeDate 判断(value=0)我的任务/(value=1)全部任务
* @param @return    设定文件 
* @return String    返回类型 
* @throws 
*/
@RequestMapping(value="dispatchAreaEDEndList",method=RequestMethod.POST)
@ResponseBody
public String dispatchAreaEndList(HttpServletRequest request,int page, int rows,
		String receipter, String begintime,
		String endtime, String typeDate
		){
	String json = "";
	User user = (User) request.getSession().getAttribute("loginUser");
	page = (page == 0 ? 1 : page);
	rows = (rows == 0 ? 15 : rows);
	int start = (page - 1) * rows;
	rows = start + rows;
	logger.info("this page rows is " + page + "|" + rows);
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("p1", start);
	map.put("p2", rows);
	if(receipter == null || receipter.length() == 0){
		map.put("receipter", null);
	}else {
		map.put("receipter", receipter);
	}
	if(begintime == null || begintime.length() == 0){
		map.put("begintime",null);
	}else {
		try {
			map.put("begintime", datetimeFormat.parse(begintime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	if(endtime == null || endtime.length() == 0){
		map.put("endtime", null);
	}else {
		try {
			map.put("endtime", datetimeFormat.parse(endtime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	//typeDate=1为全部任务
	if (typeDate.equals("1")) {
		map.put("state", 3);//state=3为已完成
		map.put("receipts",null);
	} else {
		map.put("state", 2);
	try {
		List<String> receipts= new ArrayList<>();
		try {
			receipts = activitiUnitService.findObjIds("autoOrder",user.getUserid(),
					 "B");//获取receipt的id
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (receipts.size() == 0) {
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", 0);
			List<String> l= new ArrayList<String>();
			jsonMap.put("rows", l);
		    gson=new Gson();
			json = gson.toJson(jsonMap);
			return json;
		} else {
			map.put("receipts",receipts);
		}
	} catch (Exception e1) {
		e1.printStackTrace();
	}
	}
	Map<String, Object> jsonMap = new HashMap<String, Object>();
	List<Receipt> docket = receiptService.findEDEndList(map);
	int total = receiptService.findEDEndCount(map);
	jsonMap.put("total", total);
	jsonMap.put("rows", docket);
	gson = new GsonBuilder().registerTypeAdapter(Receipt.class,
			new JsonSerializer<Receipt>() {
				@Override
				public JsonElement serialize(Receipt src, Type typeOfSrc,
						JsonSerializationContext context) {
					JsonObject o = new JsonObject();
					o.addProperty("id", src.getId());
					o.addProperty("Code", src.getCode());
					o.addProperty("Launcher", src.getLauncher());
					o.addProperty("LaunchTime",
							datetimeFormat.format(src.getLaunchtime()));
					o.addProperty("EndTime",
							datetimeFormat.format(src.getEndtime()));
					o.addProperty("Receipter", src.getReceipteUser()
							.getUsername());
					o.addProperty("ReceipteTime",
							datetimeFormat.format(src.getReceiptetime()));
					o.addProperty("Createtime",
							datetimeFormat.format(src.getCreatetime()));
					o.addProperty("state", src.getState());
					if (src.getState() == 0) {
						o.addProperty("stateName", "新建");
					} else if (src.getState() == 2) {
						o.addProperty("stateName", "已签发");
					} else if (src.getState() == 1) {
						o.addProperty("stateName", "已提交");
					} else if(src.getState()==3){
						o.addProperty("stateName", "已完成");
					}
					if (src.getDispatchtype() == 0) {
						o.addProperty("DispatchType", "片区调度");
					} else if (src.getDispatchtype() == 1) {
						o.addProperty("DispatchType", "大包围调度");
					}
					if (src.getWay() == 0) {
						o.addProperty("Way", "电话");
					} else if (src.getWay() == 1) {
						o.addProperty("Way", "传真");
					}
					o.addProperty("Memo", src.getMemo());
					if(src.getHandlerUser() != null){
						o.addProperty("handler", src.getHandlerUser().getUsername());
					}
					if(src.getHandletime() != null){
						o.addProperty("handletime", datetimeFormat.format(src.getHandletime()));
					}
					return o;
				}
			}).create();
	json = gson.toJson(jsonMap);
	logger.info("this receipt list strJson is " + json);
	return json;
	
}
/** 
* @Title: dispatchAreaEDEndInfo 
* @Description: TODO(跳转到info) 
* @param @param id 主表id
* @param @param look (value=1)为查看，value=0为编辑
* @param @return    设定文件 
* @return ModelAndView    返回类型 
* @throws 
*/
@RequestMapping(value = "dispatchAreaEDEndInfo", method = RequestMethod.GET)
public ModelAndView dispatchAreaEDEndInfo(String id,String look) {
	ModelAndView mv = new ModelAndView();
	Receipt receipt = new Receipt();
	receipt = receiptService.selectByPrimaryKey(id);
	mv.addObject("wayName",receipt.getWay() == 0 ?"电话":"传真");
	mv.addObject("typeName",receipt.getDispatchtype() == 0 ?"片区调度":"大包围调度");
	mv.addObject("launchTime",
			datetimeFormat.format(receipt.getLaunchtime()));
	mv.addObject("endTime", datetimeFormat.format(receipt.getEndtime()));
	mv.addObject("receiptetime",
			datetimeFormat.format(receipt.getReceiptetime()));
	mv.addObject("receipt", receipt);
	if (receipt.getFileaddress()==null){
		mv.addObject("fileaddress","");
	}else{
		String []array=receipt.getFileaddress().split("_");
		mv.addObject("fileaddress",array[1]);
		
	}
	mv.addObject("look",look);
	mv.setViewName("dispatch/dispatchAreaEDEndInfo");
	return mv;
}
/**
 * @Title: end
 * @Description: 结束流程(这里用一句话描述这个方法的作用)
 * @param @param id receipt的id
 * @param @return 设定文件
 * @return String 返回类型
 * @throws
 */
@RequestMapping(value = "end", method = RequestMethod.POST)
@ResponseBody
public String end(String id,HttpSession session) {
	String result = "";
	try {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		User user=(User)session.getAttribute("loginUser");
		map.put("state", 3);
		//判断调度单是否分解
		if(activitiUnitService.subflowIsOver(id)){
			activitiUnitService.completeTask("autoOrder", user.getUserid(), id,null);
			receiptService.updateState(map);
			result = "{'result':true}";	
		}else{
			return "{'result':false,'msg':'分解单还未结束！'}";
		}
		
	} catch (Exception ex) {
		ex.printStackTrace();
		result = "{'result':false,'msg':'结束失敗！'}";
	}
	return result;
}
/** 
* @Title: downAccessory 
* @Description: TODO(下载附件) 
* @param @param filename
* @param @param response
* @param @param request    设定文件 
* @return void    返回类型 
* @throws 
*/
@RequestMapping(value="downAccessory",method = RequestMethod.GET)
@ResponseBody
public void downAccessory(String filename,HttpServletResponse response,
		HttpServletRequest request){
	// 得到要下载的文件名
			String fileaddress = DecoderUtil.decoder(filename);
			if(fileaddress!=""){
				String array[]=fileaddress.split("_");
				String fileName = array[1];
			// 获取上传文件的目录
			ServletContext sc = request.getSession().getServletContext();
			// 上传位置
			String fileSaveRootPath = sc.getRealPath("/upload/dispatchAccessory");
			// 得到要下载的文件
			File file = new File(fileSaveRootPath + "\\" + fileaddress);
			// 如果文件不存在
			if (!file.exists()) {
				request.setAttribute("message", "您要下载的资源已被删除！！");
				System.out.println("您要下载的资源已被删除！！");
				return;
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			try {
				response.setHeader("Content-Disposition", "attachment;fileName="
						+ new String(fileName.getBytes("gb2312"),"iso8859-1"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			try {
				InputStream inputStream = new FileInputStream(file);
				OutputStream os = response.getOutputStream();
				byte[] b = new byte[1024];
				int length;
				while ((length = inputStream.read(b)) > 0) {
					os.write(b, 0, length);
				}
				inputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
}
}
	
}
