/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.factory.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.factory.entity.Student;
import com.thinkgem.jeesite.modules.factory.dao.StudentDao;

/**
 * 产品Service
 * @author ThinkGem
 * @version 2017-06-29
 */
@Component
@Transactional(readOnly = true)
public class StudentService extends BaseService {

	@Autowired
	private StudentDao studentDao;
	
	public Student get(String id) {
		return studentDao.get(id);
	}
	
	public Page<Student> find(Page<Student> page, Student student) {
		DetachedCriteria dc = studentDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(student.getName())){
			dc.add(Restrictions.like("name", "%"+student.getName()+"%"));
		}
		//dc.add(Restrictions.eq(Student.FIELD_DEL_FLAG, Student.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return studentDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Student student) {
		studentDao.save(student);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		studentDao.deleteById(id);
	}
	
}
