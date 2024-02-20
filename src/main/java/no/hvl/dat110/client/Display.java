package no.hvl.dat110.client;

import no.hvl.dat110.iotsystem.Common;
import no.hvl.dat110.messages.Message;

public class Display extends Client {

    public Display(String server, int port) {
        super("display", server, port);
    }

    public void startDisplay() {
        if (connect()) { // Koble til megleren
            subscribe(Common.TEMPTOPIC); // Abonner på temperatur-temaet

            // Sett opp en teller for å holde styr på antall meldinger mottatt
            int messagesReceived = 0;

            // Maksimalt antall meldinger som skal mottas
            int maxMessages = 10; // Endre dette til ønsket antall meldinger!!

            // Mottak av meldinger
            while (messagesReceived < maxMessages) {
                Message msg = receive(); // Motta melding fra megleren
                if (msg != null) {
                    System.out.println("Mottatt temperatur: " + msg.getMessage());
                    messagesReceived++;
                }
            }

            // Avslutt klienten etter å ha mottatt ønsket antall meldinger
            disconnect();
        }
    }

    public static void main(String[] args) {
        Display displayClient = new Display(Common.BROKERHOST, Common.BROKERPORT);
        displayClient.startDisplay();
    }
}
