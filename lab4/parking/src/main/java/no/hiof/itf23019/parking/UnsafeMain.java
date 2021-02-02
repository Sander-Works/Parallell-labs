package no.hiof.itf23019.parking;

import no.hiof.itf23019.parking.unsafe.UnsafeParkingCash;
import no.hiof.itf23019.parking.unsafe.UnsafeParkingStats;
import no.hiof.itf23019.parking.unsafe.UnsafeSensor;

public class UnsafeMain {

	public static void main(String[] args) {

		UnsafeParkingCash cash = new UnsafeParkingCash();
		UnsafeParkingStats stats = new UnsafeParkingStats(cash);

		System.out.printf("Parking Simulator\n");
		int numberSensors=2 * Runtime.getRuntime().availableProcessors();
		System.out.println("numberSensors=" + numberSensors);
		Thread threads[]=new Thread[numberSensors];
		for (int i = 0; i < numberSensors; i++) {
			UnsafeSensor sensor=new UnsafeSensor(stats);
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
