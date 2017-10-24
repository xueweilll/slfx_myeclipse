package com.benqzl.service.system;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.core.LeeTree;
import com.benqzl.dao.system.AuthorityMapper;
/*import com.benqzl.dao.system.MenuMapper;*/
import com.benqzl.pojo.system.Authority;
import com.benqzl.pojo.system.Menu;
import com.benqzl.pojo.system.MenuAndAuthority;
@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {
/*	@Autowired
	private MenuMapper menuMapper;*/
	@Autowired
	private AuthorityMapper authorityMapper;
	@Override
	public LeeTree<Menu> selectAll(String id) {
		List<MenuAndAuthority> menus = authorityMapper.selectAll(id);
		LeeTree<Menu> root = new LeeTree<>();
		root.setId("0");
		root.setText("菜单");
		root.setIsLeaf(true);
		LeeTree<Menu> node;
		for (MenuAndAuthority menu : menus) {
			Map<String, Object> map = new HashMap<String, Object>();
			char[] ch;
			if (menu.getAuthority().getId()==null) {
				map.put("AID", 0);
				map.put("val", false);
			}else{
				map.put("AID", menu.getAuthority().getId());
				BigInteger src = new BigInteger(menu.getAuthority().getVal().toString());
				String aa = src.toString(2).toString();
				if (aa.length()<8) {
					int a= aa.length();
					for (int i = 0; i < 8-a; i++) {
						aa="0"+aa;
					}
				}
				ch = aa.toCharArray();
				map.put("val", ch);
			}
			node = new LeeTree<>();
			node.setId(menu.getMenuid());
			node.setText(menu.getMenuname());
			node.setObj(map);
			node.setPareId(menu.getPareid());
			if(node.getPareId().equals(root.getId())){
				root.addNode(recursionLeeTree(node,menus));
			}
		}
		return root;
	}
	private LeeTree<Menu> recursionLeeTree(LeeTree<Menu> parent,List<MenuAndAuthority> menus){
		LeeTree<Menu> node = null;
		for (MenuAndAuthority menu : menus) {
			if(menu.getPareid().equals(parent.getId())){
				Map<String, Object> map = new HashMap<String, Object>();
				char[] ch;
				if (menu.getAuthority().getId()==null) {
					map.put("AID", 0);
					map.put("val", false);
				}else{
					map.put("AID", menu.getAuthority().getId());
					BigInteger src = new BigInteger(menu.getAuthority().getVal().toString());
					String aa = src.toString(2).toString();
					if (aa.length()<8) {
						int a= aa.length();
						for (int i = 0; i < 8-a; i++) {
							aa="0"+aa;
						}
					}
					ch = aa.toCharArray();
					map.put("val", ch);
				}
				node = new LeeTree<>();
				node.setId(menu.getMenuid());
				node.setText(menu.getMenuname());
				node.setObj(map);
				node.setPareId(menu.getPareid());
				parent.addNode(recursionLeeTree(node,menus));
			}
		}
		return parent;
	}
	@Override
	public String save(List<Authority> authorities) {
		// TODO Auto-generated method stub
		System.out.println(authorities);
		List<Authority> list = new ArrayList<Authority>();
		for (Authority authority : authorities) {
			if(!authority.getVal().equals(new Long(0))){
				UUID uuid = UUID.randomUUID();
				authority.setId(uuid.toString());
				authority.setCreatetime(new Date());
				authority.setEdittime(new Date());
				list.add(authority);
			}
		}
		authorityMapper.deleteByPrimaryKey(authorities.get(0).getActorid());
		if(list.size()>0){
			authorityMapper.insert(list);
		}
		return null;
	}
	@Override
	public List<Authority> findAuthritys(String actorid) throws Exception {
		// TODO Auto-generated method stub
		List<MenuAndAuthority> menus = authorityMapper.selectAll(actorid);
		List<Authority> authorities = new ArrayList<>();
		for (MenuAndAuthority menuAndAuthority : menus) {
			if(menuAndAuthority.getAuthority().getId()!=null){
				Authority authority = menuAndAuthority.getAuthority();
				Menu menu = new Menu();
				menu.setMenuid(menuAndAuthority.getMenuid());
				menu.setMenuicon(menuAndAuthority.getMenuicon());
				menu.setMenuname(menuAndAuthority.getMenuname());
				menu.setMenuorder(menuAndAuthority.getMenuorder());
				menu.setMenuurl(menuAndAuthority.getMenuurl());
				menu.setPareid(menuAndAuthority.getPareid());
				menu.setIsshow(menuAndAuthority.getIsshow());
				authority.setMenu(menu);
				authorities.add(authority);
			}
			
		}
		return authorities;
	}
	

}
