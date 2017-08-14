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
import com.thinkgem.jeesite.modules.terminal.entity.OrderInfo;
import com.thinkgem.jeesite.modules.terminal.service.OrderInfoService;

/**
 * 订单信息Controller
 * @author Hey
 * @version 2017-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/orderinfo/orderInfo")
public class OrderInfoController extends BaseController {

	@Autowired
	private OrderInfoService orderInfoService;

	@ModelAttribute
	public OrderInfo get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return orderInfoService.get(id);
		}else{
			return new OrderInfo();
		}
	}

	//@RequiresPermissions("orderinfo:orderInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderInfo orderInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			orderInfo.setCreateBy(user);
		}
        Page<OrderInfo> page = orderInfoService.find(new Page<OrderInfo>(request, response), orderInfo);
        model.addAttribute("page", page);
		return "modules/" + "orderinfo/orderInfoList";
	}

	//@RequiresPermissions("orderinfo:orderInfo:view")
	@RequestMapping(value = "form")
	public String form(OrderInfo orderInfo, Model model) {
		model.addAttribute("orderInfo", orderInfo);
		return "modules/" + "orderinfo/orderInfoForm";
	}

	//@RequiresPermissions("orderinfo:orderInfo:edit")
	@RequestMapping(value = "save")
	public String save(OrderInfo orderInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderInfo)){
			return form(orderInfo, model);
		}
		orderInfoService.save(orderInfo);
		//addMessage(redirectAttributes, "保存订单信息'" + orderInfo.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/orderinfo/orderInfo/?repage";
	}

	//@RequiresPermissions("orderinfo:orderInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		orderInfoService.delete(id);
		addMessage(redirectAttributes, "删除订单信息成功");
		return "redirect:"+Global.getAdminPath()+"/orderinfo/orderInfo/?repage";
	}

}