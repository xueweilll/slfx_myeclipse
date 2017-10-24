package com.benqzl.core.dwr;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.directwebremoting.ScriptSession;
//import org.directwebremoting.WebContext;
//import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;

public class DWRScriptSessionListener implements ScriptSessionListener {

	// 维护一个Map key为session的Id， value为ScriptSession对象
	public static final Map<String, ScriptSession> scriptSessionMap = new HashMap<String, ScriptSession>();

	/**
	 * ScriptSession创建事件
	 */
	public void sessionCreated(ScriptSessionEvent event) {
		/*WebContext webContext = WebContextFactory.get();
		HttpSession session = webContext.getSession();*/
		System.out.println("scriptSession:begin create!");
		if(!SecurityUtils.getSubject().isAuthenticated()){
			return;
		}
		Session session = SecurityUtils.getSubject().getSession();
		ScriptSession scriptSession = event.getSession();
		scriptSessionMap.put(session.getId().toString(), scriptSession); // 添加scriptSession
		System.out.println("session: " + session.getId() + " scriptSession: "
				+ scriptSession.getId() + "is created!");
	}

	/**
	 * ScriptSession销毁事件
	 */
	public void sessionDestroyed(ScriptSessionEvent event) {
		/*WebContext webContext = WebContextFactory.get();
		HttpSession session = webContext.getSession();*/
		System.out.println("ScriptSession销毁事件-->开始");
		if(!SecurityUtils.getSubject().isAuthenticated()){
			System.out.println("ScriptSession销毁事件-->aaa");
			return;
		}
		System.out.println("ScriptSession销毁事件-->正在销毁中");
		Session session = SecurityUtils.getSubject().getSession();
		ScriptSession scriptSession = scriptSessionMap.remove(session.getId().toString()); // 移除scriptSession
		System.out.println("session: " + session.getId() + " scriptSession: "
				+ scriptSession.getId() + "is destroyed!");
	}

	/**
	 * 获取所有ScriptSession
	 */
	public static Collection<ScriptSession> getScriptSessions() {
		return scriptSessionMap.values();
	}
}
