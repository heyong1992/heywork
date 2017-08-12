/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.gen.dao;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.modules.gen.entity.GenTableColumn;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务表字段DAO接口
 * @author ThinkGem
 * @version 2013-10-15
 */
@Repository
public class GenTableColumnDao extends BaseDao<GenTableColumn> {
    public List<GenTableColumn> findList(GenTableColumn genScheme){
        StringBuffer hql=new StringBuffer();
        List<Object> paras = new ArrayList<Object>();
        hql.append("from GenTableColumn n where 1=1 ");
        if(genScheme.getGenTable()!=null){
            if(genScheme.getGenTable().getId()!=null){
                hql.append(" and n.genTable.id= ? ");
                paras.add(genScheme.getGenTable().getId());
            }
        }
        return super.find2(hql.toString(),paras.toArray());
    }

    public void update(GenTableColumn genScheme){
        super.getSession().update(genScheme);
    }
}
