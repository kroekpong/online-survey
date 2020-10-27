<%@ page isELIgnored="false" %>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="modal-dialog modal-lg"  style="max-width:70em  !important;"  role="document">
		
    <div class="modal-content">
        <div class="modal-header">
				<h5 class="modal-title"><i class="ti-pencil-alt"></i>  Edit Questionaire Set</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			
        <div class="modal-body">
            <form  id="addQuestionForm"  class="form-horizontal" data-toggle="validator" role="form" >
                <div class="portlet box blue">
                 
                    
                    <div class="portlet-body current">
                    
                         
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label">Questionnaire Set Name</label>
                            <div class="col-md-6"> 
                                 <input type="text" class="form-control"  placeholder="Questionnaire Set Name" id="qname"  
                                 data-toggle="tooltip"  data-original-title="Questionnaire Set Name" name="qname"   autocomplete="off" required >
                            </div>
<!--                             <label class="col-md-2 col-form-label">Questionnaire Description</label> -->
                            <div class="col-md-3">
                                  <label><input type="checkbox" value="1" id="pre_flg"  name="pre_flg"> Pre-Survey </label>&nbsp;&nbsp;
		       				  <label><input type="checkbox" value="1"   id="default_flg" name="default_flg" disabled="disabled"> Default</label>
                            </div>
                        </div>
                        <div class="form-group row">
<!--                             <label class="col-md-2 col-form-label">Questionnaire Set Name</label> -->
<!--                             <div class="col-md-4">  -->
<!--                                  <input type="text" class="form-control"  name="name" data-validator="required" required > -->
<!--                             </div> -->
                            <label class="col-md-3 col-form-label">Questionnaire Description</label>
                            <div class="col-md-6">
                                  <input type="text" class="form-control" placeholder="Questionnaire Description" id="description"  name="description"
                                   data-validator="required"  autocomplete="off" required  >
                            </div>
                        </div>
                                                 
                    </div>
                </div>
                
               <hr> 
                
			<fieldset id="question-main" class="pl-3 pr-3"></fieldset>

				

		  <div class="text-center">
			<button onclick="addQuestion('L')" type="button" class="btn btn-success"  ><i class="fa fa-plus"></i> New Question  </button>
		  </div>



            </form>
        </div>
        <div class="modal-footer">
            <button  onclick="addHide()" type="button" class="btn btn-inverse"  ><i class="fa fa-close"></i> Cancel</button>&nbsp;&nbsp;
            <button onclick="saveAddQuestion()" type="submit" class="btn btn-info"  ><i class="fa fa-save"></i> Save</button>
        </div>
    </div>
</div>




<!-- /***** Question Zone *****/ -->
				<div  id="question-template" style="display: none;">
				<div class="card  qt-{qid}"  >
					<div class="card-header caption bg-primary">
						<strong class="qt-hd">XXX</strong>
						
<!-- 						<i class="pull-right fa fa-minus minimize-card">  -->
<!-- 						</i>  -->
								 
<!-- 						<button type="button" class="pull-right"  data-toggle="collapse" data-target="#card-qt-{qid}" -->
<!-- 							      aria-expanded="false"  -->
<!-- 								<span aria-hidden="true">-</span> -->
<!-- 						</button> -->
							<!-- <div class="card-header-right question-div">
                               <ul class="list-unstyled card-option" >
                                   <li data-toggle="collapse" data-target="#card-qt-{qid}" aria-expanded="true" aria-controls="card-qt-{qid}" >
                                   <i class="fa fa-minus minimize-card question-txt" ></i>
                                   </li>
                               </ul>
                           </div> -->
					</div>

					<div class="card-body  " id="card-qt-{qid}">
					
						<div class="form-group row">
<!-- 						    <label class="col-md-2 col-form-label">Question Name En</label> -->
							<div class="col-md-6">
							    <input type="text" class="form-control"  name="questionNameEn_{qid}" placeholder="Question Name English"  
							    autocomplete="off" data-toggle="tooltip" data-original-title="Question Name English" required>
							</div>
                            <label class="col-md-2 col-form-label"><b>Question Type</b></label>
							<div class="col-md-4">
									<select size="1" class="form-control"   id="questionType_{qid}" name="questionType_{qid}"  onChange="getAnswerType(this.value, {qid})" > 
                                     	<c:forEach var="item" items="${DDL_QUESTION_TYPE}">
											<option value="${item.code}">${item.name}</option>
										</c:forEach>
                                   </select>		
                             </div>
						</div>
						<div class="form-group row">
