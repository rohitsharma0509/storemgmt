<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<jsp:include page="header.jsp"></jsp:include>
<style>
</style>
<%
String action = "Save";
%>
<div class="row panel">
	<c:choose>
	    <c:when test="${empty supplier.id}">
	        <div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > <b><a href="${contextPath}/suppliers">Suppliers</a></b> > Add Supplier</div>
	    </c:when>
	    <c:otherwise>
	        <div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > <b><a href="${contextPath}/suppliers">Suppliers</a></b> > Edit Supplier</div>
	        <% 
	        action = "Edit";
	        %>
	    </c:otherwise>
	</c:choose>
</div>
<div class="row" style="height:10px;">
</div>
<form:form method="POST" modelAttribute="supplier" commandName="supplier" class="form-horizontal" action="suppliers">
<form:hidden path="id"  class="form-control input-sm"/>
<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-1"></div>
		<div class="col-sm-10">
			<div class="well">
				<div class="row form-group-row">
					<div class="col-sm-1"></div>
					<div class="col-sm-5">
						<label for="firstName">First Name</label>
            			<form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
            		</div>
            		<div class="col-sm-5">
						<label for="lastName">Last Name</label>
            			<form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
            		</div>
            		<div class="col-sm-1"></div>
				</div>
				<div class="row form-group-row">
					<div class="col-sm-1"></div>
					<div class="col-sm-5">
						<label for="exampleInputEmail1">Company Name</label>
            			<form:input type="text" path="companyName" id="companyName" class="form-control input-sm"/>
            		</div>
					<div class="col-sm-5">
						<label for="exampleInputEmail1">Email</label>
						<form:input type="text" path="email" id="email" class="form-control input-sm"/>
            		</div>
            		<div class="col-sm-1"></div>
				</div>
				<div class="row form-group-row">
					<div class="col-sm-1"></div>
					<div class="col-sm-5">
						<label for="exampleInputEmail1">Mobile</label>
						<form:input type="text" path="mobile" id="mobile" class="form-control input-sm"/>
            		</div>
            		<div class="col-sm-5">
            		</div>
            		<div class="col-sm-1"></div>
				</div>
				<div class="row form-group-row">
					<div class="col-sm-1"></div>
					<div class="col-sm-11">
            			<button type="submit" class="btn btn-info"><%=action %></button>
            		</div>
        		</div>
			</div>
		</div>
		<div class="col-sm-1"></div>
	</div>
</div>
</form:form>
<jsp:include page="footer.jsp"></jsp:include>
