package com.khotdee168.common.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonController {

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String login(ModelMap model, Locale locale) {
		
		return "login";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,HttpServletResponse response, Locale locale) {
		if (error != null) {
			model.addAttribute("error", "Invalid Username or Password !");
		}
		if (logout != null) {
			model.addAttribute("msg", "You have been successfully logged out !");
		}
		
		response.addHeader("REQUIRES_AUTH","1");
		
		return "login";
	}
	
	/*@GetMapping("/mlogin")
	public String mloginPage(Model model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,HttpServletResponse response) {
		if (error != null) {
			model.addAttribute("error", "Invalid Username or Password !");
		}
		if (logout != null) {
			model.addAttribute("msg", "You have been successfully logged out !");
		}
		
		response.addHeader("REQUIRES_AUTH","1");
		
		return "mlogin";
	} 
	
	@RequestMapping(value = {"/maintenance"}, method = RequestMethod.GET)
	public String maintenance(ModelMap model) {
		return "maintenance";
	}*/
	
//	@RequestMapping("/error")
//	public String handleError(HttpServletRequest request) {
//	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//	     
//	    if (status != null) {
//	        Integer statusCode = Integer.valueOf(status.toString());
//	     
//	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
//	            return "error-404";
//	        }
//	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//	            return "error-500";
//	        }
//	    }
//	    return "error";
//	}
 
    
}
