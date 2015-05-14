package fr.iotqvt.master.infra;

import java.util.ArrayList;
import java.util.List;

import fr.iotqvt.master.modele.Mesure;

public class MesureEntrepot {
private static ArrayList<Mesure> list = new ArrayList<Mesure>();


public static void ajouter(Mesure m){
	list.add(m);
}

public static List<Mesure> getAll(){
	return list;
}

}
