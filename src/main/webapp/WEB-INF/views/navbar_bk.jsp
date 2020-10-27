<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<nav class="navbar header-navbar pcoded-header" >

               <div class="navbar-wrapper">
                   <div class="navbar-logo">
                       <a class="mobile-menu" id="mobile-collapse" href="#!">
                           <i class="ti-menu"></i>
                       </a>
                       <div class="mobile-search">
                           <div class="header-search">
                               <div class="main-search morphsearch-search">
                                   <div class="input-group">
                                       <span class="input-group-addon search-close"><i class="ti-close"></i></span>
                                       <input type="text" class="form-control" placeholder="Enter Keyword">
                                       <span class="input-group-addon search-btn"><i class="ti-search"></i></span>
                                   </div>
                               </div>
                           </div>
                       </div>
                       <a href="home" > 
<%--                        	<img src="${context}/assets/img/homeceramic2.png" alt=""  class="logo-img"> --%>
                           <img src="${context}/assets/images/mg-logo.png"  class="logo-img"/>
                          
                       </a>
                       <a class="mobile-options">
                           <i class="ti-more"></i>
                       </a>
                   </div>

                   <div class="navbar-container container-fluid">
                       <ul class="nav-left">
                           <li>
                               <div class="sidebar_toggle"><a href="javascript:void(0)"><i class="ti-menu"></i></a></div>
                           </li>
                        <!--    <li>
                               <a href="#!" onclick="javascript:toggleFullScreen()">
                                   <i class="ti-fullscreen"></i>
                               </a>
                           </li> -->
                          <!--  <li class="header-search">
                               <div class="main-search morphsearch-search">
                                   <div class="input-group">
                                       <span class="input-group-addon search-close"><i class="ti-close"></i></span>
                                       <input type="text" class="form-control">
                                       <span class="input-group-addon search-btn"><i class="ti-search"></i></span>
                                   </div>
                               </div>
                           </li>
                           <li>
                               <a href="#!" onclick="javascript:toggleFullScreen()">
                                   <i class="ti-fullscreen"></i>
                               </a>
                           </li> -->
                       </ul>

                       <ul class="nav-right">
                           
                           <li class="header-notification">
                               <a href="#" style="text-transform: uppercase;"> ${localeCode}
<!--                                    <i class="ti-direction-alt"></i> -->
<!--                                    <span class="badge bg-success"></span> -->
                               </a>
                               <ul class="show-notification" style="width: 200px;">
                                   <li>
                                       <h6><fmt:message key="login.langChange" /></h6>
<!--                                        <label class="label label-info"><i class="ti-direction-alt"></i></label> -->
                                   </li>
                                   <li>
                                       <div class="media">
                                           <div class="media-body">
                                               <a href="?lang=en" class="notification-msg">EN : English</a>
                                           </div>
                                       </div>
                                   </li>
                                   <li>
                                       <div class="media">
                                           <div class="media-body">
                                                     <a href="?lang=th" class="notification-msg">TH : ภาษาไทย</a>
<!--                                                <p class="notification-msg">TH : ภาษาไทย</p> -->
                                           </div>
                                       </div>
                                   </li>
                                    
                               </ul>
                           </li>
                           
                           
                           <li class="user-profile header-notification">
                           
                               <a href="#!">
<!--                                <i class="fa fa-user-circle-o" aria-hidden="true" style="  font-size: 20px; padding-right: 9px;"></i> -->
                                   
                                   <img src="${context}/assets/images/avatar-admin.png" class="img-radius photo-border" alt="ADMIN">
                                  
                                  
                                    <span> 
                                  	 <c:out value="${userProfile.userName}"></c:out>
<!--                                   	 <p style=" font-size: 12px;"> -->
<%--                                   	 (<c:out value="${userProfile.supName}"></c:out>) --%>
<!--                                   	 </p> -->
                                   </span>
                                  
                                  
                                  
                                  
                                  
                                  
                                  
<!--                                    <span>  -->
<%--                                   	 <c:out value="${userProfile.name}"></c:out> --%>
<!--                                   	 <p style=" font-size: 12px;"> -->
<%--                                   	 (<c:out value="${userProfile.supName}"></c:out>) --%>
<!--                                   	 </p> -->
<!--                                    </span> -->

                                  
<!--                                    <span   style=" font-size: 12px;" > -->
<%--                               			(<c:out value="${userProfile.supName}"></c:out>) --%>
<!--                                    </span> -->
                                   
                                   
                                   <i class="ti-angle-down"></i>
                               </a>
                               <ul class="show-notification profile-notification">
<!--                                    <li> -->
<!--                                        <a href="#"> -->
<!--                                            <i class="ti-settings"></i> Settings -->
<!--                                        </a> -->
<!--                                    </li> -->
<!--                                    <li> -->
<!--                                        <a href="#"> -->
<!--                                            <i class="ti-user"></i> Profile -->
<!--                                        </a> -->
<!--                                    </li> -->
                                   <li>
                                       <a href="${context}/logout">
                                       <i class="ti-layout-sidebar-left"></i> Logout
                                   </a>
                                   </li>
                               </ul>
                           </li>
                       </ul>
                   </div>
               </div>
           </nav>