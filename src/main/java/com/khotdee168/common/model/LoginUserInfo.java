 
package com.khotdee168.common.model;

import java.util.List;

public class LoginUserInfo  {
	
	private static final long serialVersionUID = 1L;
    /**
     * 返回代码：
     * 10502001 成功, 
     * 10502002 没有找到请求的用户信息
     * 10508003 用户密码不正确 
     * 10508004 用户帐户不可用 
     * 10508005 经销商组织状态失效
     * 10508006 用户已被禁用 
     */
    private int rtnCode;
    
    /** 登陆返回信息 */
    private String loginMsg;
    
    /**  用户编码*/
    private Long userId;

    /** 用户账号  */
    private String userName;

    /** 用户名称 */
    private String name;
    
    private String mobile;
    
    /**
     * 是否SIS专员
     */
    private Integer isSis;
    /**
     * 是否可以代理登陆
     */
    private Integer isAgentLogin;

    /** 用户登录信息，包括岗位列表*/
    private List<UserLoginVO> userLoginInfoList;
    
    /** 岗位列表*/
    private List<OrgPostInfoVO> orgPostInfoList;

    
    public List<OrgPostInfoVO> getOrgPostInfoList() {
        return orgPostInfoList;
    }

    public void setOrgPostInfoList(List<OrgPostInfoVO> orgPostInfoList) {
        this.orgPostInfoList = orgPostInfoList;
    }

    public List<UserLoginVO> getUserLoginInfoList() {
        return userLoginInfoList;
    }

    public void setUserLoginInfoList(List<UserLoginVO> userLoginInfoList) {
        this.userLoginInfoList = userLoginInfoList;
    }

    public int getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(int rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getLoginMsg() {
        return loginMsg;
    }

    public void setLoginMsg(String loginMsg) {
        this.loginMsg = loginMsg;
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

    public Integer getIsSis() {
        return isSis;
    }

    public void setIsSis(Integer isSis) {
        this.isSis = isSis;
    }

	public Integer getIsAgentLogin() {
		return isAgentLogin;
	}

	public void setIsAgentLogin(Integer isAgentLogin) {
		this.isAgentLogin = isAgentLogin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    
}
