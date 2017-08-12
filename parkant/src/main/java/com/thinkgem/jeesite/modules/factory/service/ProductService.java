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
import com.thinkgem.jeesite.modules.factory.entity.Product;
import com.thinkgem.jeesite.modules.factory.dao.ProductDao;

/**
 * 产品Service
 * @author ThinkGem
 * @version 2017-06-29
 */
@Component
@Transactional(readOnly = true)
public class ProductService extends BaseService {

	@Autowired
	private ProductDao productDao;
	
	public Product get(String id) {
		return productDao.get(id);
	}
	
	public Page<Product> find(Page<Product> page, Product product) {
		DetachedCriteria dc = productDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(product.getName())){
			dc.add(Restrictions.like("name", "%"+product.getName()+"%"));
		}
		dc.add(Restrictions.eq(Product.FIELD_DEL_FLAG, Product.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return productDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Product product) {
		productDao.save(product);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		productDao.deleteById(id);
	}
	
}
