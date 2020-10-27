<%@ page isELIgnored="false" %>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	
<style>
<!--
.input-group {
    margin-bottom: 0;
}

#totalTxt{
	font-size: 14px;
}
-->
</style>
<div class="modal-dialog modal-lg"  style="max-width:80em  !important;"  role="document">
		
    <div class="modal-content">
        <div class="modal-header">
				<h5 class="modal-title"><i class="fa fa-support"></i> Follow up log</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			
        <div class="modal-body">
            <form  id="logForm"  class="form-horizontal" data-toggle="validator" role="form" >
                <div class="portlet box blue">
                 
                    <div class="portlet-body current">
                    
                        <div class="form-group row">
                            <label class="col-md-2 col-form-label">Activity Name</label> 
                            <div class="col-md-4"> 
                                 <input type="text" class="form-control" disabled value="${activityBean.activityName}" >
                            
                            
                            </div>
                            <label class="col-md-2 col-form-label">Description</label>
                            <div class="col-md-4"> 
                                 <input type="text" class="form-control"  disabled value="${activityBean.description}" >
                            </div>
                             
                        </div> 
                        <div class="form-group row">
                            <label class="col-md-2 col-form-label">Customer Name</label> 
                            <div class="col-md-4"> 
                                 <input type="text" class="form-control" disabled value="${activityBean.customer}" >
                            </div>
                            <label class="col-md-2 col-form-label">Telephone No.</label>
                            <div class="col-md-4"> 
                                 <input type="text" class="form-control"  disabled value="${activityBean.tel}" >
                            </div>
                             
                        </div>
                         
                        <hr> 
                         <input type="hidden" name="sid"  value="${activityBean.sid}"  >
                        
                         <div class="form-group row">
                            <label class="col-md-2 col-form-label">Contact Status</label> 
                            <div class="col-md-4"> 
                            
                             <select  class="form-control"  id="status" name="status" > 
										<c:forEach var="item" items="${DDL_CONTACT_STATUS}">
												<option   value="${item.code}" >${item.name}</option>
										</c:forEach>     
								 </select>	
								 
								 
                            </div>
                            <label class="col-md-2 col-form-label freason"   >Reason</label>
                            <div class="col-md-4"> 
                             <select  class="form-control freason"  id="reason" name="reason" > 
										<c:forEach var="item" items="${DDL_REASON}">
												<option  value="${item.code}" >${item.name}</option>
										</c:forEach>     
								 </select>	
								 
                            </div>
                            
                             
                        </div>
                        
                         
                    </div>
                    
                    
                </div>
                
               
                

            </form>
            
               <div class="tb-card">
             <div class="table-responsive ">
                       <table id="logTable" class="table table-hover table-striped">
                           <thead >
                               <tr class="">
			                    <th class="text-center">#</th>
			                    <th class="text-center">Reason</th>
			                    <th class="text-center">Date</th>
			                </tr>
                           </thead>
                       		<tbody>
                       		<c:forEach var="item" items="${logList}" varStatus="row">
                       		
                       		 <tr class="">
			                    <td class="text-center">${row.index+1}</td>
			                    <td class="">${item.reason}</td>
			                    <td class="text-center">${item.createDate}</td>
			                </tr>
			                
                       		</c:forEach>
                       		
                       		</tbody>
                   </table>
               </div>
               </div>

            
            
            
            
            
            
            
            
            
            
            
            
        </div>
        <div class="modal-footer">
            <button  onclick="modalHide()" type="button" class="btn btn-inverse"  ><i class="fa fa-close"></i> Cancel</button>&nbsp;&nbsp;
            <button onclick="comfirmSave()" type="submit" class="btn btn-info"  ><i class="fa fa-save"></i> Confirm</button>
        </div>
    </div>
</div>



  
<script type="text/javascript">

$(function() {
	
	
	$('#status').change(function() {
		if ($(this).val()==1) {
			$(".freason").hide();
		}else{
			$(".freason").show();
		}
 });
 
	
});


function comfirmSave(){ 
 
	swal({
		title: _confirmSaveTxt,
		icon: "warning",
		buttons : true
	}).then((isok) => {
		if (isok) {
			saveLog();
		}
	});	
}
	
	
function saveLog(){ 
	
	var data = $('#logForm').serialize();
			
	$.ajax({
        type: 'POST',
		data: data ,
        url: cPath+'/followup/saveLog',
        success: function(data) {
			if(data == "99"){
				swal( "", _errorTxt ,"warning");
			}else if(data == "0"){
				 
				$('#modalMain').modal('hide');
				notify(_successSaveTxt, 'success');
				searchRo();
			}
        }
    });
}

// $('#logTable').DataTable({
// });

</script>



 