package fr.iotqvt.master.modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

public class Mesure implements Comparable<Mesure> {
	
	private Float valeur;
	private long date;
	private Capteur capteur;
	

	public Mesure(Float valeur, long date, Capteur capteur) {
		super();
		this.valeur = valeur;
		this.date = date;
		this.capteur = capteur;
	}

	public Mesure() {
		
	}
	
	public Capteur getCapteur() {
		return capteur;
	}

	public void setCapteur(Capteur capteur) {
		this.capteur = capteur;
	}

	public Float getValeur() {
		return valeur;
	}
	public void setValeur(Float valeur) {
		this.valeur = valeur;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	
	
	public String getDateString(){
		
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(this.getDate()));
	}
	public void setDateString(String string) throws ParseException {
		this.setDate(new SimpleDateFormat("yyyyMMddHHmmss").parse(string).getTime());
		
	}
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	@Override
	public int compareTo(Mesure o) {
		if ( this.getDate() <  o.getDate())
	        return -1;
	    if (this.getDate() >(o.getDate()))
	        return 1;
	    
		return 0;
	    
	}



}
