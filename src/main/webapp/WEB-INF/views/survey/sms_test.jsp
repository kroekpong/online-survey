<%@ page isELIgnored = "false" %>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="main-body">
        <div class="page-wrapper pop-up-wrapper">
            <!-- Page-header start -->
            <div class="page-header card">
                <div class="card-block">
                    <h5 class="m-b-10">Test SMS Sender</h5>
                    
                    <ul class="breadcrumb-title b-t-default p-t-10">
                        <li class="breadcrumb-item"><a href="?lang=en">EN</a>
                        </li>
                        <li class="breadcrumb-item"><a href="?lang=th">TH</a>
                        </li>
                    </ul>
                    
                </div>
            </div>
            <!-- Page-header end -->
            
            <!-- Page body start -->
            <div class="page-body">
                <div class="row">
                    <div class="col-sm-12">
                        <!-- Register your self card start -->
                        <div class="card">
                            <div class="card-header">
                                <h5>Information</h5>
                            </div>
                            <div class="card-block">
                                 <div class="form-group row">
		                             <label class="col-sm-2 col-form-label" >Mobile No.</label>
		                             <div class="col-md-2"> 
			                                 <input type="text" class="form-control" id="tel" name="tel" value="0983058707">
			                            </div>
		                             <label class="col-sm-1 col-form-label" >SID </label>
		                             <div class="col-md-2"> 
			                                 <input type="text" class="form-control" id="sid" name="sid" value="SIDXXXXXXXX">
			                            </div>
		                             <div class="col-md-2"> 
		                             <button onclick="sendSMS()" type="button" class="btn btn-info"  >
					                    <i class="fa fa-check-square-o"></i> Send </button>
						      
			                            </div>
		                     </div>
		                      
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Page body end -->
        </div>
    </div>
    
    <<script type="text/javascript">
 
function sendSMS(){ 
	
	swal({
		title: "Confirm Send SMS ?",
		icon: "warning",
		buttons : true
	}).then((isok) => {
		if (isok) {
// 			saveRelease();

// 			var data = $('#addForm').serialize();
					
			$.ajax({
	            type: 'POST',
				data: { tel :  $('#tel').val() ,sid :  $('#sid').val()  } ,
	            url: cPath+'/sms/send',
	            success: function(data) {
					if(data == "99"){
						swal( "", _errorTxt ,"warning");
					}else if(data == "0"){
						notify(_successSaveTxt, 'success');
					}
	            },error : function(data) {
	            	swal( "", _errorTxt ,"warning");
	            }
	        });
			
			
		}
	});	
}
 
</script>
    