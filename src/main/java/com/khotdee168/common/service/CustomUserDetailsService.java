package com.khotdee168.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.khotdee168.common.dao.UserProfileDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserProfileDao dao;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		return dao.findByUserName(username);
	}

}