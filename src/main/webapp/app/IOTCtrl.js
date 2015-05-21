iotqvt.controller('IOTCtrl' , function () {

	this.iots = [{id:'rasp-1', capteurs:[{name:"Temperature", unite:"°C"}]},{id:'rasp-2',capteurs:[]}, {id:'rasp-3',capteurs:[{name:"Temperature" , unite:"°C"},{name:"Bruit", unite:"dB"},{name:"Lum", unite:"dB"}]}];
	this.iotSelect = null;

});