<!-- 							 <label class="col-md-2 col-form-label">Question Name Th</label> -->
							<div class="col-md-6">
							    <input type="text" class="form-control" name="questionNameTh_{qid}"  data-toggle="tooltip" data-original-title="Question Name Thai" 
							    placeholder="Question Name Thai"  autocomplete="off" required>
							</div>
 							<label class="col-md-2 col-form-label"><b>Question Group</b></label>
							<div class="col-md-4">
									<select size="1" class="form-control"   id="questionGroup_{qid}" name="questionGroup_{qid}"> 
 										<c:forEach var="item" items="${DDL_QUESTION_GROUP}">
											<option value="${item.id}">${item.name}</option>
										</c:forEach>                                
									</select>							
                             </div>
						</div>
						
						<hr>
						
						<fieldset id="ans-main-{qid}"></fieldset>
						
						
						<hr>

						<div class="row no-print">
							<div class="col-md-12 ">
								<button type="button" class="btn btn-danger pull-right" onclick="deleteQuestion({qid})"
									style="margin-right: 5px;"><i class="ti-trash"></i> Delete Question</button>


							<label class="col-form-label pull-right pr-5"> <input type="checkbox" value="1"   id="requiredFlg_{qid}" name="requiredFlg_{qid}"> Required 
								</label> 

							</div>
						</div>


					</div>
					
      
				</div>
				</div>
				
				
<!-- 			ANSWER  Template  -->
					<div  id="ans-l-template" style="display: none;">
							<div class="form-group row">
								<label class="col-md-2 col-form-label"  ><b> Scale  </b> </label>
								<div class="col-md-2">
									<select size="1" class="form-control" name="scaleStart_{qid}">
                                    	<option value="0">0</option>
                                        <option value="1">1</option>
                                    </select>
                                </div>  
                                <label class="col-md-1 col-form-label" style="text-align: center;"> To </label>
								<div class="col-md-2">
									<select size="1" class="form-control" name="scaleEnd_{qid}">
                                    	<option value="2">2</option>
                                    	<option value="3">3</option>
                                    	<option value="4">4</option>
                                    	<option value="5">5</option>
                                    	<option value="6">6</option>
                                    	<option value="7">7</option>
                                    	<option value="8">8</option>
                                    	<option value="9">9</option>
                                    	<option value="10" selected>10</option>
                                    </select>
                                </div>
							</div>
						
							<div class="form-group row">
							    <label class="col-md-2 col-form-label"  ><b> Scale Start </b> </label>
								<div class="col-md-4">
								    <input type="text" class="form-control"  name="scaleStartEn_{qid}" placeholder="English" required>
								</div>
								<div class="col-md-4">
								    <input type="text" class="form-control" name="scaleStartTh_{qid}" placeholder="Thai" required>
								</div>
							</div>
							<div class="form-group row">
							    <label class="col-md-2 col-form-label"><b> Scale End </b> </label>
								<div class="col-md-4">
								    <input type="text" class="form-control"  name="scaleEndEn_{qid}" placeholder="English" required>
								</div>
								<div class="col-md-4">
								    <input type="text" class="form-control" name="scaleEndTh_{qid}" placeholder="Thai" required>
								</div>
							</div>
							 
					</div>	
						
					<div  id="ans-m-template" style="display: none;">	
							<div class="form-group row  ans-m-fm-{qid}-{ansno}" >
								<label class="col-md-2 col-form-label"  ><b class="ans-m-hd-{qid}" > XX </b></label>
								<div class="col-md-4">
								    <input type="text" class="form-control"  name="answerNameEn_{qid}-{ansno}" placeholder="Answer English" required autocomplete="off">
								</div>
								<div class="col-md-4">
								    <input type="text" class="form-control" name="answerNameTh_{qid}-{ansno}" placeholder="Answer Thai" required autocomplete="off">
								</div>
								  <div class="col-md-2">			
								 	<button onclick="addAnswer({qid});" type="button" class="btn btn-success add-ans-btn-{qid}"  >  Add </button>
								 	<button onclick="delAnswer({qid}, '{ansno}');" type="button" class="btn btn-danger del-ans-btn-{qid}"  >  Delete </button>
								 </div>
							</div>
							 
					 </div>
						
						
					<div  id="ans-p-template" style="display: none;">	
						
						<div class="form-group row">
						    <label class="col-md-2 col-form-label"><b>Paragraph</b> </label>
							<div class="col-md-10">
							    <input type="text" class="form-control"  placeholder="Free Text" disabled>
							</div>
						</div>
							 
				 </div>
						
				<!-- 			ANSWER  Template  -->
				
				
