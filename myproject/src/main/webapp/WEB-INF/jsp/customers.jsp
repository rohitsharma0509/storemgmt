<%@page import="com.app.myproject.constants.Constants"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp">
	<jsp:param name="selectedNav" value="Customers" />
</jsp:include>
<div class="row panel">
	<div class="col-sm-12" style="height: 40px;"><spring:message code="Customers" text="Customers" /></div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row main-row">
	<div class="col-sm-3">
		<form:form method="GET" modelAttribute="customer"
			class="form-horizontal" action="customers">
			<div class="card">
				<div class="card-body main-center">
					<div class="row">
						<div class="col-sm-12">
							<label for="name" class="control-label text-right"><spring:message code="Name" text="Name" /> </label>
							<form:input type="text" path="name" id="name" class="form-control input-sm" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="mobile"><spring:message code="Mobile" text="Mobile" /></label>
							<form:input type="text" path="mobile" id="mobile" class="form-control input-sm" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="city"><spring:message code="City" text="City" /></label>
							<form:input type="text" path="city" id="city" class="form-control input-sm" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<input type="submit" value="<spring:message code="Search" text="Search" />" style="margin-top: 15px;" class="btn btn-sm btn-info form-control">
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
								<th><spring:message code="Customer Name" text="Customer Name" /></th>
								<th><spring:message code="Mobile" text="Mobile" /></th>
								<th><spring:message code="Email" text="Email" /></th>
								<th><spring:message code="City" text="City" /></th>
								<th><spring:message code="State" text="State" /></th>
							</tr>
							<c:forEach var="customer" items="${page.getContent()}"
								varStatus="loop">
								<tr>
									<td>${customer.name}</td>
									<td>${customer.mobile}</td>
									<td>${customer.email}</td>
									<td>${customer.city}</td>
									<td>${customer.state}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-1"><a class="btn btn-info btn-sm" href="${contextPath}/excel?reportName=<%=Constants.CUSTOMERS%>"><spring:message code="Export as Excel" text="Export as Excel" /></a></div>
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
<jsp:include page="footer.jsp"></jsp:include>