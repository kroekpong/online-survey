 
package com.khotdee168.common.model;

/**
 * 组织岗位详情数据对象<br>
 * 
 * @author wangzhaolin
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class OrgPostInfoVO {

    /**
     * 组织ID
     */
    private Long orgId;

    /**
     * 组织CODE
     */
    private String orgCode;

    /**
     * 组织名称
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
     * 岗位ID
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
     * 岗位类型描述
     */
    private String positionTypeDesc;

    /**
     * 岗位描述
     */
    private String positionDescription;

    /**
     * 数据范围：1-本人；2-本组织及以下
     */
    private Integer dataScope;

    /**
     * 岗位代码
     */
    private String positionCode;
    
    /** 限制app权限，1:是，2:否 */
    private Integer appRight;
    
    /** 是否有app登录权限，跟岗位配合使用，1:是，2:否 */
    private Integer isAppLoginRight;

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

    public String getPositionTypeDesc() {
        return positionTypeDesc;
    }

    public void setPositionTypeDesc(String positionTypeDesc) {
        this.positionTypeDesc = positionTypeDesc;
    }

    public Integer getDataScope() {
        return dataScope;
    }

    public void setDataScope(Integer dataScope) {
        this.dataScope = dataScope;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    /**
     * @return the appRight
     */
    public Integer getAppRight() {
        return appRight;
    }

    /**
     * @param appRight the appRight to set
     */
    public void setAppRight(Integer appRight) {
        this.appRight = appRight;
    }

    /**
     * @return the isAppLoginRight
     */
    public Integer getIsAppLoginRight() {
        return isAppLoginRight;
    }

    /**
     * @param isAppLoginRight the isAppLoginRight to set
     */
    public void setIsAppLoginRight(Integer isAppLoginRight) {
        this.isAppLoginRight = isAppLoginRight;
    }
}
