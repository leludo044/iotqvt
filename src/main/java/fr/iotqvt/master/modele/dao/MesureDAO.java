package fr.iotqvt.master.modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import fr.iotqvt.master.modele.jdbc.Jdbc;
import fr.iotqvt.master.modele.metier.Mesure;

public class MesureDAO implements DaoInterface<Mesure, String> {


	@Override
	public int create(Mesure mesure) throws Exception {
		int num= 0;
        String requete = "INSERT INTO mesure (valeur, date, capteur_id, capteur_iot_id) VALUES (?, ?,?,?)";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setFloat(1, mesure.getValeur());
            ps.setTimestamp(2,new Timestamp(mesure.getDate()));
            ps.setString(3, mesure.getCapteur().getId());
            ps.setString(4, mesure.getCapteur().getIot());
            num = ps.executeUpdate();

            return num;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return num;
            
        }
	}

	@Override
	public Mesure getOne(String idMetier) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(String idMetier, Mesure objetMetier) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Mesure> getAll() throws Exception {
		ArrayList<Mesure> result = new ArrayList<Mesure>();
		ResultSet rs;
		// préparer la requête
		String requete = "SELECT * FROM mesure ORDER BY date";
		try {
			PreparedStatement ps = Jdbc.getInstance().getConnexion()
					.prepareStatement(requete);

			rs = ps.executeQuery();
			// Charger les enregistrements dans la collection
			while (rs.next()) {
				Mesure mesure = chargerUnEnregistrement(rs);
				result.add(mesure);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(String idMetier) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	

	public Collection<Mesure> getByCapteur(String capteurId, String iotId) throws Exception {
		ArrayList<Mesure> result = new ArrayList<Mesure>();
		ResultSet rs;
		// préparer la requête
		String requete = "SELECT * FROM mesure  WHERE capteur_id=? and capteur_iot_id=?";
		try {
			PreparedStatement ps = Jdbc.getInstance().getConnexion()
					.prepareStatement(requete);
			ps.setString(1, capteurId);
			ps.setString(2, iotId);
			rs = ps.executeQuery();
			// Charger les enregistrements dans la collection
			while (rs.next()) {
				Mesure mesure = chargerUnEnregistrement(rs);
				result.add(mesure);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	private Mesure chargerUnEnregistrement(ResultSet rs) {
		Mesure mesure = new Mesure();
		CapteurDAO capteurDAO = new CapteurDAO();
		try {
			mesure.setValeur(rs.getFloat("valeur"));
			mesure.setDate(rs.getTimestamp("date").getTime());
//			Capteur capteur = new Capteur();
//			capteur.setId(rs.getString("capteur_id"));
//			capteur.setIot(rs.getString("capteur_iot_id"));
			mesure.setCapteur(capteurDAO.getOne(rs.getString("capteur_iot_id"), rs.getString("capteur_id")));
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mesure;
	}

}
