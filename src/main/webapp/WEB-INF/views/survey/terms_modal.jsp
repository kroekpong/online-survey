<%@ page isELIgnored = "false" %>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<style>

.body-tc {
   padding: 15px;
   height: 52vh;
    overflow-y: auto;
}
 

</style>
	 
	<!-- Modal -->
<div class="modal fade" id="termsModal" tabindex="-1" role="dialog" aria-labelledby="termsModalLabel"
  aria-hidden="true" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog" role="document" >
    <div class="modal-content" >
      <div class="modal-header">
        <h4 class="modal-title"><i class="fa  fa-bullhorn"></i> ข้อตกลงและเงื่อนไข </h4>
      </div>

      <div class="modal-body body-tc" >
 			${TC_TEXT}
      </div>  

      <div class="modal-footer ">
               
              <div class="  border-checkbox-section" >
                      
                    <div class="checkbox-fade fade-in-primary">
                          <label>
                              <input type="checkbox" id="conchk">
                              <span class="cr">
                                  <i class="cr-icon icofont icofont-ui-check txt-primary"></i>
                              </span>
                              <span> ยอมรับข้อตกลงและเงื่อนไข </span>
                          </label>
                      </div>
                                                                    
          </div>
            <button  id="conbtn" type="button" class="btn btn-primary disabled" disabled > ยอมรับ  </button>
      </div>

    </div>
  </div>
</div>
 

<script>
  $(document).ready(function(){
        var TC_SHOW  = '${TC_SHOW}';
//         console.log('> '+TC_SHOW);
      
        if('Y' == TC_SHOW && $checkFlg){
          $('#termsModal').modal('show');  // TC
        }
  });
  
//   console.log('>checkFlg  '+$checkFlg);
  
  $('#conchk').change(function() {
      var chk = $(this).is(':checked');
      // console.log(chk);
      $("#conbtn").prop('disabled', !chk);
      if(chk){
      	$("#conbtn").removeClass("disabled");
      }else{
      	$("#conbtn").addClass("disabled");
      }
  });   

  $('#conbtn').click(function(){
      $('#termsModal').modal('hide'); 
      
      
  });
  
</script>

	
	
	