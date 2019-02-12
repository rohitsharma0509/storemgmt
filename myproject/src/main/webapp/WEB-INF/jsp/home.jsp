<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header.jsp"></jsp:include>
<script src="${contextPath}/resources/js/sockjs.min.js"></script>
<script src="${contextPath}/resources/js/stomp.min.js"></script>
<style>
.indicator {
	background-color: #dee2e6!important;
}
.indicator-body {
	color: black;
	min-height: 123px;
}
.chat-wrapper {
    position: fixed;
    z-index: 1;
    background: rgba(80,135,199,.9);
    color: #fff;
    height: 40px;
    padding: 10px;
}
.chat-wrapper > a {
	color: white;
}
#chat-page {
    position: relative;
    height: 100%;
}

#chat-page ul {
    list-style-type: none;
    background-color: #FFF;
    margin: 0;
    overflow: auto;
    overflow-y: scroll;
    padding: 0 20px 0px 20px;
    height: calc(100% - 150px);
}

#chat-page #messageForm {
    padding: 20px;
}

#chat-page ul li {
    line-height: 1.5rem;
    padding: 10px 20px;
    margin: 0;
    border-bottom: 1px solid #f4f4f4;
}

#chat-page ul li p {
    margin: 0;
}

#chat-page .event-message {
    width: 100%;
    text-align: center;
    clear: both;
}

#chat-page .event-message p {
    color: #777;
    font-size: 14px;
    word-wrap: break-word;
}

#chat-page .chat-message {
    padding-left: 68px;
    position: relative;
}

#chat-page .chat-message i {
    position: absolute;
    width: 42px;
    height: 42px;
    overflow: hidden;
    left: 10px;
    display: inline-block;
    vertical-align: middle;
    font-size: 18px;
    line-height: 42px;
    color: #fff;
    text-align: center;
    border-radius: 50%;
    font-style: normal;
    text-transform: uppercase;
}

#chat-page .chat-message span {
    color: #333;
    font-weight: 600;
}

#chat-page .chat-message p {
    color: #43464b;
}

#messageForm .input-group input {
    float: left;
    width: calc(100% - 85px);
}

#messageForm .input-group button {
    float: left;
    width: 80px;
    height: 38px;
    margin-left: 5px;
}

.chat-header {
    text-align: center;
    padding: 15px;
    border-bottom: 1px solid #ececec;
}

.chat-header h2 {
    margin: 0;
    font-weight: 500;
}

.connecting {
    padding-top: 5px;
    text-align: center;
    color: #777;
    position: absolute;
    top: 65px;
    width: 100%;
}


@media screen and (max-width: 730px) {

    .chat-container {
        margin-left: 10px;
        margin-right: 10px;
        margin-top: 10px;
    }
}

@media screen and (max-width: 480px) {
    .chat-container {
        height: calc(100% - 30px);
    }

    .username-page-container {
        width: auto;
        margin-left: 15px;
        margin-right: 15px;
        padding: 25px;
    }

    #chat-page ul {
        height: calc(100% - 120px);
    }

    #messageForm .input-group button {
        width: 65px;
    }

    #messageForm .input-group input {
        width: calc(100% - 70px);
    }

    .chat-header {
        padding: 10px;
    }

    .connecting {
        top: 60px;
    }

    .chat-header h2 {
        font-size: 1.1em;
    }
}
</style>
<script>
var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function openChatWindow() {
	$('#chatWindow').modal({show:true});
	connect();
}

function connect() {
	var chatPage = document.querySelector('#chat-page');
    username = document.querySelector('#name').value.trim();

    if(username) {
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/chat-messaging');
        stompClient = Stomp.over(socket);

        stompClient.connect({"X-CSRF-TOKEN": $("input[name='_csrf']").val()}, onConnected, onError);
    }
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/addChatUser", {}, JSON.stringify({from: username, type: 'JOIN'}) );
    var connectingElement = document.querySelector('.connecting');
    connectingElement.classList.add('d-none');
}


function onError(error) {
	var connectingElement = document.querySelector('.connecting');
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage() {
	var messageInput = document.querySelector('#message');
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            from: username,
            message: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.message = message.from + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.message = message.from + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.from.substring(0, 1));
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.from);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.from);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.message);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(from) {
    var hash = 0;
    for (var i = 0; i < from.length; i++) {
        hash = 31 * hash + from.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

function disconnect() {
	stompClient.disconnect();
}
</script>
<div class="row">
	<div class="col-sm-11" style="height: 40px;"><spring:message code="Dashboard" text="Dashboard" /></div>
	<div class="col-sm-1">
		<div class="chat-wrapper">
			<a href="#" onclick="openChatWindow()"><i class="fa fa-comments" aria-hidden="true"></i>&nbsp;Chat Now</a>
		</div>
	</div>
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
<div class="row">
	<div id="chatWindow" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">How may help you ?</h4>
					<button type="button" onclick="disconnect()" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div id="chat-page">
						<div class="chat-container">
							<div class="connecting d-none">Connecting...</div>
							<ul id="messageArea"></ul>
							<br>
							<div class="form-group">
								<div class="input-group clearfix">
									<input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control" />
									<input type="hidden" id="name" value="${pageContext.request.userPrincipal.name}" class="form-control" />
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button class="btn btn-info btn-sm" id="btn-send" onclick="sendMessage()">Send</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row" style="height: 50px;"></div>
<jsp:include page="footer.jsp"></jsp:include>