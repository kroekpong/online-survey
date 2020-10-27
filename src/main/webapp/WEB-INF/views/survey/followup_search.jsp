
<%@ include file="/WEB-INF/views/header.jsp" %>


<style>
.blink {
  animation: blinker 1s linear infinite;
}

@keyframes blinker {
  50% {
    opacity: 0;
  }
}
</style>

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
                                            <h5 class="m-b-3"><fmt:message key="followup.title" /></h5>
                                        </div>
                                           <div class="card-block">
                                                            <h4 class="sub-title"><fmt:message key="lbCsearch" /></h4>
                                                 <form class="form-horizontal" id="searchForm"  method="post" action="car_tracking/export"> 
                                                              
                                                          	 
					                                        <div class="form-group row">
					                                        
					                                            <label class="col-sm-2 col-form-label" ><fmt:message key="activity.name" /></label>
					                                            <div class="col-sm-4">
					                                                <input class="form-control" name="activityName"  >
					                                            </div>
					                                            <label class="col-sm-2 col-form-label" ><fmt:message key="lb0006" /></label>
					                                            <div class="col-sm-4">
					                                                <input class="form-control" name="description">
					                                            </div>
					                                             
					                                        </div>
					                                      
                                                       	 
														<div class="form-group text-center">
														
															<!-- <button type="reset" class="btn btn-inverse ">
																<i class="fa fa-refresh "></i>Clear
															</button> -->
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
			                                                <table id="rstable" class="table table-hover">
			                                                    <thead>
			                                                        <tr class="active">
								                                        <th class="text-center">Activity Name</th>
								                                        <th class="text-center">Description</th>
								                                        <th class="text-center">Status</th>
								                                        <th class="text-center"><fmt:message key="expired.date" /></th>
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
        

		
<div class="modal fade" id="modalAdd"   aria-hidden="true"  data-backdrop="false" ></div>
 
 		<script type="text/javascript">
 		
 		function preStatus(s,f) {
 			  var s = (s==1)? _Yes : _No;
 			  
 			  if( (f==1)){
 				  s += ' <span class="label label-info">'+_Default+'</span>';
 			  }
 			  return s;
 		}
 		

 		function _colActionAct(id) {
 			var  s = 				
 				'<button type="button" class="btn btn-table btn-outline-info" onclick="doView('+id+')"><i class="fa fa-eye"></i>View</button>' 
 			  +'<button type="button" class="btn btn-table btn-outline-warning" onclick="doEdit('+id+')"><i class="fa fa-edit "></i>Edit</button>' 
 			  +'<button type="button" class="btn btn-table btn-outline-danger" onclick="doClose(\''+id+'\')"><i class="fa fa-close"></i>Close</button>' ;
 				return s
 		}
 		
 		
 		   var rsTable = $('#rstable').DataTable({
 			 
 		        "columns": [
 		           { "data": "activityName"},
 		           { "data": "description"},
//  		           { "data": "releaseDate"},
 		          { "data": "id"
 		        	 ,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
				            $(nTd).html( oData['completeNo'] + " / "+ oData['totalNo']);
				        } 
 		          },
 		           { "data": "expireDate"
 		        	  ,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
 		        		 var txt = sData;
 		        		if(oData['expiredFlg']=="1"){
 		        			txt = ' <span class="text-danger">'+sData+'</span>';
 		        		}else if(oData['expiredFlg']=="2"){
 		        			txt = ' <span class="text-warning blink">'+sData+'</span>';
	 		   			  }
				           $(nTd).html(txt);
 		        	  }
 		           },
 		            
 		            { "data": "id"  
 		            	,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
 		            		var txt = "";
 		            		if(oData['expiredFlg']!="1"){
 		            			txt = '<button type="button" class="btn btn-table btn-outline-info" onclick="doView('+sData+')"><i class="fa fa-eye"></i>View</button>'; 
 		            		}
				            $(nTd).html(txt);
				        } 
 		            }
 		        ]
		  			,"aoColumnDefs": [
	    		      { "sClass": "text-center", "aTargets": [2,3,4] }
	    		    ],
 		    });
 		    

//  		    $('#rstable tr').dblclick(function() {
// 	 		    alert('Row dblclicked');
// 	 		        alert($(this).attr('class'));
// 	 	    });
 		    
 		    
 		   
 			function doSearch(){
 				_loader();
 				$.ajax({
 		            url: cPath+"/followup/getDataTable",
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
//  						dangerMode : true,
//  				}).then((isok) => {
//  					if (isok) {
//  						$.ajax({
// 							type: 'POST',
// 							url: cPath+"/followup/delete/"+id,
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
  		 	    var url = cPath + "/followup/editActivity?id="+id ;
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
 		 		
 		 		location= cPath + "/followup/info/"+id ;
 		 		
 		 	}
 		 	
 		 	
 		 	function showAdd(){
 		 	    var url = cPath + "/followup/addActivity";
 		 	    var modal = $("#modalAdd");
 		 	  		modal.load(url, function() {
 		 			modal.modal({
 		 	            backdrop : 'static',
 		 	            keyboard : false
 		 	        });
 		 			modal.modal("show");
 		 	    });
 		 	}
 		 	
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
