package com.benqzl.controller.system.parameter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;

@Controller
@RequestMapping("paramers")
public class ParamersController extends BasicController {

	public ParamersController() {
		super(ParamersController.class);
	}

	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/paramers");
		return mv;
	}
	
	@RequestMapping(value="types",method=RequestMethod.POST)
	@ResponseBody
	public String types(){
		String json = "[{\"id\":\"0\",\"text\":\"贯流\"},{\"id\":\"1\",\"text\":\"轴流\"}]";
		return json;
	}
	
	@RequestMapping(value="featureData",method=RequestMethod.POST)
	@ResponseBody
	public String featureData(){
		String json = "[{\"id\":\"0\",\"text\":\"引水\"},{\"id\":\"1\",\"text\":\"排涝\"}]";
		return json;
	}
	
	@RequestMapping(value="onOffWayData",method=RequestMethod.POST)
	@ResponseBody
	public String onOffWayData(){
		String json = "[{\"id\":\"0\",\"text\":\"液压\"},{\"id\":\"1\",\"text\":\"卷扬\"}]";
		return json;
	}
	@RequestMapping(value="sex",method=RequestMethod.POST)
	@ResponseBody
	public String sex(){
		String json = "[{\"id\":\"0\",\"text\":\"男\"},{\"id\":\"1\",\"text\":\"女\"}]";
		return json;
	}
	@RequestMapping(value="loggerType",method=RequestMethod.POST)
	@ResponseBody
	public String loggerType(){
		String json = "[{\"id\":\"8\",\"text\":\"全部操作\"},{\"id\":\"0\",\"text\":\"登录\"},{\"id\":\"1\",\"text\":\"退出\"},{\"id\":\"2\",\"text\":\"增加\"},{\"id\":\"3\",\"text\":\"删除\"},{\"id\":\"4\",\"text\":\"修改\"},{\"id\":\"5\",\"text\":\"查找\"},{\"id\":\"6\",\"text\":\"审批\"},{\"id\":\"7\",\"text\":\"打印\"}]";
		return json;
	}
	@RequestMapping(value="loggerLever",method=RequestMethod.POST)
	@ResponseBody
	public String loggerLever(){
		String json = "[{\"id\":\"8\",\"text\":\"全部操作\"},{\"id\":\"0\",\"text\":\"轻\"},{\"id\":\"1\",\"text\":\"一般\"},{\"id\":\"2\",\"text\":\"严重\"}]";
		return json;
	}
	
	@RequestMapping(value="receiverType",method=RequestMethod.POST)
	@ResponseBody
	public String receiverType(){
		String json = "[{\"id\":\"0\",\"text\":\"手机\"},{\"id\":\"1\",\"text\":\"PC\"}]";
		return json;
	}

	@RequestMapping(value="leve",method=RequestMethod.POST)
	@ResponseBody
	public String leve(){
		String json = "[{\"id\":\"0\",\"text\":\"处级\"},{\"id\":\"1\",\"text\":\"科员\"},{\"id\":\"2\",\"text\":\"科级\"}]";
		return json;
	}
	

	@RequestMapping(value="activitiType",method=RequestMethod.POST)
	@ResponseBody
	public String activitiType(){
		String json = "[{\"id\":\"0\",\"text\":\"防洪调度\"},{\"id\":\"1\",\"text\":\"安防巡检\"}]";
		return json;
	}
	
	@RequestMapping(value="wayData",method=RequestMethod.POST)
	@ResponseBody
	public String wayData(){
		String json = "[{\"id\":\"0\",\"text\":\"电话\"},{\"id\":\"1\",\"text\":\"传真\"}]";
		return json;
	}
	
	@RequestMapping(value="bigWayData",method=RequestMethod.POST)
	@ResponseBody
	public String bigWayData(){
		String json = "[{\"id\":\"0\",\"text\":\"电话\"},{\"id\":\"1\",\"text\":\"传真\"},{\"id\":\"3\",\"text\":\"全部\"}]";
		return json;
	}

	@RequestMapping(value="instruction",method=RequestMethod.POST)
	@ResponseBody
	public String instruction(){
		String json = "[{\"id\":\"0\",\"text\":\"不操作\"},{\"id\":\"1\",\"text\":\"开闸\"},{\"id\":\"2\",\"text\":\"关闸\"}]";
		return json;
	}
	
	@RequestMapping(value="gate",method=RequestMethod.POST)
	@ResponseBody
	public String gate(){
		String json = "[{\"id\":\"0\",\"text\":\"不操作\"},{\"id\":\"1\",\"text\":\"开泵\"},{\"id\":\"2\",\"text\":\"关泵\"}]";
		return json;
	}
	
	@RequestMapping(value="station",method=RequestMethod.POST)
	@ResponseBody
	public String station(){
		String json = "[{\"id\":\"0\",\"text\":\"横塘河北枢纽\"},{\"id\":\"1\",\"text\":\"横塘河南枢纽\"},{\"id\":\"2\",\"text\":\"丁横河枢纽\"},{\"id\":\"3\",\"text\":\"糜家塘枢纽\"},{\"id\":\"4\",\"text\":\"横峰沟枢纽\"}]";
		return json;
	}

	@RequestMapping(value="gateOperate",method=RequestMethod.GET)
	@ResponseBody
	public String gateOperate(){
		String json = "[{\"id\":\"0\",\"text\":\"请选择\"},{\"id\":\"1\",\"text\":\"开闸\"},{\"id\":\"2\",\"text\":\"关闸\"}]";
		return json;
	}
	@RequestMapping(value="dispatchTypeData",method=RequestMethod.POST)
	@ResponseBody
	public String dispatchTypeData(){
		String json = "[{\"id\":\"0\",\"text\":\"片区调度\"},{\"id\":\"1\",\"text\":\"大包围调度\"}]";
		return json;
	}
	@RequestMapping(value="unitOperate",method=RequestMethod.GET)
	@ResponseBody
	public String unitOperate(){
		String json = "[{\"id\":\"0\",\"text\":\"请选择\"},{\"id\":\"1\",\"text\":\"开泵\"},{\"id\":\"2\",\"text\":\"关泵\"}]";
		return json;
	}
	@RequestMapping(value="Operate",method=RequestMethod.POST)
	@ResponseBody
	public String Operate(){
		String json = "[{\"id\":\"0\",\"text\":\"关闸\"},{\"id\":\"1\",\"text\":\"开闸\"},{\"id\":\"2\",\"text\":\"常关\"}]";
		return json;
	}
	@RequestMapping(value="PatrolType",method=RequestMethod.POST)
	@ResponseBody
	public String PatrolType(){
		String json = "[{\"enumid\":\"01\",\"text\":\"内外河引河\",\"contents\":\"水流平顺，无杂物，无水草，无威胁工程的漂浮物\"},"
				+ "{\"enumid\":\"02\",\"text\":\"内外河护坡\",\"contents\":\"护坡完好，排水畅通，无塌陷，表面无开裂破损\"},"
				+ "{\"enumid\":\"03\",\"text\":\"内外河翼墙\",\"contents\":\"墙体完好，排水通畅，无塌陷，表面无开裂破损\"},"
				+ "{\"enumid\":\"04\",\"text\":\"翼墙后填土区\",\"contents\":\"无雨淋沟，无裂缝，无塌陷，无渗漏，无滑坡\"},"
				+ "{\"enumid\":\"05\",\"text\":\"泵站内外进水池\",\"contents\":\"水流平顺，无杂物，无水草\"},"
				+ "{\"enumid\":\"06\",\"text\":\"导流墩\",\"contents\":\"墩体完好，无裂缝，无渗漏，观测标志完好\"},"
				+ "{\"enumid\":\"07\",\"text\":\"泵站主厂房\",\"contents\":\"墙面完好，门窗完好，伸缩缝完好，排水通畅，无破损，无渗漏\"},"
				+ "{\"enumid\":\"08\",\"text\":\"泵站站身\",\"contents\":\"工程设施完好，无裂缝，无渗漏，伸缩缝完好\"},"
				+ "{\"enumid\":\"09\",\"text\":\"变压器\",\"contents\":\"室温正常，风扇运行正常\"},"
				+ "{\"enumid\":\"10\",\"text\":\"节制闸闸室\",\"contents\":\"闸门无振动、倾斜，闸下流态正常，无漂浮物\"},"
				+ "{\"enumid\":\"11\",\"text\":\"节制闸启闭机\",\"contents\":\"钢丝绳无断丝无明显变形，油缸无泄漏\"},"
				+ "{\"enumid\":\"12\",\"text\":\"交通桥\",\"contents\":\"桥面完好，栏杆完好，观测标志完好，无裂缝，无渗漏\"},"
				+ "{\"enumid\":\"13\",\"text\":\"消防器材\",\"contents\":\"灭火器压力正常，消防栓内水带，水栓完好\"},"
				+ "{\"enumid\":\"14\",\"text\":\"高压室\",\"contents\":\"柜体、开关完好，仪表显示正常，柜内照明正常，柜前后绝缘垫完好清洁\"},"
				+ "{\"enumid\":\"15\",\"text\":\"高压电容室\",\"contents\":\"柜体、开关完好，仪表显示正常，柜内照明正常，柜前后绝缘垫完好清洁\"},"
				+ "{\"enumid\":\"16\",\"text\":\"低压室\",\"contents\":\"柜体、开关完好，仪表显示正常，电容补偿正常，指示灯显示正常，柜前后绝缘垫完好清洁\"},"
				+ "{\"enumid\":\"17\",\"text\":\"中控室\",\"contents\":\"监控设备运行正常，数据显示正确，视频监控画面清晰\"},"
				+ "{\"enumid\":\"18\",\"text\":\"其他\",\"contents\":\"\"}]";
		return json;
	}
	@RequestMapping(value="MaterialType",method=RequestMethod.POST)
	@ResponseBody
	public String MaterialType(){
		String json = "[{\"id\":\"0\",\"text\":\"灯具\"},{\"id\":\"1\",\"text\":\"电器开关\"},"
				+ "{\"id\":\"2\",\"text\":\"工具\"},{\"id\":\"3\",\"text\":\"劳保用品\"},"
				+ "{\"id\":\"4\",\"text\":\"电线电缆\"},{\"id\":\"5\",\"text\":\"油类\"},"
				+ "{\"id\":\"6\",\"text\":\"办公用品\"},{\"id\":\"7\",\"text\":\"生活用具\"},"
				+ "{\"id\":\"8\",\"text\":\"备用备件\"}]";
		return json;
	}
	
	@RequestMapping(value="MaterialTypeAll",method=RequestMethod.POST)
	@ResponseBody
	public String MaterialTypeAll(){
		String json = "[{\"id\":\"\",\"text\":\"全部\"},{\"id\":\"0\",\"text\":\"灯具\"},{\"id\":\"1\",\"text\":\"电器开关\"},"
				+ "{\"id\":\"2\",\"text\":\"工具\"},{\"id\":\"3\",\"text\":\"劳保用品\"},"
				+ "{\"id\":\"4\",\"text\":\"电线电缆\"},{\"id\":\"5\",\"text\":\"油类\"},"
				+ "{\"id\":\"6\",\"text\":\"办公用品\"},{\"id\":\"7\",\"text\":\"生活用具\"},"
				+ "{\"id\":\"8\",\"text\":\"备用备件\"}]";
		return json;
	}
	
	@RequestMapping(value="ProblemType",method=RequestMethod.POST)
	@ResponseBody
	public String ProblemType(){
		String json = "[{\"id\":\"0\",\"text\":\"正常\"},{\"id\":\"1\",\"text\":\"有问题\"}]";
		return json;
	}
	
	
	
	@RequestMapping(value="HandleType",method=RequestMethod.POST)
	@ResponseBody
	public String HandleType(){
		String json = "[{\"id\":\"0\",\"text\":\"自行解决\"},{\"id\":\"1\",\"text\":\"上报工程科\"}]";
		return json;
	}
	
	@RequestMapping(value="profitAndLossType",method=RequestMethod.POST)
	@ResponseBody
	public String profitAndLossType(){
		String json = "[{\"id\":\"0\",\"text\":\"溢出\"},{\"id\":\"1\",\"text\":\"损失\"}]";
		return json;
	}
	@RequestMapping(value="inStockType",method=RequestMethod.POST)
	@ResponseBody
	public String inStockType(){
		String json = "[{\"id\":\"9\",\"text\":\"全部\"},{\"id\":\"1\",\"text\":\"手动入库\"},{\"id\":\"2\",\"text\":\"归还入库\"},"
				+ "{\"id\":\"0\",\"text\":\"溢出入库\"}]";
		return json;
	}
	@RequestMapping(value="outStockType",method=RequestMethod.POST)
	@ResponseBody
	public String outStockType(){
		String json = "[{\"id\":\"9\",\"text\":\"全部\"},{\"id\":\"1\",\"text\":\"手动出库\"},{\"id\":\"2\",\"text\":\"外借出库\"},"
				+ "{\"id\":\"3\",\"text\":\"调拨出库\"},{\"id\":\"0\",\"text\":\"损失出库\"}]";
		return json;
	}
	@RequestMapping(value="month",method=RequestMethod.POST)
	@ResponseBody
	public String month(){
		String json = "[{\"id\":\"0\",\"text\":\"全部\"},{\"id\":\"1\",\"text\":\"一月\"},{\"id\":\"2\",\"text\":\"二月\"},"
				+ "{\"id\":\"3\",\"text\":\"三月\"},{\"id\":\"4\",\"text\":\"四月\"},{\"id\":\"5\",\"text\":\"五月\"},"
				+ "{\"id\":\"6\",\"text\":\"六月\"},{\"id\":\"7\",\"text\":\"七月\"},"
				+ "{\"id\":\"8\",\"text\":\"八月\"},{\"id\":\"9\",\"text\":\"九月\"},{\"id\":\"10\",\"text\":\"十月\"},"
				+ "{\"id\":\"11\",\"text\":\"十一月\"},{\"id\":\"12\",\"text\":\"十二月\"}]";
		return json;
	}
	@RequestMapping(value="gateType",method=RequestMethod.POST)
	@ResponseBody
	public String gateType(){
		String json = "[{\"id\":\"0\",\"text\":\"关闸\"},{\"id\":\"1\",\"text\":\"开闸\"}]";
		return json;
	}
}	
