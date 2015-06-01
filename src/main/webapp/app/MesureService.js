'use strict';

iotqvt.service('MesureService', 
		function(WebSocketService, WebServiceFactory, _) {

			var mesures = [];
			var chartData = [];
            var chartDataMap = new Map() ;
            var charDataObject = {};
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
                

                // Tentative de dispacth des mesures dans les différents capteurs
                var key = data.capteur.iot+':'+data.capteur.id ;
                if (!chartDataMap.has(key)) {
                    chartDataMap.set(key, new Array());
                }
                
                chartDataMap.get(key).push([ data.date, data.valeur ]);
//                console.log(chartDataMap.size) ;
//                console.log(key+' |' +chartDataMap.get(key));
//                console.log(chartData) ;

                var key2 =data.capteur.iot.concat(data.capteur.id) ;
                if (charDataObject[key2] === undefined )
                {
                    charDataObject[key2] = [] ;
                }
                charDataObject[key2].push([ data.date, data.valeur ]);
                // Fin de la tentative de dispatch
                
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
				chartDataMap : chartDataMap,
				last : last,
				first : first,
				min : min,
				max : max
			};
		});
