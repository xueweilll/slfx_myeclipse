package com.benqzl.controller.dispatch.water;

import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.water.StPumpr;
import com.benqzl.service.system.StationService;
import com.benqzl.service.water.WaterRegimeService;
import com.benqzl.unit.DecoderUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: HistoryWaterRegimeController
 * @Description: 历史水情
 * @author pxj
 * @date 2016年1月30日 下午4:57:47
 * 
 */
@Controller
@RequestMapping("historyWaterRegime")
public class HistoryWaterRegimeController extends BasicController {

	@Autowired
	private WaterRegimeService regime;
	
	@Autowired
	private StationService stationservice;

	public HistoryWaterRegimeController() {
		super(BasicController.class);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/water/historyWaterRegimeList");
		return mv;
	}

	/**
	 * @Title: historyWaterRegimeList
	 * @Description: TODO(历史水情列表)
	 * @param @param page
	 * @param @param rows
	 * @param @param starttime
	 * @param @param endtime
	 * @param @param sname
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@RequestMapping(value = "/historyWaterRegimeList", method = RequestMethod.POST)
	@ResponseBody
	public String historyWaterRegimeList(int page, int rows, String starttime,
			String endtime, String sname,String stcd) {
		Map<String, Object> map = new HashMap<String, Object>();
		final String str = stcd;
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		if (sname == null || sname.replaceAll(" ", "").length() == 0) {
			map.put("sname", null);
		} else {
			map.put("sname", sname.replaceAll(" ", ""));
		}
		if (stcd == null || stcd.replaceAll(" ", "").length() == 0) {
			map.put("stcd", null);
		} else {
			map.put("stcd", stcd.replaceAll(" ", ""));
		}
		//map.put("sname", null);
		if (starttime == null || starttime.length() == 0) {
			map.put("starttime", null);
		} else {
			try {
				map.put("starttime", datetimeFormat.parse(starttime));
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
		List<StPumpr> historyRegime = null;
		int pageCount = 0;
		try {
			historyRegime = regime.findRegimeByPage(map);
			pageCount = regime.pageRegimeCount(map);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", pageCount);
		jsonMap.put("rows", historyRegime);
		gson = new GsonBuilder().registerTypeAdapter(StPumpr.class,
				new JsonSerializer<StPumpr>() {
					@Override
					public JsonElement serialize(StPumpr arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("fid", arg0.getfId());
						json.addProperty("code", arg0.getStcd());
						if (arg0.getStation() == null) {
							json.addProperty("sname", "");
						} else {
							json.addProperty("sname", arg0.getStation()
									.getName());
						}
						if(str==null){
							json.addProperty("tm",
									datetimeFormat.format(arg0.getTm()));
						}else{
							json.addProperty("tm",
									simpleDateFormat.format(arg0.getTm()));
						}
						if(arg0.getStcd().equals("zg")){
							json.addProperty("ppupz", arg0.getPpdwz());
							json.addProperty("ppdwz", arg0.getPpupz());
						}else{
							json.addProperty("ppupz", arg0.getPpupz());
							json.addProperty("ppdwz", arg0.getPpdwz());
						}
						
						json.addProperty("omcn", arg0.getOmcn());
						json.addProperty("ompwr", arg0.getOmpwr());
						json.addProperty("pmpq", arg0.getPmpq());
						String ppupwttn = "";
						if (arg0.getPpupwptn().equals("4")) {
							ppupwttn = "落";
						} else if (arg0.getPpupwptn().equals("5")) {
							ppupwttn = "涨";
						} else if (arg0.getPpupwptn().equals("6")) {
							ppupwttn = "平";
						}
						json.addProperty("ppupwptn", ppupwttn);
						String ppdwwptn = "";
						if (arg0.getPpdwwptn().equals("4")) {
							ppdwwptn = "落";
						} else if (arg0.getPpdwwptn().equals("5")) {
							ppdwwptn = "涨";
						} else if (arg0.getPpupwptn().equals("6")) {
							ppdwwptn = "平";
						}
						json.addProperty("ppdwwptn", ppdwwptn);
						String pdchcd = "";
						if (arg0.getPdchcd() == null) {
							pdchcd = "";
						} else if (arg0.getPdchcd().equals("2")) {
							pdchcd = "排水";
						} else if (arg0.getPdchcd().equals("1")) {
							pdchcd = "引水";
						}
						json.addProperty("pdchcd", pdchcd);
						return json;
					}
				}).create();
		return gson.toJson(jsonMap);
	}
     
	
	@RequestMapping(value="/station",method=RequestMethod.POST)
	@ResponseBody
	public String station(){
		String json="";
		List<Station> station=stationservice.findStation();
		gson = new GsonBuilder().registerTypeAdapter(
				Station.class,
				new JsonSerializer<Station>() {
					@Override
					public JsonElement serialize(Station arg0,
							Type arg1, JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("name", arg0.getName());
						return json;
					}
				}).create();
		json=gson.toJson(station);
		return json;
	}
	
	/**
	 * @Title: waterMobileJSON
	 * @Description: TODO(手机端waterJson数据)
	 * @param @param time
	 * @param @param year
	 * @param @param month
	 * @param @param day
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "waterMobileJSON", method = RequestMethod.GET)
	@ResponseBody
	public String waterMobileJSON(String day,String stationId) throws ParseException {
		List<Map<String, Object>> map;
		Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("year",  null );
			map1.put("month", null);
			map1.put("day",  day);
            map1.put("time", null);
            map1.put("sid", stationId);
		try {
			map = regime.findHistoryMobileWater(map1);
			gson = new Gson();
			return gson.toJson(map);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'数据读取失败！'}";
		}
	}
	/**
	 * @Title: waterJSON
	 * @Description: TODO(图形数据)
	 * @param @param time
	 * @param @param year
	 * @param @param month
	 * @param @param day
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "waterJSON", method = RequestMethod.GET)
	@ResponseBody
	public String waterJSON(String time, String year, String month, String day,String station) throws ParseException {
		List<Map<String, Object>> map;
		Map<String, Object> map1 = new HashMap<String, Object>();
		String sname=DecoderUtil.decoder(station);
		String years[]=dateFormat.format(new Date()).split("-");
		if (year == null || month == null || day == null
				||(year.equals("")&&month.equals("")&&day.equals(""))) {
			map1.put("year", null);
			map1.put("month", null);
			map1.put("day", null);
			//Station stations=stationservice.findStationName();
			map1.put("sid", sname);
			map1.put("time", years[0]);
		}
		else {
			map1.put("year", year == null || year.equals("") ? null : year);
			map1.put("month", month == null || month.equals("") ? null : month);
			map1.put("day", day == null || day.equals("") ? null : day);
            map1.put("time", null);
            map1.put("sid", sname);
		}

		try {
			map = regime.findHistoryWater(map1);
			gson = new Gson();
			return gson.toJson(map);
		} catch (Exception e) {
			e.printStackTrace();
			return "{'result':false,'msg':'数据读取失败！'}";
		}
	}
	@RequestMapping(value = "export", method = RequestMethod.POST)
	@ResponseBody
	public String export(String stationId,String startTime,String endTime, HttpServletRequest request){
		Map<String, Object>  map = new HashMap<>();
		map.put("stationid", stationId);
		try {
			map.put("starttime", datetimeFormat.parse(startTime));
			map.put("endtime", datetimeFormat.parse(endTime));
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("历史水情导出：时间格式化", e);
		}
		if(regime.pageCountStPumprExport(map)==0){
			return "{'result':false,'msg':'没有可以导出的数据！'}";
		}
		if(regime.pageCountStPumprExport(map)>10000){
			return "{'result':false,'msg':'数据量过大,请重新选择！'}";
		}
		List<StPumpr> stPumprs= regime.findStPumprExport(map);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("历史水情记录");
		sheet.setDefaultColumnWidth(20);
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("枢纽名称");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("时间");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("外河水位");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("内河水位");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("开机台数");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("开机功率");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("抽水流量");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("外河水势");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("内河水势");
		cell.setCellStyle(style);
		cell = row.createCell(9);
		cell.setCellValue("引排特征码");
		cell.setCellStyle(style);
		for (int i = 0; i < stPumprs.size(); i++) {
			StPumpr stPumpr = stPumprs.get(i);
			row=sheet.createRow(i+1);
			HSSFCell cellCloumn = row.createCell(0);
			cellCloumn.setCellStyle(style);
			cellCloumn.setCellValue(stPumpr.getSname());
			cellCloumn=row.createCell(1);
			cellCloumn.setCellStyle(style);
			cellCloumn.setCellValue(datetimeFormat.format(stPumpr.getTm())); 
			cellCloumn=row.createCell(4);
			cellCloumn.setCellStyle(style);
			cellCloumn.setCellValue(stPumpr.getOmcn()); 
			cellCloumn=row.createCell(5);
			cellCloumn.setCellStyle(style);
			cellCloumn.setCellValue(stPumpr.getOmpwr()); 
			cellCloumn=row.createCell(6);
			cellCloumn.setCellStyle(style);
			cellCloumn.setCellValue(stPumpr.getPmpq().toString()); 
			String ppupwttn = "";
			if (stPumpr.getPpupwptn().equals("4")) {
				ppupwttn = "落";
			} else if (stPumpr.getPpupwptn().equals("5")) {
				ppupwttn = "涨";
			} else if (stPumpr.getPpupwptn().equals("6")) {
				ppupwttn = "平";
			}
			String ppdwwptn = "";
			if (stPumpr.getPpdwwptn().equals("4")) {
				ppdwwptn = "落";
			} else if (stPumpr.getPpdwwptn().equals("5")) {
				ppdwwptn = "涨";
			} else if (stPumpr.getPpupwptn().equals("6")) {
				ppdwwptn = "平";
			}
			cellCloumn=row.createCell(7);
			cellCloumn.setCellStyle(style);
			cellCloumn.setCellValue(ppupwttn); 
			cellCloumn=row.createCell(8);
			cellCloumn.setCellStyle(style);
			cellCloumn.setCellValue(ppdwwptn); 
			if (stPumpr.getPdchcd() == null) {
				cellCloumn=row.createCell(9);
				cellCloumn.setCellStyle(style);
				cellCloumn.setCellValue("");
			} else if (stPumpr.getPdchcd().equals("2")) {
				cellCloumn=row.createCell(9);
				cellCloumn.setCellStyle(style);
				cellCloumn.setCellValue("排水");
			} else if (stPumpr.getPdchcd().equals("1")) {
				cellCloumn=row.createCell(9);
				cellCloumn.setCellStyle(style);
				cellCloumn.setCellValue("引水");
			}
			if(stPumpr.getStcd().equals("zg")){
				cellCloumn=row.createCell(3);
				cellCloumn.setCellStyle(style);
				cellCloumn.setCellValue(stPumpr.getPpupz().toString()); 
				cellCloumn=row.createCell(2);
				cellCloumn.setCellStyle(style);
				cellCloumn.setCellValue(stPumpr.getPpdwz().toString()); 
			}else{
				cellCloumn=row.createCell(2);
				cellCloumn.setCellStyle(style);
				cellCloumn.setCellValue(stPumpr.getPpupz().toString()); 
				cellCloumn=row.createCell(3);
				cellCloumn.setCellStyle(style);
				cellCloumn.setCellValue(stPumpr.getPpdwz().toString()); 
			}
		}
		try{
			String date = new SimpleDateFormat("yyyy年MM月dd日HH时mm分").format(new Date());
			String path=request.getSession().getServletContext().getRealPath("/") +"upload/"+stPumprs.get(0).getSname()+date+"导出历史水情.xls";
            System.out.println(path);
			FileOutputStream fout = new FileOutputStream(path);  
            wb.write(fout);  
            fout.close();
            return "{'result':true,'msg':'"+stPumprs.get(0).getSname()+date+"导出历史水情.xls"+"'}";
        }catch (Exception e){  
            e.printStackTrace();
            logger.error("数据导出失败", e);
            return "{'result':false,'msg':'导出失败'}";
        }
	}
}
