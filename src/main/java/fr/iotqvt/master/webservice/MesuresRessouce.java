package fr.iotqvt.master.webservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.iotqvt.master.modele.dao.MesureDAO;
import fr.iotqvt.master.modele.jdbc.Jdbc;
import fr.iotqvt.master.modele.metier.Mesure;

@Path("mesures")
public class MesuresRessouce {

	@PostConstruct
	public void init(){
		 try {
				Jdbc.getInstance().connecter();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Mesure> getMesures(){
		MesureDAO mesureDao = new MesureDAO();
		Collection<Mesure> mesures = null;
		try {
		 mesures =	mesureDao.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(mesures instanceof List){
			return (List<Mesure>) mesures;
			
		}else{
			return new ArrayList<Mesure>(mesures);
		}
	}	
	@PreDestroy
	public void detroy(){
		 try {
				Jdbc.getInstance().deconnecter();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

}
