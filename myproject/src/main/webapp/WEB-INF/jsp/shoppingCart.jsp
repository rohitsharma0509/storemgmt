<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp">
	<jsp:param name="selectedNav" value="Products" />
</jsp:include>
<script>
$(document).ready(function() {
	$('.btn-number').click(function(e){
		e.preventDefault();
	  
		fieldName = $(this).attr('data-field');
		type      = $(this).attr('data-type');
		
		var input = $("input[name='"+fieldName+"']");
		var currentVal = parseInt(input.val());
		//var index = fieldName.substring(6, fieldName.lastIndexOf("]"));
		var index = fieldName.substring(12, fieldName.lastIndexOf("]"));
		var subTotalInput = $("input[name='subTotal["+index+"]']");
		var price = parseFloat(subTotalInput.attr('data-field'));
		var totalPriceLabel = $("#totalPriceLabel");
		var totalPriceHidden = $("#totalPrice");
		var totalPrice = parseFloat(totalPriceLabel.text());
		
		if (!isNaN(currentVal)) {
	    	if(type == 'minus') {
	        	if(currentVal > input.attr('min')) {
	            	input.val(currentVal - 1).change();
	            	subTotalInput.val(parseFloat((currentVal - 1)*price));
	            	totalPriceLabel.text(parseFloat(totalPrice - price));
	            	totalPriceHidden.val(parseFloat(totalPrice - price));
	        	} 
	        	if(parseInt(input.val()) == input.attr('min')) {
	            	$(this).attr('disabled', true);
	        	}
			} else if(type == 'plus') {
				if(currentVal < input.attr('max')) {
	        		input.val(currentVal + 1).change();
	        		subTotalInput.val(parseFloat((currentVal + 1)*price));
	        		totalPriceLabel.text(parseFloat(totalPrice + price));
	        		totalPriceHidden.val(parseFloat(totalPrice + price));
	    		}
	    		if(parseInt(input.val()) == input.attr('max')) {
	        		$(this).attr('disabled', true);
	    		}
			}
		} else {
	    	input.val(1);
		}
	});
	$('.input-number').focusin(function(){
		$(this).data('oldValue', $(this).val());
	});
	$('.input-number').change(function() {
		minValue =  parseInt($(this).attr('min'));
		maxValue =  parseInt($(this).attr('max'));
		valueCurrent = parseInt($(this).val());
		name = $(this).attr('name');
		
		if(valueCurrent >= minValue) {
			$(".btn-number[data-type='minus'][data-field='"+name+"']").removeAttr('disabled')
		} else {
			alert('Sorry, the minimum value was reached');
		    $(this).val($(this).data('oldValue'));
		}
		if(valueCurrent <= maxValue) {
			$(".btn-number[data-type='plus'][data-field='"+name+"']").removeAttr('disabled')
		} else {
			alert('Sorry, the maximum value was reached');
			$(this).val($(this).data('oldValue'));
		}
	});
	$(".input-number").keydown(function (e) {
		// Allow: backspace, delete, tab, escape, enter and .
		if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
			// Allow: Ctrl+A
			(e.keyCode == 65 && e.ctrlKey === true) || 
		    // Allow: home, end, left, right
			(e.keyCode >= 35 && e.keyCode <= 39)) {
		    // let it happen, don't do anything
		    return;
		}
		// Ensure that it is a number and stop the keypress
		if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
			e.preventDefault();
		}
	});
});
</script>
<style>


.quantity {
    float: left;
    margin-right: 15px;
    background-color: #eee;
    position: relative;
    width: 80px;
    overflow: hidden
}

.quantity input {
    margin: 0;
    text-align: center;
    width: 15px;
    height: 15px;
    padding: 0;
    float: right;
    color: #000;
    font-size: 20px;
    border: 0;
    outline: 0;
    background-color: #F6F6F6
}

.quantity input.qty {
    position: relative;
    border: 0;
    width: 100%;
    height: 40px;
    padding: 10px 25px 10px 10px;
    text-align: center;
    font-weight: 400;
    font-size: 15px;
    border-radius: 0;
    background-clip: padding-box
}

