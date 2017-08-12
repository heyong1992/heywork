/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 单表生成Entity
 * @author Hey
 * @version 2017-07-04
 */
@Entity
@Table(name = "student_tStudent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TStudent extends IdEntity<TStudent> {
	/**
	 *age
	 */ 
	private String age;
	/**
	 *cid
	 */ 
	private String cid;
	/**
	 *name
	 */ 
	private String name;



	public TStudent() {
		super();
	}

	public TStudent(String id){
		this();
		this.id = id;
	}


	@Length(min=0, max=11, message="age长度必须介于 0 和 11 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Length(min=0, max=11, message="cid长度必须介于 0 和 11 之间")
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Length(min=0, max=20, message="name长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}




}