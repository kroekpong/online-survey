package com.khotdee168.survey.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.common.service.DDLService;
import com.khotdee168.survey.model.ActivityBean;
import com.khotdee168.survey.service.ActivityService;
import com.khotdee168.survey.service.FollowUpService;
import com.khotdee168.survey.service.ReportService;


@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ReportService reportService ;
	
	@Autowired
	private ActivityService activityService ;
	
	@Autowired
	private DDLService ddlService ;
	
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder )
	{
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor( true ));
	}
	
	
	@RequestMapping(value = "/tracking")
	public String tracking(ModelMap model ) {
		return "report_tracking";
	}
	
	@RequestMapping(value = "/tracking_dashboard")
	public String tracking_dashboard(ModelMap model ,ActivityBean bean ) {
		
//		model.addAttribute("TC_TEXT", ddlService.getSysParamDesc("TC_TEXT"));
		
		model.addAttribute("reportData",  reportService.getTrackingReport(bean));
		
		return "report_tracking_dashboard";
	}
	
	@RequestMapping(value = "/getTracking" )
	public @ResponseBody DataTableAjax  getTracking(ActivityBean bean) {
		DataTableAjax  dataTableAjax =  activityService.getDataTable(bean);
		return dataTableAjax;
	}
	
	
	
	
	
	
	
	
	
	
	 
	@RequestMapping(value = "/score_sheet")
	public String score_sheet(ModelMap model ) {
		
		return "report_score_sheet";
	}
	
	@RequestMapping(value = "/analysis")
	public String analysis(ModelMap model ) {
		
		return "report_analysis";
	}
	
	
	@RequestMapping(value = "/sms")
	public String sms(ModelMap model ) {
		
		return "report_sms";
	}
	
	
	@RequestMapping(value = "/trending")
	public String trending(ModelMap model ) {
		
		return "report_trending";
	}
	
	
	
}
