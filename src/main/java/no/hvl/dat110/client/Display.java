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
            // Logikk for displaying temperaturdata motattt fra sensoren
            while (true) {
                String message = receive().getMessage();
                System.out.println("Received temperature: " + message);
            }
        }
    }

    public static void main(String[] args) {
        Display displayClient = new Display(Common.BROKERHOST, Common.BROKERPORT);
        displayClient.startDisplay();
    }
}

