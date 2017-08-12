/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.gen.dao;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.gen.entity.GenTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码模板DAO接口
 * @author ThinkGem
 * @version 2013-10-15
 */
@Repository
public class GenTemplateDao extends BaseDao<GenTemplate> {

    public List<GenTemplate> findList(GenTemplate genScheme){
        StringBuffer hql=new StringBuffer();
        List<Object> paras = new ArrayList<Object>();
        hql.append("from GenTemplate n where 1=1 ");
        return super.findAll();
    }

    public void update(GenTemplate genScheme){
        super.getSession().update(genScheme);
    }
	
}
