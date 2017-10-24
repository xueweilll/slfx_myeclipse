package com.benqzl.controller.oa;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.benqzl.controller.BasicController;
import com.benqzl.pojo.oa.TodoList;
import com.benqzl.pojo.oa.TodoListPeople;
import com.benqzl.pojo.system.User;
import com.benqzl.service.oa.TodoService;
import com.benqzl.unit.DecoderUtil;
import com.benqzl.unit.SendMessageAutoUtil;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


/** 
* @ClassName: todolistController 
* @Description: TODO(待办事项维护) 
* @author gyl 
* @date 2015年12月16日 上午9:55:40 
*  
*/
@Controller
@RequestMapping("/todolist")
public class TodolistController extends BasicController{

	public TodolistController() {
		super(TodolistController.class);
		// TODO Auto-generated constructor stub
	}
	
	@Resource
	private TodoService todoservice;

	/** 
	* @Title: index 
	* @Description: TODO(新增待办事项页面) 
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("oa/todoList");
		return mv;
	}

	/** 
	* @Title: userList 
	* @Description: TODO(获得所有用户信息) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/userList", method = RequestMethod.POST)
	@ResponseBody
	public String userList(){
		String json="";
		List<User> list=todoservice.selectUsers();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(User.class,
				new JsonSerializer<User>() {
					@Override
					public JsonElement serialize(User user, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("userid", user.getUserid());
						o.addProperty("name", user.getEmployee().getName());
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		json=json.replace("{\"rows\":", "");
		json=json.substring(0,json.length()-1);
		json=json.substring(0,1)+"{\"userid\":\"0\",\"name\":\"--全部--\"},"+json.substring(1,json.length());
		return json;
	}
	@RequestMapping(value = "/userList2", method = RequestMethod.POST)
	@ResponseBody
	public String userList2(){
		String json="";
		List<User> list=todoservice.selectUsers();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(User.class,
				new JsonSerializer<User>() {
					@Override
					public JsonElement serialize(User user, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("userid", user.getUserid());
						o.addProperty("name", user.getEmployee().getName());
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		json=json.replace("{\"rows\":", "");
		json=json.substring(0,json.length()-1);
//		json=json.substring(0,1)+"{\"userid\":\"0\",\"name\":\"--全部--\"},"+json.substring(1,json.length());
		return json;
	}
	/** 
	* @Title: todolistList 
	* @Description: TODO(发送人获得所有新增待办事项信息) 
	* @param @param page
	* @param @param rows
	* @param @param sn
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/todolistList", method = RequestMethod.POST)
	@ResponseBody
	public String todolistList(int page,int rows,String userid,String title) {
		String json="";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", String.valueOf(start));
		map.put("p2", Integer.toString(rows));
		if(userid !=null && !userid.trim().equals("") && !userid.trim().equals("0")){
			map.put("userid",userid);
		}
		if(title !=null && !title.trim().equals("")){
			map.put("title",title);
		}
		List<TodoList> list=todoservice.findByPage3(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = todoservice.pageCount(map);
		jsonMap.put("total", total);
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(TodoList.class,
				new JsonSerializer<TodoList>() {
					@Override
					public JsonElement serialize(TodoList todolist, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", todolist.getId());
						o.addProperty("title", todolist.getTitle());
						o.addProperty("contents", todolist.getContents());
						o.addProperty("dodate", datetimeFormat.format(todolist.getDodate()));
						o.addProperty("ispc", todolist.getIspc()==0?"√":"×");
						o.addProperty("isphonemess", todolist.getIsphonemess()==0?"√":"×");									
//						int state=0;
//						String username="";
//						for(TodoListPeople p : list){
//							state+=p.getState();
//							username+=p.getUser().getEmployee().getName()+",";
//						}
//						if(!username.equals("")){
//							username=username.substring(0,username.length()-1);
//						}
//						o.addProperty("username", username);
						o.addProperty("state",todolist.getIsdel().intValue()==2?"已处理":"正在处理");
						o.addProperty("fileaddress", todolist.getFileaddress());			
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		
		return json;
	}
	
	
	@RequestMapping(value = "/searchTodopeopleById", method = RequestMethod.GET)
	public ModelAndView searchTodopeopleById(String id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id",id);
		mv.setViewName("oa/todosituation");
		return mv;
	}
	
	/** 
	* @Title: todolistSearch 
	* @Description: TODO(收件人获得所有待办事项) 
	* @param @param page
	* @param @param rows
	* @param @param title
	* @param @param dodate
	* @param @param userid
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/todolistSearch", method = RequestMethod.POST)
	@ResponseBody
	public String todolistSearch(int page,int rows,String title,String begin,String end,String userid,String todoid) {
		String json="";
		page = (page == 0 ? 1 : page);
		rows = (rows == 0 ? 15 : rows);
		int start = (page - 1) * rows;
		rows = start+rows;
		logger.info("this page rows is " + page + "|" + rows);
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", String.valueOf(start));
		map.put("p2", Integer.toString(rows));
		if(title!=null && !title.trim().equals("")){
			map.put("title", title);
		}
		if(begin!=null && !begin.trim().equals("")){
			map.put("begin", begin);
		}
		if(end!=null && !end.trim().equals("")){
			map.put("end", end);
		}
		if(userid!=null && !userid.trim().equals("")){
			map.put("userid", userid);
		}
		if(todoid !=null && !todoid.trim().equals("")){
			map.put("todoid",todoid);
		}
		List<TodoListPeople> list=todoservice.findByPage(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		int total = todoservice.pageCount2(map);
		jsonMap.put("total", total);
		jsonMap.put("rows",list);
		gson = new GsonBuilder().registerTypeAdapter(TodoListPeople.class,
				new JsonSerializer<TodoListPeople>() {
					@Override
					public JsonElement serialize(TodoListPeople todolistpeople, Type typeOfSrc,JsonSerializationContext context) {
						JsonObject o = new JsonObject();
						o.addProperty("id", todolistpeople.getId());
						o.addProperty("todolistid", todolistpeople.getTodolist().getId());
						o.addProperty("title", todolistpeople.getTodolist().getTitle());
						o.addProperty("contents", todolistpeople.getTodolist().getContents());
						o.addProperty("dodate", datetimeFormat.format(todolistpeople.getTodolist().getDodate()));
						o.addProperty("username", todolistpeople.getUser().getEmployee().getName());
						o.addProperty("state", todolistpeople.getState()==0?"待查看":(todolistpeople.getState()==1?"待处理":(todolistpeople.getState()==2?"已处理":"已删除")));
						o.addProperty("fileaddress", todolistpeople.getTodolist().getFileaddress());
						o.addProperty("ispc", todolistpeople.getTodolist().getIspc()==0?"√":"×");
						o.addProperty("isphonemess", todolistpeople.getTodolist().getIsphonemess()==0?"√":"×");
						o.addProperty("todolistid", todolistpeople.getTodolist().getId());
						if(todolistpeople.getViewdate()!=null){
							o.addProperty("viewdate", datetimeFormat.format(todolistpeople.getViewdate()));
						};
						if(todolistpeople.getFeedbackdate()!=null){
							o.addProperty("feedbackdate", datetimeFormat.format(todolistpeople.getFeedbackdate()));
						}						
						return o;
					}
				}).create();
		json=gson.toJson(jsonMap);
		
		return json;
	}
	/** 
	* @Title: todolistInfo 
	* @Description: TODO(跳转到新增页面、查看待办事项页面) 
	* @param @param id
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "todolistInfo", method = RequestMethod.GET)
	public ModelAndView todolistInfo(String id,String state,HttpServletRequest request,String todolistid) {
		ModelAndView mv = new ModelAndView();
		if(!id.equals("0")){
			if(state!=null && state.equals("0")){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id", id);			
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
				map.put("feedbackdate", sdf.format(new Date()));
				User user=(User) request.getSession().getAttribute("loginUser");
				todoservice.updatetwo(map,user.getUserid(),todolistid);				
			}
			TodoListPeople todopeople=todoservice.selectByPrimaryKey(id);
			mv.addObject("todopeople",todopeople);
			mv.addObject("fileaddress",todopeople.getTodolist().getFileaddress());
			mv.setViewName("oa/todolistpeopleInfo");			
		}else{
			mv.setViewName("oa/todoListInfo");			
		}
		return mv;
	}
	/** 
	* @Title: todolistInfoEdit 
	* @Description: TODO(跳转到修改页面) 
	* @param @param id
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "todolistInfoEdit", method = RequestMethod.GET)
	public ModelAndView todolistInfoEdit(String id) {
		ModelAndView mv = new ModelAndView();				
		TodoList todolist=todoservice.findtodolistByid(id);
		List<TodoListPeople> list=todolist.getTodolistpeople();
		String userIds = "";
		for(TodoListPeople todolistpeople:list){
			if(userIds==""){
				userIds+="[";
			}
			userIds+="'"+todolistpeople.getUser().getUserid()+"',";
		}
		userIds = userIds.substring(0,userIds.length()-1);
		userIds+="]";
		System.out.println(userIds);
		mv.addObject("users",userIds);
		mv.addObject("date", datetimeFormat.format(todolist.getDodate()));		
		mv.addObject("todolist",todolist);
		
		mv.setViewName("oa/todoListInfo");
		return mv;
	}	
	/**
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException  
	* @Title: save 
	* @Description: TODO(执行新增、修改待办事项) 
	* @param @param json
	* @param @param file
	* @param @param request
	* @param @return
	* @param @throws IOException
	* @param @throws ParseException    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
//	public String save(
//			@RequestParam("json") String json,
//			@RequestParam(value="fileaddress",required=false) MultipartFile files[],HttpServletRequest request)
//			throws IOException, ParseException {
	public String save(String json,HttpServletRequest request) throws IOException, ParseException{
		String path = request.getSession().getServletContext().getRealPath("/upload")+"\\";	
//		String path2="d:\\upload\\";
		String result="";
		String[] para=json.split("/");		
		String todolistid=para[0];
		String title=para[1];
		String dodate=para[2];
		String contents=para[3];
		String ispc=para[4];
		String isphonemess=para[5];
		//传进来的新userids数组
		String[] userids=para[6].split(",");
		String [] filenames=null;
		if(para.length>7){
			filenames=para[7].split(",");
		}
		String[] fileaddresses=null;		
		if(para.length>8){
			fileaddresses=para[8].split(",");
		}
		TodoList todolist=new TodoList();		
//		if(!file.getOriginalFilename().equals("")){
//			todolist.setFileaddress(todolist.getId()+"_"+file.getOriginalFilename());
//		}else{
//			todolist.setFileaddress("");
//		}				
		//新增
		if(todolistid.equals("0")){
//			if(!file.getOriginalFilename().equals("")){
//				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, todolist.getId()+"_"+ file.getOriginalFilename()));
//			}
			UUID uuid=UUID.randomUUID();
			//多文件上传
			if(fileaddresses!=null){
				for(int i=0;i<fileaddresses.length;i++){
					if(!fileaddresses[i].equals("")){
						InputStream inputstream=new FileInputStream(fileaddresses[i]);
						String filename=fileaddresses[i].substring(fileaddresses[i].lastIndexOf("\\")+1, fileaddresses[i].length());
						FileUtils.copyInputStreamToFile(inputstream, new File(path, uuid+"_"+ filename));
					}
				}
			}			
			todolist.setId(uuid.toString());
			todolist.setTitle(title);
			todolist.setDodate(datetimeFormat.parse(dodate));
			todolist.setContents(contents);
			todolist.setIsfeedback(new Long(1));
			String fileaddress="";
			if(fileaddresses!=null){
				for(int i=0;i<fileaddresses.length;i++){
					fileaddress+=uuid+"_"+fileaddresses[i].substring(fileaddresses[i].lastIndexOf("\\")+1, fileaddresses[i].length())+",";
				}
				if(!fileaddress.equals("")){
					fileaddress=fileaddress.substring(0,fileaddress.length()-1);
				}
			}			
			todolist.setFileaddress(fileaddress);
			User user=(User) request.getSession().getAttribute("loginUser");
			todolist.setCreaterid(user.getUserid());
			todolist.setIsdel(new Long(1));
			todolist.setCreatetime(new Date());
			todolist.setEditerid("");
			todolist.setEdittime(new Date());
			todolist.setIspc(Long.parseLong(ispc));
			todolist.setIsphonemess(Long.parseLong(isphonemess));
			
			
			TodoListPeople[] people=new TodoListPeople[userids.length];
			List<String> l=new ArrayList<String>();
			for(int i=0;i<userids.length;i++){				
				people[i]=new TodoListPeople();
				UUID uuid2=UUID.randomUUID();
				people[i].setId(uuid2.toString());
				people[i].setDoid(uuid.toString());
				people[i].setDotoer(userids[i]);
				people[i].setState(new Long(0));
				l.add(userids[i]);
			}
			try {
				todoservice.insertTodolist(todolist);
				for(int i=0;i<userids.length;i++){					
					todoservice.insertTodolistpeople(people[i]);
				}
				result="true";
			} catch (Exception e) {
				result="false";
				e.printStackTrace();
			}
			SendMessageAutoUtil.sendMessageListAuto(l);			
		//修改	
		}else{
			TodoList todo=todoservice.findtodolistByid(todolistid);
			String address=todo.getFileaddress();
			String[] address2=null;
			if(address!=null){
				address2=address.split(",");
			}
			//默认没值
			String fileaddress="";
			//原来就有值，新的为空，则删除原来的文件，并设置fileaddress为空
			if(address!=null && filenames==null){
				for(int i=0;i<address2.length;i++){
					String del=path+address2[i];
					File f=new File(del.replace("\\", "\\\\"));
					f.delete();
				}
			//原来有值，现在也有值
			}else if(address!=null && filenames!=null){
				//删掉老的多的文件
				for(int i=0;i<address2.length;i++){
					boolean xx=false;
					for(int j=0;j<filenames.length;j++){						
						String oldname=address2[i].substring(37);
						String newname=filenames[j];
						if(oldname.equals(newname)){
							xx=true;
							break;
						}
					}
					if(xx){
						fileaddress+=address2[i]+",";
					}else{
						String del=path+address2[i];					
						File f=new File(del.replace("\\", "\\\\"));
						f.delete();
					}
				}
				//新增新的文件
				for(int i=0;i<filenames.length;i++){
					boolean xx=false;
					for(int j=0;j<address2.length;j++){
						String oldname=address2[j].substring(37);
						String newname=filenames[i];
						if(oldname.equals(newname)){
							xx=true;
							break;
						}
					}
					if(xx==false){
						fileaddress+=todolistid+"_"+filenames[i]+",";
						for(int j=0;j<fileaddresses.length;j++){
							String name1=fileaddresses[j].substring(fileaddresses[j].lastIndexOf("\\")+1,fileaddresses[j].length());
							if(name1.equals(filenames[i])){
								InputStream inputstream=new FileInputStream(fileaddresses[j]);								
								FileUtils.copyInputStreamToFile(inputstream, new File(path, todolistid+"_"+ name1));
								break;
							}
						}
					}
				}
				//原来没值，现在有值
			}else if(address==null && filenames!=null){
				if(fileaddresses!=null){
					for(int i=0;i<fileaddresses.length;i++){
						fileaddress+=todolistid+"_"+filenames[i]+",";
						InputStream inputstream=new FileInputStream(fileaddresses[i]);
						String filename=fileaddresses[i].substring(fileaddresses[i].lastIndexOf("\\")+1, fileaddresses[i].length());
						FileUtils.copyInputStreamToFile(inputstream, new File(path, todolistid+"_"+ filename));						
					}
				}								
			}
			if(!fileaddress.equals("")){
				fileaddress=fileaddress.substring(0, fileaddress.length()-1);
			}
			todolist.setFileaddress(fileaddress);
			//更新todolist
			todolist.setId(todolistid);
			todolist.setTitle(title);
			todolist.setContents(contents);
			todolist.setIspc(Long.parseLong(ispc));
			todolist.setIsphonemess(Long.parseLong(isphonemess));									
			todoservice.updateByPrimaryKeySelective(todolist);												
			List<TodoListPeople> todolistpeoples=todo.getTodolistpeople();
			String [] userids2=new String[todolistpeoples.size()];
			String [] todolistpeopleids=new String[todolistpeoples.size()];
			//原来的usersid数组赋值
			for(int i=0;i<todolistpeoples.size();i++){
				userids2[i]=todolistpeoples.get(i).getUser().getUserid();
				todolistpeopleids[i]=todolistpeoples.get(i).getId();
			}						
			boolean panduan=false;
			//遍历老的，如果发现新的没有老的，则删除老的，有的话不删。
			for(int i=0;i<userids2.length;i++){
				panduan=true;
				for(int j=0;j<userids.length;j++){
					if(userids[j].equals(userids2[i])){
						panduan=false;
						break;
					}
				}
				if(panduan==true){
					//删除老的todolistpeople(userids2[i])
					todoservice.deletetodolistpeopleById(todolistpeopleids[i]);
				}
			}			
			//遍历新的，如果发现老的没有新的，则新增新的，有的话就不新增
			for(int i=0;i<userids.length;i++){
				panduan=true;
				for(int j=0;j<userids2.length;j++){
					if(userids[i].equals(userids2[j])){
						panduan=false;
						break;
					}
				}
				if(panduan==true){
					TodoListPeople tp=new TodoListPeople();
					UUID uuid3=UUID.randomUUID();
					tp.setId(uuid3.toString());
					tp.setDoid(todolistid);
					tp.setDotoer(userids[i]);
					tp.setState(new Long(0));
					todoservice.insertTodolistpeople(tp);
				}
			}						
		}
		return result;
	}
	/** 
	* @Title: todolistDelete 
	* @Description: TODO(执行逻辑删除待办事项功能) 
	* @param @param todolistid
	* @param @param todolistpeopleid
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/todolistDelete" , method=RequestMethod.POST)
	@ResponseBody
	public String todolistDelete(String todolistid) {
		String result="";
		try {
			todoservice.deletetodolistpeople(todolistid);
			todoservice.deletetodolist(todolistid);
			result="true";
		} catch (Exception e) {
			result="false";
			e.printStackTrace();			
		}		
		return result;
	}	
	/** 
	* @Title: doneTodo 
	* @Description: TODO(执行反馈功能) 
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@RequestMapping(value="/doneTodo" , method=RequestMethod.POST)
	@ResponseBody
	public String doneTodo(String id,String todolistid) {
		String result="";
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", id);			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
			map.put("feedbackdate", sdf.format(new Date()));
			todoservice.updateone(map);
			map.put("todolistid", todolistid);
			List<TodoListPeople> list=todoservice.findOtherPeopleByTodolistid(map);
			boolean panduan=true;
			if(list!=null){
				for(int i=0;i<list.size();i++){
					if(list.get(i).getState().intValue()!=2){
						panduan=false;
						break;
					}
				}
			}
			if(panduan){
				todoservice.updateTodolist(todolistid);
			}
			result="true";
		} catch (Exception e) {
			result="false";
			e.printStackTrace();
		}
		return result;
	}
	/** 
	* @Title: downoad 
	* @Description: TODO(下载附件) 
	* @param @param request
	* @param @param response
	* @param @param fileaddress    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void downoad(HttpServletRequest request,
			HttpServletResponse response, String fileaddress) {
		// 得到要下载的文件名
				fileaddress = DecoderUtil.decoder(fileaddress);
				String fileName = fileaddress;
					// 获取上传文件的目录
					ServletContext sc = request.getSession().getServletContext();
					// 上传位置
					String path = sc.getRealPath("/upload")+"\\";
//					String path2="d:\\upload\\";
					// 得到要下载的文件
//					File file = new File(fileSaveRootPath + "\\" + fileName);
					File file = new File((path + fileName).replace("\\", "\\\\"));
					// 如果文件不存在
					if (!file.exists()) {
						request.setAttribute("message", "您要下载的资源已被删除！！");
						System.out.println("您要下载的资源已被删除！！");
						return;
					}
					response.setCharacterEncoding("utf-8");
					response.setContentType("multipart/form-data");

					response.setHeader("Content-Disposition", "attachment;fileName="+DecoderUtil.encoder(fileName));
					try {
						System.out.println(file.getAbsolutePath());
						InputStream inputStream=new FileInputStream(file);
						OutputStream os=response.getOutputStream();
						byte[] b=new byte[1024];
						int length;
						while((length=inputStream.read(b))>0){
							os.write(b,0,length);
						}
						inputStream.close();
					} 
					catch (FileNotFoundException e) {
						e.printStackTrace();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
	}
	
}

