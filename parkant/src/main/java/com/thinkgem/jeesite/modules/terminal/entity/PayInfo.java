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
 * 支付信息Entity
 * @author Hey
 * @version 2017-08-14
 */
@Entity
@Table(name = "t_pay_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PayInfo extends IdEntity<PayInfo> {
	/**
	 *支付类型
	 */
	@Column(name="pay_type")
	private String payType;
	/**
	 *支付金额
	 */
	@Column(name="pay_sum")
	private String paySum;
	/**
	 *订单状态
	 */
	@Column(name="pay_status")
	private String payStatus;
	/**
	 *支付人ID
	 */
	@Column(name="u_id")
	private String uId;
	/**
	 *支付凭证
	 */
	@Column(name="pay_evidence")
	private String payEvidence;
	/**
	 *订单ID
	 */
	@Column(name="order_id")
	private String orderId;



	public PayInfo() {
		super();
	}

	public PayInfo(String id){
		this();
		this.id = id;
	}


	@Length(min=0, max=2, message="支付类型长度必须介于 0 和 2 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPaySum() {
		return paySum;
	}

	public void setPaySum(String paySum) {
		this.paySum = paySum;
	}

	@Length(min=0, max=2, message="订单ID长度必须介于 0 和 2 之间")
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	@Length(min=0, max=64, message="支付人ID长度必须介于 0 和 64 之间")
	public String getUId() {
		return uId;
	}

	public void setUId(String uId) {
		this.uId = uId;
	}

	@Length(min=0, max=64, message="支付凭证长度必须介于 0 和 64 之间")
	public String getPayEvidence() {
		return payEvidence;
	}

	public void setPayEvidence(String payEvidence) {
		this.payEvidence = payEvidence;
	}

	@Length(min=0, max=64, message="订单ID长度必须介于 0 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}




}