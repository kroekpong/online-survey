
<%@ include file="/WEB-INF/views/header.jsp" %>



  <body>
	  <div class="fixed-button">
	  </div>
       <!-- Pre-loader start -->
    <div class="theme-loader">
        <div class="loader-track">
            <div class="loader-bar"></div>
        </div>
    </div>
    <!-- Pre-loader end -->
    <div id="pcoded" class="pcoded">
        <div class="pcoded-overlay-box"></div>
        <div class="pcoded-container navbar-wrapper">

            <%@ include file="/WEB-INF/views/navbar.jsp" %>
            
           <div class="pcoded-main-container">
                <div class="pcoded-wrapper">
                
     			<%@ include file="/WEB-INF/views/sidebar.jsp" %>
                    
                    <div class="pcoded-content">
                        <div class="pcoded-inner-content">
                            <div class="main-body">
                                <div class="page-wrapper">
                                
                                <div class="page-header card">
                                        <div class="card-block">
                                            <h5 class="m-b-3"><fmt:message key="followup.title.info" /></h5>
                                        </div>
                                           <div class="card-block">
                                                            <h4 class="sub-title"><fmt:message key="lbCsearch" /></h4>
                                                 <form class="form-horizontal" id="searchForm"  method="post" action="car_tracking/export"> 
                                                              
                                                          	 
					                                        <div class="form-group row">
					                                        
					                                            <label class="col-sm-2 col-form-label" ><fmt:message key="activity.name" /></label>
					                                            <div class="col-sm-4">
					                                                <input class="form-control" value="${activityBean.activityName}" readonly >
					                                            </div>
					                                            <label class="col-sm-2 col-form-label" ><fmt:message key="lb0006" /></label>
					                                            <div class="col-sm-4">
					                                                <input class="form-control" value="${activityBean.description}" readonly>
					                                            </div>
					                                             
					                                        </div>
					                                        <div class="form-group row">
<%-- 					                                        ${activityBean} --%>
					                                            <label class="col-sm-2 col-form-label" ><fmt:message key="expired.date" /></label>
					                                            <div class="col-sm-4">
					                                                <input class="form-control"  value="${activityBean.expireDate}" readonly>
					                                            </div>
					                                            
					                                            <label class="col-sm-2 col-form-label" ><fmt:message key="lbStatus" /></label>
					                                            <div class="col-sm-4">
<!-- 					                                                  <select  class="form-control" name="status" required>  -->
<!-- 								 										<option value="">All</option> -->
<!-- 								 										<option value="1">Complete</option> -->
<!-- 								                                    	<option value="0">Pending</option>                              -->
<!-- 																	</select>	 -->
																		  <select  class="form-control"   name="status" >
																		  		<option value="">${_ALL}</option> 
																				<c:forEach var="item" items="${DDL_SURVEY_STATUS}">
																						<option  value="${item.code}" >${item.name}</option>
																				</c:forEach>     
																		 </select>	
								 
					                                            </div>
					                                             
					                                        </div>
					                                      
                                                       	 
														<div class="form-group text-center">
														
															<button type="reset" class="btn btn-inverse  mx-3" id="cancel-btn">
																<i class="fa  fa-reply "></i>Back
															</button>
															<button type="button" class="btn btn-mat btn-primary"  id="search-btn">
																<i class="fa fa-search"></i><fmt:message key="lbSearch" />
															</button>
															<!-- <button type="button" class="btn btn-mat btn-warning mx-3"  id="dw-btn">
																<i class="fa fa-download"></i>Download
															</button> -->
														</div>
														
														
   												</form>  
										</div>
										
										
                                                                    
                                                    
                                    </div>
                                

                                    <div class="page-body">
                                     
                                               <div class="card tb-card">
			                                        <div class="card-block table-border-style">
			                                            <div class="table-responsive">
			                                               <table id="roTable" class="table table-hover table-striped">
											                           <thead >
											                               <tr >
														                    <th class="text-center">#</th>
														                    <th class="text-center">Customer Name</th>
														                    <th class="text-center">Telephone No.</th>
														                    <th class="text-center">RO Finish Date</th>
														                    <th class="text-center">Dealer Name</th>
														                    <th class="text-center">Status</th>
														                    <th class="text-center">Action</th>
														                </tr>
											                           </thead>
											                       
											                   </table>
			                                            </div>
			                                        </div>
			                                    </div>
                                           
                                    </div>

                                    <!-- <div id="styleSelector"> -->

                                    <!-- </div> -->

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        

		
<div class="modal fade" id="modalMain"  ></div>

 <%@ include file="/WEB-INF/views/survey/terms_modal_cc.jsp" %>
  									
 		<script type="text/javascript">
 		

 		$(function() {

 			$('#modalMain').on('hidden.bs.modal', function () {
 				 $("#modalMain").html("");
 			});
 			 
 		});
 		
 		function voteStatus(s) {
 			  if(s==0){
 				  return '<span class="label label-warning"><fmt:message key="status.Pending" /></span>';
 			  }else if(s==1){
 				  return '<span class="label label-danger"><fmt:message key="status.Close" /></span>';
 			  }else if(s==2){
 				  return '<span class="label label-success"><fmt:message key="status.Completed" /></span>';
 			  }
 			  return s;
 		}

 		function _colActionAct(sid) {
 			var  s = 				
 				'<button type="button" class="btn btn-table btn-outline-info" onclick="doSurvey(\''+sid+'\')"><i class="fa  fa-paper-plane-o "></i>Survey</button>' 
 			  +'<button type="button" class="btn btn-table btn-outline-warning" onclick="doTrack(\''+sid+'\')"><i class="fa fa-support"></i>Track</button>' 
//  			  +'<button type="button" class="btn btn-table btn-outline-danger" onclick="doClose(\''+id+'\')"><i class="fa fa-close"></i>Close</button>' ;
 				return s
 		}

 		var roTable = $('#roTable').DataTable({
 		 
 			 lengthChange: false,
 			 pageLength: 100,
 		     "columns": [
 		        { "data": "sid"
 		        	,"render": function ( data, type, row, meta ) {
 		         		return meta.row + meta.settings._iDisplayStart + 1;
 		        	}
 		        },
 		        { "data": "customer"},
 		        { "data": "tel"},
//  		        { "data": "ro_no"},
 		        { "data": "settleDate"},
 		        { "data": "dealer_name"},
//  		        { "data": "channel"},
// 		         { "data": "release_flg"}
 			    { "data": "complete_flg"
 					,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
 				           $(nTd).html(voteStatus(sData));
 				       } 
 				 },
 				 { "data": "sid"  
		            	,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
		            		var  txt = "";
		            		if(oData['complete_flg']==0){
		            		 	txt = _colActionAct(sData);
		            		}
				            $(nTd).html(txt);
				        } 
		           }
 		     ]
 			 ,"aoColumnDefs": [
 			      { "sClass": "text-center", "aTargets": [0,3,5] }
 			    ],
 			    
 		 });
 		 
 			function resetR(){
 				roTable.clear().draw();
 				$('#btnRelease').hide();
 				
 			}
 			
