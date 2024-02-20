package no.hvl.dat110.client;

import no.hvl.dat110.iotsystem.Common;
import no.hvl.dat110.iotsystem.TemperatureSensor;

public class Sensor extends Client {

    private final TemperatureSensor temperatureSensor;

    public Sensor(String server, int port) {
        super("sensor", server, port);
        this.temperatureSensor = new TemperatureSensor();
    }

    public void startSensor() {
            if (connect()) { // Koble til broker
                subscribe(Common.TEMPTOPIC); // Abonner på temperatur-temaet

                final int ANTALL_GANGER = 10; // Definer antall iterasjoner

                for (int i = 0; i < ANTALL_GANGER; i++) { // Løkke ANTALL_GANGER ganger
                    int temperatur = temperatureSensor.read(); // Les temperaturdata
                    publish(Common.TEMPTOPIC, Integer.toString(temperatur)); // Publiser temperaturdataen
                    try {
                        Thread.sleep(5000); // Vent i 5 sekunder før neste iterasjon
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                disconnect(); // Koble fra broker etter at løkken er fullført
            }
        }


        public static void main(String[] args) {
        Sensor sensorClient = new Sensor(Common.BROKERHOST, Common.BROKERPORT);
        sensorClient.startSensor();
    }
}