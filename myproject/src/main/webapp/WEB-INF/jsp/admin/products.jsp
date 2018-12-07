<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../header.jsp"></jsp:include>
<div class="row panel">
	<div class="col-sm-12" style="height: 40px;">
		<b><a href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a></b> > <spring:message code="Products" text="Products" />
	</div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row main-row">
	<div class="col-sm-3">
		<form method="GET" class="form-horizontal" action="<%=RequestUrls.PRODUCTS %>">
			<div class="card">
				<div class="card-body main-center">
					<div class="row">
						<div class="col-sm-12">
							<label for="category" class="control-label text-right"><spring:message code="Category" text="Category" /></label> 
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
							<label for="brandName"><spring:message code="Brand Name" text="Brand Name" /></label> <input type="text"
								id="brandName" name="brandName" value="${param.brandName}"
								class="form-control input-sm" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="productName"><spring:message code="Product Name" text="Product Name" /></label> 
							<input type="text" id="productName" name="productName" value="${param.productName}" class="form-control input-sm" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="status"><spring:message code="Status" text="Status" /></label> 
							<select id="statusId" name="statusId" class="form-control input-sm">
								<option value="-1"><spring:message code="Select Status" text="Select Status" /></option>
								<c:forEach var="status" items="${statuses}">
									<c:choose>
										<c:when test="${param.statusId == status.key}">
											<option selected value="${status.key}">${status.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${status.key}">${status.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<input type="submit" value="Search" style="margin-top: 15px;" class="btn btn-sm btn-success form-control">
						</div>
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
								<th><spring:message code="Code" text="Code" /></th>
								<th><spring:message code="Name" text="Name" /></th>
								<th><spring:message code="Quantity" text="Quantity" /></th>
								<th><spring:message code="Price" text="Price" /></th>
								<th><spring:message code="Alert Quantity" text="Alert Quantity" /></th>
								<th><spring:message code="Action" text="Action" /></th>
							</tr>
							<c:forEach var="product" items="${page.getContent()}">
								<tr>
									<td>${product.code}</td>
									<td>${product.name}</td>
									<td>${product.quantity}</td>
									<td>${product.perProductPrice}</td>
									<td>${product.alertQuantity}</td>
									<td>
										<a href="${contextPath}<%=RequestUrls.ADD_PRODUCT %>?id=${product.id}"><i class="fa fa-edit" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;
										<a href="#" onclick="callAjaxForDelete('${contextPath}<%=RequestUrls.PRODUCTS %>/${product.id}')"><i class="fa fa-trash" aria-hidden="true"></i></a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row card norecord">
					<div class="col-sm-12" ><spring:message code="No Records Found" text="No Records Found" />.</div>
				</div>
			</c:otherwise>
		</c:choose>
		<div class="row">
			<div class="col-sm-4">
				<a class="btn btn-success btn-sm" href="${contextPath}<%=RequestUrls.ADD_PRODUCT %>"><spring:message code="Add Product" text="Add Product" /></a>
				<a class="btn btn-success btn-sm" href="${contextPath}<%=RequestUrls.PRODUCTS_IMPORT %>"><spring:message code="Import CSV/XML" text="Import CSV/XML" /></a>
			</div>
			<c:choose>
				<c:when test="${page.getTotalPages() > 1}">
					<div class="col-sm-8">${pagging}</div>
				</c:when>
				<c:otherwise>
					<div class="col-sm-8"></div>
				</c:otherwise>
			</c:choose>
		</div>

	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>