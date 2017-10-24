package com.benqzl.controller.system.materials;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Assets;
import com.benqzl.pojo.system.User;
import com.benqzl.service.system.AssetsService;
import com.benqzl.unit.ActivitiUnitService;
import com.benqzl.unit.DecoderUtil;
import com.benqzl.unit.QRCodeUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: AssetsController
 * @Description: TODO(固定资产)
 * @author MJ005
 * @date 2016年7月19日 下午3:20:56
 * 
 */
@Controller
@RequestMapping("assets")
public class AssetsController extends BasicController {

	public AssetsController() {
		super(AssetsController.class);
	}

	@Autowired
	private AssetsService assetsService;
	@Autowired
	ActivitiUnitService activitiUnitService;

	/**
	 * @Title: index @Description: TODO(固定资产页面) @param @return 设定文件 @return
	 * ModelAndView 返回类型 @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/assets");
		return mv;
	}

	/**
	 * @Title: assetsList @Description: TODO(查询固定资产list) @param @param
	 * page @param @param rows @param @param jsonStr @param @return 设定文件 @return
	 * String 返回类型 @throws
	 */
	@RequestMapping(value = "/assetsList", method = RequestMethod.POST)
	@ResponseBody
	public String assetsList(int page, int rows, String jsonStr) {
		String strJson = "";
		gson = new Gson();
		Assets assets = new Assets();
		if (jsonStr != null) {
			assets = gson.fromJson(jsonStr, Assets.class);
		}
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
		map.put("creater", assets.getCreater());
		if (assets.getEdittime() == null) {
			map.put("endtime", null);
		} else {
			map.put("endtime", datetimeFormat.format(assets.getEdittime()));

		}
		if (assets.getCreatetime() == null) {
			map.put("createtime", null);
		} else {
			map.put("createtime", datetimeFormat.format(assets.getCreatetime()));
		}
		List<Assets> list = assetsService.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = assetsService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Assets.class, new JsonSerializer<Assets>() {

			@Override
			public JsonElement serialize(Assets src, Type typeOfSrc, JsonSerializationContext context) {
				JsonObject o = new JsonObject();
				o.addProperty("creater", src.getEmployee().getName());
				if (src.getCreatetime() == null) {
					o.addProperty("createtime", "");
				} else {
					o.addProperty("createtime", datetimeFormat.format(src.getCreatetime()));

				}
				if (src.getEdittime() == null) {
					o.addProperty("edittime", "");
				} else {
					o.addProperty("edittime", datetimeFormat.format(src.getEdittime()));
				}
				if (src.getStation() == null) {
					o.addProperty("sname", "");
				} else {
					o.addProperty("sname", src.getStation().getName());
				}
				o.addProperty("name", src.getName());
				o.addProperty("id", src.getId());
				o.addProperty("code", src.getCode());
				o.addProperty("principal", src.getPrincipal());
				String states = "";
				if (src.getState() == 0) {
					states = "未生成";
				} else if (src.getState() == 1) {
					states = "已生成";
				}
				o.addProperty("state", states);
				o.addProperty("memo", src.getMemo());
				o.addProperty("moblie", src.getMoblie());
				o.addProperty("materialType", src.getMaterialtype());
				o.addProperty("url", src.getUrl());
				return o;
			}
		}).create();
		strJson = gson.toJson(jsonMap);
		logger.info("this systemLogger list strJson is " + strJson);
		return strJson;
	}

	/**
	 * @Title: assetsInfo @Description: TODO(固定资产info页面) @param @param
	 * id @param @return 设定文件 @return ModelAndView 返回类型 @throws
	 */
	@RequestMapping(value = "/assetsInfo", method = RequestMethod.GET)
	public ModelAndView assetsInfo(String id) {
		ModelAndView mv = new ModelAndView();
		if (!id.equals("0")) {
			Assets assets = assetsService.selectByPrimaryKey(id);
			mv.addObject("assets", assets);
			mv.addObject("dcode", assets.getCode());
			mv.addObject("createtime", datetimeFormat.format(assets.getCreatetime()));
		} else {
			mv.addObject("dcode", activitiUnitService.selectMaxCode("PQ"));
		}
		mv.setViewName("system/assetsInfo");
		return mv;
	}

	/**
	 * @Title: saveMaterials @Description: TODO(保存) @param @param
	 * jsonStr @param @param request @param @return 设定文件 @return String
	 * 返回类型 @throws
	 */
	@RequestMapping(value = "/saveAssets", method = RequestMethod.POST)
	@ResponseBody
	public String saveAssets(String jsonStr, HttpServletRequest request) {
		String result = "";
		User user = (User) request.getSession().getAttribute("loginUser");
		gson = new Gson();
		Assets assets = gson.fromJson(jsonStr, Assets.class);
		if (assets.getId().equals("0")) {
			UUID uuid = UUID.randomUUID();
			assets.setId(uuid.toString());
			assets.setCreatetime(new Date());
			assets.setEdittime(new Date());
			assets.setCreater(user.getUserid());
			try {
				assetsService.insert(assets);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'添加失敗！'}";
			}

		}
		// 更新
		else {
			assets.setCreater(user.getUserid());
			assets.setEdittime(new Date());
			try {
				assetsService.updateByPrimaryKey(assets);
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'編輯失敗！'}";
			}
		}
		return result;
	}

	/**
	 * @Title: build @Description: TODO(二维码) @param @param jsonStr @param @param
	 * request @param @return 设定文件 @return String 返回类型 @throws
	 */
