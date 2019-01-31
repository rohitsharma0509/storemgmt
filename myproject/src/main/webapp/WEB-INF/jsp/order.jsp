<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp">
	<jsp:param name="selectedNav" value="Orders" />
</jsp:include>
<style>
.invoice {
	font-size: 14px;
}
.table thead th {
    background-color: #e9ecef;
}
.table-bordered td, .table-bordered th {
    border: 1px solid #721c24;
}
.table thead th {
    border-bottom: 2px solid #1b1e21;
}
</style>
<div class="row">
	<div class="col-sm-12" style="height: 40px;"><spring:message code="Order Detail" text="Order Detail" /></div>
</div>
<hr>
<div class="row" style="height: 10px;"></div>
<div class="container invoice">
	<div class="row">
		<div class="col-sm-6">
			<spring:message code="Order Number" text="Order Number" />: <strong>${orderDto.orderNumber}</strong>
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
	<div class="row" style="height: 30px;"></div>
	<div class="row">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th class="text-left">#</th>
					<th class="text-center"><spring:message code="Item" text="Item" /></th>
					<th class="text-center"><spring:message code="Description" text="Description" /></th>
					<th class="text-center"><spring:message code="Unit Cost" text="Unit Cost" /></th>
					<th class="text-center"><spring:message code="Qty" text="Qty" /></th>
					<th class="text-right"><spring:message code="Total" text="Total" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${orderDto.productDtos}" varStatus="loop">
				<tr>
					<td class="text-left">${loop.index + 1}</td>
					<td class="text-center">${product.name}</td>
					<td class="text-center">${product.code}</td>
					<td class="text-center">${product.perProductPrice}</td>
					<td class="text-center">${product.quantity}</td>
					<td class="text-right">${product.perProductPrice * product.quantity}</td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
			    	<td class="center"><strong><spring:message code="Subtotal" text="Subtotal" /></strong></td>
			    	<td class="text-right" colspan="5"><strong>${orderDto.totalAmount}</strong></td>
			    </tr>
			    <tr>
			    	<td class="center"><strong><spring:message code="Tax" text="Tax" /></strong></td>
			    	<td class="text-right" colspan="5"><strong>0.0</strong></td>
			    </tr>
			    <tr>
			    	<td class="center"><strong><spring:message code="Total" text="Total" /></strong></td>
			    	<td class="text-right" colspan="5"><strong>${orderDto.totalAmount}</strong></td>
				</tr>
			</tfoot>
		</table>
	</div>
	<div class="row">
		<div class="col-sm-10"></div>
		<div class="col-sm-2" align="right" style="padding-right: 0px;"><a href="${contextPath}/orders/download/${orderDto.id}" class="btn btn-sm btn-info"> <spring:message code="Download" text="Download" /></a></div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>