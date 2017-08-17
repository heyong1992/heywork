/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.terminal.entity.ShareCarseatInfo;
import com.thinkgem.jeesite.modules.terminal.service.ShareCarseatInfoService;
import com.thinkgem.jeesite.modules.terminal.vo.OrderVo;
import com.thinkgem.jeesite.modules.terminal.vo.ReturnVo;
import org.hibernate.criterion.Order;
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

import java.util.Date;
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
	@Autowired
	private ShareCarseatInfoService shareCarseatInfoService;

	/**
	 * 获取订单详情
	 * @param id 订单id
	 */
	@RequestMapping(value = "get")
	@ResponseBody
	public OrderVo get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return orderInfoService.orderConvertVo(orderInfoService.get(id));
		}else{
			return new OrderVo();
		}
	}

	/**
	 * 查询用户预订订单
	 */
	@RequestMapping(value = "byUid")
	@ResponseBody
	public ReturnVo byUid(@RequestParam(required=false) String uid) {
		OrderInfo orderInfo=orderInfoService.findByUid(uid);
		ReturnVo returnVo=new ReturnVo();
		if(orderInfo!=null){
			returnVo.setSuccess("true");
			returnVo.setObject(orderInfoService.orderConvertVo(orderInfo));
			return returnVo;
		}else{
			returnVo.setSuccess("false");
			returnVo.setReason("用户目前没有预订单");
			return returnVo;
		}

	}
	/**
	 * 查询订单列表
	 * @param orderInfo
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public List<OrderVo> list(OrderInfo orderInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<OrderInfo> page = orderInfoService.find(new Page<OrderInfo>(request, response), orderInfo);
		return orderInfoService.orderConvertVo(page.getList());
	}

	/**
	 * 预定共享车位订单
	 * @param orderInfo
	 */
	@RequestMapping(value = "create")
	@ResponseBody
	public ReturnVo create(OrderInfo orderInfo,String startDate,String endDate, Model model, RedirectAttributes redirectAttributes) {
		ReturnVo returnVo=new ReturnVo();
		OrderInfo order=orderInfoService.findByUid(orderInfo.getUse().getId());
		if(order!=null){
			returnVo.setSuccess("false");
			returnVo.setReason("发布失败:您有订单尚未处理完成");
			return returnVo;
		}
		orderInfo.setStartTime(new Date(startDate));
		orderInfo.setEndTime(new Date(endDate));
		orderInfo.setStatus("预订中");
		orderInfoService.save(orderInfo);
		returnVo.setSuccess("true");
		returnVo.setReason("发布成功");
		return returnVo;
	}

	/**
	 * 用户订单上锁
	 */
	@RequestMapping(value = "carSeatLock")
	@ResponseBody
	public ReturnVo carSeatLock(String id) {
		OrderInfo orderInfo=orderInfoService.get(id);
		orderInfo.setLeaveTime(new Date());
		orderInfo.setStatus("已完成");
		orderInfo.setPayMoney("60");
		orderInfoService.save(orderInfo);

		orderInfo.getShareCarseatInfo().setDelFlag(ShareCarseatInfo.DEL_FLAG_DELETE);
		shareCarseatInfoService.save(orderInfo.getShareCarseatInfo());

		ReturnVo returnVo=new ReturnVo();
		returnVo.setSuccess("true");
		returnVo.setReason("操作成功");
		return returnVo;
	}


}