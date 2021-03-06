package com.benqzl.controller.patrol;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.benqzl.pojo.patrol.Maintenance;
import com.benqzl.pojo.patrol.MaintenanceAduit;
import com.benqzl.pojo.patrol.MaintenanceDetails;
import com.benqzl.pojo.system.User;
import com.benqzl.service.patrol.MainTenanceAduitService;
import com.benqzl.unit.ActivitiUnitService;
import com.benqzl.unit.DecoderUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: MainTenanceShController
 * @Description: TODO(审核)
 * @author pxj
 * @date 2016年2月26日 下午2:49:07
 * 
 */
@Controller
@RequestMapping("tenanceShList")
public class MainTenanceShController extends BasicController {
	@Autowired
	private MainTenanceAduitService mainTenanceService;
	@Autowired
	private ActivitiUnitService activitiUnitService;

	public MainTenanceShController() {
		super(MainTenanceShController.class);
	}

	/**
	 * @Title: index
	 * @Description: TODO(审核页面)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/patrol/tenanceShList");
		return mv;
	}

	/**
	 * @Title: tenanceShList
	 * @Description: TODO(审核list)
	 * @param @param request
	 * @param @param page
	 * @param @param rows
	 * @param @param code
	 * @param @param bh
	 * @param @param type
	 * @param @param starttime
	 * @param @param endtime
	 * @param @param typeDate
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/tenanceShList", method = RequestMethod.POST)
	@ResponseBody
	public String tenanceShList(HttpServletRequest request, int page, int rows,
			String code, String bh, String type, String starttime,
			String endtime, String typeDate) {
		User user = (User) request.getSession().getAttribute("loginUser");
		List<String> mid = null;
		try {
			mid = activitiUnitService.findMaintainId(user.getUserid(), "audit");
			if (mid.size() == 0) {
				Map<String, Object> map = new HashMap<>();
				map.put("total", 0);
				map.put("rows", mid);
				gson = new Gson();
				return gson.toJson(map);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Maintenance> list = new ArrayList<>();
		String strJson = "";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		map.put("type", "1");
		map.put("typeDate", typeDate);
		if (typeDate.equals("0")) {
			map.put("state", "3");
			map.put("lc", "1");
		} else {
			map.put("aduittype", "1");
			map.put("state", null);
		}
		map.put("id", mid);
		map.put("total", null);
		if (starttime == null || starttime.trim().equals("")) {
			map.put("begintime", null);
		} else {
			try {
				map.put("begintime", datetimeFormat.parse(starttime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (bh == null || bh.equals("")) {
			map.put("code", null);
		} else {
			map.put("code", bh);
		}
		if (type == null || type.equals("")) {
			map.put("way", null);
		} else {
			map.put("way", type);
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
		int count = 0;
		try {
			list = mainTenanceService.findByPage(map);
			count = mainTenanceService.pageCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", count);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Maintenance.class,
				new JsonSerializer<Maintenance>() {
					@Override
					public JsonElement serialize(Maintenance arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("projectName", arg0.getProjectname()); // 工程名称
						json.addProperty("code", arg0.getCode());// 编号
						json.addProperty("department", arg0.getDepartment()
								.getName());// 部门
						json.addProperty("createtime",
								datetimeFormat.format(arg0.getCreatetime()));// 创建时间
						json.addProperty("applydate",
								datetimeFormat.format(arg0.getApplydate()));// 申请时间
						json.addProperty("constructionunits",
								arg0.getConstructionunits());// 施工单位
						json.addProperty("applyer", arg0.getApply()
								.getUsername());// 申请人
						String state = "";
						switch (arg0.getState().toString()) {
						case "3":
							state = "已复核";
							break;
						case "4":
							state = "已审核";
							break;
						case "5":
							state = "已审批";
							break;
						}

						json.addProperty("state", state);

						// 状态
						if (arg0.getMemo() == null) {
							json.addProperty("memo", "");
						} else {
							json.addProperty("memo", arg0.getMemo());// 备注
						}
						if (arg0.getStepmemo() == null) {
							json.addProperty("stepmemo", "");
						} else {
							json.addProperty("stepmemo", arg0.getStepmemo());
						}
						return json;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		return strJson;
	}

	/**
	 * @Title: tenanceShInfo
	 * @Description: TODO(审核info)
	 * @param @param id
	 * @param @param projectName
	 * @param @param code
	 * @param @param department
	 * @param @param createtime
	 * @param @param applydate
	 * @param @param constructionunits
	 * @param @param applyer
	 * @param @param checker
	 * @param @param state
	 * @param @param memo
	 * @param @param stepmemo
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/tenanceShInfo", method = RequestMethod.GET)
	public ModelAndView tenanceShInfo(String id, String projectName,
			String code, String department, String createtime,
			String applydate, String constructionunits, String applyer,
			String checker, String state, String memo, String stepmemo) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("type", "1");
		map.put("state", "1");
		MaintenanceAduit aduitTenance = mainTenanceService.findMemo(map);
		mv.addObject("id", id);
		mv.addObject("projectName", DecoderUtil.decoder(projectName));
		mv.addObject("code", DecoderUtil.decoder(code));
		mv.addObject("department", DecoderUtil.decoder(department));
		mv.addObject("createtime", DecoderUtil.decoder(createtime));
		mv.addObject("applydate", DecoderUtil.decoder(applydate));
		mv.addObject("constructionunits",
				DecoderUtil.decoder(constructionunits));
		mv.addObject("applyer", DecoderUtil.decoder(applyer));
		mv.addObject("checker", DecoderUtil.decoder(checker));
		mv.addObject("memo", DecoderUtil.decoder(memo));
		if (aduitTenance == null || aduitTenance.getMemo() == null) {
			mv.addObject("stepmemo", "");
		} else {
			mv.addObject("stepmemo", aduitTenance.getMemo());
		}
		mv.setViewName("/patrol/tenanceShInfo");
		return mv;
	}

	/**
	 * @Title: tenanceShInfoList
	 * @Description: TODO(审核项目list)
	 * @param @param id
	 * @param @param fileaddress
	 * @param @param request
	 * @param @param response
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/tenanceShInfoList", method = RequestMethod.POST)
	@ResponseBody
	public String tenanceShInfoList(String id, String fileaddress,
			HttpServletRequest request, HttpServletResponse response) {
		String total = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("total", total);
		List<MaintenanceDetails> details = mainTenanceService
				.findDetailsById(map);
		gson = new GsonBuilder().registerTypeAdapter(MaintenanceDetails.class,
				new JsonSerializer<MaintenanceDetails>() {
					@Override
					public JsonElement serialize(MaintenanceDetails arg0,
							Type arg1, JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("itemname", arg0.getItemname());
						json.addProperty("materials", arg0.getMaterials());// 材料人工
						json.addProperty("unit", arg0.getUnit());// 单位
						json.addProperty("quantity", arg0.getQuantity());// 数量
						json.addProperty("price", arg0.getPrice());
						json.addProperty("totalamount", arg0.getTotalamount());
						json.addProperty("memo", arg0.getMemo());// 备注
						return json;
					}
				}).create();
		return gson.toJson(details);
	}

	/**
	 * @Title: agree
	 * @Description: TODO(审核)
	 * @param @param id
	 * @param @param json
	 * @param @param suggest
	 * @param @param type
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/agree", method = RequestMethod.POST)
	@ResponseBody
	public String agree(String id, String json, String suggest, String type,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		try {
			MaintenanceAduit aduit = gson
					.fromJson(json, MaintenanceAduit.class);
			UUID uuid = UUID.randomUUID();
			aduit.setId(uuid.toString());
			aduit.setAduittime(new Date());
			aduit.setType(new Long(1));
			aduit.setAduiter(user.getUserid());
			String step = "";
			String state = "";
			if (type.equals("0")) {
				step = null;
				state = "4";
				mainTenanceService.insertAduit(aduit, step, state, suggest);
				activitiUnitService.messagePush(
						"audit", user.getUserid(),id);
				activitiUnitService.completeTask("audit", user.getUserid(), id,
						"order", "true");
				
			} else {
				step = "4";
				state = "1";
				mainTenanceService.disagreeAduit(aduit, step, state, suggest);
				activitiUnitService.messagePush(
						"audit", user.getUserid(),id);
				activitiUnitService.completeTask("audit", user.getUserid(), id,
						"order", "false");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'审核失敗！'}";
		}

		return "{'result':true,'msg':'审核成功！'}";
	}
}
