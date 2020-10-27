package com.khotdee168.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.khotdee168.common.dao.DDLDao;
import com.khotdee168.common.model.DDLBean;

@Service
public class DDLService {
	
	@Autowired
	public DDLDao ddlDao;
	
	public List  getStatus() {
        List  list = new ArrayList<>();
        list.add(new DDLBean("1", "1", "Active", "Active"));
        list.add(new DDLBean("0", "0", "Disabled", "Disabled"));
        return list;
    }
	
	public List  getProductStatus() {
		List  list = new ArrayList<>();
		list.add(new DDLBean("1", "1", "Stock", "Stock"));
		list.add(new DDLBean("0", "0", "Stock Out", "Stock Out"));
		return list;
	}
	
	 
	public List  getRules() {
		List  list = new ArrayList<>();	
		String lang = LocaleContextHolder.getLocale().getLanguage();
		if("th".equalsIgnoreCase(lang)){
			list.add(new DDLBean("1", "1", "\u0E08\u0E33\u0E19\u0E27\u0E19\u0E17\u0E35\u0E48\u0E15\u0E49\u0E2D\u0E07\u0E01\u0E32\u0E23\u0E2A\u0E48\u0E07" ));
			list.add(new DDLBean("2", "2", "\u0E08\u0E33\u0E19\u0E27\u0E19\u0E40\u0E1B\u0E2D\u0E23\u0E4C\u0E40\u0E0B\u0E47\u0E19\u0E15\u0E4C\u0E41\u0E15\u0E48\u0E25\u0E30\u0E14\u0E35\u0E25\u0E40\u0E25\u0E2D\u0E23\u0E4C"));
		}else{
			list.add(new DDLBean("1", "1", "Number of release records" ));
			list.add(new DDLBean("2", "2", "RO percentage per dealer"));
		}
		return list;
	}
	
	public List  getQuestionType() {
		
		List  list = new ArrayList<>();
		String lang = LocaleContextHolder.getLocale().getLanguage();
		if("th".equalsIgnoreCase(lang)){
			list.add(new DDLBean("L", "L", "\u0E43\u0E2B\u0E49\u0E04\u0E30\u0E41\u0E19\u0E19"));
			list.add(new DDLBean("C", "C", "\u0E17\u0E33\u0E40\u0E04\u0E23\u0E37\u0E48\u0E2D\u0E07\u0E2B\u0E21\u0E32\u0E22"));
			list.add(new DDLBean("M", "M", "\u0E40\u0E25\u0E37\u0E2D\u0E01\u0E02\u0E49\u0E2D\u0E43\u0E14\u0E02\u0E49\u0E2D\u0E2B\u0E19\u0E36\u0E48\u0E07"));
			list.add(new DDLBean("P", "P", "\u0E43\u0E2A\u0E48\u0E02\u0E49\u0E2D\u0E04\u0E27\u0E32\u0E21"));
		}else{
			list.add(new DDLBean("L", "L", "Linear scale (Score)"));
			list.add(new DDLBean("C", "C", "Checkboxes"));
			list.add(new DDLBean("M", "M", "Multiple choice"));
			list.add(new DDLBean("P", "P", "Paragraph (Free text)"));
		}
		
		return list;
	}
	
	public List  getRoles() {
		return ddlDao.getRoles();
	}
	
	public List  getActiveSupplier() {
		return  new ArrayList<>();
	}
	
	public List  getActiveCompany() {
		return ddlDao.getActiveCompany();
	}
	
	public List  getAllSupplier() {
		return  new ArrayList<>();
	}
	
	public List getLocationType() {
		return ddlDao.getLocationType();
	}

	public List getActiveWarehouse() {
		return ddlDao.getActiveWarehouse();
	}
	
	public List getLogType() {
		return ddlDao.getLogType();
	}

	public List getQuestionGroup() {
		return ddlDao.getQuestionGroup();
	}

	public List getRepairType() {
		return ddlDao.getRepairType();
	}
	
	public List getQuestionSet() {
		return ddlDao.getQuestionSet(false);
	}
	
	public List getQuestionSetAll() {
		return ddlDao.getQuestionSet(true);
	}
	
	
	public List getDDLType(String type) {
		return ddlDao.getDDLType(type);
	}

	public String getSysParamValue(String type) {
		return ddlDao.getSysParamValue(type);
	}
	
	public String getSysParamDesc(String type) {
		return ddlDao.getSysParamDesc(type);
	}
	
	
	
//	public List getActiveProductionDay() {
//		return ddlDao.getActiveProductionDay();
//	}
	
//	
//	public List getActiveProduct(String productId) {
//		return ddlDao.getActiveProductCode(productId);
//	}
//	
//	public List getActiveTraceability(String traceabilityId) {
//		return ddlDao.getActiveTraceability(traceabilityId);
//	}
//	
//	public List getActiveSpecification(String specificationId) {
//		return ddlDao.getActiveSpecification(specificationId);
//	}
//	
//	public List getActiveBatteryType(String batterytypeId) {
//		return ddlDao.getActiveBatteryType(batterytypeId);
//	}
//	
//	public List getActiveProductionDay(String dateId) {
//		return ddlDao.getActiveProductionDay(dateId);
//	}
//	
}
