package fr.iotqvt.master.infra;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import fr.iotqvt.master.modele.Mesure;

public class MesureEntrepot {
private static ArrayList<Mesure> list = new ArrayList<Mesure>();


public static void ajouter(Mesure m){
	Jedis jedis = new Jedis(":1nJi6I8z7Z8jJNfd@pub-redis-18189.eu-west-1-1.1.ec2.garantiadata.com:18189");
	jedis.set(String.valueOf(m.getDate()), String.valueOf(m.getTemp()));
	jedis.close();
	list.add(m);
}

public static List<Mesure> getAll(){
	return list;
}

}
