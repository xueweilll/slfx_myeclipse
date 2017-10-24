package com.benqzl.service.material;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.benqzl.dao.material.MaterialManageMapper;
/*import com.benqzl.dao.material.MonthReportMapper;*/
import com.benqzl.dao.material.StorageMapper;
import com.benqzl.pojo.material.MaterialManage;
import com.benqzl.pojo.material.StockItems;
import com.benqzl.pojo.material.Storage;

@Service("storageService")
@Scope("singleton")
public class StorageServiceImpl implements StorageService {
	@Autowired
	private StorageMapper storageMapper;
/*	@Autowired
	private MonthReportMapper reportMapper;*/
	@Autowired
	private MaterialManageMapper manageMapper;
	private static Lock lock = new ReentrantLock();

	@Override
	public boolean inStorage(List<StockItems> items,String type) throws Exception {
		lock.lock();
		List<String> materialIds = new ArrayList<>();
		for (StockItems stockItems : items) {
			materialIds.add(stockItems.getMaterialid());
		}
		List<Storage> storages=storageMapper.selectStorageById(materialIds);
		List<String> storageIds = new ArrayList<>();
		for (Storage storage : storages){
			storageIds.add(storage.getMaterialid());
		}
		List<String> updateStorages=new ArrayList<>();
		List<Storage> insertStorages=new ArrayList<>();
		List<MaterialManage> manages = new ArrayList<>();
		for (StockItems stockItems : items) {
			MaterialManage manage = new MaterialManage();
			manage.setId(UUID.randomUUID().toString());
			manage.setMemo(stockItems.getMemo());
			manage.setSource(stockItems.getSource());
			manage.setType(new Long(0));
			manage.setTypedate(new Long(type));
			manage.setCreatetime(new Date());
			manage.setEdittime(new Date());
			if(storageIds.contains(stockItems.getMaterialid())){
				for (Storage storage : storages) {
					if(stockItems.getMaterialid().equals(storage.getMaterialid())){
						manage.setOldstorage(storage.getStorage());
						manage.setNewstorage(storage.getStorage().add(stockItems.getCount()));
						manage.setStorageid(storage.getId());
						storage.setEdittime(new Date());
						storage.setStorage(storage.getStorage().add(stockItems.getCount()));
						storage.setInstorage(storage.getInstorage().add(stockItems.getCount()));
						insertStorages.add(storage);
						updateStorages.add(storage.getId());
					}
				}
			}else{
				Storage storage = new Storage();
				storage.setId(UUID.randomUUID().toString());
				storage.setCreatetime(new Date());
				storage.setEdittime(new Date());
				storage.setCheckoutstorage(new BigDecimal(0));
				storage.setOutstorage(new BigDecimal(0));
				storage.setScrapstorage(new BigDecimal(0));
				storage.setInstorage(stockItems.getCount());
				storage.setStorage(stockItems.getCount());
				storage.setMaterialid(stockItems.getMaterialid());
				insertStorages.add(storage);
				manage.setOldstorage(new BigDecimal(0));
				manage.setNewstorage(stockItems.getCount());
				manage.setStorageid(storage.getId());
			}
			manages.add(manage);
		}
		try{
			if(updateStorages.size()>0&&updateStorages!=null){
				storageMapper.deleteByPrimaryKey(updateStorages);
			}
			storageMapper.insert(insertStorages);
			manageMapper.insert(manages);
		}catch(Exception e){
			throw e;
		}finally{
			lock.unlock();
		}
		return true;
	}

	@Override
	public List<String> outStorage(List<StockItems> items,String type) throws Exception {
		lock.lock();
		List<String> materialIds = new ArrayList<>();
		for (StockItems stockItems : items) {
			materialIds.add(stockItems.getMaterialid());
		}
		List<Storage> storages=storageMapper.selectStorageById(materialIds);
		List<String> storageIds = new ArrayList<>();
		for (Storage storage : storages){
			storageIds.add(storage.getMaterialid());
		}
		if(!storageIds.containsAll(materialIds)){
			materialIds.removeAll(storageIds);
			lock.unlock();
			return materialIds;
		}
		List<String> updateStorages=new ArrayList<>();
		List<Storage> insertStorages=new ArrayList<>();
		List<MaterialManage> manages = new ArrayList<>();
		for (StockItems stockItems : items) {
			MaterialManage manage = new MaterialManage();
			manage.setId(UUID.randomUUID().toString());
			manage.setMemo(stockItems.getMemo());
			manage.setSource(stockItems.getSource());
			manage.setType(new Long(1));
			manage.setTypedate(new Long(type));
			manage.setCreatetime(new Date());
			manage.setEdittime(new Date());
			for (Storage storage : storages) {
				if(stockItems.getMaterialid().equals(storage.getMaterialid())){
					if(storage.getStorage().compareTo(stockItems.getCount())!=-1){
						manage.setOldstorage(storage.getStorage());
						manage.setNewstorage(storage.getStorage().subtract(stockItems.getCount()));
						manage.setStorageid(storage.getId());
						storage.setEdittime(new Date());
						storage.setStorage(storage.getStorage().subtract(stockItems.getCount()));
						if(type.equals("4")){
							storage.setScrapstorage(storage.getScrapstorage().add(stockItems.getCount()));
						}else if(type.equals("2")){
							storage.setCheckoutstorage(storage.getCheckoutstorage().add(stockItems.getCount()));
						}else{
							storage.setOutstorage(storage.getOutstorage().add(stockItems.getCount()));
						}
						insertStorages.add(storage);
						updateStorages.add(storage.getId());
						materialIds.remove(storage.getMaterialid());
					}
				}
			}
			manages.add(manage);
		}
		try{
			if(materialIds.size()==0){
				if(updateStorages.size()>0&&updateStorages!=null){
					storageMapper.deleteByPrimaryKey(updateStorages);
				}
				storageMapper.insert(insertStorages);
				manageMapper.insert(manages);
			}
		}catch(Exception e){
			throw e;
		}finally{
			lock.unlock();
		}
		return materialIds;
	}

