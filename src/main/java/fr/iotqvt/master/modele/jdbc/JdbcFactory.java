package fr.iotqvt.master.modele.jdbc;

import java.net.URI;
import java.net.URISyntaxException;

public class JdbcFactory {
	/**
	 * Instanciation du singleton de connexion Jdbc en fonction de la variable
	 * d'environnement CLEARDB_DATABASE_URL
	 * Si la variable n'est pas trouvée c'est l'accès direct à la base Heroku qui est utilisée
	 */
	public static void creer() {

		/*
		 * CLEARDB_DATABASE_URL =
		 * mysql://bf7596b269ec35:96131fcf@eu-cdbr-west-01.cleardb.com/heroku_32cfb72abf5da72?reconnect=true
		 */

		URI dbUri = null;
		try {
			String var = System.getenv("CLEARDB_DATABASE_URL") ;
			dbUri = new URI(var);
			System.out.println("Variable CLEARDB_DATABASE_URL trouvée : "+var);
		} catch (Exception e) {
			try {
				dbUri = new URI(
						"mysql://bf7596b269ec35:96131fcf@eu-cdbr-west-01.cleardb.com/heroku_32cfb72abf5da72?reconnect=true");
				System.out.println("Variable CLEARDB_DATABASE_URL non trouvée ! Connexion Heroku effectuée !");
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		String pilote = "com.mysql.jdbc.Driver";
		String protocole = "jdbc:mysql:";
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String url = "//" + dbUri.getHost() + "/";
		String base = dbUri.getPath().substring(1);

		Jdbc.creer(pilote, protocole, url, base, username, password);

	}
}
