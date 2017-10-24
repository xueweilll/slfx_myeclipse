package com.benqzl.controller.oa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.oa.Document;
import com.benqzl.pojo.oa.DocumentReceiver;
import com.benqzl.pojo.system.User;
import com.benqzl.service.oa.DocumentService;
import com.benqzl.unit.DecoderUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/** 
* @ClassName: DocumentSearchController 
* @Description: TODO(公文查看) 
* @author pxj 
* @date 2015年12月29日 上午9:51:17 
*  
*/
@Controller
@RequestMapping("/search")
public class DocumentSearchController extends BasicController {

	public DocumentSearchController() {
		super(DocumentController.class);
	}

	@Autowired
	private DocumentService documentService;

	/**
	 * @Title: index
	 * @Description: TODO(用户查页面)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/oa/documentsearch");
		return mv;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public String search(int page, int rows, String jsonStr,
			HttpServletRequest request) {
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
		if (document.getTitle() != null) {
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
						o.addProperty("fileaddress", src.getFileaddress());
						o.addProperty("title", src.getTitle());
						o.addProperty("createid", src.getCreaterid());
						o.addProperty("receiverid", src.getDocumentReceiver()
								.getReceiverid());
						o.addProperty("createname", src.getEmployee().getName());
						o.addProperty("phonemessage",
								src.getIsphonemess() == 0 ? "x" : "√");
						o.addProperty("pc", src.getIspc() == 0 ? "×" : "√");
						/*o.addProperty("username", src.getUser().getUsername());*/
						if (src.getDocumentReceiver().getReceiptdate() == null) {
							o.addProperty("receiptdate", "");
						} else {
							o.addProperty("receiptdate", datetimeFormat
									.format(src.getDocumentReceiver()
											.getReceiptdate()));
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

/*	@RequestMapping(value = "/watch", method = RequestMethod.POST)
	@ResponseBody
	public String watch(String id, String fileaddress, String userid,
			HttpServletRequest request, HttpServletResponse response) {
		// User user=(User) request.getSession().getAttribute("loginUser");
		try {
			documentService.updateState(id, userid);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'下载失敗！'}";
		}

		return "{'result':true,'msg':'下载成功！'}";
	}*/
	
	@RequestMapping(value = "documentWacth", method = RequestMethod.GET)
	public ModelAndView documentInfo(String id,String userid) {

		ModelAndView mv = new ModelAndView();
		if (!id.equals("0")) {
			documentService.updateState(id, userid);
			Document document = documentService.findUser(id);
			List<DocumentReceiver> list = document.getfindUser();
			String userIds = "";
			for (DocumentReceiver DocumentUser : list) {
				if (userIds == "") {
					userIds += "[";
				}
				userIds += "'" + DocumentUser.getEmployee().getName() + "',";
			}
			userIds = userIds.substring(0, userIds.length() - 1);
			userIds += "]";
			mv.addObject("textaddress", document.getFileaddress());
			mv.addObject("address", document.getFileaddress());
			mv.addObject("users", userIds);
			mv.addObject("document", document);
			mv.addObject("type","1");
		}else{
			mv.addObject("type","0");
		}
		mv.setViewName("oa/documentWacth");
		return mv;
	}
	/**
	 * @Title: downoad
	 * @Description: TODO(文件下载)
	 * @param @param request
	 * @param @param response
	 * @param @param fileaddress 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/download2", method = RequestMethod.GET)
	public void downoad(HttpServletRequest request,
			HttpServletResponse response, String fileaddress)
			throws IOException {
		fileaddress = DecoderUtil.decoder(fileaddress);
		// 得到要下载的文件名
		fileaddress = DecoderUtil.decoder(fileaddress);
		String fileName = fileaddress;
		// 获取上传文件的目录
		ServletContext sc = request.getSession().getServletContext();
		// 上传位置
		String fileSaveRootPath = sc.getRealPath("/upload/document");
		// 得到要下载的文件
		File file = new File(fileSaveRootPath + "\\" + fileName);

		// 如果文件不存在
		if (!file.exists()) {
			request.setAttribute("message", "您要下载的资源已被删除！！");
			System.out.println("您要下载的资源已被删除！！");

		}

		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");

		response.setHeader("Content-Disposition", "attachment;fileName="
				+ DecoderUtil.encoder(fileName));
		try {
			System.out.println(file.getAbsolutePath());
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
