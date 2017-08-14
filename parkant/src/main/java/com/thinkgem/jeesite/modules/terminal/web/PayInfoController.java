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
import com.thinkgem.jeesite.modules.terminal.entity.PayInfo;
import com.thinkgem.jeesite.modules.terminal.service.PayInfoService;

/**
 * 支付信息Controller
 * @author Hey
 * @version 2017-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/payinfo/payInfo")
public class PayInfoController extends BaseController {

	@Autowired
	private PayInfoService payInfoService;

	@ModelAttribute
	public PayInfo get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return payInfoService.get(id);
		}else{
			return new PayInfo();
		}
	}

	//@RequiresPermissions("payinfo:payInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PayInfo payInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			payInfo.setCreateBy(user);
		}
        Page<PayInfo> page = payInfoService.find(new Page<PayInfo>(request, response), payInfo);
        model.addAttribute("page", page);
		return "modules/" + "payinfo/payInfoList";
	}

	//@RequiresPermissions("payinfo:payInfo:view")
	@RequestMapping(value = "form")
	public String form(PayInfo payInfo, Model model) {
		model.addAttribute("payInfo", payInfo);
		return "modules/" + "payinfo/payInfoForm";
	}

	//@RequiresPermissions("payinfo:payInfo:edit")
	@RequestMapping(value = "save")
	public String save(PayInfo payInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, payInfo)){
			return form(payInfo, model);
		}
		payInfoService.save(payInfo);
		//addMessage(redirectAttributes, "保存支付信息'" + payInfo.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/payinfo/payInfo/?repage";
	}

	//@RequiresPermissions("payinfo:payInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		payInfoService.delete(id);
		addMessage(redirectAttributes, "删除支付信息成功");
		return "redirect:"+Global.getAdminPath()+"/payinfo/payInfo/?repage";
	}

}