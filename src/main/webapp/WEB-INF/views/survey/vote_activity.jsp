<%@ page isELIgnored = "false" %>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<style>

.surveyHeader {
    /* background-color: gray !important; */
      background-color: #ca2824 !important; 
/*     background-color: #be3633 !important; */

	display: none;
 
}

.titleHdr {
    color: white !important;
    font-size: 1.2em;
/*     text-align: center; */
}

.dealerHdr {
/*     color: white !important; */
    width: 100%;
}
 

.qhdr{
  text-transform:none;
}

.qgroup{
font-size: 16px;
}
  
 .require-flg{
    color: #d93025;
        padding-left: 5px;
}
 
 
 .qst-title {
    border-bottom: 1px solid rgba(204, 204, 204, 0.35);
    text-transform:  none;
    padding-bottom: 10px;
    margin-bottom: 15px;
    font-size: 14px;
}

.radio-inline {
    display: inline-block;
}

.form-radio{
    padding:5px;
}

.border-checkbox-section .border-checkbox-group {
    display: block;
    padding:5px;
}

.freeTxt{
	height: 100px !important;
}

.radio-l{
	text-align: center;
}

@media only screen and (max-width: 767px) {
	.m-hide {
 	    display: none;
	}
	.radio-l{
		text-align: left;
	}
}	


.modal-body{
    background-color: #fff;
    padding: 1.5em 2em;
}

.label-main {
    margin: 10px 0px;
}

.card {
    border: 2px solid rgba(0,0,0,.125) !important;
}

</style>

<!-- <body class="fix-menu "> -->
 
 
 
<div class="modal-dialog modal-lg"  style="max-width:70em  !important;"  role="document">
		
    <div class="modal-content surveyHeader">
        <div class="modal-header  " >
				<h5 class="modal-title  titleHdr" id="description"> Activity Survey </h5>
<%-- 				  <span class="text-muted" > ${surveyBean.dealerName}</span> --%>
				<button type="button" class="close v-hide" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" >×</span>
				</button>
			</div>
			
        <div class="modal-body" >
        
                    <form  id="voteForm"  class="form-horizontal" data-toggle="validator" role="form" >
        
        
            <div class=" card-block mr-auto ml-auto">
                               
								
									<!-- Page-header start -->
                                  <%--   <div class="card borderless-card surveyHeader m-b-10">
                                   
                                        <div class="card-block">
                                         <h4 class="titleHdr m-b-20" id="description"> Title </h4>
                                            <span class="text-muted dealerHdr" > ${surveyBean.dealerName}</span>
                                        </div>
                                    </div> --%>
                                    <!-- Page-header end -->
                                    
                                
                                    <div class="form-group row">
			                            <label class="col-md-2 col-form-label "><strong>Dealer Name</strong></label> 
			                            <div class="col-md-10"> 
			                                 <input type="text" class="form-control" disabled value="${surveyBean.dealerName}" >
			                            </div>
			                             
			                        </div>
			                        
                                    <div class="form-group row">
			                            <label class="col-md-2 col-form-label"><strong>Customer Name</strong></label> 
			                            <div class="col-md-4"> 
			                                 <input type="text" class="form-control" disabled value="${surveyBean.customer}" >
			                            </div>
			                            <label class="col-md-2 col-form-label"><strong>Telephone No.</strong></label>
			                            <div class="col-md-4"> 
			                                 <input type="text" class="form-control"  disabled value="${surveyBean.tel}" >
			                            </div>
			                             
			                        </div>
			                        <hr>
					                        
                                                                
                                    <div class="page-body" id="question-main">
                                   
                                        
                                    </div>
                                    
                                    
  									<%@ include file="/WEB-INF/views/survey/vote_footer.jsp" %> 
                                    
                                     <div class="text-center">
							            <button  onclick="modalHide()" type="button" class="btn btn-inverse m-r-10 v-hide "  >
							            <i class="fa fa-close"></i> <fmt:message key="lbCancel"/></button> 
							                    
					                    <button onclick="saveSurvey()" type="button" class="btn btn-info"  >
					                    <i class="fa fa-check-square-o"></i><fmt:message key="lbSubmit"/> </button>
						        	</div>
                             
                            </div>
     			
			
			
			 </form>

 <div style="display: none;">
 
<!--             ************   Q Group       -->
                   <div id="question-template">
						    <div class="label-main ">
                                  <label class="label label-primary qgroup"> <i class="fa fa-chevron-circle-right"></i> {gtitle} </label>
                              </div>
						        
			                 <div  id="qt-main-{gid}"></div>
                   </div>
                                        
                                        
