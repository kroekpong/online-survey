package com.khotdee168.common.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khotdee168.common.constant.CommonConstants;
import com.khotdee168.common.model.DataTableAjax;
import com.khotdee168.common.model.UserBean;
import com.khotdee168.common.service.DDLService;
import com.khotdee168.common.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private DDLService ddlService;

	@Autowired
	private UserService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String init(ModelMap model) {

		model.addAttribute("DDL_COMPANY", ddlService.getActiveCompany());
		model.addAttribute("DDL_STATUS", ddlService.getStatus());
		model.addAttribute("DDL_ROLE", ddlService.getRoles());
		
		
		return "user";
	}

	@RequestMapping(value = "/getDataTable")
	public @ResponseBody DataTableAjax getDataTable(@ModelAttribute UserBean bean) {
		DataTableAjax dataTableAjax = service.getDataTable(bean);
		return dataTableAjax;
	}
	
	
	@RequestMapping("/save")
	public ResponseEntity<?> saveOrUpdate(@ModelAttribute UserBean bean) {
		try {
			System.out.println(bean);
			
			if (service.checkDuplicate(bean) && StringUtils.isEmpty(bean.getUserId())) {
				return new ResponseEntity<>(CommonConstants.HTTP.STATUS_DUPLICATE, HttpStatus.OK);
			}

				
			if (service.saveOrUpdate(bean)) {
				return new ResponseEntity<>(CommonConstants.HTTP.STATUS_SUCCESS, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(CommonConstants.HTTP.STATUS_FAILED, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = e + " <== error";
			return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public ResponseEntity<?> getFoosBySimplePathWithPathVariable(@PathVariable String id) {
		try {
			if(service.delete(id)){
				return new ResponseEntity<>("0", HttpStatus.OK);
			}else{
				return new ResponseEntity<>("99", HttpStatus.BAD_REQUEST);
			}
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("Cannot delete, this user already assigned !", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "rolemap", method = RequestMethod.GET)
	public String rolemap(ModelMap model) {
		
		model.addAttribute("DDL_ROLE", ddlService.getRoles());
		model.addAttribute("DATA_ROLE", service.getRolesFunction());

//		DataTableAjax dataTableAjax = service.getDataTable(bean);
		
		return "rolemap";
	}
	
	
	
}
