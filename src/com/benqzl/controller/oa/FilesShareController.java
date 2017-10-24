package com.benqzl.controller.oa;

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
import com.benqzl.pojo.oa.FileShare;
import com.benqzl.service.oa.FilesShareService;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
* @ClassName: FilesShareController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author shimh 
* @date 2015年12月23日 下午12:34:01  
*
 */
@Controller
@RequestMapping("/filesShare")
public class FilesShareController extends BasicController {
	@Autowired
	private FilesShareService service;

	public FilesShareController() {
		super(FilesShareController.class);
	}

	/**
	 * 
	* @Title: index  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @return    设定文件  
	* @return ModelAndView    返回类型  
	* @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/oa/filesShare");
		return mv;
	}
	
	
	/**
	 * 
	* @Title: filesShareList  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param page
	* @param @param rows
	* @param @param starttime
	* @param @param endtime
	* @param @param folerid
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws
	 */
	@RequestMapping(value = "/mysharelist", method = RequestMethod.POST)
	@ResponseBody
	public String myShareList(int page, int rows, String starttime,
			String endtime) {
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
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
		List<FileShare> fileShare = null;
		try {
			fileShare = service.findMyShare(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = 0;
		try {
			total = service.pageCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonMap.put("total", total);
		jsonMap.put("rows", fileShare);

		gson = new GsonBuilder().registerTypeAdapter(FileShare.class,
				new JsonSerializer<FileShare>() {

					@Override
					public JsonElement serialize(FileShare arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("filesid", arg0.getFilesid());
						json.addProperty("shareuserid", arg0.getShareuserid());
						json.addProperty("sharedate", datetimeFormat.format(arg0.getSharedate()));
						json.addProperty("name", arg0.getFiles().getName().substring(36));
						json.addProperty("username", arg0.getUser()
								.getUsername());
						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}
	
	/**
	 * 
	* @Title: shareToMeList  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param page
	* @param @param rows
	* @param @param starttime
	* @param @param endtime
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws
	 */
	@RequestMapping(value = "/sharetomelist", method = RequestMethod.POST)
	@ResponseBody
	public String shareToMeList(int page, int rows, String starttime,
			String endtime) {
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
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
		List<FileShare> fileShare = null;
		try {
			fileShare = service.findShareToMe(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = 0;
		try {
			total = service.pageCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonMap.put("total", total);
		jsonMap.put("rows", fileShare);

		gson = new GsonBuilder().registerTypeAdapter(FileShare.class,
				new JsonSerializer<FileShare>() {

					@Override
					public JsonElement serialize(FileShare arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("filesid", arg0.getFilesid());
						json.addProperty("sharedate", datetimeFormat.format(arg0.getSharedate()));
						json.addProperty("uploader", arg0.getFiles().getUploader());
						json.addProperty("name", arg0.getFiles().getName().substring(36));
						json.addProperty("username", arg0.getFiles().getUser().getUsername());
						json.addProperty("uploadroute", arg0.getFiles().getUploadroute());
						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}

}
