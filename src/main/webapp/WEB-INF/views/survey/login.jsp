<%@ page isELIgnored="false" %>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html >

<head>
    <title><spring:message code="app.title" /></title>
  
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    
    <link rel="icon" href="assets/images/MG LOGO RED.png" type="image/x-icon">
<!--     <link rel="icon" href="assets/img/icorcm.jpg" type="image/x-icon"> -->
<!--     <link rel="icon" type="image/x-icon" href="https://www.mgcars.com/images/MG LOGO RED 200PX-02.png"> -->
    
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    
	    
	<c:set var="localeCode" value="${pageContext.response.locale.language}" />
	<%-- <c:set var="availLanguages" value="EN,TH" /> --%>
	
	<fmt:setBundle basename="messages"/>

<style>
     
@font-face {
	font-family: "Poppins";
	src: url("assets/fonts/Poppins/Poppins-Regular.ttf");
}

body , html, h2{
	font-family: 'Poppins', sans-serif !important;
}

h4{
color: #4099ff;
}

body {
   background: #FFF;
}

.login{
/* 	 background: url("assets/images/bg_mg.jpg") no-repeat center fixed; */
	  -webkit-background-size: cover;
	  -moz-background-size: cover;
	  -o-background-size: cover;
	  background-size: cover;  
	  display : flex  !important;
	 background: #FFF;
}

.logo{
	height: 85px;
}

@media screen and (max-width: 992px) {
	.lg-f{
		text-align: center !important;
	}
	.mb-hide{
		display: none;
	}
}


 .header_k {
    width: 100%;
    height: 85px;
    position: relative;
    z-index: 110;
    top: 0;
    background: #C72330;
    background-image: linear-gradient(to bottom,#C72330,#C72330);
    background-repeat: repeat-x;
}
  

.lan-wrap {
     width: 150px; 
    position: fixed;
    top: 0;
    right: 0;
    height: 67px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #fff;
    margin-right: 10px;
    top: 20px;
    margin-right: 20px;
    
}

.lan-body {
/*     display: none; */
     position: absolute; 
     top: 67px; 
     right: 0; 
     width: 150px; 
     color: black; 
     display: flex; 
   justify-content: center; 
    align-items: center;
    flex-wrap: wrap;
/*      cursor: pointer;  */
}

.lefta-box {
    margin-left: auto;
    margin-right: auto;
    width: 530px;
}

.ch-container {
    margin-left: auto;
    margin-right: auto;
    padding-top: 38px;
    padding-bottom: 38px;
}

.ilabel {
    font-size: 18px;
}

.iform {
	border-radius: 4px;
}

.w100{
	width: 100px;
}

.lan-title{
	  color: #fff;
	  font-size: 16px;
}

.lan-text {
    width: 100%;
    text-align: center;
    color: #333;
    cursor: pointer;
    font-size: 16px;
}

</style>
</head>



<body class="fix-menu">
        <!-- Pre-loader start -->
    <div class="theme-loader">
        <div class="loader-track">
            <div class="loader-bar"></div>
        </div>
    </div>
    <!-- Pre-loader end -->
    
    
    <div class="header_k" id="btop">
		<div class="header">
			<a href="#" title=""> <img style="margin-left: 35px;" border="0" src="assets/images/logos.png">
			</a>
		</div>

	 <div class="lan-wrap">
			<p  id="lanTitle">
				<a href="#"  class="lan-title" onclick="showLang()"> <spring:message code="login.langChange" /> </a>
			</p> 
			
			
			<div id="lanBody" class="lan-body" >
				<a class="lan-text m-b-5" href="?lang=en" >English</a>
				<a class="lan-text m-b-5" href="?lang=th" >ภาษาไทย</a>
<!-- 				<a class="lan-text" onclick="changelan(3)">Thai</a> -->
                               
                               
			</div>
		</div>
	</div>
	
	
	
	 
	 
    <section class="ch-container   text-center ">
    
    <div class="lefta-box">
    
   
		
		
        <form class="md-float-material" action="login" method="post">
<!--                             <div class="auth-box"> -->
                                <div class="row  ">
                                    <div class="col-md-12">
			                                <img src="assets/images/logo_mg_sm.png"  class="logo" alt="logo.png">
<%--                                         <h4 class="text-center m-3 f-w-50 0"><spring:message code="app.name" /></h4> --%>
                                    </div>
                                </div>
                
		 <div class="row">
			<div class="col-md-12 login-header">
				<p style="padding: 20px; font-size: 16px; color: #777;"><spring:message code="login.wtxt" /></p>
			</div>
		</div>
                                
           <div class="row m-t-20">
			<div class="col-md-12 ">
			
                                <c:if test="${not empty error}">
                               	 <div class="alert alert-danger" role="alert"> ${error} </div>
								</c:if>
								
								<div class="form-group row">
                                    <label class="col-sm-3 col-form-label ilabel"><spring:message code="login.account" /></label>
                                    <div class="col-sm-8">
                                             <input type="text" class="form-control iform" placeholder="<spring:message code="login.iaccount" />" name="username" value="qeioc">
                                    </div>
                                </div>
                                
								<div class="form-group row">
                                    <label class="col-sm-3 col-form-label ilabel"><spring:message code="login.Password" /></label>
                                    <div class="col-sm-8">
                                             <input type="password" class="form-control iform" placeholder="<spring:message code="login.iPassword" />" name="password" value="Pass@1234">
                                    </div>
                                </div>
                                
                                
                               <!--  <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Username" name="username" value="qeioc">
                                    <span class="md-line"></span>
                                </div> -->
<!--                                 <div class="input-group"> -->
<!--                                     <input type="password" class="form-control" placeholder="Input Password" name="password" value="Pass@1234"> -->
<!--                                     <span class="md-line"></span> -->
<!--                                 </div> -->
                                
                                <div class="row m-t-30">
                                    <div class="col-md-12">
                                        <button type="submit" class="btn btn-primary w100"><spring:message code="login.btnTxt" /></button>
                                    </div>
                                </div>
                                
                        </div>
		</div>       
<!--  <div class="row"> -->
<!-- 			<div class="col-md-5 p-t-10"> -->
<!-- 				<p style=" color: #777;">Version 1.0.2</p> -->
<!-- 			</div> -->
<!-- 		</div> -->
		
<!--                             </div> -->
                        </form>
                        
                        </div>
    </section> 
    
 
    <!-- Warning Section Ends -->
    <!-- Required Jquery -->
    <script type="text/javascript" src="assets/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="assets/js/jquery-ui/jquery-ui.min.js"></script>
    <script type="text/javascript" src="assets/js/popper/popper.min.js"></script>
    <script type="text/javascript" src="assets/js/bootstrap/js/bootstrap.min.js"></script>
    
   <script>
	     
// 	  var isChrome = /Chrome/.test(navigator.userAgent) && /Google Inc/.test(navigator.vendor);

// 	  if(detectMob()){
// 		  location = "mlogin";
// 	  }

$(document).ready(function() {
	
    $(".theme-loader").animate({
        opacity: "0"
    },500);
    
    setTimeout(function() {
        $(".theme-loader").remove();
    }, 500);
    
    $("#lanTitle").focus(function() {
    	 $("#lanBody").show();
    });
    
    $('#lanTitle').focusout(function() {
   	  $('#lanBody').hide();
   	});
    
    $("#lanBody").hide();
     
});


function showLang() {
	 $("#lanBody").toggle();
}

// function changeLang(lang) {
// 	console.log(lang);
// 	location = "?lang="+lang;
// }

</script>
    
    
</body>

</html>
