package com.khotdee168.survey.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReportTrackingBean {

	private Integer total;
	private Integer pre;
	private Integer sms;
	private Integer cc;
	private Integer incomplete;

	private BigDecimal totalP;
	private BigDecimal preP;
	private BigDecimal smsP;
	private BigDecimal ccP;
	private BigDecimal incompleteP;

	private ActivityBean activity;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPre() {
		return pre;
	}

	public void setPre(Integer pre) {
		this.pre = pre;
	}

	public Integer getSms() {
		return sms;
	}

	public void setSms(Integer sms) {
		this.sms = sms;
	}

	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
	}

	public Integer getIncomplete() {
		return incomplete;
	}

	public void setIncomplete(Integer incomplete) {
		this.incomplete = incomplete;
	}

	public BigDecimal getTotalP() {
		return totalP;
	}

	public void setTotalP(BigDecimal totalP) {
		this.totalP = totalP;
	}

	public BigDecimal getPreP() {
		return preP;
	}

	public void setPreP(BigDecimal preP) {
		this.preP = preP;
	}

	public BigDecimal getSmsP() {
		return smsP;
	}

	public void setSmsP(BigDecimal smsP) {
		this.smsP = smsP;
	}

	public BigDecimal getCcP() {
		return ccP;
	}

	public void setCcP(BigDecimal ccP) {
		this.ccP = ccP;
	}

	public BigDecimal getIncompleteP() {
		return incompleteP;
	}

	public void setIncompleteP(BigDecimal incompleteP) {
		this.incompleteP = incompleteP;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public ActivityBean getActivity() {
		return activity;
	}

	public void setActivity(ActivityBean activity) {
		this.activity = activity;
	}

}