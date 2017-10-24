package com.benqzl.controller.dispatch.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.util.UserStatistics;
import com.benqzl.service.dispatch.ReceiptService;
import com.benqzl.unit.DecoderUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
@Controller
@RequestMapping("statisticsList")
public class UserStatisticsController extends BasicController {

	
	@Autowired
	private ReceiptService receiptDispatchExecute;
	public UserStatisticsController() {
		super(UserStatisticsController.class);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dispatch/userStatisticsList");
		return mv;
	}
	
	@RequestMapping(value = "print", method = RequestMethod.GET)
	public ModelAndView print(String ssid,String dcode,String type,String begintimes,String endtimes,String dispatchtype) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		if(dcode==null ||dcode==""){
			map.put("dcode", null);
		}else{
			map.put("dcode", dcode.replaceAll(" ", ""));
		}
		String[] s=ssid.split(",");
		List<String> ss=new ArrayList<>();
		for(int i=0;i<s.length;i++){
			if(s[i].equals("0")||s[i].equals("")){
				ss=null;
				break;
			}else{
				ss.add(s[i]);
			}
		}
		map.put("ssid", ss);
		if(type==null||type.equals("")){
			map.put("way", null);
		}else{
			map.put("way", type);
		}
		if(begintimes==null||begintimes.trim().equals("")){
			map.put("begintime", null);
		}else{
			try {
				map.put("begintime", datetimeFormat.parse(begintimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endtimes==null||endtimes.trim().equals("")){
			map.put("endtime", null);
		}else{
			try {
				map.put("endtime", datetimeFormat.parse(endtimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<UserStatistics>  list = new ArrayList<>();
		if (dispatchtype.equals("1")) {
			map.put("dispatchtype", "0");
		} else if (dispatchtype.equals("0")) {
			map.put("dispatchtype", "1");
		}
		if (dispatchtype.equals("2")) {
			list = receiptDispatchExecute.findSelfStatisticsPrint(map);
		} else {
			try {
				list = receiptDispatchExecute.findStatisticsPrint(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		gson = new GsonBuilder().registerTypeAdapter(UserStatistics.class, new JsonSerializer<UserStatistics>() {
			@Override
			public JsonElement serialize(UserStatistics arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("designdischarge", arg0.getDesignDischarge());
				json.addProperty("count", arg0.getCount());
				if(arg0.getBegintime()==null){
					json.addProperty("begintime", "");
				}else{
				json.addProperty("begintime",arg0.getBegintime().replace(".0", ""));
				}
				if(arg0.getEndtime()==null){
				json.addProperty("endtime", "");	
				}else{
				json.addProperty("endtime", arg0.getEndtime().replace(".0", ""));
				}
				json.addProperty("kjtime", arg0.getKjtime());
				json.addProperty("dcscharge", arg0.getDcscharge());
				json.addProperty("cscharge", arg0.getCscharge());
				json.addProperty("sname",arg0.getSname());//枢纽名称
				json.addProperty("rdstate", arg0.getRdstate()==2?"已实施未完成":"完成");//状态
				json.addProperty("code", arg0.getCode());
				json.addProperty("runtime", arg0.getRuntime());//运行时间
                json.addProperty("rdeid", arg0.getId());//id
	         	if(arg0.getDispatchtype()==null){
				json.addProperty("dispatchtype", "自主调度");
				if(arg0.getSelfDispatchExecute()==null){
					json.addProperty("startinlandlevel", "");
					json.addProperty("startouterlevel", "");
					json.addProperty("stopinlandlevel", "");
					json.addProperty("stopouterlevel", "");	
				}else{
				json.addProperty("startinlandlevel", arg0.getSelfDispatchExecute().getStartinlandlevel());
				json.addProperty("startouterlevel", arg0.getSelfDispatchExecute().getStartouterlevel());
				json.addProperty("stopinlandlevel", arg0.getSelfDispatchExecute().getStopinlandlevel());
				json.addProperty("stopouterlevel", arg0.getSelfDispatchExecute().getStopouterlevel());
				}}else if(arg0.getDispatchtype()==0){
					json.addProperty("dispatchtype", "片区调度");	
					if(arg0.getReceiptDispatchExecute()==null){
						json.addProperty("startinlandlevel", "");
						json.addProperty("startouterlevel", "");
						json.addProperty("stopinlandlevel", "");
						json.addProperty("stopouterlevel", "");	
					}
					else{
					json.addProperty("startinlandlevel", arg0.getReceiptDispatchExecute().getStartinlandlevel());
					json.addProperty("startouterlevel", arg0.getReceiptDispatchExecute().getStartouterlevel());
					json.addProperty("stopinlandlevel", arg0.getReceiptDispatchExecute().getStopinlandlevel());
					json.addProperty("stopouterlevel", arg0.getReceiptDispatchExecute().getStopouterlevel());
				}}else if(arg0.getDispatchtype()==1){
					json.addProperty("dispatchtype","大包围调度");
					if(arg0.getReceiptDispatchExecute()==null){
						json.addProperty("startinlandlevel", "");
						json.addProperty("startouterlevel", "");
						json.addProperty("stopinlandlevel","");
						json.addProperty("stopouterlevel", "");	
					}else{
					json.addProperty("startinlandlevel", arg0.getReceiptDispatchExecute().getStartinlandlevel());
					json.addProperty("startouterlevel", arg0.getReceiptDispatchExecute().getStartouterlevel());
					json.addProperty("stopinlandlevel", arg0.getReceiptDispatchExecute().getStopinlandlevel());
					json.addProperty("stopouterlevel", arg0.getReceiptDispatchExecute().getStopouterlevel());
				}}
				return json;
			}
		}).create();
		mv.addObject("firstDemo", gson.toJson(list));
		mv.setViewName("dispatch/userStatisticsPrint");
		return mv;
	}
	
	@RequestMapping(value="/userStatisticsList",method=RequestMethod.POST)
	@ResponseBody
	public String bind(String jsonStr,String ssid,HttpServletRequest request,int page,int rows,
			String dcode,String type,String begintimes,String endtimes,String deadlines,String dispatchtype){
		Map<String, Object> map = new HashMap<String, Object>();
		String strJson = "";
	/*	if(ssid==null||ssid.equals("")||ssid.equals("0")){
			map.put("ssid", null);
		}else{
		map.put("ssid", ssid);
		}*/
		List<String> sids=new ArrayList<>();
		if(jsonStr==null||jsonStr==""){
			sids=null;
		}else{
			List<String> sides=new ArrayList<>();
			gson = new Gson();
			UserStatistics stationJson = gson.fromJson(jsonStr, UserStatistics.class);//获取前台传递的数据参数，解析成java类
			    if(stationJson.getSsid().size()==0){
			    	sides=null;
			    }else{
			    	for(int i=0;i<stationJson.getSsid().size();i++){
						if(stationJson.getSsid().get(i).equals("0")||stationJson.getSsid().get(i).equals("")){
							sides=null;
							break;
					}
						sides.add(stationJson.getSsid().get(i));
					}
			    }	
			sids=sides;
		}
		map.put("ssid", sids);
		List<UserStatistics> list = new ArrayList<>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		map.put("type", "1");
		
		if(dcode==null ||dcode==""){
			map.put("dcode", null);
		}else{
			map.put("dcode", dcode.replaceAll(" ", ""));
		}
	
		if(type==null||type.equals("")){
			map.put("way", null);
		}else{
			map.put("way", type);
		}
		if(begintimes==null||begintimes.trim().equals("")){
			map.put("begintime", null);
		}else{
			try {
				map.put("begintime", datetimeFormat.parse(begintimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endtimes==null||endtimes.trim().equals("")){
			map.put("endtime", null);
		}else{
			try {
				map.put("endtime", datetimeFormat.parse(endtimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(deadlines==null||deadlines.trim().equals("")){//截止时间
			map.put("deadlines", null);
		}else{
			map.put("deadlines", deadlines);
		}
		int count=0;
		if(dispatchtype.equals("1")){
			map.put("dispatchtype", "0");
		}else if(dispatchtype.equals("0")){
			map.put("dispatchtype", "1");
		}
		if(dispatchtype.equals("2")){
			
			list=receiptDispatchExecute.findSelfStatisticsByPage(map);
			count=receiptDispatchExecute.findSelfStatisticsCount(map);
		}else{
		try {
			list=receiptDispatchExecute.findStatisticsByPage(map);
			count=receiptDispatchExecute.pageStatisticsCount(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		Map<String, Object> jsonMap = new LinkedHashMap<String, Object>();
		Long kjcounts=new Long(0);
		BigDecimal sumll=new BigDecimal(0);
		BigDecimal kjtimes=new BigDecimal(0);
		for(int i=0;i<list.size();i++){
			kjcounts+=list.get(i).getCount();
			if(list.get(i).getCscharge()==null){
				BigDecimal big=new BigDecimal(0);
				sumll=sumll.add(big);
			}else{
			sumll=sumll.add(list.get(i).getCscharge());
			}
			if(list.get(i).getKjtime()==null){
				BigDecimal kjtime=new BigDecimal(0);
				kjtimes=kjtimes.add(kjtime);
			}else{
				kjtimes=kjtimes.add(list.get(i).getKjtime());
			}
		}
		gson = new GsonBuilder().registerTypeAdapter(UserStatistics.class, new JsonSerializer<UserStatistics>() {
			@Override
			public JsonElement serialize(UserStatistics arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject json = new JsonObject();
				json.addProperty("designdischarge", arg0.getDesignDischarge());
				json.addProperty("count", arg0.getCount());
				if(arg0.getBegintime()==null){
					json.addProperty("begintime", "");
				}else{
				json.addProperty("begintime",arg0.getBegintime().replace(".0", ""));
				}
				if(arg0.getEndtime()==null){
				json.addProperty("endtime", "");	
				}else{
				json.addProperty("endtime", arg0.getEndtime().replace(".0", ""));
				}
				json.addProperty("kjtime", arg0.getKjtime());
				json.addProperty("dcscharge", arg0.getDcscharge());
				json.addProperty("cscharge", arg0.getCscharge());
				json.addProperty("sname",arg0.getSname());//枢纽名称
				json.addProperty("rdstate", arg0.getRdstate()==2?"已实施未完成":"完成");//状态
				json.addProperty("code", arg0.getCode());
				json.addProperty("runtime", arg0.getRuntime());//运行时间
                json.addProperty("rdeid", arg0.getId());//id
	         	if(arg0.getDispatchtype()==null){
				json.addProperty("dispatchtype", "自主调度");
				if(arg0.getSelfDispatchExecute()==null){
					json.addProperty("startinlandlevel", "");
					json.addProperty("startouterlevel", "");
					json.addProperty("stopinlandlevel", "");
					json.addProperty("stopouterlevel", "");	
				}else{
				json.addProperty("startinlandlevel", arg0.getSelfDispatchExecute().getStartinlandlevel());
				json.addProperty("startouterlevel", arg0.getSelfDispatchExecute().getStartouterlevel());
				json.addProperty("stopinlandlevel", arg0.getSelfDispatchExecute().getStopinlandlevel());
				json.addProperty("stopouterlevel", arg0.getSelfDispatchExecute().getStopouterlevel());
				}
				}else if(arg0.getDispatchtype()==0){
					json.addProperty("dispatchtype", "片区调度");	
					if(arg0.getReceiptDispatchExecute()==null){
						json.addProperty("startinlandlevel", "");
						json.addProperty("startouterlevel", "");
						json.addProperty("stopinlandlevel", "");
						json.addProperty("stopouterlevel", "");	
					}
					else{
					json.addProperty("startinlandlevel", arg0.getReceiptDispatchExecute().getStartinlandlevel());
					json.addProperty("startouterlevel", arg0.getReceiptDispatchExecute().getStartouterlevel());
					json.addProperty("stopinlandlevel", arg0.getReceiptDispatchExecute().getStopinlandlevel());
					json.addProperty("stopouterlevel", arg0.getReceiptDispatchExecute().getStopouterlevel());
				}
					}else if(arg0.getDispatchtype()==1){
					json.addProperty("dispatchtype","大包围调度");
					if(arg0.getReceiptDispatchExecute()==null){
						json.addProperty("startinlandlevel", "");
						json.addProperty("startouterlevel", "");
						json.addProperty("stopinlandlevel","");
						json.addProperty("stopouterlevel", "");	
					}else{
					json.addProperty("startinlandlevel", arg0.getReceiptDispatchExecute().getStartinlandlevel());
					json.addProperty("startouterlevel", arg0.getReceiptDispatchExecute().getStartouterlevel());
					json.addProperty("stopinlandlevel", arg0.getReceiptDispatchExecute().getStopinlandlevel());
					json.addProperty("stopouterlevel", arg0.getReceiptDispatchExecute().getStopouterlevel());
				}
					}
				return json;
			}
		}).create();
		List<Map<String,Object>> lists= new ArrayList<>();
		Map<String,Object> maps=new HashMap<>();
		maps.put("stopouterlevel", "总计:");
		//maps.put("dcscharge", "总流量:");
		maps.put("kjtime", kjtimes);
		maps.put("count", kjcounts);
		maps.put("cscharge", sumll);
		lists.add(maps);
		jsonMap.put("total", count);
		jsonMap.put("rows", list);
		jsonMap.put("footer", lists);
		strJson=gson.toJson(jsonMap);
		//System.out.println(strJson);
		return strJson;
	}
	
	@RequestMapping(value = "/station", method = RequestMethod.POST)
	@ResponseBody
	public String station() {
		String json = "";
		List<Station> list1 = receiptDispatchExecute.findStation();
		gson = new GsonBuilder().registerTypeAdapter(Station.class,
				new JsonSerializer<Station>() {
					@Override
					public JsonElement serialize(Station arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("name", arg0.getName());
						return json;
					}
				}).create();
		json = gson.toJson(list1);
		return json;
	}
	
	@RequestMapping(value="/stations",method=RequestMethod.POST)
	@ResponseBody
	public String stations(String sid){
		List<UserStatistics> stations=receiptDispatchExecute.findStations(sid);
		gson = new GsonBuilder().registerTypeAdapter(UserStatistics.class,
				new JsonSerializer<UserStatistics>() {
					@Override
					public JsonElement serialize(UserStatistics arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("designdischarge",arg0.getDesigndischarge());
						json.addProperty("motertype", arg0.getMotertype());
						json.addProperty("count", arg0.getCount());
						return json;
					}
				}).create();
		return gson.toJson(stations);
	}
	
	public void download(HttpServletRequest request,
			HttpServletResponse response, String fileaddress) {
		// 得到要下载的文件名
		fileaddress = DecoderUtil.decoder(fileaddress);
		String fileNames = fileaddress;
		//String fileName[] =fileNames.split("\\.");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String fileName=df.format(new Date())+"运用统计";
		// 获取上传文件的目录
		ServletContext sc = request.getSession().getServletContext();
		// 上传位置
		String fileSaveRootPath = sc.getRealPath("/upload");
		// 得到要下载的文件
		File file = new File(fileSaveRootPath + "\\" + fileNames);
		// 如果文件不存在
		if (!file.exists()) {
			request.setAttribute("message", "您要下载的资源已被删除！！");
			System.out.println("您要下载的资源已被删除！！");
			return;
		}

		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		try {
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ new String(fileName.getBytes("gb2312"),"iso8859-1")+".xls");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
		//	System.out.println(file.getAbsolutePath());
			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			inputStream.close();
			file.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: export
	 * @Description: TODO(导出EXECLE)
	 * @param @param month
	 * @param @param year
	 * @param @param request
	 * @param @param response 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	@ResponseBody
	public void export(String ssid,HttpServletRequest request,String dcode,String type,String begintimes,String endtimes,String dispatchtype,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String docsPath = request.getSession().getServletContext()
				.getRealPath("/upload");
		String id=UUID.randomUUID().toString();
		String fileName =id.toString()+"调度报表.xls";
		String filePath = docsPath + "\\" + fileName;
		String[] s=ssid.split(",");
		List<String> ss=new ArrayList<>();
		for(int i=0;i<s.length;i++){
			if(s[i].equals("0")||s[i].equals("")){
				ss=null;
				break;
			}else{
				ss.add(s[i]);
			}
		}
		List<UserStatistics> list = new ArrayList<>();
		map.put("type", "1");
		map.put("ssid", ss);
		if(dcode==null ||dcode==""){
			map.put("dcode", null);
		}else{
			map.put("dcode", dcode.replaceAll(" ", ""));
		}
		if(type==null||type.equals("")){
			map.put("way", null);
		}else{
			map.put("way", type);
		}
		if(begintimes==null||begintimes.trim().equals("")){
			map.put("begintime", null);
		}else{
			try {
				map.put("begintime", datetimeFormat.parse(begintimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endtimes==null||endtimes.trim().equals("")){
			map.put("endtime", null);
		}else{
			try {
				map.put("endtime", datetimeFormat.parse(endtimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//dispatchtype="1";
		if(dispatchtype.equals("1")){
			map.put("dispatchtype", "0");
		}else if(dispatchtype.equals("0")){
			map.put("dispatchtype", "1");
		}
		if(dispatchtype.equals("2")){
			list=receiptDispatchExecute.findSelfStatisticsByExport(map);
		}else{
		try {
			list=receiptDispatchExecute.findStatisticsByExport(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		WritableWorkbook book = null;

		try {
			book = Workbook.createWorkbook(new File(filePath));
			WritableSheet sheet = book.createSheet("调度报表", 0);
			Label label1 = new Label(0, 0, "调度类型");
			Label label2 = new Label(1, 0, "枢纽名称");
			Label label3 = new Label(2, 0, "开机时间");
			Label label4 = new Label(3, 0, "停机时间");
			Label label5 = new Label(4, 0, "运行时间/(时)");
			Label label6 = new Label(5, 0, "开机时内河水位");
			Label label7 = new Label(6, 0, "开机时外河水位");
			Label label11 = new Label(7, 0, "停机时内河水位");
			Label label8 = new Label(8, 0, "停机时外河水位");
			Label label9 = new Label(9, 0, "开机台数");
			Label label10 = new Label(10, 0, "开机台时/(时)");
			Label label12= new Label(11,0,"抽水流量(米³/秒)");
			Label label13=new Label(12,0,"总流量(万立方米)");
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);
			sheet.addCell(label6);
			sheet.addCell(label7);
			sheet.addCell(label8);
			sheet.addCell(label9);
			sheet.addCell(label10);
			sheet.addCell(label11);
			sheet.addCell(label12);
			sheet.addCell(label13);
			Long kjcounts=new Long(0);
			BigDecimal sumll=new BigDecimal(0);
			BigDecimal kjtimes=new BigDecimal(0);
			for (int i = 1; i < list.size() + 1; i++) {
					kjcounts+=list.get(i-1).getCount();
					if(list.get(i-1).getCscharge()==null){
						BigDecimal big=new BigDecimal(0);
						sumll=sumll.add(big);
					}else{
					sumll=sumll.add(list.get(i-1).getCscharge());
					}
					if(list.get(i-1).getKjtime()==null){
						BigDecimal kjtime=new BigDecimal(0);
						kjtimes=kjtimes.add(kjtime);
					}else{
						kjtimes=kjtimes.add(list.get(i-1).getKjtime());
					}
				String dispatchtypes="";//调度类型
				String startinlandlevel="";//开机内河水位；
				String startouterlevel="";//开机外河水位；
				String stopinlandlevel="";//停机内河水位；
				String stopouterlevel="";//停机外河水位；
				if(list.get(i-1).getDispatchtype()==null){
					dispatchtypes="自主调度";
					if(list.get(i-1).getSelfDispatchExecute()==null){
						startinlandlevel="";//开机内河水位；
						startouterlevel="";//开机外河水位；
						stopinlandlevel="";//停机内河水位；
						stopouterlevel="";//停机外河水位；
					}else{
						if(list.get(i-1).getSelfDispatchExecute().getStartinlandlevel()==null){
							startinlandlevel="";//开机内河水位；
						}else{
							startinlandlevel=list.get(i-1).getSelfDispatchExecute().getStartinlandlevel().toString();//开机内河水位；
						}if(list.get(i-1).getSelfDispatchExecute().getStartouterlevel()==null){
							startouterlevel="";//开机外河水位；
						}else{
							startouterlevel=list.get(i-1).getSelfDispatchExecute().getStartouterlevel().toString();//开机外河水位；
						}
						if(list.get(i-1).getSelfDispatchExecute().getStopinlandlevel()==null){
							stopinlandlevel="";//停机机内河水位；
						}else{
							stopinlandlevel=list.get(i-1).getSelfDispatchExecute().getStopinlandlevel().toString();//停机内河水位；
						}if(list.get(i-1).getSelfDispatchExecute().getStopouterlevel()==null){
							stopouterlevel="";//停机机外河水位；
						}else{
							stopouterlevel=list.get(i-1).getSelfDispatchExecute().getStopouterlevel().toString();//停机外河水位；	
						}
					}
					}else if(list.get(i-1).getDispatchtype()==0){
						dispatchtypes= "片区调度";	
						if(list.get(i-1).getReceiptDispatchExecute()==null){
							startinlandlevel="";//开机内河水位；
							startouterlevel="";//开机外河水位；
							stopinlandlevel="";//停机内河水位；
							stopouterlevel="";//停机外河水位；
						}
						else{
							if(list.get(i-1).getReceiptDispatchExecute().getStartinlandlevel()==null){
								startinlandlevel="";//开机内河水位；
							}else{
								startinlandlevel=list.get(i-1).getReceiptDispatchExecute().getStartinlandlevel().toString();//开机内河水位；
							}if(list.get(i-1).getReceiptDispatchExecute().getStartouterlevel()==null){
								startouterlevel="";//开机外河水位；
							}else{
								startouterlevel=list.get(i-1).getReceiptDispatchExecute().getStartouterlevel().toString();//开机外河水位；
							}
							if(list.get(i-1).getReceiptDispatchExecute().getStopinlandlevel()==null){
								stopinlandlevel="";//停机机内河水位；
							}else{
								stopinlandlevel=list.get(i-1).getReceiptDispatchExecute().getStopinlandlevel().toString();//停机内河水位；
							}if(list.get(i-1).getReceiptDispatchExecute().getStopouterlevel()==null){
								stopouterlevel="";//停机机外河水位；
							}else{
								stopouterlevel=list.get(i-1).getReceiptDispatchExecute().getStopouterlevel().toString();//停机外河水位；	
							}
					}
						}else if(list.get(i-1).getDispatchtype()==1){
							dispatchtypes="大包围调度";
						if(list.get(i-1).getReceiptDispatchExecute()==null){
							startinlandlevel="";//开机内河水位；
							startouterlevel="";//开机外河水位；
							stopinlandlevel="";//停机内河水位；
							stopouterlevel="";//停机外河水位；
						}else{
							if(list.get(i-1).getReceiptDispatchExecute().getStartinlandlevel()==null){
								startinlandlevel="";//开机内河水位；
							}else{
								startinlandlevel=list.get(i-1).getReceiptDispatchExecute().getStartinlandlevel().toString();//开机内河水位；
							}if(list.get(i-1).getReceiptDispatchExecute().getStartouterlevel()==null){
								startouterlevel="";//开机外河水位；
							}else{
								startouterlevel=list.get(i-1).getReceiptDispatchExecute().getStartouterlevel().toString();//开机外河水位；
							}
							if(list.get(i-1).getReceiptDispatchExecute().getStopinlandlevel()==null){
								stopinlandlevel="";//停机机内河水位；
							}else{
								stopinlandlevel=list.get(i-1).getReceiptDispatchExecute().getStopinlandlevel().toString();//停机内河水位；
							}if(list.get(i-1).getReceiptDispatchExecute().getStopouterlevel()==null){
								stopouterlevel="";//停机机外河水位；
							}else{
								stopouterlevel=list.get(i-1).getReceiptDispatchExecute().getStopouterlevel().toString();//停机外河水位；	
							}
					}
						}
				    String stoptime="";
					Label number1 = new Label(0, i, dispatchtypes);
					Label number2 = new Label(1, i, list.get(i - 1)
							.getSname());//枢纽名称
					Label number3 = new Label(2, i, list.get(i - 1)
							.getBegintime().replace(".0", ""));//开机时间
					if(list.get(i - 1)
							.getEndtime()==null){
						stoptime="";
					}else{
						stoptime=list.get(i - 1).getEndtime().replace(".0", "");
					}
					Label number4 = new Label(3, i,stoptime);//停机时间
					String runtime="";
					if(list.get(i - 1)
							.getRuntime()==null){
						runtime="";
					}else{
					    runtime=list.get(i - 1)
								.getRuntime().toString();
							}
					Label number5 = new Label(4, i,runtime);//运行时间
					Label number6 = new Label(5, i, startinlandlevel);//开机时内河水位
					Label number7 = new Label(6, i, startouterlevel);//开机时外河水位
					Label number8 = new Label(7, i, stopinlandlevel);//停机时内河水位
					Label number9 = new Label(8, i, stopouterlevel);//停外
					Label number10 = new Label(9, i, list.get(i - 1)
							.getCount().toString());//开机台数
					String kjtime="";
					if(list.get(i - 1).getKjtime()==null){
						kjtime="";
					}else{
						kjtime=list.get(i - 1).getKjtime().toString();
					}
					Label number11 = new Label(10, i,kjtime);//开机台时间
					String dcscharge="";
					if(list.get(i - 1).getDcscharge()==null){
						dcscharge="";
					}else{
						dcscharge=list.get(i - 1).getDcscharge().toString();
					}
					Label number12 = new Label(11, i, dcscharge);//抽水流量
					String cscharge="";
					if(list.get(i - 1).getCscharge()==null){
						cscharge="";
					}else{
						cscharge=list.get(i - 1).getCscharge().toString();
					}
					Label number13 = new Label(12, i, cscharge);//总流量
					sheet.addCell(number1);
					sheet.addCell(number2);
					sheet.addCell(number3);
					sheet.addCell(number4);
					sheet.addCell(number5);
					sheet.addCell(number6);
					sheet.addCell(number7);
					sheet.addCell(number8);
					sheet.addCell(number9);
					sheet.addCell(number10);
					sheet.addCell(number11);
					sheet.addCell(number12);
					sheet.addCell(number13);	
				}
				Label number1 = new Label(0, list.size()+1, "");
				Label number2 = new Label(1, list.size()+1, "");//枢纽名称
				Label number3 = new Label(2, list.size()+1, "");//开机时间
				Label number4 = new Label(3, list.size()+1, "");//停机时间
				Label number5 = new Label(4, list.size()+1,"");//运行时间
				Label number6 = new Label(5, list.size()+1, "");//开机时内河水位
				Label number7 = new Label(6, list.size()+1, "");//开机时外河水位
				Label number8 = new Label(7, list.size()+1, "");//停机时内河水位
				Label number9 = new Label(8, list.size()+1, "总计");//停外
				Label number10 = new Label(9, list.size()+1, kjcounts.toString());//开机台数
				Label number11 = new Label(10, list.size()+1,kjtimes.toString());//开机台时间
				Label number12 = new Label(11, list.size()+1, "");//抽水流量
				Label number13 = new Label(12, list.size()+1, sumll.toString());//总流量
				sheet.addCell(number1);
				sheet.addCell(number2);
				sheet.addCell(number3);
				sheet.addCell(number4);
				sheet.addCell(number5);
				sheet.addCell(number6);
				sheet.addCell(number7);
				sheet.addCell(number8);
				sheet.addCell(number9);
				sheet.addCell(number10);
				sheet.addCell(number11);
				sheet.addCell(number12);
				sheet.addCell(number13);
			book.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				book.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		download(request, response, fileName);
	}
	
}
