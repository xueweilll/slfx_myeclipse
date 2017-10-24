package com.benqzl.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.MenuMapper;
import com.benqzl.pojo.system.Menu;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> getMenus() {
		return menuMapper.selectAll();
	}

	@Override
	public List<Menu> getMenusByPower() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getMenusByPareId(String Id) {
		return menuMapper.selectByPareId(Id);
	}

}
