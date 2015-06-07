package fr.iotqvt.master.modele.metier;

public class Capteur {
	private String id;
	private TypeCapteur typeCapteur;
	private Integer frequenceMesures;
	private String modele;
	private Float refMin;
	private Float refMax;
	private String iot;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TypeCapteur getTypeCapteur() {
		return typeCapteur;
	}

	public void setTypeCapteur(TypeCapteur typeCapteur) {
		this.typeCapteur = typeCapteur;
	}

	public Integer getFrequenceMesures() {
		return frequenceMesures;
	}

	public void setFrequenceMesures(Integer frequenceMesures) {
		this.frequenceMesures = frequenceMesures;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public Float getRefMin() {
		return refMin;
	}

	public void setRefMin(Float refMin) {
		this.refMin = refMin;
	}

	public Float getRefMax() {
		return refMax;
	}

	public void setRefMax(Float refMax) {
		this.refMax = refMax;
	}

	public String getIot() {
		return iot;
	}

	public void setIot(String iot) {
		this.iot = iot;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Capteur [id=");
		builder.append(id);
		builder.append(", typeCapteur=");
		builder.append(typeCapteur);
		builder.append(", frequenceMesures=");
		builder.append(frequenceMesures);
		builder.append(", modele=");
		builder.append(modele);
		builder.append(", refMin=");
		builder.append(refMin);
		builder.append(", refMax=");
		builder.append(refMax);
		builder.append(", iot=");
		builder.append(iot);
		builder.append("]");
		return builder.toString();
	}

}
