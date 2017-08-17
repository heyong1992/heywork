/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.service;

import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.terminal.entity.CarSeatInfo;
import com.thinkgem.jeesite.modules.terminal.vo.ShareCarSeatVo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.terminal.entity.ShareCarseatInfo;
import com.thinkgem.jeesite.modules.terminal.dao.ShareCarseatInfoDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 共享车位信息Service
 * @author Hey
 * @version 2017-08-14
 */
@Component
@Transactional(readOnly = true)
public class ShareCarseatInfoService extends BaseService {

	@Autowired
	private ShareCarseatInfoDao shareCarseatInfoDao;

	public ShareCarseatInfo get(String id) {
		return shareCarseatInfoDao.get(id);
	}

	public Page<ShareCarseatInfo> find(Page<ShareCarseatInfo> page, ShareCarseatInfo shareCarseatInfo) {
		DetachedCriteria dc = shareCarseatInfoDao.createDetachedCriteria();
		if (shareCarseatInfo.getParking()!=null){
			dc.add(Restrictions.eq("parking.id", shareCarseatInfo.getParking().getId()));
		}
		if (shareCarseatInfo.getUser()!=null){
			dc.add(Restrictions.eq("user.id", shareCarseatInfo.getUser().getId()));
		}
		dc.add(Restrictions.eq(ShareCarseatInfo.FIELD_DEL_FLAG, ShareCarseatInfo.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return shareCarseatInfoDao.find(page, dc);
	}

	public ShareCarseatInfo findByCid(String cid){
		String hql="from ShareCarseatInfo s where s.carSeat.id=:p1 and s.delFlag=:p2";
		ShareCarseatInfo shareCarseatInfo=shareCarseatInfoDao.getByHql(hql,new Parameter(cid,ShareCarseatInfo.DEL_FLAG_NORMAL));
		return shareCarseatInfo;
	}

	@Transactional(readOnly = false)
	public void save(ShareCarseatInfo shareCarseatInfo) {
		shareCarseatInfoDao.save(shareCarseatInfo);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		shareCarseatInfoDao.deleteById(id);
	}

	public List<ShareCarseatInfo> findByParking(String pid,String userId){
		DetachedCriteria dc = shareCarseatInfoDao.createDetachedCriteria();
		if(StringUtils.isNotBlank(pid)){
			dc.add(Restrictions.eq("parking.id", pid));
		}
		if(StringUtils.isNotBlank(userId)){
			dc.add(Restrictions.eq("user.id", userId));
		}

		dc.add(Restrictions.eq(ShareCarseatInfo.FIELD_DEL_FLAG, ShareCarseatInfo.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return shareCarseatInfoDao.find(dc);
	}

	public List<ShareCarSeatVo> shareCarSeatConvertVo(List<ShareCarseatInfo> shareCarseatInfos){
		List<ShareCarSeatVo> shareCarSeatVos=new ArrayList<ShareCarSeatVo>();
		ShareCarSeatVo seatVo;
		for(ShareCarseatInfo shareCarseatInfo:shareCarseatInfos){
			seatVo=new ShareCarSeatVo();
			seatVo.setId(shareCarseatInfo.getId());
			seatVo.setCarSeatName(shareCarseatInfo.getCarSeat().getCarSeatName());
			seatVo.setStartTime(DateUtils.formatDate(shareCarseatInfo.getStartTime(),"yyyy-MM-dd hh:mm"));
			seatVo.setEndTime(DateUtils.formatDate(shareCarseatInfo.getEndTime(),"yyyy-MM-dd hh:mm"));
			seatVo.setParkingName(shareCarseatInfo.getParking().getSName());
			seatVo.setHaverId(shareCarseatInfo.getUser().getId());
			shareCarSeatVos.add(seatVo);
		}
		return shareCarSeatVos;
	}

}