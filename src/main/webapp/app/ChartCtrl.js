iotqvt.controller('ChartCtrl', ['MesureService', '$scope', '$rootScope', function (MesureService,$scope, $rootScope) {
    Highcharts.setOptions({
        chart: {
        	backgroundColor: 'rgba(255, 255, 255, 0)',
            plotBackgroundColor: 'rgba(255, 255, 255, 0.9)',
            plotShadow: false,
            
        },
        lang: {
            months: ['janvier', 'février', 'mars', 'avril', 'mai', 'juin',
                          'juillet', 'août', 'septembre', 'octobre', 'novembre', 'décembre'],
            weekdays: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 
                             'Jeudi', 'Vendredi', 'Samedi'],
            shortMonths: ['Jan', 'Fev', 'Mar', 'Avr', 'Mai', 'Juin', 'Juil',
                                'Aout', 'Sept', 'Oct', 'Nov', 'Déc'],
            decimalPoint: ',',
            downloadPNG: 'Télécharger en image PNG',
            downloadJPEG: 'Télécharger en image JPEG',
            downloadPDF: 'Télécharger en document PDF',
            downloadSVG: 'Télécharger en document Vectoriel',
            exportButtonTitle: 'Export du graphique',
            loading: 'Chargement en cours...',
            printButtonTitle: 'Imprimer le graphique',
            resetZoom: 'Réinitialiser le zoom',
            resetZoomTitle: 'Réinitialiser le zoom au niveau 1:1',
            thousandsSep: ' ',
            decimalPoint: ','
        },
        global: {
            timezoneOffset: -2 * 60
        }
    });

		this.last = MesureService.last;
		this.max = MesureService.max;
		this.min = MesureService.min;
		this.capteur = $scope.capteur;
		this.plotLines = [];
		$scope.range = { from: 0, to: 18 };
		 $scope.$watch('range', function() {
			 console.log('test');
//			 _.assign( $scope.chartCtrl.config.yAxis.plotLines[0],{value:$scope.range.to} );
//			 $scope.chartCtrl.config.yAxis.plotLines[0].value = range.to
			 $scope.chartCtrl.plotLines.push({
	             value: $scope.range.to,
	             color: 'red',
	             width: 1,
	             label: {
	                 text: 'max',
	                 align: 'left',
	                 style: {
	                     color: 'gray'
	                 }
	             }
	         });
			 console.log( $scope.chartCtrl.config.yAxis.plotLines);
		   });
		
//		 [{
//             value: 20,
//             color: 'red',
//             width: 1,
//             label: {
//                 text: 'max',
//                 align: 'left',
//                 style: {
//                     color: 'gray'
//                 }
//             }
//         },{
//             value: 10,
//             color: 'red',
//             width: 1,
//             label: {
//                 text: 'min',
//                 align: 'left',
//                 style: {
//                     color: 'gray'
//                 }
//             }
//         }]
		this.config =  {
	        options: {
	            chart: {
	                type: 'line'
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
					text : this.capteur.name+' '+this.capteur.unite
				},
				
	            plotLines: this.plotLines
			},
			tooltip : {
				valueSuffix : this.capteur.unite
			},
			 legend: {
		            enabled: false,
		            align: 'right',
		            verticalAlign: 'middle'
		        },
		
	        series:[{
	        	name : "test",
	        	data : MesureService.chartData
	        }],
	        title: {
	            text: '',
	            style: {
	                display: 'none'
	            }
	        },
	        loading:false
	    };
	


}]);