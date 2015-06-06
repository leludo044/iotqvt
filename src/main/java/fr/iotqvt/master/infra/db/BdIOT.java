package fr.iotqvt.master.infra.db;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.jcabi.aspects.Immutable;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;



	@Immutable
	final public class BdIOT implements IOT {
	  private final DataSource dbase;
	  private String id;
	  public BdIOT(DataSource data, String id) {
	    this.dbase = data;
	    this.id = id;
	  }
	  public String id() {
	    try {
			return new JdbcSession(this.dbase)
			  .sql("SELECT id FROM post WHERE id = ?")
			  .set(this.id)
			  .select(new SingleOutcome<String>(String.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	  }

	}

