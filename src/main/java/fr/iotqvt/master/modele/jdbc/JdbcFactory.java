package fr.iotqvt.master.modele.jdbc;


public class JdbcFactory {
	  /**
     * instancier le singleton de connexion Jdbc en fonction d'un fichier de paramètres
     *
     * @param ficPropertiesJdbc nom du fichier de properties
     */
    public static void creer()  {
	
//			dbUri = new URI("mysql://bf7596b269ec35:96131fcf@eu-cdbr-west-01.cleardb.com/heroku_32cfb72abf5da72?reconnect=true");
			
			String pilote ="com.mysql.jdbc.Driver";
			String protocole ="jdbc:mysql:";
			String url = "//eu-cdbr-west-01.cleardb.com/";
			String base = "heroku_32cfb72abf5da72";
			String utilisateur="bf7596b269ec35";
			String mdp ="96131fcf";
			
	        Jdbc.creer(pilote,protocole,url, base,utilisateur, mdp);
	 

    }
}
