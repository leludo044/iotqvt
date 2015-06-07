package fr.iotqvt.master.webservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.iotqvt.master.modele.dao.MesureDAO;
import fr.iotqvt.master.modele.metier.Mesure;

@Path("mesures")
public class MesuresRessouce {

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
	

}
