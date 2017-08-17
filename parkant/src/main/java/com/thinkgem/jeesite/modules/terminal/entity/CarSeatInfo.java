/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.entity;

import javax.persistence.*;

import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import org.hibernate.annotations.NotFound;

/**
 * 车位信息Entity
 * @author Hey
 * @version 2017-08-14
 */
@Entity
@Table(name = "t_carseat_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CarSeatInfo extends IdEntity<CarSeatInfo> {
	/**
	 *车位名称
	 */ 
	private String carSeatName;
	/**
	 *拥有者ID
	 */
	@Column(name="user_id")
	private User user;
	/**
	 *停车场ID
	 */
	@Column(name="parking_id")
	private ParkingInfo parking;
	/**
	 *车位锁编号
	 */ 
	private String deviceNum;

	public CarSeatInfo() {
		super();
	}

	public CarSeatInfo(String id){
		this();
		this.id = id;
	}

	public String getCarSeatName() {
		return carSeatName;
	}

	public void setCarSeatName(String parkingName) {
		this.carSeatName = parkingName;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String parkingNum) {
		this.deviceNum = parkingNum;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "parking_id")
	public ParkingInfo getParking() {
		return parking;
	}

	public void setParking(ParkingInfo parking) {
		this.parking = parking;
	}
}