/*	@RequestMapping(value = "/build", method = RequestMethod.POST)
	@ResponseBody
	public String build(String jsonStr, String sname, HttpServletRequest request) {
		String result = "";
		User user = (User) request.getSession().getAttribute("loginUser");
		gson = new Gson();
		String docsPath = request.getSession().getServletContext().getRealPath("upload");
		Assets assets = gson.fromJson(jsonStr, Assets.class);
		String fileName = assets.getCode();
		assets.setUrl(fileName + ".jpg");
		String filePath = docsPath + "\\QRCode\\" + fileName;
		if (assets.getId().equals("0")) {
			UUID uuid = UUID.randomUUID();
			assets.setId(uuid.toString());
			assets.setCreatetime(new Date());
			assets.setEdittime(new Date());
			assets.setCreater(user.getUserid());
			try {
				assetsService.insert(assets);
				String text = "枢纽名称:" + sname + "\n" + "物品名称:" + assets.getName() + "\n" + "负责人:"
						+ assets.getPrincipal() + "\n" + "联系电话:" + assets.getMoblie() + "\n资产描述:" + assets.getMemo();
				QRCodeUtil.encode(text, null, filePath, false, assets.getCode());
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'添加失敗！'}";
			}

		}
		// 更新
		else {
			assets.setCreater(user.getUserid());
			assets.setEdittime(new Date());
			try {
				assetsService.updateByPrimaryKey(assets);
				String text = "枢纽名称:" + sname + "\n" + "物品名称:" + assets.getName() + "\n" + "负责人:"
						+ assets.getPrincipal() + "\n" + "联系电话:" + assets.getMoblie() + "\n资产描述:" + assets.getMemo();
				QRCodeUtil.encode(text, null, filePath, false, assets.getCode());
				result = "{'result':true}";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'編輯失敗！'}";
			}
		}
		return result;
	}*/

	/**
	 * @Title: down @Description: TODO(下载) @param @param id @param @param
	 * url @param @param request @param @param response 设定文件 @return void
	 * 返回类型 @throws
	 */
	@RequestMapping(value = "/down", method = RequestMethod.GET)
	@ResponseBody
	public void down(String id, HttpServletRequest request, HttpServletResponse response) {
		Assets assets = assetsService.selectDownByPrimaryKey(id);
		String fileName = assets.getId();//id为文件名
		String docsPath = request.getSession().getServletContext().getRealPath("upload");
		String filePath = docsPath + "\\QRCode\\" + fileName;
	/*	String principal="";
		if(assets.getPrincipal()!=null){
			principal=assets.getPrincipal();
		}
		String mobile="";
		if(assets.getMoblie()!=null){
			mobile=assets.getMoblie();
		}
		String memo="";
		if(assets.getMemo()!=null){
			memo=assets.getMemo();
		}*/
		/*String text = "枢纽名称:" + assets.getStation().getName() + "\n" + "物品名称:" + assets.getName() + "\n" + 
		"负责人:"+ principal+ "\n" + 
		"联系电话:" + mobile + 
		"\n资产描述:" + memo;*/
		ResourceBundle bundle=ResourceBundle.getBundle("jdbc");
		String url=bundle.getString("jdbc.qrcodeurl");
		String text=url+assets.getId();
		try {
			QRCodeUtil.encode(text, null, filePath, false, assets.getId());
			Assets assets1 = new Assets();
			assets1.setId(id);
			assets1.setState((long) 1);
			assets1.setUrl(fileName + ".jpg");
			assetsService.updateByPrimaryKey(assets1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 得到要下载的文件
		File file = new File(filePath + "\\" + fileName + ".jpg");
		// 如果文件不存在
		if (!file.exists()) {
			request.setAttribute("message", "您要下载的资源已被删除！！");
			// System.out.println("您要下载的资源已被删除！！");
			return;
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + DecoderUtil.encoder(fileName + ".jpg"));
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

	/**
	 * @Title: deleteAssets @Description: TODO(删除) @param @param
	 * id @param @return 设定文件 @return String 返回类型 @throws
	 */
	@RequestMapping(value = "/deleteAssets", method = RequestMethod.POST)
	@ResponseBody
	public String deleteAssets(String id) {
		String result = "";
		if (id != null && id != "") {
			try {
				assetsService.deleteByPrimaryKey(id);
				return "result:true";
			} catch (Exception e) {
				e.printStackTrace();
				result = "{'result':false,'msg':'删除失敗！'}";
			}
		}
		return result;
	}
}