<!-- *** Template ******** -->
				

<script type="text/javascript">


$(function() {
	
	$('#pre_flg').change(function(){
	
		if ($('#pre_flg').prop('checked')) {
			$("#default_flg").prop('disabled', false);
			$("#default_flg").prop('checked', false);
		}else{
			$("#default_flg").prop('disabled', true);
			$("#default_flg").prop('checked', false);
		}
		
	});
	  
});

 
var _qid = 1;
function addQuestion(qtype){
// 	console.log("addQuestion");
	let html = $("#question-template").html();
	html = html.replaceAll('{qid}',_qid );
	
	$("#question-main").append(html);
	
// 	$(".qt-"+_qid).hide().slideDown("slow");
	sortQuestion();
	
	getAnswerType(qtype,_qid)
	
	_qid++;
}

function deleteQuestion(qid){
	
	swal({
			title: _confirmDelTxt ,
			icon: "warning",
			buttons : true
//			dangerMode : true,
	}).then((isok) => {
		if (isok) {
				$(".qt-"+qid).remove();
				sortQuestion();
				delete _qtype[qid];
				delete _ans[qid];
		}
	});	
	
}

function sortQuestion(){
	var tno = 1;
	$(".qt-hd").each(function() {
		$(this).text(_questionTxt+" "+tno++);
	});
	
	$("#addQuestionForm").removeClass('was-validated');
}

var _ans =  {};
function addAnswer(qid){
//	console.log(_ans[qid]);
	if(_ans[qid]>0){
		_ans[qid] = _ans[qid]+1;
	}else{
		_ans[qid] = 1;
	}
	
	ansno = _ans[qid];
	
	let html = $("#ans-m-template").html();
	html = html.replaceAll('{qid}',qid ).replaceAll('{ansno}',ansno );
	
	$("#ans-main-"+qid).append(html);
	
	sortAnswer(qid);
}


function delAnswer(qid , ansno){
	$(".ans-m-fm-"+qid+"-"+ansno).remove();
	sortAnswer(qid);
	_ans[qid]--;
}


function sortAnswer(qid){
	var ano = 1;
	$(".ans-m-hd-"+qid).each(function() {
		$(this).text(_answerTxt+" "+ano++);
	});
	
	var abtn = $(".add-ans-btn-"+qid).length;
	var abtni = 0;
	$(".add-ans-btn-"+qid).each(function() {
		if(abtni==0){
			$(this).show();
		}else{
			$(this).hide();
		}
		abtni++;
	});
	
	var dbtni = 0;
	$(".del-ans-btn-"+qid).each(function() {
		if(dbtni>0){
			$(this).show();
		}else{
			$(this).hide();
		}
		dbtni++;
	});
	
}

var _qtype = {};
function getAnswerType(qtype, qid){
//	console.log("getAnswerType : "+qtype , qid);
//	console.log(_qtype , _qtype[qid]);
	
	if("L"==qtype){
		$("#ans-main-"+qid).html("");
		let html = $("#ans-l-template").html();
		html = html.replaceAll('{qid}',qid );
		$("#ans-main-"+qid).append(html);
		delete _ans[qid];
	}else if("P"==qtype){
		$("#ans-main-"+qid).html("");
// 		let html = $("#ans-p-"+qid).html();
		let html = $("#ans-p-template").html();
		html = html.replaceAll('{qid}',qid );
		
		$("#ans-main-"+qid).append(html);
		delete _ans[qid];
	}else if("M"==qtype || "C"==qtype){
		if("M"!=_qtype[qid] && "C"!=_qtype[qid]){
			$("#ans-main-"+qid).html("");
			addAnswer(qid);
		}
	}
	
	_qtype[qid] = qtype;
	
	
}



function doValidate(){
	 var form = $("#addQuestionForm")[0];
    form.classList.add('was-validated');   
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
      swal("",_comAllTxt,"warning");
      return false;
    }
    return true;
}



