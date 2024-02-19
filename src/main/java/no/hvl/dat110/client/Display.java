package no.hvl.dat110.client;
import no.hvl.dat110.client.Client;
import no.hvl.dat110.iotsystem.Common;

public class Display extends Client{
    public Display(String server, int port) {
        super("display", server, port);
    }

    public void startDisplay(){
        if(connect()){
            subscribe(Common.TEMPTOPIC);

            // Sett opp en teller for å holde styr på antall meldinger mottatt
            int messagesReceived = 0;

            // Maksimalt antall meldinger som skal mottas
            int maxMessages = 10; // Endre dette til ønsket antall meldinger!!

            // Mottak av meldinger
            while (messagesReceived < maxMessages) {
                String message = receive().getMessage();
                System.out.println("Received temperature: " + message);
                messagesReceived++;
            }
            //avslutter klienten etter å ha motatt ønsket antall meldigner
            disconnect();
        }
    }

    public static void main(String[] args) {
        Display displayClient = new Display(Common.BROKERHOST, Common.BROKERPORT);
        displayClient.startDisplay();
    }
}
