package fr.iotqvt.master.modele.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import fr.iotqvt.master.modele.jdbc.Jdbc;
import fr.iotqvt.master.modele.metier.IOT;

public class IOTDao implements DaoInterface<IOT, String> {


	@Override
	public int create(IOT iot) throws Exception {

        String requete = "INSERT INTO iot (id,  Piece_id) VALUES (?, ?)";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);

            ps.setString(1, iot.getId());
            ps.setNull(2, Types.VARCHAR);
            ps.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
            
        }
      
	}

	@Override
	public IOT getOne(String idMetier) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IOT> getAll() throws Exception {
		ArrayList<IOT> result = new ArrayList<IOT>();
		ResultSet rs;
		// préparer la requête
		String requete = "SELECT * FROM IOT";
		try {
			PreparedStatement ps = Jdbc.getInstance().getConnexion()
					.prepareStatement(requete);
			rs = ps.executeQuery();
			// Charger les enregistrements dans la collection
			while (rs.next()) {
				IOT iot = chargerUnEnregistrement(rs);
				result.add(iot);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(String idMetier, IOT objetMetier) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String idMetier) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	private IOT chargerUnEnregistrement(ResultSet rs) {
		IOT iot = new IOT();
		try {

			iot.setId(rs.getString("id"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return iot;
	}
}
