/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.gen.dao;

import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;
import com.thinkgem.jeesite.modules.gen.entity.GenTableColumn;
import com.thinkgem.jeesite.modules.gen.entity.GenTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * 业务表字段DAO接口
 * @author ThinkGem
 * @version 2013-10-15
 */
@Repository
public class GenDataBaseDictDao extends BaseDao<GenTableColumn> {

    public List<GenTableColumn> findList(GenTableColumn genScheme){
        StringBuffer hql=new StringBuffer();
        List<Object> paras = new ArrayList<Object>();
        hql.append("from GenTableColumn n where 1=1 ");
        return super.findAll();
    }

    public void update(GenTableColumn genScheme){
        super.getSession().update(genScheme);
    }

    public List<GenTable> findTableList(GenTable genTable){
        List<GenTable> returnList=new ArrayList<GenTable>();
        StringBuffer sql=new StringBuffer();
        List<Object> paras = new ArrayList<Object>();
        sql.append(" SELECT t.table_name AS name,t.TABLE_COMMENT AS comments ");
        sql.append(" FROM information_schema.TABLES t  ");
        sql.append(" WHERE t.TABLE_SCHEMA = (select database()) ");
        if(StringUtils.isNotBlank(genTable.getName())){
            sql.append(" AND t.table_name = ? ");
            paras.add(genTable.getName());
        }
        sql.append(" ORDER BY t.table_name ");
        List<Object[]> list=super.executeSQLQuery(sql.toString(),paras);
        for(Object[] obj:list){
            GenTable g=new GenTable();
            g.setName(obj[0].toString());
            if(obj[1]!=null){
            g.setComments(obj[1].toString());
            }
            returnList.add(g);
        }

        return returnList;
    }

    public List<GenTableColumn> findTableColumnList(GenTable genTable){
        List<GenTableColumn> returnList=new ArrayList<GenTableColumn>();
        StringBuffer sql=new StringBuffer();
        List<Object> paras = new ArrayList<Object>();
        sql.append(" SELECT t.COLUMN_NAME AS name, (CASE WHEN t.IS_NULLABLE = 'YES' THEN '1' ELSE '0' END) ");
        sql.append(" AS isNull,(t.ORDINAL_POSITION * 10) AS sort,t.COLUMN_COMMENT AS comments,t.COLUMN_TYPE AS jdbcType  ");
        sql.append(" FROM information_schema.`COLUMNS` t  ");
        sql.append(" WHERE t.TABLE_SCHEMA = (select database()) ");
        if(StringUtils.isNotBlank(genTable.getName())){
            sql.append(" AND t.TABLE_NAME = ? ");
            paras.add(genTable.getName());
        }
        sql.append(" ORDER BY t.ORDINAL_POSITION ");
        List<Object[]> list=super.executeSQLQuery(sql.toString(),paras);
        for(Object[] obj:list){
            GenTableColumn g=new GenTableColumn();
            g.setName(obj[0].toString());
            g.setIsNull(obj[1].toString());
            g.setSort(Integer.parseInt(obj[2].toString()));
            if(obj[3]!=null){
                g.setComments(obj[3].toString());
            }
            g.setJdbcType(obj[4].toString());
            returnList.add(g);
        }

        return returnList;
    }

    public List<String> findTablePK(GenTable genTable){
        List<String> returnList=new ArrayList<String>();
        StringBuffer sql=new StringBuffer();
        List<Object> paras = new ArrayList<Object>();
        sql.append(" SELECT lower(au.COLUMN_NAME) AS columnName,0  ");
        sql.append(" FROM information_schema.`COLUMNS` au  ");
        sql.append(" WHERE au.TABLE_SCHEMA = (select database())  ");
        sql.append(" AND au.TABLE_NAME = ? ");
        paras.add(genTable.getName());
        List<Object[]> list=super.executeSQLQuery(sql.toString(),paras);
        for(Object[] obj:list){
            returnList.add(obj[0].toString());
        }

        return returnList;
    }
	
}
