package no.hvl.dat110.messages;

public class DeleteTopicMsg extends Message {
    private String topicName;

	// message sent from client to create topic on the broker

    public DeleteTopicMsg(String user, String topic) {

        super(user);
        this.topicName = topicName;
    }

	// TODO:
	// Implement object variables - a topic is required

	// Complete the constructor, get/set-methods, and toString method
	// as described in the project text
/*
* Task A - getter og setter + toString
 */
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public String toString() {
        return "DeleteTopicMsg{" +
                "user='" + getUser() + '\'' +
                ", topicName='" + topicName + '\'' +
                '}';
    }
}
