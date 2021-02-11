package no.hiof.itf23019.parking;

import no.hiof.itf23019.parking.safe.SafeParkingCash;
import no.hiof.itf23019.parking.safe.SafeParkingStats;
import no.hiof.itf23019.parking.safe.SafeSensor;

public class SafeMain {

	public static void main(String[] args) {
		SafeParkingCash cash = new SafeParkingCash();
		SafeParkingStats stats = new SafeParkingStats(cash);

		System.out.printf("Parking Simulator\n");
		int numberSensors=2 * Runtime.getRuntime().availableProcessors();
		System.out.println("numberSensors=" + numberSensors);
		Thread threads[]=new Thread[numberSensors];
		for (int i = 0; i < numberSensors; i++) {
			SafeSensor sensor=new SafeSensor(stats);
			Thread thread=new Thread(sensor);
			thread.start();
			threads[i]=thread;
		}
		
		for (int i=0; i< numberSensors; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.printf("Number of cars: %d\n", stats.getNumberCars());
		System.out.printf("Number of motorcycles: %d\n", stats.getNumberMotorcycles());
		cash.close();
	}
}

