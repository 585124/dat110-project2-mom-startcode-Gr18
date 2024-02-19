package no.hvl.dat110.messages;

public abstract class Message {

	// base class for messages exchanged between broker and clients
	private MessageType type;
	private String user;
	private String message;
	
	public Message(String user) {
		
	}
	
	public Message(MessageType type, String user, String message) {
		this.type = type;
		this.user = user;
		this.message = message;

	}

	public MessageType getType() { return this.type; }

	
	public String getUser() {
		return user;
	}


	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "Message [type=" + type + ", user=" + user + ", message=" + message + "]";
	};



}
