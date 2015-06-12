'use strict';

iotqvt.service('MesureService',
		function(WebSocketService, WebServiceFactory, _, $rootScope) {
			console.log("new MesureService");

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
			var soleil = true;

			WebServiceFactory.onReceiveData(function(data) {
				var x;
				for (x in data) {
					pushMesure(data[x]);
				}
			});

			WebSocketService.onReceiveData(function(data) {
				if (data.type === "mesure") {
					var mesure = data.mesure;
					pushMesure(mesure);
				}
			});
			function pushMesure(mesure) {
//				var mesure = data.mesure;
				// Arrondi de la valeur à 1 chiffre après la virgule 
				mesure.valeur = Math.round(mesure.valeur*10)/10;


				// Répartition des mesures dans les différents capteurs
				var key = mesure.capteur.iot.concat(mesure.capteur.id);
				if(typeof capteurData[key]  === 'undefined'){
					capteurData[key] = {mesures:[], max :{} , min:{}, soleil:{}, capteur:{}};
				}
				if(typeof capteurData[key].last  !== 'undefined' && capteurData[key].last.date > mesure.date){
					return;
				}
				capteurData[key].mesures.push([ mesure.date, mesure.valeur ]);

				// Collecte et assignation des données annexes
				/*
				 * L'utilisation de _.assign est indispensable pour ne
				 * réaffecter que les valeurs dans l'objet et non pas lui
				 * assigner une nouvelle référence
				 */
				_.assign(capteurData[key].last, {
					valeur : mesure.valeur,
					date : mesure.date
				});

				if (capteurData[key].max.valeur == null
						|| capteurData[key].max.valeur < mesure.valeur) {
					_.assign(capteurData[key].max, {
						valeur : mesure.valeur,
						date : mesure.date
					});
				}

				if (capteurData[key].min.valeur == null
						|| capteurData[key].min.valeur > mesure.valeur) {
					_.assign(capteurData[key].min, {
						valeur : mesure.valeur,
						date : mesure.date
					});
				}

				/*
				 * On vérifie si on est dans les seuils pour mettre à jour l'état du capteur
				 */
				if ((mesure.valeur > capteurData[key].capteur.refMax) || (mesure.valeur < capteurData[key].capteur.refMin)) {
					_.assign(capteurData[key].soleil, {
						valeur : false
					});
				} else {
					_.assign(capteurData[key].soleil, {
						valeur : true
					});
				}
				
				/*
				 * On informe le $rootScope des états de tous les capteurs
				 */
				$rootScope.changeMeteo(capteurData);

			}

			/*
			 * Enregistrement et initialisation d'un réceptacle de mesures pour
			 * un capteur.
			 */
			var register = function(capteur, idCapteur) {
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
					}, 
					capteur : capteur
				};
			};

			var loadMesure = function(capteur){
				WebServiceFactory.load();
			}
			return {
				capteurData : capteurData,
				last : last,
				first : first,
				min : min,
				max : max,
				soleil : soleil,
				register : register,
				loadMesure :loadMesure
			};
		});
