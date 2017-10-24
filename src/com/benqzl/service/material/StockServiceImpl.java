package com.benqzl.service.material;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.material.MaterialManageMapper;
import com.benqzl.dao.material.MonthReportMapper;
import com.benqzl.dao.material.StockItemsMapper;
import com.benqzl.dao.material.StockMapper;
import com.benqzl.dao.material.StorageMapper;
import com.benqzl.dao.system.ActivitiTaskMapper;
import com.benqzl.dao.system.UserMapper;
import com.benqzl.pojo.material.MaterialManage;
import com.benqzl.pojo.material.MonthReport;
import com.benqzl.pojo.material.Stock;
import com.benqzl.pojo.material.StockItems;
import com.benqzl.pojo.material.Storage;
import com.benqzl.pojo.system.Material;
import com.benqzl.pojo.system.User;

@Service("stockService")
public class StockServiceImpl implements StockService {
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private StockItemsMapper itemsMapper;
	@Autowired
	private UserMapper userMapper;
	@Override
	public List<Material> findMaterials(Map<String, Object> map) {
		return stockMapper.findMaterials(map);
	}
	@Override
	public void insert(Stock stock) throws Exception {
		if(!stock.getId().equals("0")){
			itemsMapper.deleteByPrimaryKey(stock.getId());
			stockMapper.deleteByPrimaryKey(stock.getId());
		}
		stock.setId(UUID.randomUUID().toString());
		stock.setState(new Long(0));
		List<StockItems> list = new ArrayList<>();
		for (StockItems stockItems : stock.getItems()) {
			StockItems items = new StockItems();
			items.setId(UUID.randomUUID().toString());
			items.setCount(stockItems.getCount());
			items.setMaterialid(stockItems.getMaterialid());
			items.setStockid(stock.getId());
			items.setCreatetime(new Date());
			items.setEdittime(new Date());
			items.setSurpluscount(stockItems.getSurpluscount());
			if(!stock.getType().equals(new Long(5))){
				items.setState(new Long(0));
			}else{
				items.setState(new Long(1));
				items.setSurpluscount(new Long(stockItems.getCount().toString()));
			}
			list.add(items);
		}
		stockMapper.insertSelective(stock);
		itemsMapper.insert(list);
	}
	@Override
	public List<Stock> findByPage(Map<String, Object> map) throws Exception {
		return stockMapper.findByPage(map);
	}
	@Override
	public int pageCount(Map<String, Object> map) throws Exception {
		return stockMapper.pageCount(map);
	}
	@Override
	public Stock selectById(Map<String, Object> map) throws Exception {
		return stockMapper.selectByIdAndType(map);
	}
	@Override
	public List<StockItems> selectItemsByStockId(String id) {
		return itemsMapper.selectByStockId(id);
	}
	@Override
	public void updateState(String id,Long state) {
		Subject subject = SecurityUtils.getSubject();
		User user=(User) subject.getSession().getAttribute("loginUser");
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("state",state);
		map.put("time",new Date());
		map.put("userid",user.getUserid());
		stockMapper.updateState(map);
		
	}
	@Override
	public List<Stock> findStockAll(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		return stockMapper.findStockAll(map);
	}
	@Override
	public void insertStockItems(List<StockItems> strings1) {
		// TODO Auto-generated method stub\
		List<String> strings = new ArrayList<>();
		for (StockItems stockItems : strings1) {
			strings.add(stockItems.getId());
		}
		itemsMapper.deleteByPrimaryById(strings);
		itemsMapper.insert(strings1);
	}
	
	@Autowired
	private StorageMapper storageMapper;
	@Autowired 
	private ActivitiTaskMapper amapper;
	@Override
	public void insertProfitAndLoss(String id) throws Exception {
		String suffix= amapper.selectMaxCode();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd"); 
		String date = bartDateFormat.format(new Date());
		String doce="SY-"+date+"-"+suffix;
		List<StockItems> items  =itemsMapper.selectByStockId(id);
		Stock parentStock = stockMapper.selectByPrimaryKey(id);
		List<String> ids = new ArrayList<>();
		for (StockItems stockItems : items) {
			ids.add(stockItems.getMaterialid());
		}
		List<Storage> storages = storageMapper.selectStorageById(ids);
		Stock stock = new Stock();
		stock.setCode(doce);
		stock.setId(UUID.randomUUID().toString());
		stock.setCreatetime(new Date());
		stock.setCreater(parentStock.getHandler());
		stock.setParentid(parentStock.getId());
		stock.setType(new Long(7));
		stock.setState(new Long(0));
		List<StockItems> list = new ArrayList<>();
		for (Storage storage : storages) {
			for (StockItems stockItems : items) {
				if(storage.getMaterialid().equals(stockItems.getMaterialid())){
					StockItems stockItems2  =new StockItems();
					stockItems2.setId(UUID.randomUUID().toString());
					stockItems2.setCount(stockItems.getCount());
					stockItems2.setMaterialid(stockItems.getMaterialid());
					stockItems2.setStockid(stock.getId());
					stockItems2.setCreatetime(new Date());
					stockItems2.setEdittime(new Date());
					stockItems2.setState(new Long(0));
					if(storage.getStorage().compareTo(stockItems.getCount())==1){//损
						stockItems2.setCount(storage.getStorage().subtract(stockItems.getCount()));
						stockItems2.setSurpluscount(new Long(1));
					}else if(storage.getStorage().compareTo(stockItems.getCount())==-1){//溢
						stockItems2.setCount(stockItems.getCount().subtract(storage.getStorage()));
						stockItems2.setSurpluscount(new Long(0));
					}
					list.add(stockItems2);
				}
			}
		}
		if(list.size()>0&&list!=null){
			stockMapper.insertSelective(stock);
			itemsMapper.insert(list);
		}
	}
	@Override
	public void delete(String id) throws Exception {
		itemsMapper.deleteByPrimaryKey(id);
		stockMapper.deleteByPrimaryKey(id);
	}
	
