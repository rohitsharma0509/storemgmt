<%@page import="org.springframework.util.StringUtils"%>
<%@page import="com.app.myproject.constants.RequestUrls"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp">
	<jsp:param name="selectedNav" value="Profit & Loss" />
</jsp:include>
<script>
function loadTab(tab, url, data) {
	var target = tab.data("target"); // the target pane
	
	$(target).load(url, data, function(result) {
		tab.tab('show');
	});
}

function submitForm(f, tabId) {
	var data = $(f).serialize();
	var tab = $('.nav-tabs a[id="'+tabId+'"]')
	var href = tab.attr("href");// the remote url for content
	var url = href;
	loadTab(tab, url, data);
}

	$(function() {
		$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
			var tab = $(this); // this tab
			var url = tab.attr("href"); // the remote url for content
			
			loadTab(tab, url, null);
		});
		
		$('#tab1').tab('show');
	});
</script>
<div class="row">
	<div class="col-sm-12" style="height: 40px;"><spring:message code="Profit & Loss" text="Profit & Loss" /></div>
</div>
<div class="row" style="height: 10px;"></div>
<div class="row">
	<div class="col-sm-12">
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link" data-toggle="tab" id="tab1" data-target="#daily" href="${contextPath}<%=RequestUrls.DAILY_PROFIT_LOSS %>"><spring:message code="Daily" text="Daily" /></a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab" id="tab2" data-target="#monthly" href="${contextPath}<%=RequestUrls.MONTHLY_PROFIT_LOSS %>"><spring:message code="Monthly" text="Monthly" /></a></li>
			<li class="nav-item"><a class="nav-link"  data-toggle="tab" id="tab3" data-target="#quarterly" href="${contextPath}<%=RequestUrls.QUARTERLY_PROFIT_LOSS %>"><spring:message code="Quarterly" text="Quarterly" /></a></li>
			<li class="nav-item"><a class="nav-link"  data-toggle="tab" id="tab4" data-target="#yearly" href="${contextPath}<%=RequestUrls.YEARLY_PROFIT_LOSS %>"><spring:message code="Yearly" text="Yearly" /></a></li>
		</ul>
		
		<div class="tab-content">
			<div class="tab-pane container" id="daily"></div>
			<div class="tab-pane container fade" id="monthly"></div>
			<div class="tab-pane container fade" id="quarterly"></div>
			<div class="tab-pane container fade" id="yearly"></div>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>