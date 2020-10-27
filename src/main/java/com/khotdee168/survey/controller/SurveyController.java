package com.khotdee168.survey.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.khotdee168.common.constant.CommonConstants;
import com.khotdee168.common.service.DDLService;
import com.khotdee168.survey.model.ActivityVoteBean;
import com.khotdee168.survey.model.FollowUpActivityBean;
import com.khotdee168.survey.model.QuestionSetBean;
import com.khotdee168.survey.service.ActivityVoteService;
import com.khotdee168.survey.service.FollowUpService;

@Controller
public class SurveyController {

	@Autowired
	private FollowUpService fservice;
	
	@Autowired
	private ActivityVoteService service;

	@Autowired
	private DDLService ddlService;

	@RequestMapping(value = "/v/{sid}")
	public String SMS_Vote(ModelMap model, @PathVariable String sid) {

		FollowUpActivityBean surveyBean = fservice.getActivityBySid(sid);

		model.addAttribute("surveyBean", surveyBean);
		model.addAttribute("setId", surveyBean.getSetId());

		model.addAttribute("DDL_OCCUPATION", ddlService.getDDLType("DDL_OCCUPATION"));
		model.addAttribute("DDL_INCOME", ddlService.getDDLType("DDL_INCOME"));
		model.addAttribute("DDL_HOBBY", ddlService.getDDLType("DDL_HOBBY"));

		model.addAttribute("TC_SHOW", ddlService.getSysParamValue("TC_SHOW"));
		model.addAttribute("TC_TEXT", ddlService.getSysParamDesc("TC_TEXT"));
		
		model.addAttribute("CHANNEL", CommonConstants.CHANNEL.SMS);
		
		fservice.updateOpenFlg(sid);

		return "survey_activity";
	}

	@RequestMapping(value = "/vote")
	public String QR_Vote(ModelMap model,@RequestParam Map<String,String> allParams) {

//		System.out.println("keySet : "+ allParams.keySet());
		
		String uid = null;
		
		if(null!=allParams && allParams.keySet().size()>=0){
				uid = (String) allParams.keySet().toArray()[0];
		}
		
//		System.out.println("uid : "+uid);
		
		if(null!=uid){
			FollowUpActivityBean surveyBean = fservice.getActivityByUid(uid);
			model.addAttribute("surveyBean", surveyBean);
			model.addAttribute("setId", surveyBean.getSetId());
		}
		
		model.addAttribute("DDL_OCCUPATION", ddlService.getDDLType("DDL_OCCUPATION"));
		model.addAttribute("DDL_INCOME", ddlService.getDDLType("DDL_INCOME"));
		model.addAttribute("DDL_HOBBY", ddlService.getDDLType("DDL_HOBBY"));
		
		model.addAttribute("TC_SHOW", ddlService.getSysParamValue("TC_SHOW"));
		model.addAttribute("TC_TEXT", ddlService.getSysParamDesc("TC_TEXT"));

		model.addAttribute("CHANNEL",  CommonConstants.CHANNEL.QR);
		
		return "survey_activity";
	}

	@RequestMapping(value = "/ccvote/{sid}")
	public String survey(ModelMap model, @PathVariable String sid) {

		FollowUpActivityBean surveyBean = fservice.getActivityBySid(sid);

		model.addAttribute("surveyBean", surveyBean);
		model.addAttribute("setId", surveyBean.getSetId());

		model.addAttribute("DDL_OCCUPATION", ddlService.getDDLType("DDL_OCCUPATION"));
		model.addAttribute("DDL_INCOME", ddlService.getDDLType("DDL_INCOME"));
		model.addAttribute("DDL_HOBBY", ddlService.getDDLType("DDL_HOBBY"));
		
		model.addAttribute("CHANNEL", CommonConstants.CHANNEL.CALL_CENTER);
		
		return "vote_activity";
	}

	@RequestMapping(value = "/sms")
	public String SMS_Test(ModelMap model) {
		return "sms_test";
	}
	
	@PostMapping(value = "/sms/send")
	public ResponseEntity<?> sendsms(Locale locale, String tel, String sid) {
		
		if(fservice.testSMSSender(tel,sid,locale.getLanguage())){
			return new ResponseEntity<>(CommonConstants.HTTP.STATUS_SUCCESS, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(CommonConstants.HTTP.STATUS_FAILED, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/vote/save")
	public ResponseEntity<?> save(String data) {
		
		Gson gson = new  Gson();
		ActivityVoteBean bean = gson.fromJson(data, ActivityVoteBean.class);
		System.out.println(bean);
		
		if(service.submitVote(bean)){
			return new ResponseEntity<>(CommonConstants.HTTP.STATUS_SUCCESS, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(CommonConstants.HTTP.STATUS_FAILED, HttpStatus.BAD_REQUEST);
		}
		
	}

	
	

}
