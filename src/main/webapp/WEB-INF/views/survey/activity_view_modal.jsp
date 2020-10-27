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
				<h5 class="modal-title"><i class="ti-eye"></i>  View Release Activity</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			
        <div class="modal-body">
            <form  id="viewActivityForm"  class="form-horizontal" data-toggle="validator" role="form" >
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
                            <label class="col-md-2 col-form-label">Questionnaire Set</label>
                            <div class="col-md-4">
                           		 <select size="1" class="form-control"  id="setId"  name="setId" disabled> 
 										<c:forEach var="item" items="${DDL_QUESTION_SET}">
											<option title="${item.description}" ${item.id==activityBean.setId? 'selected':''}  >${item.name}</option>
										</c:forEach>                                
									</select>		
								 <input type="hidden" id="preFlg" name="preFlg"  >
									
                            </div>
                            
							<label class="col-sm-2 col-form-label">Period RO (From - To)</label>
							<div class="col-sm-2">
								 <div class="input-group date" >
									<input type='text' class="form-control" disabled value="${activityBean.roStartDate}"/> <span
										class="input-group-addon"> 
										<span class="ti-calendar"></span>
									</span>
								</div>														
							</div>
							<div class="col-sm-2">
								 <div class="input-group date" >
									<input type='text' class="form-control"  disabled value="${activityBean.roEndDate}"/> <span
										class="input-group-addon"> 
										<span class="ti-calendar"></span>
									</span>
								</div>												 
							</div>		 
                             
                        </div>
                        <div class="form-group row">
                            <label class="col-md-2 col-form-label">Repair Order Type</label>
                            <div class="col-md-4"> 
 									<select size="1" class="form-control "  multiple id="repairType" name="repairType" disabled > 
 										<c:forEach var="item" items="${DDL_REPAIR_TYPE}">
											<option  value="${item.id}"   >${item.nameEn}</option>
										</c:forEach>                                
									</select>	
                            </div>
                            <label class="col-md-2 col-form-label">Submit Channel</label>
                            <div class="col-md-4"> 
                                 <input type="text" class="form-control" disabled value="${activityBean.channelName}" >
                            </div>
                             
                        </div>
                        
                        <div class="form-group row">
                            <label class="col-md-2 col-form-label">Rule Setting</label>
                            <div class="col-md-4"> 
                            		<select size="1" class="form-control" name="rule" disabled> 
                            			<c:forEach var="item" items="${DDL_RULES}">
											<option  ${item.code==activityBean.rule? 'selected':''}  value="${item.code}" >${item.name}</option>
										</c:forEach>   
									</select>
                                  <%--  <select size="1" class="form-control" name="rule" disabled> 
 										<option value="1" ${activityBean.rule==1? 'selected':''} >Number of release records</option>
                                    	<option value="2" ${activityBean.rule==2? 'selected':''} >RO percentage per dealer</option>                             
									</select>	 --%>	
                            </div>
                            <label class="col-md-2 col-form-label">Number of release</label>
                            <div class="col-md-4"> 
                                    <input type="text" class="form-control"  disabled value="${activityBean.submitNo}" >
                            </div>
                             
                        </div>
                        
                        <div class="form-group row">
                            
                            <label class="col-md-2 col-form-label">Expire Date</label>
                            <div class="col-md-4"> 
	                            <div class="input-group date" >
										<input type='text' class="form-control"   disabled value="${activityBean.expireDate}"/> <span
											class="input-group-addon"> 
											<span class="ti-calendar"></span>
										</span>
								</div>	
                            </div>
                            
                            <label class="col-md-2 col-form-label">Release Date</label>
                            <div class="col-md-4"> 
	                            <div class="input-group date" >
										<input type='text' class="form-control"  disabled value="${activityBean.releaseDate}"/> <span
											class="input-group-addon"> 
											<span class="ti-calendar"></span>
										</span>
								</div>	
                            </div>
                        </div>
                        
                         
                    </div>
                    
                    
                </div>
                
<!--                <hr>  -->
                

            </form>
            
            <hr>
<!--             <h4  id="totalTxt" class=" " > sdsfsdfdfsdf</h4> -->
               <div class="tb-card">
            <span id="totalTxt" class="label label-inverse pull-right "></span>
             <div class="table-responsive ">
                       <table id="roTable" class="table table-hover table-striped">
                           <thead >
                               <tr class="bg-primary">
			                    <th class="text-center">#</th>
			                    <th class="text-center">Customer Name</th>
			                    <th class="text-center">Telephone No.</th>
			                    <th class="text-center">RO No.</th>
			                    <th class="text-center">RO Date</th>
			                    <th class="text-center">Dealer Name</th>
			                    <th class="text-center">Channel</th>
			                    <th class="text-center">Release Status</th>
			                </tr>
                           </thead>
                       
                   </table>
               </div>
               </div>

            
            
            
            
            
            
            
            
            
            
            
            
        </div>
        <div class="modal-footer">
            <button  onclick="addHide()" type="button" class="btn btn-inverse"  ><i class="fa fa-close"></i> Cancel</button>&nbsp;&nbsp;
<!--             <button onclick="saveAddQuestion()" type="submit" class="btn btn-info"  ><i class="fa fa-check-square-o"></i> Release</button> -->
        </div>
    </div>
</div>



  
<script type="text/javascript">

$(function() {
	
	 $("#repairType").select2();
	 
	 var rType  = [${activityBean.repairType}];
	 
	 $('#repairType').val(rType).trigger('change');
	 
});


function releaseStatus(s) {
	  if(s==1){
		  return '<span class="label label-success">Completed</span>';
	  }else if(s==0){
		  return '<span class="label label-warning">Processing</span>';
	  }else{
		  return '<span class="label label-danger">Failed</span>';
	  }
	  return s;
}

var roTable = $('#roTable').DataTable({
 
	 lengthChange: false,
	 pageLength: 100,
     "columns": [
        { "data": "id"
        	,"render": function ( data, type, row, meta ) {
         		return meta.row + meta.settings._iDisplayStart + 1;
        	}
        },
        { "data": "customer"},
        { "data": "tel"},
        { "data": "ro_no"},
        { "data": "settleDate"},
        { "data": "dealer_name"},
        { "data": "channel"},
//         { "data": "release_flg"}
	    { "data": "release_flg"
			,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
		           $(nTd).html(releaseStatus(sData));
		       } 
		  }
     ]
	 ,"aoColumnDefs": [
	      { "sClass": "text-center", "aTargets": [0,4,6,7] }
	    ],
	    
 });
 
	function resetR(){
		roTable.clear().draw();
		$('#btnRelease').hide();
		
	}
	
// 	var _uids = {};
	
	function searchRo(){
	 
		_loader();
		$.ajax({
	         url: cPath+"/activity/getReleaseData?id=${activityId}&viewMode=true"
// 	         data: $('#addForm').serialize()
	     }).done(function (result) {
	    	 roTable.clear().draw();
	         if(result.recordsTotal>0){
	        	 
	        	 roTable.rows.add(result.data).draw();
// 	        	 _uids = result['uids'];
// 	        	 $('#btnRelease').show();
	        	 
	         }else{
	         }
	         
	         $('#totalTxt').html(_totalTxt + result.recordsTotal);
	         
	        _unloader();
	     });
		
	};
    
	 resetR();
	 
	 searchRo();
	 
</script>



 