<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<jsp:include page="../header.jsp"></jsp:include>
<%
String action = "Save";
%>
<div class="row panel">
	<c:choose>
	    <c:when test="${empty productCategory.id}">
	        <div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > <b><a href="${contextPath}<%=RequestUrls.ROLES %>">Roles</a></b> > Add Role</div>
	    </c:when>
	    <c:otherwise>
	        <div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > <b><a href="${contextPath}<%=RequestUrls.ROLES %>">Roles</a></b> > Edit Role</div>
	        <% 
	        action = "Edit";
	        %>
	    </c:otherwise>
	</c:choose>
</div>
<div class="row" style="height:10px;">
</div>
<form:form method="POST" modelAttribute="role" class="form-horizontal" action="<%=RequestUrls.ROLES %>">
<form:hidden path="id"  class="form-control input-sm"/>
<div class="card">
  <div class="card-body">
    <div class="form-group row">
      <label for="name" class="col-sm-1"></label>
      <label for="name" class="col-sm-2 col-form-label">Role Name&nbsp;<span class="urgent_fields">*</span></label>
      <div class="col-sm-3">
            <form:input type="text" path="name" id="name" class="form-control"/>
            <form:errors path="name" class="help-inline has-error"></form:errors>
      </div>
      <label for="name" class="col-sm-6"></label>
    </div>
    <div class="form-group row">
    	<label for="name" class="col-sm-1"></label>
        <button type="submit" class="col-sm-1 btn btn-info"><%=action %></button>
    </div>
</div>
</div>
</form:form>
<div class="row" style="height: 100px;"></div>
<jsp:include page="../footer.jsp"></jsp:include>