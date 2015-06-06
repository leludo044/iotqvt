package fr.iotqvt.master.launcher;

import fr.iotqvt.master.infra.db.BdIOTs;
import fr.iotqvt.master.infra.db.DataSourceFactory;
import fr.iotqvt.master.infra.db.IOT;
import fr.iotqvt.master.infra.db.IOTs;

public class Test {

	public static void main(String[] args) {
		System.out.println("test");
		IOTs iots = new BdIOTs(DataSourceFactory.getMySQLDataSource());
		for (IOT iot : iots.iterate()){
		  System.out.println("id: " + iot.id());
		}
	}
}
