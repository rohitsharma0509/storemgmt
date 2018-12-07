<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<jsp:include page="../header.jsp"></jsp:include>
<div class="row panel">
	<div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > Email Templates</div>
</div>
<div class="row" style="height:10px;"></div>
<div class="row">
	<div class="col-sm-12">
		<c:choose>
			<c:when test="${page.getContent().size() > 0}">
				<div class="row">
					<div class="col-sm-12">
						<table class="table table-striped table-bordered content-table">
							<tr>
								<th>Code</th>
								<th>Name</th>
								<th>Quantity</th>
								<th>Price</th>
								<th>Alert Quantity</th>
								<th>Action</th>
							</tr>
							<c:forEach var="product" items="${page.getContent()}">
							<tr>  
				   				<td>${product.code}</td>
				   				<td>${product.name}</td>
				   				<td>${product.quantity}</td>
				   				<td>${product.perProductPrice}</td>
				   				<td>${product.alertQuantity}</td>
				   				<td>
				   					<a href="${contextPath}/addEmailTemplate?id=${product.id}"><i class="fa fa-edit" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;
				   					<a href="#" onclick="callAjaxForDelete('${contextPath}/emailTemplates/${product.id}')"><i class="fa fa-trash" aria-hidden="true"></i></a>
				   				</td>
						 	</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<a class="btn btn-info btn-sm" href="${contextPath}/addEmailTemplate">Add Email Template</a>
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
			</c:when>
			<c:otherwise>
				<div class="row">
					<div class="col-sm-12" style="padding-right: 0px;">No Records Found.</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>