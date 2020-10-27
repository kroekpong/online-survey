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
				<h5 class="modal-title"><i class="ti-pencil-alt"></i>  Maintain Activity & Release </h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			
        <div class="modal-body">
            <form  id="addForm"  class="form-horizontal" data-toggle="validator" role="form" >
                <div class="portlet box blue">
                 
                    
                    <div class="portlet-body current">
                    
                         
                        <div class="form-group row">
                            <label class="col-md-2 col-form-label">Activity Name</label>
                            <div class="col-md-4"> 
                                 <input type="text" class="form-control"  name="activityName" autocomplete="off" required >
                            </div>
                            <label class="col-md-2 col-form-label">Description</label>
                            <div class="col-md-4"> 
                                 <input type="text" class="form-control"  name="description"  autocomplete="off" required >
                            </div>
                             
                        </div>
                        <div class="form-group row">
                            <label class="col-md-2 col-form-label">Questionnaire Set</label>
                            <div class="col-md-4"> 
                           		 <select  class="form-control"  id="setId"  name="setId"> 
 										<c:forEach var="item" items="${DDL_QUESTION_SET}">
											<option value="${item.id}" data-name="${item.pre_flg}"  title="${item.description}" >${item.name}</option>
										</c:forEach>                                
									</select>		
								 <input type="hidden" id="preFlg" name="preFlg"  >
									
                            </div>
                            
							<label class="col-sm-2 col-form-label">Period RO (From - To)</label>
							<div class="col-sm-2">
								 <div class="input-group date" >
									<input type='text' class="form-control" id="roStartDate" autocomplete="off"  name="roStartDate" required/> <span
										class="input-group-addon"> 
										<span class="ti-calendar"></span>
									</span>
								</div>														
							</div>
							<div class="col-sm-2">
								 <div class="input-group date" >
									<input type='text' class="form-control"  id="roEndDate" autocomplete="off" name="roEndDate" required/> <span
										class="input-group-addon"> 
										<span class="ti-calendar"></span>
									</span>
								</div>												 
							</div>		 
                             
                        </div>
                        <div class="form-group row">
                            <label class="col-md-2 col-form-label">Repair Order Type</label>
                            <div class="col-md-4"> 
                                  <select class="form-control select2" multiple="multiple"  id="repairType" name="repairTypes" required> 
 										<c:forEach var="item" items="${DDL_REPAIR_TYPE}">
											<option data-name="${item.nameTh}" value="${item.id}" >${item.name}</option>
										</c:forEach>                                
									</select>		
									<input type="hidden" id="repairTypeName" name="repairTypeName"  >
                            </div>
                            <label class="col-md-2 col-form-label">Submit Channel</label>
                            <div class="col-md-4"> 
                                    <select  class="form-control" name="channel" required> 
 										<option value="SMS">SMS</option>
                                    	<option value="CC">Call Center</option>                             
									</select>	
                            </div>
                             
                        </div>
                        
                        <div class="form-group row">
                            <label class="col-md-2 col-form-label">Rule Setting</label>
                            <div class="col-md-4"> 
                                   <select  class="form-control"  id="rule" name="rule"> 
 											<c:forEach var="item" items="${DDL_RULES}">
												<option  value="${item.code}" >${item.name}</option>
										</c:forEach>                              
									</select>		
                            </div>
                             <label class="col-md-2 col-form-label" id="ruleTxt">Number of submit</label>
                            <div class="col-md-4"> 
                             		 <div class="input-group " >
		                                    <input type="text" class="form-control"  id="submitNo"  name="submitNo" autocomplete="off" required  >
		                            		<span class="input-group-addon" id="percgroup">%</span>
		                            </div>
                            </div>
                            
                             
                        </div>
                        
                        <div class="form-group row">
                            
                            <label class="col-md-2 col-form-label">Expire Date</label>
                            <div class="col-md-4"> 
                            <div class="input-group date" >
									<input type='text' class="form-control" id="expireDate"  name="expireDate" autocomplete="off" required/> <span
										class="input-group-addon"> 
										<span class="ti-calendar"></span>
									</span>
							</div>	
<!--                                     <input type="text" class="form-control"  name="expireDate"  required > -->
                            </div>
                              <label class="col-md-2 col-form-label"></label>
                            <div class="col-md-4"> 
                                        <button onclick="searchRo()" type="button" class="btn btn-primary"  ><i class="fa fa-search"></i> Search</button>
                                        <button id="btnRelease" onclick="comfirmRelease()" type="button" class="btn btn-success m-l-5 "  ><i class="fa fa-check-square-o"></i> Release</button>
                            
<!--                                     <input type="text" class="form-control"  name="submitNo"  required > -->
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
                               <tr class="bg-danger ">
			                    <th class="text-center">#</th>
			                    <th class="text-center">Customer Name</th>
			                    <th class="text-center">Telephone No.</th>
			                    <th class="text-center">RO No.</th>
			                    <th class="text-center">RO Date</th>
			                    <th class="text-center">Dealer Name</th>
<!-- 			                    <th class="text-center">Channel</th> -->
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