	@Override
	public List<String> profitAndLossStorage(List<StockItems> items)
			throws Exception {
		lock.lock();
		List<StockItems> initems = new ArrayList<>();
		List<StockItems> outitems = new ArrayList<>();
		for (StockItems stockItems : items) {
			if(stockItems.getSurpluscount().equals(new Long(0))){
				initems.add(stockItems);
			}else{
				outitems.add(stockItems);
			}
		}
		List<String> materialIds = new ArrayList<>();
		for (StockItems stockItems : items) {
			materialIds.add(stockItems.getMaterialid());
		}
		List<Storage> storages=storageMapper.selectStorageById(materialIds);
		List<String> storageIds = new ArrayList<>();
		for (Storage storage : storages){
			storageIds.add(storage.getMaterialid());
		}
		if(!storageIds.containsAll(materialIds)){
			materialIds.removeAll(storageIds);
			lock.unlock();
			return materialIds;
		}
		//入库
		List<String> updateStorages=new ArrayList<>();
		List<Storage> insertStorages=new ArrayList<>();
		List<MaterialManage> manages = new ArrayList<>();
		for (StockItems stockItems : initems) {
			MaterialManage manage = new MaterialManage();
			manage.setId(UUID.randomUUID().toString());
			manage.setMemo(stockItems.getMemo());
			manage.setSource(stockItems.getSource());
			manage.setType(new Long(0));
			manage.setTypedate(new Long(0));
			manage.setCreatetime(new Date());
			manage.setEdittime(new Date());
			for (Storage storage : storages) {
				if(stockItems.getMaterialid().equals(storage.getMaterialid())){
					manage.setOldstorage(storage.getStorage());
					manage.setNewstorage(storage.getStorage().add(stockItems.getCount()));
					manage.setStorageid(storage.getId());
					storage.setEdittime(new Date());
					storage.setStorage(storage.getStorage().add(stockItems.getCount()));
					storage.setInstorage(storage.getInstorage().add(stockItems.getCount()));
					insertStorages.add(storage);
					updateStorages.add(storage.getId());
					materialIds.remove(storage.getMaterialid());
				}
			}
			manages.add(manage);
		}
		
		//出库
		for (StockItems stockItems : outitems) {
			MaterialManage manage = new MaterialManage();
			manage.setId(UUID.randomUUID().toString());
			manage.setMemo(stockItems.getMemo());
			manage.setSource(stockItems.getSource());
			manage.setType(new Long(1));
			manage.setTypedate(new Long(0));
			manage.setCreatetime(new Date());
			manage.setEdittime(new Date());
			for (Storage storage : storages) {
				if(stockItems.getMaterialid().equals(storage.getMaterialid())){
					if(storage.getStorage().compareTo(stockItems.getCount())!=-1){
						manage.setOldstorage(storage.getStorage());
						manage.setNewstorage(storage.getStorage().subtract(stockItems.getCount()));
						manage.setStorageid(storage.getId());
						storage.setEdittime(new Date());
						storage.setStorage(storage.getStorage().subtract(stockItems.getCount()));
						storage.setOutstorage(storage.getOutstorage().add(stockItems.getCount()));
						insertStorages.add(storage);
						updateStorages.add(storage.getId());
						materialIds.remove(storage.getMaterialid());
					}
				}
			}
			manages.add(manage);
		}
		
		try{
			if(materialIds.size()==0){
				if(updateStorages.size()>0&&updateStorages!=null){
					storageMapper.deleteByPrimaryKey(updateStorages);
				}
				storageMapper.insert(insertStorages);
				manageMapper.insert(manages);
			}
		}catch(Exception e){
			throw e;
		}finally{
			lock.unlock();
		}
		return materialIds;
	}



}
