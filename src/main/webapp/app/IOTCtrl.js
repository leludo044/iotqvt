iotqvt.controller('IOTCtrl' , ['IOTService',function (IOTService) {

//	this.iots = [{id:'rasp-amaury', statut:"rouge", capteurs:[{name:"Temperature", couleur:"bleu", statut:"vert", unite:"°C"}]},{id:'rasp-ludo', statut:"rouge", capteurs:[{name:"Temperature" , unite:"°C", couleur:"bleu", statut:"vert"}]}, {id:'rasp-jerome', statut:"vert", capteurs:[{name:"Temperature" , couleur:"bleu",  statut:"rouge", unite:"°C"},{name:"Bruit",  couleur:"orange",  statut:"rouge", unite:"dB"},{name:"Luminosite",  couleur:"violet",  statut:"vert", unite:"Lux"}]}];
	
	this.iots = IOTService.iots;
	this.iotSelect = null;
	
	this.entites = [{regate:'444150', libelle:"PAGEOT CSORH", pieces:['011', '012']},
	                {regate:'444180', libelle:"PAGEOT CSOFI", pieces:['425', '426', '427']}];
	this.entiteSelect = null ;

	this.pieces = null ;
	
}]);