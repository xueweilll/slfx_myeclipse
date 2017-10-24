package com.benqzl.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.benqzl.controller.BasicController;
import com.benqzl.pojo.system.AlarmShip;
import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;
import com.benqzl.service.system.AlarmShipService;
import com.benqzl.socket.MessageQueue;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("alarmShip")
public class AlarmShipController extends BasicController {

	public AlarmShipController() {
		super(AlarmShipController.class);
	}

	@Resource
	MessageQueue messageQueue;

	@Resource
	AlarmShipService alarmShipService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/system/alarmShip");
		return mv;
	}

	@RequestMapping(value = "bind", method = RequestMethod.POST)
	@ResponseBody
	public String bind() {
		String result = "";

		List<Station> stations = messageQueue.getStations();
		gson = new Gson();
		result = gson.toJson(stations);

		return result;
	}

	@RequestMapping(value = "bindShip", method = RequestMethod.POST)
	@ResponseBody
	public String bindShip(HttpServletRequest request) {
		String result = "";

		User user = (User) request.getSession().getAttribute("loginUser");
		List<AlarmShip> alarmShips = alarmShipService.alarmShipsByUserId(user.getUserid());
		if (alarmShips == null) {
			return null;
		}
		for (AlarmShip alarmShip : alarmShips) {
			result += alarmShip.getStationcode();
			if (result != "") {
				result += ",";
			}
		}

		return result;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request, String codes) {
		String result = "";
		User user = (User) request.getSession().getAttribute("loginUser");

		alarmShipService.deleteAlarmShipsByUserId(user.getUserid());
		AlarmShip alarmShip = null;
		List<AlarmShip> alarmShips = new ArrayList<>();
		if (!codes.isEmpty()) {
			String[] codeArr = codes.split(",");
			if (codeArr == null) {
				return null;
			}

			if (codeArr.length == 0) {
				return null;
			}

			for (String code : codeArr) {
				alarmShip = new AlarmShip();
				alarmShip.setId(UUID.randomUUID().toString());
				alarmShip.setUserid(user.getUserid());
				alarmShip.setStationcode(code);
				alarmShips.add(alarmShip);
			}
			alarmShipService.saveAlarmShips(alarmShips);
		}
		
		request.getSession().setAttribute("alarmShips", alarmShips);
		result = "{'result':'true','msg':'保存成功！'}";
		return result;
	}
}
