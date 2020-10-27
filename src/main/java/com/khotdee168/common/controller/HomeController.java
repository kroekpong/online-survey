package com.khotdee168.common.controller;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/home" )
	public String init(ModelMap model, Locale locale) {
			return "home";
	}
	
	
	@RequestMapping(value = "/dashboard")
	public String dashboard(ModelMap model, String d) {
		
		return "dashboard";
	}
	
	
	@RequestMapping(value = "/status" )
	@ResponseBody
	public ResponseEntity<?> status() {
		return new ResponseEntity<>("0", HttpStatus.OK);
	}
	
	
	
}
