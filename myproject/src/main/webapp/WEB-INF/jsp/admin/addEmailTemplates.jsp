<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="${contextPath}/resources/ckeditor/ckeditor/ckeditor.js"></script>
<jsp:include page="../header.jsp"></jsp:include>
<%
String action = "Save";
%>
<div class="row panel">
	<c:choose>
	    <c:when test="${empty emailTemplateDto.id}">
	        <div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a></b> > <b><a href="${contextPath}<%=RequestUrls.EMAIL_TEMPLATES %>"><spring:message code="Email Templates" text="Email Templates" /></a></b> > <spring:message code="Add Email Template" text="Add Email Template" /></div>
	    </c:when>
	    <c:otherwise>
	        <div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin"><spring:message code="Admin" text="Admin" /></a></b> > <b><a href="${contextPath}<%=RequestUrls.EMAIL_TEMPLATES %>"><spring:message code="Email Templates" text="Email Templates" /></a></b> > <spring:message code="Edit Email Template" text="Edit Email Template" /></div>
	        <% 
	        action = "Edit";
	        %>
	    </c:otherwise>
	</c:choose>
</div>
<div class="row" style="height:10px;">
</div>
<form:form method="POST" modelAttribute="emailTemplateDto" class="form-horizontal" enctype="multipart/form-data" action="<%=RequestUrls.EMAIL_TEMPLATES %>">
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
							<label for="type"><spring:message code="Template For" text="Template For" />&nbsp;<span class="urgent_fields">*</span></label>
							<form:select path="type" id="type" class="form-control input-sm">
					        	<form:option value="">Select Type</form:option>
					        	<c:forEach var="type" items="${types}">
				    	    		<form:option value="${type.id}">${type.type}</form:option>
				    	    	</c:forEach>
					    	</form:select>
					    	<form:errors path="type" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-5">
							<label for="subject">Subject&nbsp;<span class="urgent_fields">*</span></label>
							<form:input type="text" path="subject" id="subject" class="form-control input-sm"/>
							<form:errors path="subject" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-1"></div>
					</div>
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
						<div class="col-sm-5">
							<label for="from"><spring:message code="From" text="From" />&nbsp;<span class="urgent_fields">*</span></label>
	            			<form:input type="text" path="from" id="from" class="form-control input-sm"/>
	            			<form:errors path="from" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-5">
							<label for="to"><spring:message code="To" text="To" />&nbsp;<span class="urgent_fields">*</span></label>
							<form:input type="text" path="to" id="to" data-role="tagsinput" class="form-control input-sm"/>
							<form:errors path="to" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-1"></div>
					</div>
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
						<div class="col-sm-5">
							<label for="cc"><spring:message code="CC" text="CC" /></label>
							<form:input type="text" path="cc" id="cc" data-role="tagsinput" class="form-control input-sm"/>
							<form:errors path="cc" class="help-inline has-error"></form:errors>
	            		</div>
						<div class="col-sm-5">
							<label for="bcc"><spring:message code="BCC" text="BCC" /></label>
							<form:input type="text" path="bcc" id="bcc" data-role="tagsinput" class="form-control input-sm"/>
							<form:errors path="bcc" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-1"></div>
					</div>
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
						<div class="col-sm-10">
							<label for="body"><spring:message code="Body" text="Body" />&nbsp;<span class="urgent_fields">*</span></label>
							<form:textarea path="body" rows="5" cols="60" />
							<ckeditor:replace replace="body" basePath="/ckeditor/" />
							<form:errors path="body" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-1"></div>
					</div>
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
						<div class="col-sm-11">
	            			<button type="submit" class="btn btn-sm btn-info"><%=action %></button>
	            		</div>
	        		</div>
				</div>
			</div>
		</div>
		<div class="col-sm-1"></div>
	</div>
</div>
</form:form>
<jsp:include page="../footer.jsp"></jsp:include>
