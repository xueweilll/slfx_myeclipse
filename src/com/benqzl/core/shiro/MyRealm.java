package com.benqzl.core.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.benqzl.pojo.system.AlarmControl;
import com.benqzl.pojo.system.AlarmShip;
import com.benqzl.pojo.system.Authority;
import com.benqzl.pojo.system.User;
import com.benqzl.service.system.AlarmShipService;
import com.benqzl.service.system.AuthorityService;
import com.benqzl.service.system.UserService;
import com.benqzl.unit.DataTypeConvert;

/**
 * @ClassName: MyRealm
 * @Description: TODO(为当前登录的Subject授予角色和权限)
 * @author lyf
 * @date 2016年1月7日 下午7:28:15
 */
public class MyRealm extends AuthorizingRealm {
	
	@Resource
	UserService userService;
	
	@Resource
	AuthorityService authorityService;
	
	@Resource
	AlarmShipService alarmShipService;

	/*
	 * (非 Javadoc) <p>Title: doGetAuthorizationInfo</p> <p>Description: </p>
	 * 
	 * @param principals
	 * 
	 * @return
	 * 
	 * @see 经测试:本例中该方法的调用时机为需授权资源被访问时
	 * 
	 * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
	 * 
	 * @see
	 * 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
	 * 
	 * @see 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
	 */
	/*@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username = (String)principals.getPrimaryPrincipal();
		try {
			User user = userService.selectByName(username);
			Actor actor = actorService.findActor(user.getUserid());
			authorizationInfo.addRole(actor.getName());
			for(Authority authority:actor.getAuthorities()){
				authority.get
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}*/

	/*
	 * (非 Javadoc) <p>Title: doGetAuthenticationInfo</p> <p>Description: </p>
	 * 
	 * @param token
	 * 
	 * @return
	 * 
	 * @throws AuthenticationException
	 * 
	 * 验证当前登录的Subject
	 * 
	 * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		//获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
        //两个token的引用都是一样的
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;  
        System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));  
//      User user = userService.getByUsername(token.getUsername());  
//      if(null != user){  
//          AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), user.getNickname());  
//          this.setSession("currentUser", user);  
//          return authcInfo;  
//      }else{  
//          return null;  
//      }  
        
        User user=null;
		try {
			user = userService.selectByName(token.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			//result = "{'result':false,'msg':'用户名或密码错误！'}";
			//return result;
		}
        
        //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息  
        //说白了就是第一个参数填登录用户名,第二个参数填合法的登录密码(可以是从数据库中取到的,本例中为了演示就硬编码了)  
        //这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证  
        /*if("mike".equals(token.getUsername())){  
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("mike", "mike", this.getName());  
            this.setSession("currentUser", "mike");
            return authcInfo;  
        }*/
		if(user !=null){
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(),user.getUserpwd(),getName());
			this.setSession("loginUser", user);
			//Actor actor = actorService.findActor(user.getUserid());
			List<Authority> Authorities = null;
			try {
				Authorities = authorityService.findAuthritys(user.getActor().getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Map<String,String[]> permissions = new HashMap<>();
			
			for(Authority authority:Authorities){
				permissions.put(authority.getMenu().getMenuurl().replace(".html", "").toLowerCase(),permission(authority.getVal()));
			}
			this.setSession("permissions", permissions);
			List<AlarmShip> alarmShips = alarmShipService.alarmShipsByUserId(user.getUserid());
			this.setSession("alarmShips", alarmShips);
			AlarmControl alarmControl = new AlarmControl();
			this.setSession("alarmControl", alarmControl);
			return authcInfo;
		}
        //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常  
        return null;  
	}
	
	private String[] permission(Long val) {
		String[] perms = new String[8];

		// byte[] bytes = Long.toString(val).getBytes();
		DataTypeConvert dataTypeConvert = new DataTypeConvert();
		byte[] bytes = dataTypeConvert.longtobytes(val);
		if (bytes[0] == 1) {
			perms[0] = "save";
		}
		if (bytes[1] == 1) {
			perms[1] = "delete";
		}
		if (bytes[2] == 1) {
			perms[2] = "aduit";
		}
		if (bytes[3] == 1) {
			perms[3] = "send";
		}
		if (bytes[4] == 1) {
			perms[4] = "transmit";
		}
		if (bytes[5] == 1) {
			perms[5] = "commit";
		}
		if (bytes[6] == 1) {
			perms[6] = "print";
		}
		if (bytes[7] == 1) {
			perms[7] = "export";
		}
		return perms;
	}
	
	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        //currentUser.hasRole("");
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}  

}
