package fr.iotqvt.master.websocket;


import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import fr.iotqvt.master.modele.metier.Mesure;


@ServerEndpoint(value = "/websocket/mesure")
public class WebsocketServer {

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
		System.out.println("onMessage"+json);
		try{
			Gson gson = new Gson();
			Mesure m = gson.fromJson(json, Mesure.class);
			if(m.getCapteur() != null){
			
				broadcastText(json);
			}
	
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@OnError
	public void onError(Throwable t) throws Throwable {
		System.out.println("error");
		System.out.println(t.getMessage());
		t.printStackTrace();
	}
	public void broadcast(Mesure m){
		Gson gson = new Gson();
		String json = gson.toJson(m);
		
		for(Session uneSession : sessions){
			uneSession.getAsyncRemote().sendText(json);
		}
	}
	public void broadcastText(String json){
	
		
		for(Session uneSession : sessions){
			uneSession.getAsyncRemote().sendText(json);
		}
	}
}
