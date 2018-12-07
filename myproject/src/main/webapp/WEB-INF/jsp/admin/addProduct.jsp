<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<jsp:include page="../header.jsp"></jsp:include>
<%
String action = "Save";
%>
<div class="row panel">
	<c:choose>
	    <c:when test="${empty productDto.id}">
	        <div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > <b><a href="${contextPath}<%=RequestUrls.PRODUCTS %>">Products</a></b> > Add Product</div>
	    </c:when>
	    <c:otherwise>
	        <div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > <b><a href="${contextPath}<%=RequestUrls.PRODUCTS %>">Products</a></b> > Edit Product</div>
	        <% 
	        action = "Edit";
	        %>
	    </c:otherwise>
	</c:choose>
</div>
<div class="row" style="height:10px;">
</div>
<form:form method="POST" modelAttribute="productDto" class="form-horizontal" enctype="multipart/form-data" action="<%=RequestUrls.PRODUCTS %>">
<form:hidden path="id"  class="form-control input-sm"/>
<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-1"></div>
		<div class="col-sm-10">
			<div class="card">
				<div class="card-body">
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
						<div class="col-sm-5">
							<label for="firstName">Category&nbsp;<span class="urgent_fields">*</span></label>
							<form:select path="categoryId" id="categoryId" class="form-control input-sm">
					        	<form:option value="-1">Select Category</form:option>
					        	<c:forEach var="category" items="${categories}">
				    	    		<form:option value="${category.id}">${category.name}</form:option>
				    	    	</c:forEach>
					    	</form:select>
					    	<form:errors path="categoryId" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-5">
							<label for="brandName">Brand Name&nbsp;<span class="urgent_fields">*</span></label>
	            			<form:input type="text" path="brandName" id="brandName" class="form-control input-sm"/>
	            			<form:errors path="brandName" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-1"></div>
					</div>
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
	            		<div class="col-sm-5">
							<label for="name">Name&nbsp;<span class="urgent_fields">*</span></label>
	            			<form:input type="text" path="name" id="name" class="form-control input-sm"/>
	            			<form:errors path="name" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-5">
							<label for="image">Image</label>
							<form:input type="file" path="image" id="image" class="form-control input-sm"/>
	            		</div>
	            		<div class="col-sm-1"></div>
					</div>
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
						<div class="col-sm-5">
	            			<label for="purchasePrice">Purchase Price&nbsp;<span class="urgent_fields">*</span></label>
							<form:input type="text" path="purchasePrice" id="purchasePrice" class="form-control input-sm"/>
							<form:errors path="purchasePrice" class="help-inline has-error"></form:errors>
	            		</div>
						<div class="col-sm-5">
	            			<label for="perProductPrice">Sell Price&nbsp;<span class="urgent_fields">*</span></label>
							<form:input type="text" path="perProductPrice" id="perProductPrice" class="form-control input-sm"/>
							<form:errors path="perProductPrice" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-1"></div>
					</div>
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
						<div class="col-sm-5">
							<label for="quantity">Quantity&nbsp;<span class="urgent_fields">*</span></label>
	            			<form:input type="text" path="quantity" id="quantity" class="form-control input-sm"/>
	            			<form:errors path="quantity" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-5">
							<label for="alertQuantity">Alert Quantity&nbsp;<span class="urgent_fields">*</span></label>
	            			<form:input type="text" path="alertQuantity" id="alertQuantity" class="form-control input-sm"/>
	            			<form:errors path="alertQuantity" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-1"></div>
					</div>
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
						<div class="col-sm-11">
	            			<button type="submit" class="btn btn-info"><%=action %></button>
	            		</div>
	        		</div>
				</div>
			</div>
		</div>
		<div class="col-sm-1"></div>
	</div>
</div>
</form:form>
<jsp:include page="../footer.jsp"></jsp:include>
