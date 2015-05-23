iotqvt.factory('WebServiceFactory', ['$q', '$rootScope', '_', '$timeout' ,function($q, $rootScope, _, $timeout) {
	var _onReceiveData = _.noop;
	
	
    var Service = {};

    var ws = new WebSocket("http://" + document.location.hostname + ":" + document.location.port
    		+ document.location.pathname + "webservice/mesure");
	

    
    	   $http.get(ws).success( listener(data) );
    
 
    



    function listener(data) {
      $timeout(function () {
    	  console.log("webservice receiveData")
    	  _onReceiveData(data);
      });
	
    }

  Service.onReceiveData = function (f) {
		_onReceiveData = f;
	}
 
    return Service;
}])