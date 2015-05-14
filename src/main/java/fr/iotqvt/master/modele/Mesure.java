package fr.iotqvt.master.modele;

public class Mesure {
	
	private int temp;
	private long date;
	private String id;
	
	
	public Mesure() {
		
	}
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
