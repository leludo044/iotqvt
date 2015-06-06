package fr.iotqvt.master.infra.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.jcabi.aspects.Immutable;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

@Immutable
class BdIOTs implements IOTs {
	  private final DataSource dbase;
	  public BdIOTs(DataSource data) {
	    this.dbase =  data;
	  }
	@Override
	public Iterable<IOT> iterate() {
		 try {
			return new JdbcSession(this.dbase)
			  .sql("SELECT id FROM iot")
			  .select(
			    new ListOutcome<IOT>(
			      new ListOutcome.Mapping<IOT>() {
			        @Override
			        public IOT map(final ResultSet rset) {
			          try {
						return new BdIOT(dbase, rset.getString(1));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
			        }
			      }
			    ));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	 
	@Override
	public IOT add(String id) {
		   try {
				return new BdIOT(
				  this.dbase,
				  new JdbcSession(this.dbase)
				    .sql("INSERT INTO iot (id) VALUES (?)")
				    .set(id)
				    
				    .insert(new SingleOutcome<String>(String.class))
				);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}

 

}