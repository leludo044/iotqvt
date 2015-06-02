package fr.iotqvt.master.infra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import fr.iotqvt.master.modele.Capteur;

public class DAOCapteur {

	public static List<Capteur> getByIOT(String iot) {
		List<Capteur> resultat = new ArrayList<Capteur>();
	Set<String> set= new HashSet<String>();
		Jedis jedis = JedisFactory.getInstance();
		Set<String> keys = jedis.keys("iot:"+iot+":capteur:*");
		for (String key : keys) {
			set.add(key.substring(key.indexOf(":capteur")+9, key.indexOf(":mesure")));
		}
		for(String key : set){
			Capteur capteur = new Capteur();
			capteur.setId(key);
			capteur.setIot(iot);
			resultat.add(capteur);
		}
	
		return resultat;
	}


}
