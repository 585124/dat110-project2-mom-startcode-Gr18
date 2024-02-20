package no.hvl.dat110.messages;

import com.google.gson.*;

import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messages.*;
import no.hvl.dat110.messagetransport.Connection;
import no.hvl.dat110.messagetransport.TransportMessage;

public class MessageUtils {

	public static Message fromJson(String msg) {

		JsonParser jsonParser = new JsonParser();
		JsonObject json = jsonParser.parse(msg).getAsJsonObject();

		String typestr = json.get("type").getAsString();

		if (typestr != null) {
			MessageType type = MessageType.valueOf(typestr);

			Gson gson = new Gson();
			Message message = null;

			switch (type) {

				case CONNECT:
					String user = json.has("user") ? json.get("user").getAsString() : null;
					String messageContent = null;
					if (json.has("message") && !json.get("message").isJsonNull()) {
						messageContent = json.get("message").getAsString();
					}
					message = new ConnectMsg(user, messageContent);
				break;

				case DISCONNECT:
					message = gson.fromJson(json, DisconnectMsg.class);
					break;

				case CREATETOPIC:
					message = gson.fromJson(json, CreateTopicMsg.class);
					break;

				case DELETETOPIC:
					message = gson.fromJson(json, DeleteTopicMsg.class);
					break;

				case SUBSCRIBE:
					message = gson.fromJson(json, SubscribeMsg.class);
					break;

				case UNSUBSCRIBE:
					message = gson.fromJson(json, UnsubscribeMsg.class);
					break;

				case PUBLISH:
					//message = gson.fromJson(json, PublishMsg.class);
					// Sjekk om "message" nøkkelen eksisterer før du prøver å hente ut verdien
					//String publishMessage = json.has("message") ? json.get("message").getAsString() : null;
					//message = new PublishMsg(json.get("user").getAsString(), publishMessage);


					// Check if "message" key exists and its value is not null
					if (json.has("message")) {
						JsonElement messageElement = json.get("message");
						if (messageElement != null && !messageElement.isJsonNull()) {
							String publishMessage = messageElement.getAsString();
							message = new PublishMsg(json.get("user").getAsString(), publishMessage);
						} else {
							// Assign a default value if "message" key exists but its value is null
							message = new PublishMsg(json.get("user").getAsString(), "Default message");
						}
					} else {
						// Log a message indicating that the "message" key is not present in the JSON object
						Logger.log("The 'message' key is not present in the JSON object: " + json);
						// Handle this case accordingly, e.g., throw an exception or assign a default value
					}
					break;


				default:
					Logger.log("fromJson - unknown message type");
					break;
			}

			return message;
		} else {
			Logger.log("fromJson - type is not found in JSON object");
			return null;
		}
	}

	public static Message fromBytes(byte[] payload) {
		
		return (fromJson (new String(payload)));
	}
	
	public static String toJson(Message msg) {

		Gson gson = new Gson();

		String json = gson.toJson(msg);

		return json;
	}
	
	public static byte[] getBytes(Message msg) {
		
		return toJson(msg).getBytes();
				
	}
	
	public static TransportMessage toTransportMessage(Message msg) {
		
		return new TransportMessage(getBytes(msg));
	}
	
	public static Message fromTransportMessage(TransportMessage msg) {
		
		return fromBytes(msg.getData());
	}

	public static void send (Connection connection, Message message) {
		connection.send(toTransportMessage(message));
	}
	
	public static Message receive (Connection connection) {
		
		Logger.log("?");
		
		Message msg = fromTransportMessage (connection.receive());
		
		Logger.log(msg.toString());
		
		return msg;
	}
}
