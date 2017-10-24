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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.benqzl.pojo.dispatch.Receipt;
import com.benqzl.pojo.system.Actor;
import com.benqzl.pojo.system.User;
import com.benqzl.service.activiti.ActivitiUnitService;
import com.benqzl.service.dispatch.BigSurroundService;
import com.benqzl.service.dispatch.ReceiptService;
import com.benqzl.service.system.ActorService;
import com.benqzl.unit.DecoderUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Controller
@RequestMapping("bigsurroundreceipt")
public class BigSurroundReceiptController extends BasicController {

	public BigSurroundReceiptController() {
		super(BigSurroundReceiptController.class);
	}

	@Autowired
	private ReceiptService receiptService;

	@Autowired
	private ActivitiUnitService activitiUnitService;
	
	@Autowired
	private ActorService actorService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		String actorid=user.getActor().getId();
		Actor actor = actorService.selectByPrimaryKey(actorid);
		ModelAndView mv = new ModelAndView();
		mv.addObject("type", 1);
		mv.setViewName("dispatch/bigSurroundReceipt");
		if(actor!=null&&actor.getName().equals("超级管理员")){
			mv.addObject("actor", 0);
		}else{
			mv.addObject("actor", 1);
		}
		return mv;
	}

	@RequestMapping(value = "receiptInfo", method = RequestMethod.GET)
	public ModelAndView receiptInfo(String id, String type, String state) throws Exception {
		ModelAndView mv = new ModelAndView();
		Receipt receipt = new Receipt();
		if (!id.equals("0")) {
			receipt = receiptService.selectByPrimaryKey(id);
			mv.addObject("wayName", receipt.getWay() == 0 ? "电话" : "传真");
			mv.addObject("typeName", receipt.getDispatchtype() == 0 ? "片区调度" : "大包围调度");
			mv.addObject("launchTime", datetimeFormat.format(receipt.getLaunchtime()));
			mv.addObject("endTime", datetimeFormat.format(receipt.getEndtime()));
			mv.addObject("receiptetime", datetimeFormat.format(receipt.getReceiptetime()));
			if (receipt.getFileaddress() == null) {
				mv.addObject("DispatchAccessory", "");
			} else {
				String array[] = receipt.getFileaddress().split("_");
				mv.addObject("DispatchAccessory", array[1]);
			}
			mv.addObject("fileaddress", receipt.getFileaddress());
		} else {
			// String code =
			// activitiUnitService.selectMaxCode("CUFC");只有在提交调度单后才添加code
			receipt.setCode("");
			receipt.setDispatchtype(Long.parseLong(type));
		}
		mv.addObject("state", state);
		mv.addObject("receipt", receipt);
		mv.setViewName("dispatch/bigSurroundReceiptInfo");
		return mv;
	}

	@RequestMapping(value = "/pageBind", method = RequestMethod.POST)
	@ResponseBody
	public String PageBind(HttpServletRequest request, int page, int rows, String type, String isAduit,
			String receipter, String code, String name, String begintime, String endtime, String typeDate) {
		// User user = (User) request.getSession().getAttribute("loginUser");
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
		if (receipter == null || receipter.length() == 0) {
			map.put("receipter", null);
		} else {
			map.put("receipter", receipter);
		}
		if (code == null || code.length() == 0) {
			map.put("code", null);
		} else {
			map.put("code", code);
		}
		if (name == null || name.length() == 0) {
			map.put("name", null);
		} else {
			map.put("name", name);
		}
		if (begintime == null || begintime.length() == 0) {
			map.put("begintime", null);
		} else {
			try {
				map.put("begintime", datetimeFormat.parse(begintime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (endtime == null || endtime.length() == 0) {
			map.put("endtime", null);
		} else {
			try {
				map.put("endtime", datetimeFormat.parse(endtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<Receipt> list = receiptService.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = receiptService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Receipt.class, new JsonSerializer<Receipt>() {
			@Override
			public JsonElement serialize(Receipt src, Type typeOfSrc, JsonSerializationContext context) {
				JsonObject o = new JsonObject();
				o.addProperty("id", src.getId());
				o.addProperty("Code", src.getCode());
				o.addProperty("Launcher", src.getLauncher());
				o.addProperty("LaunchTime", datetimeFormat.format(src.getLaunchtime()));
				o.addProperty("EndTime", datetimeFormat.format(src.getEndtime()));
				o.addProperty("Receipter", src.getReceipteUser().getUsername());
				o.addProperty("ReceipteTime", datetimeFormat.format(src.getReceiptetime()));
				o.addProperty("Createtime", datetimeFormat.format(src.getCreatetime()));
				o.addProperty("state", src.getState());
				if (src.getState() == 0) {
					o.addProperty("stateName", "新建");
				} else if (src.getState() == 2) {
					o.addProperty("stateName", "已签发");
				} else if (src.getState() == 1) {
					o.addProperty("stateName", "已提交");
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
				return o;
			}
		}).create();
		json = gson.toJson(jsonMap);
		logger.info("this receipt list strJson is " + json);
		return json;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String jsonStr, HttpSession session) {
		String result = "";
		gson = new Gson();
		Receipt receipt = gson.fromJson(jsonStr, Receipt.class);
		User user = (User) session.getAttribute("loginUser");
		if (receipt.getId().equals("0")) {
			UUID uuid = UUID.randomUUID();
			receipt.setId(uuid.toString());
			if (user != null) {
				receipt.setCreater(user.getUserid());
			}
			receipt.setCreatetime(new Date());

			try {
				if (receipt.getState() == 1) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("objId", receipt.getId());
					if (receipt.getDispatchtype() == 0) {
						map.put("type", "B");
						activitiUnitService.statrtByKey("autonomouslyDispatch", map);
					} else {
						map.put("type", "C");
						activitiUnitService.statrtByKey("floodControlOperation", map);
					}
					String code = activitiUnitService.selectCode("CUFC-DO");
					receipt.setCode(code);
				}
				receiptService.insert(receipt);
				result = "{'result':true,'code':'" + receipt.getCode() + "'}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'添加失敗！'}";
			}
		} else {
			receipt.setCreatetime(new Date());
			receipt.setCreater(user.getUserid());
			try {
				if (receipt.getState() == 1) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("objId", receipt.getId());
					map.put("type", receipt.getDispatchtype().toString());
					if (receipt.getDispatchtype() == 0) {
						map.put("type", "B");
						activitiUnitService.statrtByKey("autonomouslyDispatch", map);
					} else {
						map.put("type", "C");
						activitiUnitService.statrtByKey("floodControlOperation", map);
					}
					String code = activitiUnitService.selectCode("CUFC-DO");
					receipt.setCode(code);
				}
				receiptService.updateByPrimaryKey(receipt);
				result = "{'result':true,'code':'" + receipt.getCode() + "'}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'编辑失敗！'}";
			}
		}
		return result;
	}

	/**
	 * @Title: saveAccessory @Description: TODO(上传附件) @param @param
	 * file @param @param upfilename @param @param request @param @return
	 * 设定文件 @return String 返回类型 @throws
	 */
	@RequestMapping(value = "saveAccessory", method = RequestMethod.POST)
	@ResponseBody
	public String saveAccessory(@RequestParam(value = "DispatchAccessory", required = false) MultipartFile file,
			String upfilename, HttpServletRequest request) {
		String result = "";
		if (!file.getOriginalFilename().equals("")) {
			Date date = new Date();
			String path = request.getSession().getServletContext().getRealPath("/upload/dispatchAccessory") + "\\";
			String filename = "a_" + UUID.randomUUID().toString() + "_" + dateFormat.format(date) + "_" + file.getOriginalFilename();
			if (!upfilename.equals("")) {// 删除之前的文件
				File files = new File(path + upfilename);
				files.delete();
			}
			try {// 上传文件
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, filename));
				result = filename.substring(2);
				;
			} catch (IOException e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'上传失敗！'}";
			}
		}
		return result;
	}

	/**
	 * @Title: downAccessory @Description: TODO(下载附件) @param @param
	 * filename @param @param response @param @param request 设定文件 @return void
	 * 返回类型 @throws
	 */
	@RequestMapping(value = "downAccessory", method = RequestMethod.GET)
	@ResponseBody
	public void downAccessory(String filename, HttpServletResponse response, HttpServletRequest request) {
		// 得到要下载的文件名

		String fileaddress = DecoderUtil.decoder(filename);
		System.out.println(fileaddress);
		if (fileaddress != "") {
			String array[] = fileaddress.split("_");
			String fileName = array[3];
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
				response.setHeader("Content-Disposition",
						"attachment;fileName=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));
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

	/**
	 * @Title: deleteAccessory @Description: TODO(删除上传文件) @param @param
	 * upfilename @param @param request @param @return 设定文件 @return String
	 * 返回类型 @throws
	 */
	@RequestMapping(value = "deleteAccessory", method = RequestMethod.POST)
	@ResponseBody
	public String deleteAccessory(String upfilename, HttpServletRequest request) {
		String result = "";
		String path = request.getSession().getServletContext().getRealPath("/upload/dispatchAccessory") + "\\";
		if (!upfilename.equals("")) {// 删除之前的文件
			File files = new File(path + upfilename);
			try {
				files.delete();
				result = "true";
			} catch (Exception e) {
				e.printStackTrace();
				result = "false";
			}

		}

		return result;
	}

	/**
	 * @Title: userList @Description: 用户列表(这里用一句话描述这个方法的作用) @param @return
	 * 设定文件 @return String 返回类型 @throws
	 */
	@RequestMapping(value = "userList", method = RequestMethod.POST)
	@ResponseBody
	public String userList() {
		List<User> list = receiptService.userList();
		gson = new GsonBuilder().registerTypeAdapter(User.class, new JsonSerializer<User>() {
			@Override
			public JsonElement serialize(User arg0, Type arg1, JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("id", arg0.getUserid());
				json.addProperty("name", arg0.getUsername());
				return json;
			}
		}).create();
		return gson.toJson(list);
	}

	/**
	 * @Title: delete @Description: 删除(这里用一句话描述这个方法的作用) @param @param
	 * id @param @return 设定文件 @return String 返回类型 @throws
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(String id) {
		String result = "";
		try {
			receiptService.deleteState(id);
			result = "{'result':true}";
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "{'result':false,'msg':'删除失敗！'}";
		}
		return result;
	}
	@Autowired
	private BigSurroundService bigSurroundService;
	@RequestMapping(value = "compulsorydelete", method = RequestMethod.POST)
	@ResponseBody
	public String compulsorydelete(String id) {
		String result = "";
		try {
			receiptService.deleteState(id);
			bigSurroundService.updateStateByReceiptId(id);
			result = "{'result':true}";
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "{'result':false,'msg':'删除失敗！'}";
		}
		return result;
	}

	/**
	 * @Title: commit @Description: 提交(这里用一句话描述这个方法的作用) @param @param
	 * id @param @return 设定文件 @return String 返回类型 @throws
	 */
	@RequestMapping(value = "commit", method = RequestMethod.POST)
	@ResponseBody
	public String commit(String id) {
		String result = "";
		try {
			receiptService.commitState(id);
			result = "{'result':true}";
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "{'result':false,'msg':'删除失敗！'}";
		}
		return result;
	}
}
