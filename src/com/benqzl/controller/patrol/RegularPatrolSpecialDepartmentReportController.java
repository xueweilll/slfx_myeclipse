package com.benqzl.controller.patrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.service.patrol.PatrolSpecialDepartmentReportService;
import com.google.gson.GsonBuilder;
/** 
* @ClassName: RegularPatrolSpecialDepartmentReportController 
* @Description: TODO(执行部门定期汇总上报) 
* @author pxj 
* @date 2016年5月27日 上午8:36:46 
*  
*/
@Controller
@RequestMapping("regularpatrolspecialdepartmentreport")
public class RegularPatrolSpecialDepartmentReportController extends BasicController {

	public RegularPatrolSpecialDepartmentReportController() {
		super(RegularPatrolSpecialDepartmentReportController.class);
	}
	/**
	 * @Title: index
	 * @Description: TODO(水政工程科)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("patrol/regularpatrolspecialdepartmentReport");
		return mv;
	}
	@Autowired
	private PatrolSpecialDepartmentReportService patrolSpecialDepartmentReportService;
	/**
	 * @Title: info
	 * @Description: TODO(info页面)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "patrolnormaldepartmentInfo", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView info(@RequestParam(required = true) String patrolplandetailid,String isid,String look) {
		List<Map<String, Object>> allResult = new ArrayList<Map<String, Object>>();
		List<String> list = new ArrayList<String>();
		String a[] = patrolplandetailid.split(",");
		for (int i = 0; i < a.length; i++) {
			list.add(a[i]);
		}
		//list获取PL_SPECIAL_IMPLEMENT主键ID
		//List<Map<String, Object>> listResult = patrolSpecialDepartmentReportService.selectVal(list);异步获取
		List<Map<String, Object>> listResult = patrolSpecialDepartmentReportService.selectVals(isid);//同步
		List<Integer> listTable = new ArrayList<Integer>();
		for (Map<String, Object> map : listResult) {
			
			String[] vals = map.get("VALS").toString().split(",");
			for (int i = 0; i < vals.length; i++) {
				Map<String, Object> obj = new HashMap<String,Object>();
				obj.put("ID", Integer.parseInt(vals[i].split("@")[0]));
				obj.put("SID", map.get("SID"));
				obj.put("NAME", map.get("NAME"));
				obj.put("ISID", map.get("ISID"));
				obj.put("IMPLEMENTID", map.get("IMPLEMENTID"));
				//System.out.print(vals[i].split("@").length);
				if(vals[i].split("@").length==1){
					obj.put("REMARK","");
				}else{
					obj.put("REMARK",vals[i].split("@")[1]);
				}
				allResult.add(obj);
				listTable.add(Integer.parseInt(vals[i].split("@")[0]));
			}
		}
		List<Map<String, Object>> listdetials=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listTableClass = patrolSpecialDepartmentReportService.selectTable(listTable);
		for (Map<String, Object> map : listTableClass) {
			for (Map<String, Object> mapResult : allResult) {
				Map<String,Object> map1=new HashMap<String,Object>();
				if(map.get("ID").toString().equals(mapResult.get("ID").toString())){
					map1.put("ID", map.get("ID"));
					map1.put("CLASSES", map.get("CLASSES"));
					map1.put("TNAME", map.get("TNAME"));
					map1.put("SID", mapResult.get("SID"));
					map1.put("ISID",mapResult.get("ISID"));
					map1.put("IMPLEMENTID", mapResult.get("IMPLEMENTID"));
					map1.put("REMARK", mapResult.get("REMARK"));
					map1.put("NAME", mapResult.get("NAME"));
					listdetials.add(map1);
				}
			}
		}
		Map<String, Object> jsonMap= new HashMap<>();
		jsonMap.put("total", listTableClass.size());
		jsonMap.put("rows", listdetials);
		String json = new GsonBuilder().create().toJson(jsonMap);
		ModelAndView mv = new ModelAndView();
		mv.addObject("jsonStr",json);
		mv.addObject("look",look);
		mv.setViewName("patrol/regularpatrolspecialdepartmentReportInfo");
		return mv;
	}
}
