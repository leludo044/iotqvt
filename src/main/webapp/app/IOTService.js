'use strict';

iotqvt.service('IOTService', ['$http',
                              
		function($http) {

			var iots = [];
			
			
		    var url = "http://" + document.location.hostname + ":" + document.location.port
    		+ document.location.pathname + "webservice/iot";
	

    
    	   $http.get(url).success( function(data){
    			var x;
				for (x in data) {
					data[x].capteur[0].couleur = "bleu";
					iots.push(data[x]);
				}
    	   }
    		   
    	   );
		
			return {
				iots : iots
			};
		}]);
