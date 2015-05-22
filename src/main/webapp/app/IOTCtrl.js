iotqvt.controller('IOTCtrl' , function () {

	this.iots = [{id:'rasp-amaury', capteurs:[{name:"Temperature", unite:"°C"}]},{id:'rasp-ludo',capteurs:[]}, {id:'rasp-jerome',capteurs:[{name:"Temperature" , unite:"°C"},{name:"Bruit", unite:"dB"},{name:"Luminosite", unite:"Lux"}]}];
	this.iotSelect = null;

});