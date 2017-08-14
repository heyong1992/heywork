/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.terminal.entity.OrderInfo;
import com.thinkgem.jeesite.modules.terminal.service.OrderInfoService;

import java.util.List;

/**
 * 订单信息Controller
 * @author Hey
 * @version 2017-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/terminal/orderInfo")
public class OrderInfoController extends BaseController {

	@Autowired
	private OrderInfoService orderInfoService;

	/**
	 * 获取订单详情
	 * @param id 订单id
	 */
	@RequestMapping(value = "get")
	@ResponseBody
	public OrderInfo get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return orderInfoService.get(id);
		}else{
			return new OrderInfo();
		}
	}
	/**
	 * 查询订单列表
	 * @param orderInfo
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public List<OrderInfo> list(OrderInfo orderInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<OrderInfo> page = orderInfoService.find(new Page<OrderInfo>(request, response), orderInfo);
		return page.getList();
	}

	/**
	 * 创建订单
	 * @param orderInfo
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public ReturnVo create(OrderInfo orderInfo, Model model, RedirectAttributes redirectAttributes) {
		orderInfoService.save(orderInfo);
		ReturnVo returnVo=new ReturnVo();
		returnVo.setSuccess("true");
		returnVo.setReason("发布成功");
		return returnVo;
	}


}