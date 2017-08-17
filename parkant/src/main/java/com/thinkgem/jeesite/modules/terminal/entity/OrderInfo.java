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
 * 订单信息Entity
 * @author Hey
 * @version 2017-08-14
 */
@Entity
@Table(name = "t_order_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderInfo extends IdEntity<OrderInfo> {
	/**
	 *开始时间
	 */ 
	private Date startTime;
	/**
	 *离开时间
	 */ 
	private Date leaveTime;
	/**
	 *订单状态
	 */ 
	private String status;
	/**
	 *使用者ID
	 */
	@Column(name="use_id")
	private User use;
	/**
	 *共享车位ID
	 */
	@Column(name="share_id")
	private ShareCarseatInfo shareCarseatInfo;
	/**
	 *已下订O，已支付P，已冻结F，已取消C，已合并M
	 */ 
	private String orderDesc;
	/**
	 *支付ID
	 */
	private String payId;
	/**
	 *拥有者ID
	 */
	@Column(name="have_id")
	private User haver;
	/**
	 *支付金额
	 */ 
	private String payMoney;
	/**
	 *结束时间
	 */ 
	private Date endTime;



	public OrderInfo() {
		super();
	}

	public OrderInfo(String id){
		this();
		this.id = id;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name = "use_id")
	public User getUse() {
		return use;
	}

	public void setUse(User use) {
		this.use = use;
	}

	@ManyToOne
	@JoinColumn(name = "share_id")
	public ShareCarseatInfo getShareCarseatInfo() {
		return shareCarseatInfo;
	}

	public void setShareCarseatInfo(ShareCarseatInfo shareCarseatInfo) {
		this.shareCarseatInfo = shareCarseatInfo;
	}

	@Length(min=0, max=200, message="已下订O，已支付P，已冻结F，已取消C，已合并M长度必须介于 0 和 200 之间")
	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	@Length(min=0, max=64, message="支付ID长度必须介于 0 和 64 之间")
	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	@ManyToOne
	@JoinColumn(name = "have_id")
	public User getHaver() {
		return haver;
	}

	public void setHaver(User haver) {
		this.haver = haver;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}