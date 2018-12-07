<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<jsp:include page="header.jsp"></jsp:include>
<script src="${contextPath}/resources/ckeditor/ckeditor/ckeditor.js"></script>
<div class="row panel">
	<div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > Send Email</div>
</div>
<div class="row" style="height:10px;">
</div>
<form:form method="POST" modelAttribute="email" class="form-horizontal" enctype="multipart/form-data" action="sendEmail">
<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-1"></div>
		<div class="col-sm-10">
			<div class="card">
				<div class="card-body">
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
						<div class="col-sm-5">
							<label for="to">To&nbsp;<span class="urgent_fields">*</span></label>
							<form:input type="text" path="to" id="to" data-role="tagsinput" class="form-control input-sm"/>
							<form:errors path="to" class="help-inline has-error"></form:errors>
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
	            			<label for="cc">CC</label>
	            			<form:input type="text" path="cc" id="cc" class="form-control input-sm"/>
	            		</div>
	            		<div class="col-sm-5">
		            		<label for="bcc">BCC</label>
	            			<form:input type="text" path="bcc" id="bcc" class="form-control input-sm"/>
	            		</div>
	            		<div class="col-sm-1"></div>
					</div>
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
						<div class="col-sm-10">
	            			<label for="body">Body&nbsp;<span class="urgent_fields">*</span></label>
							<form:textarea path="body" rows="5" cols="60" />
							<ckeditor:replace replace="body" basePath="/ckeditor/" />
							<form:errors path="body" class="help-inline has-error"></form:errors>
	            		</div>
	            		<div class="col-sm-1"></div>
					</div>
					<div class="row form-group-row">
						<div class="col-sm-1"></div>
						<div class="col-sm-11">
	            			<button type="submit" class="btn btn-info">Send Email</button>
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