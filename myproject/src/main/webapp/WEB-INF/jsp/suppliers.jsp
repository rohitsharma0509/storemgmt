<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<jsp:include page="header.jsp"></jsp:include>
<c:url var="firstUrl" value="/suppliers?page=1" />
<c:url var="lastUrl" value="/suppliers?page=${page.totalPages}" />
<c:url var="prevUrl" value="/suppliers?page=${currentIndex - 1}" />
<c:url var="nextUrl" value="/suppliers?page=${currentIndex + 1}" />
<div class="row panel">
	<div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > Suppliers</div>
</div>
<div class="row">
	<div class="col-sm-1"><a class="btn btn-info btn-sm" href="${contextPath}/addSupplier">Add Supplier</a></div>
	<div class="col-sm-11"></div>
</div>
<div class="row" style="height:10px;">
</div>
<c:if test="${page.getContent().size() > 0}">
<div class="row">
	<ul  class="pagination pagination-sm">
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="/suppliers?page=${i}" />
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == page.totalPages}">
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<div class="row panel panel-info">
	<div class="col-sm-12"  style="padding: 0px;">
		<table class="table table-striped">
			<tr class="info">
				<th>Name</th>
				<th>Company Name</th>
				<th>Email</th>
				<th>Mobile</th>
				<th>Action</th>
			</tr> 
			<c:forEach var="supplier" items="${page.getContent()}">
			<tr>  
	   				<td>${supplier.firstName} ${supplier.lastName}</td>
	   				<td>${supplier.companyName}</td>
	   				<td>${supplier.email}</td>
	   				<td>${supplier.mobile}</td>
	   				<td>
	   					<a href="${contextPath}/addSupplier?id=${supplier.id}"><span class="glyphicon glyphicon-edit"></span></a>&nbsp;&nbsp;&nbsp;
	   					<a onclick="callAjaxForDelete('${contextPath}/suppliers/${supplier.id}')"><span class="glyphicon glyphicon-trash"></span></a>
	   				</td>
	 		</tr>  
   			</c:forEach> 
		</table>
	</div>
</div>
</c:if>
<c:if test="${page.getContent().size() <= 0}">
<div class="well">
	No Records Found.
</div>
</c:if>
<jsp:include page="footer.jsp"></jsp:include>