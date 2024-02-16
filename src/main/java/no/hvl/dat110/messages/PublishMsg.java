package no.hvl.dat110.messages;

import no.hvl.dat110.common.TODO;

public class PublishMsg extends Message {
	
	// message sent from client to create publish a message on a topic 
	private String topicName;
	private String message;

	public PublishMsg(String user, String topic, String message) {

        super(user);
		this.topicName = topicName;
		this.message = message;
    }

	// TODO:
	// Implement object variables - a topic and a message is required

	// Complete the constructor, get/set-methods, and toString method
	// as described in the project text
	
	public String getMessage() {

		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}
	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	@Override
	public String toString() {
		return "PublishMsg{" +
				"user='" + getUser() + '\'' +
				", topicName='" + topicName + '\'' +
				", message='" + message + '\'' +
				'}';
	}
}
