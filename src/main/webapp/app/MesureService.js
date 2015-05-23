'use strict';

iotqvt.service('MesureService', function(WebSocketService,WebServiceFactory, _) {
	
	
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
	var first = {
		temp : null,
		date : null
	};
	WebServiceFactory.onReceiveData(function(data) {
		for(x in data){
			mesures.push(data[x]);
			chartData.push([ data[x].date, data[x].temp / 1000 ]);
		}

		_.assign(last, _.max(mesures, function(mesure) {
			return mesure.date;
		}));
		_.assign(first, _.min(mesures, function(mesure) {
			return mesure.date;
		}));
		_.assign(max, _.max(mesures, function(mesure) {
			return mesure.temp;
		}));
		_.assign(min, _.min(mesures, function(mesure) {
			return mesure.temp;
		}));


	});
	
	
	WebSocketService.onReceiveData(function(data) {
		mesures.push(data);
		chartData.push([ data.date, data.temp / 1000 ]);
		_.assign(last, _.max(mesures, function(mesure) {
			return mesure.date;
		}));
		_.assign(first, _.min(mesures, function(mesure) {
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
		first : first,
		min : min,
		max : max
	};
});
