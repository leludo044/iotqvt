package fr.iotqvt.master.launcher;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import fr.iotqvt.master.modele.jdbc.Jdbc;
import fr.iotqvt.master.modele.jdbc.JdbcFactory;

public class Main {

	public static void main(String[] args) throws ServletException, LifecycleException, MalformedURLException {
	    JdbcFactory.creer();
	
	    String webappDirLocation = "./src/main/webapp/";
	    String contextPath = "/";
	    if(args.length ==1){
	    	 webappDirLocation = args[0];
	    	 contextPath = "";
	    }
        Tomcat tomcat = new Tomcat();
  
        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        Context context =  tomcat.addWebapp(contextPath, new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File( webappDirLocation).getAbsolutePath());
        File configFile = new File(webappDirLocation + "/WEB-INF/web.xml");
        context.setConfigFile(configFile.toURI().toURL());
        try {
	    	
    			Jdbc.getInstance().connecter();
    			System.out.println("connexion BDD OK");
    		} catch (ClassNotFoundException | SQLException e1) {
    			System.out.println("ERREUR connexion BDD");
    			e1.printStackTrace();
    		}
        tomcat.start();
        
        tomcat.getServer().await();

        }

}
