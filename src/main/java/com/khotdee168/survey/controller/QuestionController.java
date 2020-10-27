package com.khotdee168.survey.controller;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.khotdee168.common.constant.CommonConstants;
import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.common.service.DDLService;
import com.khotdee168.survey.model.QuestionSetBean;
import com.khotdee168.survey.service.QuestionService;


@Controller
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService service ;
	
	@Autowired
	private DDLService ddlService ;
	
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder )
	{
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor( true ));
	}
	
	
	@RequestMapping(value = "/maintain")
	public String init(ModelMap model ) {
		return "maintain_question";
	}
	
	@RequestMapping(value = "/getDataTable" )
	public @ResponseBody DataTableAjax  getDataTable(@ModelAttribute QuestionSetBean bean) {
		DataTableAjax  dataTableAjax =  service.getDataTable(bean);
		return dataTableAjax;
	}
	
	 @RequestMapping(value = "/addQuestion")
	 public String addQuestion(Model model, HttpSession session) {
		 model.addAttribute("DDL_QUESTION_TYPE", ddlService.getQuestionType());
		 model.addAttribute("DDL_QUESTION_GROUP", ddlService.getQuestionGroup());
		 return "question_add_modal";
	 }
	 
	 @RequestMapping(value = "/viewQuestion/{sid}")
	 public String viewQuestion(ModelMap model, @PathVariable String sid) {
		 model.addAttribute("sid", sid);
		 return "view_question";
	 }
	 
	 @RequestMapping(value = "/editQuestion")
	 public String editQuestion(ModelMap model,String sid) {
		 model.addAttribute("sid", sid);
		 model.addAttribute("DDL_QUESTION_TYPE", ddlService.getQuestionType());
		 model.addAttribute("DDL_QUESTION_GROUP", ddlService.getQuestionGroup());
		 return "question_edit_modal";
	 }
	 
	@RequestMapping("/save")
	public ResponseEntity<?> save(String data) {
		try {
			
			Gson gson = new  Gson();
			QuestionSetBean bean = gson.fromJson(data, QuestionSetBean.class);
			if(service.saveOrUpdate(bean)){
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
	
	@RequestMapping(value = "/getQuestionSet/{sid}" )
	public @ResponseBody QuestionSetBean  getQuestionSet(@PathVariable("sid") String sid) {
		QuestionSetBean  qSetBean =  service.getQuestionSet(sid,false);
		return qSetBean;
	}
	
	@RequestMapping(value = "/getQuestionView/{sid}" )
	public @ResponseBody QuestionSetBean  getQuestionView(@PathVariable("sid") String sid) {
		QuestionSetBean  qSetBean =  service.getQuestionView(sid); 
		return qSetBean;
	}
	
	
}
