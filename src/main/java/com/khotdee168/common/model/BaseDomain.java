package com.khotdee168.common.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.khotdee168.common.utils.UserLoginUtils;

public abstract class BaseDomain {

	protected String status;
//	protected String activeFlg;
//	protected Long version = 1L;
	protected String createBy;
	protected Date createDate;
	protected String createDateStr;
	protected String updateBy;
	protected Date updateDate;
	protected String updateDateStr;
	
//	protected UserProfile userProfile;
	
	private int start;
	private int draw;
	private int length;

//	public String getIsDelete() {
//		return isDelete;
//	}
//
//	public void setIsDelete(String isDelete) {
//		this.isDelete = isDelete;
//	}

//	public Long getVersion() {
//		return version;
//	}
//
//	public void setVersion(Long version) {
//		this.version = version;
//	}

	public String getCreateBy() {
		if (UserLoginUtils.getCurrentUser() != null) {
			createBy = UserLoginUtils.getCurrentUser().getUsername();
		}
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		if (UserLoginUtils.getCurrentUser() != null) {
			updateBy = UserLoginUtils.getCurrentUser().getUsername();
		}
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getUpdateDateStr() {
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

//	public String getActiveFlg() {
//		return activeFlg;
//	}
//
//	public void setActiveFlg(String activeFlg) {
//		this.activeFlg = activeFlg;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

//	public UserProfile getUserProfile() {
//		if (UserLoginUtils.getCurrentUser() != null  ) {
//			userProfile = UserLoginUtils.getCurrentUser();
//		}
//		return userProfile;
//	}
//
//	public void setUserProfile(UserProfile userProfile) {
//		this.userProfile = userProfile;
//	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
