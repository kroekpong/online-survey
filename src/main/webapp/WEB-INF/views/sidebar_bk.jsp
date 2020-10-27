<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="pcoded-navbar" >
    <div class="sidebar_toggle"><a href="#"><i class="icon-close icons"></i></a></div>
    <div class="pcoded-inner-navbar main-menu">
        
        <ul class="pcoded-item pcoded-left-item">
        <div class="pcoded-navigatio-lavel text-left"  style="text-transform:none">
       <i class="ti-menu-alt"></i> <fmt:message key="menu.system" /> </div>
<!--                                 <div class="pcoded-navigatio-lavel" style="text-transform:none" > <i class="ti-panel"   ></i> Master Data </div> -->
        
          	<li>
                <a href="${context}/home">
                    <span class="pcoded-micon"><i class="ti-home"></i></span>
                    <span class="pcoded-mtext" ><fmt:message key="menu.home" /> </span> 
                    <span class="pcoded-mcaret"></span>
                </a>
            </li>
             
             
            <li class="pcoded-hasmenu">
                <a href="javascript:void(0)">
                    <span class="pcoded-micon"><i class="ti-panel"></i></span>
                    <span class="pcoded-mtext"  ><fmt:message key="menu.main" /> </span>
                    <span class="pcoded-mcaret"></span>
                </a>
                <ul class="pcoded-submenu">
                     
                    <sec:authorize access="hasAnyRole('admin', 'AFS_OEM_ADMIN')" >
                    <li >
                        <a href="${context}/question/maintain">
                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                            <span class="pcoded-mtext"  > <fmt:message key="menu.main.question" /></span>
                        </a>
                    </li> 
                    
                    <li >
                        <a href="${context}/activity/maintain">
                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                            <span class="pcoded-mtext"  > <fmt:message key="menu.main.activity" /></span>
                        </a>
                    </li>
                    </sec:authorize>
                    
                    
                    <sec:authorize access="hasAnyRole('admin', 'AFS_CALL_CENTER' )" >
                    <li >
                        <a href="${context}/followup">
                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                            <span class="pcoded-mtext"  > <fmt:message key="menu.main.follow" /></span>
                        </a>
                    </li>
                    </sec:authorize>
                     
 
                </ul>  
                
            </li>
             
             
            <li class="pcoded-hasmenu">
                <a href="javascript:void(0)">
                    <span class="pcoded-micon"><i class="ti-bar-chart"></i></span>
                    <span class="pcoded-mtext"  > <fmt:message key="menu.report" /></span>
                    <span class="pcoded-mcaret"></span>
                </a>
                <ul class="pcoded-submenu">  
            	   <li >
                       <a href="${context}/report/tracking">
                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                           <span class="pcoded-mtext"  ><fmt:message key="menu.report.tracking" /></span>
                       </a>
                   </li> 
                   
            	   <li >
                       <a href="${context}/report/scoresheet">
                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                           <span class="pcoded-mtext"  ><fmt:message key="menu.report.scoresheet" /></span>
                       </a>
                   </li> 
            	   
            	   <li >
                       <a href="${context}/report/analysis">
                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                           <span class="pcoded-mtext"  ><fmt:message key="menu.report.analysis" /></span>
                       </a>
                   </li> 
                   
            	  <%--  <li >
                       <a href="${context}/report/workshop">
                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                           <span class="pcoded-mtext"  ><fmt:message key="menu.report.workshop" /></span>
                       </a>
                   </li> --%> 
             
            	   <li >
                       <a href="${context}/report/sms">
                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                           <span class="pcoded-mtext"  ><fmt:message key="menu.report.sms" /></span>
                       </a>
                   </li> 
                   
            	   <li >
                       <a href="${context}/report/trending">
                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                           <span class="pcoded-mtext"  ><fmt:message key="menu.report.trending" /></span>
                       </a>
                   </li> 
                   
            	  <%--  <li >
                       <a href="${context}/report/manage">
                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                           <span class="pcoded-mtext"  ><i class="ti-package"></i>&nbsp;&nbsp;Workshop Pre-print report </span>
                       </a>
                   </li>  --%>
            	   
             
               </ul>  
                
            </li>
            
            <%-- 	<sec:authorize access="hasAnyRole('ADMIN', 'admin')" >
            <li class="pcoded-hasmenu">
                <a href="javascript:void(0)">
                    <span class="pcoded-micon"><i class="ti-server"></i></span>
                    <span class="pcoded-mtext"  >Master Data</span>
                    <span class="pcoded-mcaret"></span>
                </a>
                <ul class="pcoded-submenu">
                     
                    <li >
                        <a href="${context}/product/manage">
                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                            <span class="pcoded-mtext"  ><i class="ti-package"></i>&nbsp;&nbsp; Tiles & Product </span>
                        </a>
                    </li> 
                      <li >
                        <a href="${context}/location">
                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                            <span class="pcoded-mtext"  ><i class="ti-map-alt"></i>&nbsp;&nbsp; Location</span>
                        </a>
                    </li>
                     
                    <sec:authorize access="hasAnyRole('ADMIN')" >
                    <li >
                        <a href="${context}/user">
                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                            <span class="pcoded-mtext"  ><i class="ti-user"></i>&nbsp;&nbsp; User </span>
                        </a>
                    </li>
                    <li >
                        <a href="${context}/user/rolemap">
                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
                            <span class="pcoded-mtext"  ><i class="ti-medall"></i>&nbsp;&nbsp; Role & Function </span>
                        </a>
                    </li>
                    </sec:authorize>
 
                </ul>  
                
            </li>
            </sec:authorize> --%>
             
      
             
        </ul>
        

    </div>
</nav>

<script>
$('.main-menu ul li').find('a').each(function () {
	var href = $(this).attr('href');
//     var c1 = href+'!@#';
//     var c2 = location.href+'!@#';
    var c1 = href ;
    var c2 = location.href ;
    
    if (c2.indexOf(c1)>=0) {
           $(this).parents().addClass("active pcoded-trigger");
           $(this).addClass("active"); 
    }
});
</script>


<%-- <%@ include file="tlm/modal/offline_modal.jsp" %> --%>
