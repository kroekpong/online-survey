
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

<%--             <%@ include file="/WEB-INF/views/navbar.jsp" %> --%>
            
           <div class="pcoded-main-container">
                <div class="pcoded-wrapper">
                
<%--      			<%@ include file="/WEB-INF/views/sidebar.jsp" %> --%>
                    
                    <div class="pcoded-content">
                        <div class="pcoded-inner-content">
                            <div class="main-body">
                                <div class="page-wrapper">
                                
                                <div class="page-header card">
                                        <div class="card-block">
                                            <h5 class="m-b-3"><fmt:message key="lb0001" /></h5>
                                        </div>
                                           <div class="card-block">
                                                            <h4 class="sub-title"><fmt:message key="lbCsearch" /></h4>
                                                 <form class="form-horizontal" id="searchForm"  method="post" action="car_tracking/export"> 
                                                              
                                                          	 
					                                        <div class="form-group row">
					                                        
					                                            <label class="col-sm-2 col-form-label" ><fmt:message key="lb0003" /></label>
					                                            <div class="col-sm-3">
					                                                <input class="form-control" name="name"  >
					                                            </div>
					                                            <label class="col-sm-2 col-form-label" ><fmt:message key="lb0004" /></label>
					                                            <div class="col-sm-3">
					                                                <input class="form-control" name="desc">
					                                            </div>
<!-- 					                                            <label class="col-md-1 col-form-label" ></label> -->
					                                            <div class="col-sm-2 col-form-label">
					                                                <div class="checkbox">
														                    <label>
														                      <input type="checkbox" value="1" name="pre_flg">
														                      <fmt:message key="lb0007" />
														                    </label>
														                  </div>
					                                            </div>
					                                        </div>
					                                      
                                                       	 
														<div class="form-group text-center">
														
															<!-- <button type="reset" class="btn btn-inverse ">
																<i class="fa fa-refresh "></i>Clear
															</button> -->
															<button type="button" class="btn btn-mat btn-success mx-3 " id="add-btn">
																<i class="fa fa-plus"></i><fmt:message key="lbAdd" />
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
			                                                <table id="rstable" class="table table-hover">
			                                                    <thead>
			                                                        <tr class="active">
								                                        <th class="text-center"><fmt:message key="lb0003" /></th>
								                                        <th class="text-center"><fmt:message key="lb0004" /></th>
								                                        <th class="text-center"><fmt:message key="lb0007" /></th>
								                                        <th class="text-center"><fmt:message key="lbAction" /></th>
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
 		
 		function _colActionQ(id,oData) {
 			var  s =  '<button type="button" class="btn btn-table btn-outline-info" onclick="viewQuestion('+id+')"><i class="fa fa-eye"></i>View</button>' ;
 			 if(oData['vote_flg']==0   ){ 
 				 s += '<button type="button" class="btn btn-table btn-outline-warning" onclick="doEdit('+id+')"><i class="fa fa-edit "></i>Edit</button>' ;
 				 if(oData['release_flg']==0){
 					s += '<button type="button" class="btn btn-table btn-outline-danger" onclick="doDelete(\''+id+'\')"><i class="ti-trash"></i>Delete</button>' ;
 				 }
 			}	
 			 return s
 		}

 		
 		
 		   var rsTable = $('#rstable').DataTable({
 			 
 		        "columns": [
 		           { "data": "name"},
 		           { "data": "description"},
	 		         
 		          { "data": "pre_flg"
 		        	 ,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
				            $(nTd).html(preStatus(oData['pre_flg'],oData['default_flg']));
				        } 
 		          },
 		            
 		            { "data": "id"  
 		            	,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
 		            		var txt = "";
//  		            		if(oData['vote_flg']==0  ){
//  		            			txt = _colActionQ(sData,oData['release_flg']);
 		            			txt = _colActionQ(sData,oData);
//  		            		}
				            $(nTd).html(txt);
				        } 
 		            }
 		        ]
		  			,"aoColumnDefs": [
	    		      { "sClass": "text-center", "aTargets": [2] }
	    		    ],
 		    });
 		    

//  		    $('#rstable tr').dblclick(function() {
// 	 		    alert('Row dblclicked');
// 	 		        alert($(this).attr('class'));
// 	 	    });
 		    
 		    
 		   
 			function doSearch(){
 				_loader();
 				$.ajax({
 		            url: cPath+"/question/getDataTable",
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
 		  
 		 	
 		 	function doDelete(id){

 				swal({
 						title: _confirmDelTxt,
 						icon: "warning",
 						buttons : true
//  						dangerMode : true,
 				}).then((isok) => {
 					if (isok) {
 						$.ajax({
							type: 'POST',
							url: cPath+"/question/delete/"+id,
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
 		 	
 		 	function doEdit(sid){
 		 		
//  		 		console.log(" sid = "+sid);
  		 	    var url = cPath + "/question/editQuestion?sid="+sid;
  		 	    var modal = $("#modalAdd");
	  		 	 	modal.load(url, function() {
	  		 		modal.modal({
  		 	            backdrop : 'static',
  		 	            keyboard : false
  		 	        });
  		 			modal.modal("show");
  		 	    });
 		 		
 		 		
 		 	}
 		 	
 		 	function viewQuestion(sid){
 		 		
//   		 	    var url = cPath + "/question/viewQuestion/"+sid;
//   		 	 	window.open(url,'_blank');

 		 	   var url = cPath + "/question/viewQuestion/"+sid;
 		 	    var modals = $("#modalAdd");
	  		 	 	modals.load(url, function() {
	  		 	 	modals.modal({
 		 	            backdrop : 'static',
 		 	            keyboard : false
 		 	        });
 		 			modals.modal("show");
 		 	    });
		 		
	  		 	 	
 		 	}
 		 	
 		 	function showAdd(){
 		 	    var url = cPath + "/question/addQuestion";
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
