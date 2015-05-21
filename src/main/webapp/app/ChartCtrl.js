iotqvt.controller('ChartCtrl', [
		'MesureService',
		'$scope',
		'$rootScope',
		function(MesureService, $scope, $rootScope) {

			this.last = null;
			this.max = null;
			this.min = null;
			this.borneMax = 25;
			this.borneMin = -5;
			$scope.mesures = MesureService.mesures;

			$scope.$watchCollection('mesures', function(newMesure, oldMesure) {

				if ($scope.chartCtrl.config.series.length === 0
						&& newMesure.length > 0) {
					$scope.chartCtrl.config.series.push({
						name : newMesure[0].id,
						data : []
					});
				}
				var newMesureDiff = _.difference(newMesure, oldMesure);
				console.log(newMesureDiff);
				for (x in newMesureDiff) {
		
					$scope.chartCtrl.config.series[0].data.push([
							newMesureDiff[x].date, newMesureDiff[x].temp / 1000 ]);
					$scope.chartCtrl.last = (newMesureDiff[x].temp / 1000);
				
					if($scope.chartCtrl.last > $scope.chartCtrl.max ||  $scope.chartCtrl.max ==null){
						$scope.chartCtrl.max = $scope.chartCtrl.last ;
					}	
					if($scope.chartCtrl.last < $scope.chartCtrl.min ||  $scope.chartCtrl.min ==null){
						$scope.chartCtrl.min =  $scope.chartCtrl.last;
					}
				}
			});
			Highcharts.setOptions({
				global : {
					timezoneOffset : new Date().getTimezoneOffset()
				},
				lang : {
					loading : 'Chargement...',
					months : [ 'janvier', 'février', 'mars', 'avril',
							'mai', 'juin', 'juillet', 'août', 'septembre',
							'octobre', 'novembre', 'décembre' ],
					weekdays : [ 'dimanche', 'lundi', 'mardi', 'mercredi',
							'jeudi', 'vendredi', 'samedi' ],
					shortMonths : [ 'jan', 'fév', 'mar', 'avr', 'mai',
							'juin', 'juil', 'aoû', 'sep', 'oct', 'nov',
							'déc' ],
					exportButtonTitle : "Exporter",
					printButtonTitle : "Imprimer",
					rangeSelectorFrom : "Du",
					rangeSelectorTo : "au",
					rangeSelectorZoom : "Période",
					downloadPNG : 'Télécharger en PNG',
					downloadJPEG : 'Télécharger en JPEG',
					downloadPDF : 'Télécharger en PDF',
					downloadSVG : 'Télécharger en SVG',
					resetZoom : "Réinitialiser le zoom",
					resetZoomTitle : "Réinitialiser le zoom",
					thousandsSep : " ",
					decimalPoint : ','
				}
			});
			this.capteur = $scope.capteur;

			this.config = {
				options : {
					chart : {
						type : 'line'
					}
				},
				xAxis : {
					type : 'datetime',
					dateTimeLabelFormats : {
						month : '%e. %b',
						year : '%b'
					},
					title : {
						text : 'Date'
					}
				},
				yAxis : {
					title : {
						text : this.capteur.name + ' ' + this.capteur.unite
					},
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					},{
				        value:10,
				        color: '#ff0000',
				        width:2,
				        label:{text:'goal'}
				    } ]
			
					
				},
				tooltip : {
					valueSuffix : this.capteur.unite
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'middle',
					borderWidth : 0
				},
				series : [],
				title : {
					text : '',
					style : {
						display : 'none'
					}
				}

			};

		} ]);