<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp">
	<jsp:param name="selectedNav" value="Orders" />
</jsp:include>
<div class="row">
	<div class="col-sm-12" style="height: 40px;"><spring:message code="Order Detail" text="Order Detail" /></div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="container">
	<div class="card">
		<div class="card-header">
			<spring:message code="Order Number" text="Order Number" />: <strong>${orderDto.orderNumber}</strong>
		</div>
		<div class="card-body">
			<div class="row mb-4">
				<div class="col-sm-6">
					<div>
						<strong>${orderDto.customerDto.name}</strong>
					</div>
					<div>${orderDto.customerDto.addressLine1},</div>
					<div>${orderDto.customerDto.addressLine2}</div>
					<div>${orderDto.customerDto.city}, ${orderDto.customerDto.state}, ${orderDto.customerDto.pincode}</div>
					<div><strong><spring:message code="Email" text="Email" />:</strong> ${orderDto.customerDto.email}</div>
					<div><strong><spring:message code="Mobile" text="Mobile" />:</strong> ${orderDto.customerDto.mobile}</div>
				</div>
				<div class="col-sm-6">
					<div style="float:right"><strong><spring:message code="Order Date" text="Order Date" />:</strong> ${orderDto.orderDate}</div>
				</div>
			</div>
			<div class="table-responsive-sm">
				<table class="table content-table">
					<thead>
						<tr>
							<th class="center">#</th>
							<th><spring:message code="Item" text="Item" /></th>
							<th><spring:message code="Description" text="Description" /></th>
							<th class="right"><spring:message code="Unit Cost" text="Unit Cost" /></th>
							<th class="center"><spring:message code="Qty" text="Qty" /></th>
							<th class="right"><spring:message code="Total" text="Total" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${orderDto.productDtos}" varStatus="loop">
						<tr>
							<td class="center">${loop.index + 1}</td>
							<td class="left strong">${product.name}</td>
							<td class="left">${product.code}</td>
							<td class="right">${product.perProductPrice}</td>
							<td class="center">${product.quantity}</td>
							<td class="right">${product.perProductPrice * product.quantity}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="row">
				<div class="col-sm-4 col-sm-5"></div>
				<div class="col-sm-4 col-sm-5 ml-auto">
					<table class="table table-clear">
						<tbody>
							<tr>
								<td class="left"><strong><spring:message code="Total" text="Total" /></strong></td>
								<td class="right"><strong>${orderDto.totalAmount}</strong></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="card-footer">
			<div class="row">
	        	<div class="col-sm-10 text-center"></div>
	        	<div class="col-sm-2 text-center"><a href="${contextPath}/orders/download/${orderDto.id}" class="btn btn-sm btn-success"> <spring:message code="Download" text="Download" /></a></div>
	        </div>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>