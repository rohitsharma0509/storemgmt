<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.myproject.constants.RequestUrls"%>
<jsp:include page="header.jsp"></jsp:include>
<div class="row panel">
	<div class="col-sm-12" style="height:40px;"><spring:message code="Admin" text="Admin" /></div>
</div>
<div class="row">
	<div class="col-sm-6">
		<table class="table">
			<tr class="info"><th><span class="glyphicon glyphicon-users"><b><spring:message code="Users" text="Users" /></b></span></th></tr>
			<tr><td><a href="${contextPath}<%=RequestUrls.USERS %>"><spring:message code="Users" text="Users" /></a></td></tr>
		</table>
	</div>
	<div class="col-sm-6">
		<table class="table">
			<tr class="info"><th><span class="glyphicon glyphicon-users"><b><spring:message code="Access Control" text="Access Control" /></b></span></th></tr>
			<tr><td><a href="${contextPath}<%=RequestUrls.ROLES %>"><spring:message code="Roles" text="Roles" /></a></td></tr>
		</table>
	</div>
</div>
<div class="row">
	<div class="col-sm-6">
		<table class="table">
			<tr class="info"><th><span class="glyphicon glyphicon-users"><b><spring:message code="Manage Categories & Products" text="Manage Categories & Products" /></b></span></th></tr>
			<tr><td><a href="${contextPath}<%=RequestUrls.CATEGORIES %>"><spring:message code="Category" text="Category" /></a></td></tr>
			<tr><td><a href="${contextPath}<%=RequestUrls.PRODUCTS %>"><spring:message code="Products" text="Products" /></a></td></tr>
		</table>
	</div>
	<div class="col-sm-6">
		<table class="table">
			<tr class="info"><th><span class="glyphicon glyphicon-users"><b><spring:message code="Email" text="Email" /></b></span></th></tr>
			<tr><td><a href="${contextPath}/getEmailAccount"><spring:message code="Add Email Account" text="Add Email Account" /></a></td></tr>
			<tr><td><a href="${contextPath}<%=RequestUrls.EMAIL_TEMPLATES %>"><spring:message code="Manage Email Template" text="Manage Email Template" /></a></td></tr>
			<tr><td><a href="${contextPath}/composeEmail"><spring:message code="Send Email" text="Send Email" /></a></td></tr>
		</table>
	</div>
</div>
<div class="row" style="height: 100px;"></div>
<jsp:include page="footer.jsp"></jsp:include>
