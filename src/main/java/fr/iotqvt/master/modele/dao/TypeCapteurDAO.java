package fr.iotqvt.master.modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import fr.iotqvt.master.modele.jdbc.Jdbc;
import fr.iotqvt.master.modele.metier.TypeCapteur;

public class TypeCapteurDAO implements DaoInterface<TypeCapteur, String> {

	@Override
	public int create(TypeCapteur type) throws Exception {
		int num= 0;
        String requete = "INSERT IGNORE INTO typecapteur (libelle, unite) VALUES (?,?)";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);

            ps.setString(1, type.getLibelle());
            ps.setString(2, type.getUnite());
            num =  ps.executeUpdate();

            return num;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return num;
            
        }
	}

	@Override
	public TypeCapteur getOne(String libelle) throws Exception {
		TypeCapteur result = null;
		ResultSet rs;
		// préparer la requête
		String requete = "SELECT * FROM typecapteur WHERE libelle=? ";
		try {
			PreparedStatement ps = Jdbc.getInstance().getConnexion()
					.prepareStatement(requete);
			ps.setString(1,libelle);
			rs = ps.executeQuery();
			// Charger les enregistrements dans la collection
			while (rs.next()) {
				result = chargerUnEnregistrement(rs);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}



	@Override
	public Collection<TypeCapteur> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(String idMetier, TypeCapteur objetMetier)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String idMetier) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	private TypeCapteur chargerUnEnregistrement(ResultSet rs) {
		TypeCapteur type = new TypeCapteur();
		try {
			type.setLibelle(rs.getString("libelle"));
			type.setUnite(rs.getString("unite"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return type;
	}
}
