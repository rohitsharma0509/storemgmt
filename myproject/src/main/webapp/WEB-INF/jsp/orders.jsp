<%@page import="com.app.myproject.constants.Constants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp">
	<jsp:param name="selectedNav" value="Orders" />
</jsp:include>
<div class="row">
	<div class="col-sm-12" style="height: 40px;"><spring:message code="Orders" text="Orders" /></div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row main-row">
	<div class="col-sm-3">
		<form:form method="GET" modelAttribute="order" class="form-horizontal" action="orders">
			<div class="card">
				<div class="card-body main-center">
					<div class="row">
						<div class="col-sm-12">
							<label for="orderNumber" class="control-label text-right"><spring:message code="Order Number" text="Order Number" /></label>
							<form:input type="text" path="orderNumber" id="orderNumber" class="form-control input-sm" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="from"><spring:message code="From" text="From" /></label>
							<input type="date" id="from" class="form-control input-sm" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="to"><spring:message code="To" text="To" /></label>
							<input type="date" id="to" class="form-control input-sm" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<button type="submit" style="margin-top: 15px;" class="btn btn-sm btn-info form-control"><spring:message code="Search" text="Search" /></button>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<div class="col-sm-9">
		<c:choose>
			<c:when test="${page.getContent().size() > 0}">
				<div class="row">
					<div class="col-sm-12">
						<table class="table content-table">
							<tr>
								<th><spring:message code="Order Number" text="Order Number" /></th>
								<th><spring:message code="Order Date" text="Order Date" /></th>
								<th><spring:message code="Amount" text="Amount" /></th>
							</tr>
							<c:forEach var="order" items="${page.getContent()}"
								varStatus="loop">
								<tr>
									<!-- <td><a onclick="openModal('${contextPath}/orders/${order.id}')" href="#">${order.orderNumber}</a></td> -->
									<td><a href="${contextPath}/orders/${order.id}">${order.orderNumber}</a></td>
									<td>${order.orderDate}</td>
									<td>${order.totalAmount}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-1"><a class="btn btn-info btn-sm" href="${contextPath}/excel?reportName=<%=Constants.ORDERS%>"><spring:message code="Export as Excel" text="Export as Excel" /></a></div>
					<c:choose>
						<c:when test="${page.getTotalPages() > 1}">
							<div class="col-sm-11">${pagging}</div>
						</c:when>
					</c:choose>
				</div>
			</c:when>
			<c:otherwise><spring:message code="No Records Found" text="No Records Found" />.</c:otherwise>
		</c:choose>
	</div>
</div>
<div class="row">
	<div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog">
	        <!-- Modal content-->
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title">Order details</h4>
	                <button type="button" class="close" data-dismiss="modal">&times;</button>
	            </div>
	            <div class="modal-body"></div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	            </div>
	        </div>
	    </div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>