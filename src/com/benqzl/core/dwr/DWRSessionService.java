package com.benqzl.core.dwr;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.directwebremoting.extend.Calls;
import org.directwebremoting.extend.Replies;
import org.directwebremoting.impl.DefaultRemoter;

public class DWRSessionService extends DefaultRemoter {
	public Replies execute(Calls calls) {
		//HttpSession session = WebContextFactory.get().getSession();
		Subject subject = SecurityUtils.getSubject();
		// session检查
		if (!subject.isAuthenticated()) {
			/*logOut();*/
			return null;
		}
		return super.execute(calls);
	}

	/*@SuppressWarnings("deprecation")
	private void logOut() {
		System.out.println("dwr session过期");
		WebContext wct = WebContextFactory.get();
		Util utilThis = new Util(wct.getScriptSession());
		utilThis.addScript(new ScriptBuffer("logout()"));

	}*/
}
