/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.gen.dao;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.gen.entity.GenScheme;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成方案DAO接口
 * @author ThinkGem
 * @version 2013-10-15
 */
@Repository
public class GenSchemeDao extends BaseDao<GenScheme> {

    public List<GenScheme> findList(GenScheme genScheme){
        StringBuffer hql=new StringBuffer();
        List<Object> paras = new ArrayList<Object>();
        hql.append("from GenScheme n where 1=1 ");

        return super.findAll();
    }

    public void update(GenScheme genScheme){
        super.getSession().update(genScheme);
    }
	
}
