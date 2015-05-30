package fr.iotqvt.master.infra;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import fr.iotqvt.master.modele.Mesure;

public class MesureEntrepot {
//private static ArrayList<Mesure> list = new ArrayList<Mesure>();


public static void ajouter(Mesure m){
	URI redisURI = null;
	  try {
		 redisURI = new URI("redis://:1nJi6I8z7Z8jJNfd@pub-redis-18189.eu-west-1-1.1.ec2.garantiadata.com:18189");
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Jedis jedis = new Jedis(redisURI);
	jedis.set("iot:test:capteur:"+m.getCapteur().getId()+":mesure:"+String.valueOf(m.getDate()), String.valueOf(m.getValeur()));
	jedis.close();
//	list.add(m);
}

public static List<Mesure> getAll(){
	ArrayList<Mesure> list = new ArrayList<Mesure>();
	URI redisURI = null;
	  try {
		 redisURI = new URI("redis://:1nJi6I8z7Z8jJNfd@pub-redis-18189.eu-west-1-1.1.ec2.garantiadata.com:18189");
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Jedis jedis = new Jedis(redisURI);
	Set<String> keys = jedis.keys("iot:test:capteur:*:mesure:*");
	for (String key : keys) {
		Mesure m = new Mesure();
		String value = jedis.get(key);
		m.setDate(Long.parseLong(key));
		m.setValeur(Float.valueOf(value));
	
		list.add(m);
	}
	jedis.close();
	Collections.sort(list);
	return list;
}

}
