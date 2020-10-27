package com.khotdee168.survey.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.khotdee168.survey.service.ActivityService;


@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService service ;
	
	@Autowired
	private DDLService ddlService ;
	
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder )
	{
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor( true ));
	}
	
	
	@RequestMapping(value = "/maintain")
	public String init(ModelMap model, Locale locale) {
		return "maintain_activity";
	}
	
	@RequestMapping(value = "/getDataTable" )
	public @ResponseBody DataTableAjax  getDataTable(ActivityBean bean) {
		
		DataTableAjax  dataTableAjax =  service.getDataTable(bean);
		return dataTableAjax;
	}
	
	 @RequestMapping(value = "/addActivity")
	 public String addActivity(Model model, HttpSession session) {
		 model.addAttribute("DDL_RULES", ddlService.getRules());
		 model.addAttribute("DDL_REPAIR_TYPE", ddlService.getRepairType());
		 model.addAttribute("DDL_QUESTION_SET", ddlService.getQuestionSet());
		 
		 return "activity_add_modal";
	 }
	 
	 @RequestMapping(value = "/editActivity")
	 public String editActivity(Model model,ActivityBean bean) {
		 model.addAttribute("DDL_RULES", ddlService.getRules());
		 model.addAttribute("DDL_REPAIR_TYPE", ddlService.getRepairType());
		 model.addAttribute("DDL_QUESTION_SET", ddlService.getQuestionSetAll());
		 
		 ActivityBean  activityBean =  service.getActivity(bean.getId());
		 model.addAttribute("activityBean", activityBean);
		 model.addAttribute("activityId", bean.getId());
		 return "activity_edit_modal";
	 }
	 
	 @RequestMapping(value = "/view")
	 public String viewActivity(Model model,ActivityBean bean) {
		 model.addAttribute("DDL_RULES", ddlService.getRules());
		 model.addAttribute("DDL_REPAIR_TYPE", ddlService.getRepairType());
		 model.addAttribute("DDL_QUESTION_SET", ddlService.getQuestionSet());
		 
		 ActivityBean  activityBean =  service.getActivity(bean.getId());
		 model.addAttribute("activityBean", activityBean);
		 model.addAttribute("activityId", bean.getId());
		 return "activity_view_modal";
	 }
	 
//	@RequestMapping("/save")
//	public ResponseEntity<?> save(ActivityBean bean, String data) {
//		try {
			
//			Gson gson = new  Gson();
//			List<String> uids = gson.fromJson(data, ActivityBean.class);
//			if(service.saveOrUpdate(bean)){
//				return new ResponseEntity<>(CommonConstants.HTTP.STATUS_SUCCESS, HttpStatus.OK);
//			}else{
//				return new ResponseEntity<>(CommonConstants.HTTP.STATUS_FAILED, HttpStatus.BAD_REQUEST);
//			}
			 
//		} catch (Exception e) {
//			e.printStackTrace();
//			String errorMessage = e + " <== error";
//	        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//		}
//	}
	
	@RequestMapping("/release")
	public ResponseEntity<?> release(ActivityBean bean, Locale locale) {
		try {
			
			List<ActivityReleaseBean> list = service.releaseActivity(bean);
			
			if(null!=list && list.size()>0){
				if(CommonConstants.CHANNEL.SMS.equals(bean.getChannel())){ 
					service.callSMSSender(list, locale.getLanguage());
				}
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
	
	
	
	@RequestMapping(value = "/delete/{id}" )
	@ResponseBody
	public ResponseEntity<?> delete( @PathVariable String id) {
		
		try {
			if(service.delete(id)){
				return new ResponseEntity<>("0", HttpStatus.OK);
			}else{
				return new ResponseEntity<>("99", HttpStatus.BAD_REQUEST);
			}
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("Cannot delete, this company already assigned !", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		 
	}
	
	
	@RequestMapping(value = "/getCustomerRo" )
	public @ResponseBody DataTableActivity  getCustomerRo(ActivityBean bean) {
		DataTableActivity  dataTableAjax =  service.getCustomerRo(bean);
		return dataTableAjax;
	}
	
	@RequestMapping(value = "/getReleaseData" )
	public @ResponseBody DataTableActivity  getReleaseData(ActivityBean bean) {
		DataTableActivity  dataTableAjax =  service.getReleaseData(bean);
		return dataTableAjax;
	}
	
	
//	@RequestMapping(value = "/getActivitySet/{sid}" )
//	public @ResponseBody ActivityBean  getActivitySet(@PathVariable("sid") String sid) {
//		ActivityBean  qSetBean =  service.getActivitySet(sid,false);
//		return qSetBean;
//	}
	
	
}
