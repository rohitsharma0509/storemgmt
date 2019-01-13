<%@page import="com.app.myproject.constants.Constants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp">
	<jsp:param name="selectedNav" value="Stock" />
</jsp:include>
<div class="row">
	<div class="col-sm-10" style="height: 40px;"><spring:message code="Stock" text="Stock" /></div>
</div>
<div class="row main-row">
	<div class="col-sm-3">
			<form method="GET" class="form-horizontal" action="stock">
			<div class="card">
				<div class="card-body main-center">
					<div class="row">
						<div class="col-sm-12">
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
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="brandName"><spring:message code="Brand Name" text="Brand Name" /></label>
							<input type="text" id="brandName" name="brandName" value="${param.brandName}" class="form-control input-sm" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="productName"><spring:message code="Product Name" text="Product Name" /></label>
							<input type="text" id="productName" name="productName" value="${param.productName}" class="form-control input-sm" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12"><input type="submit" value="<spring:message code="Search" text="Search" />" style="margin-top: 15px;" class="btn btn-sm btn-info form-control"></div>
					</div>
				</div>
			</div>
			</form>
	</div>
	<div class="col-sm-9">
		<c:choose>
			<c:when test="${page.getContent().size() > 0}">
				<div class="row">
					<div class="col-sm-12">
						<table class="table content-table">
							<tr>
								<th><spring:message code="Category Name" text="Category Name" /></th>
								<th><spring:message code="Brand Name" text="Brand Name" /></th>
								<th><spring:message code="Product Name" text="Product Name" /></th>
								<th><spring:message code="Total Qty" text="Total Qty" /></th>
								<th><spring:message code="Ordered Qty" text="Ordered Qty" /></th>
								<th><spring:message code="Available Qty" text="Available Qty" /></th>
							</tr>
							<c:forEach var="stock" items="${page.getContent()}">
								<tr>
									<td>${stock.categoryName}</td>
									<td>${stock.brandName}</td>
									<td>${stock.name}</td>
									<td>${stock.totalQty}</td>
									<td>${stock.orderedQty}</td>
									<td>${stock.availableQty}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-1"><a class="btn btn-info btn-sm" href="${contextPath}/excel?reportName=<%=Constants.STOCK%>"><spring:message code="Export as Excel" text="Export as Excel" /></a></div>
					<c:choose>
						<c:when test="${page.getTotalPages() > 1}">
							<div class="col-sm-11">${pagging}</div>
						</c:when>
					</c:choose>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row">
					<div class="col-sm-12"><spring:message code="No Records Found" text="No Records Found" />.</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>