/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.web;


import com.thinkgem.jeesite.modules.terminal.entity.CarSeatInfo;
import com.thinkgem.jeesite.modules.terminal.service.CarSeatInfoService;
import com.thinkgem.jeesite.modules.terminal.vo.CarSeatVo;
import com.thinkgem.jeesite.modules.terminal.vo.ReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.terminal.entity.ParkingInfo;
import com.thinkgem.jeesite.modules.terminal.service.ParkingInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 停车信息Controller
 * @author Hey
 * @version 2017-08-12
 */
@Controller
@RequestMapping(value = "${adminPath}/terminal/parkingInfo")
public class ParkingInfoController extends BaseController {

	@Autowired
	private ParkingInfoService parkingInfoService;

	@Autowired
	private CarSeatInfoService carSeatInfoService;

	@ModelAttribute
	public ParkingInfo get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return parkingInfoService.get(id);
		}else{
			return new ParkingInfo();
		}
	}

	/**
	 * 获取停车场列表
	 * @param lot 经度
	 * @param lat 纬度
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public ReturnVo list(String lot, String lat, HttpServletRequest request, HttpServletResponse response) {
		ReturnVo returnVo=new ReturnVo();
        Page<ParkingInfo> page = parkingInfoService.find(new Page<ParkingInfo>(request, response), null);
		returnVo.setList(page.getList());
		return returnVo;
	}

	/**
	 * 停车场录入
	 */
	@RequestMapping(value = "create",method=RequestMethod.POST)
	@ResponseBody
	public ReturnVo create(ParkingInfo parkingInfo, Model model) {
		ReturnVo returnVo=new ReturnVo();
		if (!beanValidator(model, parkingInfo)){
			returnVo.setSuccess("false");
			returnVo.setReason("参数格式不正确");
			return returnVo;
		}
		parkingInfoService.save(parkingInfo);
		User user = UserUtils.getUser();
		returnVo.setSuccess("true");
		returnVo.setReason("入驻成功");
		return returnVo;
	}


	/**
	 * 车位录入
	 */
	@RequestMapping(value = "carseatCreate",method=RequestMethod.POST)
	@ResponseBody
	public ReturnVo carseatCreate(CarSeatInfo carSeatInfo, Model model) {
		ReturnVo returnVo=new ReturnVo();
		carSeatInfoService.save(carSeatInfo);
		returnVo.setSuccess("true");
		returnVo.setReason("入驻成功");
		return returnVo;
	}


	/**
	 * 获取停车场车位列表
	 */
	@RequestMapping(value = "carSeatList")
	@ResponseBody
	public ReturnVo carSeatList(CarSeatInfo carSeatInfo, HttpServletRequest request, HttpServletResponse response) {
		ReturnVo returnVo=new ReturnVo();
		Page<CarSeatInfo> page = carSeatInfoService.find(new Page<CarSeatInfo>(request, response), carSeatInfo);
		CarSeatVo carSeatVo=new CarSeatVo();
		returnVo.setList(page.getList());
		return returnVo;
	}

	/**
	 * 车位解绑
	 */
	@RequestMapping(value = "deleteCarSeat")
	@ResponseBody
	public ReturnVo deleteCarSeat(String id, RedirectAttributes redirectAttributes) {
		ReturnVo returnVo=new ReturnVo();
		carSeatInfoService.delete(id);
		// RedirectAttributes 专门用于重定向之后还能带参数跳转的
		addMessage(redirectAttributes, "删除停车信息成功");
		returnVo.setSuccess("true");
		returnVo.setReason("入驻成功");
		return returnVo;
	}

}