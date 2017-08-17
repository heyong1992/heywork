/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.service;

import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.terminal.vo.CarSeatVo;
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

import java.util.ArrayList;
import java.util.List;

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
		if (carSeatInfo.getParking()!=null){
			dc.add(Restrictions.eq("parkingInfo.id", carSeatInfo.getParking().getId()));
		}
		if (carSeatInfo.getUser()!=null){
			dc.add(Restrictions.eq("user.id", carSeatInfo.getUser().getId()));
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

	public CarSeatInfo findByPuid(CarSeatInfo c) {
		String hql="from CarSeatInfo c where c.deviceNum=:p1 or (c.carSeatName=:p2 and c.parking.id=:p3)";
		CarSeatInfo carSeatInfo=carSeatInfoDao.getByHql(hql,new Parameter(c.getDeviceNum(),c.getCarSeatName(),c.getParking().getId()));
		return carSeatInfo;
	}

	public List<CarSeatVo> carSeatInfoConvertVo(List<CarSeatInfo> carSeatInfos){
		List<CarSeatVo> carSeatVos=new ArrayList<CarSeatVo>();
		CarSeatVo carSeatVo;
		for(CarSeatInfo carSeatInfo:carSeatInfos){
			carSeatVo=new CarSeatVo();
			carSeatVo.setId(carSeatInfo.getId());
			carSeatVo.setName(carSeatInfo.getCarSeatName());
			carSeatVo.setParkName(carSeatInfo.getParking().getSName());
			carSeatVos.add(carSeatVo);
		}
		return carSeatVos;
	}

}