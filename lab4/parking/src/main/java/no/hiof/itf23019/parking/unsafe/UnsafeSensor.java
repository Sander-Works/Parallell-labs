package no.hiof.itf23019.parking.unsafe;

import java.util.concurrent.TimeUnit;

public class UnsafeSensor implements Runnable {

	private UnsafeParkingStats stats;

	public UnsafeSensor(UnsafeParkingStats stats) {
		this.stats = stats;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			stats.carComeIn();
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stats.motoComeIn();
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stats.motoGoOut();
			stats.carGoOut();
		}
	}

}
