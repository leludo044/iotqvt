package fr.iotqvt.master.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.iotqvt.master.infra.MesureEntrepot;
import fr.iotqvt.master.modele.Mesure;

@Path("mesure")
public class MesureRessouce {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Mesure> getMesures(){
		MesureEntrepot.getAll();
		
		return MesureEntrepot.getAll();
		
	}
}
