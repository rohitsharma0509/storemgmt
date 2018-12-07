<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script src="${contextPath}/resources/js/popper.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/c560c025cf.js"></script>
<style>
body {
    font-family: 'Ropa Sans', sans-serif;
    margin-top: 30px;
    background-color: #331cf34f;
    text-align: center;
    color: #fff;
}
.error-heading {
	margin: 50px auto;
	width: 250px;
	border: 5px solid #fff;
	font-size: 126px;
	line-height: 126px;
	border-radius: 30px;
	text-shadow: 6px 6px 5px #000;
}

.error-heading img {
	width: 100%;
}

.error-main h1 {
	text-align: center;
	font-size: 72px;
	margin: 0px;
	color: #F3661C;
	text-shadow: 0px 0px 5px #fff;
}

.error-actions {margin-top:15px;margin-bottom:15px;}
.error-actions .btn { margin-right:10px; }

</style>
<body>
<div class="error-main">
	<h1>Oops!</h1>
	<div class="error-heading">${code}</div>
	<p>${message}</p>
	<div class="error-actions">
		<a href="${contextPath}/home" class="btn btn-success btn-lg">
			<i class="fa fa-home" aria-hidden="true"></i> Take Me Home 
		</a>
	</div>
</div>
</body>