package com.benqzl.controller.oa;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.oa.Document;
import com.benqzl.pojo.system.User;
import com.benqzl.service.oa.DocumentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/** 
* @ClassName: DocumentAcceptController 
* @Description: TODO(公文接收) 
* @author pxj 
* @date 2015年12月17日 下午4:57:45 
*  
*/
@Controller
@RequestMapping("/accept")
public class DocumentAcceptController extends BasicController {

	public DocumentAcceptController() {
		super(DocumentController.class);
	}

	@Autowired
	private DocumentService documentService;

	/**
	 * @Title: index
	 * @Description: TODO(用户增删改查页面)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/oa/documentaccept");
		return mv;
	}

	/** 
	* @Title: search 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param page
	* @param @param rows
	* @param @param jsonStr
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public String search(int page,int rows,String jsonStr,HttpServletRequest request){
		String strJson = "";
		Map<String, Object> map = new HashMap<String, Object>();
		gson = new Gson();
		Document document = new Document();
	if (jsonStr != null) {
			document = gson.fromJson(jsonStr, Document.class);
			if (document.getCode() != null) {
				map.put("code", document.getCode().trim());
			} else {
				map.put("code", null);
			}
		}
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		if (document.getTitle()!= null) {
			map.put("title", document.getTitle().trim());
		} else {
			map.put("title", null);
		}
		User user = (User) request.getSession().getAttribute("loginUser");
		map.put("createid", user.getUserid());
		List<Document> list = documentService.findByPages(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = documentService.pageCounts(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Document.class,
				new JsonSerializer<Document>() {

					@Override
					public JsonElement serialize(Document src, Type typeOfSrc,
							JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("code", src.getCode());
						o.addProperty("fileaddress",src.getFileaddress());
						o.addProperty("title", src.getTitle());
						o.addProperty("createid", src.getCreaterid());
						o.addProperty("state", src.getDocumentReceiver().getState()==0?"未接收":"已接收");
						o.addProperty("createname", src.getEmployee().getName());
						o.addProperty("phonemessage", src.getIsphonemess() == 0 ? "x" : "√");
						o.addProperty("pc", src.getIspc()==0 ? "×":"√");
					/*	o.addProperty("username", src.getUser().getUsername());*/
						if(src.getDocumentReceiver().getReceiptdate()==null){
							o.addProperty("receiptdate", "");
						}
						else{
							o.addProperty("receiptdate",datetimeFormat.format(src.getDocumentReceiver().getReceiptdate()));
						}
						o.addProperty("createtime",
								datetimeFormat.format(src.getCreatetime()));
						o.addProperty("edittime",
								datetimeFormat.format(src.getEdittime()));
						return o;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		logger.info("this document list strJson is " + strJson);
		return strJson;
	}


}
