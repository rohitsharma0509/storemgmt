<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
	.btn-transparent {
		border: 1px solid #000000;
		color: #000000;
	}
	.productCard {
		background-color: #009edf1a;
	}
</style>
<c:forEach var="product" items="${page.getContent()}" varStatus="loop">
	<c:if test="${loop.index ==0 || (loop.index % 4) == 0}">
	<div class="row">
	</c:if>
		<div class="col-sm-3">
			<div class="card productCard">
				<c:choose>
					<c:when test="${product.base64Image != null && product.base64Image != ''}">
						<div class="card-img-top"><img style="max-width: 100%; max-height: 100px;" alt="${product.code}" src="data:image/jpg;base64,${product.base64Image}"/></div>
					</c:when>
				</c:choose>
				<div class="card-body shadow">
					<h6 class="card-title">${product.name}</h6>
					<h6 class="card-title">${product.brandName}</h6>
					<h6 class="card-title">${product.perProductPrice}</h6>
					<c:choose>
						<c:when test="${product.quantity != null && product.quantity > 0}">
							<a href="${contextPath}/checkout?id=${product.id}" class="btn btn-sm btn-transparent"><spring:message code="Buy Now" text="Buy Now" /></a>
    						<a href="${contextPath}/addToCart?id=${product.id}" class="btn btn-sm btn-transparent"><spring:message code="Add To Cart" text="Add To Cart" /></a>
    					</c:when>
	    				<c:otherwise>
	    					<h6 class="card-title has-error text-center"><spring:message code="Out Of Stock" text="Out Of Stock" /></h6>
	    				</c:otherwise>
    				</c:choose>
				</div>
			</div>
		</div>
	<c:if test="${loop.index == (fn:length(page.content)-1) && ((loop.index + 1) % 4) == 1}">
		<div class="col-sm-9"></div>
	</c:if>
	<c:if test="${loop.index == (fn:length(page.content)-1) && ((loop.index + 1) % 4) == 2}">
		<div class="col-sm-6"></div>
	</c:if>
	<c:if test="${loop.index == (fn:length(page.content)-1) && ((loop.index + 1) % 4) == 3}">
		<div class="col-sm-3"></div>
	</c:if>
	<c:if test="${loop.index == (fn:length(page.content)-1) || ((loop.index + 1) % 4) == 0}">
	</div>
	<div class="row" style="height: 10px;"></div>
	</c:if>
</c:forEach>