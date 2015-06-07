package fr.iotqvt.master.launcher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import fr.iotqvt.master.modele.dao.IOTDAO;
import fr.iotqvt.master.modele.jdbc.Jdbc;
import fr.iotqvt.master.modele.jdbc.JdbcFactory;
import fr.iotqvt.master.modele.metier.Capteur;
import fr.iotqvt.master.modele.metier.IOT;


public class Test {

	public static void main(String[] args) {
		 JdbcFactory.creer();
		 try {
			Jdbc.getInstance().connecter();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 
		IOTDAO dao = new IOTDAO();
		
		try {
			IOT iot1 = new IOT();
			ArrayList<Capteur> capteurs = new ArrayList<Capteur>();
			Capteur cap = new Capteur();
			cap.setId("03");
			capteurs.add(cap);
			iot1.setId("rasp-test4");
			iot1.setCapteurs(capteurs);
			dao.create(iot1);
			
			Collection<IOT> iots = dao.getAll();
			for(IOT iot : iots){
				System.out.println(iot);		
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
