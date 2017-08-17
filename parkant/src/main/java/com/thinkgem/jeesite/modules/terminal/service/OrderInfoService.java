/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.service;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.terminal.vo.OrderVo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.terminal.entity.OrderInfo;
import com.thinkgem.jeesite.modules.terminal.dao.OrderInfoDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单信息Service
 * @author Hey
 * @version 2017-08-14
 */
@Component
@Transactional(readOnly = true)
public class OrderInfoService extends BaseService {

	@Autowired
	private OrderInfoDao orderInfoDao;

	public OrderInfo get(String id) {
		return orderInfoDao.get(id);
	}

	public Page<OrderInfo> find(Page<OrderInfo> page, OrderInfo orderInfo) {
		DetachedCriteria dc = orderInfoDao.createDetachedCriteria();
		if (orderInfo.getUse()!=null){
			dc.add(Restrictions.like("use.id", orderInfo.getUse().getId()));
		}
		dc.add(Restrictions.eq(OrderInfo.FIELD_DEL_FLAG, OrderInfo.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return orderInfoDao.find(page, dc);
	}

	public OrderInfo findByUid(String uid) {
		String hql="from OrderInfo o where o.status='预订中' and o.use.id='"+uid+"'";
		OrderInfo orderInfo=orderInfoDao.getByHql(hql);
		return orderInfo;
	}

	@Transactional(readOnly = false)
	public void save(OrderInfo orderInfo) {
		orderInfoDao.save(orderInfo);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		orderInfoDao.deleteById(id);
	}

	public List<OrderVo> orderConvertVo(List<OrderInfo> orderInfos){
		List<OrderVo> orderVos=new ArrayList<OrderVo>();
		OrderVo orderVo;

		for(OrderInfo orderInfo:orderInfos){
			orderVo=new OrderVo();
			orderVo.setId(orderInfo.getId());
			orderVo.setHaverName(orderInfo.getHaver().getName());
			orderVo.setHaverPhone(orderInfo.getHaver().getPhone());
			orderVo.setCarSeatNum(orderInfo.getShareCarseatInfo().getCarSeat().getCarSeatName());
			orderVo.setParkingName(orderInfo.getShareCarseatInfo().getParking().getSName());
			orderVo.setShareStartDate(DateUtils.formatDate(orderInfo.getShareCarseatInfo().getStartTime(),"yyyy-MM-dd hh:mm"));
			orderVo.setShareEndDate(DateUtils.formatDate(orderInfo.getShareCarseatInfo().getEndTime(),"yyyy-MM-dd hh:mm"));
			orderVo.setStartDate(DateUtils.formatDate(orderInfo.getStartTime(),"yyyy-MM-dd hh:mm"));
			orderVo.setStatus(orderInfo.getStatus());
			orderVos.add(orderVo);
		}
		return orderVos;
	}

	public OrderVo orderConvertVo(OrderInfo orderInfo){
		OrderVo orderVo=new OrderVo();;
		orderVo.setId(orderInfo.getId());
		orderVo.setHaverName(orderInfo.getHaver().getName());
		orderVo.setHaverPhone(orderInfo.getHaver().getPhone());
		orderVo.setCarSeatNum(orderInfo.getShareCarseatInfo().getCarSeat().getCarSeatName());
		orderVo.setParkingName(orderInfo.getShareCarseatInfo().getParking().getSName());
		orderVo.setShareStartDate(DateUtils.formatDate(orderInfo.getShareCarseatInfo().getStartTime(),"yyyy-MM-dd hh:mm"));
		orderVo.setShareEndDate(DateUtils.formatDate(orderInfo.getShareCarseatInfo().getEndTime(),"yyyy-MM-dd hh:mm"));
		orderVo.setStartDate(DateUtils.formatDate(orderInfo.getStartTime(),"yyyy-MM-dd hh:mm"));
		orderVo.setEndDate(DateUtils.formatDate(orderInfo.getEndTime(),"yyyy-MM-dd hh:mm"));
		return orderVo;
	}

}