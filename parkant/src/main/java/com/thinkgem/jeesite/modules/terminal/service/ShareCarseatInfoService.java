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
import com.thinkgem.jeesite.modules.terminal.entity.ShareCarseatInfo;
import com.thinkgem.jeesite.modules.terminal.dao.ShareCarseatInfoDao;

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
		//if (StringUtils.isNotEmpty(shareCarseatInfo.getName())){
			//dc.add(Restrictions.like("name", "%"+shareCarseatInfo.getName()+"%"));
		//}
		dc.add(Restrictions.eq(ShareCarseatInfo.FIELD_DEL_FLAG, ShareCarseatInfo.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return shareCarseatInfoDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(ShareCarseatInfo shareCarseatInfo) {
		shareCarseatInfoDao.save(shareCarseatInfo);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		shareCarseatInfoDao.deleteById(id);
	}

}