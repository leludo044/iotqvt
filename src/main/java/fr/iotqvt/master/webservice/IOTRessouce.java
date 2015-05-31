package fr.iotqvt.master.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.iotqvt.master.infra.DAOIOT;
import fr.iotqvt.master.modele.IOT;

@Path("iot")
public class IOTRessouce {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<IOT> getIOTs(){
		
		
		return DAOIOT.getAll();
		
	}
	

}