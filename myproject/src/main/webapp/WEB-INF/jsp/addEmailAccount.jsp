<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<jsp:include page="header.jsp"></jsp:include>
<%
String action = "Save";
%>
<div class="row panel">
	<c:choose>
	    <c:when test="${empty emailAccount.id}">
	        <div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > Add Email Account</div>
	    </c:when>
	    <c:otherwise>
	        <div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > Edit Email Account</div>
	        <% 
	        action = "Edit";
	        %>
	    </c:otherwise>
	</c:choose>
</div>
<div class="row" style="height:10px;">
</div>
<form:form method="POST" modelAttribute="emailAccount" class="form-horizontal" enctype="multipart/form-data" action="addEmailAccount">
<form:hidden path="id"  class="form-control input-sm"/>
<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-1"></div>
		<div class="col-sm-10">
			<div class="card">
				<div class="card-body">
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
						<div class="col-sm-5">
							<label for="host">Host</label>
							<form:input type="text" path="host" id="host" class="form-control input-sm"/>
	            		</div>
	            		<div class="col-sm-5">
							<label for="port">Port</label>
	            			<form:input type="text" path="port" id="port" class="form-control input-sm"/>
	            		</div>
	            		<div class="col-sm-1"></div>
					</div>
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
	            		<div class="col-sm-5">
							<label for="username">Username</label>
	            			<form:input type="text" path="username" id="username" class="form-control input-sm"/>
	            		</div>
	            		<div class="col-sm-5">
							<label for="password">Password</label>
							<form:input type="password" path="password" id="password" class="form-control input-sm"/>
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
		</div>
		<div class="col-sm-1"></div>
	</div>
</div>
</form:form>
<jsp:include page="footer.jsp"></jsp:include>