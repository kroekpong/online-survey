package com.khotdee168.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class LoginCheckInfo {

	 private LoginUserInfo LoginReturnInfo;

	public LoginUserInfo getLoginReturnInfo() {
		return LoginReturnInfo;
	}

	public void setLoginReturnInfo(LoginUserInfo loginReturnInfo) {
		LoginReturnInfo = loginReturnInfo;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	 
}
