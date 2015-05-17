iotqvt.controller('ChartCtrl', ['MesureService', '$scope', '$rootScope', function (MesureService,$scope, $rootScope) {

	$scope.mesures =	MesureService.mesures;
		


	$scope.$watchCollection('mesures', function(newMesure,  oldMesure) {
		for(x in newMesure){
			 $scope.highchartsNG.series[0].data.push([newMesure[x].date,newMesure[x].temp/1000]);
		}
		
		});

	

    $scope.highchartsNG = {
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
				text : 'Temperature (°C)'
			},
			plotLines : [ {
				value : 0,
				width : 1,
				color : '#808080'
			} ]
		},
		tooltip : {
			valueSuffix : '°C'
		},
		legend : {
			layout : 'vertical',
			align : 'right',
			verticalAlign : 'middle',
			borderWidth : 0
		},
        series:[{
        	name:"rasp",
        	data:[]
        		
        }],
        title: {
            text: 'Temperature'
        },
        loading: false
    }


}]);