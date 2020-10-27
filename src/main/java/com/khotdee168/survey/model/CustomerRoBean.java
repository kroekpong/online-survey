package com.khotdee168.survey.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CustomerRoBean {

	private String uid;
	private String sid;
	private String dealer_code;
	private String dealer_name;
	private String customer;
	private String tel;
	private String license;
	private String repair_type;
	private String ro_no;
	private String stm_no;
	private String vote_flg; // 0:NO , 1:Pre, 2:SMS, 3:CC
	private String lock_flg;
	private String release_flg;
	private String sa_name;
	private String createDate; // RO Create
	private String settleDate; // RO Create
	private String updateDate;
	private String channel;
	private String releaseStatus; // 0:NO , 1:Pre, 2:SMS, 3:CC
	
//	private List<String> uids;
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDealer_code() {
		return dealer_code;
	}

	public void setDealer_code(String dealer_code) {
		this.dealer_code = dealer_code;
	}

	public String getDealer_name() {
		return dealer_name;
	}

	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
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

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getRepair_type() {
		return repair_type;
	}

	public void setRepair_type(String repair_type) {
		this.repair_type = repair_type;
	}

	public String getRo_no() {
		return ro_no;
	}

	public void setRo_no(String ro_no) {
		this.ro_no = ro_no;
	}

	public String getStm_no() {
		return stm_no;
	}

	public void setStm_no(String stm_no) {
		this.stm_no = stm_no;
	}

	public String getVote_flg() {
		return vote_flg;
	}

	public void setVote_flg(String vote_flg) {
		this.vote_flg = vote_flg;
	}

	public String getLock_flg() {
		return lock_flg;
	}

	public void setLock_flg(String lock_flg) {
		this.lock_flg = lock_flg;
	}

	public String getSa_name() {
		return sa_name;
	}

	public void setSa_name(String sa_name) {
		this.sa_name = sa_name;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getRelease_flg() {
		return release_flg;
	}

	public void setRelease_flg(String release_flg) {
		this.release_flg = release_flg;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

//	public List<String> getUids() {
//		return uids;
//	}
//
//	public void setUids(List<String> uids) {
//		this.uids = uids;
//	}

}