
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
                                            <h5 class="m-b-3">Report Tracking</h5>
                                        </div>
                                           <div class="card-block">
                                                            <h4 class="sub-title"><fmt:message key="lbCsearch" /> </h4>
                                                 <form class="form-horizontal" id="searchForm"  method="post" action="car_tracking/export"> 
                                                              
                                                          	 
					                                        <div class="form-group row">
					                                        
					                                            <label class="col-sm-2 col-form-label" >Activity Name</label>
					                                            <div class="col-sm-4">
					                                                <input class="form-control" name="activityName" autocomplete="off" >
					                                            </div>
					                                            <label class="col-sm-2 col-form-label" >Description</label>
					                                            <div class="col-sm-4">
					                                                <input class="form-control" name="activityDesc" autocomplete="off">
					                                            </div>
					                                        </div>
					                                        
					                                          <div class="form-group row">
					                                        
					                                            <label class="col-sm-2 col-form-label" >Questionnaire Set</label>
					                                            <div class="col-sm-4">
					                                                <input class="form-control" name="questionName"  >
					                                            </div>
					                                            <label class="col-sm-2 col-form-label" >Description</label>
					                                            <div class="col-sm-4">
					                                                <input class="form-control" name="questionDesc">
					                                            </div>
  					                                         
					                                        </div>
					                                        
					                                        <div class="form-group row">
								                           <label class="col-sm-2 col-form-label">Release Date</label>
															<div class="col-sm-2">
																 <div class="input-group date">
																	<input type="text" class="form-control" id="releaseFrom" autocomplete="off" name="releaseFrom" required=""> <span class="input-group-addon"> 
																		<span class="ti-calendar"></span>
																	</span>
																</div>														
															</div>
															<div class="col-sm-2">
																 <div class="input-group date">
																	<input type="text" class="form-control" id="releaseTo" autocomplete="off" name="releaseTo" required=""> <span class="input-group-addon"> 
																		<span class="ti-calendar"></span>
																	</span>
																</div>												 
															</div>		 
								                             
															<label class="col-sm-2 col-form-label">Expire Date</label>
															<div class="col-sm-2">
																 <div class="input-group date">
																	<input type="text" class="form-control" id="expFrom" autocomplete="off" name="expFrom" required=""> <span class="input-group-addon"> 
																		<span class="ti-calendar"></span>
																	</span>
																</div>														
															</div>
															<div class="col-sm-2">
																 <div class="input-group date">
																	<input type="text" class="form-control" id="expTo" autocomplete="off" name="expTo" required=""> <span class="input-group-addon"> 
																		<span class="ti-calendar"></span>
																	</span>
																</div>												 
															</div>		 
								                             
								                        </div>
								                        
					                                      
					                                      
                                                       	 
														<div class="form-group text-center">
														
															 
															<button type="button" class="btn btn-mat btn-primary"  id="search-btn">
																<i class="fa fa-search"></i>Search
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
			                                                <table id="rstable" class="table table-hover">
			                                                    <thead>
			                                                        <tr class="active">
								                                        <th class="text-center">Activity Name</th>
								                                        <th class="text-center">Description</th>
								                                        <th class="text-center">Questionnare Set</th>
								                                        <th class="text-center">Release Date</th>
								                                        <th class="text-center">Expire Date</th>
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
        
 

  <script src="https://cdn.jsdelivr.net/npm/chart.js@2.7.3/dist/Chart.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>


<div class="modal fade" id="modalAdd"   aria-hidden="true"  data-backdrop="false" ></div>
 


 		<script type="text/javascript">
 		
 		

 		$(function() { 
 			$('#releaseFrom,#releaseTo,#expFrom,#expTo' ).datepicker({
 				format : "yyyy-mm-dd",
 				autoclose:true,
 				enableOnReadonly:true
 			}); 
 			
 			
 		});

 		function _colActionAct(id, oData) {
 			var  s = '<button type="button" class="btn btn-table btn-outline-info" onclick="doView('+id+')"><i class="fa fa-dashboard"></i>Dashboard</button>' 
 				if(oData['closeFlg']=="1"){
 					s += '<button type="button" class="btn btn-table btn-outline-warning" onclick="doEdit('+id+')"><i class="fa fa-refresh"></i>Release</button>' ;
 				}
 				return s
 		}
 		
 		
 		   var rsTable = $('#rstable').DataTable({
 			 
 		        "columns": [
 		           { "data": "activityName"},
 		           { "data": "description"},
 		           { "data": "setDescription"},
 		           { "data": "releaseDate"},
 		           { "data": "expireDate"},
	 		         
//  		          { "data": "pre_flg"
//  		        	 ,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
// 				            $(nTd).html(preStatus(oData['pre_flg'],oData['default_flg']));
// 				        } 
//  		          },
 		            
 		            { "data": "id"  
 		            	,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
 		            		var  txt = _colActionAct(sData,oData);
				            $(nTd).html(txt);
				        } 
 		            }
 		        ]
		  			,"aoColumnDefs": [
	    		      { "sClass": "text-center", "aTargets": [3,4] }
	    		    ],
 		    });
 		    

