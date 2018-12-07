<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>My Project</title>
<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/popper.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/c560c025cf.js"></script>

<script>
	function callAjax(method, baseUrl) {
		$.ajax({
		    type: method,
		    url: baseUrl,
		    contentType: "application/json",
		    dataType: "json",
		    headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
		    success: function(response) {
		        console.log(response);
		    },
		    error: function(response) {
		        console.log(response);
		    }
		});		
	}
	function callAjaxForDelete(baseUrl){
		alert("Are you sure you want to delete?");
		$.ajax({
		    type: "DELETE",
		    url: baseUrl,
		    contentType: "application/json",
		    dataType: "json",
		    headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
		    success: function(response) {
		    	$(this).closest("tr").remove();
		        console.log(response);
		    },
		    error: function(response) {
		        console.log(response);
		    }
		});		
	}
	</script>

<style>
.main-row {
	min-height: 400px;
}
.usermenulistcol {
	padding-right: 0px;
}

.submenus {
	background-color: #222222a8;
}

.menubar>li>a {
	margin-right: 0px;
	border: 1px solid #000000;
}

.navbar-dark .navbar-nav .nav-link {
	color: white;
}

.urgent_fields {
	color: red;
}

.form-group-row {
	padding-bottom: 10px;
}

.main-center {
	background: #009edf1a;
	color: #000000;
	text-shadow: none;
	-webkit-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
	-moz-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
	box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
}

.shadow {
	text-shadow: none;
	-webkit-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
	-moz-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
	box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
}

.content-table {
	background: #009edf1a;
	-webkit-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
	-moz-box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
	box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
}
.has-error {
    color: red;
}
.norecord {
	height: 100px;
    margin-left: 1px;
    margin-right: 1px;
    margin-bottom: 10px;
}
.back-img {
	background-image: url('/resources/images/back6.jpg');
	color: white;
}
.selected-menu {
	color: #84d5ff;
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="container" style="background-color: #dddddd38;">
		<div class="row" style="height: 10px; "></div>
		<div class="row" style="height: 50px; margin-top: 10px;background-color: black;">
			<div class="col-sm-9"></div>
			<div class="col-sm-3">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="dropdown">
							<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" 
							style="float: right;min-width:160px;background-color: black;color:white;">${pageContext.request.userPrincipal.name}</button>
							<div class="dropdown-menu">
								<security:authorize access="hasAuthority('ADMIN')">
									<a class="dropdown-item" href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a>
								</security:authorize>
								<a class="dropdown-item" href="${contextPath}<%=RequestUrls.MY_ACCOUNT %>"><spring:message code="My Account" text="My Account" /></a>
								<a class="dropdown-item" href="#" onclick="document.forms['logoutForm'].submit()"><spring:message code="Logout" text="Logout" /></a>
							</div>
						</div>
					</form>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12" style="padding: 0px;">
				<nav class="navbar navbar-expand-sm navbar-dark" style="background-color:#009688;">
					<ul class="navbar-nav">
						<%
						java.util.Map<String, String> menuMap = new java.util.LinkedHashMap<>();
						menuMap.put("Dashboard", "home");
						menuMap.put("Products", "products/all");
						menuMap.put("Stock", "stock");
						menuMap.put("Orders", "orders");
						menuMap.put("Customers", "customers");
						menuMap.put("Profit & Loss", "dailyProfitLoss");
						
						for (java.util.Map.Entry<String, String> entry : menuMap.entrySet()){
							if(null == request.getParameter("selectedNav") && "Dashboard".equalsIgnoreCase(entry.getKey())) {%>
								<li class="nav-item"><a class="nav-link selected-menu" href="${contextPath}/<%=entry.getValue() %>"><spring:message code="<%=entry.getKey() %>" text="<%=entry.getKey() %>" /></a></li>
							<%} else if(entry.getKey().equalsIgnoreCase(request.getParameter("selectedNav"))) {%>
								<li class="nav-item"><a class="nav-link selected-menu" href="${contextPath}/<%=entry.getValue() %>"><spring:message code="<%=entry.getKey() %>" text="<%=entry.getKey() %>" /></a></li>
							<%} else {%>
								<li class="nav-item"><a class="nav-link" href="${contextPath}/<%=entry.getValue() %>"><spring:message code="<%=entry.getKey() %>" text="<%=entry.getKey() %>" /></a></li>
							<%}
						} %>
					</ul>
				</nav>
			</div>
		</div>
		<div class="row" style="height: 10px;"></div>