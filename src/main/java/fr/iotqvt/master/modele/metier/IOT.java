package fr.iotqvt.master.modele.metier;

import java.util.List;

public class IOT {
	private String id;
	private List<Capteur> capteurs;
//	private Piece piece;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Capteur> getCapteurs() {
		return capteurs;
	}
	public void setCapteurs(List<Capteur> capteurs) {
		this.capteurs = capteurs;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IOT [id=");
		builder.append(id);
		builder.append(", capteurs=");
		builder.append(capteurs);

		builder.append("]");
		return builder.toString();
	}
}