<!--                  **********         Q Group                      -->
                                        
<!--                  **********         Answer                         -->

		<div  id="question-template-l">

			<div class="row ">
				<div class="col-sm-12">
					 <div class="card">
                              <div class="card-block" >
									<h4 class="qst-title">{qtitle}</h4>
							<div class="form-group row m-hide m-b-5">
								<div class="col-sm-12">

									<label class="col-form-label label label-inverse-primary">{scaleStartTxt}</label> <label
										class="col-form-label label label-inverse-primary pull-right">{scaleEndTxt}</label>

								</div>
							</div>

							<div class="form-group  row">
								<div class="col-sm-12 radio-l p-0">
									<div class="form-radio">
										<form id="qt-l-{gid}-{qid}"></form>
									</div>
								</div>
							</div>

						</div>
                          </div>
				</div>
			</div>
		</div>
<!-- ----------- P  -->

		<div  id="question-template-p">

			<div class="row">
				<div class="col-sm-12">
					 <div class="card">
                              <div class="card-block" >
                              <h4 class="qst-title">{qtitle}</h4>
                                 
                                 <div class="form-group  row">
								<div class="col-sm-12">
										<textarea rows="5" cols="5" class="form-control freeTxt" 
										id="ans-p-{qid}"  name="ans-p-{qid}" required ></textarea>
									
								</div></div>
                                 
<!--                                  </form> -->
                              </div>
                          </div>
				</div>
			</div>
		</div>
		
<!-- -----------M / C  -->

		<div   id="question-template-m">

			<div class="row">
				<div class="col-sm-12">
					 <div class="card">
                        <div class="card-block"  >
                                    <h4 class="qst-title">{qtitle}</h4>
							<div class=" row">
								<div class="col-sm-12 p-l-30">

									<form id="qt-m-{gid}-{qid}">
										
									</form>
								</div>
							</div>

						</div>
                          </div>
				</div>
			</div>
		</div>


		<!--                  **********         Answer                      -->
	  
</div>																  
		
		<!--                  **********         Question ENDDD                      -->
	  	
			
			

			                      
        </div>
    </div>
</div>
<%-- 	${surveyBean}		 --%>
			
