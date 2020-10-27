package com.khotdee168.survey.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khotdee168.survey.dao.ReportDao;
import com.khotdee168.survey.model.ActivityBean;
import com.khotdee168.survey.model.ReportTrackingBean;

@Service
public class ReportService {
	
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	public ReportDao dao;

	public ReportTrackingBean getTrackingReport(ActivityBean bean) {
		return dao.getTrackingReport(bean);
	}
	
	
 
	
	
	
 
}
