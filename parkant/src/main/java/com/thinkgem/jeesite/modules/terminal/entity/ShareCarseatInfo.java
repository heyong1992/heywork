/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.terminal.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

import java.util.Date;

/**
 * 共享车位信息Entity
 * @author Hey
 * @version 2017-08-14
 */
@Entity
@Table(name = "t_share_carseat_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShareCarseatInfo extends IdEntity<ShareCarseatInfo> {
	/**
	 *允许超过时间（分钟）
	 */ 
	private String overtime;
	/**
	 *车位ID
	 */
	@Column(name="car_seat_id")
	private CarSeatInfo carSeat;
	/**
	 *是否已共享
	 */ 
	private String isShare;
	/**
	 *停车场ID
	 */
	@Column(name="parking_id")
	private ParkingInfo parking;
	/**
	 *结束时间
	 */ 
	private Date endTime;
	/**
	 *开始时间
	 */ 
	private Date startTime;
	/**
	 *发布人ID
	 */
	@Column(name="user_id")
	private User user;

	public ShareCarseatInfo() {
		super();
	}

	public ShareCarseatInfo(String id){
		this();
		this.id = id;
	}

	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	@ManyToOne
	@JoinColumn(name = "car_seat_id")
	public CarSeatInfo getCarSeat() {
		return carSeat;
	}

	public void setCarSeat(CarSeatInfo carSeat) {
		this.carSeat = carSeat;
	}

	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}

	@ManyToOne
	@JoinColumn(name = "parking_id")
	public ParkingInfo getParking() {
		return parking;
	}

	public void setParking(ParkingInfo parking) {
		this.parking = parking;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}