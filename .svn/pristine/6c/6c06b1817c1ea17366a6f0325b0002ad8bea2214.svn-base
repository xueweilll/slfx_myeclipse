package com.benqzl.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.benqzl.pojo.system.AlarmInfo;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.Unit;
import com.benqzl.pojo.water.PumprunItems;
import com.benqzl.pojo.water.StPptnR;
import com.benqzl.pojo.water.StPumpr;
import com.benqzl.pojo.water.TrGaterun;
import com.benqzl.pojo.water.TrPumprun;
import com.benqzl.pojo.water.TrWarnlog;
import com.benqzl.service.water.HydraulicRegimeService;
import com.benqzl.service.water.PumprunItemsService;
import com.benqzl.service.water.StPptnRService;
import com.benqzl.service.water.WarnCenterService;
import com.benqzl.service.water.WaterRegimeService;

@Service("minaServerService")
@Scope("singleton")
public class MinaServerService {
	static boolean flag = true;
	@Resource
	MessageQueue messageQueue;
	IoAcceptor acceptor;

	@Resource
	private HydraulicRegimeService hydraulicRegimeService;

	@Resource
	private WaterRegimeService waterRegimeService;

	@Resource
	private WarnCenterService warnCenterService;
	
	@Autowired
	private PumprunItemsService itemsService;

	/**
	 * 薛卫同志，这里是注入service
	 */
	@Resource
	private StPptnRService stPptnRService;

	Thread htThread;
	Thread sqThread;

