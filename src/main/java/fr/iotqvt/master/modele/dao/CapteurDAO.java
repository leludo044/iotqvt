package fr.iotqvt.master.modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import fr.iotqvt.master.modele.metier.Capteur;

public class CapteurDAO implements DaoInterface<Capteur, String> {



	@Override
	public int create(Capteur objetMetier) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Capteur getOne(String idMetier) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(String idMetier, Capteur objetMetier) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Capteur> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String idMetier) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	private Capteur chargerUnEnregistrement(ResultSet rs) {
		Capteur capteur = new Capteur();
		try {

			capteur.setId(rs.getString("id"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return capteur;
	}
}
