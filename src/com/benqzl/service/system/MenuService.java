package com.benqzl.service.system;

import java.util.List;

import com.benqzl.pojo.system.Menu;

public interface MenuService {
	
	//查询所有菜单
	List<Menu> getMenus();
	
	//根据权限查询菜单
	List<Menu> getMenusByPower();
	
	//根据父节点查询菜单
	List<Menu> getMenusByPareId(String Id);
}
