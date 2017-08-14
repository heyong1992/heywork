/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.terminal.entity.CarSeatInfo;
import com.thinkgem.jeesite.modules.terminal.service.CarSeatInfoService;

/**
 * 车位信息Controller
 * @author Hey
 * @version 2017-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/carseatinfo/carSeatInfo")
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

	//@RequiresPermissions("carseatinfo:carSeatInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CarSeatInfo carSeatInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			carSeatInfo.setCreateBy(user);
		}
        Page<CarSeatInfo> page = carSeatInfoService.find(new Page<CarSeatInfo>(request, response), carSeatInfo);
        model.addAttribute("page", page);
		return "modules/" + "carseatinfo/carSeatInfoList";
	}

	//@RequiresPermissions("carseatinfo:carSeatInfo:view")
	@RequestMapping(value = "form")
	public String form(CarSeatInfo carSeatInfo, Model model) {
		model.addAttribute("carSeatInfo", carSeatInfo);
		return "modules/" + "carseatinfo/carSeatInfoForm";
	}

	//@RequiresPermissions("carseatinfo:carSeatInfo:edit")
	@RequestMapping(value = "save")
	public String save(CarSeatInfo carSeatInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, carSeatInfo)){
			return form(carSeatInfo, model);
		}
		carSeatInfoService.save(carSeatInfo);
		//addMessage(redirectAttributes, "保存车位信息'" + carSeatInfo.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/carseatinfo/carSeatInfo/?repage";
	}

	//@RequiresPermissions("carseatinfo:carSeatInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		carSeatInfoService.delete(id);
		addMessage(redirectAttributes, "删除车位信息成功");
		return "redirect:"+Global.getAdminPath()+"/carseatinfo/carSeatInfo/?repage";
	}

}