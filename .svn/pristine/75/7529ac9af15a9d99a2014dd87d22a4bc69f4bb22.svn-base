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
@RequestMapping("prickle")
public class PrickleController extends BasicController {

	public PrickleController() {
		super(PrickleController.class);
	}
    @Autowired
    private MaterialService materialService;
    @RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/prickle");
		return mv;
	}
    @RequestMapping(value = "/systemPrickle", method = RequestMethod.POST)
    @ResponseBody
    public String systemPrickle(int page, int rows, String jsonStr) {
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
    	List<Prickle> list = materialService.findPrickleByPage(map);
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
    	int total = materialService.pagePrickleCount(map);
    	jsonMap.put("total", total);
    	jsonMap.put("rows", list);
    	gson = new GsonBuilder().registerTypeAdapter(Prickle.class,
    			new JsonSerializer<Prickle>() {

    				@Override
    				public JsonElement serialize(Prickle src,
    						Type typeOfSrc, JsonSerializationContext context) {
    					JsonObject o = new JsonObject();
    					o.addProperty("creater", src.getEmployee().getName());
    					o.addProperty("editer", src.getEmployee().getPhone());//Phone用于编辑人
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

    @RequestMapping(value = "/prickleInfo", method = RequestMethod.GET)
    public ModelAndView prickleInfo(String id) {
    ModelAndView mv = new ModelAndView();
    if (!id.equals("0")) {
    	Prickle prickle = materialService.selectPrickleByPrimaryKey(id);	
    	mv.addObject("size", prickle);
    }
    mv.setViewName("system/prickleInfo");
    return mv;
    }

    @RequestMapping(value = "/savePrickle",method = RequestMethod.POST)
    @ResponseBody
    public String savePrickle(String jsonStr,HttpServletRequest request){
    String result = "";
    User user = (User) request.getSession().getAttribute("loginUser");
    gson = new Gson();
    Prickle prickle = gson.fromJson(jsonStr, Prickle.class);
    if (prickle.getId().equals("0")) {
    	UUID uuid = UUID.randomUUID();
    	prickle.setId(uuid.toString());
    	prickle.setCreatetime(new Date());
     	prickle.setEdittime(new Date());
    	prickle.setIsdel(new Long(0));
    	prickle.setCreaterid(user.getUserid());
    	prickle.setEditer(user.getUserid());
    	try {
    		materialService.insertPrickle(prickle);
    		result = "{'result':true}";
    	} catch (Exception e) {
    		e.printStackTrace();
    		result = "{'result':false,'msg':'添加失敗！'}";
    	}

    }
    // 更新
    else {
    	prickle.setEdittime(new Date());
    	prickle.setEditer(user.getUserid());
    	try {
    		materialService.updatePrickleByPrimaryKey(prickle);
    		result = "{'result':true}";
    	} catch (Exception e) {
    		e.printStackTrace();
    		result = "{'result':false,'msg':'編輯失敗！'}";
    	}
    }
    return result;
    }
    @RequestMapping(value = "/findPrickleName", method = RequestMethod.POST)
    @ResponseBody
    public String findPrickleName(){
		String json = "";
	    List<Prickle> list = materialService.findPrickleName();
		json = gson.toJson(list);
		return json;
    }
    @RequestMapping(value = "/deletePrickle", method = RequestMethod.POST)
    @ResponseBody
    public String deletePrickle(String id) {
    String result = "";
    if (id != null && id != "") {
    	try {
    		materialService.deletePrickleByPrimaryKey(id);
    		return "result:true";
    	} catch (Exception e) {
    		e.printStackTrace();
    		result = "{'result':false,'msg':'删除失敗！'}";
    	}
    }
    return result;
    }
    
}