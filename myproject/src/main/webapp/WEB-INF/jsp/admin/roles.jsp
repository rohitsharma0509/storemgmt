<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="../header.jsp"></jsp:include>
<div class="row panel">
	<div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a></b> > <spring:message code="Roles" text="Roles" /></div>
</div>
<div class="row" style="height:10px;"></div>
<div class="row">
	<div class="col-sm-12">
		<form method="GET" class="form-horizontal" action="<%=RequestUrls.ROLES%>">
			<div class="card">
				<div class="card-body main-center">
					<div class="row">
						<div class="col-sm-2">
							<label for="name"><spring:message code="User Name" text="User Name" /></label>
							<input type="text" id="name" name="name" value="${param.name}" class="form-control input-sm" />
						</div>
						<div class="col-sm-1"></div>
						<div class="col-sm-2">
							<label for="roleName"><spring:message code="Role Name" text="Role Name" /></label> 
							<input type="text" id="roleName" name="roleName" value="${param.roleName}" class="form-control input-sm" />
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
	  <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.ADD_ROLE %>"><spring:message code="Add Role" text="Add Role" /></a></li>
	  <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.ROLES %>"><spring:message code="Delete" text="Delete All" /></a></li>
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
						<th><spring:message code="User Assigned" text="User Assigned" /></th>
						<th><spring:message code="Action" text="Action" /></th>
					</tr>
					<c:forEach var="role" items="${page.getContent()}">
						<tr>
							<td><input class="checkbox" type="checkbox" name="ids" value="${role.id}" /></td>
							<td>${role.name}</td>
				   			<td>${fn:length(role.users)}</td>
							<td><a href="${contextPath}<%=RequestUrls.ADD_ROLE %>?id=${role.id}"><i class="fa fa-edit" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;
								<a href="#" onclick="callAjaxForDelete('${contextPath}<%=RequestUrls.ROLES %>/${role.id}')"><i class="fa fa-trash" aria-hidden="true"></i></a>
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
