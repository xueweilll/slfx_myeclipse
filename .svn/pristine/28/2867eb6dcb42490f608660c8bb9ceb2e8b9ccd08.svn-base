package com.benqzl.controller.dispatch.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.benqzl.pojo.dispatch.ReceiptDispatchExecuteGate;
import com.benqzl.pojo.dispatch.SelfDispatchExecuteGate;
import com.benqzl.service.dispatch.DispatchGateRunService;
import com.benqzl.unit.DecoderUtil;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Controller
@RequestMapping("dispatchgaterun")
public class DispatchGateRunTotalController extends BasicController{

	public DispatchGateRunTotalController() {
		super(DispatchGateRunTotalController.class);
	}
	
	/**
	 * @Title: callback
	 * @Description: 回访(这里用一句话描述这个方法的作用)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView callback() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dispatch/dispatchGateRunTotal");
		return mv;
	}
	
	@Autowired
	private DispatchGateRunService dispatchGateRunService;
	
	@RequestMapping(value = "pageBind", method = RequestMethod.POST)
	@ResponseBody
	public String pageBind(HttpServletRequest request,int page, int rows, String sids,String begintimes,String endtimes,String index){
		String strJson = "";
		HashMap<String,Object> map = new HashMap<String, Object>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		map.put("p1", start);
		map.put("p2", rows);
		if(sids != null){
			String[] s=sids.split(",");
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
			System.out.println(ss);
		}
		
		if(begintimes != null && !begintimes.equals("")){
			try {
				map.put("begintime", datetimeFormat.parse(begintimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endtimes != null && !endtimes.equals("")){
			try {
				map.put("endtime", datetimeFormat.parse(endtimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(!index.equals("2")){
			if(index.equals("0")){
				map.put("type", 1);
			}else{
				map.put("type", 0);
			}
			List<ReceiptDispatchExecuteGate> list = dispatchGateRunService.selectByTotal(map);
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			int total = dispatchGateRunService.selectByCount(map);
			jsonMap.put("total", total);
			jsonMap.put("rows", list);
			gson = new GsonBuilder().registerTypeAdapter(ReceiptDispatchExecuteGate.class,
					new JsonSerializer<ReceiptDispatchExecuteGate>() {
						@Override
						public JsonElement serialize(ReceiptDispatchExecuteGate src,
								Type typeOfSrc, JsonSerializationContext context) {
							JsonObject o = new JsonObject();
							if( src.getExecute() != null && src.getExecute().getStation() != null){
								o.addProperty("stationname", src.getExecute().getStation().getName());
							}
							if(src.getGate() != null){
								o.addProperty("gatename", src.getGate().getName());
							}
	
							if(src.getOperatetime() != null){
								o.addProperty("operatetime", datetimeFormat.format(src.getOperatetime()));
							}
							
							if (src.getOperate() == 0) {
								o.addProperty("operate", "关闸");
							}else{
								o.addProperty("operate", "开闸");
							}
							return o;
						}
					}).create();
			strJson = gson.toJson(jsonMap);
		}else {
			List<SelfDispatchExecuteGate> list = dispatchGateRunService.findPageByTotal(map);
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			int total = dispatchGateRunService.findPageByCount(map);
			jsonMap.put("total", total);
			jsonMap.put("rows", list);
			gson = new GsonBuilder().registerTypeAdapter(SelfDispatchExecuteGate.class,
					new JsonSerializer<SelfDispatchExecuteGate>() {
						@Override
						public JsonElement serialize(SelfDispatchExecuteGate src,
								Type typeOfSrc, JsonSerializationContext context) {
							JsonObject o = new JsonObject();
							if( src.getExecute() != null && src.getExecute().getS() != null){
								o.addProperty("stationname", src.getExecute().getS().getName());
							}
							if(src.getGate() != null){
								o.addProperty("gatename", src.getGate().getName());
							}
	
							if(src.getOperatetime() != null){
								o.addProperty("operatetime", datetimeFormat.format(src.getOperatetime()));
							}
							
							if (src.getOperate() == 0) {
								o.addProperty("operate", "关闸");
							}else{
								o.addProperty("operate", "开闸");
							}
							return o;
						}
					}).create();
			strJson = gson.toJson(jsonMap);
	
			
		}
		return strJson;
	}

	
	@RequestMapping(value = "print", method = RequestMethod.GET)
	public ModelAndView print( String sids,String begintimes,String endtimes,String index) {
		ModelAndView mv = new ModelAndView();
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(sids != null){
			String[] s=sids.split(",");
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
		}
		
		if(begintimes != null && !begintimes.equals("")){
			try {
				map.put("begintime", datetimeFormat.parse(begintimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endtimes != null && !endtimes.equals("")){
			try {
				map.put("endtime", datetimeFormat.parse(endtimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(!index.equals("2")){
			if(index.equals("0")){
				map.put("type", 1);
			}else if(index.equals("1")){
				map.put("type", 0);
			}
		
			List<ReceiptDispatchExecuteGate> list = dispatchGateRunService.selectByTotalPrint(map);
			gson = new GsonBuilder().registerTypeAdapter(ReceiptDispatchExecuteGate.class,
					new JsonSerializer<ReceiptDispatchExecuteGate>() {
						@Override
						public JsonElement serialize(ReceiptDispatchExecuteGate src,
								Type typeOfSrc, JsonSerializationContext context) {
							JsonObject o = new JsonObject();
							if( src.getExecute() != null && src.getExecute().getStation() != null){
								o.addProperty("stationname", src.getExecute().getStation().getName());
							}
							if(src.getGate() != null){
								o.addProperty("gatename", src.getGate().getName());
							}
	
							if(src.getOperatetime() != null){
								o.addProperty("operatetime", datetimeFormat.format(src.getOperatetime()));
							}
							
							if (src.getOperate() == 0) {
								o.addProperty("operate", "关闸");
							}else{
								o.addProperty("operate", "开闸");
							}
							return o;
						}
					}).create();
			mv.addObject("type",index);
			mv.addObject("firstDemo", gson.toJson(list));
		}else {
			List<SelfDispatchExecuteGate> list = dispatchGateRunService.findPageByTotalPrint(map);
			gson = new GsonBuilder().registerTypeAdapter(SelfDispatchExecuteGate.class,
					new JsonSerializer<SelfDispatchExecuteGate>() {
						@Override
						public JsonElement serialize(SelfDispatchExecuteGate src,
								Type typeOfSrc, JsonSerializationContext context) {
							JsonObject o = new JsonObject();
							if( src.getExecute() != null && src.getExecute().getS() != null){
								o.addProperty("stationname", src.getExecute().getS().getName());
							}
							if(src.getGate() != null){
								o.addProperty("gatename", src.getGate().getName());
							}
	
							if(src.getOperatetime() != null){
								o.addProperty("operatetime", datetimeFormat.format(src.getOperatetime()));
							}
							
							if (src.getOperate() == 0) {
								o.addProperty("operate", "关闸");
							}else{
								o.addProperty("operate", "开闸");
							}
							return o;
						}
					}).create();
			mv.addObject("type",index);
			mv.addObject("firstDemo", gson.toJson(list));
		}
		mv.setViewName("dispatch/dispatchGateRunTotalPrint");
		return mv;
	}
	
	@RequestMapping(value = "export", method = RequestMethod.GET)
	@ResponseBody
	public void export( String sids,String begintimes,String endtimes,String index, 
			HttpServletRequest request,HttpServletResponse response) {
		String docsPath = request.getSession().getServletContext()
				.getRealPath("/upload");
		String id=UUID.randomUUID().toString();
		String fileName =id+"闸门统计.xls";
		String filePath = docsPath + "\\" + fileName;
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		if(sids != null){
			String[] s=sids.split(",");
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
		}
		
		if(begintimes != null && !begintimes.equals("")){
			try {
				map.put("begintime", datetimeFormat.parse(begintimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endtimes != null && !endtimes.equals("")){
			try {
				map.put("endtime", datetimeFormat.parse(endtimes));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(index.equals("2")){// 
			List<SelfDispatchExecuteGate> list = dispatchGateRunService.findPageByTotalPrint(map);
			WritableWorkbook book = null;
			try {
				book = Workbook.createWorkbook(new File(filePath));
				WritableSheet sheet = book.createSheet("闸门统计", 0);
				Label label1 = new Label(0, 0, "类型");
				Label label2 = new Label(1, 0, "枢纽名称");
				Label label3 = new Label(2, 0, "闸门名称");
				Label label4 = new Label(3, 0, "操作时间");
				Label label5 = new Label(4, 0, "操作");
				sheet.addCell(label1);
				sheet.addCell(label2);
				sheet.addCell(label3);
				sheet.addCell(label4);
				sheet.addCell(label5);
				for (int i = 1; i < list.size() + 1; i++) {
						Label number1 = new Label(0, i, "自主调度");//调度类型
						String sn="";
						if(list.get(i - 1).getExecute() == null || list.get(i - 1).getExecute().getS() == null){//发起时间
							sn="";
						}else{
							sn=list.get(i - 1).getExecute().getS().getName();	
						}
						Label number2 = new Label(1, i, sn);
						String zm="";
						if(list.get(i - 1).getGate() == null){//调度发起人
							zm="";	
						}else{
							zm=list.get(i - 1).getGate().getName();
						}
						Label number3=new Label(2,i,zm);
						String czsj="";
						if(list.get(i - 1).getOperatetime() == null){//分解人
							czsj="";
						}else{
							czsj=datetimeFormat.format(list.get(i - 1).getOperatetime());	
						}
						Label number4=new Label(3,i,czsj);
						String cz="";
						if(list.get(i - 1).getOperate()==null){//分解时间
							cz="";
						}else if (list.get(i - 1).getOperate() == 0) {
							cz= "关闸";
						}else{
							cz="开闸";
						}
						Label number5=new Label(4,i,cz);
						sheet.addCell(number1);
						sheet.addCell(number2);
						sheet.addCell(number3);
						sheet.addCell(number4);
						sheet.addCell(number5);
					}
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
		}else {
			if(index.equals("0")){
				map.put("type", 1);
			}else if(index.equals("1")){
				map.put("type", 0);
			}
			List<ReceiptDispatchExecuteGate> list = dispatchGateRunService.selectByTotalPrint(map);
			WritableWorkbook book = null;
			try {
				book = Workbook.createWorkbook(new File(filePath));
				WritableSheet sheet = book.createSheet("闸门统计", 0);
				Label label1 = new Label(0, 0, "类型");
				Label label2 = new Label(1, 0, "枢纽名称");
				Label label3 = new Label(2, 0, "闸门名称");
				Label label4 = new Label(3, 0, "操作时间");
				Label label5 = new Label(4, 0, "操作");
				sheet.addCell(label1);
				sheet.addCell(label2);
				sheet.addCell(label3);
				sheet.addCell(label4);
				sheet.addCell(label5);
				for (int i = 1; i < list.size() + 1; i++) {
					String n = "";
					if(index.equals("0")){
						n = "大包围调度";
					}else{
						n="片区调度";
					}
					Label number1 = new Label(0, i, n);//调度类型
					String sn="";
					if(list.get(i - 1).getExecute() == null || list.get(i - 1).getExecute().getStation() == null){//发起时间
						sn="";
					}else{
						sn=list.get(i - 1).getExecute().getStation().getName();	
					}
					Label number2 = new Label(1, i, sn);
					String zm="";
					if(list.get(i - 1).getGate() == null){//调度发起人
						zm="";	
					}else{
						zm=list.get(i - 1).getGate().getName();
					}
					Label number3=new Label(2,i,zm);
					String czsj="";
					if(list.get(i - 1).getOperatetime() == null){//分解人
						czsj="";
					}else{
						czsj=datetimeFormat.format(list.get(i - 1).getOperatetime());	
					}
					Label number4=new Label(3,i,czsj);
					String cz="";
					if(list.get(i - 1).getOperate()==null){//分解时间
						cz="";
					}else if (list.get(i - 1).getOperate() == 0) {
						cz= "关闸";
					}else{
						cz="开闸";
					}
					Label number5=new Label(4,i,cz);
					sheet.addCell(number1);
					sheet.addCell(number2);
					sheet.addCell(number3);
					sheet.addCell(number4);
					sheet.addCell(number5);
				}
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
	
	public void download(HttpServletRequest request,
			HttpServletResponse response, String fileaddress) {
		// 得到要下载的文件名
		fileaddress = DecoderUtil.decoder(fileaddress);
		String fileNames = fileaddress;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");//设置日期格式
		String fileName=df.format(new Date())+"闸门统计";
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
}
