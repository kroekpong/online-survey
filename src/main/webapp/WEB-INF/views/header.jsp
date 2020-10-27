
<%@ page isELIgnored="false" %>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="com.khotdee168.common.model.UserProfile"%>
<%@ page import="com.khotdee168.common.utils.UserLoginUtils"%>
<%@ page import="java.util.Locale"%>

<c:set var="context" value="${pageContext.request.contextPath}"  scope="session" />


<c:set var="localeCode" value="${pageContext.response.locale.language}" />
<%-- <c:set var="availLanguages" value="EN,TH" /> --%>

<fmt:setBundle basename="messages"/>


<!DOCTYPE html>
<html lang="en">

<head>

	<title><spring:message code="app.name" /></title>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	
	<link rel="icon" href="${context}/assets/images/MG LOGO RED.png" type="image/x-icon">
	 
	
	<link rel="stylesheet" type="text/css" href="${context}/assets/css/bootstrap/css/bootstrap.min.css">
	<!-- themify-icons line icon -->
	<link rel="stylesheet" type="text/css" href="${context}/assets/icon/themify-icons/themify-icons.css">
	<link rel="stylesheet" type="text/css" href="${context}/assets/icon/font-awesome/css/font-awesome.min.css">
	<!-- ico font -->
	<link rel="stylesheet" type="text/css" href="${context}/assets/icon/icofont/css/icofont.css">

      <link rel="stylesheet" type="text/css" href="${context}/assets/pages/notification/notification.css">
      
      
      <!-- Animate.css -->
      <link rel="stylesheet" type="text/css" href="${context}/assets/css/animate.css/css/animate.css">
      
	<!-- Style.css -->
	<link rel="stylesheet" type="text/css" href="${context}/assets/css/jquery.mCustomScrollbar.css">
	<link rel="stylesheet" type="text/css" href="${context}/assets/css/dataTables.bootstrap4.min.css">
	
<%-- 	<link rel="stylesheet" href="${context}/assets/css/bootstrap-select.min.css"> --%>
	<link rel="stylesheet" href="${context}/assets/css/bootstrap-datepicker.min.css">
	
<!-- 	<link rel="stylesheet" href="http://html.codedthemes.com/gradient-able/bootstrap/default/assets/css/plugins/select2.min.css"> -->
	
	
	<link rel="stylesheet" href="${context}/assets/css/select2.min.css" />
<%-- 	<link rel="stylesheet" href="${context}/assets/css/select2-bootstrap4.min.css"> --%>
	
	
	<link rel="stylesheet" type="text/css" href="${context}/assets/css/style.css">
	<link rel="stylesheet" type="text/css" href="${context}/assets/css/custom.css">
	
	
	<!-- CSS only -->
	
	
	<script type="text/javascript" src="${context}/assets/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${context}/assets/js/jquery-ui/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${context}/assets/js/popper/popper.min.js"></script>
	<script type="text/javascript" src="${context}/assets/js/bootstrap/js/bootstrap.min.js"></script>
	
	
	<!-- notification js -->
	<script type="text/javascript" src="${context}/assets/js/bootstrap-growl.min.js"></script>
	<script type="text/javascript" src="${context}/assets/pages/notification/notification.js"></script>
		
	
	<script type="text/javascript" src="${context}/assets/js/modernizr/modernizr.js"></script>
	<script type="text/javascript" src="${context}/assets/js/jquery-slimscroll/jquery.slimscroll.js"></script>
	<script type="text/javascript" src="${context}/assets/js/pcoded.min.js"></script>
	<script type="text/javascript" src="${context}/assets/js/vartical-demo.js"></script>
	<script type="text/javascript" src="${context}/assets/js/jquery.mCustomScrollbar.concat.min.js"></script>


	<script src="${context}/assets/js/jquery.dataTables.min.js"></script>
	<script src="${context}/assets/js/dataTables.bootstrap4.min.js"></script>
	
 
	<script src="${context}/assets/js/sweetalert.min.js"></script>
<!-- 	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script> -->

	<script src="${context}/assets/js/bootstrap-datepicker.min.js"></script>
	
	
	
	<!-- Bootstrap 4 Autocomplete -->
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap-4-autocomplete/dist/bootstrap-4-autocomplete.min.js" crossorigin="anonymous"></script> -->

<script src="${context}/assets/js/select2.min.js"></script>	
	
	<script type="text/javascript" src="${context}/assets/js/script.js"></script>
	
	<c:set var="_ALL" scope="session" value="--- ALL ---"/>
	<c:set var="_SEL" scope="session" value="--- Please Select ---"/>
		
	 <%
		String username = UserLoginUtils.getCurrentUser().getUsername();
		UserProfile userProfile = null;
		if(null!=UserLoginUtils.getCurrentUser()){
			userProfile = UserLoginUtils.getCurrentUser();
	 		session.setAttribute("userProfile",userProfile);
		}

  %>
		

  	
<script>


	var cPath = "${context}"; 
	 
	var _localeCode = "${localeCode}"; 
	var _lang = "${localeCode}"=="th" ? "Th":"En"; 


	var _confirmSaveTxt =  '<fmt:message key="msg.confirmSaveTxt" />';
	var _confirmDelTxt = '<fmt:message key="msg.confirmDelTxt" />';
	var _successDelTxt =  '<fmt:message key="msg.successDelTxt" />';
	var _successSaveTxt =  '<fmt:message key="msg.successSaveTxt" />';
	var _confirmCloseTxt =  '<fmt:message key="msg.confirmCloseTxt" />';

	var _chkTxt = '<fmt:message key="msg.chkTxt" />';
	var _multiTxt =  '<fmt:message key="msg.multiTxt" />';
	var _questionTxt = '<fmt:message key="msg.questionTxt" />';
	var _answerTxt =  '<fmt:message key="msg.answerTxt" />';
	var _comAllTxt =  '<fmt:message key="msg.comAllTxt" />';
	var _errorTxt =  '<fmt:message key="msg.errorTxt" />';
	var _Yes ='<fmt:message key="msg.Yes" />';
	var _No =  '<fmt:message key="msg.No" />';
	var _Default =  '<fmt:message key="msg.Default" />';
	var _totalTxt = '<fmt:message key="msg.totalTxt" />';
	var _numberMaxTxt = '<fmt:message key="msg.numberMaxTxt" />';
	 

	
// 	function detectMob() {
// 		    return ( ( window.innerWidth <= 800 ) && ( window.innerHeight <= 600 ) );
// 	}
	  
	$(document).ready(function() {
		
		$("form").bind("keydown", function(e) {
		   	if (e.keyCode === 13) return false;
		 });
		
// 		console.log("detectMob() > "+detectMob());

	    
	 });
	
	  $(function() { 
	      $(".numonly").on('input', function(e) { 
	          $(this).val($(this).val().replace(/[^0-9]/g, '')); 
	      }); 
	  });
	   
	  
	  $(document).ajaxComplete(function(e, xhr, settings){
    	  if (xhr.getResponseHeader('REQUIRES_AUTH') == '1') {
    		  window.location = cPath+'/logout';
    	  } 
//     	  console.log(xhr);
//     	  console.log(xhr.getResponseHeader('REQUIRES_AUTH'));
    });
	 
	  
	  /* var isChrome = /Chrome/.test(navigator.userAgent) && /Google Inc/.test(navigator.vendor);

	  if(!isChrome){
		  alert('logout');
		  swal("Please open in Chrome Browser !");
		  window.location = cPath+'/logout';
	  } */
	  
// 	  console.log("_localeCode : ",_localeCode)
// 	  console.log("lang : ",_lang)
 
 
</script>


</head> 
 
		