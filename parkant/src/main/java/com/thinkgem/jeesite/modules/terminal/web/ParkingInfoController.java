/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.web;


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

	@ModelAttribute
	public ParkingInfo get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return parkingInfoService.get(id);
		}else{
			return new ParkingInfo();
		}
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public List<ParkingInfo> list(String lot, String lat, HttpServletRequest request, HttpServletResponse response) {
		User user = UserUtils.getUser();
        Page<ParkingInfo> page = parkingInfoService.find(new Page<ParkingInfo>(request, response), null);
		return page.getList();
	}

	@RequestMapping(value = "form")
	public String form(ParkingInfo parkingInfo, Model model) {
		model.addAttribute("parkingInfo", parkingInfo);
		return "modules/" + "parkinfo/parkingInfoForm";
	}

	@RequestMapping(value = "save")
	public String save(ParkingInfo parkingInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, parkingInfo)){
			return form(parkingInfo, model);
		}
		parkingInfoService.save(parkingInfo);
		return "redirect:"+Global.getAdminPath()+"/parkinfo/parkingInfo/?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		parkingInfoService.delete(id);
		addMessage(redirectAttributes, "删除停车信息成功");
		return "redirect:"+Global.getAdminPath()+"/parkinfo/parkingInfo/?repage";
	}

}