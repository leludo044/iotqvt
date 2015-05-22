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

		
		this.capteur = $scope.capteur;
		
		
		
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
				plotLines : [ {
					value : 20,
					width : 1,
					color : '#ffffff'
				} ]
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