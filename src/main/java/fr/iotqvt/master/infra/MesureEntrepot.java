package fr.iotqvt.master.infra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import fr.iotqvt.master.modele.Mesure;

public class MesureEntrepot {
	

public static void ajouter(Mesure m){
	String key = "iot:test:capteur:"+m.getCapteur().getId()+":mesure:"+String.valueOf(m.getDate());
	String value = 	String.valueOf(m.getValeur());
	JedisFactory.getInstance().set(key, value);
	
}

public static List<Mesure> getAll(){

	
	List<Mesure> list = new ArrayList<Mesure>();
	Jedis jedis = 	JedisFactory.getInstance();
	Set<String> keys = jedis.keys("iot:test:capteur:*:mesure:*");
	for (String key : keys) {
		Mesure m = new Mesure();
		String value = jedis.get(key);
		m.setDate(Long.parseLong(key.substring(key.length()-10)));
		m.setValeur(Float.valueOf(value));
	
		list.add(m);
	}
	Collections.sort(list);
	return list;
}

}
