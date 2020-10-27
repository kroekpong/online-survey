package com.khotdee168.common.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khotdee168.common.dao.UserDao;
import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.common.model.UserBean;

@Service
public class UserService {

	@Autowired
	public UserDao dao;
	
	
	public DataTableAjax  getDataTable(UserBean bean) {
		return dao.getDataTable(bean);
	}

	@Transactional
	public boolean saveOrUpdate(UserBean bean) {
		if(StringUtils.isNotEmpty(bean.getId())){
			return dao.update(bean);
		} else {
			return dao.insert(bean);
		}
	}

	@Transactional
	public boolean delete(String id) {
		return dao.delete(id);
	}

	public boolean checkDuplicate(UserBean bean) {
		return dao.checkDuplicate(bean);
	}

	public List getRolesFunction() {
		return dao.getRolesFunction();
	}
	
}
