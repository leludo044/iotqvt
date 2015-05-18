'use strict';

iotqvt.factory('MesureService', function(WebSocketService, _) {
	var mesures = [];
	WebSocketService.onReceiveData(function (data) {
		mesures.push(data);

      });

	return {
		mesures : mesures
	};
});
