<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/header.jsp" %>

<!--     <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css"> -->
 
  <body>
  
  <style>
  .project-task{
      min-height: 30em;
  }
<!--

-->
</style>
 
    <!-- Pre-loader end -->
    <div id="pcoded" class="pcoded">
        <div class="pcoded-overlay-box"></div>
        <div class="pcoded-container navbar-wrapper">

            <%@ include file="/WEB-INF/views/navbar.jsp" %>
            
           <div class="pcoded-main-container m-container">
                <div class="pcoded-wrapper">
                
     			<%@ include file="/WEB-INF/views/sidebar.jsp" %>
                    
                    <div class="pcoded-content">
                        <div class="pcoded-inner-content">
                            <div class="main-body">
                                <div class="page-wrapper">

                                    <div class="page-body">
                   
                                     
                          		 <div class="row">         
                          		 
                          	
                          			<div class="col-md-12 col-xl-4 ">
                                                <div class="card project-task">
                                                    <div class="card-header">
                                                        <div class="card-header-left text-c-blue">
                                                            <h4><i class="ti-panel p-r-5"></i> <fmt:message key="lbMainMenu" /></h4>
                                                        </div>
                                                    </div>
                                                    <div class="card-block p-b-10">
                                                        <div class="table-responsive">
                                                            <table class="table table-hover">
                                                                <tbody>
                                                                	  <sec:authorize access="hasAnyRole('admin', 'AFS_OEM_ADMIN')" >
                                                                <tr>
                                                                    <td>
                                                                           <a href="${context}/question/maintain"  title="<fmt:message key="menu.main.question" />" >
													                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
													                            <span class="pcoded-mtext"  > <fmt:message key="menu.main.question" /></span>
													                        </a>
                                                                    </td>
                                                                </tr> 
                                                                <tr>
                                                                    <td>
                                                                           <a href="${context}/activity/maintain"  title="<fmt:message key="menu.main.activity" />" >
													                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
													                            <span class="pcoded-mtext"  ><fmt:message key="menu.main.activity" /></span>
													                        </a>
                                                                    </td>
                                                                </tr> 
                                                                </sec:authorize>
                                                                 <sec:authorize access="hasAnyRole('admin', 'AFS_CALL_CENTER' )" >
                                                                <tr>
                                                                    <td>
                                                                           <a href="${context}/followup"  title="<fmt:message key="menu.main.follow" />" >
													                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
													                            <span class="pcoded-mtext"  > <fmt:message key="menu.main.follow" /></span>
													                        </a>
                                                                    </td>
                                                                </tr> 
                                                                 </sec:authorize>
                                                                 
                                                                 
                                                                </tbody>
                                                            </table>
                                                            
                                                            
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        
                                         
                                              <sec:authorize access="hasAnyRole('admin', 'AFS_CALL_CENTER' )" >
                          					<div class="col-md-12 col-xl-4 ">
                                                <div class="card project-task">
                                                    <div class="card-header">
                                                        <div class="card-header-left text-c-blue">
                                                            <h4><i class="ti-bar-chart  p-r-5"></i>  <fmt:message key="menu.report" /></h4>
                                                        </div>
                                                    </div>
                                                    <div class="card-block p-b-10">
                                                        <div class="table-responsive">
                                                            <table class="table table-hover">
                                                                <tbody>
                                                                <tr><td >
											                       <a href="${context}/report/tracking"  title="<fmt:message key="menu.report.tracking" />">
											                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
											                           <span class="pcoded-mtext"  ><fmt:message key="menu.report.tracking" /></span>
											                       </a>
											                   </tr></td> 
											                   
											            	   <tr><td >
											                       <a href="${context}/report/scoresheet"  title="<fmt:message key="menu.report.scoresheet" />">
											                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
											                           <span class="pcoded-mtext"  ><fmt:message key="menu.report.scoresheet" /></span>
											                       </a>
											                   </tr></td> 
											            	   
											            	   <tr><td >
											                       <a href="${context}/report/analysis"  title="<fmt:message key="menu.report.analysis" />">
											                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
											                           <span class="pcoded-mtext"  ><fmt:message key="menu.report.analysis" /></span>
											                       </a>
											                   </tr></td> 
											                   
											            	  <%--  <tr><td >
											                       <a href="${context}/report/workshop"  title="<fmt:message key="menu.report.workshop" />">
											                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
											                           <span class="pcoded-mtext"  ><fmt:message key="menu.report.workshop" /></span>
											                       </a>
											                   </tr></td> --%> 
											             
											            	   <tr><td >
											                       <a href="${context}/report/sms"  title="<fmt:message key="menu.report.sms" />">
											                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
											                           <span class="pcoded-mtext"  ><fmt:message key="menu.report.sms" /></span>
											                       </a>
											                   </tr></td> 
											                   
											            	   <tr><td >
											                       <a href="${context}/report/trending" title="<fmt:message key="menu.report.trending" />" >
											                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
											                           <span class="pcoded-mtext"  ><fmt:message key="menu.report.trending" /></span>
											                       </a>
											                   </tr></td> 
                                                                
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            </sec:authorize>
                                            
                                    	</div>
                                     
                                    
                                    
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
 

</body>

</html>


<!-- Morris Chart js -->
<%-- <script src="${context}/assets/js/raphael/raphael.min.js"></script> --%>
<%-- <script src="${context}/assets/js/morris.js/morris.js"></script> --%>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.bundle.js"></script> -->

<script>

// function lineChart(result) {
	 
//     var labels = result.map(function(e) {
// 	   return e.date;
// 	});
// 	var data = result.map(function(e) {
// 	   return e.pvalue;
// 	});
// 	var rpdata = result.map(function(e) {
// 	   return e.rpvalue;
// 	});

	 
	 
// 	var ctx = document.getElementById('lineChart');
// 	var myChart = new Chart(ctx, {
// 	    type: 'line',
// 	    data: {
// 	        labels: labels,
// 	        datasets: [{
// 	           label: 'Total Print Label',
// 	           data: data,
// 	           backgroundColor: 'rgba(0, 119, 204, 0.3)'
// 	        },{
// 	           label: 'Total Re-Print Label',
// 	           data: rpdata,
// 	           backgroundColor: 'gba(255, 159, 64, 0.2)'
// 	        }
	        
// 	        ]
// 	     },
// 	    options: {
// 	        scales: {
// 	            yAxes: [{
// 	                ticks: {
// 	                    beginAtZero: true
// 	                }
// 	            }]
// 	        }
// 	    }
// 	});
// }


 

// function getDataGraph(){

// 	 $.ajax({
//          url: cPath+"/home/getDataGraph",
//      }).done(function (result) {
         
//     	 lineChart(result);
    	
//      });
// };


// getDataGraph();	
	


</script>

<!-- Custom js -->
<%-- <script src="${context}/assets/pages/chart/morris/morris-custom-chart.js"></script> --%>
