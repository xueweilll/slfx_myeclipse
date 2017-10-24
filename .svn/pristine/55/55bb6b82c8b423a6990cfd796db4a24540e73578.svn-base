package com.benqzl.controller.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import javax.annotation.Resource;







import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.directwebremoting.ScriptSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.User;
import com.benqzl.service.system.UserService;
//import com.benqzl.core.dwr.DWRScriptSessionListener;
import com.benqzl.unit.DecoderUtil;

/**
 * @ClassName: LoginController
 * @Description: TODO(登錄界面)
 * @author lyf
 * @date 2016年1月6日 上午10:03:41
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BasicController {

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description: 構造函數
	 * </p>
	 */
	
	@Autowired
	private UserService service;
	public LoginController() {
		super(LoginController.class);
	}

	/**
	 * @Title: index @Description: TODO(登錄頁) @param @return 设定文件 @return
	 *         ModelAndView 返回类型 @throws
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		// request.getSession().removeAttribute("loginUser");
		/*
		 * try { Subject subject = SecurityUtils.getSubject();
		 * if(subject.isAuthenticated()){
		 * subject.getSession().removeAttribute("loginUser"); subject.logout();
		 * } } catch (Exception e) { e.printStackTrace(); }
		 */
		// request.getSession().removeAttribute("loginUser");
		Subject currentUser = SecurityUtils.getSubject();
		Object username= currentUser.getPrincipal();
		System.out.println(username);
		ModelAndView mv = new ModelAndView();
		if(username!=null){
			try {
				User user = service.selectByName(username.toString());
				mv.addObject("username", user.getUsername());
				mv.addObject("userpwd", user.getUserpwd());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mv.setViewName("/main/login");
		return mv;
	}

	@RequestMapping(value = "download", method = RequestMethod.GET)
	public void download(HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		// String path = request.getParameter("path") != null ?
		// request.getParameter("path") : "";
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "js\\soft\\常州城市防洪.lnk";
		// System.out.println("解密前：" + path);
		// path = URLDecoder.decode(path, "utf-8");
		// System.out.println("解密后：" + path);
		File packageZip = new File(path);
		// log.info("开始下载订单的源文件path="+path);
		// 渲染的源文件名
		String fileName = "常州城市防洪.lnk";
		long file_length = 0;
		if (packageZip.exists()) {
			// 文件的大小
			file_length = packageZip.length();
			fileName = packageZip.getName();
			// 将获取的数据当成文件下载
			getSrcStrem(response, packageZip, fileName, file_length);
		}
	}

	private void getSrcStrem(HttpServletResponse response, File path,
			String fileName, long file_length) throws IOException {
		// 清空response
		response.reset();

		// 设置response的头部
		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String(fileName.getBytes("gb2312"), "ISO8859-1"));
		response.addHeader("Content-Length", "" + file_length);
		response.setContentType("appication/octet-stream");
		BufferedOutputStream output = null;
		BufferedInputStream bfs = null;
		try {
			bfs = new BufferedInputStream(new FileInputStream(path));
			// 新建文件输入输出流对象
			output = new BufferedOutputStream(response.getOutputStream());
			// 设置每次写入缓存大小
			byte[] b = new byte[2048];
			int bytesindex;
			while (-1 != (bytesindex = bfs.read(b, 0, b.length))) {
				output.write(b, 0, bytesindex);
			}
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
			// log.info("下载发生异常"+e.getMessage());
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				output = null;
			}
			if (bfs != null) {
				try {
					bfs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				bfs = null;
			}

		}
		response.flushBuffer();// 强行将响应缓存中的内容发送到目的地
	}

	/**
	 * @Title: verify @Description: TODO(身份驗證) @param @param username @param @param
	 *         password @param @return 设定文件 @return String 返回类型 @throws
	 */
	@RequestMapping(value = "verifyMobile", method = RequestMethod.POST)
	@ResponseBody
	public String verifyMobile(String username, String password) {
		String result = "";
		/*
		 * User user; try { user = userService.selectByName(username); } catch
		 * (Exception e) { e.printStackTrace(); result =
		 * "{'result':false,'msg':'用户名或密码错误！'}"; return result; }
		 * 
		 * if (user.getUserpwd().equals(password)) {
		 * request.getSession().setAttribute("loginUser", user); result =
		 * "{'result':true}"; return result; } else { result =
		 * "{'result':false,'msg':'用户名或密码错误！'}"; }
		 */
		username = DecoderUtil.decoder(username);
		password = DecoderUtil.decoder(password);
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);

		Subject currentUser = SecurityUtils.getSubject();
		try {
			System.out.println("----------------------------");
			if (!currentUser.isAuthenticated()) {
				// token.setRememberMe(true);
				currentUser.login(token);
			}
			result = "{'result':true}";
			System.out.println("result: " + result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result = "{'result':false,'msg':'用户名或密码错误！'}";
		}

		return result;
	}

	// @Resource
	// UserService userService;

	/**
	 * @Title: verify @Description: TODO(身份驗證) @param @param username @param @param
	 *         password @param @return 设定文件 @return String 返回类型 @throws
	 */
	@RequestMapping(value = "verify", method = RequestMethod.POST)
	@ResponseBody
	public String verify(String username, String password) {
		String result = "";
		/*
		 * User user; try { user = userService.selectByName(username); } catch
		 * (Exception e) { e.printStackTrace(); result =
		 * "{'result':false,'msg':'用户名或密码错误！'}"; return result; }
		 * 
		 * if (user.getUserpwd().equals(password)) {
		 * request.getSession().setAttribute("loginUser", user); result =
		 * "{'result':true}"; return result; } else { result =
		 * "{'result':false,'msg':'用户名或密码错误！'}"; }
		 */

		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		token.setRememberMe(true);

		Subject currentUser = SecurityUtils.getSubject();
		try {
			System.out.println("----------------------------");
			if (!currentUser.isAuthenticated()) {
				// token.setRememberMe(true);
				currentUser.login(token);
			}
			result = "{'result':true}";
			System.out.println("result: " + result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result = "{'result':false,'msg':'用户名或密码错误！'}";
		}

		return result;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public String logout() {
		String result = "";

		try {
			Subject subject = SecurityUtils.getSubject();
			if (subject.isAuthenticated()) {
				// subject.getSession().removeAttribute("loginUser");
				/*
				 * Session session = subject.getSession(); ScriptSession
				 * scriptSession =
				 * DWRScriptSessionListener.scriptSessionMap.remove(session.
				 * getId().toString()); System.out.println("session: " +
				 * session.getId() + " scriptSession: " + scriptSession.getId()
				 * + "is destroyed!");
				 */
				subject.logout();
				result = "{'result':true}";
				// return "redirect:/login.html";
			}
		} catch (Exception e) {
			result = "{'result':false,'msg':'用户名或密码错误！'}";
			e.printStackTrace();
		}

		return result;
	}
}
