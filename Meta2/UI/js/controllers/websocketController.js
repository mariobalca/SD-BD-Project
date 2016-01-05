myApp.controller('websocketController', ['IPAddress', '$scope', '$log', '$http', 'authService', '$location', function(IPAddress, $scope, $log, $http, authService, $location){
	var websocket = null;
	var notifications = false;

	window.onload = function() { // URI = ws://10.16.0.165:8080/WebSocket/ws
	    connect('ws://' + IPAddress + ':8080' + "/endpoint/" + authService.user.id);
	}

	function connect(host) { // connect to the host websocket
	    if ('WebSocket' in window)
	        websocket = new WebSocket(host);
	    else if ('MozWebSocket' in window)
	        websocket = new MozWebSocket(host);
	    else {
	        writeToHistory('Get a real browser which supports WebSocket.');
	        return;
	    }

	    websocket.onopen    = onOpen; // set the event listeners below
	    websocket.onclose   = onClose;
	    websocket.onmessage = onMessage;
	    websocket.onerror   = onError;
	}

	function onOpen(event) {
	    $log.info('Connected to ' + window.location.host + '.');
	}

	function onClose(event) {
	    $log.info('WebSocket closed.');
	}

	function onMessage(message) { // print the received message
	    writeToHistory(message.data);
	}

	function onError(event) {
	    $log.info('WebSocket error (' + event.data + ').');
	}

	function writeToHistory(json) {
		var obj = jQuery.parseJSON(json)
		if(obj.action == 'message')
		{
			$('#notifications').append('<div class="alert alert-info alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>New message on <a href="#/projects/' + obj.projectId + '">this project</a></strong></div>');
		}
		if(obj.action == 'pledge')
		{
	    	$('#notifications').append('<div class="alert alert-info alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><strong>New pledge of ' + obj.value + ' on <a href="#/projects/' + obj.projectId + '">this project</a></strong></div>');
	    	$('.value').each(function(){
	    		if($(this).data('projectId') == projectId)
	    			$(this).html(obj.value)
	    	})
		}
		if(obj.action == 'newValue'){
			$('.value').each(function(){
	    		if($(this).data('projectId') == projectId)
	    			$(this).html(obj.value)
	    	})
		}
		notifications = true;
	}
	$scope.hasNotifications = function(){
		return notifications;
	}
}]);