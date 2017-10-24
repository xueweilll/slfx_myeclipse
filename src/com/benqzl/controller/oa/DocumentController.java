package com.benqzl.controller.oa;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.oa.Document;
import com.benqzl.pojo.oa.DocumentReceiver;
import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.User;
import com.benqzl.service.oa.DocumentService;
import com.benqzl.service.system.EmployeeService;
//import com.benqzl.service.system.UserService;
import com.benqzl.unit.DecoderUtil;
import com.benqzl.unit.SendMessageAutoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: DocumentController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author pxj
 * @date 2015年12月22日 下午12:37:13
 * 
 */
@Controller
@RequestMapping("/document")
public class DocumentController extends BasicController {

	public DocumentController() {
		super(DocumentController.class);
	}

	@Autowired
	private DocumentService documentService;
    @Autowired
    private EmployeeService employeeService;
    /*@Autowired
    private UserService userService;*/
	/**
	 * @Title: index
	 * @Description: TODO(公文管理维护)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/oa/document");
		return mv;
	}

	/**
	 * @Title: search
	 * @Description: TODO(查询文件)
	 * @param @param page
	 * @param @param rows
	 * @param @param jsonStr
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
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
		//String leve=user.getEmployee().getId();
		//final Long levels=documentService.selectLevel(leve);
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
						String type="";
						if(src.getLevels()==0){
								type="上级公文";
							}else if(src.getLevels()==2){
								type="同级公文";
							}else{
								type="下级公文";
							}
							o.addProperty("leve", type);
					o.addProperty("state", src.getDocumentReceiver()
								.getState());
						o.addProperty("createname", src.getEmployee().getName());
						o.addProperty("phonemessage",
								src.getIsphonemess() == 0 ? "x" : "√");
						o.addProperty("pc", src.getIspc() == 0 ? "×" : "√");
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

	/**
	 * @Title: documentInfo
	 * @Description: TODO(用户添加和编辑页面)
	 * @param @param id
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "documentInfo", method = RequestMethod.GET)
	public ModelAndView documentInfo(String id) {

		ModelAndView mv = new ModelAndView();
		if (!id.equals("0")) {
			Document document = documentService.findUser(id);
			List<DocumentReceiver> list = document.getfindUser();
			String userIds = "";
			for (DocumentReceiver DocumentUser : list) {
				if (userIds == "") {
					userIds += "[";	
				}
				userIds += "'" + DocumentUser.getUser().getUserid() + "',";
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
		mv.setViewName("oa/documentInfo");
		return mv;
	}

	/**
	 * @Title: watch
	 * @Description: TODO(公文查看)
	 * @param @param id
	 * @param @param fileaddress
	 * @param @param userid
	 * @param @param request
	 * @param @param response
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/watch", method = RequestMethod.POST)
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
	}

	/**
	 * @Title: receiverBind
	 * @Description: TODO(获取绑定用户)
	 * @param @param id
	 * @param @param fileaddress
	 * @param @param request
	 * @param @param response
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/receiverBind", method = RequestMethod.POST)
	@ResponseBody
	public String receiverBind(String id, String fileaddress,
			HttpServletRequest request, HttpServletResponse response) {
		List<User> username = documentService.selectUserbyID(id);
		gson = new GsonBuilder().registerTypeAdapter(User.class,
				new JsonSerializer<User>() {
					@Override
					public JsonElement serialize(User arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getUserid());
						json.addProperty("dname", arg0.getEmployee()
								.getDepartment().getName());
						json.addProperty("username", arg0.getEmployee().getName());
						return json;
					}
				}).create();
		return gson.toJson(username);
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
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void downoad(HttpServletRequest request,
			HttpServletResponse response, String fileaddress) {
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
			return;
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
	
	@RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
	@ResponseBody
	public void deleteFile(HttpServletRequest request,
			HttpServletResponse response, String fileaddress) throws IOException{
		
		// 得到要的文件名
		fileaddress = DecoderUtil.decoder(fileaddress);
		String fileName = fileaddress;
		String id=fileName.substring(0, 36);
		Document docunment = documentService.selectByPrimaryKey(id);
		String files[] = docunment.getFileaddress().split(",");
		String newFileName="";
		if(files.length == 1){
			newFileName="";
		}
		else {
			String f[] = new String[files.length];
			int j =0;
			for(int i = files.length-1;i>=0;i--){
				if(fileName.contains(files[i])){
					continue;
				}else{
					f[j]=files[i];
					j++;
				}
			}
			for(int i =0;i<f.length-1;i++){
				newFileName= newFileName + f[i]+",";
			}
			newFileName=newFileName.substring(0, newFileName.length()-1);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id);
		map.put("fileaddress", newFileName);
		
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
			return;
		}
		file.delete();
		documentService.updateFilesAddress(map);
	}

	/**
	 * @Title: save
	 * @Description: TODO(文件带参数上传保存)
	 * @param @param jsonStr
	 * @param @param file
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr,String file,HttpServletRequest request) throws IOException {
		String str[] = jsonStr.split("/");
		String id = str[0];
		String code = str[1];
		String title = str[2];
		String receiverid[] = str[3].split(",");
		String type = str[5];
		String leve=str[4];
		String fileaddress = str[6];
		String result = "";
		Document document1 = documentService.findUser(id);
		User user = (User) request.getSession().getAttribute("loginUser");
		gson = new Gson();
		Document document = new Document();
		ServletContext sc = request.getSession().getServletContext();
		// 上传位置
		String fileSaveRootPath = sc.getRealPath("/upload/document");
		if (id.equals("0")) {
			
				UUID uuid = UUID.randomUUID();
				document.setId(uuid.toString());
				document.setCreatetime(new Date());
				document.setEdittime(new Date());
				document.setIsdel(new Long(0));
				Long levels=Long.parseLong(leve);
				document.setLevels(levels);
				document.setFileaddress(fileaddress);
				if (type.equals("0")) {
					document.setIspc(new Long(0));
					document.setIsphonemess(new Long(1));
				} else if (type.equals("1")) {
					document.setIspc(new Long(1));
					document.setIsphonemess(new Long(0));
				} else {
					document.setIspc(new Long(1));
					document.setIsphonemess(new Long(1));
				}
				document.setCode(code);
				document.setTitle(title);
                  
				document.setCreaterid(user.getUserid());
				List<String> l = new ArrayList<String>();
				List<DocumentReceiver> receiver = new ArrayList<DocumentReceiver>();
				for (String userid : receiverid) {
					DocumentReceiver receivers = new DocumentReceiver();
					receivers.setId(UUID.randomUUID().toString());
					receivers.setReceiverid(userid);
					receivers.setState(new Long(0));
					receivers.setDocument(document);
					receiver.add(receivers);
					l.add(userid);
				}
				try {
					documentService.insert(document, receiver);
					result = "{'result':true}";
				} catch (Exception e) {
					e.printStackTrace();
					result = "{'result':false,'msg':'添加失敗！'}";
				}
			
				String files[]=file.split(",");
				for(int i=0;i<files.length;i++){
					
					File f = new File(files[i]);
					if(f.exists()){
						int num = files[i].lastIndexOf("\\");
						String fname = files[i].substring(num +1,  files[i].length());
						InputStream inputStream = new FileInputStream(f);
						FileUtils.copyInputStreamToFile(inputStream,
						new File(fileSaveRootPath + "//", document.getId() + "_"
								+ fname));
					}
				}
				
				SendMessageAutoUtil.sendMessageListAuto(l);
			}

		
		// 更新
		else {
			document.setId(id);
			document.setCode(code);
			document.setTitle(title);
			document.setEdittime(new Date());
		    document.setFileaddress(fileaddress);
			String files[]=file.split(",");
			for(int i=0;i<files.length;i++){
			File f = new File(files[i]);
			if(files[i].contains(":")){
				int num = files[i].lastIndexOf("\\");
				String fname = files[i].substring(num +1,  files[i].length());
				InputStream inputStream = new FileInputStream(f);
				FileUtils.copyInputStreamToFile(inputStream,
				new File(fileSaveRootPath + "//", document.getId() + "_"
						+ fname));
			}
			}
			
            
			List<DocumentReceiver> list = document1.getfindUser();

			String userids2[] = new String[list.size()];

			String listx[] = new String[list.size()];

			for (int i = 0; i < list.size(); i++) {
				userids2[i] = list.get(i).getUser().getUserid();
				listx[i] = list.get(i).getId();
			}
			boolean panduan = false;

			for (int i = 0; i < userids2.length; i++) {
				panduan = true;
				for (int j = 0; j < receiverid.length; j++) {
					if (receiverid[j].equals(userids2[i])) {
						panduan = false;
						break;
					}
				}
				if (panduan == true) {
					// 删除老的DetailsID
					documentService.deleteByDocumentID(listx[i]);
				}
			}

			// 遍历新的，如果发现老的没有新的，则新增新的，有的话就不新增
			for (int i = 0; i < receiverid.length; i++) {
				panduan = true;
				for (int j = 0; j < userids2.length; j++) {
					if (receiverid[i].equals(userids2[j])) {
						panduan = false;
						break;
					}
				}
				if (panduan == true) {
					// 新增新的todolistpeople(userids[i])
					DocumentReceiver dr = new DocumentReceiver();
					UUID uuid3 = UUID.randomUUID();
					dr.setId(uuid3.toString());
					dr.setDocumentid(id);
					dr.setReceiverid(receiverid[i]);
					dr.setState(new Long(0));
					documentService.insertReceiver(dr);
				}
			}
			documentService.updateByPrimaryKey(document);
		}
		return result;
	}

	/** 
	* @Title: level 
	* @Description: TODO(判断用户级别) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="level",method=RequestMethod.POST)
	@ResponseBody
	public String level(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("loginUser");
		Employee employee=employeeService.selectFindLeve(user.getUserid());
		if(employee.getLeve()==1){
		String json = "[{\"id\":\"0\",\"text\":\"上级公文\"},{\"id\":\"2\",\"text\":\"同级公文\"}]";
		return json;
		}
	
		else  if(employee.getLeve()==2){
			String json = "[{\"id\":\"0\",\"text\":\"上级公文\"},{\"id\":\"1\",\"text\":\"下级公文\"},{\"id\":\"2\",\"text\":\"同级公文\"}]";
			return json;
		}else {
			String json = "[{\"id\":\"1\",\"text\":\"下级公文\"},{\"id\":\"2\",\"text\":\"同级公文\"}]";
			return json;
		}
			
	}
	/**
	 * @Title: getUsername
	 * @Description: TODO(获取接收人姓名部门)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/getUsername", method = RequestMethod.POST)
	@ResponseBody
	public String getUsername(String leve) {
		int le=3;
		if(leve !=null){
		 le=Integer.parseInt(leve);		
		}
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("leve", le);
		List<User> username = documentService.selectUserbyParentID(map);
		gson = new GsonBuilder().registerTypeAdapter(User.class,
				new JsonSerializer<User>() {
					@Override
					public JsonElement serialize(User arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getUserid());
						json.addProperty("dname", arg0.getEmployee()
								.getDepartment().getName());
						json.addProperty("username", arg0.getUsername());
						json.addProperty("ename", arg0.getEmployee().getName());
						return json;
					}
				}).create();
		return gson.toJson(username);

	}

	/**
	 * @Title: delete
	 * @Description: TODO(删除文件)
	 * @param @param id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id,HttpServletRequest request) {
		// 获取上传文件的目录
				ServletContext sc = request.getSession().getServletContext();
		// 上传位置
	    String fileSaveRootPath = sc.getRealPath("/upload/document/");
		try {
			documentService.deleteByPrimaryKey(id);
			documentService.deleteState(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'添加失敗！'}";
		}
	   
		File file = new File(fileSaveRootPath);
		File tempList[]=file.listFiles();
		for(int i=0;i<tempList.length;i++){
			if(tempList[i].isFile()){
				System.out.println(tempList[i]);
				if(tempList[i].toString().contains(id)){
				    tempList[i].delete();	
				}
			
			}
		}
		return "{'result':true}";
	}

	/**
	 * @Title: yanzheng
	 * @Description: TODO(验证公文)
	 * @param @param code
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/yanzheng", method = RequestMethod.POST)
	@ResponseBody
	public String yanzheng(String code) {
		String result = "";
		int count = documentService.exsitCode(code);
		if (count > 0) {
			result = "{'result':false}";
			return result;
		} else {
			result = "{'result':true}";
			return result;
		}
	}

}
