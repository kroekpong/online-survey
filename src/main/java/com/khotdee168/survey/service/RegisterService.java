package com.khotdee168.survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khotdee168.survey.dao.RegisterDao;
import com.khotdee168.survey.model.RegisterBean;


@Service
public class RegisterService{
	
	@Autowired
	public RegisterDao dao;

	public boolean register(RegisterBean bean) {
		return  dao.insertRegister(bean)>0;
	}

	public boolean cancel(RegisterBean bean) {
		return dao.updateCancelFlg(bean);
	}

	public boolean validUid(RegisterBean bean) {
		return dao.validUid(bean);
	} 
    
    
    
    
}