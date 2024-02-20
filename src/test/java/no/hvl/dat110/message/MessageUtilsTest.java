package no.hvl.dat110.message;

import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.MessageUtils;
import no.hvl.dat110.messages.PublishMsg;

public class MessageUtilsTest {
    //Laget en test for å sjekke tilfeller der json objektet ble null og hvordan håndtere det
    //Dette er ikke en del av innleveringen

        public static void main(String[] args) {
            testNullMessageValue();
        }

        public static void testNullMessageValue() {
            String jsonWithNullMessage = "{\"type\":\"PUBLISH\",\"user\":\"testuser\",\"message\":null}";
            Message message = MessageUtils.fromJson(jsonWithNullMessage);

            if (message instanceof PublishMsg) {
                PublishMsg publishMsg = (PublishMsg) message;
                System.out.println("User: " + publishMsg.getUser());
                System.out.println("Message: " + publishMsg.getMessage());
            } else {
                System.out.println("Failed to parse message or unexpected message type.");
            }
        }
    }


