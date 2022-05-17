package gr.upatras.rest.mqtt;

public interface IMessageService {
	public Message findMessage();
	public Message addMessage(Message m);
}
