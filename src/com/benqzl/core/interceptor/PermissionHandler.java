package com.benqzl.core.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PermissionHandler implements HandlerInterceptor {

	public static final String[] allowUrls = { "moblie","login", "index", "oa", "dwr","qrcode",
			"messagecenter","paramers", "system","user/editpwd","error","map","material","inspection", "floodctl", "main", "js",
			"images", "css","user/changePWD","alarmship" ,"upload"};
	static final String failMessage = "<script>alert('您没此操作权限，请联系管理员授权！')</script>";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//System.out.println(request.getMethod());
		String requestUrl = request.getRequestURI()
				.replace(request.getContextPath() + "/", "")
				.replace(".html", "").toLowerCase();
		// outputStream = response.getOutputStream();
		System.out.println("requestUrl:"+requestUrl);
		if (null != allowUrls && allowUrls.length >= 1) {
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					return true;
				}
			}
		}
		
		@SuppressWarnings("unchecked")
		Map<String, String[]> permissions = (Map<String, String[]>) request
				.getSession().getAttribute("permissions");
		if (permissions == null) {
			redirectErrorPage(response);
			return false;
		}
		String method = request.getMethod();
		System.out.println("method:"+request.getMethod());
		if(method.equals("GET")){
			if(permissions.containsKey(requestUrl)){
				System.out.println("permissionHandler:菜单权限通过授权");
				return true;
			}else{
				for(String keystr:permissions.keySet()){
					if(requestUrl.matches(keystr+".*")){
						System.out.println("permissionHandler:菜单权限通过授权");
						return true;
					}
				}
				redirectErrorPage(response);
				return false;
			}
		}else if(method.equals("POST")){
			String[] paths = requestUrl.split("/");
			String[] perms;
			boolean b = false;
			if(paths.length>1){
				//判断是否权限请求
				for(String str : allPerm){
					if(paths[1].matches(".*"+str+".*")){
						b = true;
						break;
					}
				}
				//不是权限请求放过
				if(!b){
					System.out.println("permissionHandler:不是按钮权限请求");
					return true;
				}
				if(permissions.containsKey(paths[0])){
					perms = permissions.get(paths[0]);
					for(String perm : perms){
						if(paths[1].matches(".*"+perm+".*")){
							System.out.println("permissionHandler:按钮权限通过授权");
							return true;
						}
					}
				}
			}
			writeErrorMsg(response);
			return false;
		}else{
			redirectErrorPage(response);
			return false;
		}
		/*String[] paths = requestUrl.split("/");
		
		
		if (paths.length == 1) {// 一级菜单项
			for (String menuStr : permissions.keySet()) {
				if (paths[0].contains(menuStr)) {
					System.out.println("permissionHandler:菜单权限通过授权");
					return true;
				}
			}
			outputStream = response.getOutputStream();
			outputStream.write(failMessage.getBytes("UTF-8"));
			return false;
		} else {// 二级菜单或方法
			for (String menuStr : permissions.keySet()) {
				if (paths[0].contains(menuStr)) {
					System.out.println("当前操作为:" + paths[1]);
					boolean isPerm = false;
					for (String str : allPerm) {
						if (paths[1].contains(str)) {
							isPerm = true;
						}
					}
					if (isPerm) {
						Long val = permissions.get(menuStr);
						String[] perms = permission(val);
						for (String perm : perms) {
							System.out.println("遍历当前操作人权限" + perm);
							if (perm == null) {
								continue;
							}
							if (paths[1].contains(perm)) {
								System.out
										.println("permissionHandler:按钮权限通过授权");
								return true;
							}
						}
						System.out
						.println("permissionHandler:按钮权限未通过授权");
						writer = response.getWriter();
						writer.write("{'error':true}");
						return false;
					} else {
						System.out.println("permissionHandler:非按钮权限通过授权");
						return true;
					}
				}
			}
		}
		outputStream = response.getOutputStream();
		outputStream.write(failMessage.getBytes("UTF-8"));
		return false;*/
	}
	
	private void redirectErrorPage(HttpServletResponse response) throws IOException{
		response.sendRedirect("/slfx/error.html");
	}
	
	private void writeErrorMsg(HttpServletResponse response) throws IOException{
		writer = response.getWriter();
		writer.write("{'error':true}");
	}
	
	private PrintWriter writer;
	static final String[] allPerm = new String[] { "save", "delete", "aduit",
			"send", "transmit", "commit", "print", "export" };

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
