package com.khotdee168.survey.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khotdee168.common.constant.CommonConstants;
import com.khotdee168.survey.model.RegisterBean;
import com.khotdee168.survey.service.RegisterService;

@Controller
@RequestMapping("/ws")
public class SurveyWS {
	
	@Autowired
	private RegisterService   service ;
	
	@RequestMapping("/register")
	public ResponseEntity<?> register(@ModelAttribute RegisterBean bean ) {
		try {
			
			System.out.println(bean);
			if(service.validUid(bean)){
				return new ResponseEntity<>(CommonConstants.HTTP.STATUS_DUPLICATE, HttpStatus.OK);
			}
			
			if(service.register(bean)){
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
	
	
	@RequestMapping("/cancel/{uid}")
	public ResponseEntity<?> cancel(@ModelAttribute RegisterBean bean ) {
		try {
			System.out.println(bean);
			if(service.cancel(bean)){
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