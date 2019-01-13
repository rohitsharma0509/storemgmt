<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp">
	<jsp:param name="selectedNav" value="Products" />
</jsp:include>
<link href="${contextPath}/resources/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="${contextPath}/resources/js/bootstrap-toggle.min.js"></script>

<style>
.toggle-handle {
	background-color: #b3cbb8;  
}
</style>
<script>
	$(function() {
	    $('#isCustomerExist').change(function() {
	    	if($(this).prop('checked')){
	    		$(".autofill").hide();
	    	} else {
	    		$(".autofill").show();
	    	}
	    });
  	});
	$('#mobile').autocomplete({
	    serviceUrl: '/customers/search',
	    transformResult: function(customerDtos) {
	        return {
	            suggestions: $.map(customerDtos, function(customerDto) {
	                return { value: customerDto.mobile, data: customerDto.name };
	            })
	        };
	    },
	    onSelect: function (suggestion) {
	        alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
	    }
	});
</script>
<div class="row panel">
	<div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/products"><spring:message code="Products" text="Products" /></a></b> >
	<%
	String  action = "buy";
	if(null != request.getParameter("id")){
		action = action + "?productId="+request.getParameter("id");
	%> 
		<spring:message code="Customer Detail" text="Customer Detail" />
	<%}else{%>
		<b><a href="${contextPath}/shoppingCart"><spring:message code="Shopping Cart" text="Shopping Cart" /></a></b> > <spring:message code="Customer Detail" text="Customer Detail" />
	<%} %>
	</div>
</div>
<div class="row" style="height:10px;"></div>
<form:form method="POST" modelAttribute="customerDto" class="form-horizontal" enctype="multipart/form-data" action="<%=action %>">
<div class="row">
	<div class="col-sm-6">
		<div class="card">
			<div class="card-header bg-dark text-light">
	    		<i class="fa fa-user" aria-hidden="true"></i>&nbsp;<spring:message code="Customer" text="Customer" />
	   			<div class="clearfix"></div>
	   		</div>
			<div class="card-body main-center">
				<div class="row">
					<div class="col-sm-6">
		    			<label for="isCustomerExist" class="control-label text-right"><spring:message code="Are you existing customer ?" text="Are you existing customer ?" /></label>
		    		</div>
		    		<div class="col-sm-6">
		    			<input type="checkbox" id="isCustomerExist" data-toggle="toggle" data-onstyle="success" data-offstyle="info" data-height="15" data-on="<spring:message code="YES" text="YES" />" data-off="<spring:message code="NO" text="NO" />">
		    		</div>
		    	</div>
		    	<hr>
				<div class="row">
					<div class="col-sm-6">
						<label for="mobile"><spring:message code="Mobile" text="Mobile" /></label>
						<form:input type="text" path="mobile" id="mobile" class="form-control input-sm"/>
            		</div>
					<div class="col-sm-6 autofill">
						<label for="name"><spring:message code="Name" text="Name" /></label>
            			<form:input type="text" path="name" class="form-control input-sm"/>
            		</div>
				</div>
				<div class="row autofill">
            		<div class="col-sm-6">
						<label for="addressLine1"><spring:message code="Address Line1" text="Address Line1" /></label>
            			<form:input type="text" path="addressLine1" class="form-control input-sm"/>
            		</div>
					<div class="col-sm-6">
						<label for="addressLine2"><spring:message code="Address Line2" text="Address Line2" /></label>
            			<form:input type="text" path="addressLine2" class="form-control input-sm"/>
            		</div>
				</div>
				<div class="row autofill">
					<div class="col-sm-6">
						<label for="city"><spring:message code="City" text="City" /></label>
						<form:input type="city" path="city" class="form-control input-sm"/>
            		</div>
            		<div class="col-sm-6">
						<label for="state"><spring:message code="State" text="State" /></label>
            			<form:input type="state" path="state" class="form-control input-sm"/>
            		</div>
				</div>
				<div class="row autofill">
					<div class="col-sm-6">
						<label for="pincode"><spring:message code="Pincode" text="Pincode" /></label>
						<form:input type="pincode" path="pincode" class="form-control input-sm"/>
            		</div>
            		<div class="col-sm-6">
						<label for="email"><spring:message code="Email" text="Email" /></label>
            			<form:input type="text" path="email" class="form-control input-sm"/>
            		</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-6">
	<div class="card">
		<div class="card-header bg-dark text-light">
	    	<i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;<spring:message code="Items" text="Items" />
	   		<div class="clearfix"></div>
	   </div>
       <div class="card-body">
       		<c:choose>
			<c:when test="${productDtos.size() > 0}">	
       		<c:forEach var="product" items="${productDtos}" varStatus="loop">
		   		<div class="row">
			   		<div class="col-sm-4 text-center">
			            <img class="img-responsive" src="http://placehold.it/120x80" alt="prewiew" width="120" height="80">
			        </div>
			        <div class="col-sm-3">
			            <h5><strong>${product.name}</strong></h5>
			            <h6>
			                <small>${product.code}</small>
			            </h6>
			        </div>
			        <div class="col-sm-5 text-md-right">
			        	<h6>${product.perProductPrice} <span class="text-muted">x</span> ${product.quantity} = <strong>${ product.perProductPrice*product.quantity }</strong></h6>
			        </div>
		    	</div>
	        	<hr>
        		</c:forEach>
	        	<div class="row">
			   		<div class="col-sm-7 text-center">
			         
			        </div>
			        <div class="col-sm-5 text-md-right">
			        	<h6><spring:message code="Total" text="Total" /> : <strong>${totalPrice}</strong></h6>
			        </div>
		    	</div>
        	</c:when>
			<c:otherwise>
				<spring:message code="There is no items in cart" text="There is no items in cart" />.
			</c:otherwise>
			</c:choose>
     	</div>
     	<c:if test="${productDtos.size() > 0}">
     	<div class="card-footer">
	         <div class="row">
	        	<div class="col-sm-9 text-center"></div>
	        	<div class="col-sm-3 text-center"><button type="submit" class="btn btn-sm btn-success"> <spring:message code="Buy Now" text="Buy Now" /></button></div>
			</div>
    	</div>
    	</c:if>
     	
	</div>
	</div>
 </div>
 </form:form>