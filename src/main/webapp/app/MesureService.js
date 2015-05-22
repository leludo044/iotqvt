'use strict';

iotqvt.factory('MesureService', function(WebSocketService, _) {
	var mesures = [];
	var chartData = [];
	WebSocketService.onReceiveData(function (data) {
		mesures.push(data);
		chartData.push([data.date, data.temp/1000]);
		console.log(chartData);
      });

	return {
		mesures : mesures,
		chartData : chartData
	};
});