var _confirmReleaseTxt = "Confirm Release Activity ? ";

$(function() {
	/* 
	$('#pre_flg').change(function(){
	
		if ($('#pre_flg').prop('checked')) {
			$("#default_flg").prop('disabled', false);
			$("#default_flg").prop('checked', false);
		}else{
			$("#default_flg").prop('disabled', true);
			$("#default_flg").prop('checked', false);
		}
		
	}); */
	
	resetR();
// 	$('#btnRelease').hide();
	
	$('#roStartDate,#roEndDate,#expireDate').datepicker({
		format : "yyyy-mm-dd",
		autoclose:true,
		enableOnReadonly:true
	}); 
	
	
//     $('#submitNo').bind('keyup paste', function(){
//         this.value = this.value.replace(/[^0-9]/g, '');
//         if(this.value > 5000){
//         	this.value = 5000;
//         }
        
//     });
	
    $('#submitNo').bind('keyup paste', function(){
        this.value = this.value.replace(/[^0-9]/g, '');
        this.value = validNumSubmit(this.value);
   });
    
// 	$.ajaxSetup({async:false});
	  
	
	$("#roStartDate").on("change", function(e) {
// 		console.log($(this).datepicker('getDate'));
			$('#roEndDate').datepicker('setStartDate',$(this).datepicker('getDate'));
	});
		
	$("#roEndDate").on("change", function(e) {
			$('#roStartDate').datepicker('setEndDate',$(this).datepicker('getDate'));
	});
		
		
	 $('.form-control').change(function() {
			resetR();
	 });
	 
	 $("#repairType").select2( );

		$('#rule').change(function(){
			_subType = $(this).val();
			$("#submitNo").val("");
			if (_subType==1) {
				$("#percgroup").hide();
			}else{
				$("#percgroup").show();
			}
			
		});
	 $("#percgroup").hide();
});

var _subType = 1;
function validNumSubmit(value){ 
	if (_subType==1) {
		 if(value > 5000){
	     	return 5000;
		 }else if(value <= 0){
			 return "";
	     }else{
	    	 return value;
	     }
	}else{
		 if(value > 100){
			 return 100;
	     }else if(value <= 0){
			 return "";
	     }else{
	    	 return value;
	     }
	}
}




var roTable = $('#roTable').DataTable({
	 lengthChange: false,
	 pageLength: 100,
     "columns": [
        { "data": "id"
        	,"render": function ( data, type, row, meta ) {
         		return meta.row + meta.settings._iDisplayStart + 1;
        	}},
        { "data": "customer"},
        { "data": "tel"},
        { "data": "ro_no"},
        { "data": "settleDate"},
        { "data": "dealer_name"}
	          
//          { "data": "id"  
//          	,"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
// 	            $(nTd).html(txt);
// 	        } 
//          }
     ]
			,"aoColumnDefs": [
	      { "sClass": "text-center", "aTargets": [0] }
	    ],
 });
 
	function resetR(){
		roTable.clear().draw();
		$('#btnRelease').hide();
		$('#totalTxt').html(_totalTxt + 0);	
		
	}
	
	var _uids = {};
	
	function searchRo(){
		
		var repairType = $("#repairType").find(':selected').data('name');
	    $('#repairTypeName').val(repairType);
			
		var preFlg = $("#setId").find(':selected').data('name');
	    $('#preFlg').val(preFlg);
		
		
		if(!doValidate()){
			return false;
		}
		_loader();
		$.ajax({
         url: cPath+"/activity/getCustomerRo",
         data: $('#addForm').serialize()
     }).done(function (result) {
    	 roTable.clear().draw();
         if(result.recordsTotal>0){
        	 
        	 roTable.rows.add(result.data).draw();
        	 _uids = result['uids'];
        	 $('#btnRelease').show();
        	 
         }else{
         }
         
         $('#totalTxt').html(_totalTxt + result.recordsTotal);
         
        _unloader();
     });
		
	};
  
	function saveRelease(){ 
		
		var data = $('#addForm').serialize()+ '&' + $.param({ uids : _uids },true);
				
		$.ajax({
            type: 'POST',
			data: data ,
            url: cPath+'/activity/release',
            success: function(data) {
				if(data == "99"){
					swal( "", _errorTxt ,"warning");
				}else if(data == "0"){
					 
					$('#modalAdd').modal('hide');
					notify(_successSaveTxt, 'success');
					doSearch();
				}
            }
        });
	}

	function comfirmRelease(){ 
		
		if(!doValidate()){
				return false;
		}
		
		swal({
			title: _confirmReleaseTxt,
			icon: "warning",
			buttons : true
		}).then((isok) => {
			if (isok) {
				saveRelease();
			}
		});	
	}
		
	

	function doValidate(){
		 var form = $("#addForm")[0];
	    form.classList.add('was-validated');   
	    if (form.checkValidity() === false) {
	      event.preventDefault();
	      event.stopPropagation();
	      swal("",_comAllTxt,"warning");
	      return false;
	    }
	    return true;
	}
	
</script>



 