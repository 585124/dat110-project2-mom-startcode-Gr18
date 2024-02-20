package no.hvl.dat110.messages;

public class ConnectMsg extends Message {
	
	// message sent by the client when connecting to the broker
	public ConnectMsg (String user, Object o) {
		super(MessageType.CONNECT, user, null);
	}
	
}
