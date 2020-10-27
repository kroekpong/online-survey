package com.khotdee168.survey.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khotdee168.common.constant.CommonConstants;
import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.survey.dao.ActivityDao;
import com.khotdee168.survey.model.ActivityBean;
import com.khotdee168.survey.model.ActivityReleaseBean;
import com.khotdee168.survey.model.CustomerRoBean;
import com.khotdee168.survey.model.DataTableActivity;

@Service
public class ActivityService {
	
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Value("${sid.size}") 
    private int SID_SIZE;
	
	@Autowired
	public ActivityDao dao;
	
	@Autowired
	public SMSService smsService;
	
	public DataTableAjax getDataTable(ActivityBean bean) {
		return dao.getDataTable(bean);
	}	
	
	@Transactional
	public boolean delete(String id) {
		return dao.delete(id);
	}

	public DataTableActivity getCustomerRo(ActivityBean bean) {
		System.out.println(bean);
		
		if("1".equals(bean.getRule())){ // Number
			return dao.getCustomerRo(bean);
		}else{
			return dao.getCustomerByPercent(bean);
		}
	}

	public List<ActivityReleaseBean> releaseActivity(ActivityBean bean) {
		
//		bean.setRepairTypeArr(bean.getRepairTypes());

		if(StringUtils.isEmpty(bean.getId())){
			
			if(CommonConstants.CHANNEL.CALL_CENTER.equals(bean.getChannel())){ 
				bean.setCcFlg("1");
			}
			bean.setId(String.valueOf(dao.insertActivity(bean)));
		
		}else{
			dao.updateActivity(bean);
			
			if(CommonConstants.CHANNEL.CALL_CENTER.equals(bean.getChannel())){ 
				dao.updateCCFlg(bean.getId());
			}
			
		}
		
		
		// Update Release Flg
		dao.updateReleaseQuestionFlg(bean);
		
		
		// Release By RO
		 List<ActivityReleaseBean> actList = new ArrayList<>();
		for (String uid : bean.getUids()) {
			ActivityReleaseBean releaseBean  = new ActivityReleaseBean();
			releaseBean.setAct_id(bean.getId());
			releaseBean.setSet_id(bean.getSetId());
			releaseBean.setUid(uid);
			releaseBean.setSid(genSID());
			releaseBean.setChannel(bean.getChannel());
			releaseBean.setRelease_flg(CommonConstants.CHANNEL.CALL_CENTER.equals(bean.getChannel())? "1":"0");
			actList.add(releaseBean) ;
			
			dao.updateExpireData(releaseBean);
			
			dao.insertReleaseData(releaseBean);
		}		
		
		
		return actList;
	} 
	 
	
	 @Async
	 public  void callSMSSender(List<ActivityReleaseBean> actList, String lang){
		 
		String SMS_SEND_FLG = dao.getSendSMS();
		String SMS_CONTENT= dao.getContentSMS(lang);
		System.out.println(" SMS_SEND_FLG ::>> "+SMS_SEND_FLG);
		System.out.println(" SMS_CONTENT ::>> "+SMS_CONTENT);
		
		for (ActivityReleaseBean actBean : actList) {
			try {
				
				// +++++++++++++++++++++++  TODO		
				CustomerRoBean data = dao.getRegisterData(actBean.getUid());
				
				String mobileNo = data.getTel();
				
				boolean validMobile = smsService.validMobileNo(smsService.formatMobile(data.getTel()));
				
				System.out.println("@@ Call SMSSender ::>> "+actBean.getSid()+" , Tel : "+data.getTel());
				System.out.println(" VALID Mobile ::>> "+validMobile);
				
//				Thread.sleep(3000);
				
				String flg = "10"; //-- Mock Send
				
				if("Y".equals(SMS_SEND_FLG) && validMobile){
					
					try {
						
						String content = SMS_CONTENT.replace("{sid}", actBean.getSid());
						
						System.out.println(" SMS content ::>> "+content);
						
						boolean suc = smsService.sendMsg(mobileNo, content);
						flg = suc? "1" : "-1";
					} catch (Exception e) {
						flg = "-9";
						e.printStackTrace();
					}
					
				}else if(!validMobile){// Invalid Mobile
					flg = "99";
				}
				
				dao.updateReleaseActivityFlg(actBean, flg);
					
				// +++++++++++++++++++++++  TODO				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private  String genSID(){
        String code = "";
        String str = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASFGHJKLZXCVBNM";
        String[] strs = str.split("");
        for(int i = 0;i<SID_SIZE;i++){
            code += strs[(int)(Math.random()*strs.length)];
        }
        return code;
    }


	public ActivityBean getActivity(String id) {
		return dao.getActivity(id);
	}


	public DataTableActivity getReleaseData(ActivityBean bean) {
		return dao.getReleaseDataList(bean);
	}

	
}
