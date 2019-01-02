<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp">
	<jsp:param name="selectedNav" value="Products" />
</jsp:include>
<script>
var pageId = 1;
var totalRecords = ${page.totalRecords};

$(window).scroll(function () {
	var scrollData;
	if(totalRecords > 12*pageId) {
		scrollData="yes";
	}else{
		scrollData="no";
	}
	if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
		if(scrollData=="yes"){
			$('#loadingData1').show();
			setTimeout(function() {
			$('#loadingData1').hide();
			getProductList();
		},500);
	}
}});
function getProductList(){
 	var count = totalRecords;
 	var totalPages = count/12;
 	if(count%12 !=0) {
 		totalPages=totalPages+1;
 	}
 	pageId = pageId+1;
 	if(pageId <= totalPages) {
	   	$.ajax({    
			type: 'GET',
	        url: "/products/ajax",
	        data: {"page":pageId},
	        success: function(data){
	        	console.log(data);
	        	$('#contentDiv').append(data);
	        }
	    });  		
	}
}
</script>

<div class="row">
	<div class="col-sm-12" style="height: 40px;"><spring:message code="All Products" text="All Products" />
		<div class="pull-right">
			<a href="${contextPath}/shoppingCart"><i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;<spring:message code="Shopping cart" text="Shopping cart" /></a>
		</div>
	</div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row">
	<div class="col-sm-12">
	<form method="GET" class="form-horizontal" action="<%=RequestUrls.PRODUCT_ALL %>">
		<div class="card">
			<div class="card-body main-center">
			<div class="row">
				<div class="col-sm-2">
					<label for="category" class="control-label text-right"><spring:message code="Category" text="Category" /> </label>
					<select id="categoryId" name="categoryId" class="form-control input-sm">
					 	<option value="-1"><spring:message code="Select Category" text="Select Category" /></option>
			        	<c:forEach var="category" items="${categories}">
			        		<c:choose>
							<c:when test="${param.categoryId == category.id}">
		    	    			<option selected value="${category.id}">${category.name}</option>
		    	    		</c:when>
		    	    		<c:otherwise>
		    	    			<option value="${category.id}">${category.name}</option>
		    	    		</c:otherwise>
		    	    		</c:choose>
		    	    	</c:forEach>
			    	</select>
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-2">
					<label for="brandName"><spring:message code="Brand Name" text="Brand Name" /></label>
					<input type="text" id="brandName" name="brandName" value="${param.brandName}" class="form-control input-sm" />
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-2">
					<label for="productName"><spring:message code="Product Name" text="Product Name" /></label>
					<input type="text" id="productName" name="productName" value="${param.productName}" class="form-control input-sm" />
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-2">
					<label for="productName"><spring:message code="Price" text="Price" /></label>
					<input type="text" id="price" name="price" value="${param.price}" class="form-control input-sm" />
				</div>
				<div class="col-sm-1"></div>
			</div>
			<div class="row">
				<div class="col-sm-1">
					<input type="submit" value="<spring:message code="Search" text="Search" />" style="margin-top: 15px;" class="btn btn-sm btn-success form-control">
				</div>
				<div class="col-sm-11"></div>
			</div>
			</div>
		</div>
		</form>
	</div>
</div>
<div class="row" style="height: 20px;"></div>
<div class="row">
	<div class="col-sm-12" id="contentDiv">
		<jsp:include page="productListing.jsp">
			<jsp:param name="page" value="${page}" />
		</jsp:include>
		<div class="row" id="loadingData1" style="display:none; width:100%;"><img src = '${contextPath}/resources/images/loading-Image.gif'></div>
	</div>
</div>
<div class="row" style="height: 50px;"></div>
<jsp:include page="footer.jsp"></jsp:include>