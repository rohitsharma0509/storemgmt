<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../header.jsp"></jsp:include>
<div class="row panel">
	<div class="col-sm-12" style="height: 40px;"><b><a href="${contextPath}/admin">Admin</a></b> > Categories</div>
</div>
<div class="row" style="height: 10px;"></div>

		<c:choose>
		<c:when test="${page.getContent().size() > 0}">
		<div class="row">
			<div class="col-sm-12">
				<table class="table content-table">
					<tr>
						<th>Category Name</th>
						<th>Action</th>
					</tr>
					<c:forEach var="category" items="${page.getContent()}">
						<tr>
							<td>${category.name}</td>
							<td>
								<a href="${contextPath}<%=RequestUrls.ADD_CATEGORY %>?id=${category.id}"><i class="fa fa-edit" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp; 
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
			<div class="col-sm-12" >No Records Found.</div>
		</div>
		</c:otherwise>
		</c:choose>
		<div class="row">
			<div class="col-sm-1">
				<a class="btn btn-success btn-sm" href="${contextPath}<%=RequestUrls.ADD_CATEGORY %>">Add Category</a>
			</div>
			<c:choose>
				<c:when test="${page.getTotalPages() > 1}">
					<div class="col-sm-11">${pagging}</div>
				</c:when>
			</c:choose>
		</div>
<div class="row" style="height: 100px;"></div>
<jsp:include page="../footer.jsp"></jsp:include>