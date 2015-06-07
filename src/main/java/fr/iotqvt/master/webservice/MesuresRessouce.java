package fr.iotqvt.master.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.iotqvt.master.modele.metier.Mesure;

@Path("mesures")
public class MesuresRessouce {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Mesure> getMesures(){
		return null;
//		DAOMesure.getAll();
//		
//		return DAOMesure.getAll();
//		
	}
	

}
