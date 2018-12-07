<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<jsp:include page="../header.jsp"></jsp:include>
<link href="${contextPath}/resources/css/stepper.css" rel="stylesheet">
<style>
.btn-default {
    background-color: #2bbbad!important;
    color: #fff!important;
}
.btn-indigo {
    background-color: #3f51b5!important;
    color: #fff!important;
}
</style>
<div class="row panel">
	<div class="col-sm-12" style="height:40px;"><b><a href="${contextPath}/admin">Admin</a></b> > <b><a href="${contextPath}<%=RequestUrls.PRODUCTS %>">Products</a></b> > Import Products</div>
</div>
<div class="row" style="height:10px;"></div>
<div class="row">
	<div class="col-sm-12">
		<div class="card">
		    <div class="card-body mb-4">
		        <h2 class="text-center font-weight-bold pt-4 pb-5"><strong>Import Products</strong></h2>
		
		        <div class="steps-form">
		            <div class="steps-row setup-panel">
		                <div class="steps-step">
		                    <a href="#step-1" type="button" class="btn btn-indigo btn-circle">1</a>
		                    <p>Select File</p>
		                </div>
		                <div class="steps-step">
		                    <a href="#step-2" type="button" class="btn btn-default btn-circle">2</a>
		                    <p>Confirmation</p>
		                </div>
		            </div>
		        </div>
		        <form role="form" action="<%=RequestUrls.PRODUCTS_SAVE %>" enctype="multipart/form-data" method="post">
		        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		            <div class="row setup-content" id="step-9">
						<div class="col-sm-3"></div>
						<div class="col-sm-4">
						    <h3 class="font-weight-bold pl-0 my-4"><strong>Select File</strong></h3>
						    <div class="form-group md-form">
						        <input id="file" name="file" type="file" required="required" class="form-control validate">
						    </div>
						</div>
						<div class="col-sm-5"></div>
					</div>
		            <div class="row">
		            	<div class="col-sm-3"></div>
						<div class="col-sm-7">
		            		<div class="form-check">
								<label class="form-check-label">
								    <input type="radio" class="form-check-input" value="csv" checked name="fileType">CSV (Comma Separated Values)&nbsp;&nbsp;&nbsp;<a href="${contextPath}/sample?fileType=csv">Download Sample</a>
								</label>
							</div>
							<div class="form-check-inline">
								<label class="form-check-label">
								<input type="radio" class="form-check-input" value="xml" name="fileType">XML (Extensible Markup Language)&nbsp;&nbsp;&nbsp;<a href="${contextPath}/sample?fileType=xml">Download Sample</a>
								</label>
							</div>
						</div>
						<div class="col-sm-2"></div>
		            </div>
		            <div class="row">
		            	<div class="col-sm-3"></div>
		            	<div class="col-sm-9"><button type="submit" class="btn btn-sm btn-success" style="margin-top: 15px;"> Import</button></div>
		            </div>
		        </form>
		    </div>
		</div>
	</div>
</div>
<div class="row" style="height: 100px;"></div>
<jsp:include page="../footer.jsp"></jsp:include>