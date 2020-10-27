
package com.khotdee168.common.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
 
public class UserLoginVO implements Serializable {

	/**
	 */
	private static final long serialVersionUID = 5170639566135075198L;

	/**
	 * 1、以下为人员属性： 用户编码
	 */
	private Long userId;

	/**
	 * 用户账号
	 */
	private String userName;

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 2、以下为组织属性： 直属组织编号
	 */
	private Long orgId;

	/**
	 * 直属组织代码
	 */
	private String orgCode;

	/**
	 * 直属组织名称
	 */
	private String orgName;

	/**
	 * 品牌代码
	 */
	private String brandCode;

	/**
	 * 品牌名称
	 */
	private String brandName;

	/**
	 * 3、以下为岗位属性： 岗位编码
	 */
	private Long positionId;

	/**
	 * 岗位名称
	 */
	private String positionName;

	/**
	 * 岗位类别
	 */
	private Integer positionType;

	/**
	 * 岗位描述
	 */
	private String positionDescription;

	/**
	 * 数据范围：1-本人；2-本组织及以下
	 */
	private Integer dataScope;

	/**
	 * 是否经销商：根据岗位类别判断是厂方端还是经销商端
	 */
	private boolean isDealer;

	/**
	 * 4、以下为经销商属性： 所属经销商编号
	 */
	private Long dealerId;

	/**
	 * 所属经销商代码
	 */
	private String dealerCode;

	/**
	 * 经销商SQ码
	 */
	private String dealerSQCode;

	/**
	 * 所属经销商名称
	 */
	private String dealerName;

	/**
	 * 维修代码
	 */
	private String fixCode;

	/**
	 * SSO返回信息
	 */
	private String ssoMsg;

	/**
	 * 返回代码,100 成功,101 用户名或密码错误, 102 当日失败次数限制
	 */
	private int rtnCode;
	/**
	 * 岗位code
	 */
	private String positionCode;

	/**
	 * 是否SIS专员
	 */
	private Integer isSis;

	private List<Long> positionIds;
	/**
	 * 是否maevel专员
	 */
	private Integer IsMaevel;

	/**
	 * 当前用户使用语言
	 */
	private String language;

	/**
	 * 多租户标志，用户所在地区
	 */
	private String region;

	public Integer getIsMaevel() {
		return IsMaevel;
	}

	public void setIsMaevel(Integer isMaevel) {
		IsMaevel = isMaevel;
	}

	public List<Long> getPositionIds() {
		return positionIds;
	}

	public void setPositionIds(List<Long> positionIds) {
		this.positionIds = positionIds;
	}

	/**
	 * @return the positionCode
	 */
	public String getPositionCode() {
		return positionCode;
	}

	/**
	 * @param positionCode
	 *            the positionCode to set
	 */
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerSQCode() {
		return dealerSQCode;
	}

	public void setDealerSQCode(String dealerSQCode) {
		this.dealerSQCode = dealerSQCode;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getPositionType() {
		return positionType;
	}

	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
	}

	public String getPositionDescription() {
		return positionDescription;
	}

	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}

	public boolean isDealer() {
		return isDealer;
	}

	public void setDealer(boolean isDealer) {
		this.isDealer = isDealer;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getDataScope() {
		return dataScope;
	}

	public void setDataScope(Integer dataScope) {
		this.dataScope = dataScope;
	}

	public String getFixCode() {
		return fixCode;
	}

	public void setFixCode(String fixCode) {
		this.fixCode = fixCode;
	}

	public String getSsoMsg() {
		return ssoMsg;
	}

	public void setSsoMsg(String ssoMsg) {
		this.ssoMsg = ssoMsg;
	}

	public int getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(int rtnCode) {
		this.rtnCode = rtnCode;
	}

	public Integer getIsSis() {
		return isSis;
	}

	public void setIsSis(Integer isSis) {
		this.isSis = isSis;
	}

	public String getLanguage() {
		if (this.language.equals("en")) {
			return "en_US";
		} else if (this.language.equals("th")) {
			return "th_TH";
		} else if (this.language.equals("zh")) {
			return "zh_CN";
		}
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
