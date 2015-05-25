package fr.iotqvt.master.infra;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import fr.iotqvt.master.modele.Mesure;

public class MesureEntrepot {
private static ArrayList<Mesure> list = new ArrayList<Mesure>();


public static void ajouter(Mesure m){
	URI redisURI = null;
	  try {
		 redisURI = new URI(":1nJi6I8z7Z8jJNfd@pub-redis-18189.eu-west-1-1.1.ec2.garantiadata.com:18189");
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Jedis jedis = new Jedis(redisURI);
	jedis.set(String.valueOf(m.getDate()), String.valueOf(m.getTemp()));
	jedis.close();
	list.add(m);
}

public static List<Mesure> getAll(){
	return list;
}

}
