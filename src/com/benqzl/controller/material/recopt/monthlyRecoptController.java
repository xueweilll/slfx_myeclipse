package com.benqzl.controller.material.recopt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.benqzl.pojo.material.MonthReport;
import com.benqzl.service.material.TableService;
import com.benqzl.unit.DecoderUtil;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName: monthlyRecoptController
 * @Description: TODO(月报表导出查询)
 * @author pxj
 * @date 2016年1月27日 上午8:54:42
 * 
 */
@Controller
@RequestMapping("monthlyReport")
public class monthlyRecoptController extends BasicController {

	@Autowired
	private TableService service;

	public monthlyRecoptController() {
		super(monthlyRecoptController.class);
	}

	/**
	 * @Title: index
	 * @Description: TODO(月报表查页面)
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/material/monthlyReport");
		return mv;
	}

	/**
	 * @Title: monthlyReportList
	 * @Description: TODO(月报表)
	 * @param @param page
	 * @param @param rows
	 * @param @param year
	 * @param @param month
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "monthlyReportList", method = RequestMethod.POST)
	@ResponseBody
	public String monthlyReportList(int page, int rows, String year,
			String month) {
		Map<String, Object> map = new HashMap<String, Object>();
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start + rows;
		logger.info("this page rows is " + page + "|" + rows);
		map.put("p1", start);
		map.put("p2", rows);
		map.put("type", 1);
		if (month == null || month.equals("0")||month=="") {
			map.put("month", null);
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
			SimpleDateFormat sf = new SimpleDateFormat("MM");
			map.put("month", sf.format(calendar.getTime()));
		}
		if (year == null || year.equals("全部")||year=="") {
			map.put("year", null);
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.parseInt(year));
			SimpleDateFormat sf = new SimpleDateFormat("yyyy");
			map.put("year", sf.format(calendar.getTime()));
		}
		List<MonthReport> monthReport = null;
		int pageCount = 0;
		try {
			monthReport = service.findMonthByPage(map);
			pageCount = service.pageMonthCount(map);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", pageCount);
		jsonMap.put("rows", monthReport);
		gson = new GsonBuilder().registerTypeAdapter(MonthReport.class,
				new JsonSerializer<MonthReport>() {
					@Override
					public JsonElement serialize(MonthReport arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("id", arg0.getId());
						json.addProperty("sname", arg0.getMaterial().getSize()
								.getName());
						json.addProperty("pname", arg0.getMaterial()
								.getPrickle().getName());
						json.addProperty("mname", arg0.getMaterial().getName());
						json.addProperty("creatertime",
								dateFormat.format(arg0.getCreatetime()));
						json.addProperty("ultstorage", arg0.getUltstorage());
						json.addProperty("storage", arg0.getStorage());
						json.addProperty("checkoutstorage",
								arg0.getCheckoutstorage());
						json.addProperty("checkinstorage",
								arg0.getCheckinstorage());
						json.addProperty("instorage", arg0.getInstorage());
						json.addProperty("outstorage", arg0.getOutstorage());
						json.addProperty("scrapstorage", arg0.getScrapstorage());
						json.addProperty("memeo", "");
						return json;
					}
				}).create();
		return gson.toJson(jsonMap);
	}

	/**
	 * @Title: year
	 * @Description: TODO(获取年份)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "year", method = RequestMethod.POST)
	@ResponseBody
	public String year() {
	List<MonthReport> list = service.findYear();
		gson = new GsonBuilder().registerTypeAdapter(MonthReport.class,
				new JsonSerializer<MonthReport>() {
					@Override
					public JsonElement serialize(MonthReport arg0, Type arg1,
							JsonSerializationContext arg2) {
						JsonObject json = new JsonObject();
						json.addProperty("createtime", arg0.getMaterialid());
						return json;
					}
				}).create();
		return gson.toJson(list);
	}

	public void download(HttpServletRequest request,
			HttpServletResponse response, String fileaddress) {
		// 得到要下载的文件名
		fileaddress = DecoderUtil.decoder(fileaddress);
		String fileNames = fileaddress;
		String fileName[] =fileNames.split("\\.");
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
					+ new String(fileName[0].getBytes("gb2312"),"iso8859-1")+".xls");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			System.out.println(file.getAbsolutePath());
			InputStream inputStream = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: print
	 * @Description: TODO(导出EXECLE)
	 * @param @param month
	 * @param @param year
	 * @param @param request
	 * @param @param response 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@RequestMapping(value = "print", method = RequestMethod.GET)
	@ResponseBody
	public void print(String month, String year, HttpServletRequest request,
			HttpServletResponse response) {
		String docsPath = request.getSession().getServletContext()
				.getRealPath("/upload");
		String fileName = "月报表.xls";
		String filePath = docsPath + "\\" + fileName;
		String years = DecoderUtil.decoder(year);
		Map<String, Object> map = new HashMap<String, Object>();
		if (month == null || month.equals("0")) {
			map.put("month", null);
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
			SimpleDateFormat sf = new SimpleDateFormat("MM");
			map.put("month", sf.format(calendar.getTime()));
		}
		if (year == null || years.equals("全部")) {
			map.put("year", null);
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.parseInt(years));
			SimpleDateFormat sf = new SimpleDateFormat("yyyy");
			map.put("year", sf.format(calendar.getTime()));
		}

		List<MonthReport> monthReport = null;
		try {
			monthReport = service.print(map);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		WritableWorkbook book = null;

		try {
			book = Workbook.createWorkbook(new File(filePath));
			WritableSheet sheet = book.createSheet("月报表", 0);
			Label label1 = new Label(0, 0, "物品名称");
			Label label2 = new Label(1, 0, "规格");
			Label label3 = new Label(2, 0, "计量单位");
			Label label4 = new Label(3, 0, "上月结存");
			Label label5 = new Label(4, 0, "本月入库");
			Label label6 = new Label(5, 0, "本月出库");
			Label label7 = new Label(6, 0, "本月借出");
			Label label11 = new Label(7, 0, "本月归还");
			Label label8 = new Label(8, 0, "本月报废");
			Label label9 = new Label(9, 0, "现库存");
			Label label10 = new Label(10, 0, "时间");
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
			for (int i = 1; i < monthReport.size() + 1; i++) {
				Label number1 = new Label(0, i, monthReport.get(i - 1)
						.getMaterial().getName());
				Label number2 = new Label(1, i, monthReport.get(i - 1)
						.getMaterial().getSize().getName());
				Label number3 = new Label(2, i, monthReport.get(i - 1)
						.getMaterial().getPrickle().getName());
				Label number4 = new Label(3, i, monthReport.get(i - 1)
						.getUltstorage().toString());
				Label number5 = new Label(4, i, monthReport.get(i - 1)
						.getInstorage().toString());
				Label number6 = new Label(5, i, monthReport.get(i - 1)
						.getOutstorage().toString());
				Label number7 = new Label(6, i, monthReport.get(i - 1)
						.getCheckoutstorage().toString());
				Label number8 = new Label(7, i, monthReport.get(i - 1)
						.getCheckinstorage().toString());
				Label number9 = new Label(8, i, monthReport.get(i - 1)
						.getScrapstorage().toString());
				Label number10 = new Label(9, i, monthReport.get(i - 1)
						.getStorage().toString());
				Label number11 = new Label(10, i,dateFormat.format(monthReport.get(i-1).getCreatetime()));
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
