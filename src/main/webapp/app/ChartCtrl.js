iotqvt.controller('ChartCtrl', [
		'MesureService',
		'$scope',
		'$rootScope',
		function(MesureService, $scope, $rootScope) {
			Highcharts.setOptions({
				chart : {
					backgroundColor : 'rgba(255, 255, 255, 0)',
					plotBackgroundColor : 'rgba(255, 255, 255, 0.9)',
					plotShadow : false,

				},
				lang : {
					months : [ 'janvier', 'février', 'mars', 'avril', 'mai',
							'juin', 'juillet', 'août', 'septembre', 'octobre',
							'novembre', 'décembre' ],
					weekdays : [ 'Dimanche', 'Lundi', 'Mardi', 'Mercredi',
							'Jeudi', 'Vendredi', 'Samedi' ],
					shortMonths : [ 'Jan', 'Fev', 'Mar', 'Avr', 'Mai', 'Juin',
							'Juil', 'Aout', 'Sept', 'Oct', 'Nov', 'Déc' ],
					decimalPoint : ',',
					downloadPNG : 'Télécharger en image PNG',
					downloadJPEG : 'Télécharger en image JPEG',
					downloadPDF : 'Télécharger en document PDF',
					downloadSVG : 'Télécharger en document Vectoriel',
					exportButtonTitle : 'Export du graphique',
					loading : 'Chargement en cours...',
					printButtonTitle : 'Imprimer le graphique',
					resetZoom : 'Réinitialiser le zoom',
					resetZoomTitle : 'Réinitialiser le zoom au niveau 1:1',
					thousandsSep : ' ',
					decimalPoint : ','
				},
				global : {
					timezoneOffset : -2 * 60
				}
			});

			this.last = MesureService.last;
			this.max = MesureService.max;
			this.min = MesureService.min;
			this.capteur = $scope.capteur;
			var plotLines = [];
			this.range = {
				from : 0,
				to : 20
			};
			this.setPlot = function(range) {
				_.remove(plotLines, function(n) {
					return true;
				});
				plotLines.push({
					value : range.to,
					color : 'red',
					width : 1,
					label : {
						text : 'ref-max',
						align : 'left',
						style : {
							color : 'gray'
						}
					}
				});
				plotLines.push({
					value : range.from,
					color : 'red',
					width : 1,
					label : {
						text : 'ref-min',
						align : 'left',
						style : {
							color : 'gray'
						}
					}
				});
			};
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
						year : '%b',
					},
					tickColor: '#00004A',
				    tickWidth: 2,
				    minorGridLineWidth: 0,
		            minorTickInterval: 'auto',
		            minorTickColor: '#000000',
		            minorTickWidth: 1,
					title : {
						text : 'Date',
						style: { "color": "#00002E", "fontWeight": "bold" }
					}
				},
				yAxis : {
					title : {
						text : this.capteur.name + ' ' + this.capteur.unite,
						style: { "color": "#00002E", "fontWeight": "bold" }
					},
					minRange : 10,
					plotLines : plotLines,
					tickColor: '#00004A',
				    tickWidth: 2,
				    minorGridLineWidth: 0,
		            minorTickInterval: 'auto',
		            minorTickColor: '#000000',
		            minorTickWidth: 1
				},
				tooltip : {
					valueSuffix : this.capteur.unite
				},
				legend : {
					enabled : false,
					align : 'right',
					verticalAlign : 'middle'
				},

				series : [ {
					name : "Test",
					data : MesureService.chartData,
					color:"#00004A"
				}

				],
				title : {
					text : '',
					style : {
						display : 'none'
					}
				},
				loading : false
			};

		} ]);