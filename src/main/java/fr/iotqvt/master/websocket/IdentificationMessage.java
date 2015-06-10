package fr.iotqvt.master.websocket;

import com.google.gson.Gson;

import fr.iotqvt.master.modele.metier.IOT;

public class IdentificationMessage implements WsMessage {

	private String type ;
	private IOT cedec ;
	
	public IdentificationMessage(IOT cedec) {
		this.type = "identification" ;
		this.cedec = cedec ;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	@Override
	public String getType() {
		return type;
	}

	public IOT getCedec() {
		return this.cedec ;
	}
}
