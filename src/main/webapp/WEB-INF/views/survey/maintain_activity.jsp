
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
                                            <h5 class="m-b-3">Maintain Activity & Release</h5>
                                        </div>
                                           <div class="card-block">
                                                            <h4 class="sub-title"><fmt:message key="lbCsearch" /></h4>
                                                 <form class="form-horizontal" id="searchForm"  method="post" action="car_tracking/export"> 
                                                              
                                                          	 
					                                        <div class="form-group row">
					                                        
					                                            <label class="col-sm-2 col-form-label" >Activity Name</label>
					                                            <div class="col-sm-4">
					                                                <input class="form-control" name="name"  >
					                                            </div>
					                                            <label class="col-sm-2 col-form-label" >Description</label>
					                                            <div class="col-sm-4">
					                                                <input class="form-control" name="desc">
					                                            </div>
					                                        </div>
					                                        
					                                        
					                                        <div class="form-group row">
					                                        
<!-- 					                                            <label class="col-sm-2 col-form-label" >Channel</label> -->
<!-- 					                                            <div class="col-sm-4"> -->
<!-- 					                                                <input class="form-control" name="channel"  > -->
<!-- 					                                            </div> -->
<!-- 					                                            <label class="col-sm-2 col-form-label" >Description</label> -->
<!-- 					                                            <div class="col-sm-4"> -->
<!-- 					                                                <input class="form-control" name="desc"> -->
<!-- 					                                            </div> -->
  					                                         
					                                        </div>
					                                      
                                                       	 
														<div class="form-group text-center">
														
															<!-- <button type="reset" class="btn btn-inverse ">
																<i class="fa fa-refresh "></i>Clear
															</button> -->
															<button type="button" class="btn btn-mat btn-success mx-3 " id="add-btn">
																<i class="fa fa-plus"></i>Add
															</button> 
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
        
<!--     <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" rel="stylesheet" type="text/css" /> -->
<!--          <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" type="text/javascript"></script> -->
        
 

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
 		           { "data": "releaseDate"},
 		           { "data": "expireDate"},
	 		         
//  		          { "data": "pre_flg"
//  		        	 ,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
// 				            $(nTd).html(preStatus(oData['pre_flg'],oData['default_flg']));
// 				        } 
//  		          },
 		            
 		            { "data": "id"  
 		            	,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
 		            		var txt = "";
 		            		if(oData['closeFlg']=="1"){
 		            			txt = '<button type="button" class="btn btn-table btn-outline-info" onclick="doView('+sData+')"><i class="fa fa-eye"></i>View</button>'; 

 		            			txt += '<span class="pcoded-badge label label-danger"> CLOSE &nbsp; </span>';
 		            		}else{
 		            			txt = _colActionAct(sData);
 		            		}
				            $(nTd).html(txt);
				        } 
 		            }
 		        ]
		  			,"aoColumnDefs": [
	    		      { "sClass": "text-center", "aTargets": [2,3] }
	    		    ],
 		    });
 		    

//  		    $('#rstable tr').dblclick(function() {
// 	 		    alert('Row dblclicked');
// 	 		        alert($(this).attr('class'));
// 	 	    });
 		    
 		    
 		   
 			function doSearch(){
 				_loader();
 				$.ajax({
 		            url: cPath+"/activity/getDataTable",
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
 		  
 		 	
 		 	function doClose(id){

 				swal({
 						title: _confirmCloseTxt,
 						icon: "warning",
 						buttons : true
//  						dangerMode : true,
 				}).then((isok) => {
 					if (isok) {
 						$.ajax({
							type: 'POST',
							url: cPath+"/activity/delete/"+id,
							success: function(data) {
								notify('Delete Success !', 'success');
		 						doSearch();
							},
							error: function(e) {
								console.log(e.responseText);
		 						swal("Error !", e.responseText, {
		 							icon : "error"
		 						});
							}
 						});
 					}
 				});	

 			 }
 		 	
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
  		 	    var url = cPath + "/activity/view?id="+id ;
  		 	    var modal = $("#modalAdd");
	  		 	 	modal.load(url, function() {
	  		 		modal.modal({
  		 	            backdrop : 'static',
  		 	            keyboard : false
  		 	        });
  		 			modal.modal("show");
  		 	    });
 		 		
 		 		
 		 	}
 		 	
 		 	
 		 	function showAdd(){
 		 	    var url = cPath + "/activity/addActivity";
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
