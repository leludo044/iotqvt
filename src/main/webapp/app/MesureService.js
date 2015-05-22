'use strict';

iotqvt.service('MesureService', function(WebSocketService, _) {
	var mesures = [];
	var chartData = [];
	var last = {
		temp : null,
		date : null
	};
	var max = {
		temp : null,
		date : null
	};
	var min = {
		temp : null,
		date : null
	};
	WebSocketService.onReceiveData(function(data) {
		mesures.push(data);
		chartData.push([ data.date, data.temp / 1000 ]);
		_.assign(last, _.max(mesures, function(mesure) {
			return mesure.date;
		}));
		_.assign(max, _.max(mesures, function(mesure) {
			return mesure.temp;
		}));
		_.assign(min, _.min(mesures, function(mesure) {
			return mesure.temp;
		}));
	});
	return {
		mesures : mesures,
		chartData : chartData,
		last : last,
		min : min,
		max : max
	};
});
