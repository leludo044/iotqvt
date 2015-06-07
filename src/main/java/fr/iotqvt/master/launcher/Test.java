package fr.iotqvt.master.launcher;

import java.sql.SQLException;
import java.util.Collection;

import fr.iotqvt.master.modele.dao.IOTDao;
import fr.iotqvt.master.modele.jdbc.Jdbc;
import fr.iotqvt.master.modele.jdbc.JdbcFactory;
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
		 
		 
		IOTDao dao = new IOTDao();
		
		try {
			IOT iot1 = new IOT();
			iot1.setId("rasp-test");
			dao.create(iot1);
			
			Collection<IOT> iots = dao.getAll();
			for(IOT iot : iots){
				System.out.println(iot.getId());		
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
