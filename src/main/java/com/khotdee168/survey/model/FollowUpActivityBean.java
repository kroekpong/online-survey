package com.khotdee168.survey.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FollowUpActivityBean extends ActivityBean {

	private String completeNo;
	private String totalNo;
	private String status;
	private String customer;
	private String tel;
	private String sid;
	private String uid;
	private String reason;
	private String dealerName;
	private String completeFlg ;
	private String lockFlg ;
	private String voteFlg ;
	private String expiredFlg ;
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getCompleteNo() {
		return completeNo;
	}

	public void setCompleteNo(String completeNo) {
		this.completeNo = completeNo;
	}

	public String getTotalNo() {
		return totalNo;
	}

	public void setTotalNo(String totalNo) {
		this.totalNo = totalNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getCompleteFlg() {
		return completeFlg;
	}

	public void setCompleteFlg(String completeFlg) {
		this.completeFlg = completeFlg;
	}

	public String getLockFlg() {
		return lockFlg;
	}

	public void setLockFlg(String lockFlg) {
		this.lockFlg = lockFlg;
	}

	public String getVoteFlg() {
		return voteFlg;
	}

	public void setVoteFlg(String voteFlg) {
		this.voteFlg = voteFlg;
	}

	public String getExpiredFlg() {
		return expiredFlg;
	}

	public void setExpiredFlg(String expiredFlg) {
		this.expiredFlg = expiredFlg;
	}

}