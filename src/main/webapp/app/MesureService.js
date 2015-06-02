'use strict';

iotqvt.service('MesureService', 
		function(WebSocketService, WebServiceFactory, _) {

			var mesures = [];
			var chartData = [];
            var capteurData = {};
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
                

                // Répartition des mesures dans les différents capteurs
                var key =data.capteur.iot.concat(data.capteur.id) ;                
                
                capteurData[key].mesures.push([ data.date, data.valeur ]);
                capteurData[key].last = {
                		valeur : data.valeur,
                		date : data.date
                } ;
                console.log(key, capteurData[key].mesures);
                console.log(chartData);
                
                // Valorisation des infos annexes
                _.assign(last, chartDataObject[key].last) ;
                                
//				_.assign(last, _.max(mesures, function(mesure) {
//					return mesure.date;
//				}));
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

			/*
			 * Enregistrement d'un jeu de mesures pour un capteur.
			 * Permet d'initialiser l'objet avant de le fournir au graphe
			 */
			var register = function(idCapteur) {
				capteurData[idCapteur] = {
						mesures: [],
						last : {
							valeur : 0
						}
				};
			};
			
			return {
				mesures : mesures,
				chartData : chartData,
				capteurData : capteurData,
				last : last,
				first : first,
				min : min,
				max : max,
				register : register
			};
		});