.quantity .minus, .quantity .plus {
    line-height: 0;
    background-clip: padding-box;
    -webkit-border-radius: 0;
    -moz-border-radius: 0;
    border-radius: 0;
    -webkit-background-size: 6px 30px;
    -moz-background-size: 6px 30px;
    color: #bbb;
    font-size: 20px;
    position: absolute;
    height: 50%;
    border: 0;
    right: 0;
    padding: 0;
    width: 25px;
    z-index: 3
}

.quantity .minus:hover, .quantity .plus:hover {
    background-color: #dad8da
}

.quantity .minus {
    bottom: 0
}
</style>

<div class="row" style="height:10px;"></div>
<div class="row">
	<div class="col-sm-12">
	<div class="card">
	<form:form method="POST" modelAttribute="shoppingCart" class="form-horizontal" enctype="multipart/form-data" action="shoppingCart">
		<div class="card-header bg-dark text-light">
	    	<i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;<spring:message code="Shopping cart" text="Shopping cart" />
	   		<div class="clearfix"></div>
	   </div>
       <div class="card-body">
       		<c:choose>
			<c:when test="${shoppingCart.getProductDtos().size() > 0}">	
       		<c:forEach var="product" items="${shoppingCart.productDtos}" varStatus="loop">
       		<form:hidden path="productDtos[${loop.index}].id" value="${product.id}" class="form-control input-sm"/>
       		<form:hidden path="productDtos[${loop.index}].name" value="${product.name}" class="form-control input-sm"/>
       		<form:hidden path="productDtos[${loop.index}].code" value="${product.code}" class="form-control input-sm"/>
       		<form:hidden path="productDtos[${loop.index}].perProductPrice" value="${product.perProductPrice}" class="form-control input-sm"/>
       		<form:hidden path="totalPrice" class="form-control input-sm"/>
		   		<div class="row">
			   		<div class="col-sm-3 text-center">
			            <img class="img-responsive" src="http://placehold.it/120x80" alt="prewiew" width="120" height="80">
			        </div>
			        <div class="col-sm-3">
			            <h5><strong>${product.name}</strong></h5>
			            <h6>
			                <small>${product.code}</small>
			            </h6>
			        </div>
			        <div class="col-sm-2 text-md-right">
			        	<h6><strong>${product.perProductPrice} <span class="text-muted">x</span></strong></h6>
			        </div>
			        <div class="col-sm-1">
			        	<div class="quantity">
			        		<input type="button" value="+" class="plus btn-number" data-type="plus" data-field="productDtos[${loop.index}].quantity">
          					<input type="number" id="productDtos${loop.index}.quantity" name="productDtos[${loop.index}].quantity" step="1" max="${product.availableQuantity}" min="1" value="${product.quantity}" title="Qty" class="qty input-number" size="4" readonly>
              				<input type="button" value="-" class="minus btn-number" data-type="minus" data-field="productDtos[${loop.index}].quantity">
		                </div>
			        </div>
			        <div class="col-sm-2">
			        	<input type="text" name="subTotal[${loop.index}]" value="${product.perProductPrice*product.quantity}" data-field="${product.perProductPrice}" class="form-control input-sm" readonly />
			        </div>
			        <div class="col-sm-1">
			        	<button type="button" class="btn btn-outline-danger btn-xs" onclick="callAjaxForDelete('${contextPath}/shoppingCart/${product.id}')">
				        	<i class="fa fa-trash" aria-hidden="true"></i>
				        </button>
			        </div>
		    	</div>
	        	<c:if test="${shoppingCart.getProductDtos().size() != (loop.index+1)}">
	        	<hr>
	        	</c:if>
        	</c:forEach>
        	</c:when>
			<c:otherwise>
				<spring:message code="There is no items in cart" text="There is no items in cart" />.
	            <a href="${contextPath}/products/all" class="pull-right"><spring:message code="Click here to add" text="Click here to add" /></a>
			</c:otherwise>
			</c:choose>
     	</div>
     	<c:if test="${shoppingCart.getProductDtos().size() > 0}">
     	<div class="card-footer">
	         <div class="pull-right" style="margin: 10px">
	         	 <button type="submit" class="btn btn-info pull-right"> <spring:message code="Checkout" text="Checkout" /></button>
	             <div class="pull-right" style="margin: 5px">
	                 <spring:message code="Total price" text="Total price" />: <b id="totalPriceLabel">${shoppingCart.totalPrice}</b>
	             </div>
	        </div>
    	</div>
    	</c:if>
    </form:form>
	</div>
	</div>
 </div>