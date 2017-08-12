/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.gen.dao;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务表DAO接口
 * @author ThinkGem
 * @version 2013-10-15
 */
@Repository
public class GenTableDao extends BaseDao<GenTable> {

    public List<GenTable> findList(GenTable genScheme){
        StringBuffer hql=new StringBuffer();
        List<Object> paras = new ArrayList<Object>();
        hql.append("from GenTable n where 1=1 ");
        return super.findAll();
    }

    public void update(GenTable genScheme){
        super.getSession().update(genScheme);
    }
	
}
