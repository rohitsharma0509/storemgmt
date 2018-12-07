<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"/>
<div class="row">
	<div class="col-sm-12" style="height: 40px;"><spring:message code="Edit Profile" text="Edit Profile" /></div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row">
	<div class="col-sm-12">
		<ul class="nav nav-tabs">
			<li class="nav-item active show"><a class="nav-link" data-toggle="tab" href="#editProfile"><spring:message code="Edit Profile" text="Edit Profile" /></a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane container active" id="editProfile">
				<div class="row" style="height: 20px;"></div>
				<div class="row">
				<div class="col-sm-12">
				<form:form method="POST" modelAttribute="user" class="form-horizontal" enctype="multipart/form-data" action="<%=RequestUrls.USERS %>">
				<form:hidden path="id"  class="form-control input-sm"/>
				<form:hidden path="username"  class="form-control input-sm"/>
				<form:hidden path="password"  class="form-control input-sm"/>
					<div class="col-sm-1"></div>
					<div class="col-sm-10">
						<div class="card">
							<div class="card-body">
								<div class="row form-group-row">
									<div class="col-sm-1"></div>
									<div class="col-sm-5">
										<label for="firstName"><spring:message code="First Name" text="First Name" />&nbsp;<span class="urgent_fields">*</span></label>
										<form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
								    	<form:errors path="firstName" class="help-inline has-error"></form:errors>
				            		</div>
				            		<div class="col-sm-5">
										<label for="lastName"><spring:message code="Last Name" text="Last Name" />&nbsp;<span class="urgent_fields">*</span></label>
				            			<form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
				            			<form:errors path="lastName" class="help-inline has-error"></form:errors>
				            		</div>
				            		<div class="col-sm-1"></div>
								</div>
								<div class="row form-group-row">
									<div class="col-sm-1"></div>
				            		<div class="col-sm-5">
										<label for="email"><spring:message code="Email" text="Email" />&nbsp;<span class="urgent_fields">*</span></label>
				            			<form:input type="text" path="email" id="email" class="form-control input-sm"/>
				            			<form:errors path="email" class="help-inline has-error"></form:errors>
				            		</div>
				            		<div class="col-sm-5">
										<label for="mobile"><spring:message code="Mobile" text="Mobile" />&nbsp;<span class="urgent_fields">*</span></label>
										<form:input type="text" path="mobile" id="mobile" class="form-control input-sm"/>
				            			<form:errors path="mobile" class="help-inline has-error"></form:errors>
				            		</div>
				            		<div class="col-sm-1"></div>
								</div>
								<div class="row form-group-row">
									<div class="col-sm-1"></div>
									<div class="col-sm-5">
				            			<label for="addressLine1"><spring:message code="Address Line1" text="Address Line1" /></label>
										<form:input type="text" path="addressLine1" id="addressLine1" class="form-control input-sm"/>
				            		</div>
									<div class="col-sm-5">
				            			<label for="addressLine2"><spring:message code="Address Line2" text="Address Line2" /></label>
										<form:input type="text" path="addressLine2" id="addressLine2" class="form-control input-sm"/>
				            		</div>
				            		<div class="col-sm-1"></div>
								</div>
								<div class="row form-group-row">
									<div class="col-sm-1"></div>
									<div class="col-sm-5">
										<label for="city"><spring:message code="City" text="City" /></label>
				            			<form:input type="text" path="city" id="city" class="form-control input-sm"/>
				            		</div>
				            		<div class="col-sm-5">
										<label for="pincode"><spring:message code="Pincode" text="Pincode" /></label>
				            			<form:input type="text" path="pincode" id="pincode" class="form-control input-sm"/>
				            		</div>
				            		<div class="col-sm-1"></div>
								</div>
								<div class="row form-group-row">
									<div class="col-sm-1"></div>
									<div class="col-sm-5">
										<label for="state"><spring:message code="State" text="State" /></label>
				            			<form:input type="text" path="state" id="state" class="form-control input-sm"/>
				            		</div>
				            		<div class="col-sm-5">
										<label for="country"><spring:message code="Country" text="Country" /></label>
				            			<form:input type="text" path="country" id="country" class="form-control input-sm"/>
				            		</div>
				            		<div class="col-sm-1"></div>
								</div>
								<div class="row form-group-row">
									<div class="col-sm-1"></div>
									<div class="col-sm-5">
										<label for="language"><spring:message code="Language" text="Language" />&nbsp;<span class="urgent_fields">*</span></label>
										<select id="language" name="language" class="form-control input-sm">
											<option value="-1"><spring:message code="Select Language" text="Select Language" /></option>
											<c:forEach var="language" items="${languages}">
												<c:choose>
													<c:when test="${param.language == language.key}">
														<option selected value="${language.key}"><spring:message code="${language.value}" text="${language.value}" /></option>
													</c:when>
													<c:otherwise>
														<option value="${language.key}"><spring:message code="${language.value}" text="${language.value}" /></option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
				            			<form:errors path="language" class="help-inline has-error"></form:errors>
				            		</div>
				            		<div class="col-sm-5"></div>
				            		<div class="col-sm-1"></div>
								</div>
								<div class="row form-group-row">
									<div class="col-sm-1"></div>
									<div class="col-sm-11">
				            			<button type="submit" class="btn btn-info"><spring:message code="Update" text="Update" /></button>
				            		</div>
				        		</div>
							</div>
						</div>
					</div>
					<div class="col-sm-1"></div>
				</form:form>
				</div>
			</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>