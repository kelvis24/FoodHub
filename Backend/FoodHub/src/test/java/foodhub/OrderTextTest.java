package foodhub;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import foodhub.database.*;
import foodhub.controllers.*;
import foodhub.ioObjects.*;

@SpringBootTest
public class OrderTextTest {
	
	@InjectMocks OTCController otc;
	
	@InjectMocks OTFController otf;
	
	@InjectMocks CustomerController cc;
	
	@InjectMocks FirmController fc;
	
	@Mock CustomerRepository customerRepository;
	
	@Mock FirmRepository firmRepository;

	@Mock OrderRepository orderRepository;
	
	@Mock OTMessageRepository otmRepository;
	
	@Mock Session customerSession;

	@Mock Session firmSession;

	private List<Customer> cl;
	private List<Firm> fl;
	private List<OTMessage> ol;
	
	private List<String> backlog;
	
	@BeforeEach
	public void init() {
		cl = new ArrayList<Customer>();
		when(customerRepository.findAll()).thenReturn(cl);
		when(customerRepository.findByUsername((String)any(String.class)))
		.thenAnswer(x-> {
			String username = x.getArgument(0);
			Iterator<Customer> itr = cl.iterator();
			while (itr.hasNext()) {
				Customer c = itr.next();
				if (username.equals(c.getUsername()))
					return c;
			}
			return null;
		});
		when(customerRepository.save((Customer)any(Customer.class)))
		.thenAnswer(x -> {
			Customer c = x.getArgument(0);
			cl.add(c);
			return null;
		});
		fl = new ArrayList<Firm>();
		when(firmRepository.findAll()).thenReturn(fl);
		when(firmRepository.findByUsername((String)any(String.class)))
		.thenAnswer(x-> {
			String username = x.getArgument(0);
			Iterator<Firm> itr = fl.iterator();
			while (itr.hasNext()) {
				Firm f = itr.next();
				if (username.equals(f.getUsername()))
					return f;
			}
			return null;
		});
		when(firmRepository.save((Firm)any(Firm.class)))
		.thenAnswer(x -> {
			Firm f = x.getArgument(0);
			fl.add(f);
			return null;
		});
		ol = new ArrayList<OTMessage>();
		when(otmRepository.findAll()).thenReturn(ol);
		when(otmRepository.findByOrderId((Long)any(Long.class)))
		.thenAnswer(x-> {
			ArrayList<OTMessage> output = new ArrayList<>();
			long orderId = x.getArgument(0);
			Iterator<OTMessage> itr = ol.iterator();
			while (itr.hasNext()) {
				OTMessage otMessage = itr.next();
				if (orderId == otMessage.getOrderId())
					output.add(otMessage);
			}
			return output;
		});
		when(otmRepository.save((OTMessage)any(OTMessage.class)))
		.thenAnswer(x -> {
			OTMessage otMessage = x.getArgument(0);
			ol.add(otMessage);
			return null;
		});
		backlog = new ArrayList<String>();
		when(orderRepository.findById(2)).thenReturn(true);
		when(customerSession.getBasicRemote()).thenReturn(new FakeRemote());
		when(firmSession.getBasicRemote()).thenReturn(new FakeRemote());
		customerRepository.save(new Customer("arvidg","arvid","Arvid","Freddy"));
		firmRepository.save(new Firm("tacoh","tacos","Taco House","Taco Town","Tacos",1,2,3));
	}
	
	@Test
	public void OrderTextTest0() {
		try {
			otc.onOpen(customerSession, "2");
			otc.onMessage(customerSession,  "M1");
			// otf.onOpen(firmSession, "2");
			// otc.onMessage(customerSession,  "M2");
			// otf.onMessage(firmSession,  "M3");
			// otc.onClose(customerSession);
			// otf.onClose(firmSession);
		} catch(Exception e) {
			e.printStackTrace();
			assert false;
		}
		List<OTMessageOutput> customerMessages = cc.getOTMessages(new AuthenticationAndId("arvidg","arvid",2));
		List<OTMessageOutput> firmMessages = fc.getOTMessages(new AuthenticationAndId("tacoh","tacos",2));
	}
	
	private class FakeRemote implements RemoteEndpoint.Basic {

		@Override
		public void setBatchingAllowed(boolean batchingAllowed) throws IOException {
		}

		@Override
		public boolean getBatchingAllowed() {
			return false;
		}

		@Override
		public void flushBatch() throws IOException {
		}

		@Override
		public void sendPing(ByteBuffer applicationData) throws IOException, IllegalArgumentException {
		}

		@Override
		public void sendPong(ByteBuffer applicationData) throws IOException, IllegalArgumentException {
		}

		@Override
		public void sendText(String text) throws IOException {
			backlog.add(text);
		}

		@Override
		public void sendBinary(ByteBuffer data) throws IOException {
		}

		@Override
		public void sendText(String fragment, boolean isLast) throws IOException {
		}

		@Override
		public void sendBinary(ByteBuffer partialByte, boolean isLast) throws IOException {
		}

		@Override
		public OutputStream getSendStream() throws IOException {
			return null;
		}

		@Override
		public Writer getSendWriter() throws IOException {
			return null;
		}

		@Override
		public void sendObject(Object data) throws IOException, EncodeException {
		}
		
	}
	
}
