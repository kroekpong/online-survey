package com.khotdee168.survey.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khotdee168.common.constant.CommonConstants;
import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.common.service.DDLService;
import com.khotdee168.survey.model.ActivityBean;
import com.khotdee168.survey.model.ActivityReleaseBean;
import com.khotdee168.survey.model.DataTableActivity;
import com.khotdee168.survey.model.FollowUpActivityBean;
import com.khotdee168.survey.model.FollowUpLogBean;
import com.khotdee168.survey.service.ActivityService;
import com.khotdee168.survey.service.FollowUpService;


@Controller
@RequestMapping("/followup")
public class FollowUpController {

	@Autowired
	private FollowUpService service ;
	
	@Autowired
	private ActivityService activityService ;
	
	@Autowired
	private DDLService ddlService ;
	
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder )
	{
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor( true ));
	}
	
	
	@RequestMapping(value = "")
	public String init(ModelMap model, Locale locale) {
		return "followup_search";
	}
	
	@RequestMapping(value = "/getDataTable" )
	public @ResponseBody DataTableAjax  getDataTable(FollowUpActivityBean bean) {
		
		DataTableAjax  dataTableAjax =  service.getDataTable(bean);	
		return dataTableAjax;
	} 
	
	
	@RequestMapping(value = "/info/{id}")
	public String info(ModelMap model ,@PathVariable String id) {
		
		 ActivityBean  activityBean =  activityService.getActivity(id);
		 model.addAttribute("activityBean", activityBean);
		 model.addAttribute("activityId", id);
		
		model.addAttribute("TC_SHOW_CC", ddlService.getSysParamValue("TC_SHOW_CC"));
		model.addAttribute("TC_TEXT_CC", ddlService.getSysParamDesc("TC_TEXT_CC"));
		model.addAttribute("DDL_SURVEY_STATUS", ddlService.getDDLType("DDL_SURVEY_STATUS"));

		return "followup_info";
	}
	
	@RequestMapping(value = "/track/{sid}")
	public String track(ModelMap model ,@PathVariable String sid) {
		
		
		FollowUpActivityBean  activityBean =  service.getActivityBySid(sid);
		
		List<FollowUpLogBean>  logList =  service.getFollowUpLog(sid);
		
		model.addAttribute("activityBean", activityBean);
		model.addAttribute("logList", logList);
		
		model.addAttribute("DDL_CONTACT_STATUS", ddlService.getDDLType("DDL_CONTACT_STATUS"));
		model.addAttribute("DDL_REASON", ddlService.getDDLType("DDL_REASON"));
		 
		
		return "followup_track";
	}
	
	
	@RequestMapping(value = "/getInfoDataTable" )
	public @ResponseBody DataTableActivity  getInfoDataTable(FollowUpActivityBean bean) {
		DataTableActivity  dataTableAjax =  service.getInfoDataTable(bean);
		return dataTableAjax;
	} 
	
	@RequestMapping("/saveLog")
	public ResponseEntity<?> saveLog(FollowUpLogBean bean ) {
		try {
			
			if(service.saveLog(bean)){
				return new ResponseEntity<>(CommonConstants.HTTP.STATUS_SUCCESS, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(CommonConstants.HTTP.STATUS_FAILED, HttpStatus.BAD_REQUEST);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = e + " <== error";
	        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
