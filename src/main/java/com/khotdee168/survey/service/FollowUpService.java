package com.khotdee168.survey.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.khotdee168.common.constant.CommonConstants.CUSTOMER_STATUS;
import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.survey.dao.ActivityDao;
import com.khotdee168.survey.dao.FollowUpDao;
import com.khotdee168.survey.model.DataTableActivity;
import com.khotdee168.survey.model.FollowUpActivityBean;
import com.khotdee168.survey.model.FollowUpLogBean;

@Service
public class FollowUpService {
	
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Value("${sid.size}") 
    private int SID_SIZE;
	
	@Autowired
	public FollowUpDao dao;
	
	@Autowired
	public ActivityDao activityDao;
	
	@Autowired
	public SMSService smsService;
	
	public DataTableAjax getDataTable(FollowUpActivityBean bean) {
		return dao.getDataTable(bean);
	}

	public DataTableActivity getInfoDataTable(FollowUpActivityBean bean) {
		return dao.getReleaseDataList(bean);
	}
	
	public FollowUpActivityBean getActivityBySid(String sid) {
		return dao.getActivityBySid(sid);
	}

	public List<FollowUpLogBean> getFollowUpLog(String sid) {
		return dao.getFollowUpLog(sid);
	}
	
	public boolean updateOpenFlg(String sid) {
		return dao.updateOpenFlg(sid);
	}

	public boolean saveLog(FollowUpLogBean bean) {
		if(CUSTOMER_STATUS.CLOSE.equals(bean.getStatus())){
			return  dao.closeSurvey(bean);
		}else{
			return  dao.saveLog(bean);
		}
		
	}

	public FollowUpActivityBean getActivityByUid(String uid) {
		return dao.getActivityByUid(uid);
	}

	public boolean testSMSSender(String tel, String sid, String lang) {
			 
		String SMS_SEND_FLG = activityDao.getSendSMS();
		String SMS_CONTENT= activityDao.getContentSMS(lang);
		
//		System.out.println("SMS_SEND_FLG ::>> "+SMS_SEND_FLG);
		
		boolean validMobile = smsService.validMobileNo(smsService.formatMobile(tel));
		
//		System.out.println("Test@ SMSSender ::>> sid : "+sid+" , Tel : "+tel);
//		System.out.println("Test@ VALID ::>> "+validMobile);
//		Thread.sleep(3000);
		
		String flg = "10"; //-- Mock Send
		
		String content = SMS_CONTENT.replace("{sid}",sid);
//		System.out.println("SMS content ::>> "+content);
		
		if("Y".equals(SMS_SEND_FLG) && validMobile){
			
			try {
				
				boolean suc = smsService.sendMsg(tel, content);
				flg = suc? "1" : "-1";
			} catch (Exception e) {
				flg = "-9";
				e.printStackTrace();
			}
			
		}else if(!validMobile){// Invalid Mobile
			flg = "99";
		}
		
		return "1".endsWith(flg) || "10".endsWith(flg);
	}

//	public FollowUpActivityBean getRegisterData(String uid) {
//		return dao.getRegisterData(uid);
//	}
 
}
