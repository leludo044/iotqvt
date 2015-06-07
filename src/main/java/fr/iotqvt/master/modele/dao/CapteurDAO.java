package fr.iotqvt.master.modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import fr.iotqvt.master.modele.jdbc.Jdbc;
import fr.iotqvt.master.modele.metier.Capteur;

public class CapteurDAO implements DaoInterface<Capteur, String> {



	@Override
	public int create(Capteur capteur) throws Exception {
		int num= 0;
        String requete = "INSERT IGNORE INTO capteur (id, typecapteur_id, iot_id) VALUES (?, ?,?)";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);

            ps.setString(1, capteur.getId());
            ps.setNull(2, Types.INTEGER);
            ps.setString(3, capteur.getIot());
            num =  ps.executeUpdate();

            return num;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return num;
            
        }
      
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
	
	public Collection<Capteur> getByIOT(String idIOT) throws Exception {
		ArrayList<Capteur> result = new ArrayList<Capteur>();
		ResultSet rs;
		// pr�parer la requ�te
		String requete = "SELECT * FROM capteur WHERE iot_id=?";
		try {
			PreparedStatement ps = Jdbc.getInstance().getConnexion()
					.prepareStatement(requete);
			ps.setString(1, idIOT);
			rs = ps.executeQuery();
			// Charger les enregistrements dans la collection
			while (rs.next()) {
				Capteur capteur = chargerUnEnregistrement(rs);
				result.add(capteur);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	private Capteur chargerUnEnregistrement(ResultSet rs) {
		Capteur capteur = new Capteur();
		try {

			capteur.setId(rs.getString("id"));
			capteur.setIot(rs.getString("iot_id"));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return capteur;
	}
}