<script>

	var $checkFlg = true;

	var _question = [];
	var _channel = '${CHANNEL}';
	
	function loadQuestion() {
		
		var sid = "${setId}";
		var icon = "warning";
		
		var $lock_flg = "${surveyBean.lockFlg}";
		
		var $close_flg = "${surveyBean.closeFlg}";
		
		if("QR" == _channel ){
			var $vote_flg = "${surveyBean.voteFlg}";
			if($vote_flg > 0 ){
			  $checkFlg = false;
			  icon = "success";
			  $checkFlgTxt = '<fmt:message key="msg.voteComplete"/>';
			  
			}
			
			if($lock_flg > 0){
			  $checkFlg = false;
			  $checkFlgTxt = '<fmt:message key="msg.voteCancel"/>';
			}
			
		}else{
			
			var $complete_flg = "${surveyBean.completeFlg}";
			if($complete_flg == 2 ){
			  $checkFlg = false;
			  icon = "success";
			  $checkFlgTxt = '<fmt:message key="msg.voteComplete"/>';
			}else if($complete_flg == 1 ){
			  $checkFlg = false;
			  $checkFlgTxt = '<fmt:message key="msg.voteClose"/>';
			}
			
			var $exp_flg = "${surveyBean.expiredFlg}";
			if($exp_flg == 1 ){
			  $checkFlg = false;
			  icon = "error";
			  $checkFlgTxt = '<fmt:message key="msg.voteExpired"/>';
			}
			
		}
		
		if($close_flg> 0){
			  $checkFlg = false;
			  $checkFlgTxt = '<fmt:message key="msg.voteCancel"/>';
		}


		if(sid==""){
		  $checkFlg = false;
		  icon = "error";
		  $checkFlgTxt = '<fmt:message key="msg.voteNotFound"/>';
		}
		
		if($checkFlg){
				
			$.ajax({
					type : 'POST',
					async : false,
					data : sid,
					url : cPath + '/question/getQuestionView/' + sid,
					success : function(data) {
						buildQuestionGroup(data);
						$(".surveyHeader").show();
						
					}
				});
		
		}else{
				swal({
					title: "",
					text: $checkFlgTxt,
					icon: icon,
					buttons : false,
					closeOnClickOutside: false,
					closeOnEsc: false,
				});
		}
		
		
	}

	var _preFlg ;
	function buildQuestionGroup(data) {
		_preFlg = data['pre_flg'];
		var questionGroups = data['questionGroups'];
		
		$('#description').html(data['description']);

// 		_question = questionGroups;
		
		for ( var q in questionGroups) {
			
			var qgp = questionGroups[q];
			var gid = qgp['id'];
			
			var questions = qgp['questions'];
			
			if (questions.length > 0) {

				var html = $("#question-template").html();
				html = html.replaceAll('{gid}', gid ).replaceAll('{gtitle}', qgp['name'+_lang]);

				$("#question-main").append(html);

// 				console.log(qgp); 
				
				buildQuestion(questions,gid);

			}
			
		}

	}
	
	
	function buildQuestion(questions , gid) {
	 
// 		var qi = 1;
		for ( var q in questions) {
			var qst = questions[q];
			var qid = qst['questionId'];
			addQuestion(qst,gid,qid); // Add Question
			
// 			qi++;
		}

	}


	function addQuestion(qst,gid,qid) {
		 
// 		console.log(qst ,gid , qid); 
		
		_question.push(qst); // Question Object
		
		var qtype = qst['questionType'];
		var qtitle = qst['questionName'+_lang];
		var rflg = qst['requiredFlg'];
		var rflgTxt = '';
		if("1"==rflg){
			qtitle +=  '<label class="require-flg" > * </label>' ;
			rflgTxt = "required";
		}
// 		console.log("rflgTxt > ",rflgTxt); 
			
		if("L"==qtype){
			let html = $("#question-template-l").html();
			html = html.replaceAll('{qtitle}',qtitle ).replaceAll('{gid}',gid ).replaceAll('{qid}',qid );
			html = html.replaceAll('{scaleStartTxt}', qst['scaleStart'+_lang] ).replaceAll('{scaleEndTxt}',qst['scaleEnd'+_lang] );
			html = html.replaceAll('required', rflgTxt );
			$("#qt-main-"+gid).append(html);
			
		}else if("P"==qtype){
			let html = $("#question-template-p").html();
			html = html.replaceAll('{qtitle}',qtitle ).replaceAll('{gid}',gid ).replaceAll('{qid}',qid );
			html = html.replaceAll('required', rflgTxt );
			$("#qt-main-"+gid).append(html);
		}else if("M"==qtype || "C"==qtype){
			let html = $("#question-template-m").html();
			html = html.replaceAll('{qtitle}',qtitle ).replaceAll('{gid}',gid ).replaceAll('{qid}',qid );
			html = html.replaceAll('required', rflgTxt );
			$("#qt-main-"+gid).append(html);
		} 
		
		buildAnswer(qid, qst, qtype , gid);
		
// 		_qid++;
	}
	 


	function buildAnswer(qid , qst , qtype , gid){
		
		var ans = qst['answers'];
		
// 		console.log(ans, qtype , qid);
		 
		 if("L"==qtype){
			 
			 var scaleStart = qst['scaleStart'];
			 var scaleEnd = qst['scaleEnd'];
			 
// 				console.log(scaleStart, scaleEnd);
			 
			for (var i = scaleStart; i <= scaleEnd; i++) {
				
				 var anstxt  = '<div class="radio radio-inline">  <label> ' + i +
				 		'<input type="radio"  name="ans-l-'+qid+'" value="'+i+'" >' +
					' <i class="helper"></i></label></div>';
					
					$("#qt-l-"+gid+"-"+qid).append(anstxt); 
// 				console.log(anstxt);
			}
			 
		 } else if("M"==qtype){
			 var anstxt  = ' <div class="border-checkbox-section">  ' ;
			 for ( var i in ans) {
		 			var ansi = ans[i];
				anstxt  += ' <div class="border-checkbox-group checkbox-fade fade-in-primary"> <label>' +
				' <input type="checkbox" name="ans-m-'+qid+'" value="'+ansi['id']+'" > <span class="cr">' +
				' <i class="cr-icon icofont icofont-ui-check txt-primary"></i>' +
				' </span> <span>'+ ansi['answerName'+_lang]  +'</span> </label> </div>' ;
			 }
			
		     anstxt  += ' </div>' ;
			 $("#qt-m-"+gid+"-"+qid).append(anstxt); 
			 
		 } else if("C"==qtype){
			 var anstxt  = ' <div class="form-radio required">  ' ;
			 for ( var i in ans) {
	 			var ansi = ans[i];
	 			anstxt  +=  '<div class="radio">  <label> ' +  ansi['answerName'+_lang]  +
	 					'<input type="radio"  name="ans-c-'+qid+'"  value="'+ansi['id']+'" >' +
					' <i class="helper"></i></label></div>';
			 }
			
		     anstxt  += ' </div>' ;
		     
			 $("#qt-m-"+gid+"-"+qid).append(anstxt); 
			 
		 }
		  
	}

	

	function doValidate(){
		var form = $("#voteForm")[0];
	    form.classList.add('was-validated');   
	    if (form.checkValidity() === false) {
	      event.preventDefault();
	      event.stopPropagation();
	      swal("",_comAllTxt,"warning");
	      return false;
	    }
	    
	    return true;
	}
	
