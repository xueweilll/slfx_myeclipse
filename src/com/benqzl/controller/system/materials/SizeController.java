package com.benqzl.controller.system.materials;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Size;
import com.benqzl.pojo.system.User;
import com.benqzl.service.system.MaterialService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Controller
@RequestMapping("size")
public class SizeController extends BasicController {

	public SizeController() {
		super(SizeController.class);
	}
    @Autowired
    private MaterialService materialService;
    @RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/size");
		return mv;
	}
	@RequestMapping(value = "/systemSize", method = RequestMethod.POST)
	@ResponseBody
	public String systemSize(int page, int rows, String jsonStr) {
		String strJson = "";
		gson = new Gson();
		Size size = new Size();
		if(jsonStr!=null){
			size = gson.fromJson(jsonStr, Size.class);
		}
			page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);

		if (size.getEdittime()==null) {
			map.put("endtime", null);
		} else {
			map.put("endtime", datetimeFormat.format(size.getEdittime()));

		}
		if (size.getCreatetime()==null) {
			map.put("createtime", null);
		} else {
			map.put("createtime",
					datetimeFormat.format(size.getCreatetime()));
		}
		List<Size> list = materialService.findSizeByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = materialService.pageSizeCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Size.class,
				new JsonSerializer<Size>() {

					@Override
					public JsonElement serialize(Size src,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("creater", src.getEmployee().getName());
						o.addProperty("editer", src.getEmployee().getPhone());//phone用于编辑人
						if(src.getCreatetime()==null){
							o.addProperty("createtime","");							
						}
						else{
							o.addProperty("createtime",datetimeFormat.format(src.getCreatetime()));

						}
						if(src.getEdittime()==null){
							o.addProperty("edittime","");
						}else{	
							o.addProperty("edittime",datetimeFormat.format(src.getEdittime()));
						}
						o.addProperty("name",src.getName());
						o.addProperty("id",src.getId());
						return o;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		logger.info("this systemLogger list strJson is " + strJson);
		return strJson;
	}

@RequestMapping(value = "/sizeInfo", method = RequestMethod.GET)
public ModelAndView sizeInfo(String id) {
	ModelAndView mv = new ModelAndView();
	if (!id.equals("0")) {
		Size size = materialService.selectSizeByPrimaryKey(id);	
		mv.addObject("size", size);
	}
	mv.setViewName("system/sizeInfo");
	return mv;
}

@RequestMapping(value = "/saveSize",method = RequestMethod.POST)
@ResponseBody
public String saveSize(String jsonStr,HttpServletRequest request){
	String result = "";
	User user = (User) request.getSession().getAttribute("loginUser");
	gson = new Gson();
	Size size = gson.fromJson(jsonStr, Size.class);
	if (size.getId().equals("0")) {
		UUID uuid = UUID.randomUUID();
		size.setId(uuid.toString());
		size.setCreatetime(new Date());
		size.setEdittime(new Date());
		size.setIsdel(new Long(0));
		size.setCreaterid(user.getUserid());
		size.setEditer(user.getUserid());
		try {
			materialService.insertSize(size);
			result = "{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{'result':false,'msg':'添加失敗！'}";
		}

	}
	// 更新
	else {
		size.setEdittime(new Date());
		size.setEditer(user.getUserid());
		try {
			materialService.updateSizeByPrimaryKey(size);
			result = "{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{'result':false,'msg':'編輯失敗！'}";
		}
	}
	return result;
}
@RequestMapping(value="/findSizeName",method=RequestMethod.POST)
@ResponseBody
public String findSizeName(){
	String json="";
	List<Size> list=materialService.findSizeName();
	json=gson.toJson(list);
	return json;
}
@RequestMapping(value = "/deleteSize", method = RequestMethod.POST)
@ResponseBody
public String deleteSize(String id) {
	String result = "";
	if (id != null && id != "") {
		try {
			materialService.deleteSizeByPrimaryKey(id);
			return "result:true";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{'result':false,'msg':'删除失敗！'}";
		}
	}
	return result;
}
}
