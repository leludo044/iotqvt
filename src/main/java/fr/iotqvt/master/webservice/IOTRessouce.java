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

import fr.iotqvt.master.modele.dao.IOTDAO;
import fr.iotqvt.master.modele.jdbc.Jdbc;
import fr.iotqvt.master.modele.metier.IOT;

@Path("iot")
public class IOTRessouce {

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
	public List<IOT> getIOTs() {
		
		IOTDAO dao = new IOTDAO();
		Collection<IOT> iots = null;
		try {
			iots = dao.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (iots instanceof List){
			 return  (List<IOT>)iots;
		}else{
			return  new ArrayList<IOT>(iots);
		}
	
	}
//	@PreDestroy
//	public void detroy(){
//		 try {
//				Jdbc.getInstance().deconnecter();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	}
}
