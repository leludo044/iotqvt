iotqvt.factory('WebSocketService', ['$q', '$rootScope', '_', '$timeout' ,function($q, $rootScope, _, $timeout) {
	var _onReceiveData = _.noop;
	
	
    var Service = {};

    var ws = new WebSocket("ws://" + document.location.hostname + ":" + document.location.port
    		+ document.location.pathname + "websocket/mesure");
	
    ws.onopen = function(){  
        console.log("Connexion");  
    };
    
    ws.onmessage = function(message) {
        listener(JSON.parse(message.data));
    };



    function listener(data) {
      $timeout(function () {
    	  _onReceiveData(data);
      });
	
    }

  Service.onReceiveData = function (f) {
		_onReceiveData = f;
	}
 
    return Service;
}])