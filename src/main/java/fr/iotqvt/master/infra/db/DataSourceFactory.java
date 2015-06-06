package fr.iotqvt.master.infra.db;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceFactory {
	 
    public static DataSource getMySQLDataSource() {
        URI dbUri= null;
		try {
			dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
			
		} catch (Exception e) {
			 try {
				dbUri = new URI("mysql://bf7596b269ec35:96131fcf@eu-cdbr-west-01.cleardb.com/heroku_32cfb72abf5da72?reconnect=true");
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
//
		String username = dbUri.getUserInfo().split(":")[0] ;
		String password = dbUri.getUserInfo().split(":")[1] ;
		
		MysqlDataSource	mysqlDS = new MysqlDataSource();
		
		mysqlDS.setUrl(dbUri.getHost());
		mysqlDS.setPort(3306);
		mysqlDS.setUser(username);
		mysqlDS.setPassword(password);
		mysqlDS.setAutoReconnect(true);
		mysqlDS.setDatabaseName(dbUri.getPath());
        return mysqlDS;
    }
     

         
}