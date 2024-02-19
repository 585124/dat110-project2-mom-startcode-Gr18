package no.hvl.dat110.client;

import no.hvl.dat110.iotsystem.Common;
import no.hvl.dat110.iotsystem.TemperatureSensor;

public class Sensor extends Client {

    private TemperatureSensor temperatureSensor;

    public Sensor(String server, int port) {
        super("sensor", server, port);
        this.temperatureSensor = new TemperatureSensor();
    }

    public void startSensor() {
        if (connect()) {
            subscribe(Common.TEMPTOPIC);

            // Les temperaturdata og publiser det periodisk
            while (true) {
                int temperature = temperatureSensor.read();
                publish(Common.TEMPTOPIC, Integer.toString(temperature));
                try {
                    Thread.sleep(5000); // Vent i 5 sekunder mellom hver avlesning
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Sensor sensorClient = new Sensor(Common.BROKERHOST, Common.BROKERPORT);
        sensorClient.startSensor();
    }
}