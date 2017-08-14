/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.service;

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
		//if (StringUtils.isNotEmpty(orderInfo.getName())){
			//dc.add(Restrictions.like("name", "%"+orderInfo.getName()+"%"));
		//}
		dc.add(Restrictions.eq(OrderInfo.FIELD_DEL_FLAG, OrderInfo.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return orderInfoDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(OrderInfo orderInfo) {
		orderInfoDao.save(orderInfo);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		orderInfoDao.deleteById(id);
	}

}