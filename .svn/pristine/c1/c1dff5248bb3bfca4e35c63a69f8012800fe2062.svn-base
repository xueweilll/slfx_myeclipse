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
import java.util.Date;
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


@Controller
@RequestMapping("bigsurroundreceiptaduit")
public class BigSurroundReceiptAduitController  extends BasicController{

	public BigSurroundReceiptAduitController() {
		super(BigSurroundReceiptAduitController.class);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("type", 1);
		mv.setViewName("dispatch/bigSurroundReceiptAduit");
		return mv;
	}
	
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private ActivitiUnitService activitiUnitService;

	@RequestMapping(value = "/pageBind", method = RequestMethod.POST)
	@ResponseBody
	public String PageBind(HttpServletRequest request,int page, int rows, String type, String isAduit,
			String receipter, String code, String name, String begintime,
			String endtime, String typeDate) {
		User user = (User) request.getSession().getAttribute("loginUser");
		String json = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		map.put("type", Long.parseLong(type));		
		if (typeDate.equals("1")) {
			map.put("rpid", null);
		} else {
			try {
				if (activitiUnitService.findObjIds( "leadertask", user.getUserid(),"C").size() == 0) {
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					jsonMap.put("total", 0);
					List<String> l= new ArrayList<String>();
					jsonMap.put("rows", l);
					gson=new Gson();
					json = gson.toJson(jsonMap);
					return json;
				} else {
					map.put("rpid",	activitiUnitService.findObjIds( "leadertask", user.getUserid(),"C"));
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
		if(receipter == null || receipter.length() == 0){
			map.put("receipter", null);
		}else {
			map.put("receipter", receipter);
		}
		if(code == null || code.length() ==0){
			map.put("code", null);
		}else {
			map.put("code", code);
		}
		if(name == null || name.length()==0){
			map.put("name", null);
		}else {
			map.put("name", name);
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
		map.put("state", 1);
		List<Receipt> list = receiptService.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = receiptService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
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
						}else if(src.getState() == 3){
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
	
	@RequestMapping(value = "receiptAduitInfo", method = RequestMethod.GET)
	public ModelAndView receiptAduitInfo(String id) {
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
		if (receipt.getFileaddress()==null){
			mv.addObject("fileaddress","");
		}else{
			String []array=receipt.getFileaddress().split("_");
			mv.addObject("fileaddress",array[1]);
			
		}
		
		mv.addObject("receipt", receipt);
		mv.setViewName("dispatch/bigSurroundReceiptAduitInfo");
		return mv;
	}
	
	/**
	 * @Title: aduit
	 * @Description: 审批(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "aduit", method = RequestMethod.POST)
	@ResponseBody
	public String aduit(String id,String aduitmemo,HttpSession session) {
		String result = "";
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("aduitmemo", aduitmemo);
			User user=(User)session.getAttribute("loginUser");
			map.put("handler", user.getUserid());
			map.put("handletime", new Date());
			receiptService.aduitState(map);
			//activitiUnitService.messagePush("leadertask",user.getUserid(),id);
			activitiUnitService.completeTask("leadertask", user.getUserid(), id,null);
			result = "{'result':true}";
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "{'result':false,'msg':'审批失敗！'}";
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
