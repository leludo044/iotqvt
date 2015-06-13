package fr.iotqvt.master.websocket;

import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import fr.iotqvt.master.modele.dao.CapteurDAO;
import fr.iotqvt.master.modele.dao.IOTDAO;
import fr.iotqvt.master.modele.dao.MesureDAO;
import fr.iotqvt.master.modele.jdbc.Jdbc;
import fr.iotqvt.master.modele.metier.IOT;
import fr.iotqvt.master.modele.metier.Mesure;

@ServerEndpoint(value = "/websocket/mesure")
public class WebsocketServer {

	private static final String MESSAGE_MESURE_PREFIXE = "{\"type\":\"mesure\"";
	private static final String MESSAGE_IDENTIFICATION_PREFIXE = "{\"type\":\"identification\"";

	private static final Set<Session> sessions = new CopyOnWriteArraySet<Session>();
	private Session session;

	@OnOpen
	public void start(Session session) {
		this.session = session;
		sessions.add(session);
		System.out.println("start");
	}

	@OnClose
	public void end() {
		sessions.remove(session);
		System.out.println("end");
	}

	@OnMessage
	public void incoming(String json) {
		System.out.println("onMessage" + json);
		try {
			if (json.startsWith(MESSAGE_MESURE_PREFIXE)) {
				Gson gson = new Gson();
				MesureMessage msg = gson.fromJson(json, MesureMessage.class);
				Mesure m = msg.getMesure();
				if (m.getCapteur() != null) {
					try {
						Jdbc.getInstance().connecter();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// TODO a déporter dans l'identification
//					IOTDAO iotDao = new IOTDAO();
//					CapteurDAO capteurDao = new CapteurDAO();
//					iotDao.create(new IOT(m.getCapteur().getIot()));
//					capteurDao.create(m.getCapteur());
					// FINTODO
					
					MesureDAO mesuredao = new MesureDAO();
					mesuredao.create(m);
					broadcastText(json);
//					try {
//						Jdbc.getInstance().deconnecter();
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				}
			} else if (json.startsWith(MESSAGE_IDENTIFICATION_PREFIXE)) {
				System.out.println("Identification reçue");
				Gson gson = new Gson();
				IdentificationMessage msg = gson.fromJson(json, IdentificationMessage.class);
				identifier(msg) ;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		System.out.println("error");
		System.out.println(t.getMessage());
		t.printStackTrace();
	}

	public void broadcast(Mesure m) {
		Gson gson = new Gson();
		String json = gson.toJson(m);

		for (Session uneSession : sessions) {
			uneSession.getAsyncRemote().sendText(json);
		}
	}

	public void broadcastText(String json) {
		for (Session uneSession : sessions) {
			uneSession.getAsyncRemote().sendText(json);
		}
	}
	
	
	private void identifier(IdentificationMessage msg) throws Exception {
		System.out.println("Identification : "+msg.getCedec());
		// TODO Persister en base
		try {
			Jdbc.getInstance().connecter();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		IOTDAO iotDao = new IOTDAO();
		iotDao.create(msg.getCedec());
		try {
			Jdbc.getInstance().deconnecter();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
