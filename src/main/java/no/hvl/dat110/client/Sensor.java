package no.hvl.dat110.client;

import no.hvl.dat110.iotsystem.Common;

public class Sensor extends Client{
    public Sensor(String server, int port) {
        super("sensor", server, port);
    }

    public void startSensor(){
        if(connect()){
            subscribe(Common.TEMPTOPIC);
            //Logikk for å lese av temperaturdata og publisere det periodevis(?)
            while (true) {
                double temperature = readTemperature();
                publish(Common.TEMPTOPIC, Double.toString(temperature));
                try {
                    Thread.sleep(5000); // Sleep for 5 sekunder mellom hver avlesning
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private double readTemperature() {
        //Logikk for å lese av temperaturdata fra sensor

        //!!HER MÅ VI GJØRE NOE

        return 25.5; // tilfeldig eksempelvis temperatur
    }

    public static void main(String[] args) {
        Sensor sensorClient = new Sensor(Common.BROKERHOST, Common.BROKERPORT);
        sensorClient.startSensor();
    }
}