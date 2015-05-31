package fr.iotqvt.master.infra;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import fr.iotqvt.master.modele.Mesure;

public class DAOMesure {

	public static void ajouter(Mesure m) {
		String key = "iot:" + m.getCapteur().getIot() + ":capteur:"
				+ m.getCapteur().getId() + ":mesure:" + m.getDateString();
		String value = String.valueOf(m.getValeur());
		JedisFactory.getInstance().set(key, value);

	}

	public static List<Mesure> getAll() {

		List<Mesure> list = new ArrayList<Mesure>();
		Jedis jedis = JedisFactory.getInstance();
		Set<String> keys = jedis.keys("iot:*:capteur:*:mesure:*");
		for (String key : keys) {
			Mesure m = new Mesure();
			String value = jedis.get(key);
			try {
				m.setDateString(key.substring(key.length() - 14));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			m.setValeur(Float.valueOf(value));

			list.add(m);
		}
		Collections.sort(list);
		return list;
	}

	public static List<Mesure> getByCapteur(String iot, String capteur) {

		List<Mesure> list = new ArrayList<Mesure>();
		Jedis jedis = JedisFactory.getInstance();
		Set<String> keys = jedis.keys("iot:" + iot + ":capteur:" + capteur
				+ ":mesure:*");
		for (String key : keys) {
			Mesure m = new Mesure();
			String value = jedis.get(key);
			try {
				m.setDateString(key.substring(key.length() - 14));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			m.setValeur(Float.valueOf(value));

			list.add(m);
		}
		Collections.sort(list);
		return list;
	}

}
