package com.khotdee168.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.khotdee168.common.model.LoginCheckInfo;
import com.khotdee168.common.model.LoginUserInfo;
import com.khotdee168.common.model.UserLoginVO;
import com.khotdee168.common.model.UserProfile;


@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Value("${url.api.login}")
	private String LOGIN_API;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//TODO login without auth  chang to casAuthen
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		return doRestAuthen(username, password);
	}

	private Authentication doRestAuthen(String username, String password) {
		
		RestTemplate rest = new RestTemplate();
		
		String url =LOGIN_API+"username="+username+"&password="+password;
		
//		System.out.println("url : " + url);
		
		String json = rest.getForObject(url, String.class);
		
		if(null==json){
			json = rest.getForObject(url, String.class);
		}
		
		System.out.println("json : " + json);
		
		Gson gson = new Gson();
		LoginCheckInfo loginInfo =  gson.fromJson(json, LoginCheckInfo.class);
		
		if(null!=loginInfo && null!=loginInfo.getLoginReturnInfo()){
			
			LoginUserInfo userInfo = loginInfo.getLoginReturnInfo();
			
			final List<GrantedAuthority> grantedAuths = new ArrayList<>();
		
			for (UserLoginVO userLoginVO : userInfo.getUserLoginInfoList()) {
//				grantedAuths.add(new SimpleGrantedAuthority(userLoginVO.getPositioId()));
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_"+userLoginVO.getPositionCode()));
			}
			
			final UserProfile principal = new UserProfile(username, password, grantedAuths);
			principal.setId(String.valueOf(userInfo.getUserId()));
			principal.setUserName(userInfo.getUserName());
			principal.setTel(userInfo.getMobile());
			
			final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
			return auth;
			
		}else{
			return null;
		}
		
	}

	@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
