package no.hvl.dat110.messages;

public class CreateTopicMsg extends Message {
	private String topic;
	// message sent from client to create topic on the broker

    /**
     * Her har Bodil endret, OBS!
     * @param user
     * @param topic
     */
    public CreateTopicMsg(String user, String topic) {
        super(MessageType.CREATETOPIC, user);
        this.topic = topic;
    }


    /*
    * Så vidt startet på Task A - getter + setter og toString
     */
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "CreateTopicMsg{" +
                "user='" + getUser() + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
