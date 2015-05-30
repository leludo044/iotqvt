package fr.iotqvt.master.infra;

import java.net.URI;
import java.net.URISyntaxException;

import redis.clients.jedis.Jedis;

public class JedisFactory {

	private static Jedis jedis = null;
	
public static Jedis getInstance(){
	if(jedis==null){
		URI redisURI = null;
		try {
			redisURI = new URI("redis://:1nJi6I8z7Z8jJNfd@pub-redis-18189.eu-west-1-1.1.ec2.garantiadata.com:18189");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jedis = new Jedis(redisURI);
	}

	
	return jedis;
	
}


}