// var _fdata = {};
var _fans = ["questionNameEn", "questionNameTh" , "questionGroup" , "questionType" ];
var _ansL = [ "scaleStart" , "scaleStartTh", "scaleStartEn" ,  "scaleEnd" , "scaleEndTh", "scaleEndEn" ];
function buildSaveObj(){
	
	var qt = [];
	
	for(var k in _qtype){
		
		var qtyp = _qtype[k];
		var obj={};
		   
		for(var i in _fans){
			obj[_fans[i]] = $("[name="+_fans[i]+"_"+k+"]").val();
			
		}
		
		obj["requiredFlg"] = $("#requiredFlg_"+k).prop('checked')? "1":"0";
		
		if("L"==qtyp){
			for(var i in _ansL){
				obj[_ansL[i]] = $("[name="+_ansL[i]+"_"+k+"]").val();
			}
		}
		

		  if("C"==qtyp ||"M"==qtyp){
	    	  var ansno = _ans[k]; 
	    	  var answers = [];
	    	  
	    	  for (var i = 1; i <= ansno; i++) {
	    		  var answerNameEn = $("[name=answerNameEn_"+k+"-"+i+"]").val();
	    		  var answerNameTh = $("[name=answerNameTh_"+k+"-"+i+"]").val();
	    		  answers.push({"answerNameEn": answerNameEn  ,"answerNameTh" : answerNameTh });
			  } 
	    	  
	    	  obj['answers'] = answers;
	    	  
	      }
	      
	      qt.push(obj);
	}
	 
	
	var data = {
			id : "${sid}",
			name : $("#qname").val(),
			description : $("#description").val(),
			pre_flg : ($("#pre_flg").prop( 'checked' )? "1":"0"),
			default_flg :( $("#default_flg").prop( 'checked' )? "1":"0"),
			questions : qt
	}
	
	return data;
}



function saveAddQuestion(){ 
	
	if(!doValidate()){
			return false;
	}
	
	swal({
			title: _confirmSaveTxt,
			icon: "warning",
			buttons : true
//			dangerMode : true,
	}).then((isok) => {
		if (isok) {
			
			var data = buildSaveObj();
			
			$.ajax({
		            type: 'POST',
					data: { data : JSON.stringify(data) } ,
		            url: cPath+'/question/save',
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
	});
	
}


function loadQuestion(){
	
	var sid = "${sid}";
	
	$.ajax({
        type: 'POST',
        async: false,
		data: sid ,
        url: cPath+'/question/getQuestionSet/'+sid,
        success: function(data) {
        	buildQuestion(data);
        }
    });

}



function buildQuestion(data){
// 	console.log(data);
	
	$('#qname').val(data['name']);
	$('#description').val(data['description']);
	
	if (data['pre_flg']==1) {
		$("#pre_flg").prop('checked', true);
		$("#default_flg").prop('disabled', false);
	}
	
	if (data['default_flg']==1) {
		$("#default_flg").prop('disabled', false);
		$("#default_flg").prop('checked', true);
	}
	
	var questions = data['questions'];
	var qi = 1;
	for (var q in questions) {
		var qst = questions[q];
// 		console.log(qst);
		
		addQuestion(qst['questionType']); // Add Question
		
		buildAnswer(qi, qst);
		
		qi++;
	}
	
}


function buildAnswer(qi, qst){
	var qtyp = qst['questionType'];
	
	if (qst['requiredFlg']==1) {
		$("#requiredFlg_"+qi).prop('checked', true);
	}
	
	for(var i in _fans){
		$("[name="+_fans[i]+"_"+qi+"]").val(qst[_fans[i]]);
	}
	
	
	if("C"==qtyp ||"M"==qtyp){
		var answers = qst['answers'];
		var ri = 1;
		for (var i in answers) {
			if(ri>1){
				addAnswer(qi);
			}
			$("[name=answerNameEn_"+qi+"-"+(ri)+"]").val(answers[i]['answerNameEn']);
			$("[name=answerNameTh_"+qi+"-"+(ri)+"]").val(answers[i]['answerNameTh']);
			ri++;
		}
	}else if("L"==qtyp){
		for(var i in _ansL){
			$("[name="+_ansL[i]+"_"+qi+"]").val(qst[_ansL[i]]);
		}
	}
	
	 
	 
	
}


loadQuestion();
	
 
</script>



 