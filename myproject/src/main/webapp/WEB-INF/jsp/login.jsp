<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Log in with your account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<div class="card">
					<div class="card-header">Login Now</div>
					<div class="card-body">
						<form method="POST" action="${contextPath}/login">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<c:if test="${not empty message}">
								<div id="logoutMsg" class="alert alert-success hide">${message}</div>
							</c:if>
							<div class="form-group">
								<label for="username" class="control-label">Username</label>
								<input type="text" class="form-control" id="username" name="username" value="" required title="Please enter your username" placeholder="username">
								<span class="help-block"></span>
							</div>
							<div class="form-group">
								<label for="password" class="control-label">Password</label>
								<input type="password" class="form-control" id="password" name="password" value="" required title="Please enter your password">
								<span class="help-block"></span>
							</div>
							<c:if test="${not empty error}">
								<div id="loginErrorMsg" class="alert alert-danger hide">${error}</div>
							</c:if>
							<div class="checkbox">
								<label><input type="checkbox" name="rememberMe" id="rememberMe"> Remember login</label>
								<p class="help-block">(if this is a private computer)</p>
							</div>
							<button type="submit" class="btn btn-success btn-block">Login</button>
							<a href="${contextPath}/forgetPassword" class="btn btn-info btn-block">Help to login</a>
						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="card">
					<div class="card-body">
						<p class="lead">Register now for <span class="text-success">FREE</span></p>
						<ul class="list-unstyled" style="line-height: 2">
							<li><span class="fa fa-check text-success"></span> See all your orders</li>
							<li><span class="fa fa-check text-success"></span> Fast re-order</li>
							<li><span class="fa fa-check text-success"></span> Save your favorites</li>
							<li><span class="fa fa-check text-success"></span> Fast checkout</li>
						</ul>
						<p><a href="${contextPath}/registration" class="btn btn-info btn-block">Yes please, register now!</a></p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
