/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.terminal.vo.CarSeatVo;
import com.thinkgem.jeesite.modules.terminal.vo.ReturnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.terminal.entity.CarSeatInfo;
import com.thinkgem.jeesite.modules.terminal.service.CarSeatInfoService;

import java.util.List;

/**
 * 车位信息Controller
 * @author Hey
 * @version 2017-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/terminal/carSeatInfo")
public class CarSeatInfoController extends BaseController {

	@Autowired
	private CarSeatInfoService carSeatInfoService;

	@ModelAttribute
	public CarSeatInfo get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return carSeatInfoService.get(id);
		}else{
			return new CarSeatInfo();
		}
	}

	/**
	 * 查询用户车辆
	 *  param :user 拥有人
	 */
	@RequestMapping(value ="list")
	@ResponseBody
	public List<CarSeatVo> list(CarSeatInfo carSeatInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<CarSeatInfo> page = carSeatInfoService.find(new Page<CarSeatInfo>(request, response), carSeatInfo);
		return carSeatInfoService.carSeatInfoConvertVo(page.getList());
	}

	/**
	 * 绑定或更新用户车辆
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public ReturnVo create(CarSeatInfo carSeatInfo, Model model, RedirectAttributes redirectAttributes) {
		carSeatInfoService.save(carSeatInfo);
		ReturnVo returnVo=new ReturnVo();
		returnVo.setSuccess("true");
		returnVo.setReason("绑定成功");
		return returnVo;
	}

	/**
	 * 解绑用户车辆
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ReturnVo delete(String id, RedirectAttributes redirectAttributes) {
		carSeatInfoService.delete(id);
		ReturnVo returnVo=new ReturnVo();
		returnVo.setSuccess("true");
		returnVo.setReason("解绑成功");
		return returnVo;
	}

}