function saveSurvey(){ 
	
	if(!doValidate()){
			return false;
	}
	
	var sdata = buildSaveObj();
	if(!sdata){
		return false;
	}
	
	swal({
		title: '<fmt:message key="lbConfirmSurvey"/> ?',
		icon: "warning",
		buttons : true
	}).then((isok) => {
		if (isok) {

			$.ajax({
	            type: 'POST',
				data : {data : JSON.stringify(sdata)},
			  	cache: false,
			  	timeout: 100000,
	            url: cPath+'/vote/save',
	            success: function(data) {
					if(data == "99"){
						swal( "", _errorTxt ,"warning");
					}else if(data == "0"){
						
						swal({
							title: "",
							text: _successSaveTxt,
							icon: "success",
	// 						buttons : false,
							closeOnClickOutside: false,
							closeOnEsc: false
						}).then((value) => {
							
							if("CC"==_channel){
								searchRo();
								modalHide();
							}else{
							  	location.reload();
							}
						});
						
					}
	            },error : function(data) {
	            	swal( "", _errorTxt ,"warning");
	            }
	        });
			
			
		}
	});	
}


function buildSaveObj(){
	
	var answerArr = [];
	
	for(var i in _question){
		
		var question = _question[i];
		var qtyp = question['questionType'];
		var qid = question['questionId'];
		var requiredFlg = question['requiredFlg'];
		var answer = {  //**** Change
				 questionId : qid,
				 questionType :qtyp,
				 questionGroup :question['questionGroup'],
				 requiredFlg :requiredFlg,
				 answerId :null,
				 answerValue :null,
		 };
			
// 		 console.log("qid : "+qid);

		var showRequired = true;
		 
		if("L"==qtyp){
			
			var v = $('input[name="ans-l-'+qid+'"]:checked').val();
			
			if(requiredFlg == 1 && !v){
				 $('input[name="ans-l-'+qid+'"]').focus();
				 showRequired = false;
				 
			}
			
			let canswer = Object.assign({}, answer);
			canswer['answerValue']=v;
			answerArr.push(canswer);
			 
		}else if("C"==qtyp){
			
			var v = $('input[name="ans-c-'+qid+'"]:checked').val();
			
			if(requiredFlg == 1 && !v){
				 $('input[name="ans-c-'+qid+'"]').focus();
// 				swal("",_comAllTxt,"warning");
			    showRequired = false;
			}
			let canswer = Object.assign({}, answer);
			canswer['answerId']=v;
			answerArr.push(canswer);
			 
		}else if("M"==qtyp){
			
			var issel = false;
			 $('input[name="ans-m-'+qid+'"]:checked').each(function() {
				   let cval = this.value;
				   let canswer = Object.assign({}, answer);
				   canswer['answerId']=cval;
				   answerArr.push(canswer);
				   issel = true;
			});
			 
			 if(requiredFlg == 1 && !issel){
				 $('input[name="ans-m-'+qid+'"]').focus();
				 showRequired = false;
			 }
    	  
      }else if("P"==qtyp){
    	  
    	  var v = $('#ans-p-'+qid).val();
    	  
    	  if(requiredFlg == 1 && $.trim(v) == ""){
    		    $('#ans-p-'+qid).focus();
				showRequired = false;
			}
			let canswer = Object.assign({}, answer);
			canswer['answerValue']=v;
			answerArr.push(canswer);
      }
		
		if(!showRequired){
			swal("",_comAllTxt,"warning");
		    return false;
		}
		
	}
	
	var data = {
			setId : '${setId}',
			actId : '${surveyBean.id}',
			uid : '${surveyBean.uid}',
			sid : '${surveyBean.sid}',
			sessionId : '${pageContext.session.id}',
			channel : _channel,
			preFlg : _preFlg,
			occupation : $("#occupation").val(),
			income : $("#income").val(),
			interest : $("#interest").val(),
			expectation : $("#expectation").val(),
			answers : answerArr
	}
	
	return data;
}

	
	loadQuestion();
	
</script>


