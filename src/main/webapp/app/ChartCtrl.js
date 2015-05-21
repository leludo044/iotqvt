iotqvt.controller('ChartCtrl', ['MesureService', '$scope', '$rootScope', function (MesureService,$scope, $rootScope) {

	$scope.mesures =	MesureService.mesures;
		
	$scope.$watchCollection('mesures', function(newMesure,  oldMesure) {
		console.log("new");
		console.log(newMesure);
		console.log("old");
		console.log(oldMesure);
		if(oldMesure.length ===0 && newMesure.length > 0){
			 $scope.chartCtrl.config.series.push({name:newMesure[0].id, data:[]});
		}
		for(x in newMesure){
		
			 $scope.chartCtrl.config.series[0].data.push([newMesure[x].date,newMesure[x].temp/1000]);
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
					value : 0,
					width : 1,
					color : '#808080'
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
	        series:[],
	        title: {
	            text: '',
	            style: {
	                display: 'none'
	            }
	        }
	    };
	


}]);