/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.terminal.entity.CarSeatInfo;
import com.thinkgem.jeesite.modules.terminal.dao.CarSeatInfoDao;

/**
 * 车位信息Service
 * @author Hey
 * @version 2017-08-14
 */
@Component
@Transactional(readOnly = true)
public class CarSeatInfoService extends BaseService {

	@Autowired
	private CarSeatInfoDao carSeatInfoDao;

	public CarSeatInfo get(String id) {
		return carSeatInfoDao.get(id);
	}

	public Page<CarSeatInfo> find(Page<CarSeatInfo> page, CarSeatInfo carSeatInfo) {
		DetachedCriteria dc = carSeatInfoDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(carSeatInfo.getParkingInfo().getId())){
			dc.add(Restrictions.eq("parkingInfo.id", carSeatInfo.getParkingInfo().getId()));
		}
		dc.add(Restrictions.eq(CarSeatInfo.FIELD_DEL_FLAG, CarSeatInfo.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return carSeatInfoDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(CarSeatInfo carSeatInfo) {
		carSeatInfoDao.save(carSeatInfo);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		carSeatInfoDao.deleteById(id);
	}

}