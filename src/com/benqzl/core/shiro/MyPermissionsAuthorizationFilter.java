package com.benqzl.core.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

public class MyPermissionsAuthorizationFilter extends
		PermissionsAuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		String url = request.getLocalAddr();
		System.out.println(url);
		return super.isAccessAllowed(request, response, mappedValue);
		//return false;
	}

}
