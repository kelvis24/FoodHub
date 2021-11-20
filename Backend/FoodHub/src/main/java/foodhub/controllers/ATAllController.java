package foodhub.controllers;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;

import foodhub.database.*;

@ServerEndpoint("/ATAll/")
@Component

public class ATAllController {
	
	@Autowired private AMessageRepository aRepository;

	private static Map<Session,Long> SIM = new Hashtable<>();
	private static Map<Long,Session> ISM = new Hashtable<>();
	private static Map<Long,Integer> SQM = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(ATAllController.class);

	@OnOpen	public void onOpen(Session session) throws IOException {
	}

	@OnMessage public void onMessage(Session session, String message) throws IOException {
	}

	@OnClose public void onClose(Session session) throws IOException {
		long id = SIM.get(session);
		SIM.remove(session);
		ISM.remove(id);
		SQM.remove(id);
	}

	@OnError public void onError(Session session, Throwable throwable) {
		logger.info("Entered into Error");
	}
	
	public static boolean hasId(long id) {
		return SQM.get(id) != null;
	}
	
	public static int getSequence(long id) {
		return SQM.get(id);
	}
	
	public static void setSequence(long id, int sequence) {
		SQM.replace(id,sequence);
	}
	
	public static void sendMessage(long id, String message) {
		Session session = ISM.get(id);
		if (session != null) {
			try{session.getBasicRemote().sendText(message);
			}catch(IOException e){e.printStackTrace();}
		}
	}
	
}
