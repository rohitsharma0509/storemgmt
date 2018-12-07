<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp">
	<jsp:param name="selectedNav" value="Profit & Loss" />
</jsp:include>
<div class="row">
	<div class="col-sm-12" style="height: 40px;">Profit & Loss</div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row">
	<div class="col-sm-12">
		<ul class="nav nav-tabs">
			<li class="nav-item active show"><a class="nav-link" data-toggle="tab" href="#daily">Daily</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#monthly">Monthly</a></li>
			<li class="nav-item"><a class="nav-link"  data-toggle="tab" href="#quarterly">Quarterly</a></li>
			<li class="nav-item"><a class="nav-link"  data-toggle="tab" href="#yearly">Yearly</a></li>
		</ul>
		
		<div class="tab-content">
			<div class="tab-pane container" id="daily">
				<div class="row" style="height: 20px;"></div>
				<div class="row">
					<div class="col-sm-3">
						<form method="GET" class="form-horizontal" action="dailyProfitLoss">
						<div class="card">
							<div class="card-body main-center">
								<div class="row">
									<div class="col-sm-12">
										<label for="fromDate" class="control-label text-right">From Date </label>
										<input type="date" id="fromDate" name="fromDate" value="${param.fromDate}" class="form-control input-sm" />
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<label for="toDate">To Date</label>
										<input type="date" id="toDate" name="toDate" value="${param.toDate}" class="form-control input-sm" />
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12"><input type="submit" value="Search" style="margin-top: 15px;" class="btn btn-sm btn-success form-control"></div>
								</div>
							</div>
						</div>
						</form>
					</div>
					<div class="col-sm-9">
						<c:choose>
							<c:when test="${page.getContent().size() > 0}">
								<div class="row">
									<div class="col-sm-12">
										<table class="table content-table">
											<tr>
												<th>Date</th>
												<th>No of Orders</th>
												<th>Sold Quantity</th>
												<th>Amount Received</th>
												<th>Profit / Loss</th>
											</tr>
											<c:forEach var="profitLossDto" items="${page.getContent()}">
												<tr>
													<td>${profitLossDto.orderDate}</td>
													<td>${profitLossDto.noOfOrders}</td>
													<td>${profitLossDto.soldQuantity}</td>
													<td>${profitLossDto.amountReceived}</td>
													<td>${profitLossDto.profitOrLoss}</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
								<c:choose>
									<c:when test="${page.getTotalPages() > 1}">
										<div class="row">
											<div class="col-sm-12">${pagging}</div>
										</div>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<div class="row">
									<div class="col-sm-12">No Records Found.</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="tab-pane container fade" id="monthly">
				<div class="row" style="height: 20px;"></div>
				<div class="row">
					<div class="col-sm-3">
						<form method="GET" class="form-horizontal" action="dailyProfitLoss">
						<div class="card">
							<div class="card-body main-center">
								<div class="row">
									<div class="col-sm-12">
										<label for="month" class="control-label text-right">Month </label>${months}
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<label for="month" class="control-label text-right">Year </label>${years}
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12"><input type="submit" value="Search" style="margin-top: 15px;" class="btn btn-sm btn-success form-control"></div>
								</div>
							</div>
						</div>
						</form>
					</div>
					<div class="col-sm-9">
						<c:choose>
							<c:when test="${page.getContent().size() > 0}">
								<div class="row">
									<div class="col-sm-12">
										<table class="table content-table">
											<tr>
												<th>Date</th>
												<th>No of Orders</th>
												<th>Sold Quantity</th>
												<th>Amount Received</th>
												<th>Profit / Loss</th>
											</tr>
											<c:forEach var="profitLossDto" items="${page.getContent()}">
												<tr>
													<td>${profitLossDto.orderDate}</td>
													<td>${profitLossDto.noOfOrders}</td>
													<td>${profitLossDto.soldQuantity}</td>
													<td>${profitLossDto.amountReceived}</td>
													<td>${profitLossDto.profitOrLoss}</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
								<c:choose>
									<c:when test="${page.getTotalPages() > 1}">
										<div class="row">
											<div class="col-sm-12">${pagging}</div>
										</div>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<div class="row">
									<div class="col-sm-12">No Records Found.</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="tab-pane container fade" id="quarterly">
				<div class="row" style="height: 10px;"></div>
				<div class="row">
					<div class="col-sm-3">
						<form method="GET" class="form-horizontal" action="dailyProfitLoss">
						<div class="card">
							<div class="card-body main-center">
								<div class="row">
									<div class="col-sm-12">
										<label for="quarter" class="control-label text-right">Quarter </label>${quarters}
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12"><input type="submit" value="Search" style="margin-top: 15px;" class="btn btn-sm btn-success form-control"></div>
								</div>
							</div>
						</div>
						</form>
					</div>
					<div class="col-sm-9">
						<c:choose>
							<c:when test="${page.getContent().size() > 0}">
								<div class="row">
									<div class="col-sm-12">
										<table class="table content-table">
											<tr>
												<th>Date</th>
												<th>No of Orders</th>
												<th>Sold Quantity</th>
												<th>Amount Received</th>
												<th>Profit / Loss</th>
											</tr>
											<c:forEach var="profitLossDto" items="${page.getContent()}">
												<tr>
													<td>${profitLossDto.orderDate}</td>
													<td>${profitLossDto.noOfOrders}</td>
													<td>${profitLossDto.soldQuantity}</td>
													<td>${profitLossDto.amountReceived}</td>
													<td>${profitLossDto.profitOrLoss}</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
								<c:choose>
									<c:when test="${page.getTotalPages() > 1}">
										<div class="row">
											<div class="col-sm-12">${pagging}</div>
										</div>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<div class="row">
									<div class="col-sm-12">No Records Found.</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="tab-pane container fade" id="yearly">
				<div class="row" style="height: 10px;"></div>
				<div class="row">
					<div class="col-sm-3">
						<form method="GET" class="form-horizontal" action="dailyProfitLoss">
						<div class="card">
							<div class="card-body main-center">
								<div class="row">
									<div class="col-sm-12">
										<label for="year" class="control-label text-right">Year </label>${years}
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12"><input type="submit" value="Search" style="margin-top: 15px;" class="btn btn-sm btn-success form-control"></div>
								</div>
							</div>
						</div>
						</form>
					</div>
					<div class="col-sm-9">
						<c:choose>
							<c:when test="${page.getContent().size() > 0}">
								<div class="row">
									<div class="col-sm-12">
										<table class="table content-table">
											<tr>
												<th>Year</th>
												<th>No of Orders</th>
												<th>Sold Quantity</th>
												<th>Amount Received</th>
												<th>Profit / Loss</th>
											</tr>
											<c:forEach var="profitLossDto" items="${page.getContent()}">
												<tr>
													<td>${profitLossDto.orderDate}</td>
													<td>${profitLossDto.noOfOrders}</td>
													<td>${profitLossDto.soldQuantity}</td>
													<td>${profitLossDto.amountReceived}</td>
													<td>${profitLossDto.profitOrLoss}</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
								<c:choose>
									<c:when test="${page.getTotalPages() > 1}">
										<div class="row">
											<div class="col-sm-12">${pagging}</div>
										</div>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<div class="row">
									<div class="col-sm-12">No Records Found.</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>