//  		    $('#rstable tr').dblclick(function() {
// 	 		    alert('Row dblclicked');
// 	 		        alert($(this).attr('class'));
// 	 	    });
 		    
 		    
 		   
 			function doSearch(){
 				_loader();
 				$.ajax({
 		            url: cPath+"/report/getTracking",
 		            data: $('#searchForm').serialize()
 		        }).done(function (result) {
 		            rsTable.clear().draw();
 		            if(result.recordsTotal>0){
 			            rsTable.rows.add(result.data).draw();
 			            
 		            }else{
 		            }
 		           _unloader();
 	            });
 				
 		 	};
 		 	
 		 	/* $(document).on("dblclick", "#rstable tr", function (e) {
				var data = rsTable.row( this ).data();
				if(data){
					doEdit(data['id']);
				}
 		 	}); */
 		  
 		 	
//  		 	function doClose(id){

//  				swal({
//  						title: _confirmCloseTxt,
//  						icon: "warning",
//  						buttons : true
// //  						dangerMode : true,
//  				}).then((isok) => {
//  					if (isok) {
//  						$.ajax({
// 							type: 'POST',
// 							url: cPath+"/activity/delete/"+id,
// 							success: function(data) {
// 								notify('Delete Success !', 'success');
// 		 						doSearch();
// 							},
// 							error: function(e) {
// 								console.log(e.responseText);
// 		 						swal("Error !", e.responseText, {
// 		 							icon : "error"
// 		 						});
// 							}
//  						});
//  					}
//  				});	

//  			 }
 		 	
 		 	function doEdit(id){
 		 		
//  		 		console.log(" id = "+id);
  		 	    var url = cPath + "/activity/editActivity?id="+id ;
  		 	    var modal = $("#modalAdd");
	  		 	 	modal.load(url, function() {
	  		 		modal.modal({
  		 	            backdrop : 'static',
  		 	            keyboard : false
  		 	        });
  		 			modal.modal("show");
  		 	    });
 		 		
 		 		
 		 	}
 		 	
 			
 		 	function doView(id){
//  		 		console.log(" id = "+id);
  		 	    var url = cPath + "/report/tracking_dashboard?id="+id ;
  		 	    var modal = $("#modalAdd");
	  		 	 	modal.load(url, function() {
	  		 		modal.modal({
  		 	            backdrop : 'static',
  		 	            keyboard : false
  		 	        });
  		 			modal.modal("show");
  		 	    });
 		 		
 		 		
 		 	}
 		 	
 		 	
//  		 	function showAdd(){
//  		 	    var url = cPath + "/activity/addActivity";
//  		 	    var modal = $("#modalAdd");
//  		 	  		modal.load(url, function() {
//  		 			modal.modal({
//  		 	            backdrop : 'static',
//  		 	            keyboard : false
//  		 	        });
//  		 			modal.modal("show");
//  		 	    });
//  		 	}
 		 	
 		 	function addHide(){
 		 	    $("#modalAdd").modal("hide");
 		 	    $("#modalAdd").html("");
 		 	}
 		 	
 			$("#search-btn").on("click", function(e) {
 				doSearch();
 			 });
 		    
 			$("#add-btn").on("click", function(e) {
 				showAdd();
 			 });
 		 
//  			$("#dw-btn").on("click", function(e) {
//  				 $('#searchForm').submit();
//  			 });
 		 
    
//  			doSearch();
 		
 		
 		
 		
 		
 		
 		</script>
 		

</body>

</html>
