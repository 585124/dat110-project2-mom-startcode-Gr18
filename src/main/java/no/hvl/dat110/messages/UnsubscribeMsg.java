package no.hvl.dat110.messages;

public class UnsubscribeMsg extends Message {

	// message sent from client to unsubscribe on a topic 
    private String topicName;

    public UnsubscribeMsg(String user, String topic) {

        super(user);
        //this.topicName = topicName;
    }


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
        return "UnsubscribeMsg{" +
                "user='" + getUser() + '\'' +
                ", topicName='" + topicName + '\'' +
                '}';
    }
}
