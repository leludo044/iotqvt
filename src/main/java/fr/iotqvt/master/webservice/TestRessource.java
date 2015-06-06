package fr.iotqvt.master.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import fr.iotqvt.master.infra.db.*;
import fr.iotqvt.master.infra.db.DataSourceFactory;


@Path("test")
public class TestRessource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		String resultat = "";
		
		
		IOTs iots = new BdIOTs(DataSourceFactory.getMySQLDataSource());
		for (IOT iot : iots.iterate()){
		  System.out.println("id: " + iot.id());
		  resultat+=iot.id()+" "; 
		}
		
		
		return resultat;
		
	}
	

}
