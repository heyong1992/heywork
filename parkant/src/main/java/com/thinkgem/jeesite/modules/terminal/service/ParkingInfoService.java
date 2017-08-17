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
import com.thinkgem.jeesite.modules.terminal.entity.ParkingInfo;
import com.thinkgem.jeesite.modules.terminal.dao.ParkingInfoDao;

import java.util.List;

/**
 * 停车信息Service
 * @author Hey
 * @version 2017-08-12
 */
@Component
@Transactional(readOnly = true)
public class ParkingInfoService extends BaseService {

	@Autowired
	private ParkingInfoDao parkingInfoDao;

	public ParkingInfo get(String id) {
		return parkingInfoDao.get(id);
	}

	public Page<ParkingInfo> find(Page<ParkingInfo> page, ParkingInfo parkingInfo) {
		DetachedCriteria dc = parkingInfoDao.createDetachedCriteria();
		dc.add(Restrictions.eq(ParkingInfo.FIELD_DEL_FLAG, ParkingInfo.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return parkingInfoDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(ParkingInfo parkingInfo) {
		parkingInfoDao.save(parkingInfo);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		parkingInfoDao.deleteById(id);
	}

}