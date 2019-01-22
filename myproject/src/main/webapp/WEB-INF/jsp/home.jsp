<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp"></jsp:include>
<style>
.indicator {
	background-color: #dee2e6!important;
}
.indicator-body {
	color: black;
	min-height: 123px;
}
</style>
<div class="row">
	<div class="col-sm-12" style="height: 40px;"><spring:message code="Dashboard" text="Dashboard" /></div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row">
	<div class="col-sm-2">
		<div class="card indicator">
			<div class="card-body indicator-body shadow">
				<h6>
					<i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${totalProducts}</h6>
				<h5><spring:message code="Total Products" text="Total Products" /></h5>
			</div>
		</div>
	</div>
	<div class="col-sm-2">
		<div class="card indicator">
			<div class="card-body indicator-body shadow">
				<h6>
					<i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${availableProducts}
				</h6>
				<h5><spring:message code="Available Products" text="Available Products" /></h5>
			</div>
		</div>
	</div>
	<div class="col-sm-2">
		<div class="card indicator">
			<div class="card-body indicator-body shadow">
				<h6 class="card-title">
					<i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${outOfStockProducts}
				</h6>
				<h5 class="card-text"><spring:message code="Out of Stock Products" text="Out of Stock Products" /></h5>
			</div>
		</div>
	</div>
	<div class="col-sm-2">
		<div class="card indicator">
			<div class="card-body indicator-body shadow">
				<h6>
					<i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${alertProducts}
				</h6>
				<h5><spring:message code="Alert Products" text="Alert Products" /></h5>
			</div>
		</div>
	</div>
	<div class="col-sm-2">
		<div class="card indicator">
			<div class="card-body indicator-body shadow">
				<h6>
					<i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;${todayOrder}
				</h6>
				<h5><spring:message code="Today's Order" text="Today's Order" /></h5>
			</div>
		</div>
	</div>
	<div class="col-sm-2">
		<div class="card indicator">
			<div class="card-body indicator-body shadow">
				<h6>
					<i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;0
				</h6>
				<h5><spring:message code="Feedback Received" text="Feedback Received" /></h5>
			</div>
		</div>
	</div>
</div>
<div class="row" style="margin-top:15px;">
	<div class="col-sm-12">
		<div class="card">
			<div class="card-body shadow" style="padding:0px;">
				<div class="card-img-top"><img style="max-width: 100%;" alt="Stock Status" src="data:image/png;base64,${compareGraph}"/></div>
			</div>
		</div>
	</div>
</div>
<div class="row" style="margin-top:15px;">
	<div class="col-sm-4">
		<div class="card">
			<div class="card-body shadow" style="padding:0px;">
				<div class="card-img-top"><img style="max-width: 100%;" alt="Stock Status" src="data:image/png;base64,${stockStatus}"/></div>
			</div>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="card">
			<div class="card-body shadow" style="padding:0px;">
				<div class="card-img-top"><img style="max-width: 100%;" alt="Yearly Sales Graph" src="data:image/png;base64,${yearlySalesGraph}"/></div>
			</div>
		</div>
	</div>
	<div class="col-sm-4">
		<div class="card">
			<div class="card-body shadow" style="padding:0px;">
				<div class="card-img-top"><img style="max-width: 100%;" alt="Monthly Sales Graph" src="data:image/png;base64,${monthlySalesGraph}"/></div>
			</div>
		</div>
	</div>
</div>
<div class="row" style="height: 50px;"></div>
<jsp:include page="footer.jsp"></jsp:include>