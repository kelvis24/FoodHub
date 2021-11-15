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

@ServerEndpoint("/OTC/{orderId}")
@Component
public class OTCController {
	
	@Autowired
	private OTMessageRepository otmRepository;

	private static Map<Session,Long> SIM = new Hashtable<>();
	private static Map<Long,Session> ISM = new Hashtable<>();
	private static Map<Long,Integer> SQM = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(OTCController.class);

	@OnOpen	public void onOpen(Session session, @PathParam("orderId") String orderId) throws IOException {
		long id = Long.parseLong(orderId);
		if (ISM.get(id) != null) return;
		int sequence;
		if (OTFController.hasId(id)) {
			sequence = OTFController.getSequence(id);
		} else {
			List<OTMessage> list = otmRepository.findByOrderId(id);
			sequence = list.size();
		}
		ISM.put(id,session);
		SIM.put(session,id);
		SQM.put(id,sequence);
	}

	@OnMessage public void onMessage(Session session, String message) throws IOException {
		
	}

	@OnClose public void onClose(Session session) throws IOException {
		long id = SIM.get(session);
		SIM.remove(session);
		ISM.remove(id);
		SQM.remove(id);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		logger.info("Entered into Error");
	}
	
	/*
	private static Map<Session,String> sessionUsernameMap = new Hashtable<>();
	private static Map<String,Session> usernameSessionMap = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(OrderTextController.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		logger.info("Entered into Open");

		sessionUsernameMap.put(session, username);
		usernameSessionMap.put(username, session);

		String message = "User:" + username + " has Joined the Chat";
		broadcast(message);
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		logger.info("Entered into Message: Got Message:" + message);
		String username = sessionUsernameMap.get(session);

		if (message.startsWith("@")) {
			String destUsername = message.split(" ")[0].substring(1);
			sendMessageToPArticularUser(destUsername, "[DM] " + username + ": " + message);
			sendMessageToPArticularUser(username, "[DM] " + username + ": " + message);
		} else {
			broadcast(username + ": " + message);
		}
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Close");
		
		String username = sessionUsernameMap.get(session);
		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(username);

		String message = username + " disconnected";
		broadcast(message);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		logger.info("Entered into Error");
	}

	private void sendMessageToPArticularUser(String username, String message) {
		try {
			usernameSessionMap.get(username).getBasicRemote().sendText(message);
		} catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}

	private void broadcast(String message) {
		sessionUsernameMap.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}
		});
	}
	*/
	
}
