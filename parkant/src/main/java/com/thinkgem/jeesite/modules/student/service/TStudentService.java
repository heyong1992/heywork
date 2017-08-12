/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.student.entity.TStudent;
import com.thinkgem.jeesite.modules.student.dao.TStudentDao;

/**
 * 单表生成Service
 * @author Hey
 * @version 2017-07-04
 */
@Component
@Transactional(readOnly = true)
public class TStudentService extends BaseService {

	@Autowired
	private TStudentDao tStudentDao;

	public TStudent get(String id) {
		return tStudentDao.get(id);
	}

	public Page<TStudent> find(Page<TStudent> page, TStudent tStudent) {
		DetachedCriteria dc = tStudentDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(tStudent.getName())){
			dc.add(Restrictions.like("name", "%"+tStudent.getName()+"%"));
		}
		dc.add(Restrictions.eq(TStudent.FIELD_DEL_FLAG, TStudent.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return tStudentDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(TStudent tStudent) {
		tStudentDao.save(tStudent);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		tStudentDao.deleteById(id);
	}

}