// 		 	var _uids = {};
 			
 			function searchRo(){
 			 
 				_loader();
 				$.ajax({
 			         url: cPath+"/followup/getInfoDataTable?id=${activityId}",
		 	         data: $('#searchForm').serialize()
 			     }).done(function (result) {
 			    	 roTable.clear().draw();
 			         if(result.recordsTotal>0){
 			        	 
 			        	 roTable.rows.add(result.data).draw();
 			        	 
 			         }else{
 			         }
 			         
 			         $('#totalTxt').html(_totalTxt + result.recordsTotal);
 			         
 			        _unloader();
 			     });
 				
 			};
 			
 			function doTrack(sid){
 		 		
		 	    var url = cPath + "/followup/track/"+sid ;
		 	    var modal = $("#modalMain");
  		 	 	modal.load(url, function() {
  		 		modal.modal({
		 	            backdrop : 'static',
		 	            keyboard : false
		 	        });
		 			modal.modal("show");
		 	    });
		 		
		 	}
 			 
 			var _sid;
 			function doSurvey(sid){
 			
// 			    var url = cPath + "/ccvote/"+sid;
//  		 	    var modals = $("#modalMain");
// 	  		 	 	modals.load(url, function() {
// 	  		 	 	modals.modal({
//  		 	            backdrop : 'static',
//  		 	            keyboard : false
//  		 	        });
//  		 			modals.modal("show");
//  		 	    });
	  		 	 _sid = sid;
	  		 	 showTC();
 			}
 		
 			function showSurvey(){
 	 			
			    var url = cPath + "/ccvote/"+_sid;
 		 	    var modals = $("#modalMain");
	  		 	 	modals.load(url, function() {
	  		 	 	modals.modal({
 		 	            backdrop : 'static',
 		 	            keyboard : false
 		 	        });
 		 			modals.modal("show");
 		 	    });
 			}
 		
 			
			function showTC(){
				 var TC_SHOW_CC  = '${TC_SHOW_CC}';
		         if('Y' == TC_SHOW_CC ){
		        	 resetTC();
		           $('#termsModal').modal('show');  // TC
		         }else{
		        	 showSurvey();
		         }
		 	}
			 

			function modalHide(){
			    $("#modalMain").modal("hide");
			}
		
		  
 		 	
 			$("#search-btn").on("click", function(e) {
 				searchRo();
 			 });
 			
 			$("#cancel-btn").on("click", function(e) {
 				location = cPath + "/followup";
 			 });
 			searchRo();
 			
 			
 		</script>
 		

</body>

</html>
