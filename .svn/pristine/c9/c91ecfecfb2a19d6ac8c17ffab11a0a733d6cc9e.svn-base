package com.benqzl.service.oa;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.core.LeeTree;
import com.benqzl.dao.oa.FileShareMapper;
import com.benqzl.dao.oa.FilesMapper;
import com.benqzl.dao.oa.FolerMapper;
import com.benqzl.pojo.oa.FileShare;
import com.benqzl.pojo.oa.Files;
import com.benqzl.pojo.oa.Foler;

/**
 * 
 * @ClassName: FilesServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shimh
 * @date 2015年12月16日 上午10:15:32
 * 
 */
@Service("filesServie")
public class FilesServiceImpl implements FilesService {

	@Autowired
	private FilesMapper mapper;

	@Autowired
	private FolerMapper folerMapper;
	
	@Autowired
	private FileShareMapper fileShareMapper;

	@Override
	public List<Files> findByPage(Map<String, Object> map) {
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		return mapper.pageCount(map);
	}

	/*
	 * (非 Javadoc) <p>Title: selectAll</p> <p>Description: 获取所有文件Tree</p>
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see com.benqzl.service.FilesService#selectAll()
	 */

	@Override
	public LeeTree<Foler> selectFoler() {
		LeeTree<Foler> leeTree = new LeeTree<>("0", "根文件夹", "", null, true);
		List<Foler> list = folerMapper.selectAll();
		LeeTree<Foler> node;
		for (Foler foler : list) {
			node = new LeeTree<>(foler.getId(), foler.getName(),
					foler.getParentid(), foler, true);
			if (node.getPareId().equals(leeTree.getId())) {
				leeTree.addNode(recursionLeeTree(node, list));
			}
		}
		return leeTree;
	}

	private LeeTree<Foler> recursionLeeTree(LeeTree<Foler> parent,
			List<Foler> list) {
		LeeTree<Foler> node = null;
		for (Foler foler : list) {
			if (foler.getParentid().equals(parent.getId())) {
				node = new LeeTree<>(foler.getId(), foler.getName(),
						foler.getParentid(), foler, true);
				parent.addNode(recursionLeeTree(node, list));
			}
		}
		return parent;
	}

	@Override
	public int deleteFiles(String id) throws Exception {
		return mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int deleteFolder(String id) throws Exception {
		return folerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Foler foler) throws Exception {
		return folerMapper.insertSelective(foler);
	}

	@Override
	public Foler selectByPrimaryKey(String id) {
		return folerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Foler foler) {
		return folerMapper.updateByPrimaryKeySelective(foler);
	}

	@Override
	public int insertSelective(Files files) throws Exception {
		return mapper.insertSelective(files);
	}

	@Override
	public Files selectByFilesId(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(FileShare filesShare) throws Exception {
		return fileShareMapper.insertSelective(filesShare);
	}

	@Override
	public int deleteFilesShareByFilesId(String id) throws Exception {
		return fileShareMapper.deleteFilesShareByFilesId(id);
	}
}
