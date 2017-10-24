package com.benqzl.controller.dispatch.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
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
import com.benqzl.pojo.dispatch.ReceiptDispatch;
import com.benqzl.pojo.dispatch.ReceiptDispatchTotal;
import com.benqzl.pojo.dispatch.SelfDispatch;
import com.benqzl.service.dispatch.DispatchIssuedListService;
import com.benqzl.service.dispatch.ReceiptDispatchService;
import com.benqzl.service.dispatch.SelfDispatchStationsService;
import com.benqzl.unit.DecoderUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Controller
@RequestMapping("exertionrecord")
public class ExertionRecordController extends BasicController {

	public ExertionRecordController() {
		super(ExertionRecordController.class);
	}
	

	@Autowired
	private ReceiptDispatchService receiptDispatchService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dispatch/exertionrecord");
		return mv;
	}	
	
	@RequestMapping(value="exertionrecordInfo",method=RequestMethod.GET)
	public ModelAndView exertionrecordInfo(String id,String type){
		ModelAndView mv = new ModelAndView();
		mv.addObject("id",id);
		mv.addObject("type",type);
		mv.setViewName("dispatch/exertionrecordInfo");
		return mv;
	}
	
	@Autowired
	private DispatchIssuedListService dispatchIssuedListService;
	
	@Autowired
	private SelfDispatchStationsService selfDispatchStationsService;
	
	
	@RequestMapping(value = "print", method = RequestMethod.GET)
	public ModelAndView print(String code,String launchBegintime,String launchEndtime,String launcher,String typeData) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		gson = new Gson();
		if(typeData.equals("2")){//  
			//map.put("state",7);//自主完成
			map.put("state", null);//全部状态
			if(code != null){
				map.put("code", code);
			}
			if(launcher != null){
				map.put("promoter", launcher);
			}
			try{
				 
				if(launchBegintime != null && launchBegintime.length() > 0){
					map.put("begintime", launchBegintime);
				}
				if(launchEndtime != null && launchEndtime.length() > 0){
					map.put("endtime", launchEndtime);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			List<SelfDispatch> list = dispatchIssuedListService.findByReceiptPagePrint(map);
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("rows", list);
			gson = new GsonBuilder().registerTypeAdapter(SelfDispatch.class,
					new JsonSerializer<SelfDispatch>() {	
						@Override
						public JsonElement serialize(SelfDispatch src,
								Type typeOfSrc, JsonSerializationContext context) {
							JsonObject o = new JsonObject();
							o.addProperty("id", src.getId());
							o.addProperty("code", src.getCode());
							o.addProperty("dispatchtype", "自主调度");
							o.addProperty("launchTime",datetimeFormat.format(src.getPromotetime()));
							o.addProperty("promoter", src.getUser().getUsername());
							if(src.getLdApproval() != null){
								o.addProperty("ldapproval", src.getLdApproval().getUsername());
							}
							if(src.getLdapprovaltime() != null){
								o.addProperty("ldapprovaltime",datetimeFormat.format(src.getLdapprovaltime()));
							}
							o.addProperty("creater", src.getCreateUser().getUsername());
							o.addProperty("createtime",datetimeFormat.format(src.getCreatetime()));
														
							/*if(src.getSendUser() != null){
								o.addProperty("sender", src.getSendUser().getUsername());
							}
							if(src.getSendtime() != null){
								o.addProperty("sendTime",	datetimeFormat.format(src.getSendtime()));
							}else {
								o.addProperty("sendertime","");
							}*/
							o.addProperty("sender", src.getDepartmentnames());
							o.addProperty("sendTime", src.getDepartmenttimes());
							o.addProperty("state", src.getState());	
							return o;
						}
					}).create();
			mv.addObject("type",typeData);
			mv.addObject("firstDemo", gson.toJson(list));
			mv.setViewName("dispatch/exertionrecordPrint");
			return mv;
		}
		else {		
			if(typeData.equals("1")){
				map.put("type", "0");
				map.put("state", null);//查询片区全部调度
				//map.put("state",3);//调度完成
			}
			else {
				map.put("type", "1");
				map.put("state", null);
				map.put("trstate", null);//查询大包围全部数据
				//map.put("state",3);
				//map.put("trstate",4);
			}
			if(code != null){
				map.put("code", code);
			}
			if(launcher != null){
				map.put("launcher", launcher);
			}
			try{
				 SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
				if(launchBegintime != null && launchBegintime.length() > 0){
					map.put("launchBegintime", sdf.parse(launchBegintime));
				}
				if(launchEndtime != null && launchEndtime.length() > 0){
					map.put("launchEndtime", sdf.parse(launchEndtime));
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			List<ReceiptDispatch> list = receiptDispatchService.findRdSendPrint(map);
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("rows", list);
			gson = new GsonBuilder().registerTypeAdapter(ReceiptDispatch.class,
					new JsonSerializer<ReceiptDispatch>() {	
						@Override
						public JsonElement serialize(ReceiptDispatch src,
								Type typeOfSrc, JsonSerializationContext context) {
							JsonObject o = new JsonObject();
								o.addProperty("id", src.getId());
								o.addProperty("code", src.getCode());
								if ("0".equals(src.getDispatchtype())) {
									o.addProperty("dispatchtype", "片区调度");
								} else {
									o.addProperty("dispatchtype", "大包围调度");
								}
								o.addProperty("launcher", src.getReceipt()
										.getLauncher());
								o.addProperty("launchTime", datetimeFormat.format(src
										.getReceipt().getLaunchtime()));
								o.addProperty("endTime", datetimeFormat.format(src
										.getReceipt().getEndtime()));
								o.addProperty("receipter", src.getNames()
										.getReceiptName());
								o.addProperty("receipteTime", datetimeFormat.format(src
										.getReceipt().getReceiptetime()));
								o.addProperty("memo", src.getReceipt().getMemo());
								o.addProperty("creater", src.getNames().getCreateName());
								o.addProperty("createtime",
										datetimeFormat.format(src.getCreatetime()));
								/*o.addProperty("sender", src.getNames()
										.getSendUserName());
								if (src.getSendtime() != null) {
									o.addProperty("sendTime",
											datetimeFormat.format(src.getSendtime()));
								}*/
								o.addProperty("transmitName", src.getNames().getTransmitName());
								if(src.getTransmittime() != null){
									o.addProperty("transmittime", datetimeFormat.format(src.getTransmittime()));
								}
								o.addProperty("stateName", "完成");
								if(src.getNames() != null){
									o.addProperty("sender", src.getNames().getDepartmentnames());
									o.addProperty("sendTime",src.getNames().getDepartmenttimes());
									o.addProperty("handlername", src.getNames().getHandlerName());
								}
								if(src.getReceipt() != null){
									if(src.getReceipt().getHandletime() != null){
										o.addProperty("handletime",datetimeFormat.format(src.getReceipt().getHandletime()));
									}
								} 
							
							return o;
						}
					}).create();
		mv.addObject("type",typeData);
		mv.addObject("firstDemo", gson.toJson(list));
		mv.setViewName("dispatch/exertionrecordPrint");
		return mv;
	}
	}
	public void download(HttpServletRequest request,
			HttpServletResponse response, String fileaddress) {
		// 得到要下载的文件名
		fileaddress = DecoderUtil.decoder(fileaddress);
		String fileNames = fileaddress;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String fileName=df.format(new Date())+"运用记录报表";
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
	public void export(String code,String launchBegintime,String launchEndtime,String launcher,String typeData,
			HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String docsPath = request.getSession().getServletContext()
				.getRealPath("/upload");
		String id=UUID.randomUUID().toString();
		String fileName =id+"运用记录报表.xls";

		String filePath = docsPath + "\\" + fileName;
		if(typeData.equals("2")){//  
			//map.put("state",7);//片区完成
			map.put("state", null);//全部状态
			if(code != null){
				map.put("code", code);
			}
			if(launcher != null){
				map.put("promoter", launcher);
			}
			try{
				 
				if(launchBegintime != null && launchBegintime.length() > 0){
					map.put("begintime", launchBegintime);
				}
				if(launchEndtime != null && launchEndtime.length() > 0){
					map.put("endtime", launchEndtime);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			List<SelfDispatch> list = dispatchIssuedListService.findByReceiptPagePrint(map);
		WritableWorkbook book = null;
		try {
			book = Workbook.createWorkbook(new File(filePath));
			WritableSheet sheet = book.createSheet("运用记录报表", 0);
			Label label1 = new Label(0, 0, "类型");
			Label label2 = new Label(1, 0, "发起时间");
			Label label3 = new Label(2, 0, "调度发起人");
			Label label4 = new Label(3, 0, "领导审批人");
			Label label5 = new Label(4, 0, "领导审批时间");
			/*Label label6 = new Label(5, 0, "部门执行人");
			Label label7 = new Label(6, 0, "部门执行时间");*/
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);
			/*sheet.addCell(label6);
			sheet.addCell(label7);*/
			for (int i = 1; i < list.size() + 1; i++) {
					Label number1 = new Label(0, i, "自主调度");//调度类型
					String promotetime="";
					if(list.get(i - 1).getPromotetime()==null){//发起时间
						promotetime="";
					}else{
						promotetime=datetimeFormat.format(list.get(i - 1).getPromotetime());	
					}
					Label number2 = new Label(1, i, promotetime);
					String username="";
					if(list.get(i - 1).getUser().getUsername()==null){//调度发起人
						username="";	
					}else{
						username=list.get(i - 1).getUser().getUsername();	
					}
					Label number3=new Label(2,i,username);
					String fj="";
					if(list.get(i - 1).getLdApproval() == null || list.get(i - 1).getLdApproval().getUsername()==null){//分解人
						fj="";
					}else{
						fj=list.get(i - 1).getLdApproval().getUsername();	
					}
					Label number4=new Label(3,i,fj);
					String fjtime="";
					if(list.get(i - 1).getLdapprovaltime()==null){//分解时间
						fjtime="";
					}else{
						fjtime=datetimeFormat.format(list.get(i - 1).getLdapprovaltime());	
					}
					Label number5=new Label(4,i,fjtime);
                  /*  String zxr="";
					if(list.get(i-1).getDepartmentnames()==null){//执行人
                    	zxr="";
                    }else{
                    	zxr=list.get(i-1).getDepartmentnames();
                    }
					Label number6=new Label(5,i,zxr);
					//执行时间
					String zxtime="";
					if(list.get(i-1).getDepartmenttimes()==null){
						zxtime="";
					}else{
						zxtime=list.get(i-1).getDepartmenttimes();
					}
					Label number7 =new Label(6,i,zxtime);*/
					sheet.addCell(number1);
					sheet.addCell(number2);
					sheet.addCell(number3);
					sheet.addCell(number4);
					sheet.addCell(number5);
					/*sheet.addCell(number6);
					sheet.addCell(number7);*/
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
	}else{
		if(typeData.equals("1")){
			map.put("type", "0");
			map.put("state", null);//查询片区全部调度
			//map.put("state",3);//调度完成
		}
		else {
			map.put("type", "1");
			map.put("state", null);
			map.put("trstate", null);//查询大包围全部数据
			//map.put("state",3);
			//map.put("trstate",4);
		}
		if(code != null){
			map.put("code", code);
		}
		if(launcher != null){
			map.put("launcher", launcher);
		}
		try{
			 SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			if(launchBegintime != null && launchBegintime.length() > 0){
				map.put("launchBegintime", sdf.parse(launchBegintime));
			}
			if(launchEndtime != null && launchEndtime.length() > 0){
				map.put("launchEndtime", sdf.parse(launchEndtime));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		List<ReceiptDispatch> list = receiptDispatchService.findRdSendPrint(map);
		WritableWorkbook book = null;
			try {
				book = Workbook.createWorkbook(new File(filePath));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			WritableSheet sheet = book.createSheet("运用记录报表", 0);
			if(typeData.equals("1")){//片区
				try {	
				Label label1 = new Label(0, 0, "类型");
				Label label2 = new Label(1, 0, "发起时间");
				Label label3 = new Label(2, 0, "发令人");
				Label label4 = new Label(3, 0, "接收人");
				Label label7 = new Label(4, 0, "签发人");
				Label label8 = new Label(5, 0, "签发时间");
				/*Label label5 = new Label(6, 0, "执行人");
				Label label6 = new Label(7, 0, "执行时间");*/
				
				sheet.addCell(label1);
				sheet.addCell(label2);
				sheet.addCell(label3);
				sheet.addCell(label4);
			/*	sheet.addCell(label5);
				sheet.addCell(label6);*/
				sheet.addCell(label7);
				sheet.addCell(label8);
				for (int i = 1; i < list.size() + 1; i++) {
					Label number1 = new Label(0, i, "片区调度");//调度类型
					String Launchtime="";
					if(list.get(i - 1).getReceipt().getLaunchtime()==null){//发起时间
						Launchtime="";
					}else{
						Launchtime=datetimeFormat.format(list.get(i - 1).getReceipt().getLaunchtime());	
					}
					Label number2 = new Label(1, i, Launchtime);
					String flr="";
					if(list.get(i - 1).getReceipt()
							.getLauncher()==null){//发令人
						flr="";	
					}else{
						flr=list.get(i - 1).getReceipt()
								.getLauncher();	
					}
					Label number3=new Label(2,i,flr);
					String jsr="";
					if(list.get(i - 1).getNames()
							.getReceiptName()==null){//接收人
						jsr="";
					}else{
						jsr=list.get(i - 1).getNames()
								.getReceiptName();	
					}					
					Label number4=new Label(3,i,jsr);
					String qsr = "";
					if(list.get(i - 1).getNames() == null || list.get(i - 1).getNames().getHandlerName()==null){
						qsr="";
					}else {
						qsr=list.get(i - 1).getNames().getHandlerName();
					}
					Label number7=new Label(4,i,qsr);
					String qssj="";
					if(list.get(i - 1).getReceipt() == null || list.get(i - 1).getReceipt().getHandletime()==null){
						qssj="";
					}else {
						qssj=datetimeFormat.format(list.get(i - 1).getReceipt().getHandletime());
					}
					Label number8=new Label(5,i,qssj);
	/*				String zxr="";
					if(list.get(i - 1).getNames() == null || list.get(i - 1).getNames().getDepartmentnames()==null){//执行人
						zxr="";
					}else{
						zxr=list.get(i - 1).getNames()
								.getDepartmentnames();	
					}
					Label number5=new Label(6,i,zxr);
					//执行时间
					String zxtime="";
					if(list.get(i-1).getNames() == null || list.get(i-1).getNames().getDepartmenttimes()==null){
						zxtime="";
					}else{
						zxtime=list.get(i-1).getNames().getDepartmenttimes();
					}
					Label number6 =new Label(7,i,zxtime);*/
					sheet.addCell(number1);
					sheet.addCell(number2);
					sheet.addCell(number3);
					sheet.addCell(number4);
					sheet.addCell(number7);
					sheet.addCell(number8);
					/*sheet.addCell(number5);
					sheet.addCell(number6);*/
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
			}else{
				try {	
				Label label1 = new Label(0, 0, "类型");
				Label label2 = new Label(1, 0, "发起时间");
				Label label3 = new Label(2, 0, "发令人");
				Label label4 = new Label(3, 0, "接收人");
				Label label9 = new Label(4, 0, "签发人");
				Label label10 = new Label(5, 0, "签发时间");
				/*Label label5 = new Label(6, 0, "执行人");
				Label label6 = new Label(7, 0, "执行时间");*/
				Label label7 = new Label(6, 0, "转发人");
				Label label8 = new Label(7, 0, "转发时间");
				sheet.addCell(label1);
				sheet.addCell(label2);
				sheet.addCell(label3);
				sheet.addCell(label4);
				/*sheet.addCell(label5);
				sheet.addCell(label6);*/
				sheet.addCell(label7);
				sheet.addCell(label8);
				sheet.addCell(label9);
				sheet.addCell(label10);
				for (int i = 1; i < list.size() + 1; i++) {
					Label number1 = new Label(0, i, "大包围调度");//调度类型
					String Launchtime="";
					if(list.get(i - 1).getReceipt().getLaunchtime()==null){//发起时间
						Launchtime="";
					}else{
						Launchtime=datetimeFormat.format(list.get(i - 1)
								.getReceipt().getLaunchtime());	
					}
					Label number2 = new Label(1, i, Launchtime);
					String flr="";
					if(list.get(i - 1).getReceipt()
							.getLauncher()==null){//发令人
						flr="";	
					}else{
						flr=list.get(i - 1).getReceipt()
								.getLauncher();	
					}
					Label number3=new Label(2,i,flr);
					String jsr="";
					if(list.get(i - 1).getNames()
							.getReceiptName()==null){//接收人
						jsr="";
					}else{
						jsr=list.get(i - 1).getNames()
								.getReceiptName();	
					}
					Label number4=new Label(3,i,jsr);
					
					String qsr = "";
					if(list.get(i - 1).getNames() == null || list.get(i - 1).getNames().getHandlerName()==null){
						qsr="";
					}else {
						qsr=list.get(i - 1).getNames().getHandlerName();
					}
					Label number7=new Label(4,i,qsr);
					String qssj="";
					if(list.get(i - 1).getReceipt() == null || list.get(i - 1).getReceipt().getHandletime()==null){
						qssj="";
					}else {
						qssj=datetimeFormat.format(list.get(i - 1).getReceipt().getHandletime());
					}
					Label number8=new Label(5,i,qssj);
					
				/*	String zxr="";
					if(list.get(i - 1).getNames() == null || list.get(i - 1).getNames().getDepartmentnames()==null){//执行人
						zxr="";
					}else{
						zxr=list.get(i - 1).getNames()
								.getDepartmentnames();	
					}
					Label number5=new Label(6,i,zxr);
					//执行时间
					String zxtime="";
					if(list.get(i-1).getNames() == null || list.get(i-1).getNames().getDepartmenttimes()==null){
						zxtime="";
					}else{
						zxtime=list.get(i-1).getNames().getDepartmenttimes();
					}
					Label number6 =new Label(7,i,zxtime);*/
					String zfr="";
					if(list.get(i-1).getNames().getTransmitName()==null){//转发人
						zfr="";
					}else{
						zfr=list.get(i-1).getNames().getTransmitName();
					}
					Label number9 =new Label(6,i,zfr);
					String zftime="";
					if(list.get(i-1).getTransmittime()==null){//转发时间
						zftime="";
					}else{
						zftime=datetimeFormat.format(list.get(i-1).getTransmittime());
					}
					Label number10=new Label(7,i,zftime);
					sheet.addCell(number1);
					sheet.addCell(number2);
					sheet.addCell(number3);
					sheet.addCell(number4);				
					sheet.addCell(number7);
					sheet.addCell(number8);
					/*sheet.addCell(number5);
					sheet.addCell(number6);*/
					sheet.addCell(number9);
					sheet.addCell(number10);
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
			}

		download(request, response, fileName);
	}
	
	}
	
	
	
	
	/**
	 * @Title: search
	 * @Description: TODO(查询文件)
	 * @param @param page
	 * @param @param rows
	 * @param @param jsonStr
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/pageBind", method = RequestMethod.POST)
	@ResponseBody
	public String search(HttpServletRequest request,int page, int rows, String code,String launchBegintime,String launchEndtime,String launcher,String typeData) {
		String strJson = "";
		Map<String, Object> map = new HashMap<String, Object>();
		gson = new Gson();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		
		if(typeData.equals("2")){//  
			//map.put("state",7);//片区完成
			map.put("state", null);//全部状态
			if(code != null){
				map.put("code", code);
			}
			if(launcher != null){
				map.put("promoter", launcher);
			}
			try{
				 
				if(launchBegintime != null && launchBegintime.length() > 0){
					map.put("begintime", launchBegintime);
				}
				if(launchEndtime != null && launchEndtime.length() > 0){
					map.put("endtime", launchEndtime);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			List<SelfDispatch> list = dispatchIssuedListService.findByPage(map);
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			int total = dispatchIssuedListService.pageCount(map);
			jsonMap.put("total", total);
			jsonMap.put("rows", list);
			gson = new GsonBuilder().registerTypeAdapter(SelfDispatch.class,
					new JsonSerializer<SelfDispatch>() {	
						@Override
						public JsonElement serialize(SelfDispatch src,
								Type typeOfSrc, JsonSerializationContext context) {
							JsonObject o = new JsonObject();
							o.addProperty("id", src.getId());
							o.addProperty("code", src.getCode());
							o.addProperty("dispatchtype", "自主调度");
							o.addProperty("launchTime",datetimeFormat.format(src.getPromotetime()));
							o.addProperty("promoter", src.getUser().getUsername());
							if(src.getLdApproval() != null){
								o.addProperty("ldapproval", src.getLdApproval().getUsername());
							}
							if(src.getLdapprovaltime() != null){
								o.addProperty("ldapprovaltime",datetimeFormat.format(src.getLdapprovaltime()));
							}
							o.addProperty("creater", src.getCreateUser().getUsername());
							o.addProperty("createtime",datetimeFormat.format(src.getCreatetime()));
							
							/*if(src.getSendUser() != null){
								o.addProperty("sender", src.getSendUser().getUsername());
							}
							if(src.getSendtime() != null){
								o.addProperty("sendTime",	datetimeFormat.format(src.getSendtime()));
							}else {
								o.addProperty("sendertime","");
							}*/
							o.addProperty("sender", src.getDepartmentnames());
							o.addProperty("sendTime", src.getDepartmenttimes());
							o.addProperty("state", src.getState());	
							return o;
						}
					}).create();
			strJson = gson.toJson(jsonMap);
			logger.info("this document list strJson is " + strJson);
			return strJson;
		}
		else {		
			try {
			if(typeData.equals("1")){
				map.put("type", "0");
				map.put("state", null);//查询片区全部调度
				//map.put("state",3);//调度完成
			}
			else {
				map.put("type", "1");
				map.put("state", null);
				map.put("trstate", null);//查询大包围全部数据
				//map.put("state",3);
				//map.put("trstate",4);
			}
			if(code != null){
				map.put("code", code);
			}
			if(launcher != null){
				map.put("launcher", launcher);
			}
			try{
				 SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
				if(launchBegintime != null && launchBegintime.length() > 0){
					map.put("launchBegintime", sdf.parse(launchBegintime));
				}
				if(launchEndtime != null && launchEndtime.length() > 0){
					map.put("launchEndtime", sdf.parse(launchEndtime));
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			List<ReceiptDispatch> list = receiptDispatchService.findRdSendList(map);
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			int total = receiptDispatchService.findRdSendCount(map);
			jsonMap.put("total", total);
			jsonMap.put("rows", list);
			
			
				gson = new GsonBuilder().registerTypeAdapter(ReceiptDispatch.class,
						new JsonSerializer<ReceiptDispatch>() {	
							@Override
							public JsonElement serialize(ReceiptDispatch src,
									Type typeOfSrc, JsonSerializationContext context) {
								JsonObject o = new JsonObject();
									o.addProperty("id", src.getId());
									o.addProperty("code", src.getCode());
									if ("0".equals(src.getDispatchtype())) {
										o.addProperty("dispatchtype", "片区调度");
									} else {
										o.addProperty("dispatchtype", "大包围调度");
									}
									o.addProperty("launcher", src.getReceipt()
											.getLauncher());
									if(src.getReceipt().getLaunchtime() != null){
										o.addProperty("launchTime", datetimeFormat.format(src.getReceipt().getLaunchtime()));
									}
									if(src.getReceipt().getEndtime() != null){
										o.addProperty("endTime", datetimeFormat.format(src
											.getReceipt().getEndtime()));
									}
									o.addProperty("receipter", src.getNames().getReceiptName());
									if(src.getReceipt().getReceiptetime() != null){
										o.addProperty("receipteTime", datetimeFormat.format(src.getReceipt().getReceiptetime()));
									}
									o.addProperty("memo", src.getReceipt().getMemo());
									o.addProperty("creater", src.getNames().getCreateName());
									o.addProperty("createtime",
											datetimeFormat.format(src.getCreatetime()));
									/*o.addProperty("sender", src.getNames()
											.getSendUserName());
									if (src.getSendtime() != null) {
										o.addProperty("sendTime",
												datetimeFormat.format(src.getSendtime()));
									}*/
									o.addProperty("transmitName", src.getNames().getTransmitName());
									if(src.getTransmittime() != null){
										o.addProperty("transmittime", datetimeFormat.format(src.getTransmittime()));
									}
									o.addProperty("stateName", "完成");
									if(src.getNames() != null){
										o.addProperty("sender", src.getNames().getDepartmentnames());
										o.addProperty("sendTime",src.getNames().getDepartmenttimes());
										o.addProperty("handlername", src.getNames().getHandlerName());
									}
									if(src.getReceipt() != null){
										if(src.getReceipt().getHandletime() != null){
											o.addProperty("handletime",datetimeFormat.format(src.getReceipt().getHandletime()));
										}
									} 
								
								return o;
							}
						}).create();
				strJson = gson.toJson(jsonMap);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
			
			
			logger.info("this document list strJson is " + strJson);
			return strJson;
		}
		
	}
	
	
	
	/** 
	* @Title: detailsBind 
	* @Description: 查询明细页面数据(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/detailsBind", method = RequestMethod.POST)
	@ResponseBody
	public String detailsBind(String id,String type){
		String strJson="";
		List<ReceiptDispatchTotal> list = new ArrayList<ReceiptDispatchTotal>();
		/*if(type.equals("0")){*/
		if(type.equals("2")){//自主
			list = selfDispatchStationsService.selectTotalBySDID(id);
		}
		else {
			list = receiptDispatchService.selectTotalByRDID(id);
		}
		gson = new GsonBuilder().registerTypeAdapter(ReceiptDispatchTotal.class,
				new JsonSerializer<ReceiptDispatchTotal>() {	
					@Override
					public JsonElement serialize(ReceiptDispatchTotal src,
							Type typeOfSrc, JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", src.getId());
						o.addProperty("rdeid", src.getRdeid());
						o.addProperty("rdsid", src.getRdsid());
						o.addProperty("memo", src.getMemo());
						o.addProperty("sid", src.getSid());
						o.addProperty("stationname", src.getStationname());
						o.addProperty("usernames", src.getUsernames());	
						o.addProperty("departmentname", src.getDepartmentname());
						o.addProperty("sendername", src.getSendername());
						if(src.getSendtime() != null){
							o.addProperty("sendtime",datetimeFormat.format(src.getSendtime()));
						}
						return o;
					}
				}).create();
		strJson = gson.toJson(list);
		return strJson;
	}
}
