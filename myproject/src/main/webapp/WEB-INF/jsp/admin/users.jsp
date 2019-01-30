<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="../header.jsp"></jsp:include>
<div class="row panel">
	<div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a></b> > <spring:message code="Users" text="Users" /></div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row">
	<div class="col-sm-12">
		<form method="GET" class="form-horizontal" action="<%=RequestUrls.USERS%>">
			<div class="card">
				<div class="card-body main-center">
					<div class="row">
						<div class="col-sm-2">
							<label for="name"><spring:message code="Name" text="Name" /></label>
							<input type="text" id="name" name="name" value="${param.name}" class="form-control input-sm" />
						</div>
						<div class="col-sm-1"></div>
						<div class="col-sm-2">
							<label for="email"><spring:message code="Email" text="Email" /></label> 
							<input type="text" id="email" name="email" value="${param.email}" class="form-control input-sm" />
						</div>
						<div class="col-sm-7"></div>
					</div>
					<div class="row">
						<div class="col-sm-1">
							<input type="submit" value="<spring:message code="Search" text="Search" />" style="margin-top: 15px;" class="btn btn-sm btn-info form-control">
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
	<div class="col-sm-11">
		<a class="btn btn-sm btn-info" href="#" rel="moreActions" data-popover-content="#moreActionContent" data-toggle="popover" ><spring:message code="More Actions" text="More Actions" />&nbsp;&nbsp;<i class="fa fa-caret-right" aria-hidden="true" ></i></a>
	</div>
</div>
<div id="moreActionContent" class="d-none">
	<ul class="list-group list-group-flush">
	  <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.USERS %>"><spring:message code="Modify" text="Modify" /></a></li>
	  <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.USERS %>"><spring:message code="Delete" text="Delete All" /></a></li>
	</ul>
</div>
<div class="row" style="height: 10px;"></div>
<c:choose>
	<c:when test="${page.getContent().size() > 0}">
		<div class="row">
			<div class="col-sm-12">
				<table class="table content-table">
					<tr>
						<th><input type="checkbox" name="ids" id="all" /></th>
						<th><spring:message code="Name" text="Name" /></th>
						<th><spring:message code="Email" text="Email" /></th>
						<th><spring:message code="Mobile" text="Mobile" /></th>
						<th><spring:message code="Action" text="Action" /></th>
					</tr>
					<c:forEach var="user" items="${page.getContent()}">
						<tr>
							<td><input class="checkbox" type="checkbox" name="ids" value="${user.id}" /></td>
							<td>${user.firstName} ${user.lastName}</td>
				   			<td>${user.mobile}</td>
				   			<td>${user.email}</td>
							<td><a href="${contextPath}<%=RequestUrls.USERS %>?id=${user.id}"><i class="fa fa-edit" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;
								<a href="#" onclick="callAjaxForDelete('${contextPath}<%=RequestUrls.USERS %>/${user.id}')"><i class="fa fa-trash" aria-hidden="true"></i></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="row card norecord">
			<div class="col-sm-12"><spring:message code="No Records Found" text="No Records Found" />.</div>
		</div>
	</c:otherwise>
</c:choose>
	<c:choose>
		<c:when test="${page.getTotalPages() > 1}">
			<div class="row">
				<div class="col-sm-12">${pagging}</div>
			</div>
		</c:when>
	</c:choose>
<div class="row" style="height: 100px;"></div>
<jsp:include page="../footer.jsp"></jsp:include>