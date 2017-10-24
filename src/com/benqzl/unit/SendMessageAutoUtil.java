package com.benqzl.unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
public class SendMessageAutoUtil {
	// 消息反推 指定某个客户端的页面显示
	public static void sendMessageAuto(String userid) {
		final String yhId = userid;// 页面标志
		// 执行推送
		Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
			public boolean match(ScriptSession scriptSession) {
				String yhid = (String) scriptSession.getAttribute("yhId");
				if (yhid != null) {
					return yhId.equals(yhid);
				} else {
					return false;
				}
			}
		}, new Runnable() {
			private ScriptBuffer script = new ScriptBuffer();

			public void run() {
				// 设置要调用的 js及参数
				script.appendCall("mCenter","");
				// 得到过滤之后的ScriptSession
				Collection<ScriptSession> sessions = Browser
						.getTargetSessions();
				// 遍历每一个ScriptSession
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(script);
				}
			}
		});// 注意这里调用了有filter功能的方法
	}

	// 消息推送 指定某个客户端的页面显示 此方法是把要推送的ID放到集合
	public static void sendMessageListAuto( List<String> targetYhidList) {
		final List<String> targetIdList = targetYhidList;
		//final String autoMessage = "";
		List<String> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		for (String string : targetIdList) {
			if(!list.contains(string)){
				list.add(string);
				map.put(string, 1);
			}else{
				int count = (int) map.get(string);
				map.put(string, count+1);
			}
		}
		final Map<String, Object> maps = map;
		// 执行推送
		Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
			public boolean match(ScriptSession scriptSession) {
				String yhid = (String) scriptSession.getAttribute("yhId");
				if (yhid != null && targetIdList.contains(yhid)) {
					// return yhid.equals(yhId);
					targetIdList.remove(yhid);// 如果找到了，说明将被推送，所以不用再处理,剩下的都是要被处理的
					return true;
				} else {
					return false;
				}
			}
		}, new Runnable() {
			//private ScriptBuffer script = new ScriptBuffer();
			public void run() {
				// 设置要调用的 js及参数
				// 得到过滤之后的ScriptSession
				Collection<ScriptSession> sessions = Browser
						.getTargetSessions();
				// 遍历每一个ScriptSession
				for (ScriptSession scriptSession : sessions) {
					ScriptBuffer script = new ScriptBuffer();
					String yhid = (String) scriptSession.getAttribute("yhId");
					script.appendCall("receiveMessages",maps.get(yhid));
					scriptSession.addScript(script);
				}
			}
		});// 注意这里调用了有filter功能的方法
	}
	
	// 消息推送 指定某个客户端的页面显示 此方法是把要推送的ID放到集合
		public static void sendMessageByPage() {
			// 执行推送
			Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
				public boolean match(ScriptSession scriptSession) {
					return true;
				}
			},(new Runnable() {
				ScriptBuffer sBuffer = new ScriptBuffer();  
	            public void run() {  
	                sBuffer.appendCall("informationMessage", "00000");  
	                Collection<ScriptSession> sessions = Browser.getTargetSessions();  
	                for (ScriptSession scriptSession : sessions) {  
	                    scriptSession.addScript(sBuffer);  
	                }  
	            }  
			}));
		}
}
