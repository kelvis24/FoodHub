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

@ServerEndpoint("/OTF/{orderId}")
@Component
public class OTFController {
	
    private static OrderRepository orderRepository;
	
    private static OTMessageRepository otmRepository;

    @Autowired public void setOrderRepository(OrderRepository orderRepository){
		this.orderRepository = orderRepository;
	}
  
   @Autowired public void setOTMessageRepository(OTMessageRepository otmRepository){
		this.otmRepository = otmRepository;
	}

	private static Map<Session,Long> SIM = new Hashtable<>();
	private static Map<Long,Session> ISM = new Hashtable<>();
	private static Map<Long,Integer> SQM = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(OTFController.class);

	@OnOpen	public void onOpen(Session session, @PathParam("orderId") String orderId) throws IOException {
		long id = Long.parseLong(orderId);
		if (ISM.get(id) != null) onClose(ISM.get(id));
		System.out.println("OTF Opened");
		int sequence;
		if (OTCController.hasId(id)) {
			sequence = OTCController.getSequence(id);
		} else {
			List<OTMessage> list = otmRepository.findByOrderId(id);
			sequence = list.size();
		}
		ISM.put(id,session);
		SIM.put(session,id);
		SQM.put(id,sequence);
	}

	@OnMessage public void onMessage(Session session, String message) throws IOException {
		System.out.println("OTF Messaged");
		long id = SIM.get(session);
		if (orderRepository.findById(id) != null) {
			int sequence = SQM.get(id);
			OTMessage otmessage = new OTMessage(id,++sequence,0,message);
			otmRepository.save(otmessage);
			SQM.replace(id,sequence);
			OTCController.setSequence(id,sequence);
		}
		OTCController.sendMessage(id,message);
	}

	@OnClose public void onClose(Session session) throws IOException {
		System.out.println("OTF Closed");
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
