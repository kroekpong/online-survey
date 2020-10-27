<%@ page isELIgnored = "false" %>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" %>

<%-- <%@ include file="/WEB-INF/views/header.jsp" %> --%>



<style>

.surveyHeader {
    /* background-color: gray !important; */
    background-color: #be3633 !important;
    color: white !important;
}

.titleHdr {
    color: white !important;
    font-size: 1.2em;
/*     text-align: center; */
}

.dealerHdr {
    color: white !important;
}
 

.qhdr{
  text-transform:none;
}

.qgroup{
font-size: 16px;
}
 
 .signup-card{
/*      max-width: 60em !important;  */
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


@media only screen and (max-width: 767px) {
	.m-hide {
	    display: none;
	}
}	


.modal-body{
    background-color: #f7f7f7;
}

</style>

<!-- <body class="fix-menu "> -->
 
 
 
<div class="modal-dialog modal-lg"  style="max-width:70em  !important;"  role="document">
		
    <div class="modal-content">
        <div class="modal-header">
				<h5 class="modal-title"><i class="ti-pencil-alt"></i>  View Questionaire Set</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			
        <div class="modal-body" >
        
                    <form  id="viewQuestionForm"  class="form-horizontal" data-toggle="validator" role="form" >
        
            <div class="signup-card card-block mr-auto ml-auto">
                                <div class="page-wrapper">
								
									<!-- Page-header start -->
                                    <div class="card borderless-card surveyHeader m-b-10">
                                   
                                        <div class="card-block">
                                         <h4 class="titleHdr m-b-20" id="description"> Title </h4>
<!--                                             <h5 class="m-b-10">แบบสอบถามความพึงพอใจในการให้บริการ</h5> -->
                                            <span class="text-muted dealerHdr"></span>
                                             
                                        </div>
                                    </div>
                                    <!-- Page-header end -->
                                    

                                    <div class="page-body" id="question-main">
                                   
                                        
                                        
                                    </div>
                                    
                                    
                                    
                                     <div class="text-center">
							            <button  onclick="addHide()" type="button" class="btn btn-inverse"  >
							            <i class="fa fa-close"></i> Close</button>&nbsp;&nbsp;
							        </div>
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
							<div class="form-group row m-hide  m-b-5">
								<div class="col-sm-12">

									<label class="col-form-label label label-inverse-primary">{scaleStartTxt}</label> <label
										class="col-form-label label label-inverse-primary pull-right">{scaleEndTxt}</label>

								</div>
							</div>

							<div class="form-group  row">
								<div class="col-sm-12 text-center">
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
										<textarea rows="5" cols="5" class="form-control freeTxt" name="answer"></textarea>
									
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
			
			
			                      
        </div>
    </div>
</div>
			
																	  
<script>

	function loadQuestion() {
		var sid = "${sid}";
// 		console.log("sid  :" + sid);

		$.ajax({
			type : 'POST',
			async : false,
			data : sid,
			url : cPath + '/question/getQuestionView/' + sid,
			success : function(data) {
				buildQuestionGroup(data);
			}
		});

	}

	function buildQuestionGroup(data) {
		var questionGroups = data['questionGroups'];

		$('#description').html(data['description']);

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
	 
		var qi = 1;
		for ( var q in questions) {
			var qst = questions[q];

			addQuestion(qst,gid,qi); // Add Question
 
			qi++;
		}

	}


// 	var _qid = 1;
	function addQuestion(qst,gid,qid) {
		 
// 		console.log(qst ,gid , qid); 
		
		var qtype = qst['questionType'];
		var qtitle = qst['questionName'+_lang];
		var rflg = qst['requiredFlg'];
// 		var rflgTxt = '';
		if("1"==rflg){
			qtitle +=  '<label class="require-flg" > * </label>' ;
		}
			
		
		if("L"==qtype){
			let html = $("#question-template-l").html();
			html = html.replaceAll('{qtitle}',qtitle ).replaceAll('{gid}',gid ).replaceAll('{qid}',qid );
			html = html.replaceAll('{scaleStartTxt}', qst['scaleStart'+_lang] ).replaceAll('{scaleEndTxt}',qst['scaleEnd'+_lang] );
			$("#qt-main-"+gid).append(html);
			
		}else if("P"==qtype){
			let html = $("#question-template-p").html();
			html = html.replaceAll('{qtitle}',qtitle ).replaceAll('{gid}',gid ).replaceAll('{qid}',qid );
			$("#qt-main-"+gid).append(html);
			
		}else if("M"==qtype || "C"==qtype){
			
			let html = $("#question-template-m").html();
			html = html.replaceAll('{qtitle}',qtitle ).replaceAll('{gid}',gid ).replaceAll('{qid}',qid );
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
				
				 var anstxt  = '<div class="radio radio-inline">  <label> ' + i + '<input type="radio" name="point" >' +
					' <i class="helper"></i></label></div>';
					
					$("#qt-l-"+gid+"-"+qid).append(anstxt); 
// 				console.log(anstxt);
			}
			 
		 } else if("M"==qtype){
			 var anstxt  = ' <div class="border-checkbox-section">  ' ;
			 for ( var i in ans) {
		 			var ansi = ans[i];
				anstxt  += ' <div class="border-checkbox-group  checkbox-fade fade-in-primary"> <label>' +
				' <input type="checkbox" value=""> <span class="cr">' +
				' <i class="cr-icon icofont icofont-ui-check txt-primary"></i>' +
				' </span> <span>'+ ansi['answerName'+_lang]  +'</span> </label> </div>' ;
			 }
			
		     anstxt  += ' </div>' ;
			 $("#qt-m-"+gid+"-"+qid).append(anstxt); 
			 
		 } else if("C"==qtype){
			 var anstxt  = ' <div class="form-radio">  ' ;
			 for ( var i in ans) {
	 			var ansi = ans[i];
	 			
	 			anstxt  +=  '<div class="radio  ">  <label> ' +  ansi['answerName'+_lang]  + '<input type="radio" name="point" >' +
					' <i class="helper"></i></label></div>';
			 }
			
		     anstxt  += ' </div>' ;
		     
			 $("#qt-m-"+gid+"-"+qid).append(anstxt); 
			 
		 }
		  
	}

	loadQuestion();
	
</script>

<!-- </body> -->