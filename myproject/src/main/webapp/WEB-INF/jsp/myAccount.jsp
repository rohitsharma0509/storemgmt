<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"/>
<style>
.file {
    position: relative;
    overflow: hidden;
    margin-top: -20%;
    width: 70%;
    border: none;
    border-radius: 0;
    font-size: 15px;
    background: #212529b8;
}
.file input {
    position: absolute;
    opacity: 0;
    right: 0;
    top: 0;
}
</style>
<div class="row">
	<div class="col-sm-12" style="height: 40px;"><spring:message code="My Account" text="My Account" /></div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row">
	<div class="col-sm-10"></div>
	<div class="col-sm-2">
		<a class="" href="${contextPath}<%=RequestUrls.PERSONAL_INFO %>"><spring:message code="Edit Profile" text="Edit Profile" /></a>
	</div>
</div>
<div class="row">
	<div class="col-sm-4">
		<div>
			<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog" alt="" />
			<div class="file btn btn-sm btn-primary"><spring:message code="Change Photo" text="Change Photo" /><input type="file" name="file" /></div>
		</div>
	</div>
	<div class="col-sm-8">
		<div class="profile-head">
			<h5>${user.firstName} ${user.lastName}</h5>
			<h6><spring:message code="Admin User" text="Admin User" /></h6>
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item"><a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true"><spring:message code="About" text="About" /></a></li>
				<li class="nav-item"><a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false"><spring:message code="Contact" text="Contact" /></a></li>
			</ul>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-sm-4">

	</div>
	<div class="col-sm-8">
		<div class="tab-content profile-tab" id="myTabContent">
			<div class="tab-pane fade show active" id="home" role="tabpanel"
				aria-labelledby="home-tab">
				<div class="row">
					<div class="col-md-3"><label><spring:message code="User Id" text="User Id" /></label></div>
					<div class="col-md-9"><p>${user.username }</p></div>
				</div>
				<div class="row">
					<div class="col-md-3"><label><spring:message code="Name" text="Name" /></label></div>
					<div class="col-md-9"><p>${user.firstName} ${user.lastName}</p></div>
				</div>
				<div class="row">
					<div class="col-md-3"><label><spring:message code="Language" text="Language" /></label></div>
					<div class="col-md-9"><p>${user.language}</p></div>
				</div>
			</div>
			<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
				<div class="row">
					<div class="col-md-3"><label><spring:message code="Email" text="Email" /></label></div>
					<div class="col-md-9"><p>${user.email}</p></div>
				</div>
				<div class="row">
					<div class="col-md-3"><label><spring:message code="Mobile" text="Mobile" /></label></div>
					<div class="col-md-9"><p>${user.mobile}</p></div>
				</div>
				<div class="row">
					<div class="col-md-3"><label><spring:message code="Address" text="Address" /></label></div>
					<div class="col-md-9"><p>${user.addressLine1}, ${user.addressLine2},<br>${user.city}, ${user.state}, ${user.country}</p></div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row" style="height: 100px;"></div>
<jsp:include page="footer.jsp"></jsp:include>