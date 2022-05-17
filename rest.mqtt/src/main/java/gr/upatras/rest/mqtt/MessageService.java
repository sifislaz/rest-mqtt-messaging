package gr.upatras.rest.mqtt;

import org.springframework.stereotype.Service;

/**
 * @author jlaza
 *
 */
@Service
public class MessageService implements IMessageService{
	
	int ix = 1000;
	
	Message message;
	
	public MessageService() {
		super();
		
	}
	
	/**
	 * @return
	 */
	@Override
	public Message findMessage() {
		new MyMqttClient().runClient(message);
		return message;
	}
	
	@Override
	public Message addMessage(Message m) {
		m.setId(++ix);
		message = m;
		return m;
	}
	
	
}
