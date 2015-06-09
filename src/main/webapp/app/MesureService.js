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
			var soleil = false;

			WebServiceFactory.onReceiveData(function(data) {
				var x;
				for (x in data) {
					// mesures.push(data[x]);
					// chartData.push([ data[x].date, data[x].valeur ]);
					pushData(data[x]);
				}

				// _.assign(last, _.max(mesures, function(mesure) {
				// return mesure.date;
				// }));
				// _.assign(first, _.min(mesures, function(mesure) {
				// return mesure.date;
				// }));
				// _.assign(max, _.max(mesures, function(mesure) {
				// return mesure.valeur;
				// }));
				// _.assign(min, _.min(mesures, function(mesure) {
				// return mesure.valeur;
				// }));

			});

			WebSocketService.onReceiveData(function(data) {
				pushData(data);
			});
			function pushData(data) {
				mesures.push(data);
				chartData.push([ data.date, data.valeur ]);

				// Répartition des mesures dans les différents capteurs
				var key = data.capteur.iot.concat(data.capteur.id);
				capteurData[key].mesures.push([ data.date, data.valeur ]);

				// Collecte et assignation des données annexes
				/*
				 * L'utilisation de _.assign est indispensable pour ne
				 * réaffecter que les valeurs dans l'objet et non pas lui
				 * assigner une nouvelle référence
				 */
				_.assign(capteurData[key].last, {
					valeur : data.valeur,
					date : data.date
				});

				if (capteurData[key].max.valeur == null
						|| capteurData[key].max.valeur < data.valeur) {
					_.assign(capteurData[key].max, {
						valeur : data.valeur,
						date : data.date
					});
				}

				if (capteurData[key].min.valeur == null
						|| capteurData[key].min.valeur > data.valeur) {
					_.assign(capteurData[key].min, {
						valeur : data.valeur,
						date : data.date
					});
				}

				if (data.valeur > 22) {
					_.assign(capteurData[key].soleil, {
						valeur : false
					});
				} else {
					_.assign(capteurData[key].soleil, {
						valeur : true
					});
				}

				console.log(capteurData[key].soleil);
			}

			/*
			 * Enregistrement et initialisation d'un réceptacle de mesures pour
			 * un capteur.
			 */
			var register = function(idCapteur) {
				capteurData[idCapteur] = {
					mesures : [],
					last : {},
					max : {
						valeur : null,
						date : null
					},
					min : {
						valeur : null,
						date : null
					},
					soleil : {
						valeur : false
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
				soleil : soleil,
				register : register
			};
		});
