package fr.iotqvt.master.infra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import fr.iotqvt.master.modele.IOT;

public class DAOIOT {
	public static void ajouter(IOT i) {
		String key = "iot:" + i.getId();
		String value = i.getId();
		
		JedisFactory.getInstance().hset(key, "id", value);

	}

	public static List<IOT> getAll() {
		List<IOT> resultat = new ArrayList<IOT>();
	Set<String> set= new HashSet<String>();
		Jedis jedis = JedisFactory.getInstance();
		Set<String> keys = jedis.keys("iot:*");
		for (String key : keys) {
			set.add(key.substring(4, key.indexOf(":capteur")));
		}
		for(String key : set){
			IOT iot = new IOT();
			iot.setId(key);
			resultat.add(iot);
		}
	
		return resultat;
	}


}
