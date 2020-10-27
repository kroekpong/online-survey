<%@ page isELIgnored = "false" %>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


 <div class="label-main ">
      <label class="label label-primary qgroup"> 
      <i class="fa fa-chevron-circle-right"></i> Customer Information</label>
  </div>
  
  <div class="row">
		<div class="col-sm-12">
			 <div class="card">
                      <div class="card-block"  >
					 
					 <div class="form-group row">
                             <label class="col-sm-2 col-form-label" >Occupation</label>
                             <div class="col-sm-8">
                                 <select  class="form-control"  id="occupation" name="occupation" required> 
												<option value="">${_SEL}</option>
										<c:forEach var="item" items="${DDL_OCCUPATION}">
												<option ${item.code==99? 'selected':''}   value="${item.code}" >${item.name}</option>
										</c:forEach>     
								 </select>	
                             </div>
                     </div>
					 <div class="form-group row">
                             <label class="col-sm-2 col-form-label" >Family Income</label>
                             <div class="col-sm-8">
                                 <select  class="form-control"  id="income" name="income" required> 
												<option value="">${_SEL}</option>
										<c:forEach var="item" items="${DDL_INCOME}">
												<option  ${item.code==99? 'selected':''} value="${item.code}" >${item.name}</option>
										</c:forEach>     
								 </select>	
                             </div>
                     </div>
					 <div class="form-group row">
                             <label class="col-sm-2 col-form-label" >Interest</label>
                             <div class="col-sm-8">
                                 <select  class="form-control"  id="interest" name="interest"  required> 
												<option value="">${_SEL}</option>
										<c:forEach var="item" items="${DDL_HOBBY}">
												<option ${item.code==99? 'selected':''}  value="${item.code}" >${item.name}</option>
										</c:forEach>     
								 </select>	
                             </div>
                     </div>
					 <div class="form-group row">
                             <label class="col-sm-2 col-form-label" >Expectation</label>
                             <div class="col-sm-8">
                             
                             	<textarea rows="5" cols="5" class="form-control freeTxt" id="expectation"></textarea>
									
                               
                             </div>
                     </div>
					                                      
					                                      

				</div>
             </div>
		</div>
	</div>

	