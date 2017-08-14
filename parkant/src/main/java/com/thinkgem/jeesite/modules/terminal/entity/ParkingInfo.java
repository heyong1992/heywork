/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 停车信息Entity
 * @author Hey
 * @version 2017-08-12
 */
@Entity
@Table(name = "t_parking_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParkingInfo extends IdEntity<ParkingInfo> {
	/**
	 *收费标准
	 */
	@Column(name="s_desc")
	private String sDesc;
	/**
	 *停车场名称
	 */
	@Column(name="s_name")
	private String sName;
	/**
	 *所属省份
	 */ 
	private String province;
	/**
	 *所属城市
	 */ 
	private String city;
	/**
	 *具体位置
	 */ 
	private String address;



	public ParkingInfo() {
		super();
	}

	public ParkingInfo(String id){
		this();
		this.id = id;
	}


	@Length(min=0, max=50, message="收费标准长度必须介于 0 和 50 之间")
	public String getSDesc() {
		return sDesc;
	}

	public void setSDesc(String sDesc) {
		this.sDesc = sDesc;
	}

	@Length(min=0, max=30, message="停车场名称长度必须介于 0 和 30 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	@Length(min=0, max=10, message="所属省份长度必须介于 0 和 10 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Length(min=0, max=10, message="所属城市长度必须介于 0 和 10 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Length(min=0, max=50, message="具体位置长度必须介于 0 和 50 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}




}