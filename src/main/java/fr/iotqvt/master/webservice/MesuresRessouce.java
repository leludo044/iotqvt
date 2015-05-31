package fr.iotqvt.master.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.iotqvt.master.infra.DAOMesure;
import fr.iotqvt.master.modele.Mesure;

@Path("mesures")
public class MesuresRessouce {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Mesure> getMesures(){
		DAOMesure.getAll();
		
		return DAOMesure.getAll();
		
	}
	

}
