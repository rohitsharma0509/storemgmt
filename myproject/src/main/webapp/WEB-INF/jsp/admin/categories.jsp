<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="../header.jsp"></jsp:include>
<div class="row panel">
	<div class="col-sm-12" style="height: 40px;"><b><a href="${contextPath}/admin">Admin</a></b> > Categories</div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row">
	<div class="col-sm-12">
		<form method="GET" class="form-horizontal" action="<%=RequestUrls.CATEGORIES%>">
			<div class="card">
				<div class="card-body main-center">
					<div class="row">
						<div class="col-sm-3">
							<label for="categoryName"><spring:message code="Category Name" text="Category Name" /></label>
							<input type="text" id="name" name="name" value="${param.name}" class="form-control input-sm" />
						</div>
						<div class="col-sm-9"></div>
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
		<a class="btn btn-sm btn-info" href="#" rel="moreActions" data-popover-content="#moreActionContent" data-toggle="popover" >More Actions&nbsp;&nbsp;<i class="fa fa-caret-right" aria-hidden="true" ></i></a>
	</div>
</div>
<div id="moreActionContent" class="d-none">
	<ul class="list-group list-group-flush">
	  <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.ADD_CATEGORY %>"><spring:message code="Add Category" text="Add Category" /></a></li>
	  <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.ADD_CATEGORY %>"><spring:message code="Modify" text="Delete" /></a></li>
	  <li class="list-group-item list-group-item-action"><a href="${contextPath}<%=RequestUrls.ADD_CATEGORY %>"><spring:message code="Delete" text="Delete All" /></a></li>
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
						<th>Category Name</th>
						<th>Action</th>
					</tr>
					<c:forEach var="category" items="${page.getContent()}">
						<tr>
							<td><input class="checkbox" type="checkbox" name="ids" value="${category.id}" /></td>
							<td>${category.name}</td>
							<td><a href="${contextPath}<%=RequestUrls.ADD_CATEGORY %>?id=${category.id}"><i class="fa fa-edit" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;
								<a onclick="callAjaxForDelete('${contextPath}<%=RequestUrls.CATEGORIES %>/${category.id}')"><i class="fa fa-trash" aria-hidden="true"></i></a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="row card norecord">
			<div class="col-sm-12">No Records Found.</div>
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