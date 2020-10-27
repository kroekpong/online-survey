package com.khotdee168.common.utils;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.khotdee168.common.model.UserProfile;

public class UserLoginUtils {	

	public static UserProfile getCurrentUser() {
		UserProfile userBean = null;
		
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof User) {
				userBean = (UserProfile) principal;
			} else {
				String username = principal.toString();
				userBean = new UserProfile(username, "", AuthorityUtils.NO_AUTHORITIES);
			}
		}
		
		return userBean;
	}

	public static String getUserName() {
		if(null!=getCurrentUser()){
			return getCurrentUser().getUsername();
		}else{
			return "Annonymous";
		}
	}
	
	public static String logging(String msg) {
		return "[" + getUserName() + "] " + msg;
	}
	
}