	@Autowired
	private MonthReportMapper reportMapper;
	@Override
	public void monthReceipts() throws Exception {
		Calendar cal = Calendar.getInstance();
		if(cal.get(Calendar.MONTH)-1<0){
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);
    	}
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		//cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();//月份最后一天  
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();//月份第一天  
		Map<String, Object> map = new HashMap<>();
		map.put("firstDate", firstDate);
		map.put("lastDate", lastDate);
		List<Storage> storages = storageMapper.selectAll(map);
		List<MonthReport> reports = new ArrayList<>();
		for (Storage storage : storages) {
			MonthReport report = new MonthReport();
			report.setId(UUID.randomUUID().toString());
			report.setCreatetime(lastDate);
			report.setStorage(storage.getStorage());
			report.setMaterialid(storage.getMaterialid());
			BigDecimal count = new BigDecimal(0);//入库
			BigDecimal count2= new BigDecimal(0);//归还
			BigDecimal count3= new BigDecimal(0);//出库
			BigDecimal count4= new BigDecimal(0);//外借
			BigDecimal count5= new BigDecimal(0);//报废
			for (MaterialManage manage : storage.getManages()) {
				if(manage.getType().equals(new Long(0))){
					if(manage.getTypedate().equals(new Long(2))){
						count2=count2.add(manage.getNewstorage().subtract(manage.getOldstorage()));
					}else{
						count=count.add(manage.getNewstorage().subtract(manage.getOldstorage()));
					}
				}else{
					if(manage.getTypedate().equals(new Long(2))){
						count4=count4.add(manage.getOldstorage().subtract(manage.getNewstorage()));
					}else if(manage.getTypedate().equals(new Long(4))){
						count5=count5.add(manage.getOldstorage().subtract(manage.getNewstorage()));
					}else{
						count3=count3.add(manage.getOldstorage().subtract(manage.getNewstorage()));
					}
				}
			}
			report.setCheckinstorage(count2);
			report.setCheckoutstorage(count4);
			report.setOutstorage(count3);
			report.setInstorage(count);
			report.setScrapstorage(count5);
			BigDecimal result = new BigDecimal(0);
			result=result.add(count).add(count2).subtract(count3).subtract(count4).subtract(count5);
			if(result.compareTo(new BigDecimal(0))==-1){
				report.setUltstorage(storage.getStorage().add(result.abs()));
			}else if(result.compareTo(new BigDecimal(0))==1){
				report.setUltstorage(storage.getStorage().subtract(result.abs()));
			}else{
				report.setUltstorage(storage.getStorage());
			}
			reports.add(report);
		}
		if(reports.size()!=0&&reports!=null){
			reportMapper.insert(reports);
		}
	}
	@Override
	public Stock findStockById(String id) {
		// TODO Auto-generated method stub
		return stockMapper.selectByPrimaryKey(id);
	}
	@Override
	public User selectUser(String id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}
	@Autowired
	private MaterialManageMapper manageMapper;
	@Override
	public Map<String, Object> findByYear()
			throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		String date = sf.format(new Date());
		int year = Integer.parseInt(date.split("-")[0]);
		int mont = Integer.parseInt(date.split("-")[1]);
		map.put("lastDate", new Date());
		Date lastDate ;
		if(mont<12){
			map.put("firstDate", sf.parse((year-1)+"-"+(mont+1)+"-1"));
			lastDate=sf.parse((year-1)+"-"+(mont+1)+"-1");
		}else{
			lastDate=sf.parse((year-1)+"-"+1+"-01");
			map.put("firstDate", sf.parse(year+"-"+1+"-1"));
		}
		System.out.println(sf.format(lastDate)+"#####################################");
		List<MaterialManage> manages = manageMapper.selectByTime(map);
		List<String> strings = new ArrayList<>();
		for (int i = 11; i >=0; i--) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-i);
			strings.add(dateFormat.format(calendar.getTime()));
		}
		List<Integer> outs = new ArrayList<>();
		List<Integer> ins = new ArrayList<>();
		for (String month : strings) {
			int out=0;
			int in=0;
			for (MaterialManage materialManage : manages) {
				String str = dateFormat.format(materialManage.getCreatetime());
				if(str.equals(month)){
					if(materialManage.getType().equals(new Long(0))){
						in++;
					}else{
						out++;
					}
				}
			}
			outs.add(out);
			ins.add(in);
		}
		Map<String, Object> map2 = new HashMap<>();
		map2.put("month", strings);
		map2.put("outs", outs);
		map2.put("ints", ins);
		return map2;
	}
}
