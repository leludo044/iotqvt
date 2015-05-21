iotqvt.directive('capteur', function() {
	  return {
		    restrict: 'E',
		    transclude: true,
		    scope: {
		      capteur: '=capteur'
		    },
		    templateUrl: 'capteur.html'
		  };
		});