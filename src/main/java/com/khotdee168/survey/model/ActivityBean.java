package com.khotdee168.survey.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ActivityBean {

	private String id;
	private String setId;
//	private String questionId;
	private String preFlg;

	private String activityName;
	private String description;
	private String roStartDate;
	private String roEndDate;
	private String repairType;
	private List<String> repairTypes;
//	private String repairType;
//	private String repairTypeName;
	private String channel;
	private String channelName;
	private String rule;
	private String ruleName;
	private int submitNo;
	private String releaseDate; // Cannot modify
	private String expireDate;
	private String closeFlg;
	private String ccFlg;

	private String setName;
	private String setDescription;
	
	private List<String> uids;
	
	private boolean viewMode;
	
//	private QuestionSetBean questionSet;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSetId() {
		return setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}
//
//	public String getQuestionId() {
//		return questionId;
//	}
//
//	public void setQuestionId(String questionId) {
//		this.questionId = questionId;
//	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoStartDate() {
		return roStartDate;
	}

	public void setRoStartDate(String roStartDate) {
		this.roStartDate = roStartDate;
	}

	public String getRoEndDate() {
		return roEndDate;
	}

	public void setRoEndDate(String roEndDate) {
		this.roEndDate = roEndDate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public int getSubmitNo() {
		return submitNo;
	}

	public void setSubmitNo(int submitNo) {
		this.submitNo = submitNo;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getPreFlg() {
		return preFlg;
	}

	public void setPreFlg(String preFlg) {
		this.preFlg = preFlg;
	}

	public List<String> getUids() {
		return uids;
	}

	public void setUids(List<String> uids) {
		this.uids = uids;
	}

	public String getCloseFlg() {
		return closeFlg;
	}

	public void setCloseFlg(String closeFlg) {
		this.closeFlg = closeFlg;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRepairType() {
		return repairType;
	}

	public void setRepairType(String repairType) {
		this.repairType = repairType;
	}

	public List<String> getRepairTypes() {
		return repairTypes;
	}

	public void setRepairTypes(List<String> repairTypes) {
		this.repairTypes = repairTypes;
		setRepairTypeArr(repairTypes);
	}
	
	public void setRepairTypeArr(List<String> repairTypes) {
		repairType = String.join(",", repairTypes);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getCcFlg() {
		return ccFlg;
	}

	public void setCcFlg(String ccFlg) {
		this.ccFlg = ccFlg;
	}

//	public QuestionSetBean getQuestionSet() {
//		return questionSet;
//	}
//
//	public void setQuestionSet(QuestionSetBean questionSet) {
//		this.questionSet = questionSet;
//	}

	public String getSetDescription() {
		return setDescription;
	}

	public void setSetDescription(String setDescription) {
		this.setDescription = setDescription;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public boolean isViewMode() {
		return viewMode;
	}

	public void setViewMode(boolean viewMode) {
		this.viewMode = viewMode;
	}
 
}