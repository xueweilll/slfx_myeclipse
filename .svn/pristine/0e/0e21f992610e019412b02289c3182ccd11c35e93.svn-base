package com.benqzl.controller.oa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import com.benqzl.core.LeeTree;
import com.benqzl.pojo.oa.FileShare;
import com.benqzl.pojo.oa.Files;
import com.benqzl.pojo.oa.Foler;
import com.benqzl.pojo.system.User;
import com.benqzl.service.oa.FilesService;
import com.benqzl.unit.DecoderUtil;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: FilesController
 * @Description: 上传文件(这里用一句话描述这个类的作用)
 * @author xw
 * @date 2015年12月15日 上午10:09:10
 * 
 */
@Controller
@RequestMapping("/files")
public class FilesController extends BasicController {
	@Autowired
	private FilesService service;

	public FilesController() {
		super(FilesController.class);
	}

	/**
	 * 
	 * @Title: index
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/oa/files");
		return mv;
	}

	/**
	 * 
	 * @Title: filesTree
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/filesTree", method = RequestMethod.POST)
	@ResponseBody
	public String filesTree(String id) {
		LeeTree<Foler> leeTree = null;
		try {
			leeTree = service.selectFoler();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// gson日期格式化
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh-mm-ss").create();
		;
		return "[" + gson.toJson(leeTree).replace("\"children\":[],", "") + "]";
	}

	/**
	 * 
	 * @Title: filesList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param page
	 * @param @param rows
	 * @param @param starttime
	 * @param @param endtime
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String filesList(int page, int rows, String starttime,
			String endtime, String folerid) {
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
		if (folerid == null || folerid.trim().equals("")
				|| folerid.toString().equals("0")) {
			map.put("folerid", null);
		} else {
			map.put("folerid", folerid);
		}
		List<Files> files = null;
		try {
			files = service.findByPage(map);
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
		jsonMap.put("rows", files);

		gson = new GsonBuilder().registerTypeAdapter(Files.class,
				new JsonSerializer<Files>() {

					@Override
					public JsonElement serialize(Files arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						String name = arg0.getName();
						name = name.substring(36);
						json.addProperty("id", arg0.getId());
						json.addProperty("name", name);
						json.addProperty("folerid", arg0.getFolerid());
						json.addProperty("folername", arg0.getFoler().getName());
						json.addProperty("uploadroute", arg0.getUploadroute());
						json.addProperty("saveroute", arg0.getSaveroute());
						json.addProperty("uploader", arg0.getUploader());
						json.addProperty("username", arg0.getUser()
								.getUsername());
						json.addProperty("uploadtime",
								datetimeFormat.format(arg0.getUploadtime()));
						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/deleteFiles", method = RequestMethod.POST)
	@ResponseBody
	public String deleteFiles(HttpServletRequest request,
			HttpServletResponse response, String id) {
		Files files = service.selectByFilesId(id);
		try {
			// 获取上传文件的目录
			ServletContext sc = request.getSession().getServletContext();
			String fileSaveRootPath = sc.getRealPath("/upload/filesUpload");
			String fileName = files.getName();
			// 得到要下载的文件
			File file = new File(fileSaveRootPath + "\\" + fileName);
			file.delete();
			service.deleteFilesShareByFilesId(id);
			service.deleteFiles(id);

		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'删除失败！'}";
		}
		return "{'result':true}";
	}

	/**
	 * 
	 * @Title: deleteFolder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/deleteFolder", method = RequestMethod.POST)
	@ResponseBody
	public String deleteFolder(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", 0);
		map.put("p2", 20);
		map.put("folerid", id);
		List<Files> files = null;
		files = service.findByPage(map);

		if (files.size() > 0) {
			return "{'result':false,'msg':'删除失败！'}";
		} else {
			try {
				service.deleteFolder(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// try {
		// service.deleteFolder(id);
		// } catch (Exception e) {
		// e.printStackTrace();
		// return "{'result':false,'msg':'删除失败！'}";
		// }
		return "{'result':true}";
	}

	/**
	 * 
	 * @Title: folderInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "folderInfo", method = RequestMethod.GET)
	public ModelAndView folderInfo(String id) {
		ModelAndView mv = new ModelAndView();
		if (!id.equals("0")) {
			Foler foler = service.selectByPrimaryKey(id);
			mv.addObject("foler", foler);
		}
		mv.setViewName("oa/folderInfo");
		return mv;
	}

	/**
	 * 
	 * @Title: filesInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "filesInfo", method = RequestMethod.GET)
	public ModelAndView filesInfo(String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("oa/filesInfo");
		return mv;
	}

	/**
	 * 
	 * @Title: filesToShare
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "filesToShare", method = RequestMethod.GET)
	public ModelAndView filesToShare(String id) {
		ModelAndView mv = new ModelAndView();
		if (!id.equals("0")) {
			Files files = service.selectByFilesId(id);
			mv.addObject("files", files);
		}
		mv.setViewName("oa/filesToShare");
		return mv;
	}

	/**
	 * 
	 * @Title: folderToShare
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "folderToShare", method = RequestMethod.GET)
	public ModelAndView folderToShare(String id) {
		ModelAndView mv = new ModelAndView();
		if (!id.equals("0")) {
			Foler foler = service.selectByPrimaryKey(id);
			mv.addObject("foler", foler);
		}
		mv.setViewName("oa/folderToShare");
		return mv;
	}

	/**
	 * 
	 * @Title: saveFolder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jsonStr
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saveFolder", method = RequestMethod.POST)
	@ResponseBody
	public String saveFolder(String jsonStr, HttpServletRequest request) {
		String[] strs = jsonStr.split("/");
		String id = strs[0];
		String name = strs[1];
		String parentid = strs[2];
		Foler foler = new Foler();
		User user = (User) request.getSession().getAttribute("loginUser");
		foler.setCreaterid(user.getUserid());

		foler.setName(name);
		foler.setParentid(parentid);
		if (id.equals("0")) {
			foler.setId(UUID.randomUUID().toString());
			try {
				service.insertSelective(foler);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			foler.setId(id);
			try {
				service.updateByPrimaryKeySelective(foler);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "{'result':true}";
	}

	/**
	 * 
	 * @Title: saveFiles
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jsonStr
	 * @param @param file
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public String saveFiles(
			@RequestParam("jsonStr") String jsonStr,
			@RequestParam(value = "filename", required = false) MultipartFile file[],
			HttpServletRequest request) throws IOException {
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date date = new Date();
		// String strDate = format.format(date);
		// strDate = strDate.replace("-", "");
		// strDate = strDate.replace(":", "");
		// strDate = strDate.replace(" ", "");

		String[] strs = jsonStr.split("/");
		String folerid = strs[1];

		Files files = new Files();
		ServletContext sc = request.getSession().getServletContext();
		// 上传位置
		String fileSaveRootPath = sc.getRealPath("/upload/filesUpload");

		for (MultipartFile files1 : file) {
			if (!files1.isEmpty()) {
				UUID uuid = UUID.randomUUID();
				// String name1 = files1.getOriginalFilename();
				// String name2 = name1.substring(0, name1.lastIndexOf("."));
				// String name3 = name1.substring(name1.lastIndexOf("."));
				// String name4 = name2 + strDate + name3;

				FileUtils.copyInputStreamToFile(files1.getInputStream(),
						new File(fileSaveRootPath + "//", uuid.toString()
								+ files1.getOriginalFilename()));
				// FileUtils.copyInputStreamToFile(files1.getInputStream(),
				// new File(fileSaveRootPath + "//", name4));
				files.setId(uuid.toString());
				User user = (User) request.getSession().getAttribute(
						"loginUser");
				// files.setUser(user);
				files.setUploader(user.getUserid());
				files.setName(files1.getOriginalFilename());
				// files.setName(name4);
				files.setName(uuid.toString() + files1.getOriginalFilename());
				files.setFolerid(folerid);

				// files.setUploadroute(fileSaveRootPath+"\\" +
				// uuid.toString()+files1.getOriginalFilename());
				files.setUploadroute(uuid.toString()
						+ files1.getOriginalFilename());
				// files.setUploadroute(name4);
				// files.setUploadroute(fileSaveRootPath+"\\" +
				// files1.getOriginalFilename());
				// files.setSaveroute(saveroute);
				files.setSaveroute("c:\\Users\\liupingtoday\\Desktop\\");

				files.setUploadtime(new Date());
				try {
					service.insertSelective(files);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return "{'result':true}";
	}

	/**
	 * 
	 * @Title: saveFilesShare
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jsonStr
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saveFilesShare", method = RequestMethod.POST)
	@ResponseBody
	public String saveFilesShare(String jsonStr) {
		String[] strs = jsonStr.split("/");
		String filesid = strs[0];
		String shareuserid[] = strs[1].split(",");

		for (String userid : shareuserid) {
			FileShare fileShare = new FileShare();
			fileShare.setId(UUID.randomUUID().toString());
			fileShare.setFilesid(filesid);
			fileShare.setShareuserid(userid);
			fileShare.setSharedate(new Date());

			try {
				service.insertSelective(fileShare);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "{'result':true}";
	}

	/**
	 * TODO
	 * 
	 * @Title: saveFolderShare
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jsonStr
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saveFolderShare", method = RequestMethod.POST)
	@ResponseBody
	public String saveFolderShare(String jsonStr) {
		String[] strs = jsonStr.split("/");
		String folderId = strs[0];
		String shareuserid[] = strs[1].split(",");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", 0);
		map.put("p2", 20);
		map.put("folerid", folderId);
		List<Files> files = null;
		files = service.findByPage(map);

		for (Files files1 : files) {
			for (String userid : shareuserid) {
				FileShare fileShare = new FileShare();
				fileShare.setId(UUID.randomUUID().toString());
				fileShare.setFilesid(files1.getId());
				fileShare.setShareuserid(userid);
				fileShare.setSharedate(new Date());

				try {
					service.insertSelective(fileShare);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "{'result':true}";
	}

	/**
	 * 
	 * @Title: downoad
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param request
	 * @param @param response
	 * @param @param uploadroute 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public void downoad(HttpServletRequest request,
			HttpServletResponse response, String uploadroute) {
		// 得到要下载的文件名
		// String storeName1[] = uploadroute.split("\\\\");
		// String fileName = storeName1[7];

		// try {
		// fileName = DecoderUtil.decoder(fileName);
		// 获取上传文件的目录
		// ServletContext sc = request.getSession().getServletContext();
		// 上传位置
		// String fileSaveRootPath = sc.getRealPath("/upload/filesUpload");

		// 得到要下载的文件
		// File file = new File(fileSaveRootPath + "\\" + fileName);

		// ////////////////////////////////////////////////////////
		// uploadroute = DecoderUtil.decoder(uploadroute);
		// 得到要下载的文件名
		uploadroute = DecoderUtil.decoder(uploadroute);
		String fileName = uploadroute;
		// 获取上传文件的目录
		ServletContext sc = request.getSession().getServletContext();
		// 上传位置
		String fileSaveRootPath = sc.getRealPath("/upload/filesUpload");
		// 得到要下载的文件
		File file = new File(fileSaveRootPath + "\\" + fileName);
		// File file = new File(fileName);

		// 如果文件不存在
		if (!file.exists()) {
			request.setAttribute("message", "您要下载的资源已被删除！！");
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.flush();
			out.println("<script>");
			out.println("alert('您要下载的资源已被删除！');");
			out.println("history.back();");
			out.println("</script>");
			return;
		}

		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ DecoderUtil.encoder(fileName));

		try {
			// System.out.println(file.getAbsolutePath());
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

		// // 读取要下载的文件，保存到文件输入流
		// FileInputStream in = new FileInputStream(fileSaveRootPath + "\\"
		// + fileName);
		// // 创建输出流
		// FileUtils.copyInputStreamToFile(in, new
		// File("c:\\temp\\download\\filesDownload",
		// fileName));
		// // 关闭文件输入流
		// in.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}
}
