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
import com.thinkgem.jeesite.modules.terminal.entity.PayInfo;
import com.thinkgem.jeesite.modules.terminal.dao.PayInfoDao;

/**
 * 支付信息Service
 * @author Hey
 * @version 2017-08-14
 */
@Component
@Transactional(readOnly = true)
public class PayInfoService extends BaseService {

	@Autowired
	private PayInfoDao payInfoDao;

	public PayInfo get(String id) {
		return payInfoDao.get(id);
	}

	public Page<PayInfo> find(Page<PayInfo> page, PayInfo payInfo) {
		DetachedCriteria dc = payInfoDao.createDetachedCriteria();
		//if (StringUtils.isNotEmpty(payInfo.getName())){
			//dc.add(Restrictions.like("name", "%"+payInfo.getName()+"%"));
		//}
		dc.add(Restrictions.eq(PayInfo.FIELD_DEL_FLAG, PayInfo.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return payInfoDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(PayInfo payInfo) {
		payInfoDao.save(payInfo);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		payInfoDao.deleteById(id);
	}

}