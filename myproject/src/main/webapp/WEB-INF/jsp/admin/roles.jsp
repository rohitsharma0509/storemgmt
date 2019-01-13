<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<jsp:include page="../header.jsp"></jsp:include>
<div class="row panel">
	<div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > Roles</div>
</div>
<div class="row" style="height:10px;"></div>
<div class="row main-row">
	<div class="col-sm-3">
			<form method="GET" class="form-horizontal" action="<%=RequestUrls.ROLES %>">
			<div class="card">
				<div class="card-body main-center">
					<div class="row">
						<div class="col-sm-12">
							<label for="brandName">User Name</label>
							<input type="text" id="userName" name="userName" value="${param.userName}" class="form-control input-sm" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<label for="productName">Role Name</label>
							<input type="text" id="roleName" name="roleName" value="${param.roleName}" class="form-control input-sm" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12"><input type="submit" value="Search" style="margin-top: 15px;" class="btn btn-sm btn-info form-control"></div>
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
								<th>Name</th>
								<th>User Assigned</th>
								<th>Action</th>
							</tr>
							<c:forEach var="role" items="${page.getContent()}">
							<tr>  
				   				<td>${role.name}</td>
				   				<td>${fn:length(role.users)}</td>
				   				<td>
				   					<a href="${contextPath}<%=RequestUrls.ROLES %>?id=${role.id}"><i class="fa fa-edit" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;
				   					<a href="#" onclick="callAjaxForDelete('${contextPath}<%=RequestUrls.ROLES %>/${role.id}')"><i class="fa fa-trash" aria-hidden="true"></i></a>
				   				</td>
						 	</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<a class="btn btn-info btn-sm" href="${contextPath}<%=RequestUrls.ADD_ROLE %>">Add Role</a>
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