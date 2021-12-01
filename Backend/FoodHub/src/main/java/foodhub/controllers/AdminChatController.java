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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import foodhub.database.AMessage;
import foodhub.database.AMessageRepository;

@Controller
@ServerEndpoint(value = "/chat/{username}") 
public class AdminChatController {

	private static AMessageRepository msgRepo; 

	@Autowired
	public void setMessageRepository(AMessageRepository repo) {
		msgRepo = repo; 
	}

	private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
	private static Map<String, Session> usernameSessionMap = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(AdminChatController.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) 
      throws IOException {
		logger.info("Entered into Open");

		sessionUsernameMap.put(session, username);
		usernameSessionMap.put(username, session);

		//Send chat history to the newly connected Admin
		sendMessageToParticularUser(username, getChatHistory());
		
		//Tell all Admins new Admin has joined
		String message = "Admin:" + username + " has Joined the Group";
		broadcast(message);
	}


	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		logger.info("Entered into Message: Got Message:" + message);
		String username = sessionUsernameMap.get(session);

		//Direct message to a user using the format "@username <message>"
		if (message.startsWith("@")) {
			String destUsername = message.split(" ")[0].substring(1); 

			//allow direct messages to be shown in same admin chat
			sendMessageToParticularUser(destUsername, "[" + username + " whispered to you]: " + message);
			sendMessageToParticularUser(username, "[you whispered to " + destUsername + "]: " + message);

		} 
    else { broadcast(username + ": " + message); }
	msgRepo.save(new AMessage(username, message));
	}


	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Close");

		//remove the user connection information
		String username = sessionUsernameMap.get(session);
		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(username);

		//tell everyone who just left
		String message = "Admin " + username + " disconnected.";
		broadcast(message);
	}


	@OnError
	public void onError(Session session, Throwable throwable) {
		// Do error handling here
		logger.info("Entered into Error");
		throwable.printStackTrace();
	}


	private void sendMessageToParticularUser(String username, String message) {
		try {
			usernameSessionMap.get(username).getBasicRemote().sendText(message);
		} 
    catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}


	private void broadcast(String message) {
		sessionUsernameMap.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText(message);
			} 
			catch (IOException e) {
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}
		});
	}
	

  // Gets the Chat history from the repository 
	private String getChatHistory() {
		List<AMessage> messages = msgRepo.findAll();
    
		StringBuilder sb = new StringBuilder();
		if(messages != null && messages.size() != 0) {
			for (AMessage message : messages) {
				sb.append(message.getUsername() + ": " + message.getContent() + "\n");
			}
		}
		return sb.toString();
	}

}
