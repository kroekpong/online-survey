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

.thc{
   width: 20%;
}

#totalTxt{
	font-size: 14px;
}
-->
</style>


  
<div class="modal-dialog modal-lg"  style="max-width:80em  !important;"  role="document">
		
    <div class="modal-content">
        <div class="modal-header">
				<h5 class="modal-title"><i class="fa fa-dashboard"></i> <fmt:message key="report.dashboard" /> </h5>
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
                            <label class="col-md-2 col-form-label"><fmt:message key="activity.name" /></label> 
                            <div class="col-md-4"> 
                                 <input type="text" class="form-control" disabled 
                                 value="${reportData.activity.activityName}" title="${reportData.activity.activityName}"  >
                            
                            
                            </div>
                            <label class="col-md-2 col-form-label"><fmt:message key="lb0006" /></label>
                            <div class="col-md-4"> 
                                 <input type="text" class="form-control"  disabled
                                  value="${reportData.activity.description}" title="${reportData.activity.description}" >
                            </div>
                             
                        </div> 
                        <div class="form-group row">
                            <label class="col-md-2 col-form-label"><fmt:message key="lb0003" /></label> 
                            <div class="col-md-4"> 
                                 <input type="text" class="form-control" disabled 
                                 value="${reportData.activity.setName}" title="${reportData.activity.setName}" >
                            
                            
                            </div>
                            <label class="col-md-2 col-form-label"><fmt:message key="lb0004" /></label>
                            <div class="col-md-4"> 
                                 <input type="text" class="form-control"  disabled
                                  value="${reportData.activity.setDescription}"  title="${reportData.activity.setDescription}" >
                            </div>
                             
                        </div> 
                         
                           <div class="form-group row">
                         <canvas id="myChart" height="100"></canvas>
                        </div>
                        
                          <div class="form-group row">
                           <div class="table-responsive">
                               <table id="rstable" class="table table-bordered table-hover" >
                                   <thead>
			                          <tr class="bg-primary"  >
			                            <th class="text-center thc  " ><fmt:message key="lbTotal" /></th>
			                            <th class="text-center thc    c-pre"><fmt:message key="qtype.pre" /></th>
			                            <th class="text-center thc  "><fmt:message key="qtype.sms" /></th>
			                            <th class="text-center thc  "><fmt:message key="qtype.cc" /></th>
			                            <th class="text-center thc  "><fmt:message key="qtype.incomplete" /></th>
			                        </tr>
                                   </thead>
                                    <tr >
			                            <th class="text-center" >${reportData.total}</th>
			                            <th class="text-center c-pre" >${reportData.pre}</th>
			                            <th class="text-center" >${reportData.sms}</th>
			                            <th class="text-center" >${reportData.cc}</th>
			                            <th class="text-center" >${reportData.incomplete}</th>
			                        </tr>
                                    <tr >
			                            <th class="text-center" >${reportData.totalP}%</th>
			                            <th class="text-center c-pre" >${reportData.preP}%</th>
			                            <th class="text-center" >${reportData.smsP}%</th>
			                            <th class="text-center" >${reportData.ccP}%</th>
			                            <th class="text-center" >${reportData.incompleteP}%</th>
			                        </tr>
                                   
                               </table>
                              
                           </div>
                           </div>
                                 
                                           
                    </div>
                    
                    
                </div>
                
               
                

            </form>
            
                
            
            
            
            
        </div>
        <div class="modal-footer">
            <button  onclick="modalHide()" type="button" class="btn btn-inverse"  ><i class="fa fa-close"></i> Cancel</button>&nbsp;&nbsp;
            <button onclick="comfirmSave()" type="submit" class="btn btn-info"  ><i class="fa fa-save"></i> Confirm</button>
        </div>
    </div>
</div>


<script type="text/javascript">

var chartColors = {
	red: 'rgb(255, 99, 132)',
	orange: 'rgb(255, 159, 64)',
	yellow: 'rgb(255, 205, 86)',
	green: 'rgb(75, 192, 192)',
	blue: 'rgb(54, 162, 235)',
	purple: 'rgb(153, 102, 255)',
	grey: 'rgb(201, 203, 207)'
};
	 

		
		
function buildGraph(predata){

	var options = {
			  plugins: {
// 				  labels: {
// 					    render: function (args) {
// 					      return '$' + args.value;
// 					    },
// 					    display: 'auto'
// 					  }
	
		            datalabels: {
		            	color : "black",
		                labels: {
		                    title: {
		                        font: {
		                            weight: 'bold'
		                        }
		                    }
		                   
		                },
		                formatter: function(value, context) {
		                	 if(value>0){
		                	 	return context.chart.data.labels[context.dataIndex] +" "+ value+"%";
		                	 }else{
		                		 return "";
		                	 }
		                },
		                display: 'auto',
		                
// 		                anchor:'end'
		            }
		        }
	};
	
		
	var ctx = document.getElementById('myChart').getContext('2d');
	var myChart = new Chart(ctx, {
	    type: 'pie',
	    data: predata, 
		options: options
	});


	
}

// var _preFlg = '${CHANNEL}';

function modalHide(){
    $("#modalAdd").modal("hide");
    $("#modalAdd").html("");
}
	
	


$(function() {
	

	var predata = {
		     labels: ['<fmt:message key="qtype.pre" />', 
		              '<fmt:message key="qtype.cc" />',
		              '<fmt:message key="qtype.sms" />', 
		              '<fmt:message key="qtype.incomplete" />' ],
		     datasets: [
		           {
		         data: ['${reportData.preP}', '${reportData.ccP}', '${reportData.smsP}', '${reportData.incompleteP}'],
		         backgroundColor: [
		             chartColors.blue,
		             chartColors.green,
		             chartColors.yellow,
		             chartColors.red,
		         ] 
		     }]
	};
	
	var gdata = {
		     labels: [ 
		              '<fmt:message key="qtype.cc" />',
		              '<fmt:message key="qtype.sms" />', 
		              '<fmt:message key="qtype.incomplete" />' ],
		     datasets: [
		           {
				 data: [ '${reportData.ccP}', '${reportData.smsP}', '${reportData.incompleteP}'],
		         backgroundColor: [
		             chartColors.green,
		             chartColors.yellow,
		             chartColors.red
// 		             chartColors.blue
		         ] 
		     }]
	};
	
	var preFlg = '${reportData.activity.preFlg}';
	if("0"==preFlg){
		 $(".c-pre").hide();
		 predata = gdata;
	}

	buildGraph(predata);
	
} );
</script>


 