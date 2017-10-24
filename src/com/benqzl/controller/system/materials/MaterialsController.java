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
import com.benqzl.pojo.system.Material;
import com.benqzl.pojo.system.Prickle;
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
@RequestMapping("materials")
public class MaterialsController extends BasicController {

	public MaterialsController() {
		super(MaterialsController.class);
	}
    @Autowired
    private MaterialService materialService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/materials");
		return mv;
	}	
	@RequestMapping(value = "/systemMaterials", method = RequestMethod.POST)
	@ResponseBody
	public String systemMaterials(int page, int rows, String jsonStr) {
		String strJson = "";
		gson = new Gson();
		Material materials = new Material();
		if(jsonStr!=null){
			materials = gson.fromJson(jsonStr, Material.class);
		}
			page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p1", start);
		map.put("p2", rows);
        map.put("creater",materials.getCreater());
		if (materials.getHandlertime()==null) {
			map.put("endtime", null);
		} else {
			map.put("endtime", datetimeFormat.format(materials.getHandlertime()));

		}
		if (materials.getCreatetime()==null) {
			map.put("createtime", null);
		} else {
			map.put("createtime",
					datetimeFormat.format(materials.getCreatetime()));
		}
		List<Material> list = materialService.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = materialService.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows", list);
		gson = new GsonBuilder().registerTypeAdapter(Material.class,
				new JsonSerializer<Material>() {

					@Override
					public JsonElement serialize(Material src,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("creater", src.getEmployee().getName());
						o.addProperty("handler", src.getEmployee().getPhone());//Phone用于编辑人
						if(src.getCreatetime()==null){
							o.addProperty("createtime","");							
						}
						else{
							o.addProperty("createtime",datetimeFormat.format(src.getCreatetime()));

						}
						if(src.getHandlertime()==null){
							o.addProperty("handlertime","");
						}else{	
							o.addProperty("handlertime",datetimeFormat.format(src.getHandlertime()));
						}
						o.addProperty("mname",src.getName());
						o.addProperty("id",src.getId());
						o.addProperty("sname", src.getSize().getName());
						o.addProperty("pname", src.getPrickle().getName());
						String types="";
						if(src.getType()==null)
						{
							types="";
						}
						else if(src.getType()==0){
							types="灯具";
						}else if(src.getType()==1){
							types="电器开关";
						}else if(src.getType()==2){
							types="工具";
						}else if(src.getType()==3){
							types="劳保用品";
						}else if(src.getType()==4){
							types="电线电缆";
						}else if(src.getType()==5){
							types="油类";
						}else if(src.getType()==6){
							types="办公用品";
						}else if(src.getType()==7){
							types="生活用具";
						}else if(src.getType()==8){
							types="备用备件";
						}
						o.addProperty("type", types);
						String states="";
						if(src.getState()==0){
							states="保存";
						}else if(src.getState()==1){
							states="可用";
						}else if(src.getState()==2){
							states="撤销";
						}
						o.addProperty("state", states);
						o.addProperty("memo", src.getMemo());
						o.addProperty("sizeid",src.getSizeid());
						o.addProperty("prickleid", src.getPrickleid());
						return o;
					}
				}).create();
		strJson = gson.toJson(jsonMap);
		logger.info("this systemLogger list strJson is " + strJson);
		return strJson;
	}

@RequestMapping(value = "/materialsInfo", method = RequestMethod.GET)
public ModelAndView materialsInfo(String id) {
	ModelAndView mv = new ModelAndView();
	if (!id.equals("0")) {
		Material materials = materialService.selectMaterialByPrimaryKey(id);	
		
		 int prickle = materialService.validateprickle(materials.getPrickleid());
		 if(prickle==0){
			 materials.getPrickle().setId("");
		 }
		int size=materialService.validatesize(materials.getSizeid());
		if(size==0){
			materials.getSize().setId("");
		}
		mv.addObject("createtime",datetimeFormat.format(materials.getCreatetime()));
		mv.addObject("size", materials);
	}
	
	mv.setViewName("system/materialsInfo");
	return mv;
}

/** 
* @Title: saveMaterials 
* @Description: TODO(保存) 
* @param @param jsonStr
* @param @param request
* @param @return    设定文件 
* @return String    返回类型 
* @throws 
*/
@RequestMapping(value = "/saveMaterials",method = RequestMethod.POST)
@ResponseBody
public String saveMaterials(String jsonStr,HttpServletRequest request){
	String result = "";
	User user = (User) request.getSession().getAttribute("loginUser");
	gson = new Gson();
	Material materials = gson.fromJson(jsonStr, Material.class);
	if (materials.getId().equals("0")) {
		UUID uuid = UUID.randomUUID();
		materials.setId(uuid.toString());
		materials.setCreatetime(new Date());
		materials.setHandlertime(new Date());
		materials.setCreater(user.getUserid());
		try {
			materialService.insert(materials);
			result = "{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{'result':false,'msg':'添加失敗！'}";
		}

	}
	// 更新
	else {
		materials.setHandler(user.getUserid());
		materials.setHandlertime(new Date());
		try {
			materialService.updateByPrimaryKey(materials);
			result = "{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{'result':false,'msg':'編輯失敗！'}";
		}
	}
	return result;
}
@RequestMapping(value="/commit",method=RequestMethod.POST)
@ResponseBody
public String commit(String jsonStr,String type,HttpServletRequest request){
	String result="";
	User user = (User) request.getSession().getAttribute("loginUser");
	gson = new Gson();
	Material materials = gson.fromJson(jsonStr, Material.class);
	if(materials.getId().equals("0")){
		UUID uuid = UUID.randomUUID();
		materials.setId(uuid.toString());
		materials.setCreatetime(new Date());
		materials.setHandlertime(new Date());
		materials.setCreater(user.getUserid());
		try {
			materialService.insert(materials);
			result = "{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{'result':false,'msg':'提交失敗！'}";
		}
	}else{
		materials.setHandler(user.getUserid());
		materials.setHandlertime(new Date());
		try {
			materialService.updateByPrimaryKey(materials);
			result="{'result':true}";
		} catch (Exception e) {
			e.printStackTrace();
			result="{'result':true}";
		}
	}
	
	return result;
}
@RequestMapping(value="/findSizeName",method=RequestMethod.POST)
@ResponseBody
public String findSizeName(){
	String json="";
	gson = new Gson();
	List<Size> list=materialService.findSizeName();
	json=gson.toJson(list);
	return json;
}
@RequestMapping(value = "/findPrickleName", method = RequestMethod.POST)
@ResponseBody
public String findPrickleName(){
	String json = "";
    List<Prickle> list = materialService.findPrickleName();
	json = gson.toJson(list);
	return json;
}
@RequestMapping(value = "/deleteMaterials", method = RequestMethod.POST)
@ResponseBody
public String deleteMaterials(String id) {
	String result = "";
	if (id != null && id != "") {
		try {
			materialService.deleteByPrimaryKey(id);
			return "result:true";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{'result':false,'msg':'删除失敗！'}";
		}
	}
	return result;
}
}