	@PostConstruct
	private void init() {
		acceptor = new NioSocketAcceptor();
		acceptor.getSessionConfig().setReadBufferSize(102400);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 60);
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		TextLineCodecFactory factory = new TextLineCodecFactory(
				Charset.forName("UTF-8"), "EEFF", "EEFF");
		factory.setDecoderMaxLineLength(102400);
		factory.setEncoderMaxLineLength(102400);
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(factory));

		acceptor.setHandler(new MyHandler());

		try {
			acceptor.bind(new InetSocketAddress(8899));
		} catch (IOException e) {
			e.printStackTrace();
		}

		htThread = new Thread(new MyRunnable());
		sqThread = new Thread(new SQLRunnable());
		htThread.start();
		sqThread.start();
	}

	@PreDestroy
	public void dispose() {
		flag = false;
		htThread.interrupt();
		sqThread.interrupt();
		acceptor.unbind();
		acceptor.getFilterChain().clear();
		acceptor.dispose();
		acceptor = null;
	}

	/* Date insertDate; */

	class SQLRunnable implements Runnable {
		public void run() {
			while (flag) {
				System.out.println("---------------------------------------"
						+ flag);
				Iterator<Entry<String, List<TrGaterun>>> iterGaterun = null;
				Iterator<Entry<String, List<TrPumprun>>> iterPumprun = null;
				Iterator<Entry<String, List<StPumpr>>> iterPumpr = null;
				Iterator<Entry<String, List<TrWarnlog>>> iterWarnlog = null;
				Iterator<Entry<String, List<StPptnR>>> iterStPptnR = null;
				Entry<String, List<TrGaterun>> entryGaterun = null;
				Entry<String, List<TrPumprun>> entryPumprun = null;
				Entry<String, List<StPumpr>> entryPumpr = null;
				Entry<String, List<TrWarnlog>> entryWarnlog = null;
				Entry<String, List<StPptnR>> entryStPptnR = null;
				List<TrGaterun> listGateruns = new ArrayList<>();
				List<TrPumprun> listpumpruns = new ArrayList<>();
				List<StPumpr> listpumprs = new ArrayList<>();
				List<TrWarnlog> listwarnlogs = new ArrayList<>();
				List<StPptnR> listStPptnRs = new ArrayList<>();
				try {
					// 遍历所有接受的StPptnR
					iterStPptnR = messageQueue.concurrentsaveStPptnR.entrySet()
							.iterator();
					while (iterStPptnR.hasNext()) {
						entryStPptnR = iterStPptnR.next();
						List<StPptnR> stPptnRs = entryStPptnR.getValue();
						listStPptnRs.addAll(stPptnRs);
					}

					if (listStPptnRs != null && listStPptnRs.size() > 0) {

						/**
						 * 薛卫同志：这里是插值。
						 */
						stPptnRService.insert(listStPptnRs);
						messageQueue.concurrentsaveStPptnR.clear();
						// listGateruns = null;

					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				/*
				 * if (insertDate == null) { insertDate = new Date(); } else {
				 * if (insertDate.before(new Date())) { continue; } }
				 */

				// 遍历所有接受的Gaterun
				iterGaterun = messageQueue.concurrentsaveGateRuns.entrySet()
						.iterator();
				while (iterGaterun.hasNext()) {
					entryGaterun = iterGaterun.next();
					List<TrGaterun> gateruns = entryGaterun.getValue();
					listGateruns.addAll(gateruns);
				}

				if (listGateruns != null && listGateruns.size() > 0) {
					try {
						hydraulicRegimeService.insertGaterun(listGateruns);
						messageQueue.concurrentsaveGateRuns.clear();
						// listGateruns = null;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// 遍历所有接收的Pumprun
				iterPumprun = messageQueue.concurrentsavePumpruns.entrySet()
						.iterator();
				while (iterPumprun.hasNext()) {
					entryPumprun = iterPumprun.next();
					List<TrPumprun> pumpruns = entryPumprun.getValue();
					listpumpruns.addAll(pumpruns);
				}

				if (listpumpruns != null && listpumpruns.size() > 0) {
					try {
						hydraulicRegimeService.insertPumprun(listpumpruns);
						insertPumprunItems(listpumpruns);//插入记录值
						messageQueue.concurrentsavePumpruns.clear();
						// listpumpruns = null;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// 遍历所有接收的Pumpr
				iterPumpr = messageQueue.concurrentsavePumprs.entrySet()
						.iterator();
				while (iterPumpr.hasNext()) {
					entryPumpr = iterPumpr.next();
					List<StPumpr> pumprs = entryPumpr.getValue();
					listpumprs.addAll(pumprs);
				}

				if (listpumprs != null && listpumprs.size() > 0) {
					try {
						waterRegimeService.insert(listpumprs);
						messageQueue.concurrentsavePumprs.clear();
						// listpumprs = null;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// 遍历所有接收的Warnlog
				iterWarnlog = messageQueue.concurrentsaveTrWarnlogs.entrySet()
						.iterator();
				while (iterWarnlog.hasNext()) {
					entryWarnlog = iterWarnlog.next();
					List<TrWarnlog> warnlogs = entryWarnlog.getValue();
					listwarnlogs.addAll(warnlogs);
				}

				if (listwarnlogs != null && listwarnlogs.size() > 0) {
					try {
						// 插表
						warnCenterService.insert(listwarnlogs);
						messageQueue.concurrentsaveTrWarnlogs.clear();
						// listwarnlogs = null;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class MyRunnable implements Runnable {

		@Override
		public void run() {
			while (flag) {
				for (IoSession session : acceptor.getManagedSessions().values()) {
					session.write("AA");
					System.out
							.println("++++++++++++++++++++++++++++++++++++++++"
									+ flag);
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 关键代码 执行序列化和反序列化 进行深度拷贝
	public <T> List<T> deepCopy(List<T> src) throws IOException,
			ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);

		ByteArrayInputStream byteIn = new ByteArrayInputStream(
				byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		@SuppressWarnings("unchecked")
		List<T> dest = (List<T>) in.readObject();
		return dest;
	}

	/*
	 * public static void main(String[] args) throws IOException,
	 * ClassNotFoundException{ JSONObject jsonObject = JSON .parseObject(
	 * "{\"code\":\"lzg\",\"rains\":[{\"id\":284,\"STCD\":\"lzg\",\"TM\":\"2016-05-26 06:46:51\",\"DPR\":1.0,\"DYP\":2.0,\"hisRain\":147.0}],\"gateRuns\":[],\"pumpRuns\":[{\"fId\":18,\"fStationcode\":\"lzg\",\"fPumpcode\":\"1#泵\",\"fTime\":\"2016-05-26 08:30:00\",\"fRunstate\":\"2\",\"fDir\":\"2\",\"fVa\":234,\"fVb\":233,\"fVc\":233,\"fIa\":0.00,\"fIb\":0.00,\"fIc\":0.00},{\"fId\":19,\"fStationcode\":\"lzg\",\"fPumpcode\":\"2#泵\",\"fTime\":\"2016-05-26 08:30:00\",\"fRunstate\":\"2\",\"fDir\":\"2\",\"fVa\":234,\"fVb\":233,\"fVc\":233,\"fIa\":0.00,\"fIb\":0.00,\"fIc\":0.00},{\"fId\":20,\"fStationcode\":\"lzg\",\"fPumpcode\":\"3#泵\",\"fTime\":\"2016-05-26 08:30:00\",\"fRunstate\":\"2\",\"fDir\":\"2\",\"fVa\":234,\"fVb\":233,\"fVc\":233,\"fIa\":0.00,\"fIb\":0.00,\"fIc\":0.00},{\"fId\":21,\"fStationcode\":\"lzg\",\"fPumpcode\":\"4#泵\",\"fTime\":\"2016-05-26 08:30:00\",\"fRunstate\":\"2\",\"fDir\":\"2\",\"fVa\":234,\"fVb\":233,\"fVc\":233,\"fIa\":0.00,\"fIb\":0.00,\"fIc\":0.00}],\"pumpRs\":[{\"fid\":77597,\"stcd\":\"lzg\",\"tm\":\"2016-05-26 08:50:00\",\"ppupz\":3.796,\"ppdwz\":3.826,\"omcn\":0,\"ompwr\":0,\"pmpq\":0,\"ppupwptn\":\"6\",\"ppdwwptn\":\"6\",\"pdchcd\":\"2\"}],\"warnLogs\":[]}"
	 * );
	 * 
	 * }
	 */

	// Gson gson = new Gson();

	class MyHandler extends IoHandlerAdapter {

		@Override
		public void exceptionCaught(IoSession session, Throwable cause)
				throws Exception {
			if (session != null) {
				session.close(true);
			}
		}

		@Override
		public void messageReceived(IoSession session, Object message)
				throws Exception {

			try {
				System.out.println(message);
				JSONObject jsonObject = JSON.parseObject((String) message);
				System.out.println(jsonObject.get("code"));
				String code = (String) jsonObject.get("code");

				/**
				 * 解析雨情
				 */
				if (null != jsonObject.get("rains")) {
					try {
						List<StPptnR> stPptnRs = JSON.parseArray(jsonObject
								.get("rains").toString().toLowerCase(),
								StPptnR.class);
						for (StPptnR s : stPptnRs) {
							s.setTm(new Date());
						}
						List<StPptnR> stPptnRr = new ArrayList<>();
						stPptnRr = deepCopy(stPptnRs);
						if (stPptnRr.size() > 0) {
							messageQueue.concurrentStPptnR.put(code, stPptnRr);
						}
						messageQueue.concurrentsaveStPptnR.put(code, stPptnRs);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				/**
				 * 解析工情-闸门
				 */
				if (null != jsonObject.get("gateRuns")) {
					List<TrGaterun> gateruns = JSON.parseArray(
							jsonObject.get("gateRuns").toString(),
							TrGaterun.class);
					for (TrGaterun g : gateruns) {
						g.setfTime(new Date());
					}
					List<TrGaterun> gaterunr = new ArrayList<>();
					gaterunr = deepCopy(gateruns);
					if (gaterunr.size() > 0) {
						messageQueue.concurrentGateRuns.put(code, gaterunr);
					}
					messageQueue.concurrentsaveGateRuns.put(code, gateruns);
				}

				/**
				 * 解析工情-机组
				 */
				if (jsonObject.get("pumpRuns") != null) {
					List<TrPumprun> pumpruns = JSON.parseArray(
							jsonObject.get("pumpRuns").toString(),
							TrPumprun.class);
					for (TrPumprun p : pumpruns) {
						p.setfTime(new Date());
					}
					List<TrPumprun> pumprunr = new ArrayList<>();
					pumprunr = deepCopy(pumpruns);
					if (pumprunr.size() > 0) {
						messageQueue.concurrentPumpruns.put(code, pumprunr);
					}
					messageQueue.concurrentsavePumpruns.put(code, pumpruns);
				}

				/**
				 * 解析水情
				 */
				if (null != jsonObject.get("pumpRs")) {
					List<StPumpr> pumprs = JSON.parseArray(
							jsonObject.get("pumpRs").toString(), StPumpr.class);

					if (pumprs == null) {
						return;
					}

					if (pumprs.size() == 0) {
						return;
					}

					for (StPumpr s : pumprs) {
						s.setTm(new Date());
					}

					StPumpr stPumpr = pumprs.get(pumprs.size() - 1);
					System.out.println(code);
					Station station = messageQueue.getStationByCode(code);
					System.out.println(station.getName());
					if (station != null) {
						// ppdwz,ppupz
						AlarmInfo alarmInfo = station.getAlarmInfo(
								stPumpr.getPpdwz(), stPumpr.getPpupz());
						messageQueue.concurrentAlarmInfo.put(code, alarmInfo);
					}

					List<StPumpr> pumprr = new ArrayList<>();
					pumprr = deepCopy(pumprs);
					if (pumprr.size() > 0) {
						messageQueue.concurrentPumprs.put(code, pumprr);
					}
					messageQueue.concurrentsavePumprs.put(code, pumprs);
				}
				/*
				 * List<TrWarnlog> warnlogs =
				 * JSON.parseArray(jsonObject.get("warnLogs").toString(),
				 * TrWarnlog.class);
				 * 
				 * List<TrWarnlog> warnlogr = new ArrayList<>(); warnlogr =
				 * deepCopy(warnlogs); if (warnlogr.size() > 0) {
				 * messageQueue.concurrentTrWarnlogs.put(code, warnlogr); }
				 * messageQueue.concurrentsaveTrWarnlogs.put(code, warnlogs);
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void sessionIdle(IoSession session, IdleStatus status)
				throws Exception {
			if (session != null) {
				session.close(true);
			}
		}

	}
	
	Logger logger = Logger.getLogger(MinaServerService.class);
	//插入机组运行记录值调用方法
	public   int insertPumprunItems(List<TrPumprun> record) {
		// 取得本系统所有泵站信息，并且按照枢纽顺序排好
		List<Unit> units = new ArrayList<>();
		Iterator<String> unititer = messageQueue.unitMap.keySet().iterator();
		while (unititer.hasNext()) {
			Object key = unititer.next();
			Unit unit = messageQueue.unitMap.get(key);
			if (unit != null) {
				if (unit.getStation() != null) {
					units.add(unit);
				}
			}
		}
		List<PumprunItems> startItems = new ArrayList<>();
		List<String> endItems = new ArrayList<>();
		for (TrPumprun pumprun : record) {
			logger.info(pumprun.toString());
			String tcode = pumprun.getfStationcode() + pumprun.getfPumpcode();
			for (Unit unit : units) {
				String ucode = unit.getStation().getCode() + unit.getCode();
				if (tcode.equals(ucode)) {
					if (unit.getStates() != Integer.parseInt(pumprun.getfRunstate())) {
						if (Integer.parseInt(pumprun.getfRunstate()) == 1) {// 机组运行，插入运行记录
							PumprunItems items = new PumprunItems();
							items.setId(UUID.randomUUID().toString());
							items.setPid(unit.getId());
							items.setStarttime(new Date());
							items.setEndtime(new Date());
							startItems.add(items);
						}
						if (Integer.parseInt(pumprun.getfRunstate()) == 2) {// 机组停止，插入停止记录
							endItems.add(unit.getId());
						}
						unit.setStates(Integer.parseInt(pumprun.getfRunstate()));
						messageQueue.unitMap.put(unit.getId(), unit);
					}else{
						if (Integer.parseInt(pumprun.getfRunstate()) == 1) {// 机组运行，插入运行记录
							endItems.add(unit.getId());
						}
						unit.setStates(Integer.parseInt(pumprun.getfRunstate()));
						messageQueue.unitMap.put(unit.getId(), unit);
					}
				}
			}
		}
		logger.info("插入"+startItems.toString());
		logger.info("更新"+endItems.toString());
		return itemsService.insert(startItems, endItems);
	}
}
