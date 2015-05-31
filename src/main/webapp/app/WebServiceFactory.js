iotqvt.factory('WebServiceFactory', ['$q', '$rootScope', '_', '$timeout', '$http' ,function($q, $rootScope, _, $timeout, $http) {
	var _onReceiveData = _.noop;
	
	
    var Service = {};

    var url = "http://" + document.location.hostname + ":" + document.location.port
    		+ document.location.pathname + "webservice/mesures";
	

    
    	   $http.get(url).success( function(data){
    		   listener(data) ;
    		   }
    		   
    	   );
    	   
    
 
    



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