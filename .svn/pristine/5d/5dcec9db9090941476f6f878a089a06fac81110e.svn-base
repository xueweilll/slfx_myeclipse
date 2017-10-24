package com.benqzl.unit;
import java.util.Date;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
@Controller
@RemoteProxy(name="directController")
public class DirectController {
    @RemoteMethod
    public void onPageLoad(final String yhId) {
    	System.out.println("====================开始创建=====================");
    	System.out.println(new Date());
        ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
        scriptSession.setAttribute("yhId", yhId);
        System.out.println(new Date());
        System. out.println("创建连接+"+yhId); 
        System.out.println("====================结束创建=====================");
    }
}

