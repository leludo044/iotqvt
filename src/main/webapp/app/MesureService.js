'use strict';

iotqvt.service('MesureService',
		function(WebSocketService, WebServiceFactory, _) {

			var mesures = [];
			var chartData = [];
			var last = {
				valeur : null,
				date : null
			};
			var max = {
				valeur : null,
				date : null
			};
			var min = {
				valeur : null,
				date : null
			};
			var first = {
				valeur : null,
				date : null
			};
			WebServiceFactory.onReceiveData(function(data) {
				var x;
				for (x in data) {
					mesures.push(data[x]);
					chartData.push([ data[x].date, data[x].valeur ]);
				}

				_.assign(last, _.max(mesures, function(mesure) {
					return mesure.date;
				}));
				_.assign(first, _.min(mesures, function(mesure) {
					return mesure.date;
				}));
				_.assign(max, _.max(mesures, function(mesure) {
					return mesure.valeur;
				}));
				_.assign(min, _.min(mesures, function(mesure) {
					return mesure.valeur;
				}));

			});

			WebSocketService.onReceiveData(function(data) {
				mesures.push(data);
				chartData.push([ data.date, data.valeur ]);
				_.assign(last, _.max(mesures, function(mesure) {
					return mesure.date;
				}));
				_.assign(first, _.min(mesures, function(mesure) {
					return mesure.date;
				}));
				_.assign(max, _.max(mesures, function(mesure) {
					return mesure.valeur;
				}));
				_.assign(min, _.min(mesures, function(mesure) {
					return mesure.valeur;
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
